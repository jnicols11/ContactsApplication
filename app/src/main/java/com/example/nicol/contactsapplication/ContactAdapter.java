package com.example.nicol.contactsapplication;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class ContactAdapter extends BaseAdapter {

    Activity mActivity;
    AddressBook theList;

    public ContactAdapter(Activity mActivity, AddressBook theList) {
        this.mActivity = mActivity;
        this.theList = theList;
    }//end constructor

    @Override
    public int getCount() {
        return theList.getTheList().size();
    }

    @Override
    public BaseContact getItem(int position) {
        return theList.getTheList().get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View onePersonLine;

        LayoutInflater inflater = (LayoutInflater) mActivity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        onePersonLine = inflater.inflate(R.layout.person_one_line, parent, false);

        TextView tv_name = onePersonLine.findViewById(R.id.tv_name);
        TextView tv_phone = onePersonLine.findViewById(R.id.tv_phone);

        BaseContact b = this.getItem(position);

        tv_name.setText(b.getName());
        tv_phone.setText(b.getPhone());

        return onePersonLine;
    }
}
