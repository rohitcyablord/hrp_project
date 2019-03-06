package com.example.happyrojgar;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.TextInputEditText;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

public class Sendhrp extends DialogFragment {
    private TextInputEditText acc_nm,usr_nm, amt, pass;
    private Button btn1;
    private TextView hrp_amt;

    public Sendhrp(){ }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_pop_up_menu, container, false);
        initializeViews(v);
        return v;

    }

    private void initializeViews(View v) {
        acc_nm = (TextInputEditText)v.findViewById(R.id.info1);
        usr_nm = (TextInputEditText)v.findViewById(R.id.info2);
        amt = (TextInputEditText)v.findViewById(R.id.info3);
        pass = (TextInputEditText)v.findViewById(R.id.info4);
        hrp_amt = (TextView) v.findViewById(R.id.hrp_amt);
        btn1 = (Button)v.findViewById(R.id.cnf_btn);
    }


}
