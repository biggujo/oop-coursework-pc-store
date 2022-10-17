package Parts;

public class PartPowerSupply extends Part {

    private int powerAmount;

    public PartPowerSupply(String partType) {
        super(partType);
    }

    public PartPowerSupply(String partType, int price, String name, String manufacturer, String color, int powerAmount) {
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
