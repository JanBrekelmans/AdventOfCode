package jabre.adventofcode.year2015.day9;

import jabre.adventofcode.Solution;
import jabre.adventofcode.util.graph.Graph;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

import static jabre.adventofcode.year2015.day9.Part1.getFileContents;

public class Part2 implements Solution {
    public static void main(String[] args) {
        System.out.println(new Part2().solve());
    }

    @Override
    public String solve() {
        Part1.Day9FileContents fileContents = getFileContents();
        List<String> places = getFileContents().pathCostList().stream().flatMap(p -> Stream.of(p.from(), p.to())).distinct().toList();
        Map<Integer, String> idToNameMap = new HashMap<>();
        Map<String, List<Graph.Edge>> connectivityMap = new HashMap<>();
        Map<String, Graph.Vertex> vertexMap = new HashMap<>();

        int id = 0;
        for(String place: places) {
            idToNameMap.put(id, place);
            vertexMap.put(place, new Graph.Vertex(id));
            id++;
        }


        for(Part1.PathCost pathCost: fileContents.pathCostList()) {
            List<Graph.Edge> fromConnectivity = connectivityMap.computeIfAbsent(pathCost.from(), k -> new ArrayList<>());
            fromConnectivity.add(new Graph.Edge(vertexMap.get(pathCost.from()), vertexMap.get(pathCost.to()), -pathCost.cost()));

            List<Graph.Edge> toConnectivity = connectivityMap.computeIfAbsent(pathCost.to(), k -> new ArrayList<>());
            toConnectivity.add(new Graph.Edge(vertexMap.get(pathCost.to()), vertexMap.get(pathCost.from()), -pathCost.cost()));
        }

        Graph.Path path = Graph.findMinimumPathVisitingAllVertices(places.stream().map(vertexMap::get).toList(),
                n -> connectivityMap.get(idToNameMap.get(n.id())).stream());

        return String.valueOf(-path.totalCost());
    }
}
