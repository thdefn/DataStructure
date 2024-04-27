package algorithm.optimization.merge;

import java.io.*;
import java.util.*;

public class GeneticAlgorithm {
    public static Random random = new Random();
    public static int numberOfVertex;
    public static int[][] graph;
    public static int POPULATION_SIZE = 500;
    public static int GENERATION_SIZE = 3000;
    public static int TRIAL = 30;
    public static int TOURNAMENT_SIZE = 8;

    public static void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("src/algorithm/optimization/random_500.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new FileWriter("src/algorithm/optimization/merge/maxcut.out"));
        String[] line = br.readLine().split(" ");
        numberOfVertex = Integer.parseInt(line[0]);
        graph = new int[numberOfVertex + 1][numberOfVertex + 1];
        int numberOfEdges = Integer.parseInt(line[1]);
        for (int i = 0; i < numberOfEdges; i++) {
            line = br.readLine().split(" ");
            graph[Integer.parseInt(line[0])][Integer.parseInt(line[1])] = Integer.parseInt(line[2]);
            graph[Integer.parseInt(line[1])][Integer.parseInt(line[0])] = Integer.parseInt(line[2]);
        }

        int trial = TRIAL;
        List<Long> duration = new ArrayList<>();

        while (trial-- > 0) {
            List<Individual> population = new ArrayList<>();
            long startTime = System.currentTimeMillis();

            for (int i = 0; i < POPULATION_SIZE; i++) {
                population.add(new Individual(createGnome(), numberOfVertex, graph));
            }

            int generationSize = GENERATION_SIZE;
            while (generationSize-- > 0) {
                List<Individual> newGeneration = new ArrayList<>();
                for (int j = 0; j < POPULATION_SIZE; j++) {
                    Individual p1 = select(population);
                    Individual p2 = select(population);
                    Individual child = crossover(p1, p2);
                    mutate(child);
                    newGeneration.add(child);
                }

                population = newGeneration;
                population = mergeSort(population, 0, population.size() - 1);
//                bw.write("generation : " + (GENERATION_SIZE - generationSize) + ", best : " + population.get(0).fitness + ", average : " + population.stream().mapToInt(i -> i.fitness).average().getAsDouble() + "\n");

            }
            long du = System.currentTimeMillis() - startTime;
            duration.add(du);
            System.out.println(population.get(0).fitness);
        }
        LongSummaryStatistics statistics = duration.stream().mapToLong(i -> i).summaryStatistics();
        double avg = statistics.getAverage();
        double max = statistics.getMax();
        double dev = 0;
        double sumOfSqrOfDev = 0;

        for (int i = 0; i < duration.size(); i++) {
            dev = (duration.get(i) - avg);
            sumOfSqrOfDev += Math.pow(dev, 2);
        }
        double var = sumOfSqrOfDev / statistics.getCount();
        double std = Math.sqrt(var);
        System.out.println("best : " + max + ", average : " + avg + ", std : " + std + "\n");

    }


    public static void mutate(Individual child) {
        for (int i = 0; i < child.vertexes.size(); i++) {
            double randomValue = random.nextDouble();
            if (randomValue <= 0.015) {
                child.vertexes.set(i, random.nextInt(numberOfVertex) + 1);
            }
        }
    }

    public static Individual crossover(Individual p1, Individual p2) {
        int size = p1.vertexes.size();
        int crossoverPoint = random.nextInt(size);
        List<Integer> c1 = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            if (i < crossoverPoint)
                c1.add(p1.vertexes.get(i));
            else if (i < p2.vertexes.size()) c1.add(p2.vertexes.get(i));
        }
        return new Individual(c1, numberOfVertex, graph);
    }


    public static int getRandomCardinality() {
        return random.nextInt(numberOfVertex - 1) + 1;
    }


    private static List<Integer> createGnome() {
        List<Integer> geneSet = new ArrayList<>();
        for (int i = 0; i < getRandomCardinality(); i++) {
            int selected = random.nextInt(numberOfVertex) + 1;
            while (geneSet.contains(selected)) {
                selected = random.nextInt(numberOfVertex) + 1;
            }
            geneSet.add(selected);
        }
        return geneSet;
    }

    public static Individual select(List<Individual> population) {
        List<Individual> tournament = new ArrayList<>();
        for (int i = 0; i < TOURNAMENT_SIZE; i++) {
            int randomIndex = random.nextInt(population.size());
            tournament.add(population.get(randomIndex));
        }

        Individual best = tournament.get(0);
        int maxFitness = 0;
        for (Individual i : tournament) {
            if (i.fitness > maxFitness) {
                best = i;
                maxFitness = i.fitness;
            }
        }
        return best;
    }

    private static List<Individual> mergeSort(List<Individual> arrays, int l, int r) {
        if (l == r)
            return Collections.singletonList(arrays.get(l));

        int m = (l + r) / 2;
        List<Individual> left = mergeSort(arrays, l, m);
        List<Individual> right = mergeSort(arrays, m + 1, r);
        return merge(left, right);
    }

    private static List<Individual> merge(List<Individual> left, List<Individual> right) {
        List<Individual> merged = new ArrayList<>();
        int l = 0;
        int r = 0;
        while (l < left.size() && r < right.size()) {
            if (left.get(l).fitness >= right.get(r).fitness)
                merged.add(left.get(l++));
            else merged.add(right.get(r++));
        }

        while (l < left.size()) {
            merged.add(left.get(l++));
        }

        while (r < right.size()) {
            merged.add(right.get(r++));
        }

        return merged;
    }

}

class Individual {
    List<Integer> vertexes;
    int fitness;

    public Individual(List<Integer> vertexes, int numberOfVertexes, int[][] graph) {
        this.vertexes = vertexes;
        fitness = calcFitness(numberOfVertexes, graph);
    }

    public Set<Integer> getVertexeSet() {
        return new HashSet<>(this.vertexes);
    }

    private int calcFitness(int numberOfVertex, int[][] graph) {
        Set<Integer> contained = getVertexeSet();
        int sum = 0;
        for (int v : contained) {
            for (int i = 0; i < numberOfVertex; i++) {
                if (!contained.contains(i))
                    sum += graph[v][i];
            }
        }
        return sum;
    }
}
