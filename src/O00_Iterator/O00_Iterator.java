package O00_Iterator;

import java.io.*;
import java.util.*;

/* Iterator : 모든 컬렉션클래스에서 데이터를 읽을 때 사용
 *  리스트의 값을 받아 순차적으로 값을 출력
 * 	만약, 표준화가 안되면 모든 컬렉션클래스에서 데이터를 읽는 메서드를 일일이 알아야하고
 * 	각각 컬렉션에 접근이 힘들어짐
 * 
 * 	1. hasNext : 다음 데이터가 있는지 boolean (true/false)로 반환
 * 	2. next : 다음 요소를 나타냄
 *  3. remove : next 메서드가 호출한 데이터를 삭제
 */

public class O00_Iterator {
	static class PhoneInfo {
		String name;
		int age;

		public PhoneInfo(String name, int age) {
			this.name = name;
			this.age = age;
		}

		public String getName() {
			return name;
		}

		public void showInfo() {
			System.out.println("이름 : " + name);
			System.out.println("나이 : " + age);
		}
	}

	public static void main(String[] args) {
		LinkedList<PhoneInfo> ll = new LinkedList<>();
		ll.add(new PhoneInfo("홍길동", 20));
		ll.add(new PhoneInfo("김소월", 22));
		ll.add(new PhoneInfo("나태주", 30));

		Iterator<PhoneInfo> it = ll.iterator();
		PhoneInfo info = null;

		System.out.println("Iterator 반복자를 이용하여 출력과 동시에 삭제");
		while (it.hasNext()) {
			info = it.next();
			info.showInfo();
			if ("홍길동".compareTo((info.getName())) == 0) {
				it.remove();
			}
		}

		System.out.println("\n<삭제 후 출력>");
		it = ll.iterator();
		while (it.hasNext()) {
			info = it.next();
			info.showInfo();
		}

		System.out.println("\n<phoneInfo 첫 인자 name 내림차순 출력>");
		it = ll.descendingIterator();
		while (it.hasNext()) {
			info = it.next();
			info.showInfo();
		}
	}
}
