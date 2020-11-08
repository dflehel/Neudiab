package hu.obuda.university.neudiab.APIs;

import android.os.AsyncTask;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.SetOptions;

import java.io.IOException;
import java.net.URL;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import hu.obuda.university.neudiab.Utils.DATA_CONTANIER;
import hu.obuda.university.neudiab.Utils.DataValue;

public class DataAPIRequest extends AsyncTask<Void, Void, Void> {
    String result;
    final String strMessage = "https://neurodiabdataportalapi.azurewebsites.net/lastLog";
    @Override
    protected Void doInBackground(Void... voids) {
        URL url;
        try {
            url = new URL(strMessage);
            //BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(url.openStream()));
            //String stringBuffer;
            //String string = "";
            //while ((stringBuffer = bufferedReader.readLine()) != null){
              //  string = String.format("%s%s", string, stringBuffer);
            //}
            //bufferedReader.close();
            //result = string;
            result = "Siker";
        } catch (IOException e){
            e.printStackTrace();
            result = e.toString();
        }
        return null;
    }
    @Override
    protected void onPostExecute(Void aVoid) {
        //textMessage.setText(result);
        //textLoad.setText("Finished");
       // String time = result.toString().replace("{","").toString().replace("}","").replaceFirst(":","_").split(",")[0].split("_")[1];
       // String value = result.toString().replace("{","").toString().replace("}","").split(",")[1].split(":")[1];
       // System.out.println(time);
       // System.out.println(value);
        System.out.println(DATA_CONTANIER.getthelist().size());
        String time = new Date().toString();
        String value = (new Double(new Random().nextInt(300))).toString();
        DATA_CONTANIER.inserttolist(new DataValue(time,Double.parseDouble(value)));
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        FirebaseAuth mAuth = FirebaseAuth.getInstance();
        Map<String, Object> data = new HashMap<>();
        Map<String, Object> data2 = new HashMap<>();
        data2.put(time,data2);
        data.put(new Date().toString(),Double.parseDouble(value));
        System.out.println(new Date().toString());
        db.collection("adatok").document(mAuth.getUid()).set(data, SetOptions.merge());
        if (DATA_CONTANIER.getthelist().size()==15) {
            new DecisionAPIRequest().execute();
        }
        super.onPostExecute(aVoid);
    }
}