package com.example.ed_it_art.clientapplication.model.BackEnd;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;
import android.widget.Toast;

/**
 * Created by ed-it-art on 21/01/2018.
 */

public class ClientService extends Service {
    final String TAG = "clientService";
    static boolean ServiceRun;// = false;
    DBmanager  dBmanager;
    static {
        ServiceRun = false;
    }

    public ClientService() {
    }
    @Override
    public void onCreate() {
        super.onCreate();
 /*       Log.d(TAG, "onCreate");
        dBmanager = DBManagerFactory.GetDB();


        Toast.makeText(this, "start service", Toast.LENGTH_SHORT).show();
        Thread t = new Thread() {
            @Override
            public void run() {

                while (true) {
                    try {
                        Thread.sleep(10000);
                        Log.d(TAG, "thread run ..");
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    if (dBmanager.isUpdate()) {
                        Log.d(TAG, "isUpdatet run ..");
                        Intent intent1 = new Intent("com.boukris.RentCar.update");
                        ClientService.this.sendBroadcast(intent1);
                        //sendBroadcast(intent);
                    }

                }
            }
        };

        t.start();

*/

    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.d(TAG, "onStartCommand");

        if (!ServiceRun) {
            ServiceRun = true;
            Toast.makeText(this, "run service", Toast.LENGTH_LONG).show();
            return START_STICKY;
        }

        Toast.makeText(this, "The service is already running", Toast.LENGTH_LONG).show();
        return START_STICKY;
    }

    @Override
    public void onDestroy() {
        Log.d(TAG,"onDestroy");
        super.onDestroy();
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        Log.d(TAG, "onBind");
        return null;
    }
}
