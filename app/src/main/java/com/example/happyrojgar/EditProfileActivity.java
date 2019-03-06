package com.example.happyrojgar;

import android.app.ActionBar;
import android.support.design.widget.TextInputEditText;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Objects;

public class EditProfileActivity extends AppCompatActivity {
    private ImageView menu2;
    private FragmentManager fm1;
    private TextView user_name, user_id, acc_last_dgt;;
    private TextInputEditText usr_email, dob, gender;
    private Button edt_pro_amt, save_btn, edt_btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile);
        Objects.requireNonNull(getSupportActionBar()).setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        getSupportActionBar().setDisplayShowCustomEnabled(true);
        getSupportActionBar().setCustomView(R.layout.custom_action_bar_with_menu);
        View view = getSupportActionBar().getCustomView();
        TextView activity_name = (TextView) view.findViewById(R.id.activity_name);
        activity_name.setText(R.string.edit_profile_title);
        ImageView backBtn = (ImageView)view.findViewById(R.id.Back);
        ImageView menu = (ImageView)view.findViewById(R.id.menu2);
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

        initializeViews();

        edt_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditBankDetailsDialog edtBankDetails = new EditBankDetailsDialog();
                edtBankDetails.show(fm1,"EditBankDetails");
            }
        });
    }

    private void initializeViews() {
        //menu2 = (ImageView)findViewById(R.id.menu2);
        user_name = (TextView)findViewById(R.id.usr_nm);
        user_id = (TextView)findViewById(R.id.user_email);
        acc_last_dgt = (TextView)findViewById(R.id.edt_pro_amt);
        usr_email = (TextInputEditText)findViewById(R.id.input1);
        dob = (TextInputEditText)findViewById(R.id.input2);
        gender = (TextInputEditText)findViewById(R.id.input3);
        save_btn = (Button)findViewById(R.id.button);
        acc_last_dgt = (TextView)findViewById(R.id.acc_lst_dgt);
        edt_btn = (Button)findViewById(R.id.edt_btn);
    }

}
