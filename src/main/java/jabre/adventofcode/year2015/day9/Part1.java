package jabre.adventofcode.year2015.day9;

import jabre.adventofcode.Solution;
import jabre.adventofcode.util.IntBox;
import jabre.adventofcode.util.graph.Graph;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Files;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Part1 implements Solution {

    public static void main(String[] args) {
        System.out.println(new Part1().solve());
    }

    @Override
    public String solve() {
        Day9FileContents fileContents = getFileContents();
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


        for(PathCost pathCost: fileContents.pathCostList) {
            List<Graph.Edge> fromConnectivity = connectivityMap.computeIfAbsent(pathCost.from(), k -> new ArrayList<>());
            fromConnectivity.add(new Graph.Edge(vertexMap.get(pathCost.from()), vertexMap.get(pathCost.to()), pathCost.cost()));

            List<Graph.Edge> toConnectivity = connectivityMap.computeIfAbsent(pathCost.to(), k -> new ArrayList<>());
            toConnectivity.add(new Graph.Edge(vertexMap.get(pathCost.to()), vertexMap.get(pathCost.from()), pathCost.cost()));
        }

        Graph.Path path = Graph.findMinimumPathVisitingAllVertices(places.stream().map(vertexMap::get).toList(),
                n -> connectivityMap.get(idToNameMap.get(n.id())).stream());

        return String.valueOf(path.totalCost());
    }

    static Day9FileContents getFileContents() {
        URL fileName = Part1.class.getResource("day9.txt");
        try (BufferedReader reader = Files.newBufferedReader(new File(fileName.toURI()).toPath());
             Stream<String> lines = reader.lines()) {
            Pattern pattern = Pattern.compile("(.*) to (.*) = (\\d*)");
            return new Day9FileContents(lines.map(line -> {
                Matcher matcher = pattern.matcher(line);
                if(matcher.matches()) {
                    String from = matcher.group(1);
                    String to = matcher.group(2);
                    double cost = Double.parseDouble(matcher.group(3));
                    return new PathCost(from, to, cost);
                }
                throw new RuntimeException("Could not match line " + line);
            }).toList());
        } catch (IOException | URISyntaxException e) {
            throw new RuntimeException(e);
        }
    }

    record PathCost(String from, String to, double cost) {
    }

    record Day9FileContents(List<PathCost> pathCostList) {
    }
}
