package com.example.happyrojgar;

import android.app.ActionBar;
import android.content.Context;
import android.content.SharedPreferences;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Objects;

public class LandingActivity extends AppCompatActivity {
    private TextView transactionHistory, addFollower;
    private ImageView transferHrp, topUp;
    private FragmentManager fm;
    private SharedPreferences sharedPreferences;
    private String TAG = "LandingActivity";
    private SharedPreferences.Editor editor;
    private TextView fullName_text,userName_text,sponsor_name,balance;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_landing);

        Objects.requireNonNull(getSupportActionBar()).setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        getSupportActionBar().setDisplayShowCustomEnabled(true);
        getSupportActionBar().setCustomView(R.layout.custom_action_bar_with_menu);
        View view = getSupportActionBar().getCustomView();
        TextView activity_name = (TextView) view.findViewById(R.id.activity_name);
        activity_name.setText(R.string.title);
        ImageView backBtn = (ImageView)view.findViewById(R.id.Back);
        ImageView menu = (ImageView)view.findViewById(R.id.menu2);
        fm = getSupportFragmentManager();

        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                onBackPressed();
            }
        });

        menu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PopUpMenuDialog popUpMenu = new PopUpMenuDialog();
                popUpMenu.show(fm,"PopUpMenu");
            }
        });
        InitialiseViews();

        sharedPreferences = getSharedPreferences("MyPrefs", Context.MODE_PRIVATE);
        userName_text.setText(sharedPreferences.getString("userName",""));
        fullName_text.setText(sharedPreferences.getString("fullName",""));
        sponsor_name.setText(sharedPreferences.getString("sponsorUname",""));
        balance.setText(sharedPreferences.getString("hrp",""));

    }

    private void InitialiseViews(){
        fullName_text = (TextView) findViewById(R.id.fullName_text);
        userName_text = (TextView) findViewById(R.id.userName_text);
        sponsor_name = (TextView) findViewById(R.id.sponsor_name);
        balance = (TextView) findViewById(R.id.balance);
    }
}
