package com.example.rohit.emergency;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.telephony.SmsMessage;

/**
 * Created by rohit on 15/2/18.
 */

public class SmsReceiver extends BroadcastReceiver {

    // Interface
    private static SmsListener mListener;

    @Override
    public void onReceive(Context context, Intent intent) {
        Bundle data  = intent.getExtras();

        // PDU: “protocol data unit”, the industry format for an SMS message
        Object[] pdus = (Object[]) data.get("pdus");

        for(int i=0; i<pdus.length; i++){

            SmsMessage smsMessage = SmsMessage.createFromPdu((byte[]) pdus[i]);

            String sender = smsMessage.getDisplayOriginatingAddress();
            // We can use this sender to filter messages

            String messageBody = smsMessage.getMessageBody();
            // Pass the message text to interface
            mListener.messageReceived(messageBody, sender);
        }
    }

    public static void bindListener(SmsListener listener) {
        mListener = listener;
    }
}
