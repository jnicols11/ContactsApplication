package com.example.nicol.contactsapplication;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class editPerson extends AppCompatActivity {
    Button btn_call, btn_text, btn_email, btn_gps, btn_save, btn_delete;
    EditText et_name, et_phone, et_address, et_email, et_change;

    int positionToEdit = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_person);
        btn_call = findViewById(R.id.btn_call);
        btn_text = findViewById(R.id.btn_text);
        btn_email = findViewById(R.id.btn_email);
        btn_gps = findViewById(R.id.btn_gps);
        btn_save = findViewById(R.id.btn_save);
        btn_delete = findViewById(R.id.btn_delete);

        et_name = findViewById(R.id.et_name);
        et_phone = findViewById(R.id.et_phone);
        et_address = findViewById(R.id.et_address);
        et_email = findViewById(R.id.et_email);
        et_change = findViewById(R.id.et_change);

        Bundle incomingIntent = getIntent().getExtras();

        if(incomingIntent != null){
            String name = incomingIntent.getString("name");
            String phone = incomingIntent.getString("phone");
            String address = incomingIntent.getString("address");
            String email = incomingIntent.getString("email");
            String change = incomingIntent.getString("change");
            positionToEdit = incomingIntent.getInt("edit");

            et_name.setText(name);
            et_phone.setText(phone);
            et_address.setText(address);
            et_email.setText(email);
            et_change.setText(change);
        }

        btn_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // get strings from et_ objects
                String newName = et_name.getText().toString();
                String newPhone = et_phone.getText().toString();
                String newAddress = et_address.getText().toString();
                String newEmail = et_email.getText().toString();
                String newChange = et_change.getText().toString();
                String newUrl = "";

                //put the strings into a message

                //start main activity
                Intent i = new Intent(v.getContext(), MainActivity.class);

                i.putExtra("edit", positionToEdit);
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

        btn_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(v.getContext(), MainActivity.class);
                i.putExtra("url", "delete");
                startActivity(i);
            }
        });

        btn_email.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String [] addresses = new String [1];
                addresses[0] = et_email.getText().toString();

                composeEmail(addresses, "New Email.");
            }
        });

        btn_call.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialPhoneNumber(et_phone.getText().toString());
            }
        });

        btn_text.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                composeMmsMessage(et_phone.getText().toString());
            }
        });

        btn_gps.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String mapsQuery = "geo:0,0?q= " + et_address.getText().toString();
                Uri mapuri = Uri.parse(mapsQuery);
                showMap(mapuri);
            }
        });
    }

    public void composeEmail(String[] addresses, String subject){
        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.setType("*/*");
        intent.putExtra(Intent.EXTRA_EMAIL, addresses);
        intent.putExtra(Intent.EXTRA_SUBJECT, subject);
        if(intent.resolveActivity(getPackageManager()) != null){
            startActivity(intent);
        }
    }

    public void dialPhoneNumber(String phoneNumber) {
        Intent intent = new Intent(Intent.ACTION_DIAL);
        intent.setData(Uri.parse("tel:" + phoneNumber));
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        }
    }

    public void composeMmsMessage(String message) {
        Intent intent = new Intent(Intent.ACTION_SENDTO);
        intent.setData(Uri.parse("smsto:" + message));  // This ensures only SMS apps respond
        intent.putExtra("sms_body", "");
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        }
    }

    public void showMap(Uri geoLocation) {
        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setData(geoLocation);
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        }
    }
}

