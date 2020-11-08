package hu.obuda.university.neudiab.Utils;

import java.util.ArrayList;

public class CarvViewItem {

    private String date;
    private ArrayList<Dieabetdata> dieabetdata = new ArrayList<>();
    private ArrayList<DecisionData> decisionData = new ArrayList<>();


    public CarvViewItem(String date, ArrayList<Dieabetdata> dieabetdata, ArrayList<DecisionData> decisionData) {
        this.date = date;
        this.dieabetdata = dieabetdata;
        this.decisionData = decisionData;
    }


    public CarvViewItem() {
    }
    public void addglukosvalue(int hova,double d){
        this.dieabetdata.add(new Dieabetdata(hova,(float)d));
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public ArrayList<Dieabetdata> getDieabetdata() {
        return dieabetdata;
    }

    public void setDieabetdata(ArrayList<Dieabetdata> dieabetdata) {
        this.dieabetdata = dieabetdata;
    }

    public ArrayList<DecisionData> getDecisionData() {
        return decisionData;
    }

    public void setDecisionData(ArrayList<DecisionData> decisionData) {
        this.decisionData = decisionData;
    }

    @Override
    public String toString() {
        return "CarvViewItem{" +
                "date='" + date + '\'' +
                ", dieabetdata=" + dieabetdata +
                ", decisionData=" + decisionData +
                '}';
    }
}
