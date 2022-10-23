package Hardware;

import java.io.Serializable;
import java.util.StringJoiner;

public class HardwareCPU extends Hardware implements Serializable {

    private String socket;
    private int generation;
    private int cores;
    private double maxFrequency;

    public HardwareCPU() {
        super(TypesOfHardware.CPU);
    }

    public HardwareCPU(int price, String name, String manufacturer, String color, String socket,
                       int generation, int cores, double maxFrequency) {
        super(TypesOfHardware.CPU, price, name, manufacturer, color);

        this.socket = quotationIfComma(socket);
        this.generation = generation;
        this.cores = cores;
        this.maxFrequency = maxFrequency;
    }

    public String getSocket() {
        return socket;
    }

    public void setSocket(String socket) {
        this.socket = socket;
    }

    public int getGeneration() {
        return generation;
    }

    public void setGeneration(int generation) {
        this.generation = generation;
    }

    public int getCores() {
        return cores;
    }

    public void setCores(int cores) {
        this.cores = cores;
    }

    public double getMaxFrequency() {
        return maxFrequency;
    }

    public void setMaxFrequency(double maxFrequency) {
        this.maxFrequency = maxFrequency;
    }

    public String toString() {

        StringJoiner stringJoiner = new StringJoiner(",", super.toString() + ",{", "}");

        stringJoiner.add(socket).add(Integer.toString(generation)).add(Integer.toString(cores)).
                add(Double.toString(maxFrequency));

        return stringJoiner.toString();
    }
}
