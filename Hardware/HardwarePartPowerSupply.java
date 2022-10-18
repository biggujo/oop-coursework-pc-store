package Hardware;

public class HardwarePartPowerSupply extends HardwarePart {

    private int powerAmount;

    public HardwarePartPowerSupply(String partType) {
        super(partType);
    }

    public HardwarePartPowerSupply(String partType, int price, String name, String manufacturer, String color, int powerAmount) {
        super(partType, price, name, manufacturer, color);
        this.powerAmount = powerAmount;
    }

    public int getPowerAmount() {
        return powerAmount;
    }

    public void setPowerAmount(int powerAmount) {
        this.powerAmount = powerAmount;
    }
}
