# Hadoop4일차

### 1. 분산 컴퓨팅

client가 요청 -> 프로그램 시작 -> 어플리케이션이 n개의 host에 나눠서 접속

- 물리적으로 하나 이상의 호스트에 연결, 처리, 제어

#### 1-1 필요조건

- 장애허용 
  - 한 host에 문제가 발생했을 때, 나머지 host에서 나눠서 처리해야 함
  - 분산 클러스터 노드 중 하나가 문제가 생겨도 메인 컴퓨팅 프로세스에 부정적인 영향을 주지 않아야 한다. (프로세스 실패가 발생하지 않아야 한다.)
- 복구능력 
  - 분산 클러스터 노드에서 수행중인 작업이 실패하더라도 작업으로부터 어떤 데이터도 손실되어선 안된다.

- 선형적 확장성
  - 추가된 호스트의 cpu, memory에 맞게 성능이 선형적으로 증가해야 한다.
  - 컴퓨팅 능력, 스토리지 공간 확장 등 성능도 선형적으로 증가해야 한다.



### 2. 하둡 아키텍쳐와 하둡 클러스터

#### 2.1 하둡 아키텍처

- HDFS, Yarn, MapReduce, API
- 하둡 클러스터를 기반으로 구성

#### 2.2 하둡 클러스터

- 하둡 분산파일시스템(HDFS)과 클러스터 리소스 매니저(Yarn)를 기반으로 하는 하둡 소프트웨어를 사용하는 컴퓨터들의 집합

- 장애 허용과 복구 능력을 위해 sharding, replication을 수행한다.

- 배치 처리, 파일 기반 처리(map의 처리 결과도 map처리된 datanode에 파일로 저장, reducer의 출력결과도 HDFS에 저장, disk기반, stream기반, sequenctial하게 처리)

  

### 3. 하둡의 노드

- Hadoop2.0부터 마스터 노드 2개 이상 구성 가능 고가용성(HA)

  [^HA]: High Availability

  을 지원한다.

- 마스터 노드(Active, Standby) 

  - 하둡 클러스터의 작업을 중재
  - 하둡 클라이언트는 파일을 저장, 읽고, 처리하려면 master노드에 접속
  - namenode가 구성되고, 파일을 저장, 쓰기 요청에 대해서 파일시스템의 메타정보 관리
  - mapreduce 작업의 중재하는 프로세스 JobTracker가 구성

- 워커 노드(슬레이드 노드)

  - 마스터 노드의 지시를 받아서 명령을 수행 (실제 데이터를 저장하고, 데이터 처리 프로세싱하는 노드, TaskTracker)

- HDFS는 HDFS의 스토리지를 관리
  - NameNode 
    -  HDFS 파일 시스템 디렉토리 트리와 파일의 위치등 HDFS 스토리지 관련 메타 정보(블럭 데이터를 데이터노드에 매핑)를 관리
    - 파일, 디렉토리, 생성, 열기, 쓰기 오퍼레이션 수행
    - 어떤 데이터 노드에 복제되고, 복제 후에 삭제할지 결정
    - 데이터 노드에서 보내온 하트비트와 블럭 리포트를 처리 (블럭 위치 유지, 데이터 노드의 상태 관리)
  - SecondaryName 
    - HDFS 스토리지 메타 정보 업데이트(기본 1시간 간격, fsimage파일과 editlog파일을 병합)
  - DataNode
    - 마스터 노드에 접속 유지, 3초 간격으로 heart beat를 보낸다.
    - block report를 주기적으로 전송
    - 마스터 노드의 요청을 처리(block저장, block 삭제)
    - 로컬 파일 시스템에 블록을 저장
    - 데이터에 대한 읽기, 쓰기 수행
    - 데이터 블록 생성 및 삭제 수행
    - 클러스터에 데이터 블럭 복제
    - 주기적으로 heartbeat와 블럭 리포트 전송
- Yarn 서비스
  - resource manager - 마스터 노드에서 실행, 클러스터의 리소스를 나눠주는 역할, Task들에 대한 스케줄링
  - node manager - 워크 노드에서 실행, Task들을 실행시키고 관리, resouurce manager와 관계 유지, Task의 상태, 노드 상태 관리
  - application manager - 클러스터상에서의 메인이 되는 마스터 프로세스로서 어플리케이션별로 하나씩 실행됨, 클러스터에서 실행되는 어플리케이션의 실행 조율, 리소스 매니저와 통신(관계 유지) 하면서 리소스 조절



### 4. Join

