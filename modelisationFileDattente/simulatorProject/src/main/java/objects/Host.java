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
        packet.setDepartureHostTime((long) (System.currentTimeMillis()));
        // Simuler le délai de transmission
        try {
            Thread.sleep((long)(packet.getLink1().calculatePropagationTime()+packet.getLink1().calculateTransmissionTime(packet.getData())+1));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
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
