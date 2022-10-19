package Hardware;

import java.io.Serializable;

public class HardwarePartCase extends HardwarePart implements Serializable {

    private String formFactorCompatibility;
    private double weight;
    private boolean backlight;

    public HardwarePartCase(String partType) {
        super(partType);
    }

    public HardwarePartCase(String partType, int price, String name, String manufacturer, String color,
                            String formFactorCompatibility, double weight, boolean backlight) {
        super(partType, price, name, manufacturer, color);
        this.formFactorCompatibility = formFactorCompatibility;
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

        return super.toString() + ',' + '{' + formFactorCompatibility + ',' + weight + ',' + backlight + '}';
    }
}
