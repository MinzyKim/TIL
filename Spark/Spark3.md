# Spark3

## 1. Spark SQL

### 1. 구조화된 각종 데이터셋 다루기

- 파일 형식의 구조화된 데이터셋 - dessertDF.write

  ```scala
  scala> case class Dessert(menuId: String, name: String, price: Int, kcal: Int)
  defined class Dessert
  
  scala> val dessertRDD = sc.textFile("/data/spark/dessert-menu.csv")
  dessertRDD: org.apache.spark.rdd.RDD[String] = /data/spark/dessert-menu.csv MapPartitionsRDD[1] at textFile at <console>:24
  
  scala> val dessertDF = dessertRDD.map{ record =>
       | val splitRecord = record.split(",")
       | val menuId = splitRecord(0)
       | val name = splitRecord(1)
       | val price = splitRecord(2).toInt
       | val kcal = splitRecord(3).toInt
       | Dessert(menuId, name, price, kcal)
       | }.toDF
  dessertDF: org.apache.spark.sql.DataFrame = [menuId: string, name: string ... 2 more fields]
  
  scala> val dfWriter = dessertDF. write
  dfWriter: org.apache.spark.sql.DataFrameWriter[org.apache.spark.sql.Row] = org.apache.spark.sql.DataFrameWriter@37ea5612
  
  scala> dfWriter.format("parquet").save("/output/dessert-parquet")
  
  scala> val dfReader = spark.read
  dfReader: org.apache.spark.sql.DataFrameReader = org.apache.spark.sql.DataFrameReader@12ceff30
  
  scala> val dessertDF2 =dfReader.format("parquet").load("/output/dessert-parquet")
  dessertDF2: org.apache.spark.sql.DataFrame = [menuId: string, name: string ... 2 more fields]
  
  scala> dessertDF2.orderBy($"name").show(3)
  +------+-------------+-----+----+
  |menuId|         name|price|kcal|
  +------+-------------+-----+----+
  |  D-11|고구마 파르페| 6500| 650|
  |  D-12|    녹차 빙수| 3800| 320|
  |   D-7|  녹차 파르페| 4500| 380|
  +------+-------------+-----+----+
  only showing top 3 rows
  ```

  - `parquet`은 spark에서 쓰는 기본 파일 형식



- 테이블 형식의 구조화 된 데이터셋 - SaveAsTable

  ```scala
  scala> dessertDF.write.format("parquet").saveAsTable("dessert_tbl_parquet")
  19/09/02 19:08:16 WARN DataNucleus.General: Plugin (Bundle) "org.datanucleus" is already registered. Ensure you dont have multiple JAR versions of the same plugin in the classpath. The URL "file:/usr/local/spark-2.4.3-bin-hadoop2.7/jars/datanucleus-core-3.2.10.jar" is already registered, and you are trying to register an identical plugin located at URL "file:/usr/local/spark/jars/datanucleus-core-3.2.10.jar."
  19/09/02 19:08:16 WARN DataNucleus.General: Plugin (Bundle) "org.datanucleus.api.jdo" is already registered. Ensure you dont have multiple JAR versions of the same plugin in the classpath. The URL "file:/usr/local/spark-2.4.3-bin-hadoop2.7/jars/datanucleus-api-jdo-3.2.6.jar" is already registered, and you are trying to register an identical plugin located at URL "file:/usr/local/spark/jars/datanucleus-api-jdo-3.2.6.jar."
  19/09/02 19:08:16 WARN DataNucleus.General: Plugin (Bundle) "org.datanucleus.store.rdbms" is already registered. Ensure you dont have multiple JAR versions of the same plugin in the classpath. The URL "file:/usr/local/spark-2.4.3-bin-hadoop2.7/jars/datanucleus-rdbms-3.2.9.jar" is already registered, and you are trying to register an identical plugin located at URL "file:/usr/local/spark/jars/datanucleus-rdbms-3.2.9.jar."
  19/09/02 19:08:24 WARN metastore.ObjectStore: Version information not found in metastore. hive.metastore.schema.verification is not enabled so recording the schema version 1.2.0
  19/09/02 19:08:24 WARN metastore.ObjectStore: Failed to get database default, returning NoSuchObjectException
  
  scala> spark.read.format("parquet").table("dessert_tbl_parquet").show(3)
  19/09/02 19:09:33 WARN metastore.ObjectStore: Failed to get database global_temp, returning NoSuchObjectException
  +------+-------------+-----+----+
  |menuId|         name|price|kcal|
  +------+-------------+-----+----+
  |   D-0|초콜릿 파르페| 4900| 420|
  |   D-1|  푸딩 파르페| 5300| 380|
  |   D-2|  딸기 파르페| 5200| 320|
  +------+-------------+-----+----+
  only showing top 3 rows
  
  
  scala> spark.sql("select * from dessert_tbl_parquet LIMIT 3").show
  +------+-------------+-----+----+
  |menuId|         name|price|kcal|
  +------+-------------+-----+----+
  |   D-0|초콜릿 파르페| 4900| 420|
  |   D-1|  푸딩 파르페| 5300| 380|
  |   D-2|  딸기 파르페| 5200| 320|
  +------+-------------+-----+----+
  ```



