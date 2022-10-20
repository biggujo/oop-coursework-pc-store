package Hardware;

import java.io.Serializable;
import java.time.LocalDate;

/**
 * Basic class for computer part unit
 */
public class Hardware implements Serializable {

    private String partType;
    private int price;
    private String name;
    private String manufacturer;
    private String color;
    private LocalDate saleDate;

    public Hardware(String partType) {

        this.partType = partType;
    }

    public Hardware(String partType, int price, String name, String manufacturer, String color) {
        this.partType = partType;
        this.price = price;
        this.name = name;
        this.manufacturer = manufacturer;
        this.color = color;
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

    public LocalDate getSaleDate() {
        return saleDate;
    }

    public void setSaleDate(LocalDate saleDate) {
        this.saleDate = saleDate;
    }

    public String getPartType() {
        return partType;
    }

    public String toString() {

        return partType + ',' + name + ',' + manufacturer + ',' + price + ',' + color;
    }
}
