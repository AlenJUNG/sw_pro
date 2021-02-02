package G05_Kruskal;

import java.util.*;
import java.io.*;

public class G05_ex03_사전A0031_자율주행테스트 {
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
			} else if (this.cost == other.cost) {
				return this.from - other.from;
			}
			return 1;
		}
	}

	static int TC, N, M, ans, start, end;
	static ArrayList<Node> graph;
	static int parent[];

	public static void main(String[] args) throws IOException {
		System.setIn(new FileInputStream("src/G05_Kruskal/G05_ex03_사전A0031_자율주행테스트.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st;

		TC = Integer.parseInt(br.readLine());

		for (int tc = 1; tc <= TC; tc++) {
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());

			parent = new int[N + 1];
			graph = new ArrayList<Node>();	// 그래프

			int a, b, s;
			for (int i = 1; i <= M; i++) {
				st = new StringTokenizer(br.readLine());

				a = Integer.parseInt(st.nextToken());
				b = Integer.parseInt(st.nextToken());
				s = Integer.parseInt(st.nextToken());

				graph.add(new Node(a, b, s));	// 양방향 간선 입력
				graph.add(new Node(b, a, s));
			}
			
			// 01. 오름차순 정렬
			Collections.sort(graph);

			st = new StringTokenizer(br.readLine());
			start = Integer.parseInt(st.nextToken());
			end = Integer.parseInt(st.nextToken());

			ans = 1000000000; // > 문제 확인 : 가능한 최대 - 최소 + 1 값
			int min = 0;
			int max = 0;

			// 02. 기준점 > ALL 확인
			for (Node g : graph) {
				min = g.cost;	// * 기준점 : 핵심코드
				
				// parent 정보 초기화
				for (int i = 1; i <= N; i++) {
					parent[i] = i;
				}

				for (Node t : graph) {
					// 기준점보다 작은 값이 나오면 패스
					if (t.cost < min) continue;
					
					// 트리로 연결되어 있지 않은 간선은 연결
					if (find(t.from) != find(t.to)) {
						union(t.from, t.to);
					}
					
					// 문제 정답 찾기 > 찾으면 break;
					// 연결이 된다면 == 조상이 같다면
					if (find(start) == find(end)) {
						max = t.cost;	// 오름차순 정렬이 되어 있으니 자동적으로 최대값 나옴
						ans = Math.min(ans, max - min);	// 문제에서 원하는 최소값
						break;
					}
				}

			}

			bw.write("#" + tc + " " + ans + "\n");

		}

		br.close();
		bw.flush();
		br.close();
	}

	private static void union(int a, int b) {
		a = find(a);
		b = find(b);

//		if( a != b) {
//			parent[b] = a;
//		}

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
