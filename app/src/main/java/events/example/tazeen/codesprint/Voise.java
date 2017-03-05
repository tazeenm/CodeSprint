package events.example.tazeen.codesprint;

import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterViewFlipper;

import java.util.Timer;
import java.util.TimerTask;

import events.com.example.tazeen.codesprint.R;

public class Voise extends AppCompatActivity {

    /*CustomAdapter adapter;
    ViewPager viewPager;*/

    private Toolbar mToolBar;

    private AdapterViewFlipper simpleAdapterViewFlipper;
    int[] slideImages = {R.drawable.pic1, R.drawable.pic2, R.drawable.pic3, R.drawable.pic4, R.drawable.pic5};     // array of images
    //String fruitNames[] = {"Apple", "Pine Apple", "Litchi", "Mango", "Banana"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_voise);

        /*viewPager = (ViewPager) findViewById(R.id.viewPager);
        adapter = new CustomAdapter(this);
        viewPager.setAdapter(adapter);*/
        mToolBar = (Toolbar) findViewById(R.id.toolbar2);

        simpleAdapterViewFlipper = (AdapterViewFlipper) findViewById(R.id.simpleAdapterViewFlipper);
        CustomAdapter customAdapter = new CustomAdapter(getApplicationContext(), /*fruitNames,*/ slideImages);
        simpleAdapterViewFlipper.setAdapter(customAdapter);
        simpleAdapterViewFlipper.setFlipInterval(3000);
        simpleAdapterViewFlipper.setAutoStart(true);
    }
}

        /*Timer tm = new Timer();
        tm.scheduleAtFixedRate(new MyTimer(),5000,3000);
    }

    public class MyTimer extends TimerTask {
        @Override
        public void run() {
            Voise.this.runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    if(viewPager.getCurrentItem()==0) {
                        viewPager.setCurrentItem(1);
                    } else if(viewPager.getCurrentItem()==1){
                        viewPager.setCurrentItem(2);
                    } else {
                        viewPager.setCurrentItem(0);
                    }
                }
            });
        }
    }
}
*/