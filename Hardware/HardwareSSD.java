package Hardware;

import java.io.Serializable;
import java.util.StringJoiner;

public class HardwareSSD extends Hardware implements Serializable {

    private String connector;
    private int memory;

    public HardwareSSD() {
        super(TypesOfHardware.SSD);
    }

    public HardwareSSD(int price, String name, String manufacturer, String color, String connector, int memory) {
        super(TypesOfHardware.SSD, price, name, manufacturer, color);
        this.connector = quotationIfComma(connector);
        this.memory = memory;
    }

    public String getConnector() {
        return connector;
    }

    public void setConnector(String connector) {
        this.connector = connector;
    }

    public int getMemory() {
        return memory;
    }

    public void setMemory(int memory) {
        this.memory = memory;
    }

    public String toString() {

        StringJoiner stringJoiner = new StringJoiner(",", super.toString() + ",{", "}");

        stringJoiner.add(connector).add(Integer.toString(memory));

        return stringJoiner.toString();
    }

}
