price <- runif(10, min=1, max=100)
print(price)
plot(price, col="red")
par(new=T) #차트 추가
line_chart=1:100
#x축은 생성된 난수의 순서,  y축은 
plot(line_chart, type="l", col="red", axes=F, ann=F) #대각선 추가 

#좌표평면상의 점 등을 선으로 연결
par(mfrow=c(2, 2))
plot(price, type="l")  #실선
plot(price, type="o")  #원형과 실선
plot(price, type="h")  #직선
plot(price, type="s")  #꺽은선

#중복된 데이터의 수만큼 plot점 크기 확대
x<-c(1, 2, 3, 4, 2, 4)
y<-rep(2, 6)
table(x, y)  #빈도수


par(mfrow=c(1, 1))
plot(x, y)

xy.df <- as.data.frame(table(x, y))
xy.df

plot(x, y, pch='@', col='blue', cex=0.5*xy.df$Freq, 
     xlab="x벡터 원소", ylab="y벡터 원소")

install.packages("psych")
library(psych)
data(galton)

#child컬럼, parent컬럼을 대상으로 교차테이블을 생성 결과를 데이터프레임으로 생성

galtondf <- as.data.frame(table(galton$child, galton$parent))
head(galtondf)
str(galtondf)

names(galtondf) <- c("child", "parent", "freq")
parent <- as.numeric(galtondf$parent)
child<-as.numeric(galtondf$child)

plot(parent, child, pch=21, col="blue", bg="green",
     cex=0.2*galtondf$freq,xlab="parent", ylab="child")

attributes(iris)
pairs(iris[, 1:4])

pairs(iris[iris$Species=="setosa", 1:4])

install.packages("scatterplot3d")
library(scatterplot3d)
levels(iris$Species)
iris_setosa = iris[iris$Species=='setosa', ]
iris_versicolor = iris[iris$Species=='versicolor', ]
iris_virginica = iris[iris$Species=='virginica', ]
d3 <- scatterplot3d(iris$Petal.Length, iris$Sepal.Length,
                    iris$Sepal.Width, type='n')  #type='n'은 기본 산점도를 표시하지 않음

d3$points3d(iris_setosa$Petal.Length, iris_setosa$Sepal.Length ,
            iris_setosa$Sepal.Width, bg="orange", pch=21)

d3$points3d(iris_versicolor$Petal.Length, iris_versicolor$Sepal.Length ,
            iris_versicolor$Sepal.Width, bg="blue", pch=23)

d3$points3d(iris_virginica$Petal.Length, iris_virginica$Sepal.Length ,
            iris_virginica$Sepal.Width, bg="green", pch=25)

install.packages("plyr")
library(plyr)
x<-data.frame(id=c(1,2,3,4,5), 
              height=c(160, 171, 173, 162, 165))
y<-data.frame(id=c(5, 1, 3, 2, 4), 
              weight=c(55, 73, 60, 57, 75))

# join() : 두 데이터프레임을 merge
xyjoin <- join(x, y, by="id")
xyjoin

x<-data.frame(id=c(1,2,3,4,6), 
              height=c(160, 171, 173, 162, 165))
leftjoin <- join(x, y, by="id")   #왼쪽 데이터 프레임의 키값을 기준으로 merge
leftjoin    #키에 join할 데이터가 없으면 NA로 출력


innerjoin <- join(x, y, by="id", type="inner")
innerjoin   #innerjoin은 두 데이터프레임에서 키값이 있는 경우에만 조인을 수행


fulljoin <- join(x, y, by="id", type="full")
fulljoin   #키 값이 존재하는 전체 관측치를 대상으로 조인 수행, 키에 join할 데이터가 없으면 NA로 출력



x<-data.frame(key1=c(1,1, 2,2, 3), 
              key2=c('a', 'b', 'c','d', 'e'),
              val1 = c(10,20,30,40,50))

y<-data.frame(key1=c(3, 2, 2, 1, 1), 
              key2=c('e', 'd', 'c','b', 'a'),
              val1 = c(500,300,400,100,200))

xyjoin <- join (x, y, by=c(key1, key2))
xyjoin

install.packages("dplyr")
library("dplyr")

exam<-read.csv("./datas/exam.csv")
print(exam)
str(exam)

class1<- exam %>% filter(class==1)
print(class1)

other_class <- exam %>% filter(class!=1)
print(other_class)

class1_math50 <- exam %>% filter(class==1 & math > 50)
print(class_math50)

avg_exam <- exam %>% mutate(total=math+english+science,
                            mean=(math+english+science)/3)
