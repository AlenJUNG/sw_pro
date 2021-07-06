package G05_Kruskal;

import java.util.*;
import java.io.*;

/*
 * �Ͻ� : 2021.07.06
 * Ƚ�� : 1
 */

public class G05_ex01_����P0007_��ӵ��ΰǼ� {
	static class Node implements Comparable<Node> {
		int from, to, cost;

		public Node(int from, int to, int cost) {
			this.from = from;
			this.to = to;
			this.cost = cost;
		}

		@Override
		public int compareTo(Node other) {
			if(this.cost < other.cost) {
				return -1;	// -1�� �ٲ��� ����
			}
			return 1;	// 1�� �ٲ�
		}
	}

	static PriorityQueue<Node> pq;
	static int TC, N, M, parent[];
	static long ans;

	public static void main(String[] args) throws IOException {
		System.setIn(new FileInputStream("src/G05_Kruskal/G05_ex01_����P0007_��ӵ��ΰǼ�.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st = null;
				
		TC = Integer.parseInt(br.readLine());
		
		double T1 = System.currentTimeMillis();

		for (int tc = 1; tc <= TC; tc++) {
			N = Integer.parseInt(br.readLine());
			M = Integer.parseInt(br.readLine());
			
			// parent �迭 �ʱ�ȭ
			parent = new int[N + 1];
			for (int i = 1; i <= N; i++) {
				parent[i] = i;
			}
			
			pq = new PriorityQueue<>();
			
			// �ܹ��� or ����� �Ǵ� ����
			int a, b, c;
			for (int i = 1; i <= M; i++) {
				st = new StringTokenizer(br.readLine());
				a = Integer.parseInt(st.nextToken());
				b = Integer.parseInt(st.nextToken());
				c = Integer.parseInt(st.nextToken());
				pq.offer(new Node(a, b, c));
			}

			ans = 0;
			// ũ�罺Į �˰���
			while (!pq.isEmpty()) {
				Node now = pq.poll();

				int s = now.from;
				int e = now.to;
				int v = now.cost;

				if (find(s) == find(e)) {
					continue;
				} else {
					union(s, e);
					ans += v;
				}
			}

			bw.write("#" + tc + " " + ans + "\n");
			
		}
		double T2 = System.currentTimeMillis();	
//		System.out.println((T2 - T1)/1000);	// ����ð� �ʷ� ��ȯ
		
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
