package com.example.rohit.emergency;

/**
 * Created by rohit on 15/2/18.
 */

public interface SmsListener {
    void messageReceived(String messageText, String sender);
}
