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
        // Utilisez la connexion TCP ici
        packet.setDepartureHostTime((long) (System.currentTimeMillis()+packet.getLink1().calculatePropagationDelay()+packet.getLink1().calculateTransmissionDelay(packet.getData())));
        router.receivePacket(packet);
    }

    public void receivePacket(Packet packet) {
        // Handle receiving packet at the host
        System.out.println(name + " received packet: " + packet.getData());
        packet.setReceiveHostTime(System.currentTimeMillis());
        //totalTransmissionDelay=L1+L2 to do
        //totalTransmissionDelay=0;
    }
}
