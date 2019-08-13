# Hadoop

## 	BigData

## 	1. 1세대 vs 2세대

- 1세대는 데이터가 예측 가능, 하지만 점점 개인이 데이터의 축적에 가담하기 시작하면서 데이터량이 기하급수적으로 늘어남

- 비정형 데이터가 주가 되면서 기업 중심에서 **개인 중심**으로 변화
- 데이터량은 예측 불가하게 증가, 전처리과정의 필요성이 대두



- 대용량 고속의 다양한 데이터를 분석하여 인사이트의 가치를 주는 새로운 기술



## 	2. 빅데이터

- 초기엔 3V

  - Volume, Variety, Velocity

- 현재엔 6V

  - Volume, Variety, Velocity, Veracity, Visualization, Value

  ```bigdata
  지구상에선 지금 이 순간에도 방대한 크기(Volume)의 다양한 (Varity) 데이터들이 빠른 속도(Velocity)로 발생하고 있다. 빅 데이터는 3V(Volumn, Varity, Velocity)를 수용하며, 데이터의 진실성(Veracity)를 확보하고, 분석 데이터를 시각화(Visualization)함으로써 새로운 효익을 가져다 줄 가치(Value)를 창출하는 것이다.
  ```



## 	Hadoop

- 신뢰성 있고, 확장성 있는 분산 컴퓨팅을 위한 오픈소스 프레임워크

- 간단한 프로그래밍 모델을 사용하여 대용량 데이터의 분산 처리를 할 수 있는 프레임워크

- GFS, MapReduce 소프트웨어구현체

​     \- 아파치Top-Level 프로젝트

​     \- 코어는 Java, C/C++, Python 등 지원

- 대용량 데이터 처리를 위한 플랫폼(아래 3개로 구성)

​    \- <u>분산파일시스템(HDFS)</u>

​    \- 분산병렬처리시스템(MapReduce)

​    \- 기반소프트웨어프레임워크(Core)

### 	1. 특장점

- 장애복구
  - 복제 데이터 저장
  - 주기적인 상태 체크

-  스트리밍 방식의 데이터 접근
  - 높은 데이터 처리량에 중점을 두고 있음

-  대용량 데이터 저장
  - 하나의 파일이 테라바이트 이상 저장 가능
  - 하나의 인스턴스에서 수백만 개 이상의 파일 지원

- 데이터 무결성
  - 읽기 전용
  - 파일 이동, 삭제, 복사 인터페이스 제공

![1565681531989](C:\Users\student\AppData\Roaming\Typora\typora-user-images\1565681531989.png)





### 	2. 저장 기술	

 #### 	*HDFS(Hadoop Distributed File System)

- 파일의 분산 저장이 목적

- Namenodes와 Datanodes로 구성
  -  Master Namenode
  - Secondary Namenode
  - Datanode

-  저렴한 컴퓨터로 대 용량 데이터를 저장할 수 있는 시스템
  - 네트워크 Raid와 같이 연결된 것 처럼 사용하는 하드디스크
  - Scale Out

-  Block(Chunk) 단위로 파일관리 (저장/복제/삭제)
  - Default Size는 64M

-  복제기능을 통해 안전성/신뢰성을 보장

-  1대의 Master서버에 4000+이상의 Datanodes를 운영할 수 있음.

- API지원
  - 하둡 코어는 Python, Java, C/C++	



