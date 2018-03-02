package com.example.ed_it_art.clientapplication.model.BackEnd;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

/**
 * Created by ed-it-art on 21/01/2018.
 */

public class ClientReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {

       // Log.d("client serviece", "onReceive");
        CharSequence intentData = intent.getCharSequenceExtra("message");
        Toast.makeText(context, "Javacodegeeks received the Intent's message:  "
                +intentData,Toast.LENGTH_LONG).show();
        if(intent.getAction().matches("android.intent.action.BATTERY_LOW"))
            Toast.makeText(context,"BATTERY_LOW",Toast.LENGTH_SHORT).show();
    }
}
