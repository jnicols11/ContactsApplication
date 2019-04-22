package com.example.nicol.contactsapplication;

import android.content.Context;
import android.util.Log;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;

public class DataService {

    Context context;

    ObjectMapper om = new ObjectMapper();

    public DataService(Context context){this.context = context;}

    public void writeList(AddressBook theList, String filename){


        File path = context.getExternalFilesDir(null);
        File file = new File(path, filename);
        try{

            om.writerWithDefaultPrettyPrinter().writeValue(file, theList);

        }catch(IOException e){
            e.printStackTrace();
        }
    }

    public AddressBook readList(String filename){
        File path = context.getExternalFilesDir(null);
        File file = new File(path, filename);
        AddressBook returnList = new AddressBook();
        try{
            Log.d("myapp", "going to load json");
            returnList = om.readValue(file, AddressBook.class);
            Log.d("myapp", returnList.toString());
        }catch(IOException e){
            Log.d("myapp", "error reading file");
            e.printStackTrace();
        }
        return returnList;
    }
}
