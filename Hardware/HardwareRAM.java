package Hardware;

import java.io.Serializable;
import java.util.StringJoiner;

public class HardwareRAM extends Hardware implements Serializable {

    private int memory;
    private double throughput;

    public HardwareRAM() {
        super(TypesOfHardware.RAM);
    }

    public HardwareRAM(int price, String name, String manufacturer, String color,
                       int memory, double throughput) {
        super(TypesOfHardware.RAM, price, name, manufacturer, color);
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

    public String toString() {

        StringJoiner stringJoiner = new StringJoiner(",", super.toString() + ",{", "}");

        stringJoiner.add(Integer.toString(memory)).add(Double.toString(throughput));

        return stringJoiner.toString();
    }

}
