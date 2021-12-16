import java.util.*;

public class Main {

    public static void main(String[] args) {

        int[][] routeGraph = RouteGraphHolder.routeGraphs.get(0);

        int numberOfNodes = routeGraph.length;

        boolean validUserInput = false;
        int start = 0;
        int end = 0;

        while (!validUserInput) {
            Scanner scanner = new Scanner(System.in);
            System.out.println("Enter the number of the starting point");
            start = scanner.nextInt();
            System.out.println("Enter the number of the ending point");
            end = scanner.nextInt();
            if (start >= 0 && end >= 0 && start < numberOfNodes && end < numberOfNodes) {
                validUserInput = true;
            }
            ;
        }

        List<Node> priorityQueue = new ArrayList<>();
        List<Node> visitedQueue = new ArrayList<>();

        PrioQueueElementComparator prioQueueElementComparator = new PrioQueueElementComparator();

        Node startElement = new Node();
        startElement.setSerial(start);
        startElement.setDistance(0);
        startElement.setRouteVia(start);
        priorityQueue.add(startElement);

        for (int i = 0; i < numberOfNodes; i++) {

            Node currentNode = priorityQueue.get(0); //always the top is the current

            for (int j = 0; j < numberOfNodes; j++) {

                if (routeGraph[currentNode.getSerial()][j] == 0) { // if not neighboring, ignore it
                    continue;
                }

                boolean visited = false;

                for (Node p : visitedQueue) {
                    if (p.getSerial() == j) {
                        visited = true;
                    }
                }

                if (visited) { //we dont check distance against a visited node
                    continue;
                }

                Node nodeWithCalculatedDistanceButNotVisited = null;

                for (Node p : priorityQueue //check if the node is already has a distance
                ) {
                    if (p.getSerial() == j) {
                        nodeWithCalculatedDistanceButNotVisited = p;
                        break;
                    }
                }

                if (nodeWithCalculatedDistanceButNotVisited == null) { //branch for adding new node to the priorityQueue

                    Node newNode = new Node();
                    newNode.setSerial(j);
                    newNode.setDistance(routeGraph[currentNode.getSerial()][j] + currentNode.getDistance());
                    newNode.setRouteVia(currentNode.getSerial());

                    priorityQueue.add(newNode);
                    Collections.sort(priorityQueue, prioQueueElementComparator);
                } else { //branch for nodes with an existing distance. distance might get updated if shorter than existing

                    if ((nodeWithCalculatedDistanceButNotVisited.getDistance() + routeGraph[currentNode.getSerial()][j]) < currentNode.getDistance()) {
                        currentNode.setDistance(nodeWithCalculatedDistanceButNotVisited.getDistance() + routeGraph[currentNode.getSerial()][j]);
                        currentNode.setRouteVia(nodeWithCalculatedDistanceButNotVisited.getSerial());
                    }
                    Collections.sort(priorityQueue, prioQueueElementComparator);
                }
            }
            visitedQueue.add(currentNode);
            priorityQueue.remove(currentNode);
        }

        printResult(visitedQueue, start, end);
    }

    public static void printResult(List<Node> visitedQueue, int start, int end) {
        Node endElement = null;

        for (Node p : visitedQueue
        ) {
            if (p.getSerial() == end) {
                endElement = p;
                System.out.println(
                        "The shortest route from " + start + " to " + p.getSerial() + " is " + p.getDistance() + " units");
            }
        }

        List<Integer> path = new ArrayList<>();
        path.add(endElement.getSerial());
        path.add(endElement.getRouteVia());

        int nextPath = endElement.getRouteVia();

        while (nextPath != start) { //track back the path
            for (Node p : visitedQueue
            ) {
                if (p.getSerial() == nextPath) {
                    path.add(p.getRouteVia());
                    nextPath = p.getRouteVia();
                    break;
                }
            }
        }

        StringBuilder pathString = new StringBuilder("The shortest path is ");

        for (int i = path.size() - 1; i >= 0; i--) { //concetanate the path into a string
            pathString.append(path.get(i) + " ");
        }

        System.out.println(pathString);
    }

}
