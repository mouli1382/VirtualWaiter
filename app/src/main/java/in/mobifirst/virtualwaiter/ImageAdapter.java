package in.mobifirst.virtualwaiter;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Outline;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewOutlineProvider;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.ListAdapter;

/**
 * Created by narasimha.gonapa on 09-03-2017.
 */

public class ImageAdapter extends BaseAdapter {
    private Context mContext;
    private int screenWidth;
    private int screenHeight;

    public ImageAdapter(Context c, int width, int height) {
        mContext = c;
        screenWidth = width;
        screenHeight = height;
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
        final ImageView imageView;
        if (convertView == null) {
            // if it's not recycled, initialize some attributes

            int actualWidth = screenWidth - (2 * mContext.getResources().getDimensionPixelSize(R.dimen.grid_vertical_spacing) +
                    2 * mContext.getResources().getDimensionPixelSize(R.dimen.activity_layout_margin));

            int actualHeight = screenHeight - (3 * mContext.getResources().getDimensionPixelSize(R.dimen.grid_horizontal_spacing) +
                    3 * mContext.getResources().getDimensionPixelSize(R.dimen.activity_layout_margin));

            imageView = new ImageView(mContext);
            imageView.setLayoutParams(new GridView.LayoutParams(actualWidth / 2 , (actualHeight - screenHeight/11 )/ 4));
            imageView.setScaleType(ImageView.ScaleType.FIT_CENTER);
            imageView.setPadding(4, 4, 4, 4);
            /*if (position % 4 == 0)
                imageView.setBackgroundColor(Color.parseColor("#ffff00"));
            else if (position % 4  == 1)
                imageView.setBackgroundColor(Color.parseColor("#E6E6FA"));
            else if (position % 4  == 2)
                imageView.setBackgroundColor(Color.parseColor("#ff7f50"));
            else if (position % 4  == 3)
                imageView.setBackgroundColor(Color.parseColor("#3399ff"));
            else
                imageView.setBackgroundColor(Color.parseColor("#00ccff"));*/
            imageView.setBackgroundResource(R.drawable.background);

        } else {
            imageView = (ImageView) convertView;
        }


        imageView.setImageResource(mThumbIds[position]);
        return imageView;
    }

    // references to our images
    private Integer[] mThumbIds = {
            R.drawable.attender, R.drawable.water,
            R.drawable.coffee, R.drawable.stationery,
            R.drawable.tissue, R.drawable.cookies,
            R.drawable.fruits, R.drawable.note
    };
}
