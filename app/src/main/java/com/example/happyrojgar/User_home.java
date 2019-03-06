package com.example.happyrojgar;

import android.app.ActionBar;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.media.Image;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Objects;

public class User_home extends AppCompatActivity {

    private ImageView menu2;
    private CardView trans, topup;
    private FragmentManager fm;
    private TextView add_follower,usr_nm,usr_email, sponsor_nm, balance;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_home);

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
                PopUpMenu popUpMenu = new PopUpMenu();
                popUpMenu.show(fm,"PopUpMenu");
            }
        });

        initializeViews();

        trans.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Sendhrp sendhrp = new Sendhrp();
                sendhrp.show(fm,"Sendhrp");
            }
        });

        topup.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Topup topup = new Topup();
                topup.show(fm,"Topup");

            }
        });

        add_follower.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), AddFollower.class);
                startActivity(intent);
            }
        });
    }

    private void initializeViews() {

        trans = (CardView)findViewById(R.id.trans);
        topup = (CardView)findViewById(R.id.topup);
        add_follower = (TextView)findViewById(R.id.add_follwer);
        usr_nm = (TextView)findViewById(R.id.text1);
        usr_email=(TextView)findViewById(R.id.text2);
        sponsor_nm = (TextView)findViewById(R.id.text3);
        balance = (TextView)findViewById(R.id.balance);
    }

    @Override
    public void onBackPressed() {
        //super.onBackPressed();
        /*if (stack.size() > 1) {
            stack.pop();
            //viewPager.setCurrentItem(stackkk.lastElement());
        } else {*/
        new AlertDialog.Builder(this)
                //.setIcon(R.drawable.ic_warning_black_24dp)
                .setTitle("Closing HR")
                .setMessage("Are you sure you want to close HR ?")
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //TODO: Clear Shared preferences & logout user
                        /*SharedPreferences preferences = getSharedPreferences("MyPrefs", Context.MODE_PRIVATE);
                        SharedPreferences.Editor editor = preferences.edit();
                        editor.clear();
                        editor.apply();
                        editor.commit();*/
                        finish();
                        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                        startActivity(intent);
                    }
                })
                .setNegativeButton("No", null)
                .show();
        //}
    }

}
