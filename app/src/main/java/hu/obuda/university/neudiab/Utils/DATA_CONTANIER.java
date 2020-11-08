package hu.obuda.university.neudiab.Utils;

import java.util.LinkedList;

public class  DATA_CONTANIER {

    private static LinkedList<DataValue> dataValueList = new LinkedList<DataValue>();
    private static MainActivityRecViewAdapter rec;
    private static LinkedList<Integer> hrlist = new LinkedList<>();

    public static void setrecview(MainActivityRecViewAdapter recview){
        rec = recview;
    }

    public static void inserttohrlist(int hr){
        hrlist.add(hr);
    }

    public static void cleanhrlist(){
        hrlist = new LinkedList<>();
    }

    public static void inserttolist(DataValue data){
        if (dataValueList.size()==15){
            dataValueList.removeFirst();
            dataValueList.add(data);

        }
        else{
        dataValueList.add(data);
        }
        cleanhrlist();
        rec.updateglukoz(data.getValue());
    }

    public static LinkedList<DataValue> getthelist(){
        return dataValueList;
    }

}
