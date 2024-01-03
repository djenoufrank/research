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
        Router router = new Router("Router", 10);
        Link link1;
        Link link2;
       int choice=0;
        System.out.println("choose from 1 to 4");
        Scanner sc= new Scanner(System.in);
        choice = sc.nextInt();
switch (choice){
    case 1:
        link1 = new Link(10000, 199861330, 1000000); // 199861330: 2/3 vit de la lum en m/s
        link2 = new Link(10000, 199861330, 1000000);

        System.out.println("From Host A to Router");
        System.out.println("===========================");
        for (int i = 1; i <= 2; i++) {
            Packet packet = new Packet(i, hostA, hostB,link1,link2);
            hostA.sendPacket(packet, router);
            try {
                Thread.sleep((long)(packet.getLink1().calculatePropagationTime()+packet.getLink1().calculateTransmissionTime(packet.getData()))); // influence total dù au lien L1 entre l'hoteA et le routeur pour le packet i en millisec
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            // Affichage des résultats
            System.out.println(packet.getData() + ": "+packet.getSource().getName()+" departure:" + packet.getDepartureHostTime() + "; router arrival: "
                    + packet.getArrivalRouterTime() + " at posi: "+packet.getPosition()+"; is dropped : "+ packet.isDropped()
            +"\n");

        }
        break;
    case 2:
        link1 = new Link(10000, 199861330, 1000000); // 199861330: 2/3 vit de la lum en m/s
        // distance, vitesse de propagation et débit de transmission
        link2 = new Link(10000, 199861330, 300000);
        // Simulation de l'envoi de paquets
        //  ArrayList<Packet> packets = new ArrayList<>();
        System.out.println("From Host A to Router");
        System.out.println("===========================");
        for (int i = 1; i <= 15; i++) {
            Packet packet = new Packet(i, hostA, hostB,link1,link2);
            hostA.sendPacket(packet, router);
//            try {
//                Thread.sleep((long)(packet.getLink1().calculateTransmissionTime(packet.getData()))+10); // délai de transmission
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
            // Affichage des résultats
            System.out.println(packet.getData() + ": "+packet.getSource().getName()+" departure:" + packet.getDepartureHostTime() + "; router arrival: "
                    + packet.getArrivalRouterTime() + " at posi: "+packet.getPosition()+"; is dropped : "+ packet.isDropped()
            );

        }

        System.out.println("===========================");
        System.out.println("From Router to Host B");
        System.out.println("===========================");
        router.processQueue();  //send to host B
        System.out.println("Nombre total de paquets jetés : " + router.getEjectedPacket());
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