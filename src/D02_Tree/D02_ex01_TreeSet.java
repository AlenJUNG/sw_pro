package D02_Tree;

import java.util.*;
import java.io.*;

// https://coding-factory.tistory.com/555

// TreeSet은 HashSet과 마찬가지로 Set 인터페이스를 구현한 클래스
//  1. 객체를 중복해서 저장할 수 없고 
//  2. 저장 순서가 유지되지 않는다

// But HashSet과는 달리 TreeSet은 이진 탐색 트리(BinarySearchTree) 구조 
//  1. 이진 탐색 트리는 추가와 삭제에는 시간이 조금 더 걸리지만 
//  2. 정렬, 검색에 높은 성능을 보이는 자료구조

// 그래서 HashSet보다 데이터의 추가와 삭제는 시간이 더 걸리지만 
// ** 검색과 정렬에는 더 유리 

// TreeSet은 데이터를 저장할 시 이진탐색트리(BinarySearchTree)의 형태로 데이터를 저장하기에 
//  1. 기본적으로 nature ordering를 지원하며 
//  2. 생성자의 매개변수로 Comparator객체를 입력하여 정렬 방법을 임의 지정 가능

public class D02_ex01_TreeSet {

	public static void main(String[] args) {
		// TreeSet 선언
		TreeSet<Integer> set1 = new TreeSet<Integer>(); // TreeSet 생성
		TreeSet<Integer> set2 = new TreeSet<>(); // new에서 타입 파라미터 생략가능
		TreeSet<Integer> set3 = new TreeSet<Integer>(set1); // set1의 모든 값을 가진 TreeSet생성
		TreeSet<Integer> set4 = new TreeSet<Integer>(Arrays.asList(1, 2, 3)); // 초기값 지정

		TreeSet<Integer> set = new TreeSet<Integer>(); // TreeSet 생성
		set.add(7);
		set.add(4);
		set.add(9);
		set.add(1);
		set.add(5);

		System.out.println(set); // 그냥 출력 시, 오름차순으로 자동 출력됌

		set.remove(1); // index 아니라 값 1을 삭제함
		set.clear(); // 모든 값 제거

		TreeSet<Integer> ts = new TreeSet<Integer>(Arrays.asList(1, 2, 3));
		System.out.println(ts.size());

		ts.clear();
		ts = new TreeSet<Integer>(Arrays.asList(4, 2, 3));
		System.out.println(ts); // 오름차순 자동 정렬되어 출력됌
		System.out.println("ts.first() = " + ts.first()); // 최소값 출력
		System.out.println("ts.last() = " + ts.last()); // 최대값 출력
		System.out.println("ts.higher(3) = " + ts.higher(3)); // 3보다 큰 값 1개 출력
		System.out.println("ts.lower(3) = " + ts.lower(3)); // 3보다 작은 값 1개 출력

		// * TreeSet에는 객체 전체를 대상으로 한 번씩 반복해서 가져오는 반복자(Iterator)를 제공
		Iterator iter = ts.iterator();
		while (iter.hasNext()) {
			System.out.println(iter.next());
		}
	}
}
