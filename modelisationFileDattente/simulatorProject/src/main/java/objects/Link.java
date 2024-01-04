package objects;

public class Link {
    private double distance;
    private double propagationSpeed;//vitesse de propagation
    private double transmissionSpeed;//débit de transmission

    public Link(double distance, double propagationSpeed, double transmissionSpeed) {
        this.distance = distance;
        this.propagationSpeed = propagationSpeed;
        this.transmissionSpeed = transmissionSpeed;
    }

    public double getTransmissionSpeed() {
        return transmissionSpeed;
    }

    public double calculatePropagationTime() {
        return distance / propagationSpeed;
    }

    public double calculateTransmissionTime(int dataSize) {
        return dataSize / transmissionSpeed;
    }
}