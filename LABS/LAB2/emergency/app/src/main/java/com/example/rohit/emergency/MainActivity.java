package com.example.rohit.emergency;

import android.Manifest;
import android.content.pm.PackageManager;
import android.location.Location;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.widget.TextView;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnSuccessListener;

import java.util.List;
import java.util.ListIterator;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MainActivity extends AppCompatActivity {

    public static final String OTP_REGEX = "LocatePhone";
    SmsManager sms = SmsManager.getDefault();
    DatabaseHandler db = new DatabaseHandler(this);
    private String msg;
    GPSTracker gps = new GPSTracker(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final TextView smsTextView = (TextView) findViewById(R.id.text_view_sms);
        final TextView otpTextView = (TextView) findViewById(R.id.text_view_otp);

        TrustedContact tc = new TrustedContact("Shubhank", "+919783460709");
        db.addContact(tc);

        SmsReceiver.bindListener(new SmsListener() {
            @Override
            public void messageReceived(String messageText, String sender) {
                System.out.println(messageText);
                smsTextView.setText(messageText);

                // If your OTP is a number that can have maximum 8 digits.

                Pattern pattern = Pattern.compile(OTP_REGEX);
                Matcher matcher = pattern.matcher(messageText);
                String otp = "No OTP Present";

                while (matcher.find()) {
                    otp = matcher.group();
                }

                if (!otp.equals("No OTP Present")) {
                    List<TrustedContact> contacts = db.getAllContacts();
                    ListIterator<TrustedContact> li = contacts.listIterator();

                    while (li.hasNext()) {
                        TrustedContact t = li.next();
                        System.out.println(t.getPhoneNumber());
                        if(t.getPhoneNumber().equals(sender)){
                            if(gps.canGetLocation()){
                                String message;
                                message = "Latidute: " + gps.getLatitude() + "Longitude: " + gps.getLongitude();
                                //sms.sendTextMessage(sender, null, "Latidute: 15.38"  + "Longitude: 73.87", null, null);
                                sms.sendTextMessage(sender, null, message, null, null);
                                otpTextView.setText("OTP is: " + otp);
                                otpTextView.append("\n location sent!!");
                            }
                            else {
                                sms.sendTextMessage(sender, null, "cannot fetch location", null, null);
                                otpTextView.setText("OTP is: " + otp);
                                otpTextView.append("\n Unable to send location!!");
                            }
                        }
                        else{
                            otpTextView.setText("OTP is:" + otp);
                            otpTextView.append("\n Not in Trusted Contacts!!");
                        }
                    }
                }
                else{
                    otpTextView.setText(otp);
                }
            }
        });
    }
}