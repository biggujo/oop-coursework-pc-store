package Hardware;

public class HardwarePartRAM extends HardwarePart {

    private int memory;
    private double throughput;

    public HardwarePartRAM(String partType) {
        super(partType);
    }

    public HardwarePartRAM(String partType, int price, String name, String manufacturer, String color,
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