#### 4.1 맵 사이드 조인

- setup 메서드에서 조인될 데이터를 준비합니다.

- Hashtable을 전역변수로 선언하고 Hashtable에 데이터를 저장함

- 읽어들일 데이터가 분산캐시에 등록되어 있어야 함

-  파일의 유형에 따라 등록하는 파일이 다름

- map 메서드에서 write할 때 Hashtable의 값을 키로 저장합니다.

- 실행하기 전에 분산캐시로 사용할 파일을 HDFS 에 업로드 해야 함

- 조인 데이터 준비

  http://stat-computing.org/dataexpo/2009/supplemental-data.html

  미국 항공 코드 데이터 다운로드

  항공기 코드와 항공기 이름 콤마(,)로 구분되어 있음

  업로드

   실행 하기 전에 다운로드 받은 데이터를 HDFS에 업로드 해야 합니다.

  hadoop fs -put ./carriers.csv  /data/metadata/carriers.csv

  항공기 데이터

  hadoop fs -put ./2008.csv /data/airline

  운항 통계 데이터 

- 코드

  - MapperWithMapsideJoin

  ```java
  package lab.hadoop.join;
  
  import java.io.BufferedReader;
  import java.io.FileReader;
  import java.io.IOException;
  import java.util.Hashtable;
  
  import org.apache.hadoop.fs.Path;
  import org.apache.hadoop.io.LongWritable;
  import org.apache.hadoop.io.Text;
  import org.apache.hadoop.mapreduce.Mapper;
  import org.apache.hadoop.mapreduce.filecache.DistributedCache;
  
  public class MapperWithMapsideJoin extends Mapper<LongWritable, Text, Text, Text> {
  
  	private Hashtable<String, String> joinMap = new Hashtable<String, String>();
  
  	private Text outputKey = new Text();
  
  	@Override
  	protected void setup(Context context) throws IOException, InterruptedException {
  		// TODO Auto-generated method stub
  		try {
  
  			if (context.getCacheFiles() != null && context.getCacheFiles().length > 0) {
  				String line;
  				String[] tokens;
  				BufferedReader br = new BufferedReader(new FileReader(context.getCacheFiles()[0].toString()));
  
  				try {
  					while ((line = br.readLine()) != null) {
  						tokens = line.toString().replaceAll("\"", "").split(",");
  						joinMap.put(tokens[0], tokens[1]);
  
  					}
  				} finally {
  					br.close();
  				}
  			} else {
  				System.out.println("## cache files is null!");
  			}
  		} catch (Exception e) {
  			e.printStackTrace();
  		}
  	}
  
  	public void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
  
  		if (key.get() > 0) {
  			String[] colums = value.toString().split(",");
  			if (colums != null && colums.length > 0) {
  				try {
  					outputKey.set(joinMap.get(colums[8]));
  					context.write(outputKey, value);
  				} catch (Exception e) {
  					e.printStackTrace();
  				}
  			}
  		}
  	}
  }
  ```
  - MapsideJoin

```java
package lab.hadoop.join;

import javax.xml.soap.Text;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.conf.Configured;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.input.TextInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.apache.hadoop.mapreduce.lib.output.TextOutputFormat;
import org.apache.hadoop.util.GenericOptionsParser;
import org.apache.hadoop.util.Tool;
import org.apache.hadoop.util.ToolRunner;

public class MapsideJoin extends Configured implements Tool {

	@Override
	public int run(String[] args) throws Exception {
		// TODO Auto-generated method stub
		String[] otherArgs = new GenericOptionsParser(getConf(), args).getRemainingArgs();

		if (otherArgs.length != 3) {
			System.err.println("Usage: MapsideJoin <metadata> <in> <out>");
			System.exit(2);
		}

		Configuration conf = new Configuration();

		FileSystem hdfs = FileSystem.get(conf);

		Path path = new Path(args[2]);
		if (hdfs.exists(path)) {
			hdfs.delete(path, true);
		}

		Job job = Job.getInstance(conf, "MapsideJoin");

		job.addCacheFile(new Path(otherArgs[0]).toUri());

		FileInputFormat.addInputPath(job, new Path(otherArgs[1]));
		FileOutputFormat.setOutputPath(job, new Path(otherArgs[2]));

		job.setJarByClass(MapsideJoin.class);

		job.setMapperClass(MapperWithMapsideJoin.class);

		job.setNumReduceTasks(0);

		job.setInputFormatClass(TextInputFormat.class);
		job.setOutputFormatClass(TextOutputFormat.class);

		job.setOutputKeyClass(Text.class);
		job.setOutputValueClass(Text.class);

		job.waitForCompletion(true);
		return 0;

	}

	public static void main(String[] args) throws Exception {
		int res = ToolRunner.run(new Configuration(), new MapsideJoin(), args);
		System.out.println("## RESULT:" + res);
	}
}

```



