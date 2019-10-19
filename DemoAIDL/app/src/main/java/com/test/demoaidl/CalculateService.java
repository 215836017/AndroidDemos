package com.test.demoaidl;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.RemoteException;
import android.util.Log;

import com.test.aidl.CalculateInterface;

public class CalculateService extends Service {

    private final String tag = "CalculateService.java";

    @Override
    public IBinder onBind(Intent intent) {
        Log.d(tag, "onBind() -- start");
        //  throw new UnsupportedOperationException("Not yet implemented");
        return mBinder;

    }

    @Override
    public void onCreate() {
        Log.d(tag, "onCreate() -- start");
        super.onCreate();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.d(tag, "onStartCommand() -- start");
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public boolean onUnbind(Intent intent) {
        Log.d(tag, "onUnbind() -- start");
        return super.onUnbind(intent);
    }

    private final CalculateInterface.Stub mBinder = new CalculateInterface.Stub() {
        @Override
        public void myPrint(String msg) throws RemoteException {
            Log.i(tag, "CalculateInterface.Stub -- myPrint(), msg = " + msg);
        }

        @Override
        public String calculate(float numA, float numB, int calculateType) throws RemoteException {
            return Calculate.calculateResult(numA, numB, calculateType);
        }

    };
}
