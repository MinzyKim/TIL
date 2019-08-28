# Spark2

## 1. Spark란?

- Hadoop환경에서 작동
- 인메모리 기반의 대용량 데이터 고속 처리 엔진으로 범용 분산 클러스터 컴퓨팅 프레임워크



## 2. Spark의 구성요소

- 클러스터 매니저 : Spark standalone, Yarn, Mesos
- SparkCore 
- Spark SQL
- Spark Streaming - 실시간 처리
- MLlib
- Graph X



## 3. Spark에서 데이터 처리하기 추상화 된 모델 :

- RDD(복구 가능한 분산 데이터)

- SparkApplication => RDD에선 하나의 Job, 

  - Spark클러스터를 구성하게 되면 노드들이 종류가 나눠짐

    - SparkClient
    - MasterNode
    - WorkerNode

  - SparkClient는 Worker와 독립적으로 존재할 수 있음

    - 이때, SparkApplication을 배포하고 실행 요청을 SparkMaster에게 함

  - SparkMaster의 역할

    - Spark 클러스터 환경에서 사용할 수 있는 리소스들 관리

  - WorkerNode의 역할

    - 할당 받은 리소스(CPU core, memory)를 사용해서 SparkApplication을 실행

    - Executor라는 프로세스를 실행시킴
    - Executor는 RDD의 partition을 task단위로 실행시킴

## 4. SparkApplication 작동 순서

1. SparkContext 생성
   - Spark애플리케이션과 Spark 클러스터와의 연결을 담당하는 객체
   - 모든 스파크 애플리케이션은 SparkContext를 이용해 RDD나 accumulator 또는 broadcast 변수 등을 다루게 됩니다.
   - Spark 애플리케이션을 수행하는 데 필요한 각종 설정 정보를 담는 역할을 한다

2. RDD(불변데이터 모델) 생성
   - Collection에서 생성(List, Seq등과 같은 것으로), hive, HDFS, CSV

3. RDD 처리
   - 변환(transformation)연산(RDD의 요소를 key, value쌍으로 만든다던가, 필터처리를 한다던가, 그룹핑 등등)
   - RDD를 input으로 받아서 새로운 RDD생성

4. 집계, 요약 처리 - Action연산
5. 영속화(파일로 저장할 수 있다.)

## 5. Spark 장점

- 메모리 기반이라 반복처리와 연속으로 이루어지는 변환처리를 고속화 (메모리 기반)
- 딥러닝, 머신러닝등의 실행환경에 적합한 환경 제공
- 서로 다른 실행환경과 구조, 데이터들의 처리에 대해서 통합 환경 제공



## 6. RDD와 관련된 함수

- sc.textFile() - file로 부터 RDD 생성하는 함수

- collect - 배열로 RDD생성하는 함수

- map, flatMap() - input으로 받아서 쪼갠 다음 연산 처리하는 함수
- mkString() - 구분자를 넘겨 주는 함수