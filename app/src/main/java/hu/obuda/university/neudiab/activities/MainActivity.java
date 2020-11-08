package hu.obuda.university.neudiab.activities;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;
import java.util.Random;

import hu.obuda.university.neudiab.R;
import hu.obuda.university.neudiab.Utils.CarvViewItem;
import hu.obuda.university.neudiab.Utils.DATA_CONTANIER;
import hu.obuda.university.neudiab.Utils.DatareciverHandler;
import hu.obuda.university.neudiab.Utils.DecisionData;
import hu.obuda.university.neudiab.Utils.Dieabetdata;
import hu.obuda.university.neudiab.Utils.MainActivityRecViewAdapter;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        FirebaseAuth mAuth = FirebaseAuth.getInstance();
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        RecyclerView rec = (RecyclerView) findViewById(R.id.main_activity_rec_view);
        final TextView nametext = (TextView) findViewById(R.id.main_activity_name_text);
        /*db.collection("users").document(mAuth.getUid()).get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                if (task.isSuccessful()){
                    nametext.setText(task.getResult().get("name").toString());
                }
            }
        });*/
        //Intent intet = new Intent(LogInActivity.this,MainActivity.class);
       // Toast.makeText(LogInActivity.this,"Login succesfull",Toast.LENGTH_LONG).show();
        /*Map<String, Object> data = new HashMap<>();
        Map<String,Object> tole = new HashMap<>();
        for (Integer i=0;i<10;++i){
            Map<String,Object> aldata = new HashMap<>();
            SimpleDateFormat formatter= new SimpleDateFormat("yyyy-MM-dd");
            Date date = new Date(System.currentTimeMillis());
            Map<String,Object> measureddata = new HashMap<>();
            for(Integer j=0;j<288;++j){
                measureddata.put(j.toString(),new Random().nextInt(300));
            }
            Map<String,Object> decioss = new HashMap<>();
            for(Integer j=0;j<288;++j){
                decioss.put(j.toString(),new Random().nextInt(2));
            }
            aldata.put("measured",measureddata);
            aldata.put("decission",decioss);
            Long ido = date.getTime();
            tole.put(ido.toString(),aldata);
            data.put("userdata",tole);
        }
        db = FirebaseFirestore.getInstance();


        db.collection("users").document(mAuth.getUid()).set(data, SetOptions.merge()).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if (task.isSuccessful()){
                    System.out.println("szia");
                }
            }
        });*/
        rec.setLayoutManager(layoutManager);
        rec.setItemAnimator(new DefaultItemAnimator());
        ArrayList<CarvViewItem> carvViewItems = new ArrayList<>();
        for (int i=0;i<4;++i){
            CarvViewItem carvViewItem = new CarvViewItem();
            carvViewItem.setDate("2020.9.10");
            ArrayList<DecisionData> decisionData = new ArrayList<>();
            for (int j = 0;j<4;++j){
                DecisionData decisionData1 = new DecisionData();
                decisionData1.setDecissionPoint(j);
                decisionData1.setDecissionValue(new Random().nextInt(2));
                decisionData1.setDecissionValueProbability(new Random().nextFloat());
                decisionData.add(decisionData1);
            }
            ArrayList<Dieabetdata> dieabetdata = new ArrayList<>();
            for (int j=0;j<1;++j){
                Dieabetdata dieabetdata1 = new Dieabetdata();
                dieabetdata1.setMeasuredPoint(j);
                dieabetdata1.setMeasuredValue(new Random().nextInt(300));
                dieabetdata.add(dieabetdata1);
            }
            carvViewItem.setDecisionData(decisionData);
            carvViewItem.setDieabetdata(dieabetdata);
            carvViewItems.add(carvViewItem);
        }
        MainActivityRecViewAdapter adapter = new MainActivityRecViewAdapter(carvViewItems);
        rec.setAdapter(adapter);
        DATA_CONTANIER.setrecview(adapter);
        //FirebaseJobDispatcher dispatcher =  new FirebaseJobDispatcher(new GooglePlayDriver(getApplicationContext()));
        //Job.Builder exampleJob = dispatcher.newJobBuilder()
          //      .setService(DataReciverServices.class)
           //     .setTag("jobbbbb")
            //    .setRecurring(true)
             //   .setLifetime(Lifetime.FOREVER)
              //  .setReplaceCurrent(true)
               // .setRetryStrategy(RetryStrategy.DEFAULT_EXPONENTIAL)
                //.setConstraints(ON_UNMETERED_NETWORK, DEVICE_CHARGING)
               // .setTrigger(Trigger.executionWindow(30, 30));
        //dispatcher.mustSchedule(exampleJob.build());
        Log.d("JobScheduler", "Scheduled job");
       // Constraints constraints = new Constraints.Builder()
             //   .setRequiredNetworkType(NetworkType.CONNECTED)
              //  .build();
        // PeriodicWorkRequest periodicWorkRequest = new PeriodicWorkRequest.Builder(
       // PeriodicWorkRequest periodicWorkRequest = new PeriodicWorkRequest.Builder(
         //      proba.class,1,TimeUnit.MINUTES,30,TimeUnit.SECONDS).addTag("TESZTEORKER")
           //     .build();
        //WorkManager.getInstance(getApplicationContext()).enqueue(periodicWorkRequest);
        DatareciverHandler datareciverHandler = new DatareciverHandler(this);
        datareciverHandler.cancelAlarm();
        datareciverHandler.setAlarm();

        //WorkManager.getInstance().getWorkInfoByIdLiveData(periodicWorkRequest.getId()).observe(life);
    }
}