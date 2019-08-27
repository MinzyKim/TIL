# Spark

## 1. Hadoop환경에서 Spark(scala를 기반으로 한) Setting하기

```scala
su - 
hadoop
cd /usr/local/
tar zxvf /home/hadoop/Downloads/spark-2.4.3-bin-hadoop2.7.tgz
ls -l

ln -s  spark-2.4.3-bin-hadoop2.7  spark
ls -l
chown -R hadoop:hadoop spark
ls -l

su hadoop
[hadoop@master ~]$ vi .bash_profile
#아래 내용 추가
export SPARK_HOME=/usr/local/spark
export HADOOP_CONF_DIR=/usr/local/hadoop-2.7.7/etc/hadoop
export YARN_CONF_DIR=/usr/local/hadoop-2.7.7/etc/hadoop

===========================setting==========================================
# .bash_profile

# Get the aliases and functions
if [ -f ~/.bashrc ]; then
        . ~/.bashrc
fi

# User specific environment and startup programs

PATH=$PATH:$HOME/.local/bin:$HOME/bin
export JAVA_HOME=/usr/local/jdk1.8.0_221
export HADOOP_HOME=/usr/local/hadoop-2.7.7
export HIVE_HOME=/usr/local/hive
export HADOOP_CMD=/usr/local/hadoop-2.7.7/bin/hadoop
export HADOOP_STREAMING=/usr/local/hadoop-2.7.7/share/hadoop/tools/lib/hadoop-streaming-2.7.7.jar
export SPARK_HOME=/usr/local/spark
export HADOOP_CONF_DIR=/usr/local/hadoop-2.7.7/etc/hadoop
export YARN_CONF_DIR=/usr/local/hadoop-2.7.7/etc/hadoop
export SBT_HOME=/opt/sbt
export PATH=$PATH:$JAVA_HOME/bin:$HADOOP_HOME/bin:$HIVE_HOME/bin:$SPARK_HOME/bin:$SBT_HOME/bin


============================================================
[hadoop@master ~]$ spark-shell --master local verbose

//로컬 파일시스템에서 파일을 읽어들여서 RDD로 생성
scala> val file = sc.textFile("file:///usr/local/spark/README.md")
//RDD로부터 한 행(라인)단위로 처리 - 단어 분리 후 새로운 RDD 생성 저장
scala> val words = file.flatMap(_.split(" "))
//같은 단어끼리 모아서 요약(개수) 계산 - map형태로  단어와 출현횟수
scala> val result = words.countByValue     

scala> result.get("For").get


패키징 , 빌드 툴 설치 환경 설정 ==================================================
su -

tar zxvf /home/hadoop/Downloads/sbt-1.2.7.tgz -C /opt/

ls -l /opt/


[hadoop@master ~]$ vi .bash_profile
#아래 내용 추가
export SBT_HOME=/opt/sbt
....


[hadoop@master ~]$ sbt abount

#스파크 어플리케이션 프로젝트 폴더 생성
[hadoop@master ~]$ mkdir spark-simple-app

[hadoop@master ~]$ cd spark-simple-app

# 소스 코드 파일 저장 디렉토리 생성
[hadoop@master ~]$ mkdir -p src/main/scala  
#sbt 설정 파일 저장  디렉토리 생성
[hadoop@master ~]$ mkdir project

# 소스 코드 저장될 패키지 디렉토리 생성
[hadoop@master ~]$ mkdir -p src/main/scala/lab/spark/example
[hadoop@master ~]$ cd  src/main/scala/lab/spark/example
[hadoop@master ~]$ vi SundayCount.scala


[hadoop@master ~]$ cd ~/spark-simple-app
[hadoop@master ~]$ vi build.sbt

name := "spark-simple-app"
version := "0.1"
scalaVersion := "2.11.12"
libraryDependencies ++= Seq("org.apache.spark" % "spark-core_2.11" % "2.4.3" % "provided", "joda-time" % "joda-time" % "2.8.2")
assemblyOption in assembly := (assemblyOption in assembly).value.copy(includeScala = false)


[hadoop@master ~]$ cd project
[hadoop@master ~]$ vi plugins.sbt

addSbtPlugin("com.eed3si9n" % "sbt-assembly" % "0.14.10")


#어플리케이션 빌드
[hadoop@master ~]$ cd ~/spark-simple-app
[hadoop@master ~]$ sbt assembly


#어플리케이션 빌드 성공 후 결과
프로젝트 루트 디렉토리(~/spark-simple-app) 밑에 target 디렉토리 아래 scalar-2.11 디렉토리아래에 jar파일이 생성됨


[hadoop@master ~]$ vi date.txt

#하둡 파일 시스템에 date.txt파일 업로드
[hadoop@master ~]$ hadoop fs -mkdir  /data/spark/
[hadoop@master ~]$ hadoop fs -put date.txt  /data/spark/
 
[hadoop@master ~]$ spark-submit --master local 
--class lab.spark.example.SundayCount 
--name SundayCount  
~/spark-simple-app/target/scala-2.11/spark-simple-app-assembly-0.1.jar  
/data/spark/date.txt

```



## 2. spark-shell 에서 실습

