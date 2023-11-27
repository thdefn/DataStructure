package 기출.카카오;

import java.util.*;

public class 그래프 {
    public static void main(String[] args) {
        int[][] edges = {
                {2,3}, {4,3}, {1,1}, {2,1}
        };
        System.out.println(Arrays.toString(solution(edges)));
    }

    public static int[] solution(int[][] edges) {
        Map<Integer, LinkedList<Integer>> edgeSet = new HashMap<>();

        for (int[] edge : edges) {
            LinkedList<Integer> list = edgeSet.getOrDefault(edge[0], new LinkedList<>());
            list.add(edge[1]);
            edgeSet.put(edge[0], list);
            edgeSet.put(edge[1], edgeSet.getOrDefault(edge[1], new LinkedList<>()));
        }


        int[][] edgeCount = new int[edgeSet.size() + 2][3];
        for (int[] edge : edges) {
            // 나가는 간선 수 업데이트
            edgeCount[edge[0]][0] = edge[0];
            edgeCount[edge[0]][1]++;
            // 들어오는 간선 수 업데이트
            edgeCount[edge[1]][0] = edge[1];
            edgeCount[edge[1]][2]++;
        }

        Arrays.sort(edgeCount, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                if (o2[1] == o1[1])
                    // 들어오는 간선이 더 적은 것
                    return o1[2] - o2[2];
                // 나가는 간선이 더 많은 것
                return o2[1] - o1[1];
            }
        });

        int[] answer = new int[4];
        int coreNode = edgeCount[0][0];
        answer[0] = coreNode;
        for (int fromNode : edgeSet.get(coreNode)) {
            boolean[] visited = new boolean[edgeSet.size() + 2];
            int toNode = fromNode; // 가야할 노드
            while (edgeSet.get(toNode).size() != 0 && !visited[toNode]) {
                visited[toNode] = true;
                // 다음으로 갈 노드에 대한 처리
                toNode = edgeSet.get(toNode).get(0);
            }

            if (visited[toNode]) {
                // 순환 발생
                if (edgeSet.get(toNode).size() == 1) {
                    answer[1]++;
                } else answer[3]++;
            } else {
                answer[2]++;
            }
        }
        return answer;
    }
}
