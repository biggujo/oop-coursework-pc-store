package Hardware;

import java.io.Serializable;

public class HardwarePowerSupply extends Hardware implements Serializable {

    private int powerAmount;

    public HardwarePowerSupply(String partType) {
        super(partType);
    }

    public HardwarePowerSupply(String partType, int price, String name, String manufacturer, String color, int powerAmount) {
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
