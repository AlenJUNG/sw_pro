package G05_Kruskal;

import java.io.*;
import java.util.*;

/*
 * Update : 210708, try : 1
 * MST �⺻ ����
 */

public class G05_ex00_100_1922_��Ʈ��ũ����_���� {
	static class Node implements Comparable<Node> {
		int node, cost;

		public Node(int node, int cost) {
			this.node = node;
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

	static final int INF = 10000 + 1;
	static int visitCnt;
	static int dis[][];
	static long ans;
	static int N, M;
	static ArrayList<Node> pc[];

	@SuppressWarnings("unchecked")
	public static void main(String[] args) throws IOException {
		System.setIn(new FileInputStream("src/G05_Kruskal/G05_ex00_100_1922_��Ʈ��ũ����.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st = null;

//		double T1 = System.currentTimeMillis();

		N = Integer.parseInt(br.readLine());
		M = Integer.parseInt(br.readLine());

		pc = new ArrayList[N + 1];
		for (int i = 0; i <= N; i++) {
			pc[i] = new ArrayList<Node>();
		}

		dis = new int[N + 1][N + 1];		

		// 1. dis �迭 �ʱ�ȭ
		for (int i = 1; i <= N; i++) {
			for (int j = 1; j <= N; j++) {
				dis[i][j] = dis[j][i] = INF;
			}
		}

		int f, t, v;
		for (int i = 1; i <= M; i++) {
			st = new StringTokenizer(br.readLine());
			f = Integer.parseInt(st.nextToken());
			t = Integer.parseInt(st.nextToken());
			v = Integer.parseInt(st.nextToken());

			pc[f].add(new Node(t, v));
			pc[t].add(new Node(f, v));
		}
		
		visitCnt = 0;
		ans = 0;
		prim();

		bw.write(ans + "\n");
//		double T2 = System.currentTimeMillis();
//		System.out.println((T2 - T1)/1000);

		br.close();
		bw.flush();
		bw.close();

	}
	// ���ͽ�Ʈ�� �˰���� �����
	private static void prim() {
		PriorityQueue<Node> pq = new PriorityQueue<>();		
		boolean visit[] = new boolean[N + 1];	// �湮���� üũ
		pq.offer(new Node(1, 0));	// �ʱⰪ pc node 1���� üũ * ��� �����ϵ� �������?

		while (!pq.isEmpty()) {
			// �� N - 1�� �ƴ϶� N���� Ȯ��
			// ���������� �ƴ϶� ��� ����
			if (visitCnt == N) {
				break;
			}
			
			Node now = pq.poll();
			
			// ��湮 ���� continue
			if (visit[now.node]) {
				continue;
			}
			// �̹湮 ���� check
			visit[now.node] = true;
			
			ans += now.cost;
			visitCnt++;
			
			for (Node next : pc[now.node]) {
				if (!visit[next.node] && dis[now.node][next.node] > next.cost) {
					dis[now.node][next.node] = next.cost;
					pq.offer(new Node(next.node, dis[now.node][next.node]));
				}
			}
		}
	}
}
