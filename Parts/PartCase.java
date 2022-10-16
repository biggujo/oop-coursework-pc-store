package Parts;

public class PartCase extends Part {

    private String formFactorCompatibility;
    private double weight;
    private boolean backlight;

    public PartCase() {}

    public PartCase(int price, String name, String manufacturer, String color, String formFactorCompatibility,
                    double weight, boolean backlight) {
        super(price, name, manufacturer, color);
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
}
