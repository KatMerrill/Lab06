package com.example.lab06;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.widget.ViewPager2;

import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

public class Fragment1 extends Fragment {
    ViewPager2 myViewPager2;
    int position;
    public static Fragment newInstance(ViewPager2 myViewPager2, int position) {
        Fragment1 fragment = new Fragment1();
        fragment.myViewPager2 = myViewPager2;
        fragment.position = position;
        return fragment;
    }
    int count = 0;

    @Nullable
    @Override
    // creates the fragment/view
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_1, container, false);
    }

    // once the fragment has been created
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        // connects the tabLayout to this. can access vals of parent activity bc of getActivity()
        TabLayout tabLayout = getActivity().findViewById(R.id.tab_layout);
        new TabLayoutMediator(tabLayout, myViewPager2,
                // creates a new tab at this position, then sets text
                // ( -> operator creates a *lambda*/anonymous function)
                (tab, position) -> tab.setText("I am " + (position+1))
        ).attach();

        super.onCreate(savedInstanceState);
        TextView count_message = view.findViewById(R.id.count_message);

        View cookieButton = view.findViewById(R.id.cookie_button);
        cookieButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                count += 1;
                if (count != 1) {
                    count_message.setText("You have clicked the cookie " + count + " times");
                } else {
                    count_message.setText("You have clicked the cookie 1 time");
                }
            }
        });
    }
}
