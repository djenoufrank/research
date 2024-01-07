package objects;

public class Host {
    private final String name;

    public Host(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void sendPacket(Packet packet, Router router) {
        packet.setDepartureHostTime(System.currentTimeMillis()+(long) ((packet.getLink1().calculateTransmissionTime(packet.getData())) * 1000000));
        router.receivePacket(packet);
    }

    public void receivePacket(Packet packet) {
        try {
            Thread.sleep((long) ((long)((packet.getLink2().calculatePropagationTime() + packet.getLink2().calculateTransmissionTime(packet.getData())) * 1000000))); 
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        packet.setReceiveHostTime(System.currentTimeMillis());
    }

    @Override
    public String toString() {
        return name;
    }
}
