package com.example.lab06;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.adapter.FragmentStateAdapter;
import androidx.viewpager2.widget.ViewPager2;

import android.os.Bundle;


public class MainActivity extends AppCompatActivity {
    // ViewPager2 allows you to swipe between different Fragments
    ViewPager2 myViewPager2;
    // RecyclerView stores offscreen Fragments and "recycles" them instead of destroying them
    // the Adapter connects the views/Fragments to the RecyclerView.
    RecyclerView.Adapter myFragmentStateAdapter;

    // the number of items/views that the ViewPager will display
    int NUM_ITEMS = 2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        myViewPager2 = findViewById(R.id.container);
        // creates our own adapter for the ViewPager
        myFragmentStateAdapter = new MyFragmentStateAdapter(this);
        myViewPager2.setAdapter(myFragmentStateAdapter);
    }
    private class MyFragmentStateAdapter extends FragmentStateAdapter {
        // the constructor just refers to its parent class
        public MyFragmentStateAdapter(@NonNull FragmentActivity fragmentActivity) {
            super(fragmentActivity);
        }

        @NonNull
        @Override
        public Fragment createFragment(int position) {
            if(position == 0) {
                // newInstance returns an instantiation of the fragment
                return Fragment1.newInstance(myViewPager2, position);
            }
            else {
                return Fragment3.newInstance(myViewPager2, position);
            }
        }

        @Override
        public int getItemCount() {
            return NUM_ITEMS;
        }
    }
}
