package Parts;

import java.util.ArrayList;

public class PartCooler extends Part {

    private ArrayList<String> socketArrayList;
    private int rpm;
    private int maxNoiseLevel;
    private boolean backlight;

    public PartCooler() {}

    public PartCooler(int price, String name, String manufacturer, String color, ArrayList<String> socketArrayList, int rpm,
                      int maxNoiseLevel, boolean backlight) {
        super(price, name, manufacturer, color);
        this.socketArrayList = socketArrayList;
        this.rpm = rpm;
        this.maxNoiseLevel = maxNoiseLevel;
        this.backlight = backlight;
    }

    public ArrayList<String> getSocketArrayList() {
        return socketArrayList;
    }

    public void setSocketArrayList(ArrayList<String> socketArrayList) {
        this.socketArrayList = socketArrayList;
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
