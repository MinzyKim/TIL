# Spark4

## 1. MLlib

### 1. 파이프 라인

- 개념 :

  - 여러 종류의 알고리즘을 순차적으로 실행할 수 있게 지원하는 고차원 API이며, 파이프라인 API를 이용해 머신러닝을 위한 워크 플로우를 생성할 수 있다.

  ```def
   컴퓨터 과학에서 파이프라인(영어: pipeline)은 한 데이터 처리 단계의 출력이 다음 단계의 입력으로 이어지는 형태로 연결된 구조를 가리킨다. 이렇게 연결된 데이터 처리 단계는 한 여러 단계가 서로 동시에, 또는 병렬적으로 수행될 수 있어 효율성의 향상을 꾀할 수 있다. 각 단계 사이의 입출력을 중계하기 위해 버퍼가 사용될 수 있다.
   출처 - 위키백과
  ```

  

- 특징 :

  - 파이프라인은 데이터프레임을 사용한다.

    •Transformer – org.apache.spark.ml 패키지에 선언된 추상 클래스. 데이터프레임을 변형해 새로운 데이터프레임을 생성하는 용도로 사용

    •Estimator - org.apache.spark.ml 패키지에 선언된 추상 클래스. 데이터프레임에 알고리즘을 적용해 새로운 트랜스포머를 생성하는 역할을 합니다.

    •Pipeline - org.apache.spark.ml 패키지에 선언된 클래스. 여러 알고리즘을 순차적으로 실행할 수 있는 워크플로우를 생성하는 평가자. 하나의 파이프라인은 여러 개의 파이프라인 스테이지(PipelineStage)로 구성되며, 등록된 파이프라인 스테이지들은 우선순위에 따라 순차적으로 실행됩니다.

    •ParamMap : 평가자나 트랜스포머에 파라미터를 전달하기 위한 목적으로 사용되는 클래스

- 실습

```scala
import org.apache.spark.ml.{Pipeline, PipelineModel}
import org.apache.spark.ml.classification.{LogisticRegression,LogisticRegressionModel}
import org.apache.spark.ml.feature.VectorAssembler
import org.apache.spark.sql.SparkSession
object PipelineSample {  
    def main(args: Array[String]) {   
        val spark = SparkSession.builder().appName("PipelineSample").master("local[*]").getOrCreate()// 훈련용 데이터 (키, 몸무게, 나이, 성별)    
        val training = spark.createDataFrame(Seq((161.0, 69.87, 29, 1.0),
                                                 (176.78, 74.35, 34, 1.0),
                                                 (159.23, 58.32, 29,
                                                  0.0))).toDF("height", "weight", "age", "gender")    
        training.cache()// 테스트용 데이터    
        val test = spark.createDataFrame(Seq((169.4, 75.3, 42),
                                             (185.1, 85.0, 37),
                                             (161.6, 61.2, 28))).toDF("height", "weight", "age")    
        training.show(false)    
        val assembler = new VectorAssembler().setInputCols(Array("height", "weight", "age"))      
        										.setOutputCol("features")    // training 데이터에 features 컬럼 추가    
        val assembled_training = assembler.transform(training)    									assembled_training.show(false)// 모델 생성 알고리즘 (로지스틱 회귀 평가자)    
        val lr = new LogisticRegression()      
        					.setMaxIter(10)      
        					.setRegParam(0.01)  
        					.setLabelCol("gender")// 모델 생성    
        val model = lr.fit(assembled_training)// 예측값 생성, FIT = 모델생성해주는 메소드
        model.transform(assembled_training).show()// 파이프라인    
        val pipeline = new Pipeline().setStages(Array(assembler, lr))// 파이프라인 모델 생성    
        val pipelineModel = pipeline.fit(training)// 파이프라인 모델을 이용한 예측값 생성    
        pipelineModel.transform(training).show()  
        val path1 = "/Users/beginspark/Temp/regression-model"  
        val path2 = "/Users/beginspark/Temp/pipelinemodel"// 모델 저장    
        model.write.overwrite().save(path1)    		
        pipelineModel.write.overwrite().save(path2)// 저장된 모델 불러오기    
        val loadedModel = LogisticRegressionModel.load(path1)    
        val loadedPipelineModel = PipelineModel.load(path2)
        spark.stop  
    }
}

```

