package D04_인덱스트리;

import java.util.*;
import java.io.*;

public class D04_ex01_연습P0019_구간합 {
	static int T, N, Q;
	static int size;
	static long arr[];
	static long ans;

	public static void main(String[] args) throws IOException {
		System.setIn(new FileInputStream("src/D04_인덱스트리/D04_ex01_연습P0019_구간합.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		T = Integer.parseInt(br.readLine());

		for (int tc = 1; tc <= T; tc++) {

			N = Integer.parseInt(br.readLine());
			Q = Integer.parseInt(br.readLine());

			// STEP01. 인덱스 크기 계산 및 선언
			// 인덱스 트리(이진 트리)의 중요한 성질 이용
			// * 2의 곱셈으로 N보다 큰 직후 값은 leaf node size를 알 수 있다.
			// * 1 ~ leaf node - 1의 크기는 2^(Index[leaf node start 지점]) - 1
			size = 1;
			while (size < N) {
				size *= 2; // size는 인덱스 트리 leaf node의 시작점이 된다.
			}

			arr = new long[size * 2 + 1];

			// STEP02. leaf node부터 1 ~ N value 입력
			for (int i = size; i <= size + N; i++) {
				arr[i] = i - size + 1;
			}

			// STEP03. 1 ~ leaf node -1의 인덱스 구간합을 실행 > 인덱스 트리 완성
			// 인덱스 트리(이진 트리)의 중요한 성질 이용
			// * 부모노드 = 두 자식 노드의 합
			for (int i = size - 1; i >= 1; i--) {
				arr[i] = arr[i * 2] + arr[i * 2 + 1];
			}

			int opt, x, y;
			ans = 0;
			for (int i = 1; i <= Q; i++) {
				st = new StringTokenizer(br.readLine());
				opt = Integer.parseInt(st.nextToken());
				x = Integer.parseInt(st.nextToken());
				y = Integer.parseInt(st.nextToken());

				if (opt == 0) { // x번째 수를 y로 업데이트
					update(x, y);
				} else { // x번째 수부터 y번째 수까지의 합을 구함
					ans += getSum(x, y);
				}
			}

			System.out.println(ans % 1000000007);

		}
		br.close();

	}

	private static long getSum(int start, int end) {
		long sum = 0; // * 지역변수는 초기화 반드시 필요

		int s, e;
		s = start + size - 1; // 합을 구할 start idx 구하기
		e = end + size - 1; // 합을 구할 end idx 구하기

		while (s <= e) {
			if (s % 2 == 1) { // s가 홀수일 경우에만
				sum += arr[s]; // 1. 현 위치의 값 더하고
				s++; // 2. 오른쪽으로 한 칸 이동 (부모노드가 오른쪽으로 바뀜)
			}
			if (e % 2 == 0) { // e가 짝수일 경우에만
				sum += arr[e]; // 1. 현 위치의 값 더하고
				e--; // 2. 왼쪽으로 한 칸 이동 (부모노드가 왼쪽으로 바뀜)
			}
			s /= 2; // 조건 상관없이 부모노드로 이동
			e /= 2; // 조건 상관없이 부모노드로 이동
		}

		return sum;

	}

	// target 번째 값을 value로 변경
	private static void update(int target, int value) {
		target = target + size - 1; // target : 업데이트할 인덱스 구하기
		int plus = (int) (value - arr[target]); // ** plus = 변경할 값 - 원래 값

		// bottom - up : 원래 값과의 "차이" 만큼 업데이트할 인덱스 ~ root 노드까지 순차적 덧셈 수행
		while (target >= 1) {
			arr[target] += plus;
			target /= 2;
		}
	}

}
