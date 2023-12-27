package objects;

import java.util.LinkedList;
import java.util.Queue;

public class Router {
    private final String name;
    private  int queueCapacity;
    private  Queue<Packet> packetQueue;
    private long ejectedPacket;
    public Router(String name, int queueCapacity) {
        this.name = name;
        this.queueCapacity = queueCapacity;
        this.packetQueue = new LinkedList<>();
        this.ejectedPacket=0;
    }

    public String getName() {
        return name;
    }
    public int getQueueCapacity() {
        return queueCapacity;
    }
    public Queue<Packet> getPacketQueue() {
        return packetQueue;
    }
    public long getEjectedPacket() {
        return ejectedPacket;
    }
    public void receivePacket(Packet packet) {

        // recevoir un paquet du host et le mettre dans la file d'attente
        if (packetQueue.size() < queueCapacity) {
            packet.setArrivalRouterTime(System.currentTimeMillis());
            packetQueue.add(packet);
            packet.setPosition(packetQueue.size());
        } else {
            // Tail drop: Drop the packet if the queue is full
            //System.out.println("Packet dropped at router: " + packet);
            System.out.println("File d'attente du routeur pleine. Paquet jeté.");
            packet.setDropped(true);
            this.ejectedPacket+=1;
        }
    }

    // Simuler le traitement des paquets dans le routeur par exple: envoyer le paquet en tete a l'hote destinataire
    // si la liste n'est pas vide
    public void processQueue() {

        while (!packetQueue.isEmpty()) {

            Packet packet = packetQueue.poll();
            packet.setDepartureRouterTime((long) (System.currentTimeMillis()+packet.getLink2().calculatePropagationDelay()+packet.getLink2().calculateTransmissionDelay(packet.getData())));
            // Simuler le délai de transmission
//            try {
//                Thread.sleep(500);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
            packet.getDestination().receivePacket(packet);
            //System.out.println("Router processing packet: " + packet.getData());
            System.out.println("->Packet"+packet.getData() + ": "+name+" departure: "+ packet.getDepartureRouterTime() + "; "+packet.getDestination().getName()+" arrival: "
                            + packet.getReceiveHostTime());
        }
        //else System.out.println("The packet Queue is empty");
    }
}
