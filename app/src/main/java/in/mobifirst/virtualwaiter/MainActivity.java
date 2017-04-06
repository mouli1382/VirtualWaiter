 package in.mobifirst.virtualwaiter;

import android.content.Context;
import android.content.Intent;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.media.Ringtone;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Vibrator;
import android.preference.PreferenceManager;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.ThemedSpinnerAdapter;
import android.view.Display;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.Toast;


import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;
import java.util.Map;

import static in.mobifirst.virtualwaiter.HelpType.TEA;


 public class MainActivity extends AppCompatActivity {

     private DatabaseReference mDatabaseReference;
     private DatabaseReference mChildDatabaseReference;
     public Chair mChiarObj;
     @Override
     protected void onCreate(Bundle savedInstanceState) {
         super.onCreate(savedInstanceState);



         this.requestWindowFeature(Window.FEATURE_NO_TITLE);


         this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

//set content view AFTER ABOVE sequence (to avoid crash)

         this.findViewById(android.R.id.content).setSystemUiVisibility(View.SYSTEM_UI_FLAG_IMMERSIVE);
         setTitle("      Chutki      ");

         //getSupportActionBar().hide();

         setContentView(R.layout.activity_main);
         String mtr=null;
         mtr = PreferenceManager.getDefaultSharedPreferences(this).getString("meeting_room_id",mtr);
         String str=null;
         str = PreferenceManager.getDefaultSharedPreferences(this).getString("example_list",str);

         final GridView gridview = (GridView) findViewById(R.id.gridview);
         gridview.setAdapter(new ImageAdapter(this, getScreenWidth(this), getScreenHeight(this)));


         mDatabaseReference = FirebaseDatabase.getInstance().getReference().child(mtr);
         final Animation blinkAnimation = AnimationUtils.loadAnimation(this,
                         R.anim.blink);


         /*String key = mDatabaseReference.push().getKey();
         for (Integer i=0; i <36; i++) {
             Chair chairObj = new Chair(i.toString(), false, false, false, false, false);
             mDatabaseReference.child(i.toString()).setValue(chairObj);
         }*/

         mChildDatabaseReference = mDatabaseReference.child(str);
         mChildDatabaseReference.addValueEventListener(new ValueEventListener() {
             @Override
             public void onDataChange(DataSnapshot dataSnapshot) {
                 mChiarObj = dataSnapshot.getValue(Chair.class);





                 ////////////////////////////////////////////////////////////////////////////

                 for (int position=0; position<8; position++) {
                     ImageView image = (ImageView)gridview.getChildAt(position);
                     if (position == HelpType.TEA.ordinal()) {
                         if (mChiarObj.tea == true) {
                             image.startAnimation(blinkAnimation);

                         } else {
                             image.clearAnimation();
                         }

                     } else if (position == HelpType.ATTENDER.ordinal()) {
                         if (mChiarObj.help == true) {
                             image.startAnimation(blinkAnimation);
                         } else {
                             image.clearAnimation();
                         }

                     } else if (position == HelpType.WATER.ordinal()) {
                         if (mChiarObj.water == true) {
                             image.startAnimation(blinkAnimation);
                         } else {
                             image.clearAnimation();
                         }

                     } else if (position == HelpType.PEN.ordinal()) {
                         if (mChiarObj.pen == true) {
                             image.startAnimation(blinkAnimation);
                         } else {
                             image.clearAnimation();
                         }

                     } else if (position == HelpType.TISSUE.ordinal()) {
                         if (mChiarObj.tissue == true) {
                             image.startAnimation(blinkAnimation);
                         } else {
                             image.clearAnimation();
                         }

                     } else if (position == HelpType.SNACK.ordinal()) {
                         if (mChiarObj.snack == true) {
                             image.startAnimation(blinkAnimation);
                         } else {
                             image.clearAnimation();
                         }

                     } else if (position == HelpType.FRUIT.ordinal()) {
                         if (mChiarObj.fruit == true) {
                             image.startAnimation(blinkAnimation);
                         } else {
                             image.clearAnimation();
                         }

                     } else if (position == HelpType.NOTE.ordinal()) {
                         if (mChiarObj.note == true) {
                             image.startAnimation(blinkAnimation);
                         } else {
                             image.clearAnimation();
                         }

                     }
                 }











                 ///////////////////////////////////////////////////////////////////////////////

             }

             @Override
             public void onCancelled(DatabaseError databaseError) {

             }
         });


         //gridview.setBackgroundResource(R.drawable.wallpaper);
         //gridview.setBackgroundColor(Color.parseColor("#808080"));
         gridview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
             public void onItemClick(AdapterView<?> parent, View v,
                                     int position, long id) {

                 ImageView image = (ImageView)parent.getChildAt(position);
                 Animation animation1 =
                         AnimationUtils.loadAnimation(getApplicationContext(),
                                 R.anim.blink);

                try {

                    if (position == HelpType.TEA.ordinal()) {
                        if (mChiarObj.tea == false) {
                            image.startAnimation(animation1);

                        } else {
                            image.clearAnimation();
                        }
                        mChildDatabaseReference.child("tea").setValue(!(mChiarObj.tea));
                    } else if (position == HelpType.ATTENDER.ordinal()) {
                        if (mChiarObj.help == false) {
                            image.startAnimation(animation1);
                        } else {
                            image.clearAnimation();
                        }
                        mChildDatabaseReference.child("help").setValue(!(mChiarObj.help));
                    } else if (position == HelpType.WATER.ordinal()) {
                        if (mChiarObj.water == false) {
                            image.startAnimation(animation1);
                        } else {
                            image.clearAnimation();
                        }
                        mChildDatabaseReference.child("water").setValue(!(mChiarObj.water));
                    } else if (position == HelpType.PEN.ordinal()) {
                        if (mChiarObj.pen == false) {
                            image.startAnimation(animation1);
                        } else {
                            image.clearAnimation();
                        }
                        mChildDatabaseReference.child("pen").setValue(!(mChiarObj.pen));
                    } else if (position == HelpType.TISSUE.ordinal()) {
                        if (mChiarObj.tissue == false) {
                            image.startAnimation(animation1);
                        } else {
                            image.clearAnimation();
                        }
                        mChildDatabaseReference.child("tissue").setValue(!(mChiarObj.tissue));
                    }
                    else if (position == HelpType.SNACK.ordinal()) {
                        if (mChiarObj.snack == false) {
                            image.startAnimation(animation1);
                        } else {
                            image.clearAnimation();
                        }
                        mChildDatabaseReference.child("snack").setValue(!(mChiarObj.snack));
                    }
                    else if (position == HelpType.FRUIT.ordinal()) {
                        if (mChiarObj.fruit == false) {
                            image.startAnimation(animation1);
                        } else {
                            image.clearAnimation();
                        }
                        mChildDatabaseReference.child("fruit").setValue(!(mChiarObj.fruit));
                    }
                    else if (position == HelpType.NOTE.ordinal()) {
                        if (mChiarObj.note == false) {
                            image.startAnimation(animation1);
                        } else {
                            image.clearAnimation();
                        }
                        mChildDatabaseReference.child("note").setValue(!(mChiarObj.note));
                    }
                }
                catch (Exception e)
                 {
                     System.out.println("The write failed: Possibly chair is not initialized " + e.toString());
                 }

             }

         });
     }

     public void goToActivity2 (View view){

         Intent intent = new Intent (this, SettingsActivity.class);
         startActivity(intent);
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
