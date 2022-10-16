package Parts;

public class PartPowerSupply extends Part {

    private int powerAmount;

    public PartPowerSupply() {}

    public PartPowerSupply(int price, String name, String manufacturer, String color, int powerAmount) {
        super(price, name, manufacturer, color);
        this.powerAmount = powerAmount;
    }

    public int getPowerAmount() {
        return powerAmount;
    }

    public void setPowerAmount(int powerAmount) {
        this.powerAmount = powerAmount;
    }
}
