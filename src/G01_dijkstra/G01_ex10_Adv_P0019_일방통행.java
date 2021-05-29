package G01_dijkstra;

import java.io.*;
import java.util.*;

public class G01_ex10_Adv_P0019_일방통행 {
	static class Node implements Comparable<Node> {
		int t;
		long c;
		int card;

		public Node(int t, long c, int card) {
			this.t = t;
			this.c = c;
			this.card = card;
		}

		@Override
		public int compareTo(Node other) {
			if (this.c < other.c) {
				return -1;
			}
			return 1;
		}
	}

	static final long INF = 30000000000001L;
	static int TC, N, M;
	static char color[];
	static long D[][], ans;
	static ArrayList<Node> graph[];

	@SuppressWarnings("unchecked")
	public static void main(String[] args) throws IOException {
		System.setIn(new FileInputStream("src/G01_dijkstra/G01_ex10_Adv_P0019_일방통행.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st = null;

		TC = Integer.parseInt(br.readLine());

		for (int tc = 1; tc <= TC; tc++) {
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());

			D = new long[4][N + 1];
			graph = new ArrayList[N + 1];
			color = new char[N + 1]; // 지점의 색 정보

			for (int i = 0; i <= N; i++) {
				graph[i] = new ArrayList<Node>();
				for (int j = 0; j <= 3; j++) {
					D[j][i] = INF;
				}
			}

			st = new StringTokenizer(br.readLine());
			color = st.nextToken().toCharArray(); // * 암기할 것

			int from, to;
			long cost;
			for (int i = 1; i <= M; i++) {
				st = new StringTokenizer(br.readLine());

				from = Integer.parseInt(st.nextToken());
				to = Integer.parseInt(st.nextToken());
				cost = Long.parseLong(st.nextToken());

				// card에 해당하는 부분은 0으로 고정
				graph[from].add(new Node(to, cost, 0));
			}

			dijkstra(D, graph, 3);

			ans = INF;
			for (int i = 0; i <= 3; i++) {
				ans = Math.min(ans, D[i][N]);
			}

			if (ans == INF) {
				bw.write("#" + tc + " " + -1 + "\n");
			} else {
				bw.write("#" + tc + " " + ans + "\n");
			}

		}

		br.close();
		bw.flush();
		bw.close();

	}

	private static void dijkstra(long[][] d, ArrayList<Node>[] gra, int start_card) {
		PriorityQueue<Node> pq = new PriorityQueue<Node>();
		 // 첫 출발 시에는 통행권을 모두 들고 있고 시작 거리는 0
		d[start_card][1] = 0;
		pq.offer(new Node(1, 0, start_card));
		
		while(!pq.isEmpty()) {
			Node now = pq.poll();
			
			int nowNode = now.t;
			long nowDis = now.c;
			int nowCard = now.card;
			
			char nowColor = color[nowNode - 1];
			int newCard = nowCard;
			
			/*
			 * if(nowEnd == N){ // 이미 도착한 지점이 종료점이며 더이상 진행하지 않음 break;
			 */
			
			// 원래 이 길로 오는 루트가 있어 현재까지 확정한 거리 d[] 보다 새로운 거리가 더 크면 PASS
			if(d[nowCard][nowNode] < nowDis) {
				continue;
			}
			
			if(nowColor == 'R' && (nowCard == 1 || nowCard == 3)) {
				newCard = nowCard - 1;
			}else if(nowColor == 'B' && (nowCard == 2 || nowCard == 3)) {
				newCard = nowCard - 2;
			}else if(nowColor == 'G') {
				newCard = nowCard;
			}else if(nowColor == 'W') {
				newCard = 3;
			}else {
				continue;
			}
			
			for(Node next : gra[nowNode]) {
				int nextNode = next.t;
				long nextDis = next.c;
				// * 주의 : next의 card는 위에서 구해놓았다
				
				if(d[newCard][nextNode] > d[nowCard][nowNode] + nextDis) {
					d[newCard][nextNode] = d[nowCard][nowNode] + nextDis;
					pq.offer(new Node(nextNode, d[newCard][nextNode], newCard));
				}
			}
			
		}		
	}
}

/*
 * 3 6 9 RBWBGB 1 2 3 2 5 1 3 1 2 2 3 7 2 4 5 5 4 1 4 3 1 4 6 10 3 4 4 3 2 RBB 1
 * 2 10 2 3 10 5 5 BRWBG 4 5 3 1 2 1 3 2 6 2 3 5 2 4 2
 */