- SafeMode 

  - 출력 장소에 지정한 데이터셋이 이미 존재할 경우 어떻게 처리 할 지 결정해 준다.
    - SaveMode.ErrorIfExsists = 예외를 발생(디폴트)
    - SaveMode.Append = 기존 데이터셋에 덧붙이기
    - SaveMode.Overwrite = 기존 데이터셋에 덮어쓰기
    - SaveMode.Ignore = 기존 데이터셋을 변경하지 않음

  ```scala
  [hadoop@master ~]$ hadoop fs -mkdir /output/dessert_json
  
  scala> dessertDF.write.save("/output/dessert_json")
  org.apache.spark.sql.AnalysisException: path hdfs://master:9000/output/dessert_json already exists.;
  
  scala> import org.apache.spark.sql.SaveMode
  import org.apache.spark.sql.SaveMode
  
  scala> dessertDF.write.format("json").mode(SaveMode.Overwrite).save("/output/dessert_json")
  ```

  

- 명시적으로 스키마 정보 부여

  - 데이터셋을 읽어 들일 때 DataFrame에 부여되는 스키마 정보는 프로바이더에 의해서 자동 부여되지만 사용자가 명시적으로 부여도 가능하다. 
    - 예를 들어, DecimalType의 데이터셋을 소수점 n자리로 설정하고 싶을 때도 가능하다.

  ```scala
  scala> import java.math.BigDecimal
  scala> case class DecimalTypeContainer(data: BigDecimal)
  scala> val bdContainerDF = sc.parallelize(
  		List(new BigDecimal("12345.67899999999"))
  		).map(data => DecimalTypeContainer(data)).toDF
  
  scala> bdContainerDF.printSchema
  root
  |-- data: decimal(38.18) (nullable = true)
  
  scala> bdContainerDF.show(false)
  +-------------------+
  |data               |
  +-------------------+
  |12345.6789999999999|
  +-------------------+
  
  
  scala> bdContainerDF.write.format("orc").save("/data/spark/bdContainerORC")
  
  scala> val bdContainerORCDF = spark.read.format("orc").load("/data/spark/bdContainerORC")
  bdContainerORCDF: org.apache.spark.sql.DataFrame = [data: decimal(38,18)]
  
  scala> bdContainerORCDF.printSchema
  root
   |-- data: decimal(38,18) (nullable = true)
  
  
  scala> bdContainerORCDF.show(false)
  +------------------------+
  |data                    |
  +------------------------+
  |12345.678999999999900000|
  +------------------------+
  
  ```

  

- 파티셔닝과 실행계획 비교

  - DataFrameWriter의 partitionBy 메서드를 이용하면 DataFrame이 나타내는 데이터셋을 파티셔닝해서 출력 가능

  ```scala
  scala> import org.apache.spark.sql.types.DataTypes._
  import org.apache.spark.sql.types.DataTypes._
  
  scala> val priceRangeDessertDF = dessertDF.select(
       | ((($"price" / 1000) cast IntegerType) * 1000) as "price_range",
       | dessertDF("*")
       | )
  priceRangeDessertDF: org.apache.spark.sql.DataFrame = [price_range: int, menuId: string ... 3 more fields]
  
  scala> priceRangeDessertDF.write.format("parquet").
       | save(
       | "/data/spark/price_range_dessert_parquet_non_partitioned")
  
  scala> priceRangeDessertDF.write.format("parquet").partitionBy("price_range").
       | save("/data/spark/price_range_dessert_parquet_partitioned")
  
  ```

  