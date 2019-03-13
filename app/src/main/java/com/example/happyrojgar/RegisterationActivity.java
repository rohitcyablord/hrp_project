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
import android.widget.LinearLayout;
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
    private Button register_btn;
    private LinearLayout bank_details_lay;
    private TextView bank_Details_label;
    private TextInputEditText name_on_acc,account_number,confirm_acc_number,ifs_code,user_name,contact_no,fullname;
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
        this.context = getApplicationContext();
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

        bank_Details_label.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (bank_details_lay.getVisibility() == View.VISIBLE){
                    bank_details_lay.setVisibility(View.INVISIBLE);
                }else{
                    bank_details_lay.setVisibility(View.VISIBLE);
                }
            }
        });

        register_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (validateFields()){
                    RegisterUser();
                }else{
                    Toast.makeText(context,"Invalid",Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void initialiseViews(){

        sponsorNamesSpinner = (Spinner) findViewById(R.id.sponsor_name);
        user_name = (TextInputEditText) findViewById(R.id.user_name);
        fullname = (TextInputEditText) findViewById(R.id.fullname);
        name_on_acc = (TextInputEditText)findViewById(R.id.name_on_acc);
        contact_no = (TextInputEditText)findViewById(R.id.contact_no);
        account_number = (TextInputEditText)findViewById(R.id.account_number);
        confirm_acc_number = (TextInputEditText)findViewById(R.id.confirm_acc_number);
        ifs_code = (TextInputEditText)findViewById(R.id.ifs_code);
        register_btn = (Button)findViewById(R.id.register_btn);
        mProgressBar = (ProgressBar)findViewById(R.id.loadingBar);
        bank_details_lay = (LinearLayout)findViewById(R.id.bank_details_lay);
        bank_Details_label = (TextView) findViewById(R.id.bank_Details_label);

    }

    private void RegisterUser(){
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
                                                    ArrayAdapter aa = new ArrayAdapter(getBaseContext(),android.R.layout.simple_spinner_item, sponsorsList);
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

    private boolean validateFields(){
        return true;
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}
