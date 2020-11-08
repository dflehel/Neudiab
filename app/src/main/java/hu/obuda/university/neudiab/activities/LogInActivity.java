package hu.obuda.university.neudiab.activities;

import android.Manifest;
import android.app.Activity;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothGatt;
import android.bluetooth.BluetoothGattCallback;
import android.bluetooth.BluetoothGattCharacteristic;
import android.bluetooth.BluetoothGattDescriptor;
import android.bluetooth.BluetoothManager;
import android.bluetooth.BluetoothProfile;
import android.content.Context;
import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.icu.util.Calendar;
import android.os.Bundle;
import android.os.ParcelUuid;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.fitness.Fitness;
import com.google.android.gms.fitness.FitnessOptions;
import com.google.android.gms.fitness.data.BleDevice;
import com.google.android.gms.fitness.data.DataType;
import com.google.android.gms.fitness.request.BleScanCallback;
import com.google.android.gms.fitness.request.DataReadRequest;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import java.math.BigInteger;
import java.nio.ByteBuffer;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Observable;
import java.util.Observer;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

import hu.obuda.university.neudiab.R;
import hu.obuda.university.neudiab.miband.ActionCallback;
import hu.obuda.university.neudiab.miband.MiBand;
import hu.obuda.university.neudiab.miband.listeners.HeartRateNotifyListener;
import hu.obuda.university.neudiab.miband.listeners.NotifyListener;
import hu.obuda.university.neudiab.miband.model.UserInfo;

//import hu.obuda.university.neudiab.miband.model.Profile;

public class LogInActivity extends AppCompatActivity {


    private static final String TAG = "SZep";




    BluetoothDevice bluetoothDevice;
    // BLUETOOTH





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityCompat.requestPermissions(this,new String[]{Manifest.permission.BODY_SENSORS,Manifest.permission.ACCESS_FINE_LOCATION,Manifest.permission.BLUETOOTH_PRIVILEGED,Manifest.permission.BLUETOOTH,Manifest.permission.BLUETOOTH_ADMIN,Manifest.permission.ACCESS_COARSE_LOCATION},255);
        final String TAG = "kurva";

      //  mBluetoothManager = ((BluetoothManager) getSystemService(Context.BLUETOOTH_SERVICE));
      //  mBluetoothAdapter = mBluetoothManager.getAdapter();
       // mBluetoothMi = mBluetoothAdapter.getRemoteDevice("E5:3B:9C:41:37:0B");
        final BluetoothManager bluetooothManager = (BluetoothManager)getSystemService(Context.BLUETOOTH_SERVICE);
        List<BluetoothDevice> gattServerConnectedDevices = bluetooothManager.getConnectedDevices(BluetoothProfile.GATT_SERVER);
        for (BluetoothDevice device : gattServerConnectedDevices) {
            bluetoothDevice = device;
         //   bluetoothGatt = device.connectGatt(this,true,bluetoothGattCallback);
            HashMap<UUID,String> MIBAND_DEBUG = new HashMap<>();

            MIBAND_DEBUG.put(UUID.fromString("00002a39-0000-1000-8000-00805f9b34fb"), "Heart Rate Control Point");
            MIBAND_DEBUG.put(UUID.fromString("00002a37-0000-1000-8000-00805f9b34fb"), "Heart Rate Measurement");
            if (device.getAddress().toString().equalsIgnoreCase("E5:3B:9C:41:37:0B")) {
                MiBand miband = new MiBand(this);
                miband.connect(device, new ActionCallback() {

                    @Override
                    public void onSuccess(Object data) {
                        Log.d("sbjdsbvljbvkbvjwvbwqegfuelbgvuw", "connect success");
                        long hi = device.getUuids()[0].getUuid().getMostSignificantBits();
                        long lo = device.getUuids()[0].getUuid().getLeastSignificantBits();
                        byte[] bytes = ByteBuffer.allocate(16).putLong(hi).putLong(lo).array();
                        BigInteger big = new BigInteger(bytes);
                        String numericUuid = big.toString().replace('-', '1'); // just in case
                        UserInfo userInfo = new UserInfo(big.intValue(), 1, 32, 180, 55, "胖梁", 0);
                        miband.setUserInfo(userInfo);
                        miband.enableSensorDataNotify();
                        miband.startHeartRateScan();
                        miband.setSensorDataNotifyListener(new NotifyListener() {
                            @Override
                            public void onNotify(byte[] data) {
                                System.out.println("esaz" + data.toString());

                            }
                        });
                        miband.setHeartRateScanListener(new HeartRateNotifyListener() {
                            @Override
                            public void onNotify(int heartRate) {
                                System.out.println("" + heartRate);
                            }
                        });


                    }

                    @Override
                    public void onFail(int errorCode, String msg) {
                        Log.d(TAG, "connect fail, code:" + errorCode + ",mgs:" + msg);
                    }
                });

                //byte[] data = Char.getValue();
                //System.out.println(data.toString());
                Log.d("Tag", "Found connected device: " + device.getAddress());
                Log.d("Tag", "Found connected device: " + device.getName());
                Log.d("Tag", "Found connected device: " + device.toString());
                Log.d("Tag", "Found connected device: " + device.getBluetoothClass());
                Log.d("Tag", "Found connected device: " + device.getType());
                Log.d("Tag", "Found connected device: " + device.getUuids());
                for (ParcelUuid i : device.getUuids()) {
                    System.out.println(i);
                    System.out.println(i.toString());
                    System.out.println(i.getUuid());
                }
            }
        }

