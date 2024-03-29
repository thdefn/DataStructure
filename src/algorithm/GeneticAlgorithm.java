import java.io.*;
import java.util.*;

public class GeneticAlgorithm {
    public static Random random = new Random();
    public static int numberOfVertex;
    public static int[][] graph;
    public static int POPULATION_SIZE = 300;
    public static int TEST_SIZE = 3000;
    public static int TRIAL = 1;

    public static void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("./maxcut.in"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new FileWriter("./maxcut.out"));
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
        while (trial-- > 0) {
            List<Individual> population = new ArrayList<>();

            for (int i = 0; i < POPULATION_SIZE; i++) {
                population.add(new Individual(createGnome()));
            }

            int testSize = TEST_SIZE;

            while (testSize-- > 0) {
                int totalWeight = 0;
                for (Individual i : population) {
                    totalWeight += i.fitness;
                }

                List<Individual> newGeneration = new ArrayList<>();
                // 80% 대체
                for (int j = 0; j < POPULATION_SIZE * 0.8; j++) {
                    Individual p1 = select(population, totalWeight);
                    Individual p2 = select(population, totalWeight);
                    Individual child = crossover(p1, p2);
                    mutate(child);
                    newGeneration.add(child);
                }

                for (int i = 0; i < POPULATION_SIZE * 0.8; i++) {
                    population.set(POPULATION_SIZE - 1 - i, newGeneration.get(i));
                }
                Collections.sort(population);
            }
            for (int v : population.get(0).getVertexeSet()) {
                bw.write(v + " ");
            }
        }
        bw.flush();
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
        return new Individual(c1);
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

    public static class Individual implements Comparable<Individual> {
        List<Integer> vertexes;
        int fitness;

        public Individual(List<Integer> vertexes) {
            this.vertexes = vertexes;
            fitness = calcFitness();
        }

        public Set<Integer> getVertexeSet() {
            return new HashSet<>(this.vertexes);
        }

        private int calcFitness() {
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

        @Override
        public int compareTo(Individual o) {
            if (o.fitness == this.fitness)
                return this.vertexes.size() - o.vertexes.size();
            return o.fitness - this.fitness;
        }
    }

    public static Individual select(List<Individual> population, int totalWeight) {
        double randomValue = random.nextDouble() * totalWeight;

        int cumulativeWeight = 0;
        for (int i = 0; i < population.size(); i++) {
            cumulativeWeight += population.get(i).fitness;
            if (randomValue <= cumulativeWeight) return population.get(i);
        }
        return population.get(population.size() - 1);

    }
}