- 자바 코드 예

```java
import org.apache.spark.ml.Pipeline;
import org.apache.spark.ml.PipelineModel;
import org.apache.spark.ml.PipelineStage;
import org.apache.spark.ml.classification.LogisticRegression;
import org.apache.spark.ml.classification.LogisticRegressionModel;
import org.apache.spark.ml.feature.VectorAssembler;
import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Row;
import org.apache.spark.sql.RowFactory;
import org.apache.spark.sql.SparkSession;
import org.apache.spark.sql.types.DataTypes;
import org.apache.spark.sql.types.StructField;
import org.apache.spark.sql.types.StructType;
import java.util.Arrays;
import java.util.List;
public class PipelineSample {
public static void main(String[] args) throws Exception {
    SparkSession spark = SparkSession.builder()
            .appName("PipelineSample")
            .master("local[*]")
 .getOrCreate();
   StructField sf1 = DataTypes.createStructField("height", DataTypes.DoubleType, true);
   StructField sf2 = DataTypes.createStructField("weight", DataTypes.DoubleType, true);
   StructField sf3 = DataTypes.createStructField("age", DataTypes.IntegerType, true);
   StructField sf4 = DataTypes.createStructField("label", DataTypes.DoubleType, true);
  StructType schema1 = DataTypes.createStructType(Arrays.asList(sf1, sf2, sf3, sf4));
    List<Row> rows1 = Arrays.asList(RowFactory.create(161.0, 69.87, 29, 1.0),
        RowFactory.create(176.78, 74.35, 34, 1.0),
            RowFactory.create(159.23, 58.32, 29, 0.0));
    // 훈련용 데이터 (키, 몸무게, 나이, 성별)
    Dataset<Row> training = spark.createDataFrame(rows1, schema1);
training.cache();
List<Row> rows2 = Arrays.asList(RowFactory.create(169.4, 75.3, 42),
            RowFactory.create(185.1, 85.0, 37),
            RowFactory.create(161.6, 61.2, 28));
    StructType schema2 = DataTypes.createStructType(Arrays.asList(sf1, sf2, sf3));
    // 테스트용 데이터
    Dataset<Row> test = spark.createDataFrame(rows2, schema2);
   training.show(false);
   VectorAssembler assembler = new VectorAssembler();
    assembler.setInputCols(new String[]{"height", "weight", "age"});
   assembler.setOutputCol("features");
   Dataset<Row> assembled_training = assembler.transform(training);
  assembled_training.show(false);
// 모델 생성 알고리즘 (로지스틱 회귀 평가자)
 LogisticRegression lr = new LogisticRegression();
 lr.setMaxIter(10).setRegParam(0.01);
 // 모델 생성
   LogisticRegressionModel model = lr.fit(assembled_training);
 // 예측값 생성
 model.transform(assembled_training).show();
    // 파이프라인
   Pipeline pipeline = new Pipeline();
    pipeline.setStages(new PipelineStage[]{assembler, lr});
    // 파이프라인 모델 생성
   PipelineModel pipelineModel = pipeline.fit(training); 
    // 파이프라인 모델을 이용한 예측값 생성
    pipelineModel.transform(training).show();
    String path1 = "/output/sparkmllib/regression-model" ;
    String path2 = "/output/sparkmllib/pipelinemodel";
    // 모델 저장
   model.write().overwrite().save(path1);
   pipelineModel.write().overwrite().save(path2);
    // 저장된 모델 불러오기
  LogisticRegressionModel loadedModel = LogisticRegressionModel.load(path1);
  PipelineModel loadedPipelineModel = PipelineModel.load(path2);
    spark.stop();
  }
}
```

- 파이썬 코드 예

