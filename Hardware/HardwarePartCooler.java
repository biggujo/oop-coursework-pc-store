package Hardware;

public class HardwarePartCooler extends HardwarePart {

    private int rpm;
    private int maxNoiseLevel;
    private boolean backlight;

    public HardwarePartCooler(String partType) {
        super(partType);
    }

    public HardwarePartCooler(String partType, int price, String name, String manufacturer, String color, int rpm,
                              int maxNoiseLevel, boolean backlight) {
        super(partType, price, name, manufacturer, color);
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
}
