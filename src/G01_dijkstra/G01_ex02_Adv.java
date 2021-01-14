package G01_dijkstra;

import java.util.*;
import java.io.*;

public class G01_ex02_Adv {
	static class Node implements Comparable<Node> {
		int idx;
		int dis;

		public Node(int idx, int dis) {
			this.idx = idx;
			this.dis = dis;
		}

		public int getIdx() {
			return this.idx;
		}

		public int getDistance() {
			return this.dis;
		}

		// 거리(비용)가 짧은 것이 높은 우선순위를 가지도록 설정
		@Override
		public int compareTo(Node other) {
			if (this.dis < other.dis) {
				return -1;
			}
			return 1;
		}

	}

	static final int INF = (int) 1e9; // 무한을 의미하는 값으로 10억을 설정
	// 노드의 개수(N), 간선의 개수(M), 시작 노드 번호(Start)
	// 노드의 개수는 최대 100,000개라고 가정
	static int N, M, start;
	// Array 생성 01 : 각 노드에 연결되어 있는 노드에 대한 정보를 담는 배열
	static ArrayList<ArrayList<Node>> graph = new ArrayList<ArrayList<Node>>();
	static int distance[];

	public static void main(String[] args) throws IOException {
		System.setIn(new FileInputStream("src/G01_dijkstra/G01_ex02_Adv.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		start = Integer.parseInt(st.nextToken());

		for (int i = 0; i <= N; i++) {
			graph.add(new ArrayList<Node>());
		}

		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			int value = Integer.parseInt(st.nextToken());

			graph.get(from).add(new Node(to, value));
		}

		distance = new int[N + 1];

		Arrays.fill(distance, INF);

		dijkstra(start, graph, distance);

		// 모든 노드로 가기 위한 최단 거리를 출력
		for (int i = 1; i <= N; i++) {
			// 도달할 수 없는 경우, 무한(INFINITY)이라고 출력
			if (distance[i] == INF) {
				System.out.println("INFINITY");
			}
			// 도달할 수 있는 경우 거리를 출력
			else {
				System.out.printf("start node(%d) to node(%d) = %d\n", start, i, distance[i]);
			}
		}

	}

	private static void dijkstra(int start, ArrayList<ArrayList<Node>> gra, int d[]) {
		PriorityQueue<Node> pq = new PriorityQueue<Node>();
		// 시작 노드로 가기 위한 최단 경로는 0으로 설정하여, 큐에 삽입
		pq.offer(new Node(start, 0));
		distance[start] = 0;

		while (!pq.isEmpty()) { // 큐가 비어있지 않다면
			// 가장 최단 거리가 짧은 노드에 대한 정보 꺼내기
			Node node = pq.poll();
			int now_idx = node.getIdx(); // 현재 노드
			int now_dis = node.getDistance(); // 현재 노드까지의 비용
			// 현재 노드가 이미 처리된 적이 있는 노드라면 무시
			if (d[now_idx] < now_dis) {
				continue;
			}
			// 현재 노드와 연결된 다른 인접한 노드들을 확인
			for (int toIdx = 0; toIdx < gra.get(now_idx).size(); toIdx++) {
				int temp = d[now_idx] + gra.get(now_idx).get(toIdx).getDistance();
				// 현재 노드를 거쳐서, 다른 노드로 이동하는 거리가 더 짧은 경우
				if (temp < d[gra.get(now_idx).get(toIdx).getIdx()]) {
					d[gra.get(now_idx).get(toIdx).getIdx()] = temp;
					pq.offer(new Node(gra.get(now_idx).get(toIdx).getIdx(), temp));
				}
			}
		}
	}

}
