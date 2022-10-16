package Parts;

public class BuiltComputer {

    private PartCase computerPartCase;
    private PartCooler cooler;
    private PartCPU cpu;
    private PartGPU gpu;
    private PartMotherboard motherboard;
    private PartPowerSupply powerSupply;
    private PartRAM ram;
    private PartSSD ssd;

    public PartCase getComputerCase() {
        return computerPartCase;
    }

    public void setComputerCase(PartCase computerPartCase) {
        this.computerPartCase = computerPartCase;
    }

    public PartCooler getCooler() {
        return cooler;
    }

    public void setCooler(PartCooler partCooler) {
        this.cooler = partCooler;
    }

    public PartCPU getCpu() {
        return cpu;
    }

    public void setCpu(PartCPU partCpu) {
        this.cpu = partCpu;
    }

    public PartGPU getGpu() {
        return gpu;
    }

    public void setGpu(PartGPU partGpu) {
        this.gpu = partGpu;
    }

    public PartMotherboard getMotherboard() {
        return motherboard;
    }

    public void setMotherboard(PartMotherboard partMotherboard) {
        this.motherboard = partMotherboard;
    }

    public PartPowerSupply getPowerSupply() {
        return powerSupply;
    }

    public void setPowerSupply(PartPowerSupply partPowerSupply) {
        this.powerSupply = partPowerSupply;
    }

    public PartRAM getRam() {
        return ram;
    }

    public void setRam(PartRAM partRam) {
        this.ram = partRam;
    }

    public PartSSD getSsd() {
        return ssd;
    }

    public void setSsd(PartSSD partSsd) {
        this.ssd = partSsd;
    }
}
