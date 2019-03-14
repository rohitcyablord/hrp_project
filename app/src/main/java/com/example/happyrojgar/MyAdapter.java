package com.example.happyrojgar;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class MyAdapter extends ArrayAdapter {

    ArrayList<Item> transac_List = new ArrayList<>();

    public MyAdapter(Context context, int textViewResourceId, ArrayList<Item> objects) {
        super(context, textViewResourceId, objects);
        transac_List = objects;
    }

    @Override
    public int getCount() {
        return super.getCount();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View v = convertView;
        LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        v = inflater.inflate(R.layout.custom_transaction_view, null);
        TextView transfer_amt = (TextView) v.findViewById(R.id.transfer_amount);
        TextView user_nm = (TextView) v.findViewById(R.id.user_name);
        TextView trans_date = (TextView) v.findViewById(R.id.transaction_date);
        transfer_amt.setText(transac_List.get(position).getTransactionAmount());
        user_nm.setText(transac_List.get(position).getUserName());
        trans_date.setText(transac_List.get(position).getTransactionDate());
        return v;

    }

}