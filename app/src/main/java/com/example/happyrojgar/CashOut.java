package com.example.happyrojgar;

import android.app.ActionBar;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Objects;

public class CashOut extends AppCompatActivity {
    private ImageView menu2;
    private FragmentManager fm;
    private Button edtbtn, cnfbtn;
    private TextView ac_holder_nm,acc_type,lst_dgt,csh_out_amt, trans_fee,ttl_csh__out_amt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cash_out);
        Objects.requireNonNull(getSupportActionBar()).setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        getSupportActionBar().setDisplayShowCustomEnabled(true);
        getSupportActionBar().setCustomView(R.layout.custom_action_bar_with_menu);
        View view = getSupportActionBar().getCustomView();
        TextView activity_name = (TextView) view.findViewById(R.id.activity_name);
        activity_name.setText(R.string.title_menu_btn);
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


        edtbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditBankDetails edtBankDetails = new EditBankDetails();
                edtBankDetails.show(fm,"EditBankDetails");
            }
        });

        menu2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PopUpMenu popUpMenu = new PopUpMenu();
                popUpMenu.show(fm,"PopUpMenu");
            }
        });

    }

    private void initializeViews() {
        menu2 = (ImageView)findViewById(R.id.menu2);
        edtbtn = (Button)findViewById(R.id.edt_btn);
        ac_holder_nm = (TextView) findViewById(R.id.acc_holder_nm);
        lst_dgt = (TextView) findViewById(R.id.lst_dgt);
        acc_type = (TextView)findViewById(R.id.acc_type);
        csh_out_amt = (TextView)findViewById(R.id.csh_out_mnt);
        trans_fee =(TextView)findViewById(R.id.trans_fee);
        ttl_csh__out_amt = (TextView)findViewById(R.id.ttl_csh_out);
        cnfbtn = (Button)findViewById(R.id.cnf_btn);
    }

}
