package com.example.happyrojgar;

import android.app.ActionBar;
import android.content.Intent;
import android.media.Image;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Objects;

public class Register extends AppCompatActivity {
    private ImageView i1;
    private TextInputEditText nm_on_Acc, type_of_acc, acc_no, cnf_acc_no, ifsc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        Objects.requireNonNull(getSupportActionBar()).setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        getSupportActionBar().setDisplayShowCustomEnabled(true);
        getSupportActionBar().setCustomView(R.layout.custom_action_bar_layout);
        View view = getSupportActionBar().getCustomView();
        TextView activity_name = (TextView) view.findViewById(R.id.activity_name);
        activity_name.setText(R.string.activity_register_text1);
        ImageView backBtn = (ImageView)view.findViewById(R.id.Back);

        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                onBackPressed();
            }
        });
        initializeViews();

    }

    private void initializeViews(){
        nm_on_Acc = (TextInputEditText)findViewById(R.id.info1);
        type_of_acc =  (TextInputEditText)findViewById(R.id.info2);
        acc_no =  (TextInputEditText)findViewById(R.id.info3);
        cnf_acc_no = (TextInputEditText)findViewById(R.id.info4);
        ifsc =  (TextInputEditText)findViewById(R.id.info5);

    }
}
