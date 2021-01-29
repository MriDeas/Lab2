package com.dan.learn.lab2.ui.fragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.util.Log;
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
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        printFragmentLifeCycle("onAttach");
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        printFragmentLifeCycle("onActivityCreated");
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        printFragmentLifeCycle("onCreate");
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        printFragmentLifeCycle("onCreateView");
        return inflater.inflate(R.layout.fragment_test_f_page, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        TextView tv_fragment_text = view.findViewById(R.id.tv_fragment_text);
        tv_fragment_text.setText(mParam1);
        printFragmentLifeCycle("onViewCreated");

        TextViewFragment textViewFragment = TextViewFragment.newInstance("TextView");
        textViewFragment.setTargetFragment(this,100);
        getView().findViewById(R.id.bt_action).setOnClickListener(v -> {
            getChildFragmentManager().beginTransaction().add(textViewFragment, "hey").addToBackStack("stack").commit();
        });
        getView().findViewById(R.id.bt_action2).setOnClickListener(v -> {
            Intent intent = new Intent();
            intent.putExtra("FragmentParam", "Fragment数据");
            getTargetFragment().onActivityResult(getTargetRequestCode(), 200, intent);
        });
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        String param = "";
        if(data != null){
            param = data.getStringExtra("FragmentParam");
        }
        Log.d("TestFPageFragment ", "onActivityResult from targetFragment requestCode：" + requestCode + " from Fragment param:" + param);
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        printFragmentLifeCycle("onSaveInstanceState");
    }

    @Override
    public void onResume() {
        super.onResume();
        printFragmentLifeCycle("onResume");
    }

    @Override
    public void onPause() {
        super.onPause();
        printFragmentLifeCycle("onPause");
    }

    @Override
    public void onStop() {
        super.onStop();
        printFragmentLifeCycle("onStop");
    }

    @Override
    public void onDetach() {
        super.onDetach();
        printFragmentLifeCycle("onDetach");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        printFragmentLifeCycle("onDestroy");
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        printFragmentLifeCycle("onDestroyView");
    }

    private void printFragmentLifeCycle(String tag) {
        System.out.println("打印执行函数 ----------- Fragment" + mParam1 + "::" + tag);
    }
}