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
        packet.setDepartureHostTime((System.currentTimeMillis()));
        router.receivePacket(packet);
    }

    public void receivePacket(Packet packet) {
        packet.setReceiveHostTime(System.currentTimeMillis());
    }
}
