package Parts;

public class PartSSD extends Part {

    private String connector;
    private int memory;

    public PartSSD(String partType) {
        super(partType);
    }

    public PartSSD(String partType, int price, String name, String manufacturer, String color, String connector, int memory) {
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
