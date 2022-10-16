package Parts;

public class PartCPU extends Part {

    private String socket;
    private int generation;
    private int cores;

    public PartCPU() {}

    public PartCPU(int price, String name, String manufacturer, String color, String socket,
                   int generation, int cores, double maxFrequency) {
        super(price, name, manufacturer, color);

        this.socket = socket;
        this.generation = generation;
        this.cores = cores;
        this.maxFrequency = maxFrequency;
    }

    double maxFrequency;

    public String getSocket() {
        return socket;
    }

    public void setSocket(String socket) {
        this.socket = socket;
    }

    public int getGeneration() {
        return generation;
    }

    public void setGeneration(int generation) {
        this.generation = generation;
    }

    public int getCores() {
        return cores;
    }

    public void setCores(int cores) {
        this.cores = cores;
    }

    public double getMaxFrequency() {
        return maxFrequency;
    }

    public void setMaxFrequency(double maxFrequency) {
        this.maxFrequency = maxFrequency;
    }
}