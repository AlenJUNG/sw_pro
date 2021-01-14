package D03_완전이진트리;

import java.util.*;
import java.io.*;

/*  Complete Binary Tree
   	정의 : 이진 트리의 한 종류
		1. 마지막 레벨을 제외하고는 모든 노드가 꽉 채워져 있고
		2. 마지막 레벨은 노드가 가장 왼쪽부터 빈칸없이 채워져 있는 형태의 트리
	
	표현 : 1차원 배열 > 왼쪽에서 오른쪽으로 저장하며 1부터 시작
		Parent(X) = X/2;
		left_child(X) = X*2;
		right_child(X) = X*2 + 1;
	
	* 주의 : 1차원 배열을 1부터 시작함, 0부터 시작하면 수정 필요 */

// Q) 중위순회의 탐색

public class D03_완전이진트리 {
	static int N, tree[];

	public static void main(String[] args) throws IOException {
		System.setIn(new FileInputStream("src/D03_완전이진트리/D03_완전이진트리.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		N = Integer.parseInt(br.readLine());
		tree = new int[N + 1]; // * 1차원 배열을 1부터 시작함에 유의

		StringTokenizer st = new StringTokenizer(br.readLine());

		for (int i = 1; i <= N; i++) {
			tree[i] = Integer.parseInt(st.nextToken());
		}

		inorder(1); // 중위순회 시작

		br.close();

	}

	private static void inorder(int node) {
		if (node * 2 <= N) { // 1. 왼쪽 자식 출력
			inorder(node * 2);
		}

		System.out.println(tree[node]); // 2. 자신 출력

		if (node * 2 + 1 <= N) { // 3. 오른쪽 자식 출력
			inorder(node * 2 + 1);
		}
	}
}
