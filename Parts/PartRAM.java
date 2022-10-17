package Parts;

public class PartRAM extends Part {

    private int memory;
    private double throughput;

    public PartRAM(String partType) {
        super(partType);
    }

    public PartRAM(String partType, int price, String name, String manufacturer, String color,
                   int memory, double throughput) {
        super(partType, price, name, manufacturer, color);
        this.memory = memory;
        this.throughput = throughput;
    }

    public int getMemory() {
        return memory;
    }

    public void setMemory(int memory) {
        this.memory = memory;
    }

    public double getThroughput() {
        return throughput;
    }

    public void setThroughput(double throughput) {
        this.throughput = throughput;
    }

}
