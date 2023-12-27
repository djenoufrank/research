import objects.Host;
import objects.Link;
import objects.Packet;
import objects.Router;

import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        Host hostA = new Host("Host A");
        Host hostB = new Host("Host B");
        Router router = new Router("Router", 5);  // 10 paquets de capacité de file d'attente
        Link link1 = new Link(100, 20000000, 1000000); // Exemple de lien avec
        // distance, vitesse de propagation et débit de transmission
        Link link2 = new Link(60, 30000000, 100000);

        // Simulation de l'envoi de paquets
        ArrayList<Packet> packets = new ArrayList<>();
        for (int i = 1; i <= 10; i++) {
            Packet packet = new Packet(i, hostA, hostB,link1,link2);
            packets.add(packet);
            // envoyer le paquet dans la file d'attente du routeur
            hostA.sendPacket(packet, router);
//            try {
//                Thread.sleep(1000); // délai de transmission
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }

            // Affichage des résultats
            System.out.println("->Packet"+packet.getData() + ": "+packet.getSource().getName()+" departure:" + packet.getDepartureHostTime() + "; router arrival: "
                    + packet.getArrivalRouterTime() + " at posi: "+packet.getPosition()+"; is dropped : "+ packet.isDropped()
                    );
        }
        router.processQueue();  //send to host B
        System.out.println("Nombre total de paquets jetés : " + router.getEjectedPacket());
    }
}