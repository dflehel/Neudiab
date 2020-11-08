package hu.obuda.university.neudiab.Utils;

public class DataValue {

    private String date;
    private double value;


    @Override
    public String toString() {
        return "DataValue{" +
                "date='" + date + '\'' +
                ", value=" + value +
                '}';
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }

    public DataValue(String date, double value) {
        this.date = date;
        this.value = value;
    }
}
