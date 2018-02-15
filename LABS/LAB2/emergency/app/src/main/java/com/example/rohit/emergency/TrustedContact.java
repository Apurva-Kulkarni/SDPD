package com.example.rohit.emergency;

/**
 * Created by rohit on 15/2/18.
 */

public class TrustedContact {
    String _name;
    String _phone_number;

    public TrustedContact(String name, String phone_number){
        this._name = name;
        this._phone_number = phone_number;
    }

    // getting name
    public String getName(){
        return this._name;
    }

    // setting name
    public void setName(String name){
        this._name = name;
    }

    // getting phone number
    public String getPhoneNumber(){
        return this._phone_number;
    }

    // setting phone number
    public void setPhoneNumber(String phone_number){
        this._phone_number = phone_number;
    }
}
