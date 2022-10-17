package Parts;

/**
 * Basic class for computer part unit
 */
public class Part {

    private String partType;
    private int price;
    private String name;
    private String manufacturer;
    private String color;

    public Part(String partType) {

        this.partType = partType;
    }

    public Part(String partType, int price, String name, String manufacturer, String color) {
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

    public String getPartType() {
        return partType;
    }
}
