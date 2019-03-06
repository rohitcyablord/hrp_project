package com.example.happyrojgar;

import android.app.ActionBar;
import android.support.design.widget.TextInputEditText;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Objects;

public class AddFollower extends AppCompatActivity {
    private ImageView menu2;
    private FragmentManager fm1;
    private TextInputEditText email, sponsor_email, usr_pass, dob, gender, pass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_follower);
        Objects.requireNonNull(getSupportActionBar()).setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        getSupportActionBar().setDisplayShowCustomEnabled(true);
        getSupportActionBar().setCustomView(R.layout.custom_action_bar_with_menu);
        View view = getSupportActionBar().getCustomView();
        TextView activity_name = (TextView) view.findViewById(R.id.activity_name);
        activity_name.setText(R.string.title_menu_btn);
        ImageView backBtn = (ImageView)view.findViewById(R.id.Back);
        ImageView menu = (ImageView)view.findViewById(R.id.menu2);

        initializeViews();
        fm1 = getSupportFragmentManager();

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
                popUpMenu.show(fm1,"PopUpMenu");
            }
        });
    }

        private void initializeViews() {

            email = (TextInputEditText)findViewById((R.id.inp_text1));
            sponsor_email = (TextInputEditText)findViewById((R.id.inp_text2));
            usr_pass = (TextInputEditText)findViewById(R.id.inp_text3);
            dob = (TextInputEditText)findViewById(R.id.inp_text4);
            gender = (TextInputEditText)findViewById(R.id.inp_text5);
            pass = (TextInputEditText)findViewById(R.id.inp_text6);

        }

}
