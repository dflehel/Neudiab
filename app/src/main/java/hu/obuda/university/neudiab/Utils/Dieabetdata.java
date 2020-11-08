package hu.obuda.university.neudiab.Utils;

public class Dieabetdata {

    private int measuredPoint;
    private float measuredValue;

    public Dieabetdata(int measuredPoint, float measuredValue) {
        this.measuredPoint = measuredPoint;
        this.measuredValue = measuredValue;
    }

    public Dieabetdata() {
    }

    public int getMeasuredPoint() {
        return measuredPoint;
    }

    public void setMeasuredPoint(int measuredPoint) {
        this.measuredPoint = measuredPoint;
    }

    public float getMeasuredValue() {
        return measuredValue;
    }

    public void setMeasuredValue(float measuredValue) {
        this.measuredValue = measuredValue;
    }


    @Override
    public String toString() {
        return "Dieabetdata{" +
                "measuredPoint=" + measuredPoint +
                ", measuredValue=" + measuredValue +
                '}';
    }
}
