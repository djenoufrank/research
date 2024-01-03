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
        if (packetQueue.size() < queueCapacity) {
            packet.setArrivalRouterTime(System.currentTimeMillis());
            packetQueue.add(packet);
            packet.setPosition(packetQueue.size());
        } else {
            // Tail drop:
            System.out.println("File d'attente du routeur pleine. Paquet jeté.");
            packet.setDropped(true);
            this.ejectedPacket+=1;
        }
    }

    // Simuler le traitement des paquets dans le routeur par exple: envoyer le paquet en tete a l'hote destinataire
    // si la liste n'est pas vide
    public void processQueue(int choice) {
        while (!packetQueue.isEmpty()) {
            Packet packet = packetQueue.poll();
            packet.setDepartureRouterTime(System.currentTimeMillis());


            if (choice==1){
                packet.getDestination().receivePacket(packet);
                try {
                    Thread.sleep((long)((packet.getLink2().calculatePropagationTime()+packet.getLink2().calculateTransmissionTime(packet.getData()))*1000));
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            } if (choice==3) {
                if (packetQueue.size() < queueCapacity) {
                    packet.getDestination().receivePacket(packet);
                    try {
                        Thread.sleep((long)((packet.getLink2().calculatePropagationTime()+packet.getLink2().calculateTransmissionTime(packet.getData()))*1000));
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
            System.out.println(packet.getData() + ": "+name+" departure: "+ packet.getDepartureRouterTime() + "; "+packet.getDestination().getName()+" arrival: "
                    + packet.getReceiveHostTime());

        }

    }
}
