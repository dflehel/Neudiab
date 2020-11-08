package hu.obuda.university.neudiab.Utils;

public class DecisionData {

    private int decissionPoint;
    private float decissionValueProbability;
    private float decissionValue;


    public DecisionData(int decissionPoint, float decissionValueProbability, float decissionValue) {
        this.decissionPoint = decissionPoint;
        this.decissionValueProbability = decissionValueProbability;
        this.decissionValue = decissionValue;
    }


    public DecisionData() {
    }


    public int getDecissionPoint() {
        return decissionPoint;
    }

    public void setDecissionPoint(int decissionPoint) {
        this.decissionPoint = decissionPoint;
    }

    public float getDecissionValueProbability() {
        return decissionValueProbability;
    }

    public void setDecissionValueProbability(float decissionValueProbability) {
        this.decissionValueProbability = decissionValueProbability;
    }

    public float getDecissionValue() {
        return decissionValue;
    }

    public void setDecissionValue(float decissionValue) {
        this.decissionValue = decissionValue;
    }


    @Override
    public String toString() {
        return "DecisionData{" +
                "decissionPoint=" + decissionPoint +
                ", decissionValueProbability=" + decissionValueProbability +
                ", decissionValue=" + decissionValue +
                '}';
    }
}
