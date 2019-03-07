package com.example.happyrojgar;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import de.hdodenhof.circleimageview.CircleImageView;

public class PopUpMenuDialog extends DialogFragment {

    private CircleImageView cr1, cr2, cr3;
    private TextView usr_nm,usr_email;

    public PopUpMenuDialog() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View v = inflater.inflate(R.layout.fragment_pop_up_menu, container, false);
        initialiseViews(v);

        cr1.setOnClickListener(new View.OnClickListener() {
            @Override public void onClick(View v) {
                Intent intent = new Intent(getContext(), EditProfileActivity.class);
                startActivity(intent);
            }
        });

        cr2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), CashOutActivity.class);
                startActivity(intent);
            }
        });

        cr3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), MainActivity.class);
                startActivity(intent);
            }
        });

        return v;

    }

    private void initialiseViews(View v) {
        cr1 = (CircleImageView) v.findViewById(R.id.edt_id);
        cr2 = (CircleImageView) v.findViewById(R.id.dlr_id);
        cr3 = (CircleImageView) v.findViewById(R.id.lgot_id);
        usr_nm = (TextView)v.findViewById(R.id.usr_nm);
        usr_email = (TextView)v.findViewById(R.id.user_email);
    }

}
