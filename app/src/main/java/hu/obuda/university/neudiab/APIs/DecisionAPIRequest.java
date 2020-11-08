package hu.obuda.university.neudiab.APIs;

import android.os.AsyncTask;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.LinkedList;

import hu.obuda.university.neudiab.Utils.DATA_CONTANIER;
import hu.obuda.university.neudiab.Utils.DataValue;

public class DecisionAPIRequest extends AsyncTask<Void, Void, Void> {
    String result;
    String features;
    String strMessage = "https://us-central1-diabet-80a03.cloudfunctions.net/addMessage?text=1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23,24,25,26,27,28,29,30,31,32,33,34,35,36,37,38,39,40,41,42,43,44,45,46,47,48,49,50";
    @Override
    protected Void doInBackground(Void... voids) {
        URL url;
        try {
            strMessage = "https://us-central1-diabet-80a03.cloudfunctions.net/addMessage?text="+getFeatures();
            System.out.println("url proba");
            System.out.println(strMessage.toString());
            url = new URL(strMessage);
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(url.openStream()));
            String stringBuffer;
            String string = "";
            while ((stringBuffer = bufferedReader.readLine()) != null){
                string = String.format("%s%s", string, stringBuffer);
            }
            bufferedReader.close();
            result = string;
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
        System.out.println(result);
        //System.out.println(result.toString().split(",")[1].split("]")[0]);
        super.onPostExecute(aVoid);
    }

    private String getFeatures(){
        LinkedList<DataValue> list = DATA_CONTANIER.getthelist();
        ArrayList<Double> featurs = new ArrayList<>();
        StringBuilder stringBuilder = new StringBuilder();
        featurs.add(list.get(0).getValue() - list.get(list.size()-1).getValue());
        featurs.add((list.get(0).getValue() - list.get(list.size()-1).getValue())/list.size()-1);
        for (int i = 0;i<list.size()-1;++i){
            featurs.add(list.get(i).getValue() - list.get(i+1).getValue());
        }
        for (int i = 0;i<list.size()-1;++i){
            featurs.add((list.get(i).getValue() - list.get(i+1).getValue())/5);
        }
        for (int i = 0;i<list.size()-2;++i){
            featurs.add((((list.get(i).getValue() - list.get(i+1).getValue())/5)-((list.get(i+1).getValue() - list.get(i+2).getValue())/5))/10);
        }
        for(int i=0;i<3;++i){
            for (int j=0;j<5;++j){
                featurs.add(list.get(i*j).getValue() - list.get((i*j)+1).getValue());
            }
        }
        for(int i=0;i<3;++i){
            for (int j=0;j<5;++j){
                featurs.add((list.get(i*j).getValue() - list.get((i*j)+1).getValue())/5);
            }
        }

        featurs.add(Double.parseDouble(list.get(list.size()-1).getDate().split(":")[0].split(" ")[3]));
        featurs.add(Double.parseDouble(list.get(list.size()-1).getDate().split(":")[1]));

        for(Double d : featurs){
            stringBuilder.append(d.toString()+",");
        }
        return stringBuilder.toString();
        //Double entoenddis= list.get(0).getValue() - list.get(list.size()-1).getValue();
    }
}