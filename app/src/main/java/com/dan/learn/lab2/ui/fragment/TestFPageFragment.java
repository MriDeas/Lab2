package com.dan.learn.lab2.ui.fragment;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.dan.learn.lab2.R;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link TestFPageFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class TestFPageFragment extends Fragment {

    private static final String ARG_PARAM1 = "param1";

    private String mParam1;

    public TestFPageFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @return A new instance of fragment TestFPageFragment.
     */
    public static TestFPageFragment newInstance(String param1) {
        TestFPageFragment fragment = new TestFPageFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_test_f_page, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        TextView tv_fragment_text = view.findViewById(R.id.tv_fragment_text);
        tv_fragment_text.setText(mParam1);
    }
}