- **Node가 하나만 뜰 때 쓰는 Linux명령어**

  ```Linux
  Live Node가 2개가 아닌경우 다시 설정해볼 내용
  [hadoop@master hadoop-2.7.7]$ ls
  #tmp 삭제
  [hadoop@master hadoop-2.7.7]$ rm -rfR tmp
  [hadoop@master hadoop-2.7.7]$ ls
  
  #Slave1 노드에서도 삭제
  [hadoop@slave1 hadoop-2.7.7]$ ls
  bin  include  libexec      logs        README.txt  share
  etc  lib      LICENSE.txt  NOTICE.txt  sbin        tmp
  [hadoop@slave1 hadoop-2.7.7]$ rm -rfR tmp
  [hadoop@slave1 hadoop-2.7.7]$ ls
  
  #master 노드에서 tmp 디렉토리 다시 생성
  [hadoop@master hadoop-2.7.7]$ mkdir -p /usr/local/hadoop-2.7.7/tmp/dfs/name
  [hadoop@master hadoop-2.7.7]$ mkdir -p /usr/local/hadoop-2.7.7/tmp/dfs/data
  [hadoop@master hadoop-2.7.7]$ ls -R /usr/local/hadoop-2.7.7/tmp
  
  [hadoop@master hadoop-2.7.7]$ mkdir -p /usr/local/hadoop-2.7.7/tmp/mapred/system
  [hadoop@master hadoop-2.7.7]$ mkdir -p /usr/local/hadoop-2.7.7/tmp/mapred/local
  [hadoop@master hadoop-2.7.7]$ ls -R /usr/local/hadoop-2.7.7/tmp
  
  [hadoop@master hadoop-2.7.7]$rsync -av . hadoop@slave1:/usr/local/hadoop-2.7.7
  
  [hadoop@master hadoop-2.7.7]$ cd etc/hadoop
  [hadoop@master hadoop-2.7.7]$ rsync -av . hadoop@slave1:/usr/local/hadoop-2.7.7
  
  [hadoop@master hadoop-2.7.7]$ rm -rf ./logs/yarn*
  [hadoop@master hadoop-2.7.7]$ rm -rf ./logs/hadoop*
  
  [hadoop@master ~]$ hadoop namenode -format
  ```

  

#### 4.2 리듀스 사이드 조인

- 두 개의 데이터를 키/값으로 출력합니다.

- 조인될 키를 구분하기 위해 키 뒤에 임의의 문자 추가해서 출력

-  ex)

- WN_A(항공운항통계 데이터의 항공사 코드)

- WN_B(항공사 코드 데이터의 항공사 코드)

-  리듀스에서 출력 시 추가된 문자열에 따라 다른 키의 값을 키로 저장

- _A가 붙어있으면 키를 WN_B의 값으로 저장

```java
package lab.hadoop.join;

import java.io.IOException;

import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

public class CarrierCodeMapper extends Mapper<LongWritable, Text, Text, Text> {

	public final static String DATA_TAG = "A";

	private Text outputKey = new Text();
	private Text outputValue = new Text();

	@Override
	protected void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
		// TODO Auto-generated method stub
		if (key.get() > 0) {
			// 콤마 구분자 분리
			String[] colums = value.toString().replaceAll("\"", "").split(",");
			if (colums != null && colums.length > 0) {
				outputKey.set(colums[0] + "_" + DATA_TAG);
				outputValue.set(colums[1]);
				context.write(outputKey, outputValue);

			}

		}
	}
}
```



```java
package lab.hadoop.join;

	import java.io.IOException;

	import org.apache.hadoop.io.LongWritable;
	import org.apache.hadoop.io.Text;
	import org.apache.hadoop.mapreduce.Mapper;

	public class MapperWithReducesideJoin  extends Mapper<LongWritable, Text, Text, Text> {

		public final static String DATA_TAG = "B";

		private Text outputKey = new Text();
		private Text outputValue = new Text();

		@Override
		protected void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
			// TODO Auto-generated method stub
			if (key.get() > 0) {
				// 콤마 구분자 분리
				String[] colums = value.toString().split(",");
				if (colums != null && colums.length > 0) {
					try {
					outputKey.set(colums[8] + "_" + DATA_TAG);
					context.write(outputKey, outputValue);
					}catch(Exception e) {
						e.printStackTrace();
					}
				}

			}
		}
	}

```

