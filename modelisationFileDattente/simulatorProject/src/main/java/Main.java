import objects.Host;
import objects.Link;
import objects.Packet;
import objects.Router;

import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Host hostA = new Host("Host A");
        Host hostB = new Host("Host B");
        Router router = new Router("Router", 12);  // 10 paquets de capacité de file d'attente
        Link link1 = new Link(100000, 2000000, 1000); // Exemple de lien avec
        // distance, vitesse de propagation et débit de transmission
        Link link2 = new Link(60000, 30000000, 10000);
       int choice=0;
        System.out.println("choose from 1 to 4");
        Scanner sc= new Scanner(System.in);
        choice = sc.nextInt();
switch (choice){
    case 1:
        // Simulation de l'envoi de paquets
      //  ArrayList<Packet> packets = new ArrayList<>();
        System.out.println("From Host A to Router");
        System.out.println("===========================");
        for (int i = 1; i <= 20; i++) {
            Packet packet = new Packet(i, hostA, hostB,link1,link2);
          //  packets.add(packet);
            try {
                Thread.sleep((long)(packet.getLink1().calculateTransmissionTime(packet.getData()))+1); // délai de transmission
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            // envoyer le paquet dans la file d'attente du routeur
            hostA.sendPacket(packet, router);

            // Affichage des résultats
            System.out.println("->Packet"+packet.getData() + ": "+packet.getSource().getName()+" departure:" + packet.getDepartureHostTime() + "; router arrival: "
                    + packet.getArrivalRouterTime() + " at posi: "+packet.getPosition()+"; is dropped : "+ packet.isDropped()
            );
            System.out.println("Nombre total de paquets jetés : " + router.getEjectedPacket());
        }
        System.out.println("===========================");
        System.out.println("From Router to Host B");
        System.out.println("===========================");
        router.processQueue();  //send to host B

        break;
    case 2:
        break;
    case 3:
        break;
    case 4:
        break;
    case 5:
        break;
}

    }
}