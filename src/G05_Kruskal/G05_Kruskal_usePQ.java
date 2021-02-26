package G05_Kruskal;

import java.io.*;
import java.util.*;

// N : 노드 수, M : 간선 수 > MST cost를 구하라

public class G05_Kruskal_usePQ {
	static class Node implements Comparable<Node> {
		int from, to, dis;

		public Node(int from, int to, int dis) {
			this.from = from;
			this.to = to;
			this.dis = dis;
		}

		@Override
		public int compareTo(Node other) {
			if (this.dis < other.dis) {
				return -1;
			}
			return 1;
		}

	}

	static int N, M, ans;
	static int parent[];
	static PriorityQueue<Node> pq;

	public static void main(String[] args) throws IOException {
		System.setIn(new FileInputStream("src/G05_Kruskal/G05_Kruskal.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		parent = new int[N + 1];

		pq = new PriorityQueue<>();
		
		int from, to, distance;
		for (int i = 1; i <= M; i++) {
			st = new StringTokenizer(br.readLine());
			from = Integer.parseInt(st.nextToken());
			to = Integer.parseInt(st.nextToken());
			distance = Integer.parseInt(st.nextToken());
			
			pq.offer(new Node(from, to, distance));
		}
		
		/**************** 입력부 끝 ****************/
		
		// SPTE01. 조상배열에 노드 자신의 값을 모두 넣어줌
		for (int i = 1; i <= N; i++) {
			parent[i] = i;
		}

		ans = 0;
		
		// SPTE02. 조상배열에 노드 자신의 값을 모두 넣어줌
		// PQ를 쓰면 오름차순 정렬 메소드 별도 필요 없음 ex) Collections.sort
		while (!pq.isEmpty()) {
			Node now = pq.poll();
			
			// 항상 예외조건을 넣어주는 습관을 지닐 것
			if (findRoot(now.from) == findRoot(now.to)) {
				continue;
			// * 조상이 같지 않으면 간선을 이어준다
			} else {
				union(now.from, now.to);
				ans += now.dis; // 문제에서 원하는 cost 값 구하기
			}
		}
		
		bw.write(ans + "\n");

		br.close();
		bw.flush();
		bw.close();
	}

	private static void union(int a, int b) {
		a = findRoot(a);
		b = findRoot(b);

		if (a > b) {
			parent[a] = parent[b];
		} else {
			parent[b] = parent[a];
		}
	}

	private static int findRoot(int x) {
		if (x == parent[x]) {
			return x;
		}
		return parent[x] = findRoot(parent[x]);
	}

}
