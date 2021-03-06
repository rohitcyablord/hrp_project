package com.example.happyrojgar;

import android.app.ActionBar;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.TextInputEditText;

import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.Objects;

public class LoginActivity extends AppCompatActivity {
    private ImageView b1;
    private Context context;
    private Button lgn_btn;
    private TextInputEditText email, pass;
    private Underdog underdog;
    private String usernameString, passwordString;
    private ProgressBar mProgressBar;
    private JSONArray response;
    private Handler mHandler;
    private String TAG = "Login";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        //Objects.requireNonNull(getActionBar()).setDisplayHomeAsUpEnabled(true);
        Objects.requireNonNull(getSupportActionBar()).setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        getSupportActionBar().setDisplayShowCustomEnabled(true);
        getSupportActionBar().setCustomView(R.layout.custom_action_bar_layout);
        View view = getSupportActionBar().getCustomView();
        TextView activity_name = (TextView) view.findViewById(R.id.activity_name);
        activity_name.setText(R.string.login_activity_text);
        ImageView backBtn = (ImageView) view.findViewById(R.id.Back);

        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                onBackPressed();
            }
        });

        initializeViews();
        context = getApplicationContext();
        underdog = new Underdog(context);


        /*b1.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
            }
        });*/

        lgn_btn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                usernameString = String.valueOf(email.getText()).trim();
                passwordString = String.valueOf(pass.getText()).trim();

                if (!usernameString.equals("") && !passwordString.equals("")) {
                    lgn_btn.setEnabled(false);
                    Intent intent = new Intent(getApplicationContext(), LandingActivity.class);
                    startActivity(intent);
                } else {
                    if (usernameString.equals(""))
                        email.setError("Email should not be empty!");
                    else if (passwordString.equals(""))
                        pass.setError("Password should not be empty!");
                }

            }
        });

    }

    private void initializeViews() {
        //b1 = (ImageView) findViewById(R.id.Back);
        lgn_btn = (Button) findViewById(R.id.loginBtn);
        email = (TextInputEditText) findViewById(R.id.email);
        pass = (TextInputEditText) findViewById(R.id.password);
        mProgressBar = (ProgressBar) findViewById(R.id.loadingBar);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

    private void Login(final String emailString, String passwordString) {
        JSONObject args = new JSONObject();
        //JSONObject host = new JSONObject();
        JSONObject parent = new JSONObject();

        try {

            args.put("email", emailString);
            args.put("password", passwordString);
            parent.put("function", "Login");
            parent.put("token", "");
            parent.put("args", args);

            response = underdog.HttpCaller(parent, mProgressBar);
            Log.w(TAG, String.valueOf(response));
            //hideProgressDialog();
            new Thread(new Runnable() {

                @Override
                public void run() {
                    try {
                        if (response != null) {
                            if (response.getJSONObject(0).getString("errInResponse").equals("")) {
                                switch (response.getJSONObject(0).getString("response")) {

                                    case "true":
                                        final String designation = response.getJSONObject(0).getJSONObject("emp_details").getString("employee_designation");
                                        final String hotel_id = response.getJSONObject(0).getJSONObject("emp_details").getString("hotel_id");
                                        final String employee_name = response.getJSONObject(0).getJSONObject("emp_details").getString("employee_name");
                                        final String token = response.getJSONObject(0).getString("tokenString");

                                        mHandler.post(new Runnable() {
                                            @Override
                                            public void run() {
                                                Toast.makeText(context, "Login success !!!", Toast.LENGTH_SHORT).show();
                                                //setSharedPreferences(designation,hotel_id,employee_name,token,emailString);
                                                lgn_btn.setEnabled(true);
                                                //passingIntent();
                                            }
                                        });

                                        break;
                                    case "false":
                                        mHandler.post(new Runnable() {
                                            @Override
                                            public void run() {
                                                lgn_btn.setEnabled(true);
                                                Toast.makeText(context, "Login Failed !!!", Toast.LENGTH_SHORT).show();
                                            }
                                        });
                                        break;
                                    default:
                                        mHandler.post(new Runnable() {
                                            @Override
                                            public void run() {
                                                lgn_btn.setEnabled(true);
                                                Toast.makeText(context, "Something went wrong,Try again !!!", Toast.LENGTH_SHORT).show();
                                            }
                                        });
                                        break;
                                }
                            } else {
                                mHandler.post(new Runnable() {
                                    @Override
                                    public void run() {
                                        lgn_btn.setEnabled(true);
                                        //Log.e(TAG, response.getJSONObject(0).getString("errInResponse"));
                                        Toast.makeText(context, "Try again !!!", Toast.LENGTH_SHORT).show();
                                    }
                                });

                            }
                        } else {
                            mHandler.post(new Runnable() {
                                @Override
                                public void run() {
                                    lgn_btn.setEnabled(true);
                                    Toast.makeText(context, "Response is null ", Toast.LENGTH_SHORT).show();
                                }
                            });
                            //loginBtn.setEnabled(true);

                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            }).start();


        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
