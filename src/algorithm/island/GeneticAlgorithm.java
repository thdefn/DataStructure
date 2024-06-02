package algorithm.island;

import java.io.*;
import java.util.*;

public class GeneticAlgorithm {
    public static Random random = new Random();
    public static int numberOfVertex;
    public static int[][] graph;
    public static int GENERATION_SIZE = 680; // 2000
    public static int TRIAL = 1;
    public static int numberOfIsland = 3;
    public static Set<Integer> vertices;
    public static int localOptimizedThreshold = 300;
    public static boolean localOptimizedMode = true;

    public static List<Integer> result = new ArrayList<>();
    public static List<Long> duration = new ArrayList<>();


    public static void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("src/algorithm/island/maxcut.in"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new FileWriter("src/algorithm/island/chimera_946.out"));
        String[] line = br.readLine().split(" ");
        numberOfVertex = Integer.parseInt(line[0]);
        vertices = new HashSet<>();
        graph = new int[numberOfVertex + 1][numberOfVertex + 1];
        int numberOfEdges = Integer.parseInt(line[1]);
        for (int i = 0; i < numberOfEdges; i++) {
            line = br.readLine().split(" ");
            graph[Integer.parseInt(line[0])][Integer.parseInt(line[1])] = Integer.parseInt(line[2]);
            graph[Integer.parseInt(line[1])][Integer.parseInt(line[0])] = Integer.parseInt(line[2]);
            vertices.add(Integer.parseInt(line[0]));
        }

        int trial = TRIAL;
        while (trial-- > 0) {
            long startTime = System.currentTimeMillis();
            List<Island> islands = new ArrayList<>();
            for (int i = 0; i < numberOfIsland; i++) {
                islands.add(new Island());
            }

            for (int gen = 0; gen < GENERATION_SIZE; gen++) {
                for (Island island : islands) {
                    island.evolve();
                }
            }

            for (Island island : islands) {
                System.out.println(island.getBestIndividual().fitness);
            }

            for (Island island : islands) {
                List<Individual> migrants = island.getBestIndividuals();
                for (Island target : islands) {
                    if (target != island) {
                        target.addIndividuals(migrants);
                    }
                }
            }

            localOptimizedMode = false;
            localOptimizedThreshold = 200;
            for (int gen = 0; gen < GENERATION_SIZE; gen++) {
                for (Island island : islands) {
                    island.evolve();
                }
            }

            Individual bestResult = islands.get(0).getBestIndividual();
            for (Island i : islands) {
                if (i.getBestIndividual().fitness > bestResult.fitness) {
                    bestResult = i.getBestIndividual();
                }
                System.out.println(i.getBestIndividual().fitness);
            }
            System.out.println(bestResult.fitness);

            for (int v : bestResult.contained) {
                bw.write(v + " ");
            }

            bw.flush();
            long du = System.currentTimeMillis() - startTime;
            System.out.println(du);
            duration.add(du);
            result.add(bestResult.fitness);
//            System.out.println(du);
//            System.out.println(bestResult.fitness);
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
        System.out.println(" du best : " + max + ", average : " + avg + ", std : " + std + "\n");

        IntSummaryStatistics statistics2 = result.stream().mapToInt(i -> i).summaryStatistics();
        avg = statistics2.getAverage();
        max = statistics2.getMax();
        dev = 0;
        sumOfSqrOfDev = 0;

        for (int i = 0; i < result.size(); i++) {
            dev = (result.get(i) - avg);
            sumOfSqrOfDev += Math.pow(dev, 2);
        }
        var = sumOfSqrOfDev / statistics.getCount();
        std = Math.sqrt(var);
        System.out.println(" result best : " + max + ", average : " + avg + ", std : " + std + "\n");
    }
}

class Individual implements Comparable<Individual> {
    List<Integer> vertexes;
    int fitness;
    Set<Integer> contained;

    public Individual(List<Integer> vertexes) {
        this.contained = new HashSet<>(vertexes);
        this.vertexes = new ArrayList<>(contained);
        fitness = calcFitness();
    }

    public Individual() {
    }


    public int calcFitness() {
        int sum = 0;
        for (int v : contained) {
            for (int i = 1; i <= GeneticAlgorithm.numberOfVertex; i++) {
                sum += GeneticAlgorithm.graph[v][i];
            }
        }
        return sum;
    }

    @Override
    public int compareTo(Individual o) {
        return o.fitness - this.fitness;
    }

    public Individual copy() {
        Individual copied = new Individual();
        copied.vertexes = new ArrayList<>(vertexes);
        copied.fitness = fitness;
        copied.contained = new HashSet<>(contained);
        return copied;
    }

    public void localOptimized() {
        boolean improved = true;
        while (improved) {
            improved = false;
           Individual best = this.copy();

            for (int i : GeneticAlgorithm.vertices) { // 모든 vertex 탐색하며 마스킹
                Individual neighbor = this.copy();
                if (neighbor.contained.contains(i))
                    neighbor.contained.remove(i);
                else neighbor.contained.add(i);
                neighbor.fitness = neighbor.calcFitness();

                if (neighbor.fitness > best.fitness) {
                    best = neighbor;
                    best.vertexes = new ArrayList<>(neighbor.contained);
                    improved = true;
                    break;
                }
            }

            if (improved) {
                this.vertexes = best.vertexes;
                this.fitness = best.fitness;
                this.contained = best.contained;
            }
        }
    }
}

