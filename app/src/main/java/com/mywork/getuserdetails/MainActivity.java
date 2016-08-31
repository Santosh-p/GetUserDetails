package com.mywork.getuserdetails;

import android.accounts.Account;
import android.accounts.AccountManager;
import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.telephony.TelephonyManager;
import android.util.Patterns;
import android.widget.TextView;

import java.util.regex.Pattern;

public class MainActivity extends AppCompatActivity {
    TextView textView1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textView1=(TextView)findViewById(R.id.textView1);
        // Get the instance of TelephonyManager
        TelephonyManager tm=(TelephonyManager)getSystemService(Context.TELEPHONY_SERVICE);

        //Calling the methods of TelephonyManager the returns the information
        String IMEINumber=tm.getDeviceId();
        String subscriberID=tm.getDeviceId();
        String SIMSerialNumber=tm.getSimSerialNumber();
        String networkCountryISO=tm.getNetworkCountryIso();
        String SIMCountryISO=tm.getSimCountryIso();
        String softwareVersion=tm.getDeviceSoftwareVersion();
        String voiceMailNumber=tm.getVoiceMailNumber();
        String mobileNumber=tm.getLine1Number();
        String networkOperator=tm.getNetworkOperator();
        int CallState=tm.getCallState();

        //Get the phone type
        String strphoneType="";

        int phoneType=tm.getPhoneType();

        switch (phoneType)
        {
            case (TelephonyManager.PHONE_TYPE_CDMA):
                strphoneType="CDMA";
                break;
            case (TelephonyManager.PHONE_TYPE_GSM):
                strphoneType="GSM";
                break;
            case (TelephonyManager.PHONE_TYPE_NONE):
                strphoneType="NONE";
                break;
        }
        String possibleEmail = null,possibleEmail1 = null;
        //getting information if phone is in roaming
        boolean isRoaming=tm.isNetworkRoaming();

        Pattern emailPattern = Patterns.EMAIL_ADDRESS; // API level 8+

        Account[] accounts = AccountManager.get(this.getApplicationContext()).getAccounts();
        int t=0;
        for (Account account : accounts) {

            if (emailPattern.matcher(account.name).matches()) {
                if(t==0){
                    possibleEmail = account.name;
                }else {
                    possibleEmail1 = account.name;
                }
            }
        }
        String info ="Phone Details:\n";
        info+="\n IMEI Number:"+IMEINumber;
        info+="\n SubscriberID:"+subscriberID;
        info+="\n Sim Serial Number:"+SIMSerialNumber;
        info+="\n Network Country ISO:"+networkCountryISO;
        info+="\n SIM Country ISO:"+SIMCountryISO;
        info+="\n Software Version:"+softwareVersion;
        info+="\n Voice Mail Number:"+voiceMailNumber;
        info+="\n Voice Mobile Number:"+mobileNumber;
        info+="\n Network Operator:"+networkOperator;
        info+="\n Call State:"+CallState;
        info+="\n Phone Network Type:"+strphoneType;
        info+="\n In Roaming? :"+isRoaming;
        info+="\n Email1:"+possibleEmail;
        info+="\n Email2:"+possibleEmail1;

        textView1.setText(info);

    }
}
