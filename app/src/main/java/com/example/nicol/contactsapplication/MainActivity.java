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
    Button btn_add, btn_save, btn_load;
    ListView lv_thelist;
    SearchView sv_search;

    ContactAdapter adapter;

    AddressBook theList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btn_add = findViewById(R.id.btn_add);
        btn_save = findViewById(R.id.btn_save);
        btn_load = findViewById(R.id.btn_load);
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

            if(url.equals("delete")){
                theList.getTheList().remove(positionEdited + 1);
                adapter.notifyDataSetChanged();
            }
            else {
                if(url.equals("")) {
                    PersonContact p = new PersonContact(name, phone, address, email, "person", change);
                    theList.getTheList().add(p);
                    if(positionEdited > -1){
                        theList.getTheList().remove(positionEdited);
                        adapter.notifyDataSetChanged();
                    }
                    adapter.notifyDataSetChanged();
                } else {
                    BusinessContact p = new BusinessContact(name, phone, address, email, "business", change, url);
                    theList.getTheList().add(p);
                    if(positionEdited > -1){
                        theList.getTheList().remove(positionEdited);
                        adapter.notifyDataSetChanged();
                    }
                    adapter.notifyDataSetChanged();
                }
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
                if(theList.getTheList().get(position) instanceof PersonContact){
                    editPerson(position);
                }
                if(theList.getTheList().get(position) instanceof BusinessContact){
                    editBusiness(position);
                }
            }
        });

        btn_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DataService dataService = new DataService(v.getContext());
                dataService.writeList(theList, "ContactList.txt");
            }
        });

        btn_load.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DataService dataService = new DataService(v.getContext());
                theList.getTheList().clear();
                theList = dataService.readList("ContactList.txt");
                adapter.notifyDataSetChanged();
            }
        });

        sv_search.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                if(theList.searchFor(query) instanceof PersonContact){
                    PersonContact searched = (PersonContact) theList.searchFor(query);
                    for(int i = 0; i < theList.getTheList().size(); i++) {
                        if (theList.getTheList().get(i).getPhone().equals(searched.getPhone())) {
                            editPerson(i);
                        }
                    }
                }//end if person
                if(theList.searchFor(query) instanceof BusinessContact){
                    BusinessContact searched = (BusinessContact) theList.searchFor(query);
                    for(int i = 0; i < theList.getTheList().size(); i++) {
                        if (theList.getTheList().get(i).getPhone().equals(searched.getPhone())) {
                            editBusiness(i);
                        }
                    }
                }//end if business
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
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
