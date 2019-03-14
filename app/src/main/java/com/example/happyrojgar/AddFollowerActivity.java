package com.example.happyrojgar;

import android.app.ActionBar;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Handler;
import android.support.design.widget.TextInputEditText;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.Objects;

public class AddFollowerActivity extends AppCompatActivity {
    private ImageView menu2;
    private FragmentManager fm1;
    private TextInputEditText email, sponsor_email, usr_pass, dob, gender, pass;
    private ProgressBar mProgressBar;
    private JSONArray response;
    private Handler mHandler;
    SharedPreferences sharedPreferences;

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
        ImageView backBtn = (ImageView) view.findViewById(R.id.Back);
        ImageView menu = (ImageView) view.findViewById(R.id.menu2);

        initializeViews();
        fm1 = getSupportFragmentManager();
        sharedPreferences = getSharedPreferences("MyPrefs", Context.MODE_PRIVATE);

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
                popUpMenu.show(fm1, "PopUpMenu");
            }
        });
    }

    private void initializeViews() {

        email = (TextInputEditText) findViewById((R.id.inp_text1));
        sponsor_email = (TextInputEditText) findViewById((R.id.inp_text2));
        usr_pass = (TextInputEditText) findViewById(R.id.inp_text3);
        dob = (TextInputEditText) findViewById(R.id.inp_text4);
        gender = (TextInputEditText) findViewById(R.id.inp_text5);
        pass = (TextInputEditText) findViewById(R.id.inp_text6);

    }

    /*private void RegisterUser(){
        JSONObject args = new JSONObject();
        JSONObject parent = new JSONObject();
        try {
            args.put("sponsorUName",sponsorsList.get(sponsorNamesSpinner.getSelectedItemPosition()));
            //args.put("sponsorId",sponsorsId.get(sponsorNamesSpinner.getSelectedItemPosition()));
            args.put("userName",String.valueOf(user_name.getText()));
            args.put("fullName",String.valueOf(fullname.getText()));
            args.put("nameOnAccount",String.valueOf(name_on_acc.getText()));
            args.put("accountNumber",String.valueOf(account_number.getText()));
            args.put("mobileNumber",String.valueOf(contact_no.getText()));
            args.put("ifsCode",String.valueOf(ifs_code.getText()));
            parent.put("function", "AddUserDetails");
//          parent.put("token", sharedPreferences.getString("token", ""));
            parent.put("token","");
            parent.put("args", args);
            response = underdog.HttpCaller(parent, mProgressBar);
            Log.w(TAG, String.valueOf(response));
            new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        if (response != null) {
                            if (response.getJSONObject(0).getString("errInResponse").equals("")) {
                                switch (response.getJSONObject(0).getString("response")) {

                                    case "true":
                                        mHandler.post(new Runnable() {
                                            @Override
                                            public void run() {
                                                Toast.makeText(context, "User register successfully !!!", Toast.LENGTH_SHORT).show();
                                                register_btn.setEnabled(true);
                                                //finish();
                                            }
                                        });

                                        break;
                                    case "false":
                                        mHandler.post(new Runnable() {
                                            @Override
                                            public void run() {
                                                register_btn.setEnabled(true);
                                                Toast.makeText(context, "Failed to register user!!!", Toast.LENGTH_SHORT).show();
                                            }
                                        });
                                        break;
                                    default:
                                        mHandler.post(new Runnable() {
                                            @Override
                                            public void run() {
                                                register_btn.setEnabled(true);
                                                Toast.makeText(context, "Something went wrong,Try again !!!", Toast.LENGTH_SHORT).show();
                                            }
                                        });
                                        break;
                                }
                            } else {
                                mHandler.post(new Runnable() {
                                    @Override
                                    public void run() {
                                        register_btn.setEnabled(true);
                                        //Log.e(TAG, response.getJSONObject(0).getString("errInResponse"));
                                        Toast.makeText(context, "Try again !!!", Toast.LENGTH_SHORT).show();
                                    }
                                });

                            }
                        } else {
                            mHandler.post(new Runnable() {
                                @Override
                                public void run() {
                                    register_btn.setEnabled(true);
                                    Toast.makeText(context, "Response is null ", Toast.LENGTH_SHORT).show();
                                }
                            });


                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            }).start();
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }*/

}
