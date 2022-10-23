package Hardware;

import java.io.Serializable;
import java.util.StringJoiner;

public class HardwarePowerSupply extends Hardware implements Serializable {

    private int powerAmount;

    public HardwarePowerSupply() {
        super(TypesOfHardware.POWER_SUPPLY);
    }

    public HardwarePowerSupply(int price, String name, String manufacturer, String color, int powerAmount) {
        super(TypesOfHardware.POWER_SUPPLY, price, name, manufacturer, color);
        this.powerAmount = powerAmount;
    }

    public int getPowerAmount() {
        return powerAmount;
    }

    public void setPowerAmount(int powerAmount) {
        this.powerAmount = powerAmount;
    }

    public String toString() {

        StringJoiner stringJoiner = new StringJoiner(",", super.toString() + ",{", "}");

        stringJoiner.add(Integer.toString(powerAmount));

        return stringJoiner.toString();
    }
}
