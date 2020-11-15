## 자료구조 Queue

1. 특징

   - LIFO(Last In First Out) 선입선출의 개념, 가장 마지막에 들어간 것이 첫번째로 나간다.
   - 한 쪽 끝의 프런트(Front)만 정하여 삭제 연산을 수행함
   - 다른 한 쪽은 리어(rear)로 정하여 삽입 연산을 수행
   - 그래프의 넓이 우선 탐색(BFS)에서 사용
   - 컴퓨터 버퍼에서 주로 사용 - 버퍼(큐)를 만들어 대기

2. 문법

   ```java
   // 선언
   import java.util.Queue;
   
   Queue<Integer> queue = new LinkedList<>(); // int형 queue선언, linkedlist
   Queue<String> stack = new LinkedList<>(); // String형 queue선언, linkedlist
   
   // 자바에서 Queue는 LinkedList로 선언해줘야 함.
   
   //Queue값 추가
   queue.add(1)
   queue.add(2)
   queue.offer(3)
   // 값이 성공적으로 삽입되면 true를 반환, 공간이 없어 데이터가 들어가지 못했으면 IiiegalStateException을 발생
   
   //Queue값 삭제
   queue.poll(); //queue에 첫번째 값을 반환하고 제거, 비어있으면 null
   queue.remove(); //첫번째 값 제거
   queue.clear(); //queue 초기화
   
   //가장 먼저 들어간 값 출력
   queue.peek(); //첫번째 값 참조
   ```

   