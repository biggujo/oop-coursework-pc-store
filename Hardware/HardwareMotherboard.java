package Hardware;

import java.io.Serializable;
import java.util.StringJoiner;

public class HardwareMotherboard extends Hardware implements Serializable {

    private String socket;
    private int maxMemoryAmount;
    private String formFactor;

    public HardwareMotherboard() {
        super(TypesOfHardware.MOTHERBOARD);
    }

    public HardwareMotherboard(int price, String name, String manufacturer, String color, String socket,
                               int maxMemoryAmount, String formFactor) {
        super(TypesOfHardware.MOTHERBOARD, price, name, manufacturer, color);
        this.socket = quotationIfComma(socket);
        this.maxMemoryAmount = maxMemoryAmount;
        this.formFactor = quotationIfComma(formFactor);
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

    public String toString() {

        StringJoiner stringJoiner = new StringJoiner(",", super.toString() + ",{", "}");

        stringJoiner.add(socket).add(Integer.toString(maxMemoryAmount)).add(formFactor);

        return stringJoiner.toString();
    }
}
