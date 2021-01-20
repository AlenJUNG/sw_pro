package O02_정렬_Comparable;

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
