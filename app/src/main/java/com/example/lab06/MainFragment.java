//package com.example.lab06;
//
//import android.os.Bundle;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//import android.widget.Button;
//
//import androidx.annotation.NonNull;
//import androidx.annotation.Nullable;
//import androidx.fragment.app.Fragment;
//import androidx.viewpager2.widget.ViewPager2;
//
//import com.google.android.material.tabs.TabLayout;
//import com.google.android.material.tabs.TabLayoutMediator;
//
//public class MainFragment extends Fragment {
//    ViewPager2 myViewPager2;
//    int position;
//    public static Fragment newInstance(ViewPager2 myViewPager2, int position) {
//        MainFragment fragment = new MainFragment();
//        fragment.myViewPager2 = myViewPager2;
//        fragment.position = position;
//        return fragment;
//    }
//
//    @Nullable
//    @Override
//    // creates the fragment/view
//    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
//        return inflater.inflate(R.layout.fragment_main, container, false);
//    }
//
//    // once the fragment has been created
//    @Override
//    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
//        super.onViewCreated(view, savedInstanceState);
//        // connects the tabLayout to this. can access vals of parent activity bc of getActivity()
//        TabLayout tabLayout = getActivity().findViewById(R.id.tab_layout);
//        new TabLayoutMediator(tabLayout, myViewPager2,
//                // creates a new tab at this position, then sets text
//                // ( -> operator creates a *lambda*/anonymous function)
//                (tab, position) -> tab.setText("I am " + (position+1))
//        ).attach();
//
//        // access button - here you must use *view*.findView... because this is called from a static context
//        Button myButton = view.findViewById(R.id.default_button);
//        myButton.setText("Press " + (position+1));
//    }
//}
