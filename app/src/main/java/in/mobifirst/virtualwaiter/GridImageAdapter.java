package in.mobifirst.virtualwaiter;

import android.content.Context;
import android.preference.PreferenceManager;
import android.provider.Settings;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

/**
 * Created by narasimha.gonapa on 13-03-2017.
 */

public class GridImageAdapter extends BaseAdapter {
        private Context mContext;

        public GridImageAdapter(Context c) {
            mContext = c;
        }

        public int getCount() {
            return mThumbIds.length;
        }

        public Object getItem(int position) {
            return null;
        }

        public long getItemId(int position) {
            return 0;
        }

        // create a new ImageView for each item referenced by the Adapter
        public View getView(int position, View convertView, ViewGroup parent) {
            ImageView imageView;
             {
                // if it's not recycled, initialize some attributes
                 //if (convertView == null) {
                     imageView = new ImageView(mContext);
                     imageView.setLayoutParams(new GridView.LayoutParams(45, 45));
                     imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
                     imageView.setPadding(1, 1, 1, 1);
                // }
                // else
                 //{
                   //  imageView = (ImageView) convertView;
               //  }
                /* Integer pos = position/7;

                 String mtr=null;
                 mtr = PreferenceManager.getDefaultSharedPreferences(parent.getContext()).getString("meeting_room_id",mtr);
                 if (mtr == null)
                     return null;
                 DatabaseReference ref = FirebaseDatabase.getInstance().getReference().child(mtr).child(pos.toString()).getRoot();
                 ref.orderByKey().addValueEventListener(new ValueEventListener() {
                     @Override
                     public void onDataChange(DataSnapshot dataSnapshot) {
                            System.out.print(dataSnapshot.toString());
                      }

                     @Override
                     public void onCancelled(DatabaseError databaseError) {

                     }
                 });*/


            }



            imageView.setImageResource(mThumbIds[position]);
            return imageView;
        }

        // references to our images
        private Integer[] mThumbIds = {
                R.drawable.chief, R.drawable.attender, R.drawable.water,
                R.drawable.coffee, R.drawable.stationery,
                R.drawable.tissue, R.drawable.cookies,
                R.drawable.fruits, R.drawable.note,
                R.drawable.number, R.drawable.attender, R.drawable.water,
                R.drawable.coffee, R.drawable.stationery,
                R.drawable.tissue, R.drawable.cookies,
                R.drawable.fruits, R.drawable.note,
                R.drawable.numbertwo, R.drawable.attender, R.drawable.water,
                R.drawable.coffee, R.drawable.stationery,
                R.drawable.tissue, R.drawable.cookies,
                R.drawable.fruits, R.drawable.note,
                R.drawable.number3, R.drawable.attender, R.drawable.water,
                R.drawable.coffee, R.drawable.stationery,
                R.drawable.tissue, R.drawable.cookies,
                R.drawable.fruits, R.drawable.note,
                R.drawable.number4, R.drawable.attender, R.drawable.water,
                R.drawable.coffee, R.drawable.stationery,
                R.drawable.tissue, R.drawable.cookies,
                R.drawable.fruits, R.drawable.note,
                R.drawable.number5, R.drawable.attender, R.drawable.water,
                R.drawable.coffee, R.drawable.stationery,
                R.drawable.tissue, R.drawable.cookies,
                R.drawable.fruits, R.drawable.note,
                R.drawable.number6, R.drawable.attender, R.drawable.water,
                R.drawable.coffee, R.drawable.stationery,
                R.drawable.tissue, R.drawable.cookies,
                R.drawable.fruits, R.drawable.note,
                R.drawable.number7, R.drawable.attender, R.drawable.water,
                R.drawable.coffee, R.drawable.stationery,
                R.drawable.tissue, R.drawable.cookies,
                R.drawable.fruits, R.drawable.note,
                R.drawable.number8, R.drawable.attender, R.drawable.water,
                R.drawable.coffee, R.drawable.stationery,
                R.drawable.tissue, R.drawable.cookies,
                R.drawable.fruits, R.drawable.note,
                R.drawable.number9, R.drawable.attender, R.drawable.water,
                R.drawable.coffee, R.drawable.stationery,
                R.drawable.tissue, R.drawable.cookies,
                R.drawable.fruits, R.drawable.note,

                R.drawable.number10, R.drawable.attender, R.drawable.water,
                R.drawable.coffee, R.drawable.stationery,
                R.drawable.tissue, R.drawable.cookies,
                R.drawable.fruits, R.drawable.note,
                R.drawable.number11, R.drawable.attender, R.drawable.water,
                R.drawable.coffee, R.drawable.stationery,
                R.drawable.tissue, R.drawable.cookies,
                R.drawable.fruits, R.drawable.note,
                R.drawable.number12, R.drawable.attender, R.drawable.water,
                R.drawable.coffee, R.drawable.stationery,
                R.drawable.tissue, R.drawable.cookies,
                R.drawable.fruits, R.drawable.note,
                R.drawable.number13, R.drawable.attender, R.drawable.water,
                R.drawable.coffee, R.drawable.stationery,
                R.drawable.tissue, R.drawable.cookies,
                R.drawable.fruits, R.drawable.note,
                R.drawable.number14, R.drawable.attender, R.drawable.water,
                R.drawable.coffee, R.drawable.stationery,
                R.drawable.tissue, R.drawable.cookies,
                R.drawable.fruits, R.drawable.note,
                R.drawable.number15, R.drawable.attender, R.drawable.water,
                R.drawable.coffee, R.drawable.stationery,
                R.drawable.tissue, R.drawable.cookies,
                R.drawable.fruits, R.drawable.note,
                R.drawable.number16, R.drawable.attender, R.drawable.water,
                R.drawable.coffee, R.drawable.stationery,
                R.drawable.tissue, R.drawable.cookies,
                R.drawable.fruits, R.drawable.note,
                R.drawable.number17, R.drawable.attender, R.drawable.water,
                R.drawable.coffee, R.drawable.stationery,
                R.drawable.tissue, R.drawable.cookies,
                R.drawable.fruits, R.drawable.note,
                R.drawable.number18, R.drawable.attender, R.drawable.water,
                R.drawable.coffee, R.drawable.stationery,
                R.drawable.tissue, R.drawable.cookies,
                R.drawable.fruits, R.drawable.note
        };
}
