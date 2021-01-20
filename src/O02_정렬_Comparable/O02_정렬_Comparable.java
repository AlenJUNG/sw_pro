package O02_����_Comparable;

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

	// ���� ���� : '����(score)�� ���� ����'
	@Override
	public int compareTo(Fruit other) {
		if (this.score < other.score) { // �� score�� �ٸ� score�麸�� ������ -1
			return -1;
		}
		return 1;
	}
}

public class O02_����_Comparable {

	public static void main(String[] args) {
		ArrayList<Fruit> fruit = new ArrayList<>();

		fruit.add(new Fruit("�ٳ���", 2));
		fruit.add(new Fruit("���", 5));
		fruit.add(new Fruit("���", 3));

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