```python
from pyspark.ml.classification import LogisticRegression
from pyspark.ml.classification import LogisticRegressionModel
from pyspark.ml.feature import VectorAssembler
from pyspark.ml.pipeline import Pipeline
from pyspark.ml.pipeline import PipelineModel
from pyspark.sql import SparkSession
spark = SparkSession
.builde
.appName("pipeline_sample")
.master("local[*]")
.getOrCreate()
# 훈련용 데이터 (키, 몸무게, 나이, 성별)
training = spark.createDataFrame([
  (161.0, 69.87, 29, 1.0),
 (176.78, 74.35, 34, 1.0),
(159.23, 58.32, 29, 0.0)]).toDF("height", "weight", "age", "gender")
training.cache()
# 테스트용 데이터
test = spark.createDataFrame([
(169.4, 75.3, 42),
 (185.1, 85.0, 37),
(161.6, 61.2, 28)]).toDF("height", "weight", "age")
training.show(truncate=False)
assembler = VectorAssembler(inputCols=["height", "weight", "age"], outputCol="features")
# training 데이터에 features 컬럼 추가
assembled_training = assembler.transform(training)
assembled_training.show(truncate=False)
# 모델 생성 알고리즘 (로지스틱 회귀 평가자)
lr = LogisticRegression(maxIter=10, regParam=0.01, labelCol="gender")
# 모델 생성
model = lr.fit(assembled_training)
# 예측값 생성
model.transform(assembled_training).show()
# 파이프라인
pipeline = Pipeline(stages=[assembler, lr])
# 파이프라인 모델 생성
pipelineModel = pipeline.fit(training)
# 파이프라인 모델을 이용한 예측값 생성
pipelineModel.transform(training).show()
 
  path1 = "/output/sparkmllib/regression-model" 
  path2 = "/output/sparkmllib/pipelinemodel" 
# 모델 저장
model.write().overwrite().save(path1)
pipelineModel.write().overwrite().save(path2)
# 저장된 모델 불러오기
loadedModel = LogisticRegressionModel.load(path1)
loadedPipelineModel = PipelineModel.load(path2)
spark.stop
```

```output
+------+------+---+------+-------------------+--------------------+--------------------+----------+
|height|weight|age|gender|           features|       rawPrediction|         probability|prediction|
+------+------+---+------+-------------------+--------------------+--------------------+----------+
| 161.0| 69.87| 29|   1.0| [161.0,69.87,29.0]|[-2.4890615171055...|[0.07662857486628...|       1.0|
|176.78| 74.35| 34|   1.0|[176.78,74.35,34.0]|[-1.5515034131417...|[0.17486923465734...|       1.0|
|159.23| 58.32| 29|   0.0|[159.23,58.32,29.0]|[2.48077740707283...|[0.92278320971457...|       0.0|
+------+------+---+------+-------------------+--------------------+--------------------+----------+


```

### 2. Tokenizer

- 개념 :
  - 공백문자를 기준으로 입력 문자열을 개별 단어의 배열로 변환하고 이 배열을 값으로 하는 새로운 컬럼을 생성하는 트랜스포머. 문자열을 기반으로 하는 특성 처리에 자주 사용됨

- 예시

```scala
import org.apache.spark.ml.feature.Tokenizer
import org.apache.spark.sql.SparkSession

object TokenizerSample {  
   def main(args: Array[String]) {    
       val spark = SparkSession .builder() .appName("TokenizerSample") .master("local[*]") .getOrCreate()    
       val data = Seq("Tokenization is the process", "Refer to the Tokenizer").map(Tuple1(_))    
       val inputDF = spark.createDataFrame(data).toDF("input")    
       val tokenizer = new Tokenizer().setInputCol("input").setOutputCol("output")   
       val outputDF = tokenizer.transform(inputDF)    
           outputDF.printSchema()    
           outputDF.show(false)    
          spark.stop  
    }
}
```

```output
+---------------------------+--------------------------------+
|input                      |output                          |
+---------------------------+--------------------------------+
|Tokenization is the process|[tokenization, is, the, process]|
|Refer to the Tokenizer     |[refer, to, the, tokenizer]     |
+---------------------------+--------------------------------+


```

### 3. TF-IDF알고리즘

- 개념 :
  - 여러 문서 집합에서 특정 단어가 특정 문서 내에서 가지는 중요도를 수치화한 통계적 수치

### 4. StringIndexer

- 개념 :
  - 문자열 컬럼에 대응하는 숫자형 컬럼을 생성하는 평가자

