fruits  <- read.table("./datas/fruits.txt", header=T, skip=2, nrows=2)
print(fruits)
str(fruits)
class(fruits)

str(fruits)
save(fruits, file="./output/fruits.RData")
rm(list=ls())

install.packages("XML")
library(XML)

unlink("C:/Program Files/R/R-3.6.1/library/00LOCK", recursive = TRUE)

data2 <- xmlParse(file="./datas/emp.xml")
print(data2)
str(data2)
#ROOT NODE만 추출
rootnode <- xmlRoot(data2)
print(rootnode)
class(rootnode)
str(rootnode)

#rootnode의 자식 노드 갯수 
rootsize <- xmlSize(rootnode)
print(rootsize)

#rootnode의 첫번째 자식 노드 출력
print(rootnode[1])

#rootnode의 첫번째 자식 노드의 이름과 부서와 급여 출력
print(rootnode[[1]][[2]]);
print(rootnode[[1]][[3]]);
print(rootnode[[1]][[5]]);

#XML을 R의 지원형식인 data.frame으로 로딩
xmldataframe <- xmlToDataFrame("./datas/emp.xml")
print(xmldataframe)
str(xmldataframe)


install.packages('rjson')
library(rjson)
rm(list=ls())
data1 <- fromJSON(file="./datas/emp.json")
print(data1)
str(data1)


emp.dataframe<-as.data.frame(data1)
print(emp.dataframe)
str(emp.dataframe)

fruits1 <- read.table("./datas/fruits.txt", header=T, stringsAsFactors = 2)
print(fruits1)
str(fruits1)
class(fruits1)

result <- toJSON(fruits1)
print(result)
str(result)
write(result, "./output/fruits.json")
list.files("./output/")



install.packages("httr")
library(httr)
url <- "https://ssti.org/blog/useful-stats-capita-personal-income-state-2010-2015"
get_url <- GET(url)

html_cont <- readHTMLTable(rawToChar(get_url$content), stringAsFactor=2)
str(html_cont)
class(html_cont)
html_cont <- as.data.frame(html_cont)
head(html_cont)
str(html_cont)
class(html_cont)
names(html_cont) <- c("State", "y2010", "y2011", "y2012", "y2013", "y2014", "y2015")
tail(html_cont)

sink("./output/R_processing.txt")

sink()

library(xlsx)
studentx<-read.xlsx(file.choose(), sheetIndex=1, fileEncoding="EUC-KR", encoding="UTF-8")
print(studentx)
str(studentx)
class(studentx)
write.table(students, "./output/std.txt") # 행번호, 따옴표 출력

write.table(students, "./output/std2.txt", row.names=FALSE, quote=FALSE)

score <- scan()
if(score>=90){
  result="A학점"
}else if(score>=80){
  result="B학점"
}else if(score>=70){
  result="C학점"
}else if(score>=60){
  result="D학점"
}else {
  result="F학점"
}
cat("점수 ", score,"의 학점은 ", result)


number <- scan()
ifelse(number%%2==0, "짝수", "홀수")

ename <- scan(what="")

print(ename)
switch(ename, hong=250, lee=300, park=350, kim=200)

no<-c(1:5)
name<-c("홍길동", "이순신", "강감찬", "유관순", "김유신")
score<-c(85,90,78,74,80)
exam<-data.frame(학번=no, 이름=name, 성적=score)
print(exam)

which(이름="유관순")
print("학번", "이름", "성적")

i<-c(1:10)
#짝수만 출력
for( n in i) 
  if(n%%2==0) print(n)


for( n in i) {
  if(n%%2==1) {
    next
  }else{
    print(n) 
  }
}
i <- 0
while(i < 10){
  i<- i+1
  if(n%%2==0) print(n)
}

# 매개변수 없는 함수
f1 <- function(){
  cat("매개변수 없는 함수")
}
f1() # 함수 호출

# 매개변수가 있는 함수
f2 <- function(x) {
  if(x%%2==0) print(n)
}
f2(11) # 함수 호출

# 결과 반환 함수
f3 <- function(a, b) {
  add <- a+b
  return(add)
}
result <- f3(11, 4) # 함수 호출
print(result)

f1 <- function(x){
  result <- x*2
  if(x==0)
    return (0)
  else
    return(result)
}
f1(0)
f1(23)

nums <- 1:10
f5 <- function(v, type) {
  switch(type, mean=mean(v), sum=sum(v), median=median(v))
}
  print(f5(nums, "mean"))
  print(f5(nums, "sum"))
  print(f5(nums, "median"))
  
  callee <- function(x){
    print(x*2)
  }
  caller <- function(v, call){
    for(i in v){
      call(i)
    }
  }
  
  f10 <- function(a=3, b=6){  #기본값 파라미터 
    result <- a*b 
    print(result) 
  }
  f10(5)
  f10(9, 5)
  
  coin <- function(n) {
    r <- runif(n, min=0, max=1)
    result <- numeric()
    for( i in 1:n){
      if(r[i]<=0.5)
        result[i] <- 0 # 앞면
      else
        result[i] <- 1 # 뒷면
    }
    return(result)
  }
coin(10) # 동전 던지기 시행 횟수 10번

