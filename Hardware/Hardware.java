package Hardware;

import java.io.Serializable;
import java.util.StringJoiner;

/**
 * Basic class for computer part unit
 */
public class Hardware implements Serializable {

    private TypesOfHardware type;
    private int price;
    private String name;
    private String manufacturer;
    private String color;

    private int saleYear;
    private int saleMonth;

    public Hardware(TypesOfHardware type) {

        this.type = type;
    }

    public Hardware(TypesOfHardware type, int price, String name, String manufacturer, String color) {
        this.type = type;
        this.price = price;
        this.name = quotationIfComma(name);
        this.manufacturer = quotationIfComma(manufacturer);
        this.color = quotationIfComma(color);
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public int getSaleYear() {
        return saleYear;
    }

    public void setSaleYear(int saleYear) {
        this.saleYear = saleYear;
    }

    public int getSaleMonth() {
        return saleMonth;
    }

    public void setSaleMonth(int saleMonth) {
        this.saleMonth = saleMonth;
    }

    public TypesOfHardware getType() {
        return type;
    }

    public String toString() {

        StringJoiner stringJoiner = new StringJoiner(",");


        if (saleMonth != 0 && saleYear != 0) {

            String soldDate = String.format("SOLD:%02d.%4d", saleMonth, saleYear);

            stringJoiner.add(type.toString()).add(soldDate).add(name).
                    add(manufacturer).add(Integer.toString(price)).add(color);
        }
        else {
            stringJoiner.add(type.toString()).add(name).add(manufacturer).add(Integer.toString(price)).add(color);
        }

        return stringJoiner.toString();
    }

    // Quote a string if contains commas
    public static String quotationIfComma(String string) {

        return string.contains(",") ? '"' + string + '"' : string;
    }
}
