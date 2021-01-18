package G07_위상정렬;

import java.util.*;
import java.io.*;

public class G07_ex01_Adv_커리큘럼 {
	static int N; // 노드의 개수
	static int indegree[]; // 진입차수
	static ArrayList<ArrayList<Integer>> graph = new ArrayList<ArrayList<Integer>>();
	static int time[]; // 각 강의시간

	public static void main(String[] args) throws IOException {
		System.setIn(new FileInputStream("src/G07_위상정렬/G07_ex01_Adv_커리큘럼.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		N = Integer.parseInt(br.readLine());
		// 듣고자 하는 강의 수대로 배열 크기 선언
		indegree = new int[501];
		time = new int[501];

		// graph 초기화
		for (int i = 0; i <= N; i++) {
			graph.add(new ArrayList<Integer>());
		}

		// 방향 그래프의 모든 간선 정보를 입력받기
		for (int i = 1; i <= N; i++) {
			st = new StringTokenizer(br.readLine());
			// 첫 번째 숫자는 시간 정보
			time[i] = Integer.parseInt(st.nextToken());
			int from;
			// 진입차수(indegree) 및 그래프 선후관계 입력
			while (true) {
				from = Integer.parseInt(st.nextToken());
				// 종료조건
				if (from == -1) {
					break;
				}
				indegree[i] += 1;
				graph.get(from).add(i);
			}
		}
		topologySort();
	}

	private static void topologySort() {
		// 알고리즘 수행 결과를 담을 배열 선언
		int result[] = new int[501];
		// time > result 배열복사
		for (int i = 1; i <= N; i++) {
			result[i] = time[i];
		}

		// q 선언
		Queue<Integer> q = new LinkedList<>();

		// 처음 시작할 때 진입차수가 0인 노드를 q에 삽입
		for (int i = 1; i <= N; i++) {
			if (indegree[i] == 0) {
				q.offer(i);
			}
		}

		while (!q.isEmpty()) {
			int now = q.poll();

			for (int toIdx = 0; toIdx < graph.get(now).size(); toIdx++) {
				// 해당 원소와 연결된 노드들의 시간을 누적
				result[graph.get(now).get(toIdx)] = result[now] + time[graph.get(now).get(toIdx)];
				// * 아래 코드는 아마 초기 진입차수가 0인 노드가 여러개 인 경우 유효한 코드 같음 > 숙지할 것
//				result[graph.get(now).get(toIdx)]
//						= Math.max(result[graph.get(now).get(toIdx)], result[now] + time[graph.get(now).get(toIdx)]);
				// 해당 원소와 연결된 노드들의 진입차수에서 1빼기
				indegree[graph.get(now).get(toIdx)] -= 1;
				// 새롭게 진입차수가 0이 되는 노드를 q에 삽입
				if (indegree[graph.get(now).get(toIdx)] == 0) {
					q.offer(graph.get(now).get(toIdx));
				}
			}
		}

		// 위상 정렬 수행 결과 출력

		for (int i = 1; i <= N; i++) {
			System.out.println(result[i]);
		}
	}
}
