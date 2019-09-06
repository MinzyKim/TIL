a<-10
b<-3
print(a+b)
install.packages("stringr")
dim(available.packages())
available.packages()
installed.packages()
search()
library(stringr)
search()
data()
par(mfrow=c(1,1))
pdf("c:/Rworkspace/sample.pdf")
hist(rnorm(20))
hist(Nile)
x<-3
tracemem(x)
x<-'a'
tracemem(x)
age <- 30
class(age)
age <- "29"
age <- TRUE #상수객체(TRUE, FALSE)
age <- F
age <- NA #결측치 (Not Available)
mode(age)
class(age+10)
age<-null
sum(10,20,30)
sum(10,20,30,NA)
sum(10,20,30,NA, na.rm=T)
x<-c("1","2","3") # Vector생성
result <- x*3
result <- as.numeric(x) * 3
result <- as.integer(x) * 3
z<-5.3-3i # 복소수 자료형 생성
class(z)
Re(z) # 실수만
Im(z) # 허수부만
is.complex(z)
as.complex(5.3)
pdf(file = NULL)
dev.off()
TRUE & TRUE
TRUE & FALSE
TRUE | TRUE
TRUE | FALSE
!TRUE
!FALSE
T <- FALSE
TRUE <- FALSE
c(TRUE, TRUE) & c(TRUE, FALSE)
c(TRUE, TRUE) && c(TRUE, FALSE)
Sys.Date() # 날짜만 보여주는 함수
Sys.time() # 날짜와 시간을 보여주는 함수
date() # 미국식 날짜와 시간을 출력하는 함수
as.Date('2017-12-01') # 문자형태의 날짜를 날짜타입으로 변환해주는 
as.Date('2017/07/04')
as.Date('04-07-2017') #오류
as.Date('2017-12-01' , format='%d-%m-%Y')
as.Date(10, origin='2017-12-01') #주어진 날짜 기준으로 10일후의 날짜
as.Date(-10, origin='2017-12-01') #주어진 날짜 기준으로 10일 이전 날짜


as.Date("2017-07-04 20:00:00 ")-as.Date("2017-07-04 18:30")
as.POSIXct("2017-07-04 20:00:00 ")-as.POSIXct("2017-07-04 18:30")
as.Date("2019-Sep-05", format="%y-%b-%d")

#Sys.setlocale(category="LC_ALL", locale="언어_국가")
#현재 로케일 정보 전체 확인
Sys.setlocale(category="LC_ALL", locale="")
Sys.getlocale()

Sys.setlocale(category="LC_ALL", locale="Korean_Korea")
Sys.getlocale()

Sys.setlocale(category="LC_ALL", locale="English_US") 
Sys.getlocale()

Sys.setlocale(category="LC_ALL", locale="Japanese_Japan")
Sys.getlocale()

gender <- c("man", "woman", "woman", "man", "man")
plot(gender) #차트는 수치 데이터만 가능하므로 오류
class(gender)
mode(gender)
ngender <- as.factor(gender)
class(ngender)
mode(ngender)
plot(ngender)
is.factor(ngender)

ngender    #Levels속성에서 범주를 확인 (알파벳 순서?)

args(factor)  #factor()함수의 매개변수 확인
ogender <- factor(gender, levels=c("woman", "man"), ordered=T)
ogender    #범주의 순서 확인

par(mfrow=c(1, 2))
plot(ngender)
plot(ogender)

c(1:20)
1:20
c(1,1,2,3,3,3,4,5,5,5,5,5)
seq(1, 20)
seq(1, 20, 2) # 2씩 증가

rep(1:3, 3) # 1~3까지 3번 반복
rep(1:3, each=3) # 1~3까지 반복하는데 각각 3번


a <- c(1:5)
b <- a+1
c <- a*2
d <- rep(1:3, 3)
union(a, d)
setdiff(a, d)
intersect(a, d)
f <- c(33, -5, "4", 5:9)

class(f)
mode(f)

a <- c(1:20)
a[3:10]
a[c(3,10)]

matrix1 <- matrix(c(3, 9, -1, 4, 2, 6), nrow = 2)
matrix2 <- matrix(c(5, 2, 0, 9, 3, 4), nrow = 2)
result <- matrix1 + matrix2
cat("Result of addition","\n")
print(result)

result <- matrix1 + 10
print(result)
print(length(result))  #전체 원소 개수 반환
print(nrow(result))  #행 수 반환
print(ncol(result))  #열 수 반환

#base패키지의 apply함수 apply(행렬객체, margin(1:행, 2:열), function)
f <- function(x) {  #사용자 정의 함수  
  x*c(1,2,3)
}
result <- apply(matrix1, 1, f)
print(result)

result <- apply(matrix(1:9, ncol=3), 2, f)
print(result)