        /*GoogleSignInAccount account = GoogleSignIn.getAccountForExtension(this, fitnessOptions);
        if (!GoogleSignIn.hasPermissions(account, fitnessOptions)) {
            GoogleSignIn.requestPermissions(
                    this, // your activity
                    GOOGLE_FIT_PERMISSIONS_REQUEST_CODE, // e.g. 1
                    account,
                    fitnessOptions);
        } else {
            accessGoogleFit();
        }
*/

        setContentView(R.layout.activity_log_in);
        Button loginbutton= (Button) findViewById(R.id.log_in_activity_log_in_button);
        Button singinbutton = (Button) findViewById(R.id.log_in_activity_sign_up_button);
        final EditText email = (EditText) findViewById(R.id.username);
        final EditText pass = (EditText) findViewById(R.id.password);



       /* if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACTIVITY_RECOGNITION)
                != PackageManager.PERMISSION_GRANTED) {
            // Permission is not granted
            System.out.println("NICSNMEGBAZDMEG");
        }
        else{
            System.out.println("MEGVANBAZDMEG");
        }
        OnDataPointListener mListener = new OnDataPointListener() {
            @Override
            public void onDataPoint(DataPoint dataPoint) {
                System.out.println("MENJEL BAZDMEG");
                for (Field field : dataPoint.getDataType().getFields()) {
                    Value val = dataPoint.getValue(field);
                    Log.d("ittvan az adat bazdmeg", "Detected DataPoint field: " + field.getName());
                    Log.d("ittvan az adat bazdmeg", "Detected DataPoint value: " + val);
                }
            }
        };




       /* Fitness.getSensorsClient(this, GoogleSignIn.getAccountForExtension(this, fitnessOptions))
                .add(
                        new SensorRequest.Builder()
                               // .setDataSource(dataSource) // Optional but recommended for custom data sets.
                                .setDataType(DataType.TYPE_HEART_RATE_BPM) // Can't be omitted.
                                .setSamplingRate(10, TimeUnit.SECONDS)
                                .build(),
                        mListener)
                .addOnCompleteListener(
                        new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {
                                if (task.isSuccessful()) {
                                    Log.i("MEGY BAZDMEG", "Listener registered!");
                                } else {
                                    Log.e("NEM MEGY BAZDMEG", "Listener not registered.", task.getException());
                                }
                            }
                        });*/
       singinbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LogInActivity.this, SignIn.class);
                startActivity(intent);
            }
        });
        loginbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final FirebaseAuth mAuth = FirebaseAuth.getInstance();
                mAuth.signInWithEmailAndPassword(email.getText().toString(),pass.getText().toString()).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()){
                                Toast.makeText(LogInActivity.this,"Login succesfull",Toast.LENGTH_LONG).show();
                                Intent intent = new Intent(LogInActivity.this, MainActivity.class);
                                startActivity(intent);
                            }
                            else{
                                Toast.makeText(LogInActivity.this,"Login unsuccesfull",Toast.LENGTH_LONG).show();
                            }
                    }
                });
            }
        });
    }



}