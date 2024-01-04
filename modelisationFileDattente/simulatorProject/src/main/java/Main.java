import objects.Host;
import objects.Link;
import objects.Packet;
import objects.Router;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Host hostA = new Host("Host A");
        Host hostB = new Host("Host B");
        Router router = new Router("Router", 10);
        int choice = 0;
        System.out.println("choose from 1 to 5");
        Scanner sc = new Scanner(System.in);
        choice = sc.nextInt();
        switch (choice) {
            case 1:
                illustrationGoulotEtranglement(hostA, hostB, router);
                break;
            case 2:
                saturationL2AvecGoulot(hostA, hostB, router);
                break;
            case 3:
                saturationL2SansCongestion(hostA, hostB, router);
                break;
            case 4:
                rafalesDePaquets(hostA, hostB, router);
                break;
            case 5:
                break;
            default:
                System.out.println("erreur de saisie. recompilez svp");
        }

    }

    private static void illustrationGoulotEtranglement(Host hostA, Host hostB, Router router) {
        Link link2;
        Link link1;
        link1 = new Link(1000, 199861330, 500000); // 199861330: 2/3 vit de la lum en m/s
        link2 = new Link(1000, 199861330, 10000000);

        System.out.println("De l'hote A au Routeur");
        System.out.println("===========================");
        for (int i = 1; i <= 2; i++) {
            Packet packet = new Packet(i, hostA, hostB, link1, link2);
            hostA.sendPacket(packet, router);
            try {
                Thread.sleep((long) ((packet.getLink1().calculatePropagationTime() + packet.getLink1().calculateTransmissionTime(packet.getData())) * 1000000)); // influence total dù au lien L1 entre l'hoteA et le routeur pour le packet i en millisec
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            // Affichage des résultats
            System.out.println(packet.getData() + ": " + packet.getSource().getName() + " departure:" + packet.getDepartureHostTime() + "; router arrival: "
                    + packet.getArrivalRouterTime() + " at posi: " + packet.getPosition() + "; is dropped : " + packet.isDropped()
                    + "\n");
        }
        System.out.println("===========================");
        System.out.println("Du routeur vers l'hote B");
        System.out.println("===========================");
        router.processQueue(1);  //send to host B
    }

    private static void saturationL2AvecGoulot(Host hostA, Host hostB, Router router) {
        Link link1;
        Link link2;
        link1 = new Link(1000, 199861330, 500000); // 199861330: 2/3 vit de la lum en m/s
        // distance, vitesse de propagation et débit de transmission
        link2 = new Link(1000, 199861330, 250000);

        System.out.println("De l'hote A au Routeur");
        System.out.println("===========================");
        for (int i = 1; i <= 15; i++) {
            Packet packet = new Packet(i, hostA, hostB, link1, link2);
            hostA.sendPacket(packet, router);
            // Affichage des résultats
            System.out.println(packet.getData() + ": " + packet.getSource().getName() + " departure:" + packet.getDepartureHostTime() + "; router arrival: "
                    + packet.getArrivalRouterTime() + " at posi: " + packet.getPosition() + "; is dropped : " + packet.isDropped()
            );

        }
        System.out.println("===========================");
        System.out.println("Du routeur vers l'hote B");
        System.out.println("===========================");
        router.processQueue(2);  //send to host B
        System.out.println("\nNombre total de paquets jetés: " + router.getEjectedPacket());
    }

    private static void saturationL2SansCongestion(Host hostA, Host hostB, Router router) {
        Link link2;
        Link link1;
        link1 = new Link(1000, 199861330, 1000000);
        link2 = new Link(1000, 199861330, 2000000);
        System.out.println("De l'hote A au Routeur");
        System.out.println("===========================");
        for (int i = 1; i <= 10; i++) {
            Packet packet = new Packet(i, hostA, hostB, link1, link2);
            hostA.sendPacket(packet, router);
            try {
                Thread.sleep((long) ((packet.getLink1().calculatePropagationTime() + packet.getLink1().calculateTransmissionTime(packet.getData())) * 1000000)); // influence total dù au lien L1 entre l'hoteA et le routeur pour le packet i en millisec
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            // Affichage des résultats
            System.out.println(packet.getData() + ": " + packet.getSource().getName() + " departure:" + packet.getDepartureHostTime() + "; router arrival: "
                    + packet.getArrivalRouterTime() + " at posi: " + packet.getPosition() + "; is dropped : " + packet.isDropped()
            );

        }

        System.out.println("===========================");
        System.out.println("Du routeur vers l'hote B");
        System.out.println("===========================");
        router.processQueue(3);  //send to host B
    }

    private static void rafalesDePaquets(Host hostA, Host hostB, Router router) {

        Link link1 = new Link(1000, 199861330, 1000000);
        Link link2 = new Link(1000, 199861330, 2000000);
        System.out.println("De l'hote A au Routeur");
        System.out.println("===========================");
        for (int raf = 1; raf <= 10; raf++) {
            System.out.println("===========================");
            System.out.println("Rafale num: " + raf);

            for (int i = 1; i <= 5; i++) {
                Packet packet = new Packet(i, hostA, hostB, link1, link2);
                hostA.sendPacket(packet, router);
                try {
                    Thread.sleep((long) ((packet.getLink1().calculatePropagationTime() + packet.getLink1().calculateTransmissionTime(packet.getData())) * 1000000)); // influence total dù au lien L1 entre l'hoteA et le routeur pour le packet i en millisec
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                // Affichage des résultats
                System.out.println(packet.getData() + ": " + packet.getSource().getName() + " departure:" + packet.getDepartureHostTime() + "; router arrival: "
                        + packet.getArrivalRouterTime() + " at posi: " + packet.getPosition() + "; is dropped : " + packet.isDropped()
                );

            }
            System.out.println("===========================");
            System.out.println("Du routeur vers l'hote B");
            System.out.println("===========================");
            router.processQueue(4);  //send to host B
            // Prochaine rafale
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}