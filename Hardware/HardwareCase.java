package Hardware;

import java.io.Serializable;
import java.util.StringJoiner;

public class HardwareCase extends Hardware implements Serializable {

    private String formFactorCompatibility;
    private double weight;
    private boolean backlight;

    public HardwareCase() {
        super(TypesOfHardware.CASE);
    }

    public HardwareCase(int price, String name, String manufacturer, String color,
                        String formFactorCompatibility, double weight, boolean backlight) {
        super(TypesOfHardware.CASE, price, name, manufacturer, color);
        this.formFactorCompatibility = quotationIfComma(formFactorCompatibility);
        this.weight = weight;
        this.backlight = backlight;
    }

    public String getFormFactorCompatibility() {
        return formFactorCompatibility;
    }

    public void setFormFactor(String formFactorCompatibility) {
        this.formFactorCompatibility = formFactorCompatibility;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public boolean isBacklight() {
        return backlight;
    }

    public void setBacklight(boolean backlight) {
        this.backlight = backlight;
    }

    public String toString() {

        StringJoiner stringJoiner = new StringJoiner(",", super.toString() + ",{", "}");

        stringJoiner.add(formFactorCompatibility).add(Double.toString(weight)).add(Boolean.toString(backlight));

        return stringJoiner.toString();
    }
}
