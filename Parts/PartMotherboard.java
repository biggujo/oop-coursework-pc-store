package Parts;

public class PartMotherboard extends Part {

    private String socket;
    private int connectorForSSD;
    private int maxMemoryAmount;
    private int maxMemoryUnits;
    private String formFactor;

    public PartMotherboard() {}

    public PartMotherboard(int price, String name, String manufacturer, String color, String socket,
                           int connectorForSSD, int maxMemoryAmount, int maxMemoryUnits, String formFactor) {
        super(price, name, manufacturer, color);
        this.socket = socket;
        this.connectorForSSD = connectorForSSD;
        this.maxMemoryAmount = maxMemoryAmount;
        this.maxMemoryUnits = maxMemoryUnits;
        this.formFactor = formFactor;
    }

    public String getSocket() {
        return socket;
    }

    public void setSocket(String socket) {
        this.socket = socket;
    }

    public int getConnectorForSSD() {
        return connectorForSSD;
    }

    public void setConnectorForSSD(int connectorForSSD) {
        this.connectorForSSD = connectorForSSD;
    }

    public int getMaxMemoryAmount() {
        return maxMemoryAmount;
    }

    public void setMaxMemoryAmount(int maxMemoryAmount) {
        this.maxMemoryAmount = maxMemoryAmount;
    }

    public int getMaxMemoryUnits() {
        return maxMemoryUnits;
    }

    public void setMaxMemoryUnits(int maxMemoryUnits) {
        this.maxMemoryUnits = maxMemoryUnits;
    }

    public String getFormFactor() {
        return formFactor;
    }

    public void setFormFactor(String formFactor) {
        this.formFactor = formFactor;
    }
}
