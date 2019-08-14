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

  

```
[hadoop@master hadoop]$ vi core-site.xml
<?xml version="1.0"?>
<?xml-stylesheet type="text/xsl" href="configuration.xsl"?>
<!-- Put site-specific property overrides in this file. -->
<configuration>
<property>
<name>fs.default.name</name>
<value>hdfs://master:9000</value>
</property>
<property>
<name>hadoop.tmp.dir</name>
<value>/usr/local/hadoop-2.7.7/tmp</value>
</property>
</configuration>




[hadoop@master hadoop]$ vi hdfs-site.xml
<?xml version="1.0"?>
<?xml-stylesheet type="text/xsl" href="configuration.xsl"?>
<!-- Put site-specific property overrides in this file. -->
<configuration>
<property>
<name>dfs.replication</name>
<value>1</value>
</property>
<property>
<name>dfs.permissions.enabled</name>
<value>false</value>
</property>
<property>
<name>dfs.secondary.http.address</name>
<value>secondary:50090</value>
</property>
</configuration>



[hadoop@master hadoop]$ cp mapred-site.xml.template  mapred-site.xml
[hadoop@master hadoop]$ vi mapred-site.xml
<configuration>
<property>
<name>mapreduce.framework.name</name>
<value>yarn</value>
</property>
</configuration>


[hadoop@master hadoop]$ vi yarn-site.xml
<configuration>
<property>
<name>yarn.nodemanager.aux-services</name>
<value>mapreduce_shuffle</value>
</property>
<property>
<name>yarn.nodemanager.aux-services.mapreduce_shuffle.class</name>
<value>org.apache.hadoop.mapred.ShuffleHandler</value>
</property>
</configuration>






# sample configuration for iptables service
# you can edit this manually or use system-config-firewall
# please do not ask us to add additional ports/services to this default configuration
*filter
:INPUT ACCEPT [0:0]
:FORWARD ACCEPT [0:0]
:OUTPUT ACCEPT [0:0]
-A INPUT -m state --state RELATED,ESTABLISHED -j ACCEPT
-A INPUT -p icmp -j ACCEPT
-A INPUT -i lo -j ACCEPT
-A INPUT -p tcp -m state --state NEW -m tcp --dport 22 -j ACCEPT
-A INPUT -m state --state NEW -m tcp -p tcp --dport 8080 -j ACCEPT
-A INPUT -m state --state NEW -m tcp -p tcp --dport 8088 -j ACCEPT
-A INPUT -m state --state NEW -m tcp -p tcp --dport 50070 -j ACCEPT
-A INPUT -m state --state NEW -m tcp -p tcp --dport 5432 -j ACCEPT
-A INPUT -m state --state NEW -m tcp -p tcp --dport 3306 -j ACCEPT
-A INPUT -m state --state NEW -m tcp -p tcp --dport 8032 -j ACCEPT
-A INPUT -m state --state NEW -m tcp -p tcp --dport 12000 -j ACCEPT
-A INPUT -s 192.168.50.0/24 -d 192.168.50.0/24 -j ACCEPT
-A OUTPUT -s 192.168.50.0/24 -d 192.168.50.0/24 -j ACCEPT

-A INPUT -j REJECT --reject-with icmp-host-prohibited
-A FORWARD -j REJECT --reject-with icmp-host-prohibited
```