package objects;

public class Link {
    private double distance;
    private double propagationSpeed;//vitesse de propagation
    private double transmissionRate;//débit de transmission

    public Link(double distance, double propagationSpeed, double transmissionRate) {
        this.distance = distance;
        this.propagationSpeed = propagationSpeed;
        this.transmissionRate = transmissionRate;
    }

    public double calculatePropagationDelay() {
        return distance / propagationSpeed;
    }

    public double calculateTransmissionDelay(int dataSize) {
        return dataSize / transmissionRate;
    }
}