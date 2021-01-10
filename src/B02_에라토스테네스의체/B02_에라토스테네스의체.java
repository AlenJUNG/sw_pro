package B02_에라토스테네스의체;
// https://coding-factory.tistory.com/600
// 위 참고링크의 그림부터 보고 이해
// 비교적 작은 구간 (10^8) 이하 에서 소수를 빠르게 찾는 방법
// 특정 범위에서의 모든 소수를 찾을 때 가장 효율적인 알고리즘 (N log long N)
// 1. 2 ~ 120까지의 모든 숫자들을 나열합니다.
// 2. 2는 소수이므로 오른쪽에 2를 쓰고 2의 배수들을 모두 지웁니다. (빨간색)
// 3. 남아있는 숫자에서 3은 소수이므로 오른쪽에 3을 쓰고 3의 배수들을 모두 지웁니다. (초록색)
// 4. 남아있는 숫자에서 5는 소수이므로 오른쪽에 5를 쓰고 5의 배수들을 모두 지웁니다. (파란색)
// 5. 남아있는 숫자에서 7는 소수이므로 오른쪽에 7을 쓰고 7의 배수들을 모두 지웁니다. (노란색)
// 6. 위의 과정을 반복합니다.
// 7. 11 ×11 은 121 로 범위 2 ~ 120을 넘기 때문에 반복을 멈추고 남은 수(소수)들을 출력합니다.
//* 주의 : 배열 크기로 선언할 수 없는 구간의 소수인지 유의

// Q) 120 이하의 소수를 출력하시오

public class B02_에라토스테네스의체 {
	static int target = 120; // target 설정
	static boolean prime_arr[] = new boolean[target + 1]; // 숫자를 지울 배열

	public static void main(String[] args) {

		find_prime_number(target, prime_arr);

	}

	private static void find_prime_number(int tar, boolean prime[]) {
		prime[0] = prime[1] = true;
		// 2부터 특정 수의 배수에 해당하는 수를 모두 지움
		for (int i = 2; i <= tar; i++) {
			if (prime[i]) {
				continue; // 이미 지워진 수라면 건너뜀
			}

			// 이미 지워진 숫자가 아니라면, 해당 숫자의 배수를 모두 true로 만듦
			// 왜 i * 2 부터 시작할까? > 소수가 아닌 최초값은 * 2 부터 시작함
			for (int j = 2 * i; j <= tar; j += i) {
				prime[j] = true;
			}
		}

		// 남아있는 수를 모두 출력 (배열에서 false인 index)
		for (int i = 2; i <= tar; i++) {
			if (prime[i] == false) {
				System.out.print(i + " ");
			}
		}
	}
}
