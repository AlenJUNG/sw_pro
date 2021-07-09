package G05_Kruskal;

import java.util.*;
import java.io.*;

/*
 * 일시 : 2021.07.07
 * 시도 : 2
 * 중요) 간선의 방향성이 있는 경우, 트리의 특징
 * 1. 노드로 들어오는 in-degree가 root 노드는 0이며 나머지는 모두 1
 * 2. 노드에서 나가는 out-degree가 leaf 노드에서 0이며 나머지는 1 이상
 */

public class G05_ex13_모의P0001_잠자는사이에 {
	static int TC, N, parent[], indegree[], ansS, ansE;

	static class Node {
		int start, end;

		public Node(int start, int end) {
			this.start = start;
			this.end = end;
		}
	}

	static ArrayList<Node> al;

	public static void main(String[] args) throws IOException {
		System.setIn(new FileInputStream("src/G05_Kruskal/G05_ex13_모의P0001_잠자는사이에.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st = null;

		TC = Integer.parseInt(br.readLine());

		for (int tc = 1; tc <= TC; tc++) {
			N = Integer.parseInt(br.readLine());

			parent = new int[N + 1];
			indegree = new int[N + 1];
			al = new ArrayList<Node>();
			ansS = ansE = 0;

			int a, b;
			for (int i = 1; i <= N; i++) {
				parent[i] = i;
				st = new StringTokenizer(br.readLine());
				a = Integer.parseInt(st.nextToken());
				b = Integer.parseInt(st.nextToken());
				al.add(new Node(a, b));
				indegree[b]++; // * 핵심코드 : indegree가 계산
			}

			// 1. indegree가 2가 아닌 경우 먼저 계산함
			for (int i = 0; i < al.size(); i++) {
				// * 주의 Node로 받으면 이상하게 에러남
				int start = al.get(i).start;
				int end = al.get(i).end;
				if (indegree[end] != 2) {
					// 사이클이 있다면
					if (find(start) == find(end)) {
						ansS = start;	// * 주의 조상이(find(start)) 아니라 start
						ansE = end;
						// 마지막 값이기에 break는 두지 않음
					} else {
						union(start, end);
					}
				}
			}

			// 2. 1. 계산 다음 많약 indegree가 2인 경우가 남아있다면 추가 계산
			for (int i = 0; i < al.size(); i++) {
				int start = al.get(i).start;
				int end = al.get(i).end;
				if (indegree[end] == 2) {
					// 사이클이 있다면
					if (find(start) == find(end)) {
						ansS = start;
						ansE = end;
					} else {
						union(start, end);
					}
				}
			}

			bw.write("#" + tc + " " + ansS + " " + ansE + "\n");

		}

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
