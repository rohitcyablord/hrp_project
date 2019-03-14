package com.example.happyrojgar;

import android.app.ActionBar;
import android.content.Intent;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Objects;

public class TransactionHistoryActivity extends AppCompatActivity {

    private GridView simpleList;
    private ArrayList transacList=new ArrayList<>();
    private FragmentManager fm;
    private GridView transacsGrid;
    private TextView userNameText,userIdText,AccBalanceText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transaction_history);
        Objects.requireNonNull(getSupportActionBar()).setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        getSupportActionBar().setDisplayShowCustomEnabled(true);
        getSupportActionBar().setCustomView(R.layout.custom_action_bar_with_menu);
        View view = getSupportActionBar().getCustomView();
        TextView activity_name = (TextView) view.findViewById(R.id.activity_name);
        activity_name.setText("Transaction History");
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

        initializeView();

        simpleList = (GridView) findViewById(R.id.allTabview);
        transacList.add(new Item("100","Sarita Jadhav","11-Jan-2017"));
        transacList.add(new Item("200","Prashant Ingle","12-Dec-2018"));
        transacList.add(new Item("300","Sohail Shaikh","23-02-1989"));
        transacList.add(new Item("400","Rohit Mahindrakar","03-11-1988"));
        transacList.add(new Item("500","Vinal","19-07-1990"));
        transacList.add(new Item("600","Sankalp","22-04-1999"));

        MyAdapter myAdapter=new MyAdapter(this,R.layout.custom_transaction_view,transacList);
        simpleList.setAdapter(myAdapter);

    }

    public void initializeView(){
        transacsGrid = (GridView)findViewById(R.id.allTabview);
        userNameText =(TextView)findViewById(R.id.user_name);
        userIdText = (TextView)findViewById(R.id.user_id);
        AccBalanceText = (TextView)findViewById(R.id.acc_balance);
    }


}