```java
package lab.hadoop.join;

import java.io.IOException;

import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

public class ReducerWithReducesIdeJoin extends Reducer<Text, Text, Text, Text> {
	
	private Text outputKey = new Text();
	private Text outputValue = new Text();
	@Override
	protected void reduce(Text key, Iterable<Text> values, Context context)
			throws IOException, InterruptedException {
		// TODO Auto-generated method stub
		String tagValue = key.toString().split("_")[1];
		
		for(Text value:values) {
			if(tagValue.equals(CarrierCodeMapper.DATA_TAG)) {
				outputKey.set(value);
			}else if (tagValue.equals(MapperWithReducesideJoin.DATA_TAG)) {
				outputValue.set(value);
				context.write(outputKey,outputValue);
			}
		}
	}
	
	
}
```

```java
package lab.hadoop.join;

import javax.xml.soap.Text;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.conf.Configured;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.MultipleInputs;
import org.apache.hadoop.mapreduce.lib.input.TextInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.apache.hadoop.mapreduce.lib.output.TextOutputFormat;
import org.apache.hadoop.util.GenericOptionsParser;
import org.apache.hadoop.util.Tool;
import org.apache.hadoop.util.ToolRunner;

public class ReducesideJoin extends Configured implements Tool {

	@Override
	public int run(String[] args) throws Exception {
		// TODO Auto-generated method stub
		String[] otherArgs = new GenericOptionsParser(getConf(), args).getRemainingArgs();

		if (otherArgs.length != 3) {
			System.err.println("Usage: ReducersideJoin <metadata> <in> <out>");
			System.exit(2);
		}

		Configuration conf = new Configuration();

		FileSystem hdfs = FileSystem.get(conf);

		Path path = new Path(args[2]);
		if (hdfs.exists(path)) {
			hdfs.delete(path, true);
		}

		Job job = new Job(getConf(), "ReducesideJoin");

		FileOutputFormat.setOutputPath(job, new Path(otherArgs[2]));

		job.setJarByClass(ReducesideJoin.class);

		job.setReducerClass(ReducerWithReducesIdeJoin.class);

		job.setInputFormatClass(TextInputFormat.class);
		job.setOutputFormatClass(TextOutputFormat.class);

		job.setOutputKeyClass(Text.class);
		job.setOutputValueClass(Text.class);

		MultipleInputs.addInputPath(job, new Path(otherArgs[0]), TextInputFormat.class, CarrierCodeMapper.class);
		MultipleInputs.addInputPath(job, new Path(otherArgs[1]), TextInputFormat.class, MapperWithReducesideJoin.class);

		job.waitForCompletion(true);
		return 0;

	}

	public static void main(String[] args) throws Exception {
		int res = ToolRunner.run(new Configuration(), new ReducesideJoin(), args);
		System.out.println("## RESULT:" + res);
	}
}
```





## 5. Hive

- 변환 된 맵리듀스잡을 하이브 클러스터에서 실행

  ![1566286227352](C:\Users\student\AppData\Roaming\Typora\typora-user-images\1566286227352.png)

- 특징
  - Hive 데이터는 HDFS 상의 파일로 존재하며, Hive 테이블은 HDFS 디렉토리로 존재한다
  - Hive 데이터베이스나 스키마도 HDFS 상의 디렉터리로 존재한다. (/user/hive/warehouse/테이블명  디렉터리로 존재)
  - Hive 에서 컬럼이나 속성 등 테이블 실체가 아닌, 속성 정보에 해당하는 테이블 정의는 metastore라 불리며, RDBMS에 저장된다.
  - Hive의 테이블 정의에서는 파티션이라 불리는 물리적 관리 단위를 지정할 수 있다 
  - 파티션은 HDFS 상의 디렉토리를 분할하는 것과 같다
  - 파티션을 설정함으로써 처리 범위를 제어할 수 있어, 처리 고속화가 가능하다.
  - 파티션 내의 모든 데이터가 필요 없어지면, 파티션 단위로 삭제할 수 있어서 관리도 수월하다
  - HiveQL의 흐름은 Hive에서 쿼리문 앞에 EXPLAIN을 붙여 실행하면 확인할 수 있다
  - HiveSQ은 Stage라는 단위로 MapReduce나 부속 처리로 변환되어, Stage 간 의존 관계가 생성된다