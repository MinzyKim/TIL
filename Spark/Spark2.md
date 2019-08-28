# Spark2

## 1. Spark란?

- Hadoop환경에서 작동, 인메모리 기반의 대용량 데이터 고속 처리 엔진으로 범용 분산 클러스터 컴퓨팅 프레임워크



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



## 4. SparkApplication 작동 순서

1. SparkContext 생성
   - RDD를 생성하는 과정
   - 