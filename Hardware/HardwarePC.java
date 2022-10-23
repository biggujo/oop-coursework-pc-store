package Hardware;

import java.io.Serializable;
import java.util.StringJoiner;

// TODO: Make extends Hardware impl. Serializable
public class HardwarePC extends Hardware implements Serializable {

    private String motherboard;
    private String cpu;
    private String gpu;
    private String ram;
    private String ssd;
    private String cooler;
    private String powerSupply;
    private boolean backlight;
    private String formFactor;

    public HardwarePC() {
        super(TypesOfHardware.PC);
    }

    public HardwarePC(int price, String name, String manufacturer, String color, String motherboard,
                      String cpu, String gpu, String ram, String ssd, String cooler, String powerSupply,
                      boolean backlight, String formFactor) {
        super(TypesOfHardware.PC, price, name, manufacturer, color);
        this.cpu = cpu;
        this.gpu = gpu;
        this.ram = ram;
        this.ssd = ssd;
        this.cooler = cooler;
        this.motherboard = motherboard;
        this.powerSupply = powerSupply;
        this.backlight = backlight;
        this.formFactor = formFactor;
    }

    public String getCpu() {
        return cpu;
    }

    public void setCpu(String cpu) {
        this.cpu = cpu;
    }

    public String getGpu() {
        return gpu;
    }

    public void setGpu(String gpu) {
        this.gpu = gpu;
    }

    public String getRam() {
        return ram;
    }

    public void setRam(String ram) {
        this.ram = ram;
    }

    public String getSsd() {
        return ssd;
    }

    public void setSsd(String ssd) {
        this.ssd = ssd;
    }

    public String getCooler() {
        return cooler;
    }

    public void setCooler(String cooler) {
        this.cooler = cooler;
    }

    public String getMotherboard() {
        return motherboard;
    }

    public void setMotherboard(String motherboard) {
        this.motherboard = motherboard;
    }

    public String getPowerSupply() {
        return powerSupply;
    }

    public void setPowerSupply(String powerSupply) {
        this.powerSupply = powerSupply;
    }

    public boolean isBacklight() {
        return backlight;
    }

    public void setBacklight(boolean backlight) {
        this.backlight = backlight;
    }

    public String getFormFactor() {
        return formFactor;
    }

    public void setFormFactor(String formFactor) {
        this.formFactor = formFactor;
    }

    public String toString() {

        StringJoiner stringJoiner = new StringJoiner(",", super.toString() + ",{", "}");

        stringJoiner.add(motherboard).add(cpu).add(gpu).add(ram).add(ssd).add(cooler).add(powerSupply).
                add(Boolean.toString(backlight)).add(formFactor);

        return stringJoiner.toString();
    }
}
