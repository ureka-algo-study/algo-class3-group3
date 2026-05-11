import java.util.PriorityQueue;

class Solution {
    public int[] solution(String[] operations) {
        int n = 0;
        PriorityQueue<Integer> minHeap = new PriorityQueue<>();
        PriorityQueue<Integer> maxHeap = new PriorityQueue<>((a, b) -> b - a);
        for(String s: operations){
            switch (s.charAt(0)){
                case('I') -> {
                    n++;
                    minHeap.add(Integer.parseInt(s.substring(2)));
                    maxHeap.add(Integer.parseInt(s.substring(2)));
                }
                case('D') ->{
                    if(!minHeap.isEmpty()){
                        //최대값
                        if(s.charAt(2) == '1'){ // maxHeap에서 제거
                            int num = maxHeap.poll();
                            minHeap.remove(num);
                        }
                        //최소값
                        else{ // minHeap에서 제거
                            int num = minHeap.poll();
                            maxHeap.remove(num);
                        }
                    }
                }
            }

        }
        if(minHeap.isEmpty()){
            return new int[]{0, 0};
        }

        return new int[]{maxHeap.peek(), minHeap.peek()};
    }
}