package com.example.happyrojgar;

import android.app.ActionBar;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Handler;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Objects;

public class RegisterationActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    private ImageView back_arrow;
    private Context context;
    private Button cnf_btn;
    private TextInputEditText nm_of_sponsor,email_of_sponsor, otp;
    private ArrayList<String> sponsorsList;
    private ArrayList<String> sponsorsId;
    private Spinner sponsorNamesSpinner;
    private ProgressBar mProgressBar;
    private Handler mHandler;
    private String TAG = "RegistrationActivity";
    SharedPreferences sharedPreferences;
    private JSONArray response;
    private Underdog underdog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_registeration);
        Objects.requireNonNull(getSupportActionBar()).setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        getSupportActionBar().setDisplayShowCustomEnabled(true);
        getSupportActionBar().setCustomView(R.layout.custom_action_bar_layout);
        View view = getSupportActionBar().getCustomView();
        TextView activity_name = (TextView) view.findViewById(R.id.activity_name);
        activity_name.setText("Registration");
        ImageView backBtn = (ImageView)view.findViewById(R.id.Back);

        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                onBackPressed();
            }
        });
        context = getApplicationContext();
        underdog = new Underdog(context);
        mHandler = new Handler();

        initialiseViews();
        sponsorsList = new ArrayList<>();
        sponsorsId = new ArrayList<>();
        sponsorsList.add("Select sponsor name");
        sponsorsId.add("0");
        GetSponsorList();

//        ArrayAdapter aa = new ArrayAdapter(context, android.R.layout.simple_spinner_item, sponsorsList);
//        aa.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
//        //Setting the ArrayAdapter data on the Spinner
//        sponsorNamesSpinner.setAdapter(aa);


        sponsorNamesSpinner.setOnItemSelectedListener(this);
    }

    private void initialiseViews(){
        sponsorNamesSpinner = (Spinner) findViewById(R.id.sponsor_name);
        cnf_btn = (Button)findViewById(R.id.register_btn);
        mProgressBar = (ProgressBar)findViewById(R.id.loadingBar);
    }

    private void GetSponsorList() {
        JSONObject args = new JSONObject();
        //JSONObject host = new JSONObject();
        JSONObject parent = new JSONObject();
        try {
//            args.put("hotel_id", sharedPreferences.getString("hotel_id", ""));
//            args.put("logged_email", sharedPreferences.getString("email", ""));
            parent.put("function", "GetSponsorList");
//            parent.put("token", sharedPreferences.getString("token", ""));
            parent.put("token","");
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
                                        final JSONArray sponsorsListRes = response.getJSONObject(0).getJSONArray("sponsorList");
                                        mHandler.post(new Runnable() {
                                            @Override
                                            public void run() {
                                                //Toast.makeText(context, "User added success !!!", Toast.LENGTH_SHORT).show();
                                                if (sponsorsListRes.length() > 0) {
                                                    for (int i = 0; i < sponsorsListRes.length(); i++) {
                                                        try {
                                                            //pmcl = new ProductMgmtCategoryModel(categoryList.getJSONObject(i).getString("category_name"),categoryList.getJSONObject(i).getString("product_quantity"),categoryList.getJSONObject(i).getString("_id"), categoryList.getJSONObject(i).getString("cat_added_on"));
                                                            sponsorsList.add(sponsorsListRes.getJSONObject(i).getString("user_name"));
                                                            sponsorsId.add(sponsorsListRes.getJSONObject(i).getString("_id"));
                                                        } catch (JSONException e) {
                                                            e.printStackTrace();
                                                        }
                                                        //categoryData.add(pmcl);
                                                    }
                                                    ArrayAdapter aa = new ArrayAdapter(context, android.R.layout.simple_spinner_item, sponsorsList);
                                                    aa.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                                                    //Setting the ArrayAdapter data on the Spinner
                                                    sponsorNamesSpinner.setAdapter(aa);
                                                    sponsorNamesSpinner.setSelection(sponsorsList.indexOf("Select sponsor name"));
                                                } else {
                                                    sponsorNamesSpinner.setVisibility(View.INVISIBLE);
//                                                    product_new_edit.setVisibility(View.INVISIBLE);
//                                                    product_price_edit.setVisibility(View.INVISIBLE);
//                                                    enter_admin_password_edit.setVisibility(View.GONE);
//                                                    empty_view_recycler_list.setVisibility(View.VISIBLE);
//                                                    empty_view_recycler_list.setText(R.string.add_new_category_wraning);
//                                                    confirm_btn.setText(R.string.add_new_category_label);

                                                    //category_list.setVisibility(View.INVISIBLE);
                                                    //empty_view_recycler_list.setVisibility(View.VISIBLE);
                                                }
                                            }
                                        });

                                        break;
                                    case "false":
                                        mHandler.post(new Runnable() {
                                            @Override
                                            public void run() {
                                                Toast.makeText(context, "Something went wrong,Try again !!!", Toast.LENGTH_SHORT).show();
//                                                dismiss();
                                            }
                                        });
                                        break;
                                    default:
                                        mHandler.post(new Runnable() {
                                            @Override
                                            public void run() {
                                                Toast.makeText(context, "Something went wrong,Try again !!!", Toast.LENGTH_SHORT).show();
//                                                dismiss();
                                            }
                                        });
                                        break;
                                }
                            } else {
                                mHandler.post(new Runnable() {
                                    @Override
                                    public void run() {

                                        //Log.e(TAG, response.getJSONObject(0).getString("errInResponse"));
                                        Toast.makeText(context, "Try again !!!", Toast.LENGTH_SHORT).show();
                                    }
                                });

                            }
                        } else {
                            mHandler.post(new Runnable() {
                                @Override
                                public void run() {

                                    Toast.makeText(context, "Response is null", Toast.LENGTH_SHORT).show();
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
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}
