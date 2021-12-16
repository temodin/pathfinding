import java.util.Comparator;

public class PrioQueueElementComparator implements Comparator<Node> {

    @Override
    public int compare(Node p1, Node p2) {
        return Integer.compare(p1.getDistance(),p2.getDistance());
    }
}