- 특징 :
  - 문자열 레이블 컬럼에 적용하며 해당 컬러의 모든 문자열에 노출 빈도에 따른 인덱스를 부여해서 숫자로 된 새로운 레이블 컬럼을 생성
  - StringIndexer는 트랜스포머가 아닌 평가자로서 fit() 메서드를 이용해 stringIndexerModel을 생성하며 이 모델을 이용해 문자열 인코딩을 수행

- 실습

```scala
import org.apache.spark.ml.feature.{IndexToString, StringIndexer}

  val df1 = spark.createDataFrame(Seq(      (0, "red"),      (1, "blue"),      (2, "green"),      (3, "yellow"))).toDF("id", "color")   
  val strignIndexer = new StringIndexer().setInputCol("color") .setOutputCol("colorIndex") .fit(df1)    
  val df2 = strignIndexer.transform(df1)    
  df2.show(false)    
  val indexToString = new IndexToString() .setInputCol("colorIndex") .setOutputCol("originalColor") 
  val df3 = indexToString.transform(df2)   
  df3.show(false)    
```

```output
scala>   df2.show(false)    
+---+------+----------+
|id |color |colorIndex|
+---+------+----------+
|0  |red   |3.0       |
|1  |blue  |0.0       |
|2  |green |2.0       |
|3  |yellow|1.0       |
+---+------+----------+


scala>   df3.show(false)    
+---+------+----------+-------------+
|id |color |colorIndex|originalColor|
+---+------+----------+-------------+
|0  |red   |3.0       |red          |
|1  |blue  |0.0       |blue         |
|2  |green |2.0       |green        |
|3  |yellow|1.0       |yellow       |
+---+------+----------+-------------+


```

## 2. 회귀에 의한 매출 분석

- MLlib 입력 데이터 형으로 변환하기 위해 DataFrame으로 생성

