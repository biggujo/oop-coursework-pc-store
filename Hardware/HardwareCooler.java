package Hardware;

import java.io.Serializable;
import java.util.StringJoiner;

public class HardwareCooler extends Hardware implements Serializable {

    private int rpm;
    private int maxNoiseLevel;
    private boolean backlight;

    public HardwareCooler() {
        super(TypesOfHardware.COOLER);
    }

    public HardwareCooler(int price, String name, String manufacturer, String color, int rpm,
                          int maxNoiseLevel, boolean backlight) {
        super(TypesOfHardware.COOLER, price, name, manufacturer, color);
        this.rpm = rpm;
        this.maxNoiseLevel = maxNoiseLevel;
        this.backlight = backlight;
    }

    public int getRpm() {
        return rpm;
    }

    public void setRpm(int rpm) {
        this.rpm = rpm;
    }

    public int getMaxNoiseLevel() {
        return maxNoiseLevel;
    }

    public void setMaxNoiseLevel(int maxNoiseLevel) {
        this.maxNoiseLevel = maxNoiseLevel;
    }

    public boolean isBacklight() {
        return backlight;
    }

    public void setBacklight(boolean backlight) {
        this.backlight = backlight;
    }

    public String toString() {

        StringJoiner stringJoiner = new StringJoiner(",", super.toString() + ",{", "}");

        stringJoiner.add(Integer.toString(rpm)).add(Integer.toString(maxNoiseLevel)).add(Boolean.toString(backlight));

        return stringJoiner.toString();
    }
}