```scala
val rdd = sc.parallelize( 1 to 10 )
val result = rdd.collect
println(result.mkString(", "))

val result2 = rdd.count
println( result2 )

///////////////////////////////////////////////////////////////////
val rdd = sc.parallelize( 1 to 5 )
val result = rdd.map( _ + 1 )
println( result.collect.mkString(", " ) )

///////////////////////////////////////////////////////////////////
val fruits = List( "apple, orange", 
                   "grape, apple, mango", 
                   "blueberry, tomato, oragne")
val rdd1 = sc.parallelize(fruits)
val rdd2 = rdd1.flatMap(_.split(","))
print(rdd2)
print(rdd2.collect.mkString(", "))

///////////////////////////////////////////////////////////////////

val rdd2 = rdd1.flatMap( log => {
  //apple이라는 단어가 포함된 경우만 처리 
  if(log.contains("apple")) {
     Some(log.indexOf("apple"))
  } else {
     None
  }
 })


////////////////////////////////////////////////////////////////////
val rdd1 = sc.parallelize( 1 to 10, 3)
val rdd2 = rdd1.mapPartitions(numbers => {
   print("DB 연결 !!!")
   numbers.map {
      number => number +1
   }
})
println(rdd2.collect.mkString(", "))


///////////////////////////////////////////////////////////////////

val rdd2 = rdd1.mapPartitionsWithIndex((idx, numbers)  => {
   numbers.flatMap {
      case number if idx == 1 => Option(number + 1)
      case _                  => None
   }
})
println(rdd2.collect.mkString(", "))

///////////////////////////////////////////////////////////////////
val rdd = sc.parallelize(List("a", "b", "c")).map((_, 1))
val result = rdd.mapValues( i => i+1 )
println(result.collect.mkString("\t"))


///////////////////////////////////////////////////////////////////

val rdd = sc.parallelize(Seq((1, "a, b"), (2, "a, c"), (3, "d, e")))
val result = rdd.flatMapValues( _.split(","))
println(result.collect.mkString("\t")) 

///////////////////////////////////////////////////////////////////

val rdd = sc.parallelize(Seq((1, "a, b"), (2, "a, c"), (3, "d, e")))
val result = rdd.flatMapValues( _.split(","))
println(result.collect.mkString("\t")) 

///////////////////////////////////////////////////////////////////
val rdd = sc.parallelize( List(("k1", "v1"), ("k2", "v2"), ("k1", "v3") ))
val rdd2 = sc.parallelize( List(("k1", "v4")))
val result = rdd.cogroup(rdd2)
result.collect.foreach {
   case (k, (v_1, v_2)) => {
        println(s"($k, [${v_1.mkString(",")}], [${v_2.mkString(", ")}])")
   }
}
////////////////////////////////////////////////////////////////////
<cartesian()>

val rdd1 = sc.parallelize( List(1, 2, 3))
val rdd2 = sc.parallelize( List("a", "b", "c"))
val result = rdd1.cartesian(rdd2)
println(result.collect.mkString(", "))

////////////////////////////////////////////////////////////////////
<substract()>

val rdd1 = sc.parallelize( List("a", "b", "c", "d", "e"))
val rdd2 = sc.parallelize( List("d", "e"))
val result = rdd1.substract(rdd2)
println(result.collect.mkString(", "))

////////////////////////////////////////////////////////////////////
<union()>

val rdd1 = sc.parallelize( List("a", "b", "c"))
val rdd2 = sc.parallelize( List("d", "e", "f"))
val result = rdd1.union(rdd2)
println(result.collect.mkString(", "))


////////////////////////////////////////////////////////////////////
<intersection()>

val rdd1 = sc.parallelize( List( "a", "a", "b", "c"))
val rdd2 = sc.parallelize( List( "a", "a", "c", "c"))
val result = rdd1.intersection(rdd2)
println(result.collect.mkString(", "))

////////////////////////////////////////////////////////////////////
<join()>

val rdd1 = sc.parallelize( List("a", "b", "c", "d", "e")).map(_, 1))
val rdd2 = sc.parallelize( List("b", "c", "f")).map((_, 2))
val result = rdd1.join(rdd2)
println(result.collect.mkString(", "))


////////////////////////////////////////////////////////////////////
<>
```

## 3. WordCount실습

```scala
#스파크 어플리케이션 프로젝트 폴더 생성
[hadoop@master ~]$ mkdir wordcount-app

[hadoop@master ~]$ cd wordcount-app

# 소스 코드 파일 저장 디렉토리 생성
[hadoop@master ~]$ mkdir -p src/main/scala  
#sbt 설정 파일 저장  디렉토리 생성
[hadoop@master ~]$ mkdir project

# 소스 코드 저장될 패키지 디렉토리 생성
[hadoop@master ~]$ mkdir -p src/main/scala/lab/spark/example
[hadoop@master ~]$ cd  src/main/scala/lab/spark/example
[hadoop@master ~]$ vi WordCount.scala


[hadoop@master ~]$ cd ~/wordcount-app
[hadoop@master ~]$ vi build.sbt

name := "spark-simple-app"
version := "0.1"
scalaVersion := "2.11.12"
libraryDependencies ++= Seq("org.apache.spark" % "spark-core_2.11" % "2.4.3" % "provided")
assemblyOption in assembly := (assemblyOption in assembly).value.copy(includeScala = false)


[hadoop@master ~]$ cd project
[hadoop@master ~]$ vi plugins.sbt

addSbtPlugin("com.eed3si9n" % "sbt-assembly" % "0.14.10")


#어플리케이션 빌드
[hadoop@master ~]$ cd ~/wordcount-app
[hadoop@master ~]$ sbt assembly

#데이터 소스 생성
[hadoop@master ~]$ vi simple-words.txt
cat
dog
.org
cat
rabbit
bear
cat
&&
tiger
dog
rabbit
100
bear
tiger
cat
rabbit
?bear

#하둡 파일 시스템에 simple-words.txt파일 업로드
[hadoop@master ~]$ hadoop fs -mkdir  /data/spark/
[hadoop@master ~]$ hadoop fs -put simple-words.txt  /data/spark/

[hadoop@master ~]$ spark-submit --master local 
--class lab.spark.example.WordCount
--name WordCount  
~/wordcount-app/target/scala-2.11/wordcount-app-assembly-0.1.jar  
/data/spark/simple-words.txt
```

