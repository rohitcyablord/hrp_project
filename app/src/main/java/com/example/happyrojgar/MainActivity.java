package com.example.happyrojgar;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Button;
import android.view.View;

public class MainActivity extends AppCompatActivity {
    private Button lgn_btn,register_btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initializeViews();

        lgn_btn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),LoginActivity.class);
                //intent.putExtra("abc","val");
                startActivity(intent);
            }
        });

        register_btn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),RegisterationActivity.class);
                startActivity(intent);
            }
        });

    }

    private void initializeViews(){
        lgn_btn=(Button)findViewById(R.id.button);
        register_btn=(Button)findViewById(R.id.button2);

    }


}
