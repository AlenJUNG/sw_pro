package G01_dijkstra;

import java.io.*;
import java.util.*;

public class G01_ex05_Adv_100_1753_�ִܰ��_��2 {
	static class Node implements Comparable<Node> {
		int idx, dis;

		public Node(int idx, int dis) {
			this.idx = idx;
			this.dis = dis;
		}

		public int getIdx() {
			return this.idx;
		}

		public int getDis() {
			return this.dis;
		}

		@Override
		public int compareTo(Node other) {
			if (this.dis < other.dis) {
				return -1;
			}
			return 1;
		}
	}
	
	static final int INF = 100000000;
	static int V, E, S;
	static int D[];
	static ArrayList<ArrayList<Node>> graph = new ArrayList<ArrayList<Node>>();
	
	public static void main(String[] args) throws IOException{
		System.setIn(new FileInputStream("src/G01_dijkstra/G01_ex05_Adv_100_1753_�ִܰ��_��.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st = new StringTokenizer(br.readLine());

		V = Integer.parseInt(st.nextToken());
		E = Integer.parseInt(st.nextToken());
		S = Integer.parseInt(br.readLine());

		D = new int[V + 1];
		Arrays.fill(D, INF);
		
		// �迭 ����Ʈ ���� ���� �� �����ϱ�
		for (int i = 0; i <= V; i++) {
			graph.add(new ArrayList<Node>());
		}

		for (int i = 1; i <= E; i++) {
			st = new StringTokenizer(br.readLine());
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			int value = Integer.parseInt(st.nextToken());
			
			graph.get(from).add(new Node(to, value));
		}

		dijkstra(S, graph, D);

		StringBuilder sb = new StringBuilder();
		for (int i = 1; i <= V; i++) {
			if (D[i] == INF) {
				sb.append("INF" + "\n");
			} else {
				sb.append(D[i] + "\n");
			}
		}

		bw.write(sb.toString());

		bw.close();
		br.close();
	}

	private static void dijkstra(int start, ArrayList<ArrayList<Node>> gra, int[] d) {
		PriorityQueue<Node> pq = new PriorityQueue<>();
		pq.offer(new Node(start, 0));
		d[start] = 0;
		boolean check[] = new boolean[V+1];

		while (!pq.isEmpty()) {
			Node now = pq.poll();
			
			if (check[now.idx]){	// �湮�ߴ� ���� continue
				continue;
			}
			check[now.idx] = true;	// �̹湮 ���� �׸��� Ž���� �ű� ������ true ó��

//			if (d[now_idx] < now_dis) {
//				continue;
//			}
			
			// now <> next �� ��������
			for(Node next : gra.get(now.idx)){		// ���� ��� ���� ��ü Ž�� Node next : gra[now.idx]
				if (d[next.idx] > now.dis + next.dis){	// 
					d[next.idx] = now.dis + next.dis;
					pq.offer(new Node(next.idx, d[next.idx]));
				}
			}
			
//			for (int toIdx = 0; toIdx < gra.get(now_idx).size(); toIdx++) {
//				int temp = d[now_idx] + gra.get(now_idx).get(toIdx).getDis();
//				if (d[gra.get(now_idx).get(toIdx).getIdx()] > temp) {
//					d[gra.get(now_idx).get(toIdx).getIdx()] = temp;
//					pq.offer(new Node(gra.get(now_idx).get(toIdx).getIdx(), temp));
//				}
//			}
			
		}
		
	}

}