class Island {
    List<Individual> population;
    public int POPULATION_SIZE = 165;
    public Random random = new Random();
    public int bestFitness;
    public int noImprovementCount;

    public Island() {
        this.population = new ArrayList<>();
        for (int i = 0; i < POPULATION_SIZE; i++) {
            population.add(new Individual(createGnome()));
        }
        Collections.sort(population);
    }

    public void evolve() {
        List<Double> calculatedSelectionProbability = calculateSelectionProbability(population);
        List<Individual> newPopulation = new ArrayList<>();
        for (int j = 0; j < POPULATION_SIZE; j++) {
            Individual p1 = select(population, calculatedSelectionProbability);
            Individual p2 = select(population, calculatedSelectionProbability);
            Individual child = crossover(p1, p2);
            mutate(child);
            newPopulation.add(child);
        }
        population = newPopulation;
        Collections.sort(population);

        if (getBestIndividual().fitness <= bestFitness) {
            noImprovementCount++;
        } else {
            bestFitness = getBestIndividual().fitness;
            noImprovementCount = 0;
        }

        if (noImprovementCount >= GeneticAlgorithm.localOptimizedThreshold) {
            if(GeneticAlgorithm.localOptimizedMode)
                localOptimized(0.1);
            else localOptimized(0.5);
        }
    }

    public void localOptimized(double prop){
        getBestIndividual().localOptimized();
        for (int i = 0; i < POPULATION_SIZE * prop; i++) {
            population.add(getBestIndividual().copy());
        }
        noImprovementCount = 0;
        bestFitness = getBestIndividual().fitness;
        Collections.sort(population);
    }

    public void mutate(Individual child) {
        for (int i = 0; i < child.vertexes.size(); i++) {
            double randomValue = random.nextDouble();
            if (randomValue <= 0.015) {
                child.vertexes.set(i, random.nextInt(GeneticAlgorithm.numberOfVertex) + 1);
            }
        }
    }

    public Individual crossover(Individual p1, Individual p2) {
        int size = p1.vertexes.size();
        int crossoverPoint = random.nextInt(size);
        List<Integer> c1 = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            if (i < crossoverPoint)
                c1.add(p1.vertexes.get(i));
            else if (i < p2.vertexes.size()) c1.add(p2.vertexes.get(i));
        }
        return new Individual(c1);
    }

    public Individual ccrossover(Individual p1, Individual p2) {
        int size = Math.min(p1.vertexes.size(), p2.vertexes.size());
        List<Integer> c1 = new ArrayList<>(Collections.nCopies(size, -1));

        boolean[] visited = new boolean[size];
        int cycleStartIndex = 0;

        while (c1.contains(-1)) {
            int currentIndex = cycleStartIndex;
            while (!visited[currentIndex] && currentIndex != -1) {
                c1.set(currentIndex, p1.vertexes.get(currentIndex));
                visited[currentIndex] = true;

                int gene = p2.vertexes.get(currentIndex);
                currentIndex = p1.vertexes.indexOf(gene);

                if (currentIndex == -1 || currentIndex >= size) {
                    break;
                }
            }

            // Find the next starting index for the cycle
            while (cycleStartIndex < size && visited[cycleStartIndex]) {
                cycleStartIndex++;
            }

            // Handle the case where all remaining indices have been visited
            if (cycleStartIndex >= size) {
                break;
            }
        }

        // Fill the remaining positions with the opposite parent's genes
        for (int i = 0; i < size; i++) {
            if (c1.get(i) == -1) {
                c1.set(i, p2.vertexes.get(i));
            }
        }

        return new Individual(c1);
    }


    public int getRandomCardinality() {
        return random.nextInt(GeneticAlgorithm.numberOfVertex - 1) + 1;
    }


    private List<Integer> createGnome() {
        List<Integer> geneSet = new ArrayList<>();
        for (int i = 0; i < getRandomCardinality(); i++) {
            int selected = random.nextInt(GeneticAlgorithm.numberOfVertex) + 1;
            while (geneSet.contains(selected)) {
                selected = random.nextInt(GeneticAlgorithm.numberOfVertex) + 1;
            }
            geneSet.add(selected);
        }
        return geneSet;
    }

    public List<Double> calculateSelectionProbability(List<Individual> sorted) {
        List<Double> selectionProbability = new ArrayList<>();
        int N = sorted.size(); // 1 - N
        for (int i = 0; i < N; i++) {
            selectionProbability.add((2.0 * (N - i)) / (N * (N + 1)));
        }
        return selectionProbability;
    }

    public int selectIndex(List<Individual> population, List<Double> selectionProbability) {
        double rand = random.nextDouble();
        double cumulativeProbability = 0.0;
        for (int i = 0; i < population.size(); i++) {
            cumulativeProbability += selectionProbability.get(i);
            if (rand <= cumulativeProbability) {
                return i;
            }
        }
        return population.size() - 1;
    }

    public Individual select(List<Individual> population, List<Double> selectionProbability) {
        return population.get(selectIndex(population, selectionProbability));
    }

    public List<Individual> getBestIndividuals() {
        return new ArrayList<>(population.subList(0, (int) (POPULATION_SIZE * 0.1)));
    }

    public void addIndividuals(List<Individual> immigrants) {
        population.addAll(immigrants);
        Collections.sort(population);
        noImprovementCount = 0;
    }

    public Individual getBestIndividual() {
        return this.population.get(0);
    }
}
