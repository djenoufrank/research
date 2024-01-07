import objects.Host;
import objects.Link;
import objects.Packet;
import objects.Router;

import java.util.*;

public class Main {
    public static void main(String[] args) {
        Host hostA = new Host("Host A");
        Host hostB = new Host("Host B");
        Router router = new Router("Router", 10);
        int choice = 0;
        int lancer=1;
        while(lancer==1){

        
        System.out.println("\n\n                Modelisation file d'attente");
        System.out.println("                ===========================\n");
        System.out.println("case 1: Illustration du goulot d’étranglement");
        System.out.println("case 2: Saturation de L2 avec goulot d’étranglement");
        System.out.println("case 3: Saturation de L2 sans congestion");
        System.out.println("case 4: Rafales de paquets avec L2 comme goulot d’étranglement");
        System.out.println("case 5: Envoi aléatoire de paquets\n");
        System.out.println("choisi parmi les cas 1 au cas 5");
        Scanner sc = new Scanner(System.in);
        choice = sc.nextInt();
        while(!(choice >= 1 && choice <= 5)) {
                System.out.println("erreur de saisie. refaites un choix svp entre 1 et 5");
             choice = sc.nextInt();                
        }
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
                random(hostA, hostB, router);
                break;
        }
        System.out.println("\n      voulez vous continer? Oui=1, non=autre chiffre");
        try {
             lancer = sc.nextInt();
             if(lancer!=1)  System.out.println("Au revoir");
        } catch (Exception e) {
            lancer = 0;
            System.out.println("Au revoir");
        }
        }
    }

    private static void illustrationGoulotEtranglement(Host hostA, Host hostB, Router router) {
        Link link2;
        Link link1;
        link1 = new Link(1000, 199861330, 500000); // 199861330: 2/3 vit de la lum en m/s
        link2 = new Link(1000, 199861330, 10000000);
        List<Packet> myList = new ArrayList<>();
        System.out.println("===========================");
        for (int i = 1; i <= 2; i++) {
            Packet packet = new Packet(i, hostA, hostB, link1, link2);
            if (i > router.getQueueCapacity()) myList.add(packet);
            hostA.sendPacket(packet, router);
        }
        myList.addAll(router.processQueue(1));
        Collections.sort(myList, Comparator.comparingInt(Packet::getData));
        for (int i = 0; i < myList.size(); i++) {
            if (!myList.get(i).isDropped()) {
                System.out.println(myList.get(i).getData() + " dép " + myList.get(i).getSource().getName() + "(" + myList.get(i).getDepartureHostTime() + ") arr " + router.getName() + "(" + myList.get(i).getArrivalRouterTime() + ")  dép " + router.getName() + "(" + myList.get(i).getDepartureRouterTime() + ") arr " + myList.get(i).getDestination().getName() + "(" + myList.get(i).getReceiveHostTime() + ") pos " + router.getName() + "(" + myList.get(i).getPosition() + ") jeté?(non)");
            } else {
                System.out.println(myList.get(i).getData() + " dép " + myList.get(i).getSource().getName() + "(" + myList.get(i).getDepartureHostTime() + ") arr " + router.getName() + "(" + myList.get(i).getArrivalRouterTime() + ") pos " + router.getName() + "(" + myList.get(i).getPosition() + ") jeté?(oui)");
            }
        }
    }

    private static void saturationL2AvecGoulot(Host hostA, Host hostB, Router router) {
        Link link1;
        Link link2;
        link1 = new Link(1000, 199861330, 500000); 
        // distance, vitesse de propagation et débit de transmission
        link2 = new Link(1000, 199861330, 250000);
        List<Packet> myList = new ArrayList<>();
        System.out.println("===========================");
        for (int i = 1; i <= 15; i++) {
            Packet packet = new Packet(i, hostA, hostB, link1, link2);
            if (i > router.getQueueCapacity()) myList.add(packet);
            hostA.sendPacket(packet, router);
        }
        myList.addAll(router.processQueue(2));
        Collections.sort(myList, Comparator.comparingInt(Packet::getData));
        for (int i = 0; i < myList.size(); i++) {
            if (!myList.get(i).isDropped()) {
                System.out.println(myList.get(i).getData() + " dép " + myList.get(i).getSource().getName() + "(" + myList.get(i).getDepartureHostTime() + ") arr " + router.getName() + "(" + myList.get(i).getArrivalRouterTime() + ")  dép " + router.getName() + "(" + myList.get(i).getDepartureRouterTime() + ") arr " + myList.get(i).getDestination().getName() + "(" + myList.get(i).getReceiveHostTime() + ") pos " + router.getName() + "(" + myList.get(i).getPosition() + ") jeté?(non)");
            } else {
                System.out.println(myList.get(i).getData() + " dép " + myList.get(i).getSource().getName() + "(" + myList.get(i).getDepartureHostTime() + ") arr " + router.getName() + "(" + myList.get(i).getArrivalRouterTime() + ") pos " + router.getName() + "(" + myList.get(i).getPosition() + ") jeté?(oui)");
            }
        }
        System.out.println("\nNombre total de paquets jetés: " + router.getEjectedPacket());
    }

    private static void saturationL2SansCongestion(Host hostA, Host hostB, Router router) {
        Link link2;
        Link link1;
        link1 = new Link(1000, 199861330, 1000000);
        link2 = new Link(1000, 199861330, 2000000);
        List<Packet> myList = new ArrayList<>();
        System.out.println("===========================");
        for (int i = 1; i <= 10; i++) {
            Packet packet = new Packet(i, hostA, hostB, link1, link2);
            if (i > router.getQueueCapacity()) myList.add(packet);
            hostA.sendPacket(packet, router);
            try {
                Thread.sleep((long) ((packet.getLink1().calculatePropagationTime() + packet.getLink1().calculateTransmissionTime(packet.getData())) * 1000000)); 
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
         }
        myList.addAll(router.processQueue(3));  
        Collections.sort(myList, Comparator.comparingInt(Packet::getData));
        for (int i = 0; i < myList.size(); i++) {
            if (!myList.get(i).isDropped()) {
                System.out.println(myList.get(i).getData() + " dép " + myList.get(i).getSource().getName() + "(" + myList.get(i).getDepartureHostTime() + ") arr " + router.getName() + "(" + myList.get(i).getArrivalRouterTime() + ")  dép " + router.getName() + "(" + myList.get(i).getDepartureRouterTime() + ") arr " + myList.get(i).getDestination().getName() + "(" + myList.get(i).getReceiveHostTime() + ") pos " + router.getName() + "(" + myList.get(i).getPosition() + ") jeté?(non)");
            } else {
                System.out.println(myList.get(i).getData() + " dép " + myList.get(i).getSource().getName() + "(" + myList.get(i).getDepartureHostTime() + ") arr " + router.getName() + "(" + myList.get(i).getArrivalRouterTime() + ") pos " + router.getName() + "(" + myList.get(i).getPosition() + ") jeté?(oui)");
            }
        }
    }

    private static void rafalesDePaquets(Host hostA, Host hostB, Router router) {

        Link link1 = new Link(1000, 199861330, 1000000);
        Link link2 = new Link(1000, 199861330, 2000000);
       
        for (int raf = 1; raf <= 10; raf++) {
            System.out.println("===========================");
            System.out.println("Rafale num: " + raf);

            for (int i = 1; i <= 5; i++) {
                Packet packet = new Packet(i, hostA, hostB, link1, link2);
                hostA.sendPacket(packet, router);
                try {
                    Thread.sleep((long) ((packet.getLink1().calculatePropagationTime() + packet.getLink1().calculateTransmissionTime(packet.getData())) * 1000000));
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(packet.getData() + ": " + packet.getSource().getName() + " departure:" + packet.getDepartureHostTime() + "; router arrival: "
                        + packet.getArrivalRouterTime() + " at posi: " + packet.getPosition() + "; is dropped : " + packet.isDropped()
                );

            }
            router.processQueue(4);  
            // Prochaine rafale
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private static void random(Host hostA, Host hostB, Router router) {
        Link link2;
        Link link1;
        link1 = new Link(1000, 199861330, 100000);
        link2 = new Link(1000, 199861330, 2000);
        List<Packet> myList = new ArrayList<>();
        System.out.println("===========================");
        Random random = new Random();
        for (int i = 1; i <= 10; i++) {
            Packet packet = new Packet(i, hostA, hostB, link1, link2);
            if (i > router.getQueueCapacity()) myList.add(packet);
            hostA.sendPacket(packet, router);
            // Générer un intervalle aléatoire à partir d'une distribution exponentielle
            double randomInterval = -Math.log(1.0 - random.nextDouble()) * (1.0 / packet.getLink2().getTransmissionSpeed());
            try {
                Thread.sleep((long) ((randomInterval) * 10000)); 
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        myList.addAll(router.processQueue(5));
        Collections.sort(myList, Comparator.comparingInt(Packet::getData));
        for (int i = 0; i < myList.size(); i++) {
            if (!myList.get(i).isDropped()) {
                System.out.println(myList.get(i).getData() + " dép " + myList.get(i).getSource().getName() + "(" + myList.get(i).getDepartureHostTime() + ") arr " + router.getName() + "(" + myList.get(i).getArrivalRouterTime() + ")  dép " + router.getName() + "(" + myList.get(i).getDepartureRouterTime() + ") arr " + myList.get(i).getDestination().getName() + "(" + myList.get(i).getReceiveHostTime() + ") pos " + router.getName() + "(" + myList.get(i).getPosition() + ") jeté?(non)");
            } else {
                System.out.println(myList.get(i).getData() + " dép " + myList.get(i).getSource().getName() + "(" + myList.get(i).getDepartureHostTime() + ") arr " + router.getName() + "(" + myList.get(i).getArrivalRouterTime() + ") pos " + router.getName() + "(" + myList.get(i).getPosition() + ") jeté?(oui)");
            }
        }
    }
}