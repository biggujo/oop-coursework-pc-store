package Parts;

public class PartRAM extends Part {

    private int memory;
    private int throughput;
    private int units;

    public PartRAM() {}

    public PartRAM(int price, String name, String manufacturer, String color, int memory, int throughput, int units) {
        super(price, name, manufacturer, color);
        this.memory = memory;
        this.throughput = throughput;
        this.units = units;
    }

    public int getMemory() {
        return memory;
    }

    public void setMemory(int memory) {
        this.memory = memory;
    }

    public int getThroughput() {
        return throughput;
    }

    public void setThroughput(int throughput) {
        this.throughput = throughput;
    }

    public int getUnits() {
        return units;
    }

    public void setUnits(int units) {
        this.units = units;
    }
}
