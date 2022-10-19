package Hardware;

import java.io.Serializable;

public class HardwarePartGPU extends HardwarePart implements Serializable {

    private int frequency;
    private int memory;
    private int powerConsumption;

    public HardwarePartGPU(String partType) {
        super(partType);
    }

    public HardwarePartGPU(String partType, int price, String name, String manufacturer, String color, int frequency,
                           int memory, int powerConsumption) {
        super(partType, price, name, manufacturer, color);
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
}
