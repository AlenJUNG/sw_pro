package O02_����_Comparable;

/* 
 * 1. Compare �Լ��� 2���� �������� ��ġ�� ���Ͽ� Swap ���θ� �����ϴ� �Լ�
 * 2. Compare �Լ��� ���� �� �տ� �ִ� �����Ͱ� ��ġ������ �տ� �ִ� ������
 * 3. o1 �����Ϳ� o2 �������� ��ġ�� ���� �ٲپ���ϴ��� Ȯ��
 * 4. Compare �Լ��� �����ϴ� ���� ���� �� �������� Swap ���� ����
 * < Return value : o1 - o2>
 * 	if(o1 < o2) return -1 : �� �����͸� �ٲ��� ����
 *  if(o1 < o2) return 0 : �� �����Ͱ� ������
 *  if(o1 < o2) return 1 : �� �����͸� �ٲ�
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
