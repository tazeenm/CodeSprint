package events.example.tazeen.codesprint;

import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.AdapterViewFlipper;


import events.com.example.tazeen.codesprint.R;

public class Edc extends AppCompatActivity {

    /*CustomAdapter adapter1;
    ViewPager viewPager1;
*/
    private Toolbar ToolBar1;
    private AdapterViewFlipper simpleAdapterViewFlipper;
    int[] slideImages = {R.drawable.pic1, R.drawable.pic2, R.drawable.pic3, R.drawable.pic4, R.drawable.pic5};     // array of images
    //String fruitNames[] = {"Apple", "Pine Apple", "Litchi", "Mango", "Banana"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edc);
        ToolBar1 = (Toolbar) findViewById(R.id.toolbar5);

        simpleAdapterViewFlipper = (AdapterViewFlipper) findViewById(R.id.simpleAdapterViewFlipper);
        CustomAdapter customAdapter = new CustomAdapter(getApplicationContext(), /*fruitNames,*/ slideImages);
        simpleAdapterViewFlipper.setAdapter(customAdapter);
        simpleAdapterViewFlipper.setFlipInterval(3000);
        simpleAdapterViewFlipper.setAutoStart(true);
    }
}

        /*viewPager1 = (ViewPager) findViewById(R.id.viewPager4);
        adapter1 = new CustomAdapter(this);
        viewPager1.setAdapter(adapter1);
        ToolBar1 = (Toolbar) findViewById(R.id.toolbar5);

        Timer tm = new Timer();
        tm.scheduleAtFixedRate(new Edc.MyTimer(),5000,3000);
    }

    public class MyTimer extends TimerTask {
        @Override
        public void run() {
            Edc.this.runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    if (viewPager1.getCurrentItem() == 0) {
                        viewPager1.setCurrentItem(1);
                    } else if (viewPager1.getCurrentItem() == 1) {
                        viewPager1.setCurrentItem(2);
                    } else {
                        viewPager1.setCurrentItem(0);
                    }
                }
            });
        } */

