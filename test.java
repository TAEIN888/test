import java.util.*;

public class test {
    public static void main(String[] args) {

        String[][] a = {{"ICN", "JFK"}, {"HND", "IAD"}, {"JFK", "HND"}};
        String[][] b = {{"ICN", "SFO"}, {"ICN", "SFO"}, {"ICN", "ATL"}, {"SFO", "ATL"}, {"ATL", "ICN"}, {"ATL","SFO"}};
        String[] answer =solution(b);

        for (String s : answer) {
            System.out.print(s + " ");
        }
    }

    private static class Airport {
        String name;

        LinkedList<Airport> adList = new LinkedList<>();

        public Airport(String name) {
            this.name = name;
        }

        public void addEdge(Airport v) {
            this.adList.add(v);
        }
    }

    static String[] solution(String[][] tickets) {
        String[] answer = {};

        List<String> answerList = new ArrayList<String>();
        Set<String> airportList = new HashSet<String>();

        for (int i=0; i<tickets.length; i++) {
            for (int j=0; j<tickets[i].length; j++) {
                airportList.add(tickets[i][j]);
            }
        }

        List<Airport> airports = new LinkedList<Airport>();

        for (String airport : airportList) {
            airports.add(new Airport(airport));
        }

        for (int i=0; i<tickets.length; i++) {
            for (Airport ap : airports) {
                if (ap.name.equals(tickets[i][0])) {
                    for (Airport ap2 : airports) {
                        if (ap2.name.equals(tickets[i][1])) {
                            ap.addEdge(ap2);
                            break;
                        }
                    }
                }
            }
        }

        Queue<Airport> queue = new LinkedList<Airport>();
        answerList.add(airports.get(0).name);
        queue.offer(airports.get(0));

        while (answerList.size() <= tickets.length) {
            Airport current = queue.poll();
            LinkedList<Airport> sortedList = new LinkedList<>();
            Collections.sort(current.adList, (o1, o2) -> {
                return o1.name.compareTo(o2.name);
            });

            queue.offer(current.adList.get(0));
            answerList.add(current.adList.get(0).name);
            current.adList.remove(0);
        }

        answer = answerList.toArray(new String[answerList.size()]);
        return answer;
    }


    /**
     * 가장 먼 노드
     *
     *
     class Solution {

     private static class Vertex {
     int num;
     int distance;
     boolean visited = false;

     LinkedList<Vertex> adList = new LinkedList<>();

     public Vertex(int num, int distance) {
     this.num = num;
     this.distance = distance;
     }

     public void addEdge(Vertex v) {
     this.adList.add(v);
     }
     }

     public int solution(int n, int[][] edge) {
     int answer = 0;
     List<Vertex> verList = new LinkedList<Vertex>();

     for (int i=1; i<=n; i++) {
     Vertex a = new Vertex(i, 0);
     verList.add(a);
     }

     for (int i=0; i<edge.length; i++) {
     verList.get(edge[i][0] -1).addEdge(verList.get(edge[i][1] -1));
     verList.get(edge[i][1] -1).addEdge(verList.get(edge[i][0] -1));
     }
     Queue<Vertex> queue = new LinkedList<>();
     verList.get(0).visited = true;
     queue.offer(verList.get(0));

     while (!queue.isEmpty()) {
     Vertex current = queue.poll();

     for (Vertex v : current.adList) {
     if (!v.visited) {
     queue.offer(v);
     v.visited = true; // 방문 완료 처리
     v.distance = current.distance + 1;
     }
     }
     }
     int max = 0;
     for (Vertex v : verList) {
     if (v.distance > max) {
     max = v.distance;
     }
     }

     for (Vertex v : verList) {
     if (v.distance == max) {
     answer++;
     }
     }

     return answer;
     }
     }
     *
     */
}
