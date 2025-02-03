package jabre.adventofcode.util.graph;

import jabre.adventofcode.util.Box;
import jabre.adventofcode.util.DoubleBox;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Stream;

public class Graph {
    public record Vertex(int id) {
    }

    public record Edge(Vertex from, Vertex to, double cost) {
    }

    public record Path(List<Vertex> vertices, double totalCost) {
    }

    public static Path findMinimumPathVisitingAllVertices(List<Vertex> allVertices, Function<Vertex, Stream<Edge>> connectivitySupplier) {
        record Current(ArrayList<Vertex> currentPath, DoubleBox cost,
                       BitSet visitedIndices) implements Comparable<Current> {
            @Override
            public int compareTo(Current o) {
                return Double.compare(cost.element, o.cost.element);
            }

            Current deepCopy() {
                ArrayList<Vertex> newCurrentPath = new ArrayList<>(Current.this.currentPath);
                DoubleBox newCost = new DoubleBox(cost.element);
                BitSet newVisitedIndices = new BitSet(visitedIndices.size());
                newVisitedIndices.or(Current.this.visitedIndices);
                return new Current(
                        newCurrentPath,
                        newCost,
                        newVisitedIndices);

            }
        }

        PriorityQueue<Current> priorityQueue = new PriorityQueue<>();
        allVertices.forEach(vertex -> {
            ArrayList<Vertex> currentPath = new ArrayList<>();
            DoubleBox cost = new DoubleBox();
            BitSet unvisitedIndices = new BitSet(allVertices.size());
            currentPath.add(vertex);
            unvisitedIndices.set(vertex.id, true);
            priorityQueue.add(new Current(currentPath, cost, unvisitedIndices));
        });
        Box<Current> bestPath = new Box<>();

        while (!priorityQueue.isEmpty()) {
            Current poll = priorityQueue.poll();
            connectivitySupplier.apply(poll.currentPath.get(poll.currentPath.size() - 1))
                    .forEach(edge -> {
                        Vertex to = edge.to;
                        if(!poll.visitedIndices.get(to.id)) {
                            Current copy = poll.deepCopy();
                            copy.currentPath.add(to);
                            copy.cost.element += edge.cost;
                            copy.visitedIndices.set(to.id, true);
                            if(copy.currentPath.size() == allVertices.size()) {
                                if(bestPath.element == null || bestPath.element.cost.element > copy.cost.element) {
                                    bestPath.element = copy;
                                }
                            } else {
                                priorityQueue.add(copy);
                            }
                        }
                    });
        }

        return new Path(bestPath.element.currentPath, bestPath.element.cost.element);
    }
}
