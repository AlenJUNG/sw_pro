package O02_정렬_Comparable;

/* 
 * 1. Compare 함수는 2개의 데이터의 위치에 대하여 Swap 여부를 결정하는 함수
 * 2. Compare 함수의 인자 중 앞에 있는 데이터가 위치적으로 앞에 있는 데이터
 * 3. o1 데이터와 o2 데이터의 위치를 서로 바꾸어야하는지 확인
 * 4. Compare 함수가 리턴하는 값에 따라 두 데이터의 Swap 여부 결정
 * < Return value : o1 - o2>
 * 	if(o1 < o2) return -1 : 두 데이터를 바꾸지 않음
 *  if(o1 < o2) return 0 : 두 데이터가 동일함
 *  if(o1 < o2) return 1 : 두 데이터를 바꿈
 */

import java.util.*;
import java.io.*;

class Fruit implements Comparable<Fruit> {
	String name;
	int score;

	public Fruit(String name, int score) {
		this.name = name;
		this.score = score;
	}

	public String getName() {
		return this.name;
	}

	public int getScore() {
		return this.score;
	}

	// 정렬 기준 : '점수(score)가 낮은 순서'
	@Override
	public int compareTo(Fruit other) {
		if (this.score < other.score) { // 이 score가 다른 score들보다 작으면 -1
			return -1;
		}
		return 1;
	}
}

public class O02_정렬_Comparable {

	public static void main(String[] args) {
		ArrayList<Fruit> fruit = new ArrayList<>();

		fruit.add(new Fruit("바나나", 2));
		fruit.add(new Fruit("당근", 5));
		fruit.add(new Fruit("사과", 3));

		for (int i = 0; i < fruit.size(); i++) {
			System.out.println(fruit.get(i).getName() + " : " + fruit.get(i).getScore());
		}

		Collections.sort(fruit);
		System.out.println("== After --> Collections.sort ==");

		for (int i = 0; i < fruit.size(); i++) {
			System.out.println(fruit.get(i).getName() + " : " + fruit.get(i).getScore());
		}

	}

}
