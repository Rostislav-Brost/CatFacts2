package ru.madbrains.listexample;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

public class TestActivity extends AppCompatActivity {

    static int numberOfPages = 2;
    ViewPager myViewPager;
    MyFragmentPagerAdapter myFragmentPagerAdapter;
    static String text = "test";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);
        myViewPager = (ViewPager) findViewById(R.id.view_pager);
        myFragmentPagerAdapter = new MyFragmentPagerAdapter(getSupportFragmentManager());
        myViewPager.setAdapter(myFragmentPagerAdapter);
    }
    // Adapters
    private static class MyFragmentPagerAdapter extends FragmentPagerAdapter {
        public MyFragmentPagerAdapter(FragmentManager fm)   {
            super(fm);
        }

        @Override
        public Fragment getItem(int index) {

            return PageFragment.newInstance(text);
        }

        @Override
        public int getCount() {
            return numberOfPages;
        }
    }


}