print(avg_exam)

pass <- exam %>% mutate(mean=(math+english+science)/3)

install.packages("hflights")
library(hflights)
str(hflights)
flights_df <- tbl_df(hflights)
print(flights_df)

install.packages("ggplot2")
library(ggplot2)
#자동차 배기량에 따라 고속도록 연비 ...데이터 셋
mpg <- as.data.frame(ggplot2::mpg)
print(mpg)
str(mpg)
#displ 배기량
#manufaturer 제조사
#cty 도시연비
#hwy 고속도로 연비
#class차종
library(dplyr)
#Quiz> 회사별로 분리, suv 추출, 통합 연비(도시연비+고속도로 연비) 변수 생성, 
#통합 연비 평균 산출, 내림차순 정렬, 1~5위까지 출력


#Quiz> 어떤 회사에서 "compact"(경차) 차종을 가장 많이 생산하는지 알아보려고 합니다. 
#각 회사별로 "compact" 차종을 내림차순으로 정렬해 출력하세요

install.packages("RJDBC")
library(RJDBC)
library(rJava)

drv <- JDBC("oracle.jdbc.OracleDriver",
            classPath="C:/app/student/product/11.2.0/dbhome_1/jdbc/lib/ojdbc6.jar",
            identifier.quote="'")

con <- dbConnect(drv, "jdbc:oracle:thin:@localhost:1521:orcl", "hr", "oracle")
rs <- dbGetQuery(con, "select tname from tab")
View(rs)

install.packages("igraph")
library(igraph)
g1 <- graph(c(1, 2, 2, 3, 2, 4, 1, 4, 5, 5, 3, 6))
print(g1)
plot(g1)
str(g1)

name<-c("세종대왕", "일지매 부장", "김유신 과장", "손흥민 대리", "류현진 대리",
        "이순신 부장", "유관순 차장", "신사임당 대리", "강감찬 부장"
        , "광개토 과장", "정몽주 대리")
pemp <- c("세종대왕", "세종대왕", "일지매 부장" , "김유신 과장", "김유신 과장",
          "세종대왕",  "이순신 부장", "유관순 차장",  "세종대왕" , "강감찬 부장"
          , "광개토 과장")

emp <- data.frame(이름=name, 상사이름=pemp)
print(emp)
g <- graph.data.frame(emp, direct=T)
plot(g, layout=layout.fruchterman.reingold,
     vertex.size=8, edge.arrow.size=0.5)

install.packages("reshape")
library(reshape)
#데이터 파일을 가져오는 경우 컬럼명이 없으면 기본적으로 V1, V2, V3...

result<-read.csv("./datas/reshape.csv", header=FALSE)
head(result)
result<-rename(result,c(V1="total", V2="num1", V3="num2", V4="num3"))

data('Indometh')  #항염증제에 대한 약물동태학에 관한 데이터 셋
str(Indometh)  #생체내에서 약물의 흡수, 분포, 비축, 대사, 배설의 과정을 연구
#Subject(실험대상), time(약물 투여시간:hr), conc(농도:ml/mcg)
Indometh   #long형식

# 기준변수 : timevar="time", idvar="Subject"
# 관측변수 : v.names="conc"
# 실험대상1을 기준으로 약물투여시간 0.25에서 8까지의 ...농도를 
wide <- reshape(Indometh, v.names="conc", timevar="time", idvar="Subject", direction="wide")
wide

reshape(wide, direction="long")

#varying="반복되는 측정 색인" 사용
long <- reshape(wide, idVar="Subject", varying=2:12,
                v.names="conc",  direction="long" )
str(long)

dau <- read.csv("./datas/dau.csv", header = T, stringsAsFactors = F)
head(dau)
dpu <- read.csv("./datas/dpu.csv", header = T, stringsAsFactors = F)
head(dpu)
install <- read.csv("./datas/install.csv", header = T, stringsAsFactors= F)
head(install)

library(plyr)
join <- join(dau, install, by="user_id", type="full")
print(join)

fulljoin <- join(join, dpu, by="user_id", type="full")
str(fulljoin)
print(fulljoin)

noPayment <- fulljoin %>% select(user_id, payment) %>% filter(payment %in% NA)
print(noPayment)
install.packages("lubridate")
library(lubridate)
fulljoin[is.na(fulljoin[, "payment"]), "payment"] <- 0

data.frame(fulljoin)$month <- mutate(cbind(x, month(fulljoin$log_date)))

month <- cbind(fulljoin, month=month(fulljoin$log_date))
