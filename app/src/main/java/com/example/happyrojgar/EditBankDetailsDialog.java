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

public class EditBankDetailsDialog extends DialogFragment {
    private TextInputEditText nm_of_acc, acc_no,cnf_acc_no, ifsc, pass;
    private TextView hrp_amt;
    private Button btn1;

    public EditBankDetailsDialog() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_edit_bank_details, container, false);
    }

    private void initializeViews(View v) {
        nm_of_acc = (TextInputEditText)v.findViewById(R.id.info1);
        acc_no = (TextInputEditText)v.findViewById(R.id.info2);
        cnf_acc_no = (TextInputEditText)v.findViewById(R.id.info3);
        ifsc = (TextInputEditText)v.findViewById(R.id.info4);
        pass = (TextInputEditText)v.findViewById(R.id.info5);
        hrp_amt = (TextView)v.findViewById(R.id.hrp_amt);
    }

}
