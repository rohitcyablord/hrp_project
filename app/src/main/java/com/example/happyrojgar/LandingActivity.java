package com.example.happyrojgar;

import android.app.ActionBar;
import android.content.Intent;
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

        initializeViews();

        transferHrp.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                SendhrpDialog sendhrp = new SendhrpDialog();
                sendhrp.show(fm,"Sendhrp");
            }
        });


        topUp.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                TopupDialog TopupDialog = new TopupDialog();
                TopupDialog.show(fm,"TopupDialog");
            }
        });

        addFollower.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), RegisterationActivity.class);
                startActivity(intent);
            }
        });

        transactionHistory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), TransactionHistoryActivity.class);
                startActivity(intent);
            }
        });

    }

    public void initializeViews(){
        transferHrp = (ImageView)findViewById(R.id.transfer_hrp_icon);
        topUp = (ImageView)findViewById(R.id.topup_icon);
        transactionHistory = (TextView)findViewById(R.id.transaction_history);
        addFollower = (TextView)findViewById(R.id.add_follwer);
    }
}
