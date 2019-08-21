# Hadoop5일차

## 1. Hive

### 1.1 setting

1. 하둡실행

2. ```
   #metastore 로 사용할 database 생성 및 metastore에 스키마 생성
   [hadoop@master ~]$ su -
   [root@master ~] mysql -u root -p
   Enter password:
   mysql> show databases;
   mysql> CREATE DATABASE metastore_db;
   
   mysql> USE metastore_db;
   mysql> show tables;
   mysql> SOURCE /usr/local/hive/scripts/metastore/upgrade/mysql/hive-schema-1.1.0.mysql.sql;
   mysql> show tables;
    
   # $HIVE_HOME/lib 아래 mysql-connector-java-5.1.36-bin.jar에 복사 
   [hadoop@master ~]$ tar -xvf ./Downloads/mysql-connector-java-5.1.36.tar.gz
   [hadoop@master ~]$ ls
   [hadoop@master ~]$ cd  /home/hadoop/mysql-connector-java-5.1.36/
   [hadoop@master ~]$ cp  mysql-connector-java-5.1.36-bin.jar /usr/local/hive/lib/
   
   
   #하둡 시작
   [hadoop@master ~]$ cd /usr/local/hadoop-2.7.7/sbin
   [hadoop@master ~]$ ./start-all.sh
   
   [hadoop@master ~]$ hive
   hive> show databases;
   
   
   
   
   hive> create database test_db;
   hive> use test_db
   hive> create table test ( name  varchar(10) );
   hive> describe test
   
   #하둡 DFS에 데이터베이스와 테이블은 디렉토리로 생성됨을 확인
   [hadoop@master ~]$ hadoop fs -ls -R /user/
   
   #metastore에서 생성한 데이터베이스와 테이블 메타 정보 확인
   mysql> select OWNER, TBL_NAME, TBL_TYPE from TBLS;
   mysql> select OWNER_NAME, OWNER_TYPE, NAME from DBS;
   
   
   
   
   hive> drop database test_db cascade;
   hive> show databases;
   ```

## 2. R

### 2.1 setting

```R
https://wikidocs.net/33948


[root@master ~]# yum install epel-release
[root@master ~]# yum install npm
[root@master ~]# yum install R 
[root@master ~]# ls -l /usr/lib64
[root@master ~]# chown -R hadoop:hadoop /usr/lib64/R
[root@master ~]# ls -l /usr/lib64



#hadoop의 .bash_profile에 추가
[hadoop@master ~]$ vi .bash_profile

export HADOOP_CMD=/usr/local/hadoop-2.7.7/bin/hadoop
export HADOOP_STREAMING=/usr/local/hadoop-2.7.7/share/hadoop/tools/lib/hadoop-streaming-2.7.7.jar

[hadoop@master ~]$ source ./.bash_profile
[hadoop@master ~]$ R

> install.packages(c("rJava", "Rcpp", "RJSONIO", "bitops", "digest", "functional", "stringr", "plyr", "reshape2", "caTools"))
> install.packages(c("rhdfs", "rmr", "plyrmr"))


[root@master ~]# chown -R hadoop:hadoop /usr/share/doc/R-3.6.0/html/
> updatge.packages(c("rJava", "Rcpp", "RJSONIO", "bitops", "digest", "functional", "stringr", "plyr", "reshape2", "caTools"))


https://github.com/RevolutionAnalytics/RHadoop/wiki
 

> install.packages("/home/hadoop/Downloads/rhdfs_1.0.8.tar.gz", repos=NULL, type="source")

> install.packages("/home/hadoop/Downloads/rmr2_3.3.1.tar.gz", repos=NULL, type="source")
> install.packages("/home/hadoop/Downloads/plyrmr_0.6.0.tar.gz", repos=NULL, type="source")
> install.packages("/home/hadoop/Downloads/rhbase_1.2.1.tar.gz", repos=NULL, type="source")

> install.packages("/home/hadoop/Downloads/ravro_1.0.4.tar.gz", repos=NULL, type="source")

> install.packages(c("bit64", "rjson"))

```

### 2.2 실습

1. 1부터 1000까지의 숫자를 생성

```R
library(rhdfs) # Rhadoop package for hdfs
>hdfs.init()    # Start to connect HDFS, 반드시 rmr2를 로드하기 전
>library(rmr2)  # RHadoop package for MapReduce

hadoop fs -mkdir /tmp/ex1

> dfs.rmr("/tmp/ex1")
> small.ints <- to.dfs(1:1000, "/tmp/ex1")

> result <- mapreduce(input = small.ints, 
	map = function(k,v) cbind(v,v^2)
)
> out <- from.dfs(result)
> out
```



2. 균일분포에서 1000개씩 난수를 발생 평균계산

```R
library(rhdfs) # Rhadoop package for hdfs
hdfs.init()    # Start to connect HDFS, 반드시 rmr2를 로드하기 전
library(rmr2)  # RHadoop package for MapReduce
 
infile <- "/tmp/rmr2/ex2"
if(dfs.exists(infile)) dfs.rmr(infile)
small.ints = to.dfs(1:1000, output=infile)
## Defining the MapReduce job 

```

3. WordCount

```R
library(rhdfs) # Rhadoop package for hdfs
hdfs.init()    # Start to connect HDFS, 반드시 rmr2를 로드하기 전
library(rmr2)  # RHadoop package for MapReduce
 
inputfile <- "/tmp/README.txt"
if(!hdfs.exists(inputfile)) stop("File is not found")
outputfile <- "/tmp/ex4"
if(hdfs.exists(outputfile)) hdfs.rm(outputfile)
 
map <- function(key, val){
	words.vec <- unlist(strsplit(val, split = " "))
	#lapply(words.vec, function(word) 
    keyval(words.vec, 1)
}
 
reduce <- function(word, counts ) {
	keyval(word, sum(counts))
}
 result <- mapreduce(input = inputfile,
	output = outputfile, 
	input.format = "text", 
	map = map, 
	reduce = reduce, 
	combine = T
)
 
## wordcount output
freq.dfs <- from.dfs(result)
freq <- freq.dfs$val
word <- freq.dfs$key
oidx <- order(freq, decreasing=T)[1:10]
 
# Words frequency plot
barplot(freq[oidx], names.arg=word[oidx] )

```

4. 실행하기

```R
> source("파일이름")
> getwd() # 경로확인
```

