package S01_이진탐색;

// 이코테 201 page 실전문제 3
import java.io.*;
import java.util.*;

public class S01_ex01_떡볶이떡 {
	static final int INF = (int) 1e9;
	static int N, M, ans;
	static int arr[];

	public static void main(String[] args) throws IOException {
		System.setIn(new FileInputStream("src/S01_이진탐색/S01_ex01_떡볶이떡.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		// 떡의 개수(N)와 요청한 떡의 길이(M)
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		arr = new int[N];

		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}

		Arrays.sort(arr); // 예제에서 정렬은 되어 있음

		// 이진 탐색을 위한 시작점과 끝점 설정
		System.out.println(binarySerach(arr, 0, INF));

		br.close();
	}

	// 이진탐색 수행
	private static int binarySerach(int[] map, int start, int end) {

		while (start <= end) {
			int mid = start + (end - start) / 2;
			long temp = 0;

			// 잘랐을 때 떡의 양 계산
			for (int i = 0; i < N; i++) {
				if (map[i] > mid) {
					temp += map[i] - mid;
				}
			}
			// 떡의 양이 부족한 경우 더 많이 자르기(왼쪽 부분 탐색)
			if (temp < M) {
				end = mid - 1;
			// 떡의 양이 충분한 경우 덜 자르기(오른쪽 부분 탐색)
			} else {
				ans = mid; // 최대한 덜 잘랐을 때가 정답이므로, 여기에서 ans에 기록
				start = mid + 1;
			}

		}
		return ans;
	}
}
