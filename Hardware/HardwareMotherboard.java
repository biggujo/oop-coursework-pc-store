package Hardware;

import java.io.Serializable;

public class HardwareMotherboard extends Hardware implements Serializable {

    private String socket;
    private int maxMemoryAmount;
    private String formFactor;

    public HardwareMotherboard(String partType) {
        super(partType);
    }

    public HardwareMotherboard(String partType, int price, String name, String manufacturer, String color, String socket,
                               int maxMemoryAmount, String formFactor) {
        super(partType, price, name, manufacturer, color);
        this.socket = socket;
        this.maxMemoryAmount = maxMemoryAmount;
        this.formFactor = formFactor;
    }

    public String getSocket() {
        return socket;
    }

    public void setSocket(String socket) {
        this.socket = socket;
    }

    public int getMaxMemoryAmount() {
        return maxMemoryAmount;
    }

    public void setMaxMemoryAmount(int maxMemoryAmount) {
        this.maxMemoryAmount = maxMemoryAmount;
    }

    public String getFormFactor() {
        return formFactor;
    }

    public void setFormFactor(String formFactor) {
        this.formFactor = formFactor;
    }
}
