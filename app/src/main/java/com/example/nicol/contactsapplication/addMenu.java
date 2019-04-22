package com.example.nicol.contactsapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;

public class addMenu extends AppCompatActivity {

    Button btn_save, btn_cancel;
    RadioGroup rg_base;
    RadioButton rb_business, rb_personal;
    EditText et_name, et_phone, et_address, et_email, et_change, et_url;

    int positionToEdit = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_menu);
        btn_save = findViewById(R.id.btn_save);
        btn_cancel = findViewById(R.id.btn_cancel);
        rg_base = findViewById(R.id.rg_base);
        rb_business = findViewById(R.id.rb_business);
        rb_personal = findViewById(R.id.rb_personal);
        et_name = findViewById(R.id.et_name);
        et_phone = findViewById(R.id.et_phone);
        et_address = findViewById(R.id.et_address);
        et_email = findViewById(R.id.et_email);
        et_change = findViewById(R.id.et_change);
        et_url = findViewById(R.id.et_url);

        rb_business.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                et_change.setHint("Hours:");
                et_change.setVisibility(View.VISIBLE);
                et_url.setVisibility(View.VISIBLE);
            }
        });
        rb_personal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                et_change.setHint("Birthday:");
                et_change.setVisibility(View.VISIBLE);
                et_url.setVisibility(View.INVISIBLE);
                et_url.setText("");
            }
        });

        btn_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                // get strings from et_ objects
                String newName = et_name.getText().toString();
                String newPhone = et_phone.getText().toString();
                String newAddress = et_address.getText().toString();
                String newEmail = et_email.getText().toString();
                String newChange = et_change.getText().toString();
                String newUrl = et_url.getText().toString();

                //put the strings into a message

                //start main activity
                Intent i = new Intent(v.getContext(), MainActivity.class);

                i.putExtra("name", newName);
                i.putExtra("phone", newPhone);
                i.putExtra("address", newAddress);
                i.putExtra("email", newEmail);
                i.putExtra("change", newChange);
                i.putExtra("url", newUrl);
                i.putExtra("edit", positionToEdit);

                startActivity(i);
            }
        });
    }
}
