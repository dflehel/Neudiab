package hu.obuda.university.neudiab.Utils;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

import java.util.Date;

import hu.obuda.university.neudiab.APIs.DataAPIRequest;

public class DataReciver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        Log.d("bazdmeg menjel mar",new Date().toString());
        new DataAPIRequest().execute();
        //System.out.println(new Random().nextInt(300)+"kejhkhsughrjkbfhigugfd"+new Date().toString() );
    }


}
