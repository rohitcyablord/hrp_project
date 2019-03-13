package com.example.happyrojgar;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.design.widget.TextInputEditText;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class BankDetailsDialog extends DialogFragment {
    private TextInputEditText name_on_acc,account_number,confirm_acc_number,ifs_code;
    private ProgressBar mProgressBar;
    private Handler mHandler;
    private String TAG = "RegistrationActivity";
    private Context context;
    private Button update_btn;
    private Underdog underdog;
    private JSONArray response;
    SharedPreferences sharedPreferences;

    public BankDetailsDialog() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_bank_details_dialog, container, false);

        this.context = getContext();
        underdog = new Underdog(context);
        mHandler = new Handler();
        sharedPreferences = context.getSharedPreferences("MyPrefs", Context.MODE_PRIVATE);
        InitialiseViews(v);
        update_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                UpdateBankDetails();
            }
        });


        return v;
    }

    private void InitialiseViews(View v){
        name_on_acc = (TextInputEditText) v.findViewById(R.id.name_on_acc);
        account_number = (TextInputEditText) v.findViewById(R.id.account_number);
        confirm_acc_number = (TextInputEditText) v.findViewById(R.id.confirm_acc_number);
        ifs_code = (TextInputEditText) v.findViewById(R.id.ifs_code);
        update_btn = (Button) v.findViewById(R.id.update_btn);
        mProgressBar = (ProgressBar) v.findViewById(R.id.loadingBar);
    }

    private void UpdateBankDetails(){
        JSONObject args = new JSONObject();
        JSONObject parent = new JSONObject();
        try {
            args.put("userName",sharedPreferences.getString("userName",""));
            args.put("nameOnAccount",String.valueOf(name_on_acc.getText()));
            args.put("accountNumber",String.valueOf(account_number.getText()));
            args.put("ifsCode",String.valueOf(ifs_code.getText()));
            parent.put("function", "UpdateBankDetails");
            parent.put("token", sharedPreferences.getString("token", ""));
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
                                                Toast.makeText(context, "Bank details updated !!!", Toast.LENGTH_SHORT).show();
                                                update_btn.setEnabled(true);
                                                dismiss();
                                                Intent intent = new Intent(context, LandingActivity.class);
                                                startActivity(intent);
                                            }
                                        });

                                        break;
                                    case "false":
                                        mHandler.post(new Runnable() {
                                            @Override
                                            public void run() {
                                                update_btn.setEnabled(true);
                                                Toast.makeText(context, "Failed to update Bank details !!! Try again ", Toast.LENGTH_SHORT).show();
                                            }
                                        });
                                        break;
                                    default:
                                        mHandler.post(new Runnable() {
                                            @Override
                                            public void run() {
                                                update_btn.setEnabled(true);
                                                Toast.makeText(context, "Something went wrong,Try again !!!", Toast.LENGTH_SHORT).show();
                                            }
                                        });
                                        break;
                                }
                            } else {
                                mHandler.post(new Runnable() {
                                    @Override
                                    public void run() {
                                        update_btn.setEnabled(true);
                                        //Log.e(TAG, response.getJSONObject(0).getString("errInResponse"));
                                        Toast.makeText(context, "Try again !!!", Toast.LENGTH_SHORT).show();
                                    }
                                });

                            }
                        } else {
                            mHandler.post(new Runnable() {
                                @Override
                                public void run() {
                                    update_btn.setEnabled(true);
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

}
