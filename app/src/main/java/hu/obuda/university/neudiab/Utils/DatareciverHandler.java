package hu.obuda.university.neudiab.Utils;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;

public class DatareciverHandler {

    private Context context;

    public DatareciverHandler(Context context) {
        this.context = context;
    }

    public void setAlarm(){
        Intent intent= new Intent(context, DataReciver.class);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(context,2,intent,0);
        AlarmManager alarmManager = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
        alarmManager.setRepeating(AlarmManager.RTC_WAKEUP,300000,300000,pendingIntent);
    }

    public void cancelAlarm(){
        Intent intent= new Intent(context,DataReciver.class);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(context,2,intent,0);
        AlarmManager alarmManager = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
        alarmManager.cancel(pendingIntent);
    }
}
