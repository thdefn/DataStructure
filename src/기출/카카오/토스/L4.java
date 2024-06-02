package 기출.카카오.토스;

import java.util.HashMap;
import java.util.Map;

public class L4 {
    public static void main(String[] args) {
        System.out.println();
    }

    public int[][] solution(int servers, boolean sticky, int[] requests) {
        int[][] answer = new int[servers][requests.length];
        Map<Integer, Integer> requestServerMap = new HashMap<>();
        int count = 0;
        int[] indexes = new int[servers];
        if(sticky){
            for (int i = 0; i < requests.length; i++) {
                if(!requestServerMap.containsKey(requests[i])){
                    requestServerMap.put(requests[i], (count % servers) + 1);
                    count++;
                }
                answer[requestServerMap.get(requests[i])-1][indexes[requestServerMap.get(requests[i])-1]++] = requests[i];
            }
        }
        return answer;
    }
}
