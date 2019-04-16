package com.example.nicol.contactsapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SearchView;

public class MainActivity extends AppCompatActivity {
    Button btn_add, btn_search;
    ListView lv_thelist;
    SearchView sv_search;

    ContactAdapter adapter;

    AddressBook theList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btn_add = findViewById(R.id.btn_add);
        btn_search = findViewById(R.id.btn_search);
        lv_thelist = findViewById(R.id.lv_thelist);
        sv_search = findViewById(R.id.sv_search);

        theList = ((MyApplication) this.getApplication()).getTheList();
        adapter = new ContactAdapter(MainActivity.this, theList);

        lv_thelist.setAdapter(adapter);

        // listen for incoming messages
        Bundle incomingMessages = getIntent().getExtras();

        // capture incoming data
        if(incomingMessages != null){
            String name = incomingMessages.getString("name");
            String phone = incomingMessages.getString("phone");
            String address = incomingMessages.getString("address");
            String email = incomingMessages.getString("email");
            String change = incomingMessages.getString("change");
            String url = incomingMessages.getString("url");
            int positionEdited = incomingMessages.getInt("edit");

            if(url.equals("")){
                PersonContact p = new PersonContact(name, phone, address, email, change);
                if(positionEdited > -1){
                    theList.getTheList().remove(positionEdited);
                }
                theList.getTheList().add(p);
                adapter.notifyDataSetChanged();
            }else{
                BusinessContact p = new BusinessContact(name, phone, address, email, change, url);
                if(positionEdited > -1){
                    theList.getTheList().remove(positionEdited);
                }
                theList.getTheList().add(p);
                adapter.notifyDataSetChanged();
            }

        }

        btn_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), addMenu.class);
                startActivity(i);
            }
        });

        lv_thelist.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if(PersonContact.class.isInstance(theList.getTheList().get(position))){
                    editPerson(position);
                }else{
                    editBusiness(position);
                }
            }
        });

    }

    public void editPerson(int position){
        Intent i = new Intent(getApplicationContext(), editPerson.class);

        PersonContact p = (PersonContact) theList.getTheList().get(position);

        i.putExtra("edit", position);
        i.putExtra("name", p.getName());
        i.putExtra("phone", p.getPhone());
        i.putExtra("address", p.getAddress());
        i.putExtra("email", p.getEmail());
        i.putExtra("change", p.getBirthday());

        startActivity(i);
    }

    public void editBusiness(int position){
        Intent i = new Intent(getApplicationContext(), editBusiness.class);

        BusinessContact b = (BusinessContact) theList.getTheList().get(position);

        i.putExtra("edit", position);
        i.putExtra("name", b.getName());
        i.putExtra("phone", b.getPhone());
        i.putExtra("address", b.getAddress());
        i.putExtra("email", b.getEmail());
        i.putExtra("change", b.getHours());
        i.putExtra("url", b.getUrl());

        startActivity(i);
    }


}