monteCoin <- function(n){
  cnt <- 0
  for( i in 1:n) {
    cnt <- cnt+coin(1)
  }
  result <- cnt / n # 동전 앞면과 뒷면의 누적 결과를 시행횟수(n)로 나타냄
  return (result)
}
monteCoin(10)
monteCoin(20)
monteCoin(30)
monteCoin(100)
monteCoin(1000)

vec <- c(1, 10, 3, 6, 2, 9, 5, 8, 7, 4)
print(range(vec))

print(sd( vec ))
print(sort(vec))
print(sort(vec, decreasing=T))
print(order(vec))
print(vec[order(vec)])
print(sample(vec, 3))
print(table(vec))

n <- 1000
result <- rnorm(n, mean=0, sd=1) 
head(result, 20)
hist(result)  #좌우 균등한 종 모양의 이상적인 분포가 그려져야 함


n <- 1000
result <- runif(n, min=0, max=10) 
head(result, 20)
hist(result)


n <- 1000
result <- rbinom(n, 5, prob=1/6)
hist(result)

rnorm(5, mean=0, sd=1)
rnorm(5, mean=0, sd=1)
set.seed(123)
rnorm(5, mean=0, sd=1)
set.seed(123)
rnorm(5, mean=0, sd=1)

chart_data <- c(305, 450, 320, 460, 330, 480, 380, 520)
names(chart_data) <- c(
                      "2014 1분기", "2015 1분기"
                      , "2014 2분기", "2015 2분기"
                      , "2014 3분기", "2015 3분기"
                      ,"2014 4분기", "2015 4분기"
                      )
str(chart_data)
print(chart_data)

barplot(chart_data, ylim=c(0, 600), col=rainbow(8), main="2014년도 VS 2015년도 분기별 매출현황 비교")

barplot(chart_data, xlim=c(0, 600), horiz=TRUE,  col=rainbow(8),
        main="2014년도 VS 2015년도 분기별 매출현황 비교",
        ylab="매출액(단위:만원)", xlab="년도별 분기현황")

barplot(chart_data, xlim=c(0, 600), horiz=TRUE, 
        main="2014년도 VS 2015년도 분기별 매출현황 비교",
        ylab="매출액(단위:만원)", xlab="년도별 분기현황"
        , space=2, cex.names=0.8, col=rep(c(2, 4), 4))

#색상 index값 : 검은색(1), 빨간색(2), 초록색(3), 파란색(4), 하늘색(5), 자주색(6), 노란색(7)

barplot(chart_data, xlim=c(0, 600), horiz=TRUE, 
        main="2014년도 VS 2015년도 분기별 매출현황 비교",
        ylab="매출액(단위:만원)", xlab="년도별 분기현황"
        , space=5, cex.names=0.5, col=rep(c(1, 7), 4))

data(VADeaths)
str(VADeaths)
class(VADeaths)
mode(VADeaths)
head(VADeaths, 10)
# VADeaths 데이터셋은 1940년 미국 버지니아주의 하위계층 사망비율을 기록한 데이터셋

par(mfrow=c(1,2))
barplot(VADeaths, beside=T, col=rainbow(5), 
        main="미국 버지니아주의 하위계층 사망비율")
#범례 출력
legend(19,71, c("50-54", "55-59", "60-64", "65-69", "70-74")
       , cex=0.8, fil=rainbow(5))

#누적막대 차트
barplot(VADeaths, beside=F, col=rainbow(5) )
title(main="미국 버지니아주의 하위계층 사망비율", font.main=4)
legend(3.8, 200, c("50-54", "55-59", "60-64", "65-69", "70-74")
       , cex=0.8, fil=rainbow(5))

par(mfrow=c(1,1))
dotchart(chart_data, color=c("green", "black"), lcolor="blue",
         pch=1:2, labels=names(chart_data), xlab="매출액",
         main="2014년도 VS 2015년도 분기별 매출현황 비교", cex=1.2)

pie(chart_data, color=c("green", "yellow"), lcolor="blue",
    pch=1:2, labels=names(chart_data), xlab="매출액",
    main="2014년도 VS 2015년도 분기별 매출현황 비교", cex=1.2)
boxline(VADeaths, range=0, notch=T )

data(iris)
names(iris)
str(iris) # data.frame,
head(iris)
# 붓꽃 3종류의 관측 데이터 - Sepal.elngth, Sepal.Width(꽃받침)
#Petal.length, Petal.Width(꽃잎)

summary(iris$Sepal.Length) # 꽃받침 길이의 요약 통계
hist(iris$Sepal.Length, xlab="iris$Sepal.Length",
     col="magenta", main="꽃받침 길이 histogram", xlim=c(4.3, 7.9))

#빈도수로 히스토그램 그리기
par(mfrow=c(1,2))
hist(iris$Sepal.Width, xlab="iris$Sepal.Width", 
     col="green", main="꽃받침 넓이 histogram" , xlim=c(2.0, 4.5))

#확률 밀도로 히스토그램 그리기
hist(iris$Sepal.Width, xlab="iris$Sepal.Width", 
     col="mistyrose", freq=F,
     main="꽃받침 넓이 histogram" , xlim=c(2.0, 4.5)) 
#밀도를 기준으로 분포 곡선 추가
lines(density(iris$Sepal.Width), color="red") 
#정규분포 추정 곡선 추가
x<-seq(20, 4.5, 0.1)
curve(dnorm(x, mean=mean(iris$Width), sd=sd(iris$Width)), 
      col="blue", add=T)
