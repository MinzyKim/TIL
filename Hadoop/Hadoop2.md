# Hadoop 2일차

## 	<어제것 복습>

- 빅데이터 6V

- Hadoop - 오픈소스 분산 병렬 (파일 시스템) 프레임워크

  				- HDFS(HadoopFileSystem), MapReduce, Yarn, Core, 여러 API

- 클러스터 

  - Master-Slave구조
  - NameNode(HDFS의 namespce, meta정보)
  - DataNode(64M, 128M 데이터 블럭이 분산되어 저장)
  - 3개씩 Data Block이 복제되어 저장 - 장애 허용, 대응력 높음

  