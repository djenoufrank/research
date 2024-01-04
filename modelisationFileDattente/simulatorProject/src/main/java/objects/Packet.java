package objects;

public class Packet {
    private int data;
    private final Host source;
    private final Host destination;
    private boolean dropped;
    private int position;
    private long departureHostTime;
    private long receiveHostTime;
    private final Link link1;
    private final Link link2;
    private long departureRouterTime;
    private long arrivalRouterTime;

    public Packet(int data, Host source, Host destination, Link link1, Link link2) {
        this.data = data;
        this.source = source;
        this.destination = destination;
        this.position = -1;
        this.departureHostTime = 0;
        this.receiveHostTime = 0;
        this.link1 = link1;
        this.link2 = link2;
        departureRouterTime = 0;
        arrivalRouterTime = 0;
    }

    public int getData() {
        return data;
    }

    public Host getSource() {
        return source;
    }

    public Host getDestination() {
        return destination;
    }

    public void setDropped(boolean dropped) {
        this.dropped = dropped;
    }

    public boolean isDropped() {
        return dropped;
    }

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    public long getDepartureRouterTime() {
        return departureRouterTime;
    }

    public long getArrivalRouterTime() {
        return arrivalRouterTime;
    }

    public long getDepartureHostTime() {
        return departureHostTime;
    }

    public long getReceiveHostTime() {
        return receiveHostTime;
    }

    public void setDepartureRouterTime(long departureRouterTime) {
        this.departureRouterTime = departureRouterTime;
    }

    public void setArrivalRouterTime(long arrivalRouterTime) {
        this.arrivalRouterTime = arrivalRouterTime;
    }

    public void setDepartureHostTime(long departureHostTime) {
        this.departureHostTime = departureHostTime;
    }

    public void setReceiveHostTime(long receiveHostTime) {
        this.receiveHostTime = receiveHostTime;
    }

    public Link getLink1() {
        return link1;
    }

    public Link getLink2() {
        return link2;
    }
}

