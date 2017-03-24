package events.example.tazeen.codesprint;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import events.com.example.tazeen.codesprint.R;

/**
 * Created by Tazeen on 18-02-2017.
 */

public class CustomAdapter extends BaseAdapter {
    Context context;
    int[] slideImages;
    //String[] fruitNames;
    LayoutInflater inflter;

    public CustomAdapter(Context applicationContext, /*String[] fruitNames, */ int[] fruitImages) {
        this.context = applicationContext;
        this.slideImages = fruitImages;
        //this.fruitNames = fruitNames;
        inflter = (LayoutInflater.from(applicationContext));

    }

    @Override
    public int getCount() {
        return slideImages.length;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View view, ViewGroup parent) {
        view = inflter.inflate(R.layout.view, null);
        TextView slideName = (TextView) view.findViewById(R.id.slide_name);
        ImageView slideImage = (ImageView) view.findViewById(R.id.slide_img);
        //fruitName.setText(fruitNames[position]);
        slideImage.setImageResource(slideImages[position]);
        return view;
    }
}




/*
public class CustomAdapter extends PagerAdapter {
    private int[] images = {R.drawable.pic1, R.drawable.pic2, R.drawable.pic3, R.drawable.pic4, R.drawable.pic5};
    private LayoutInflater inflater;
    private Context ctx;

    public CustomAdapter(Context ctx) {
        this.ctx = ctx;
    }

    @Override
    public int getCount() {
        return images.length;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return (view==(LinearLayout)object);
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        inflater = (LayoutInflater)ctx.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View v = inflater.inflate(R.layout.swipe, container, false);
        ImageView img = (ImageView)v.findViewById(R.id.swipe);
        img.setImageResource(images[position]);
        container.addView(v);
        return v;
        //return super.instantiateItem(container, position);
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
       container.invalidate();

       // super.destroyItem(container,position,object);
    }

}
*/
