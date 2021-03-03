package G05_Kruskal;

/* https://www.acmicpc.net/problem/1647
 * 마을은 N개의 집과 그 집들을 연결하는 M개의 길로 이루어져 있다. 길은 어느 방향으로든지 다닐 수 있는 편리한 길이다. 그리고 각 길마다 길을 유지하는데 드는 유지비가 있다. 
 * 마을의 이장은 마을을 두 개의 분리된 마을로 분할할 계획을 가지고 있다. 
 * 마을을 분할할 때는 각 분리된 마을 안에 집들이 서로 연결되도록 분할해야 한다. 
 * 각 분리된 마을 안에 있는 임의의 두 집 사이에 경로가 항상 존재해야 한다는 뜻이다. 
 * 마을에는 집이 하나 이상 있어야 한다.
 * 그렇게 마을의 이장은 계획을 세우다가 마을 안에 길이 너무 많다는 생각을 하게 되었다. 
 * 일단 분리된 두 마을 사이에 있는 길들은 필요가 없으므로 없앨 수 있다. 
 * 그리고 각 분리된 마을 안에서도 임의의 두 집 사이에 경로가 항상 존재하게 하면서 길을 더 없앨 수 있다. 
 * 마을의 이장은 위 조건을 만족하도록 길들을 모두 없애고 나머지 길의 유지비의 합을 최소로 하고 싶다.
 */

import java.util.*;
import java.io.*;

public class G05_ex02_100_1647_도시분할계획 {
	static int N, M, max; // N은 10만 이하, M은 100만 이하
	static long ans;
	static int parent[];
	static PriorityQueue<Node> pq;

	static class Node implements Comparable<Node> {
		int f, t, c;

		public Node(int f, int t, int c) {
			this.f = f;
			this.t = t;
			this.c = c;
		}

		@Override
		public int compareTo(Node other) {
			if (this.c < other.c) {
				return -1;
			}
			return 1;
		}
	}

	public static void main(String[] args) throws IOException {
		System.setIn(new FileInputStream("src/G05_Kruskal/G05_ex02_100_1647_도시분할계획.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		parent = new int[N + 1];
		for (int i = 1; i <= N; i++) {
			parent[i] = i;
		}

		pq = new PriorityQueue<Node>();
		int from, to, cost;
		for (int i = 1; i <= M; i++) {
			st = new StringTokenizer(br.readLine());
			from = Integer.parseInt(st.nextToken());
			to = Integer.parseInt(st.nextToken());
			cost = Integer.parseInt(st.nextToken());

			pq.offer(new Node(from, to, cost));
			pq.offer(new Node(to, from, cost));
		}

		ans = 0;
		max = 0;
		while (!pq.isEmpty()) {
			Node now = pq.poll();

			if (find(now.f) == find(now.t)) {
				continue;
			} else {
				union(now.f, now.t);
				ans += now.c;
				max = now.c; // 핵심 아이디어 : pq 정렬 후 마지막 now.c 값은 자동적으로 Max cost 값이 된다
			}
		}
		
		// 핵심 아이디어 : 전체 그래프에서 2개의 MST를 만들어야 함
		// 최소한의 비용으로 2개의 신장 트리로 분할
		// > 크루스칼 알고리즘 MST - 가장 비용이 큰 간선 제거
		bw.write(ans - max + "\n");

		br.close();
		bw.flush();
		bw.close();
	}

	private static void union(int a, int b) {
		a = find(a);
		b = find(b);

		if (a > b) {
			parent[a] = b;
		} else {
			parent[b] = a;
		}

	}

	private static int find(int x) {
		if (x == parent[x]) {
			return x;
		}
		return parent[x] = find(parent[x]);
	}

}
