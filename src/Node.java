public class Node {
    int serial;
    int routeVia;
    int distance;

    public Node(int serial, int routeVia, int distance) {
        this.serial = serial;
        this.routeVia = routeVia;
        this.distance = distance;
    }

    public Node() {
    }

    public int getSerial() {
        return serial;
    }

    public void setSerial(int serial) {
        this.serial = serial;
    }

    public int getRouteVia() {
        return routeVia;
    }

    public void setRouteVia(int routeVia) {
        this.routeVia = routeVia;
    }

    public int getDistance() {
        return distance;
    }

    public void setDistance(int distance) {
        this.distance = distance;
    }
}
