package jabre.adventofcode.year2015.day14;

import jabre.adventofcode.Solution;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Files;
import java.util.List;
import java.util.PriorityQueue;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Part1 implements Solution {
    static int END_TIME = 2503;
    public static void main(String[] args) {
        System.out.println(new Part1().solve());
    }

    @Override
    public String solve() {
        Day14FileContents fileContents = getFileContents();
        List<Reindeer> reindeerList = fileContents.reindeerPropertiesList().stream()
                .map(properties -> new Reindeer(properties))
                .toList();

        PriorityQueue<Event> priorityQueue = new PriorityQueue<>();
        priorityQueue.add(new EndEvent(END_TIME));
        reindeerList.forEach(reindeer -> priorityQueue.add(new StartRunningEvent(0, reindeer)));

        Event poll;
        while((poll = priorityQueue.poll()) != null && !(poll instanceof EndEvent)) {
            List<Event> newEvents = poll.execute();
            priorityQueue.addAll(newEvents);
        }

        // Finalize running reindeers
        reindeerList.forEach(reindeer -> {
            if(!reindeer.isResting) {
                reindeer.travelledLength += (END_TIME - reindeer.startRunningTime) * reindeer.reindeerProperties.speed;
            }
        });

        return String.valueOf(reindeerList.stream().mapToInt(reinder -> reinder.travelledLength).max().getAsInt());
    }

    static Day14FileContents getFileContents() {
        URL fileName = Part1.class.getResource("day14.txt");
        try (BufferedReader reader = Files.newBufferedReader(new File(fileName.toURI()).toPath())) {
            final Pattern reindeerPattern = Pattern.compile("(\\D*) can fly (\\d*) km/s for (\\d*) seconds, but then must rest for (\\d*) seconds.");

            return new Day14FileContents(reader.lines()
                    .map(s -> {
                        Matcher matcher = reindeerPattern.matcher(s);
                        if(matcher.find()) {
                            String name = matcher.group(1);
                            int speed = Integer.parseInt(matcher.group(2));
                            int timeLength = Integer.parseInt(matcher.group(3));
                            int restLength = Integer.parseInt(matcher.group(4));
                            return new ReindeerProperties(name, speed, timeLength, restLength);
                        }
                        throw new RuntimeException("Could not parse line: " + s);
                    })
                    .toList());
        } catch (IOException | URISyntaxException e) {
            throw new RuntimeException(e);
        }
    }

    record Day14FileContents(List<ReindeerProperties> reindeerPropertiesList) {
    }

    record ReindeerProperties(String name, int speed, int timeLength, int restLength){}

    static class Reindeer {
        final ReindeerProperties reindeerProperties;
        boolean isResting = false;
        int startRunningTime = 0;
        int travelledLength = 0;

        Reindeer(ReindeerProperties reindeerProperties) {
            this.reindeerProperties = reindeerProperties;
        }
    }

    abstract static class Event implements Comparable<Event> {
        final int time;

        Event(int time) {
            this.time = time;
        }

        @Override
        public int compareTo(Event o) {
            return Integer.compare(time, o.time);
        }

        abstract List<Event> execute();
    }

    static class EndEvent extends Event {

        EndEvent(int time) {
            super(time);
        }

        @Override
        List<Event> execute(){return List.of();}
    }

    static class StartRunningEvent extends Event {
        final Reindeer reindeer;

        StartRunningEvent(int time, Reindeer reindeer) {
            super(time);
            this.reindeer = reindeer;
        }

        @Override
        List<Event> execute() {
            reindeer.isResting = false;
            reindeer.startRunningTime = time;
            return List.of(new StopRunningEvent(time + reindeer.reindeerProperties.timeLength, reindeer));
        }
    }

    static class StopRunningEvent extends Event {
        final Reindeer reindeer;

        StopRunningEvent(int time, Reindeer reindeer) {
            super(time);
            this.reindeer = reindeer;
        }

        @Override
        List<Event> execute() {
            reindeer.isResting = true;
            reindeer.travelledLength += reindeer.reindeerProperties.speed * reindeer.reindeerProperties.timeLength;
            return List.of(new StartRunningEvent(time + reindeer.reindeerProperties.restLength, reindeer));
        }
    }
}
