package D04_인덱스트리;
public class D04_ex02_P0058_탱크_중상 {
	static class Tank {
		int x, y, s;

		public Tank(int x, int y, int s) {
			this.x = x;
			this.y = y;
			this.s = s;
		}
	}

	static Tank tArr[];
	static int yArr[], yIdx[];
	static long idxTree[], ans;	// 문제에서 4 byte가 넘어갈 수 있다고 했으니 long으로 선언
	static int TC, N, a, b, c;

	public static void main(String[] args) throws IOException {
		System.setIn(new FileInputStream("src/D04_인덱스트리/D04_ex02_P0058_탱크_중상.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st;

		TC = Integer.parseInt(br.readLine().trim());

		for (int tc = 1; tc <= TC; tc++) {

			N = Integer.parseInt(br.readLine().trim());

			tArr = new Tank[N + 1];
			yArr = new int[N + 1];
			yIdx = new int[N + 1];

			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine().trim());
				a = Integer.parseInt(st.nextToken());
				b = Integer.parseInt(st.nextToken());
				c = Integer.parseInt(st.nextToken());

				tArr[i] = new Tank(a, b, c);
				yArr[i] = b;
			}

			// 내림차순 정렬 : 0 ~ N - 1 구간 주의
			Arrays.sort(tArr, 0, N, new Comparator<Tank>() {

				@Override
				public int compare(Tank o1, Tank o2) {
					return o2.x - o1.x;
				}
			});

			// 오름차순 정렬 : 0 ~ N - 1 구간 주의
			Arrays.sort(yArr, 0, N);
			
			// * 중요 : 100만의 정보를 10만의 정보로 줄여 계산하기 위해 좌표값 다시 재지정
			for (int i = 0; i < N; i++) {
				yIdx[yArr[i]] = i; // yArr[i]이 몇 번째 위치인지 순서대로 저장 > 몇 번째 y값인가?
			}
			
			// 인덱스트리 생성 > 1차원 배열로 생성
			int size = 1;
			while (size < 100000) {
				size *= 2;
			}
			// * Why? 최종 idxTree 크기가(size * 2) 262144 인가? 
			// 10만의 정보로 줄였기 때문에 N을 10만으로 봄 
			idxTree = new long[size * 2];
			
			int tempIdx, left, right;
			long temp_ans;
			ans = 0;
			
			// x좌표로 내림차순한 탱크 배열에서 탱크들을 하나씩 빼면서 점수 계산
			// 탱크의 수는 N개까지 있으니 반복
			for (int i = 0; i < N; i++) {
				// Re라벨링 : 탱크 순서대로 내림차순으로 변환된 y좌표를 인덱스로 변환
				tempIdx = yIdx[tArr[i].y] + size;
				// 구간 표현
				left = tempIdx + 1;
				right = N + size - 1;

				// 노드 1개를 Update 함
				while (tempIdx > 0) {
					idxTree[tempIdx] += (long) tArr[i].s;
					tempIdx /= 2;
				}

				temp_ans = 0L;
				while (left <= right) {
					if (left % 2 == 1) {
						temp_ans += idxTree[left];
						left++;
					}
					if (right % 2 == 0) {
						temp_ans += idxTree[right];
						right--;
					}

					left /= 2;
					right /= 2;
				}
				ans += temp_ans;
			}

			bw.write("#" + tc + " " + ans + "\n");
		}

		br.close();
		bw.flush();
		bw.close();
>>>>>>> branch 'master' of https://github.com/shadyhj/sw_pro.git

	static Tank tArr[] = new Tank[100000];
	static int yArr[] = new int[100000];
	static int yIdx[] = new int[1000001]; // 좌표 순서가 몇번째인지 저장할 거임
	static long indexTree[] = new long[262144]; // 문제에서 4 byte를 넘어갈 수 있다고 했으니 long으로 선언
	// 10만에 제일가까운 2의 제곱 * 2 사이즈로 만듦
	static int TC, N;

	public static void main(String[] args) throws IOException {
		System.setIn(new FileInputStream("src/D04_인덱스트리/D04_ex02_P0058_탱크_중상.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st;

		TC = Integer.parseInt(br.readLine().trim());

		for (int tc = 1; tc <= TC; tc++) {
			N = Integer.parseInt(br.readLine().trim());

			int x, y, s;
			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine().trim());
				x = Integer.parseInt(st.nextToken());
				y = Integer.parseInt(st.nextToken());
				s = Integer.parseInt(st.nextToken());

				tArr[i] = new Tank(x, y, s);
				yArr[i] = y;
			}

			Arrays.sort(tArr, 0, N, new Comparator<Tank>() {

				@Override
				public int compare(Tank o1, Tank o2) {
					return o2.x - o1.x; // x 기준 내림차순으로 정렬
				}
			});

			Arrays.sort(yArr, 0, N);
			for (int i = 0; i < N; i++) {
				yIdx[yArr[i]] = i;
			}

			int size = 1;
			int idx, a, b;
			long res;
			int ans = 0;

			while (size > N) {
				size *= 2;
			}

			for (int i = 0; i < N; i++) {
				idx = size + yIdx[tArr[i].y];
				a = idx + 1;
				b = N - 1 + size;

				while (idx > 0) {
					indexTree[idx] += (long) tArr[i].s;
					idx /= 2;
				}

				res = 0L;

				while (a <= b) {
					if (a % 2 == 1) {
						res += indexTree[a];
						a++;
					}
					if (b % 2 == 0) {
						res += indexTree[b];
						b--;
					}

					a /= 2;
					b /= 2;
				}

				ans += res;
			}

			bw.write("#" + tc + " " + ans + "\n");

		}

		br.close();
		bw.flush();
		bw.close();
	}

}
