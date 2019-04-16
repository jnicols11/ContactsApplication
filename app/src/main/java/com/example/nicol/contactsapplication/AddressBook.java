package com.example.nicol.contactsapplication;

import java.util.ArrayList;
import java.util.List;

public class AddressBook {
    private List<BaseContact> theList;

    //default constructor
    public AddressBook() {
        this.theList = new ArrayList<BaseContact>();	//Creating an empty list of BaseContacts
        PersonContact p1 = new PersonContact("Jordan", "9518471268", "1592 Clydesdale ct", "nicolsj99@gmail.com", "May 3, 1999");
        theList.add(p1);
    }//end default constructor


    //Generics
    public <T extends BaseContact> void addOne(T contact) {
        this.theList.add(contact);
    }

    //Generics
    public <T extends BaseContact> boolean deleteOne(T contact) {
        if(this.theList.contains(contact)) {
            this.theList.remove(contact);
            return true;
        } else {
            return false;
        }//end if/else

    }

    public void showList() {
        for(int i = 0; i < this.theList.size(); i++) {
            System.out.println("(" + (i+1) + ")" + this.theList.get(i).getName());
        }
    }


    public BaseContact searchFor(String search) {

        for(int i = 0; i < theList.size(); i++) {
            String name = theList.get(i).getName();
            name = name.toLowerCase();
            search = search.toLowerCase();
            int j = 0;
            while(name.charAt(j) == search.charAt(j)) {
                j++;
                if(j == search.length()) {
                    return theList.get(i);
                }//end if
            }//end while
        }//end for
        return null;
    }

    //Returning the called list using generics
    public List<BaseContact> getTheList() {
        return theList;
    }

    @Override
    public String toString() {
        String str = "";
        for(int i = 0; i < theList.size(); i++) {

            str += "Contact " + (i+1) + "\n" + theList.get(i) + "\n";

        }
        return str;
    }

}
