package G05_Kruskal;

import java.util.*;
import java.io.*;

public class G05_ex03_����A0031_���������׽�Ʈ {
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
		System.setIn(new FileInputStream("src/G05_Kruskal/G05_ex03_����A0031_���������׽�Ʈ.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st;

		TC = Integer.parseInt(br.readLine());

		for (int tc = 1; tc <= TC; tc++) {
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());

			parent = new int[N + 1];
			graph = new ArrayList<Node>();	// �׷���

			int a, b, s;
			for (int i = 1; i <= M; i++) {
				st = new StringTokenizer(br.readLine());

				a = Integer.parseInt(st.nextToken());
				b = Integer.parseInt(st.nextToken());
				s = Integer.parseInt(st.nextToken());

				graph.add(new Node(a, b, s));	// ����� ���� �Է�
				graph.add(new Node(b, a, s));
			}
			
			// 01. �������� ����
			Collections.sort(graph);

			st = new StringTokenizer(br.readLine());
			start = Integer.parseInt(st.nextToken());
			end = Integer.parseInt(st.nextToken());

			ans = 1000000000; // > ���� Ȯ�� : ������ �ִ� - �ּ� + 1 ��
			int min = 0;
			int max = 0;

			// 02. ������ > ALL Ȯ��
			for (Node g : graph) {
				min = g.cost;	// * ������ : �ٽ��ڵ�
				
				// parent ���� �ʱ�ȭ
				for (int i = 1; i <= N; i++) {
					parent[i] = i;
				}

				for (Node t : graph) {
					// ���������� ���� ���� ������ �н�
					if (t.cost < min) continue;
					
					// Ʈ���� ����Ǿ� ���� ���� ������ ����
					if (find(t.from) != find(t.to)) {
						union(t.from, t.to);
					}
					
					// ���� ���� ã�� > ã���� break;
					// ������ �ȴٸ� == ������ ���ٸ�
					if (find(start) == find(end)) {
						max = t.cost;	// �������� ������ �Ǿ� ������ �ڵ������� �ִ밪 ����
						ans = Math.min(ans, max - min);	// �������� ���ϴ� �ּҰ�
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
