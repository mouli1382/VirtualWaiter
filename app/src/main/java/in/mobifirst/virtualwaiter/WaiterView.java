package in.mobifirst.virtualwaiter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Point;
import android.view.Display;
import android.media.Ringtone;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Vibrator;
import android.preference.PreferenceManager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.os.Handler;
import android.util.JsonReader;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.GridView;
import android.widget.ImageView;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.json.JSONObject;

import java.util.Iterator;

/**
 * An example full-screen activity that shows and hides the system UI (i.e.
 * status bar and navigation/system bar) with user interaction.
 */
public class WaiterView extends AppCompatActivity {
    private DatabaseReference mDatabaseReference;
    public Chair mChiarObj;
    GridView gridview;

    /**
     * Some older devices needs a small delay between UI widget updates
     * and a change of the status and navigation bar.
     */
    private static final int UI_ANIMATION_DELAY = 300;
    private final Handler mHideHandler = new Handler();
    private View mContentView;
    private final Runnable mHidePart2Runnable = new Runnable() {
        @SuppressLint("InlinedApi")
        @Override
        public void run() {
            // Delayed removal of status and navigation bar

            // Note that some of these constants are new as of API 16 (Jelly Bean)
            // and API 19 (KitKat). It is safe to use them, as they are inlined
            // at compile-time and do nothing on earlier devices.
            mContentView.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LOW_PROFILE
                    | View.SYSTEM_UI_FLAG_FULLSCREEN
                    | View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                    | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
                    | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                    | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION);
        }
    };
    private View mControlsView;
    private final Runnable mShowPart2Runnable = new Runnable() {
        @Override
        public void run() {
            // Delayed display of UI elements
            ActionBar actionBar = getSupportActionBar();
            if (actionBar != null) {
                actionBar.show();
            }
            mControlsView.setVisibility(View.VISIBLE);
        }
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        this.requestWindowFeature(Window.FEATURE_NO_TITLE);


        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

//set content view AFTER ABOVE sequence (to avoid crash)

        this.findViewById(android.R.id.content).setSystemUiVisibility(View.SYSTEM_UI_FLAG_IMMERSIVE);
        getSupportActionBar().hide();

        setContentView(R.layout.activity_waiter_view);



        // Upon interacting with UI controls, delay any scheduled hide()
        // operations to prevent the jarring behavior of controls going away
        // while interacting with the UI.
        gridview = (GridView) findViewById(R.id.imagegridview);
        //gridview.setAdapter(new GridImageAdapter(this), MainActivity.getScreenWidth(getApplicationContext()), MainActivity.getScreenHeight(getApplicationContext()));
        gridview.setAdapter(new GridImageAdapter(this, getScreenWidth(this), getScreenHeight(this)));
        String mtr=null;
        mtr = PreferenceManager.getDefaultSharedPreferences(this).getString("meeting_room_id",mtr);
        if (mtr == null)
            return;
        mDatabaseReference = FirebaseDatabase.getInstance().getReference().child(mtr);

        /*mDatabaseReference.orderByChild("chairName").addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {
                Iterator<DataSnapshot> itr =   dataSnapshot.getChildren().iterator();
                int row = -1;
                //for (Iterator<DataSnapshot> iter = dataSnapshot.getChildren().iterator(); iter.hasNext(); ) {
                for (row=0; row< dataSnapshot.getChildrenCount();row++) {
                    //Chair obj = iter.next().getValue(Chair.class);
                    if (itr.hasNext() == false)
                        break;
                    Chair obj = itr.next().getValue(Chair.class);

                    //row++;



                    //////////////////////////////////////////
                    for (int position = (row*7); position < (row*7)+7; position++) {
                        ImageView image = (ImageView) gridview.getChildAt(position+1);
                        Animation animation1 =
                                AnimationUtils.loadAnimation(getApplicationContext(),
                                        R.anim.blink);
                        int ordinal = HelpType.TEA.ordinal();

                        if (position % 7 == HelpType.TEA.ordinal()) {
                            if (obj.tea == true) {
                                image.startAnimation(animation1);

                            } else {
                                image.clearAnimation();
                            }

                        } else if (position %7 == HelpType.ATTENDER.ordinal()) {
                            if (obj.help == true) {
                                image.startAnimation(animation1);
                            } else {
                                image.clearAnimation();
                            }

                        }  else if (position %7  == HelpType.WATER.ordinal()) {
                            if (obj.water == true) {
                                image.startAnimation(animation1);
                            } else {
                                image.clearAnimation();
                            }

                        } else if (position % 7 == HelpType.FILES.ordinal()) {
                            if (obj.misc == true) {
                                image.startAnimation(animation1);
                            } else {
                                image.clearAnimation();
                            }

                        }
                        else if (position == HelpType.PEN.ordinal()) {
                            if (obj.pen == true) {
                                image.startAnimation(animation1);
                            } else {
                                image.clearAnimation();
                            }
                        }

                        }

                }

            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });*/
        //Run this to initialize the app
        //Only once while deploying


         /*for (Integer i=0; i <19; i++) {
             Chair chairObj = new Chair(i.toString(), false, false, false, false, false, false, false, false);
             mDatabaseReference.child(i.toString()).setValue(chairObj);
         }*/


        mDatabaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                Iterator<DataSnapshot> itr =   dataSnapshot.getChildren().iterator();
                Uri notification = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
                Ringtone r = RingtoneManager.getRingtone(getApplicationContext(), notification);
                r.play();
                int row = -1;
                //for (Iterator<DataSnapshot> iter = dataSnapshot.getChildren().iterator(); iter.hasNext(); ) {
                 for (row=0; (row< 2) && (itr.hasNext());row++) {
                    //Chair obj = iter.next().getValue(Chair.class);
                     if (itr.hasNext() == false)
                         break;
                     Chair obj = itr.next().getValue(Chair.class);

                   //row++;



                    //////////////////////////////////////////
                    for (int position = (row*9); position < (row*9)+9; position++) {
                        ImageView image = (ImageView) gridview.getChildAt(position+1);
                        Animation animation1 =
                                AnimationUtils.loadAnimation(getApplicationContext(),
                                        R.anim.blink);
                        int ordinal = HelpType.TEA.ordinal();

                        if (position % 9 == HelpType.TEA.ordinal()) {
                            if (obj.tea == true) {
                                image.startAnimation(animation1);

                            } else {
                                image.clearAnimation();
                            }

                        } else if (position %9 == HelpType.ATTENDER.ordinal()) {
                            if (obj.help == true) {
                                image.startAnimation(animation1);
                            } else {
                                image.clearAnimation();
                            }

                        }  else if (position %9  == HelpType.WATER.ordinal()) {
                            if (obj.water == true) {
                                image.startAnimation(animation1);
                            } else {
                                image.clearAnimation();
                            }

                        } else if (position % 9 == HelpType.TISSUE.ordinal()) {
                            if (obj.tissue == true) {
                                image.startAnimation(animation1);
                            } else {
                                image.clearAnimation();
                            }

                        }
                        else if (position % 9 == HelpType.PEN.ordinal()) {
                            if (obj.pen == true) {
                                image.startAnimation(animation1);
                            } else {
                                image.clearAnimation();
                            }
                        }
                        else if (position % 9 == HelpType.FRUIT.ordinal()) {
                            if (obj.fruit == true) {
                                image.startAnimation(animation1);
                            } else {
                                image.clearAnimation();
                            }
                        }
                        else if (position % 9 == HelpType.SNACK.ordinal()) {
                            if (obj.snack == true) {
                                image.startAnimation(animation1);
                            } else {
                                image.clearAnimation();
                            }
                        }
                        else if (position % 9 == HelpType.NOTE.ordinal()) {
                            if (obj.snack == true) {
                                image.startAnimation(animation1);
                            } else {
                                image.clearAnimation();
                            }
                        }


                        }

                }



                /////////////////////////////////////////////////

            }


        });



    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);

        // Trigger the initial hide() shortly after the activity has been
        // created, to briefly hint to the user that UI controls
        // are available.

    }
    public static int getScreenWidth(Context context) {
        WindowManager windowManager = (WindowManager) context
                .getSystemService(Context.WINDOW_SERVICE);
        Display display = windowManager.getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);
        return size.x;
    }

    public static int getScreenHeight(Context context) {
        WindowManager windowManager = (WindowManager) context
                .getSystemService(Context.WINDOW_SERVICE);
        Display display = windowManager.getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);
        return size.y;
    }



}
