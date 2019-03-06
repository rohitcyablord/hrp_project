package com.example.happyrojgar;

import android.app.ActionBar;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Objects;

public class Register_step1 extends AppCompatActivity {
    private ImageView back_arrow;
    private Button next_btn;
    private TextInputEditText nm_of_sponsor,email_of_sponsor, otp;
    private String sponsor_name,sponsor_email,otp_val;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_step1);
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

        next_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sponsor_name = String.valueOf(nm_of_sponsor.getText()).trim();
                sponsor_email = String.valueOf(email_of_sponsor.getText()).trim();
                otp_val = String.valueOf(String.valueOf(otp));

                if (!sponsor_name.equals("") && !sponsor_email.equals("")) {
                    Intent intent = new Intent(getApplicationContext(),Register.class);
                    startActivity(intent);
                }else{
                    if(sponsor_name.equals("")){
                        nm_of_sponsor.setError("Sponsor name should not be empty!");
                    }else if(sponsor_email.equals("")){
                        email_of_sponsor.setError("Sponsor email should not be empty");
                    }else if(otp_val.equals(0)){
                        otp.setError("OTP should not be empty");
                    }
                }
            }
        });

    }

    @Override
    public void onDestroy() {
        clearMemory();
        super.onDestroy();
    }

    private void clearMemory(){

    }

    private void initializeViews() {
        next_btn = (Button) findViewById(R.id.button);
        nm_of_sponsor = (TextInputEditText)findViewById(R.id.info1);
        email_of_sponsor =  (TextInputEditText)findViewById(R.id.info2);
        otp =  (TextInputEditText)findViewById(R.id.info3);

    }

}
