package G04_Union_Find;

import java.util.*;
import java.io.*;

public class G04_Union_Find_Adv {
	// 노드의 개수(V)와 간선(Union 연산)의 개수(E)
	// 노드의 개수는 최대 100,000개라고 가정
	static int V, E;
	static int parent[] = new int[100001];

	public static void main(String[] args) throws IOException {
		System.setIn(new FileInputStream("src/G04_Union_Find/G04_Union_Find.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		V = Integer.parseInt(st.nextToken());
		E = Integer.parseInt(st.nextToken());

		// 부모 테이블에 부모를 자기 자신으로 초기화
		for (int i = 1; i <= V; i++) {
			parent[i] = i;
		}

		// union 연산 각각 수행
		for (int i = 0; i < E; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());

			unionParent(a, b);
		}

		// 각 원소가 속한 집합 출력하기
		System.out.println("각 원소가 속한 집합: ");
		for (int i = 1; i <= V; i++) {
			System.out.print(findParent(i) + " ");
		}
		System.out.println();

		// 부모 테이블 내용 출력하기
		System.out.println("부모 테이블: ");
		for (int i = 1; i <= V; i++) {
			System.out.print(parent[i] + " ");
		}
		System.out.println();
	}

	// 두 원소가 속한 집합을 합치기
	private static void unionParent(int a, int b) {
		a = findParent(a); // a의 루트노드 찾기
		b = findParent(b); // b의 루트노드 찾기

		// 작은 값의 루트노드가 부모가 됌
		if (a < b) {
			parent[b] = a;
		} else {
			parent[a] = b;
		}
	}

	// 특정 원소가 속한 집합 찾기
	private static int findParent(int x) {
		// x == parent[x], 즉, 루트노드라면 그대로 return
		if (x == parent[x]) {
			return x;
		// 루트노드가 아니라면 루트노드를 찾을 때까지 재귀호출
		}else {
			return parent[x] = findParent(parent[x]);
		}		
	}
}
