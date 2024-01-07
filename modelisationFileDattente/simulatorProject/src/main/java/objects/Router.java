package objects;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class Router {
    private final String name;
    private int queueCapacity;
    private Queue<Packet> packetQueue;
    private long ejectedPacket;

    public Router(String name, int queueCapacity) {
        this.name = name;
        this.queueCapacity = queueCapacity;
        this.packetQueue = new LinkedList<>();
        this.ejectedPacket = 0;
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
            try {
                Thread.sleep((long) (     (long) ((packet.getLink1().calculatePropagationTime()
                            + packet.getLink1().calculateTransmissionTime(packet.getData())) * 1000000))); 
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
       
            packet.setArrivalRouterTime(System.currentTimeMillis());
            packetQueue.add(packet);
            packet.setPosition(packetQueue.size());
        } else {
            // Tail drop:
            packet.setDropped(true);
            this.ejectedPacket += 1;
        }
    }

    //  traitement des paquets dans le routeur pour aller a l'hote B
    public List<Packet> processQueue(int choice) {
        List<Packet> myList = new ArrayList<>();
        while (!packetQueue.isEmpty()) {
            Packet packet = packetQueue.poll();
            packet.setDepartureRouterTime(System.currentTimeMillis()+(long) ((packet.getLink2().calculateTransmissionTime(packet.getData())) * 1000000));
            if (choice == 3 || choice == 4) {
                if (packetQueue.size() < queueCapacity) {
                    packet.getDestination().receivePacket(packet);
                }
            } else
                packet.getDestination().receivePacket(packet);
            myList.add(packet);
        }
        return myList;
    }

    @Override
    public String toString() {
        return name;
    }
}
