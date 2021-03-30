package D04_인덱스트리;

package D04_ex07_P0041_K_Heap슈퍼이벤트;

/* * 처음부터 어느 시점까지 입력된 값으로 이루어진 부분 수열 P에 대하여 
 *   1. 현재까지 들어온 값에 대한 정렬이 아닌
 *   2. 각 값 별 몇 번의 입력이 있었는지에 대한 문제로 치환
 *      = 뽑은 카드의 수가 몇 번 나왔는가? 1 ~ 100000
 * 그렇다면 배열 전체를 인덱스트리화 가능
 */

import java.io.*;
import java.util.*;

public class Solution {
	static int TC;
	static int Q;
	static int idx_Tree[];
	static int size;
	static int MAX_NO = 100000;	// 카드에 적힌 수는 1 이상 최대 100000 이하

	public static void main(String[] args) throws IOException {
		System.setIn(new FileInputStream("src/D04_ex07_P0041_K_Heap슈퍼이벤트/input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st;

		TC = Integer.parseInt(br.readLine().trim());
		// 인덱스트리 배열 sizing
		size = 1;
		while (size < MAX_NO) {
			size *= 2;
		}

		for (int tc = 1; tc <= TC; tc++) {
			bw.append("#" + tc);

			Q = Integer.parseInt(br.readLine().trim());
			// 인덱스트리 배열 sizing
			idx_Tree = new int[size * 2];

			for (int q = 1; q <= Q; q++) {
				st = new StringTokenizer(br.readLine().trim());
				int type = Integer.parseInt(st.nextToken());
				int value = Integer.parseInt(st.nextToken());
				// 1. leaf node에 숫자 1부터 10만까지의 인덱스에 대한 들어온 횟수를 순서대로 입력하고 업데이트
				int idx = size + value;
				
				if (type == 1) {
					idx_Tree[idx] += 1;
					update(idx);
				// 2. 질의에 대한 순번의 수를 구하기
				} else if (type == 2) {
					bw.append(" " + search(value));
				}

			}
			bw.newLine();

		}

		br.close();
		bw.flush();
		bw.close();

	}
	
	// 구하고자하는 순번에(X) 해당하는 값 구하기
	private static int search(int X) {
		// 현재 노드 = root부터 시작
		int node = 1;
		
		// 자식 노드의 값들과 X값을 비교
		while (node < size) {
			int child_node = node * 2;
			// 왼쪽 자식의 값이 X보다 작은 경우
			if (X > idx_Tree[child_node]) {
				// 오른쪽 자식 노드로 이동 
				X = X - idx_Tree[child_node];
				node = child_node + 1;
			// 왼쪽 자식 노드 값이 X보다 크거나 같은 경우
			} else {
				// 왼쪽 자식 노드로 이동
				node = child_node;
			}
		}
		
		// 자식 노드들이 없을 때까지 반복하여 마지막에 도착한 leaf node가 앞에서부터 K번째 숫자가 있는 곳이 있음
		// 새로운 값을 입력하는 경우엔 해당 인덱스 숫자만 1 더한 다음에 부분합을 다시 구함
		idx_Tree[node]--;
		update(node);

		return node - size;
	}
	
	// 업데이트 메소드 수정 필요 > 시간초과남 쓰지 말 것
//	private static void update(int idx) {
//		for (int i = size - 1; i > 0; i--) {
//			idx_Tree[i] = idx_Tree[i * 2] + idx_Tree[i * 2 + 1];
//		}
//
//	}
	
	// 부분 업데이트
	private static void update(int idx) {
		idx /= 2;
		while (idx >= 1) {
			idx_Tree[idx] = idx_Tree[idx * 2] + idx_Tree[idx * 2 + 1];
			idx /= 2;
		}
	}
	
	// 더 빠른 부분 업데이트 방법이지만 이해가지 않음
//	private static void update(int idx) {
//		while((idx >>= 1) > 0) {
//			idx_Tree[idx] = idx_Tree[(idx << 1) + 1] + idx_Tree[idx << 1];
//		}
//
//	}

}

