package com.pro.binding.module;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link DialogDemoFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class DialogDemoFragment extends DialogFragment {

//fragment_dialog_demo
    public DialogDemoFragment() {
        // Required empty public constructor
    }


    public static DialogDemoFragment newInstance() {
        return new DialogDemoFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View dialogView = inflater.inflate(R.layout.dialog_binding_demo, container, false);
        TextView tv_content = dialogView.findViewById(R.id.tv_content);
        tv_content.setText("Dialog内容");
        return dialogView;
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        AlertDialog.Builder dialog = new AlertDialog.Builder(getContext());
        dialog.setTitle("标题").setMessage("消息").setPositiveButton("你好", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(getContext(),"点击了什么",Toast.LENGTH_SHORT).show();
            }
        });
        return dialog.create();
    }


}