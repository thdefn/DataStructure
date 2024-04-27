package algorithm.genetic;

import java.io.*;
import java.util.*;

public class GeneticAlgorithmBatch {
    public static Random random = new Random();
    public static int numberOfVertex;
    public static int[][] graph;
    public static int POPULATION_SIZE = 300;
    public static int TEST_SIZE = 3000;
    public static int TRIAL = 30;
    public static List<Individual> eugenes = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("src/algorithm/genetic/out/weighted_500.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new FileWriter("src/algorithm/genetic/out/weighted_500_pop_500.out"));
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
            bw.write("----------------------------- " + (TRIAL - trial) + " ------------------------------" + "\n");
            int generation = 0;
            List<Individual> population = new ArrayList<>();

            for (int i = 0; i < POPULATION_SIZE; i++) {
                population.add(new Individual(createGnome()));
            }

            int testSize = TEST_SIZE;

            while (testSize-- > 0) {
                List<Individual> newGeneration = new ArrayList<>();
                // 80% 대체
                for (int j = 0; j < POPULATION_SIZE * 0.8; j++) {
                    Individual p1 = select(population);
                    Individual p2 = select(population);
                    Individual child = crossover(p1, p2);
                    mutate(child);
                    newGeneration.add(child);
                }

                for (int i = 0; i < POPULATION_SIZE * 0.8; i++) {
                    population.set(POPULATION_SIZE - 1 - i, newGeneration.get(i));
                }
                Collections.sort(population);
                generation++;
                bw.write("-- gen " + generation + " --" + "\n");
                bw.write("최고 품질 : " + population.get(0).fitness + "\n");
                bw.write("평균 품질 : " + population.stream().mapToInt(i -> i.fitness).average().getAsDouble() + "\n");
            }
            eugenes.add(population.get(0));
            DoubleSummaryStatistics statistics = population.stream().mapToDouble(i -> i.fitness).summaryStatistics();
            double avg = statistics.getAverage();
            double max = statistics.getMax();

            double dev = 0;
            double sumOfSqrOfDev = 0;

            for (int i = 0; i < population.size(); i++) {
                dev = (population.get(i).fitness - avg);
                sumOfSqrOfDev += Math.pow(dev, 2);
            }
            double var = sumOfSqrOfDev / statistics.getCount();
            double std = Math.sqrt(var);
            bw.write("== 최종 결과 ==\n");
            bw.write("best individual fitness : " + population.get(0).fitness + "\n");
            bw.write("best individual containing vertexes : " + population.get(0).getVertexeSet()+ "\n");
            bw.write("average : " + avg + "\n");
            bw.write("std : " + std + "\n");
            bw.write("max : " + max + "\n");
        }

        bw.write("\n\n");
        bw.write("----------------------------- 최종 결과 ------------------------------" + "\n");
        DoubleSummaryStatistics statistics = eugenes.stream().mapToDouble(i -> i.fitness).summaryStatistics();
        double avg = statistics.getAverage();
        double max = statistics.getMax();

        double dev = 0;
        double sumOfSqrOfDev = 0;

        for (int i = 0; i < eugenes.size(); i++) {
            dev = (eugenes.get(i).fitness - avg);
            sumOfSqrOfDev += Math.pow(dev, 2);
        }
        double var = sumOfSqrOfDev / statistics.getCount();
        double std = Math.sqrt(var);
        bw.write("best : " + max + "\n");
        bw.write("average : " + avg + "\n");
        bw.write("std : " + std + "\n");
        bw.flush();
    }

    private static void geneticAlgorithm(BufferedWriter bw) throws IOException {
        int generation = 0;
        List<Individual> population = new ArrayList<>();

        for (int i = 0; i < POPULATION_SIZE; i++) {
            population.add(new Individual(createGnome()));
        }

        while (TEST_SIZE-- > 0) {
            List<Individual> newGeneration = new ArrayList<>();
            // 80% 대체
            for (int j = 0; j < POPULATION_SIZE * 0.8; j++) {
                Individual p1 = select(population);
                Individual p2 = select(population);
                Individual child = crossover(p1, p2);
                mutate(child);
                newGeneration.add(child);
            }

            for (int i = 0; i < POPULATION_SIZE * 0.8; i++) {
                population.set(POPULATION_SIZE - 1 - i, newGeneration.get(i));
            }
            Collections.sort(population);
            generation++;
            bw.write("-- gen " + generation + " --" + "\n");
            bw.write("최고 품질 : " + population.get(0).fitness + "\n");
            bw.write("평균 품질 : " + population.stream().mapToInt(i -> i.fitness).average().getAsDouble() + "\n");
        }
        eugenes.add(population.get(0));
        DoubleSummaryStatistics statistics = population.stream().mapToDouble(i -> i.fitness).summaryStatistics();
        double avg = statistics.getAverage();
        double max = statistics.getMax();

        double dev = 0;
        double sumOfSqrOfDev = 0;

        for (int i = 0; i < population.size(); i++) {
            dev = (population.get(i).fitness - avg);
            sumOfSqrOfDev += Math.pow(dev, 2);
        }
        double var = sumOfSqrOfDev / statistics.getCount();
        double std = Math.sqrt(var);
        bw.write("== 최종 결과 ==\n");
        bw.write("best : " + max + "\n");
        bw.write("average : " + avg + "\n");
        bw.write("std : " + std + "\n");
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
        int fitness; // 높을수록 좋음

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

        // 내림차순
        @Override
        public int compareTo(Individual o) {
            return o.fitness - this.fitness;
        }
    }

    public static Individual select(List<Individual> population) {
        int totalWeight = 0;
        for (Individual i : population) {
            totalWeight += i.fitness;
        }

        double randomValue = random.nextDouble() * totalWeight;

        int cumulativeWeight = 0;
        for (int i = 0; i < population.size(); i++) {
            cumulativeWeight += population.get(i).fitness;
            if (randomValue <= cumulativeWeight) return population.get(i);
        }
        return population.get(population.size() - 1);

    }
}
