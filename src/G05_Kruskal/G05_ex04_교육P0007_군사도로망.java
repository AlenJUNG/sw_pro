package G05_Kruskal;

/* Q) 도시의 수, 지금 존재하는 M개의 도로들과 각 도로를 제거하는 비용, 도로를 건설하는 것이 가능한 K개의 도시의 쌍과 
 *    각 도로의 건설 비용을 받아서 위의 목적을 달성하는 비용의 최소값을 구하는 프로그램 작성
 * Input) N, M, K
 *        양 정점 제공 및 도로 제거 비용 (M개)
 *        양 정점 제공 및 도로 건설 비용 (K개)
 */

import java.util.*;
import java.io.*;

// Tip : 문제를 넓게 보자
public class G05_ex04_교육P0007_군사도로망 {
	static class Node implements Comparable<Node> {
		int from, to, cost;

		public Node(int from, int to, int cost) {
			this.from = from;
			this.to = to;
			this.cost = cost;
		}

		@Override
		public int compareTo(Node other) {
			if (this.cost < other.cost) {
				return -1;
			}
			return 1;
		}
	}

	static int TC, N, M, K;
	static int parent[];
	static long ans;
	static PriorityQueue<Node> pq;

	public static void main(String[] args) throws IOException {
		System.setIn(new FileInputStream("src/G05_Kruskal/G05_ex04_교육P0007_군사도로망.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st;

		TC = Integer.parseInt(br.readLine());

		for (int tc = 1; tc <= TC; tc++) {

			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			K = Integer.parseInt(st.nextToken());
			
			// 1. 조상값 초기화
			parent = new int[N + 1];
			for (int i = 1; i <= N; i++) {
				parent[i] = i;
			}

			pq = new PriorityQueue<>();
			ans = 0;

			int a, b, x;
			for (int i = 1; i <= M; i++) {
				st = new StringTokenizer(br.readLine());
				a = Integer.parseInt(st.nextToken());
				b = Integer.parseInt(st.nextToken());
				x = Integer.parseInt(st.nextToken());

				pq.offer(new Node(a, b, -x));
				ans += x; 	// * 전체 도로 제거 가능 시, 총합 구하기 > key point
			}

			for (int i = 1; i <= K; i++) {
				st = new StringTokenizer(br.readLine());
				a = Integer.parseInt(st.nextToken());
				b = Integer.parseInt(st.nextToken());
				x = Integer.parseInt(st.nextToken());

				pq.offer(new Node(a, b, x));

			}

			while (!pq.isEmpty()) {
				Node node = pq.poll();
				
				// 조상이 같으면 도로를 잇지 않고 continue
				if (find(node.to) == find(node.from)) {
					continue;
				// 조상이 다르면 ans에 도로 건설 값을 넣어준다 = MST
				// ** 도로 제거 비용은 조상이 같다면 자동적으로 계산이 된다.
				} else {
					union(node.to, node.from);
					ans += node.cost;
				}

			}

			bw.write("#" + tc + " " + ans + "\n");

		}

		br.close();
		bw.flush();
		bw.close();

	}

	private static void union(int a, int b) {
		a = find(a);
		b = find(b);

		if (a > b) {
			parent[a] = parent[b];
		} else {
			parent[b] = parent[a];
		}

	}

	private static int find(int x) {
		if (x == parent[x]) {
			return x;
		}
		return parent[x] = find(parent[x]);
	}

}
