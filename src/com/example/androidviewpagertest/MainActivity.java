package com.example.androidviewpagertest;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.SimpleOnPageChangeListener;
import android.util.Log;
import android.util.TypedValue;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity {

	public TextView selectedOpt;
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
	    Log.d("Pager", "onCreate");

        
        selectedOpt=(TextView) findViewById(R.id.selectedopt);
        ViewPager viewPager = (ViewPager) findViewById(R.id.viewpager);
        viewPager.setAdapter(new ImageAdapter());
        viewPager.setOnPageChangeListener(new PageListener());
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }
    
    public class ImageAdapter extends PagerAdapter {
        Integer[] images = {
            R.drawable.book1,
            R.drawable.book2,
            R.drawable.book3
           
        };

        public Object instantiateItem(View container, int position) {
        	
        	  
        		RelativeLayout layout = new RelativeLayout(MainActivity.this);
            ((ViewPager) container).addView(layout, 0);
            
            ImageView view = new ImageView(MainActivity.this);
            view.setImageResource(images[position]);
            layout.addView(view, 0);
            
            Button but = new Button(MainActivity.this);
            int height = (int)TypedValue.applyDimension(
	                TypedValue.COMPLEX_UNIT_DIP, 60, getResources().getDisplayMetrics());
            RelativeLayout.LayoutParams layoutParams =  new RelativeLayout.LayoutParams(
		    		height, height);
            but.setLayoutParams(layoutParams);
            but.setText("test");
            but.setTextColor(Color.BLACK);
            layout.addView(but);
            but.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                  Toast.makeText(MainActivity.this, MainActivity.this.toString(), Toast.LENGTH_SHORT).show();
                }
            });
            
            return layout;
        }

        @Override
        public int getCount() {
            return images.length;
        }

        @Override
        public void destroyItem(View arg0, int arg1, Object arg2) {
            ((ViewPager) arg0).removeView((View) arg2);
        }

        @Override
        public boolean isViewFromObject(View arg0, Object arg1) {        	    
            return arg0 == ((View) arg1);
        }
    }
    
    private class PageListener extends SimpleOnPageChangeListener{
        public void onPageSelected(int position) {
            selectedOpt.setText("You have selected the page number "+position);
        }
    }

    
}
