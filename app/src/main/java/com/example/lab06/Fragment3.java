package com.example.lab06;

import static android.content.Context.MODE_PRIVATE;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SeekBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.widget.ViewPager2;

import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

public class Fragment3 extends Fragment {
    String TAG = "merrill.lab03.sharedPrefs";
    TextView topLeft, bottomRight, topRight, bottomLeft;
    SeekBar seekBar;
    TextView[] views;
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;
    ConstraintLayout layout;
    int position;
    long startTime, clickCount;
    ViewPager2 myViewPager2;

    public static Fragment newInstance(ViewPager2 myViewPager2, int position) {
        Fragment3 fragment = new Fragment3();
        fragment.myViewPager2 = myViewPager2;
        fragment.position = position;
        return fragment;
    }

    @Nullable
    @Override
    // creates the fragment/view
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_3, container, false);
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
                (tab, position) -> tab.setText("I am " + (position + 1))
        ).attach();

        super.onCreate(savedInstanceState);

        topLeft = view.findViewById(R.id.topLeft);
        topRight = view.findViewById(R.id.topRight);
        bottomLeft = view.findViewById(R.id.bottomLeft);
        bottomRight = view.findViewById(R.id.bottomRight);
        views = new TextView[]{topLeft, topRight, bottomLeft, bottomRight};

        layout = view.findViewById(R.id.activity_main_layout);

        seekBar = view.findViewById(R.id.seekBar);
        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            int lastProgress;

            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                for (TextView v : views) {
                    v.setTextSize(progress);
                }
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
                lastProgress = seekBar.getProgress();
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) { // snackbar pops when it stops scrolling
                Snackbar snackBar = Snackbar.make(layout, "Font size is now " + seekBar.getProgress() + "sp",
                        Snackbar.LENGTH_LONG);
                snackBar.setAction("UNDO", new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        seekBar.setProgress(lastProgress);
                        for (TextView v : views) {
                            v.setTextSize(lastProgress);
                        }
                        Snackbar.make(layout, "Font size reverted to " + lastProgress + "sp",
                                Snackbar.LENGTH_LONG).show();
                    }
                });
                snackBar.show();
            }
        });

        sharedPreferences = getActivity().getSharedPreferences(TAG, MODE_PRIVATE);
        editor = sharedPreferences.edit();

        for (TextView v : views) {
            v.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    TextView v = (TextView) view;

                    // changes background of view when clicked
                    if (v.getTag().toString().equals("topLeft")) {
                        v.setBackgroundResource(R.drawable.topleft);
                    }
                    if (v.getTag().toString().equals("topRight")) {
                        v.setBackgroundResource(R.drawable.topright);
                    }
                    if (v.getTag().toString().equals("bottomLeft")) {
                        v.setBackgroundResource(R.drawable.bottomleft);
                    }
                    if (v.getTag().toString().equals("bottomRight")) {
                        v.setBackgroundResource(R.drawable.bottomright);
                    }

                    int count = Integer.parseInt(v.getText().toString()) + 1;
                    v.setText("" + count);
                    editor.putString(v.getTag().toString(), v.getText().toString());
                    editor.apply();

                    clickCount++;
                }
            });
        }
        setInitialVals();

        // resets on long click
        layout.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                editor.clear().apply();
                setInitialVals();
                for (TextView v : views) {
                    v.setBackgroundResource(0);
                }
                return false;
            }
        });
    }

    private void setInitialVals() {
        for (TextView v : views) {
            v.setText(sharedPreferences.getString(v.getTag().toString(), "0"));
        }
        seekBar.setProgress(30);
        startTime = System.currentTimeMillis();
    }
}
