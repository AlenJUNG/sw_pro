package G01_dijkstra;

import java.io.*;
import java.util.*;

public class G01_ex10_Answer_P0019_일방통행 {
	static class Node implements Comparable<Node> {
		int end;
		long dis;
		int card;

		public Node(int end, long dis, int card) {
			this.end = end;
			this.dis = dis;
			this.card = card;
		}

		@Override
		public int compareTo(Node other) {
			if (this.dis < other.dis) {
				return -1;
			}
			return 1;
		}
	}

	static final long INF = 30000000000001L;
	static int TC, N, M, type[];
	static long distance[][], ans;
	static ArrayList<Node> nodeList[];
	static PriorityQueue<Node> pq;
	static char color[];

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

			distance = new long[4][N + 1];
			nodeList = new ArrayList[N + 1];
			ans = INF;

			pq = new PriorityQueue<Node>();
			color = new char[N + 1]; // 지점의 색 정보

			st = new StringTokenizer(br.readLine());
			color = st.nextToken().toCharArray(); // * 암기할 것

			for (int i = 0; i <= N; i++) {
				nodeList[i] = new ArrayList<Node>();
				for (int n = 0; n <= 3; n++) {
					distance[n][i] = INF;
				}
			}

			for (int i = 1; i <= M; i++) {
				st = new StringTokenizer(br.readLine());
				int start = Integer.parseInt(st.nextToken());
				int end = Integer.parseInt(st.nextToken());
				long d = Long.parseLong(st.nextToken());

				nodeList[start].add(new Node(end, d, 0));
			}

			pq.clear();
			pq.add(new Node(1, 0, 3)); // 첫 출발 시에는 통행권을 모두 들고 있음
			// 첫출발시 거리
			distance[3][1] = 0;

			dijkstra();

			for (int i = 0; i < 4; i++) {
				ans = Math.min(ans, distance[i][N]);
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

	private static void dijkstra() {
		while (!pq.isEmpty()) {

			Node node = pq.poll();
			
			int nowEnd = node.end; // 현재 경로 도착지점
			long nowDis = node.dis; // 현재까지 이동한 거리
			int nowCard = node.card; // 현재 소지하고 있는 통행권
			
			char nowColor = color[nowEnd - 1]; // 현재지점의 색정보
			int newCard = nowCard; // 지점을 통과하면서 변하게될 통행권 정보

			/*
			 * if(nowEnd == N){ // 이미 도착한 지점이 종료점이며 더이상 진행하지 않음 break;
			 */
			
			// 1. 현재 보려는 거리가 기존 확정 거리보다 크다면 PASS
			if (distance[nowCard][nowEnd] < nowDis) {
				continue;
			}
			
			// 2. 그렇지 않다면 알아보자
			if (nowColor == 'R' && (nowCard == 1 || nowCard == 3)) {
				newCard = nowCard - 1;
			} else if (nowColor == 'B' && (nowCard == 2 || nowCard == 3)) {
				newCard = nowCard - 2;
			} else if (nowColor == 'G') {
				newCard = 3;
			} else {
				continue;
			}
			
			Node nextNode;
			int next;
			long nextDis;

			for (int i = 0; i < nodeList[nowEnd].size(); i++) {
				nextNode = nodeList[nowEnd].get(i);
				next = nextNode.end;
				nextDis = nextNode.dis;

				// next에 해당하는 지점
				if (distance[newCard][next] > distance[nowCard][nowEnd] + nextDis) {
					distance[newCard][next] = distance[nowCard][nowEnd] + nextDis;
					pq.offer(new Node(next, distance[newCard][next], newCard));
				}
			}

		}

	}

}

/*
 * 3 6 9 RBWBGB 1 2 3 2 5 1 3 1 2 2 3 7 2 4 5 5 4 1 4 3 1 4 6 10 3 4 4 3 2 RBB 1
 * 2 10 2 3 10 5 5 BRWBG 4 5 3 1 2 1 3 2 6 2 3 5 2 4 2
 */
