package Hardware;

import java.io.Serializable;

public class HardwareSSD extends Hardware implements Serializable {

    private String connector;
    private int memory;

    public HardwareSSD(String partType) {
        super(partType);
    }

    public HardwareSSD(String partType, int price, String name, String manufacturer, String color, String connector, int memory) {
        super(partType, price, name, manufacturer, color);
        this.connector = connector;
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

}