```scala
hadoop fs -mkdir /data/sales
hadoop fs -put weather.csv  /data/sales/
hadoop fs -put sales.csv  /data/sales/


1단계 : 데이터 전처리
MLlib 입력 데이터 형으로 변환하기 위해 DataFrame으로 생성

shema 정의 - case class 정의

case class Weather( date: String,
                    day_of_week: String,
                    avg_temp: Double,
                    max_temp: Double,
                    min_temp: Double,
                    rainfall: Double,
                    daylight_hours: Double,
                    max_depth_snowfall: Double,
                    total_snowfall: Double,
                    solar_radiation: Double,
                    mean_wind_speed: Double,
                    max_wind_speed: Double,
                    max_instantaneous_wind_speed: Double,
                    avg_humidity: Double,
                    avg_cloud_cover: Double)
case class Sales(date: String, sales: Double)


import spark.implicits._
import org.apache.spark.mllib.regression.{LabeledPoint,LinearRegressionWithSGD}
import org.apache.spark.mllib.linalg.Vectors
import org.apache.spark.mllib.feature.StandardScaler
import org.apache.spark.mllib.evaluation.RegressionMetrics
import org.apache.spark.sql.functions.udf



// 기상 데이터를 읽어 DataFrame으로 변환한다
val weatherCSVRDD = sc.textFile("/data/sales/weather.csv")
val headerOfWeatherCSVRDD = sc.parallelize(Array(weatherCSVRDD.first))
val weatherCSVwithoutHeaderRDD = weatherCSVRDD.subtract(headerOfWeatherCSVRDD)
val weatherDF = weatherCSVwithoutHeaderRDD.map(_.split(",")).
      map(p => Weather(p(0),
      p(1),
      p(2).trim.toDouble,
      p(3).trim.toDouble,
      p(4).trim.toDouble,
      p(5).trim.toDouble,
      p(6).trim.toDouble,
      p(7).trim.toDouble,
      p(8).trim.toDouble,
      p(9).trim.toDouble,
      p(10).trim.toDouble,
      p(11).trim.toDouble,
      p(12).trim.toDouble,
      p(13).trim.toDouble,
      p(14).trim.toDouble
    )).toDF()

// 매출 데이터를 읽어 DataFrame으로 변환한다
val salesCSVRDD = sc.textFile("/data/sales/sales.csv")
val headerOfSalesCSVRDD = sc.parallelize(Array(salesCSVRDD.first))
val salesCSVwithoutHeaderRDD = salesCSVRDD.subtract(headerOfSalesCSVRDD)
val salesDF = salesCSVwithoutHeaderRDD.map(_.split(",")).map(p => Sales(p(0), p(1).trim.toDouble)).toDF()

//정의된 스키마 확인
println(weatherDF.printSchema)  
println(salesDF.printSchema)   
// 데이터의 전처리(날짜 기준으로 조인 후, 요일 컬럼값을 수치화하고, 요일컬럼제거후 , 수치화된 주말컬럼 추가)
val salesAndWeatherDF = salesDF.join(weatherDF, "date")
val isWeekend = udf((t: String) => if(t.contains("일") || t.contains("토")) 1d 
                                       else 0d)
val replacedSalesAndWeatherDF = salesAndWeatherDF.withColumn("weekend", isWeekend(salesAndWeatherDF("day_of_week"))).drop("day_of_week")

//매출에 영향을 주는 독립변수만 추출하여 새로운 데이터 프레임 생성
//매출에 영향을 주는 독립변수 평균기온, 일강수량, 휴일을 선택

val selectedDataDF = replacedSalesAndWeatherDF.select("sales", "avg_temp", "rainfall", "weekend")

//데이터프레임을 회귀분석을 위한 Vector, LabeledPoint로 생성
 val labeledPointsRDD = selectedDataDF.map(row => LabeledPoint(row.getDouble(0),
 Vectors.dense(row.getDouble(1),row.getDouble(2),row.getDouble(3))))

//데이터 특성을 표준화(평균 0, 분산1인 스케일러 사용)
// 데이터의 표준화 (평균값을 조정하고 스케이링을 개별적으로 유효화 또는 무효화를 할 수 있다
//val scaler = new StandardScaler(withMean = true, withStd = true).fit(labeledPointsRDD.map(x => x.features))

val scaler = new StandardScaler().fit(labeledPointsRDD.map(x =>x.features))
val scaledLabledPointsRDD = labeledPointsRDD.map(x => LabeledPoint(x.label, scaler.transform(x.features)))


// 선형회귀 모델을 작성한다
    val numIterations = 20
    scaledLabledPointsRDD.cache
    val linearRegressionModel = LinearRegressionWithSGD.train(scaledLabledPointsRDD, numIterations)
    println("weights :" + linearRegressionModel.weights)

// 알고리즘에 미지의 데이터를 적용해 예측한다
    val targetDataVector1 = Vectors.dense(15.0,15.4,1)
    val targetDataVector2 = Vectors.dense(20.0,0,0)
    val targetScaledDataVector1 = scaler.transform(targetDataVector1)
    val targetScaledDataVector2 = scaler.transform(targetDataVector2)
    val result1 = linearRegressionModel.predict(targetScaledDataVector1)
    val result2 = linearRegressionModel.predict(targetScaledDataVector2)
    println("avg_tmp=15.0,rainfall=15.4,weekend=true : sales = " + result1)
    println("avg_tmp=20.0,rainfall=0,weekend=false : sales = " + result2)

// 입력 데이터를 분할하고 평가한다
    val splitScaledLabeledPointsRDD = scaledLabledPointsRDD.randomSplit(Array(0.6, 0.4), seed = 11L)
    val trainingScaledLabeledPointsRDD = splitScaledLabeledPointsRDD(0).cache()
    val testScaledLabeledPointsRDD = splitScaledLabeledPointsRDD(1)
    val linearRegressionModel2 = LinearRegressionWithSGD.train(trainingScaledLabeledPointsRDD, numIterations)
    val scoreAndLabels = testScaledLabeledPointsRDD.map { point =>
     val score = linearRegressionModel2.predict(point.features)
      (score, point.label)
    }

val metrics = new RegressionMetrics(scoreAndLabels)
    println("RMSE = "+ metrics.rootMeanSquaredError)
    // 작성한 모델을 보존한다
linearRegressionModel.save(sc, "/output/mllib/model/") 

val model2 = linearRegressionModel.load(sc, "/output/mllib/model/")

import org.apache.spark.mllib.regression.LinearRegressionModel
val model2 = LinearRegressionModel.load(sc, "/output/mllib/model/")

//스파크를 실행시킨 디렉토리 경로 아래에 파일 생성
linearRegressionModel.toPMML("model.pmml")

[hadoop@master mllib]$ cat model.pmml

```

