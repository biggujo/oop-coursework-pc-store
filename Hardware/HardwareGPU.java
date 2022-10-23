package Hardware;

import java.io.Serializable;
import java.util.StringJoiner;

public class HardwareGPU extends Hardware implements Serializable {

    private int frequency;
    private int memory;
    private int powerConsumption;

    public HardwareGPU() {
        super(TypesOfHardware.GPU);
    }

    public HardwareGPU(int price, String name, String manufacturer, String color, int frequency,
                       int memory, int powerConsumption) {
        super(TypesOfHardware.GPU, price, name, manufacturer, color);
        this.frequency = frequency;
        this.memory = memory;
        this.powerConsumption = powerConsumption;
    }

    public int getFrequency() {
        return frequency;
    }

    public void setFrequency(int frequency) {
        this.frequency = frequency;
    }

    public int getMemory() {
        return memory;
    }

    public void setMemory(int memory) {
        this.memory = memory;
    }

    public int getPowerConsumption() {
        return powerConsumption;
    }

    public void setPowerConsumption(int powerConsumption) {
        this.powerConsumption = powerConsumption;
    }

    public String toString() {

        StringJoiner stringJoiner = new StringJoiner(",", super.toString() + ",{", "}");

        stringJoiner.add(Integer.toString(frequency)).add(Integer.toString(memory)).
                add(Integer.toString(powerConsumption));

        return stringJoiner.toString();
    }
}
