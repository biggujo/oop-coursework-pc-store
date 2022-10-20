package Hardware;

import java.io.Serializable;

public class HardwareRAM extends Hardware implements Serializable {

    private int memory;
    private double throughput;

    public HardwareRAM(String partType) {
        super(partType);
    }

    public HardwareRAM(String partType, int price, String name, String manufacturer, String color,
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
