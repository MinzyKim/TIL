.libPaths()  # 패키지가 다운로드되는 경로 확인

#벡터 요소에 access하는 방법
t <- c("Sun","Mon","Tue","Wed","Thurs","Fri","Sat")
print(t[1])   #"Sun"
print(t[7])   #"Sat"
u <- t[c(2,3,6)]   #"Mon","Tue", "Fri"
print(u)

v <- t[c(TRUE,FALSE,FALSE,FALSE,FALSE,TRUE,FALSE)]
print(v)    # "Sun", "Fri"

x <- t[c(-2,-5)] #인덱스 요소를 제외하고 출력
print(x)    #"Sun", "Tue","Wed", "Fri","Sat"

y <- t[c(0,0,0,0,0,0,1)]  #index에 해당하는 요소 출력
print(y)    


#벡터 연산 : 요소간의 연산을 수행
v1 <- c(3,8,4,5,0,11)
v2 <- c(4,11,2)
add.result <- v1+v2      # v1-v2 , v1*v2, v1/v2
print(add.result)


v1 <- c(3,8,4,5,0,11)
v2 <- c(4,11)        #(4, 11, 4, 11, 4, 11)
add.result <- v1+v2  #연산 대상 벡터요소개수가 가장 긴쪽에 맞춰서 요소가 recycling
print(add.result)  

nums <- c(3/2, 3%/%2, 5%%3,2^10,2**10)
print(nums)
print(( 0 %in% nums))
print(( 1024 %in% nums))

#문> nums 벡터 요소중 10보다 큰 요소만 출력 (>, >=,==, !=, <, <= )
print(nums[nums>10])

#문> nums 벡터 요소중 짝수인 요소만 출력
print(nums[nums%%2==0]) 


loc <- c("02", "031","062", "052")
str(loc)
names(loc)<-c("서울", "경기", "광주", "부산")
print(loc["경기"])  #이름으로 벡터 요소 접근 가능
str(loc)  

v <- c(3,8,4,5,0,11, -9, 304)
sort.result <- sort(v)
print(sort.result)

revsort.result <- sort(v, decreasing = TRUE)
print(revsort.result)

v <- c("Red","Blue","yellow","violet")
sort.result <- sort(v)
print(sort.result)

#집합연산함수(교집합, 합집합, 차집합, 부분집합, ..)
#identical( 객체1, 객체2) 두객체의 데이터 갯수, 순서도 일치
#union( 객체1, 객체2)
#intersect(객체1, 객체2)
#setdiff(객체1, 객체2)
#setequal(객체1, 객체2)

vec1 <- c(1, 2, 3, 4, 5)
vec2 <- c(10, 9, 8, 4, 5)
vec3 <- c(1, 2, 3, 4, 5)
print(identical(vec1,vec3))
print(identical(vec1,vec2))
vec4 <- c(5,3,4,1,2)
print(setequal(vec1,vec4))  #순서는 일치하지 않아도 요소들만 일치하면 true리턴
print(setequal(vec1,vec3))


print(union(vec1,vec2))
print(intersect(vec1,vec2))
print(setdiff(vec1,vec2))
##########################################################
#Matrix (기본적으로 열기준으로 2차원 데이터 저장)
#matrix(data, nrow=1, ncol=1, byrow=FALSE, dimnames=NULL)
##########################################################
# Matrix 생성 실습
M <- matrix(c(3:14)) # 열 기준 2차원 데이터 구조
print(M)
str(M)

M1 <- matrix(c(3:14), nrow=3) # 열 기준 2차원 데이터 구조
print(M1)
str(M1)

M2 <- matrix(c(3:14), nrow = 4, byrow = TRUE) #행기준 2차원 데이터 구조
print(M2)
str(M2)

x1 <- c(5, 40, 50:52)
x2 <-c(30, 5, 6:8)
M3 <- rbind(x1, x2)
print(M3)
class(M3)
str(M3)

M4 <- cbind(x1, x2)
print(M4)
str(M4)
class(M3)

M <- matrix(10:19, 2)  #2행?
print(M)
str(M)

#행과 열의 수가 일치하지 않으면 오류가 발생하며, 모자라는 데이터는 첫 번째 데이터부터 재사용하여 채운다
#행렬 객체 생성시 주어진 데이터의 길이는 행과 열의 행렬 수에 정확히 일치되어야 한다. (경고 발생)
M <- matrix(10:20, 2)   

rownames <- c("row1", "row2", "row3", "row4")
colnames <- c("col1", "col2", "col3")

M5 <- matrix(c(3:14), nrow = 4, byrow = TRUE, dimnames = list(rownames, colnames))
print(M5)
str(M5)


P1 <-cbind(M5, c(13,14,15,16)) #cbind()는  컬럼을 추가
print(P1)  #4행 4열

P2 <-rbind(M5, c(13,14,15))  #rbind() 는 행을 추가
print(P2) #5행 3열

print(M5+P1)  # 열 개수가 다름 error 발생
print(M5+P2)  # 행 개수가 다름 error 발생


# Matrix 요소에 접근 - 변수[첨자, 첨자]
# 특정 행이나 특정 열만 접근하는 경우 변수명[행첨자, ], 변수명[, 열첨자] 형식으로 지정
print(M5[1,3])
print(M5[2,])  #2행 전체 요소에 접근
print(M5[,3])   #3열 전체 요소에 접근
print(M5["row1",])  #1행 전체 요소에 접근
print(M5[,"col3"])   #3열 전체 요소에 접근



# Matrix 연산
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

vector1 <- c(5,9,3)
vector2 <- c(10,11,12,13,14,15)

result <- array(c(vector1,vector2),dim = c(3,3,2))  #row, col, layer
print(result)
str(result)

column.names <- c("COL1","COL2","COL3")
row.names <- c("ROW1","ROW2","ROW3")
matrix.names <- c("Matrix1","Matrix2")

result <- array(c(vector1,vector2),dim = c(3,3,2), dimnames = list(row.names,column.names,
                                                                   matrix.names))
print(result)

vector3<-c(9,1,0)
vector4 <- c(6,0,11,3,14,1,2,6,9)
array2<-array(c(vector3, vector4), dim = c(3,3,2))
print(array2)

matrix1 <- result[, , 2]
matrix2 <- array2[, , 2]
print(matrix1 + matrix2)


list <- list("lee", "이순신", 95)
print(list)
str(list)

emp1 <- list(name='kim', address='seoul', age=30, hiredate=as.Date('2017/01/02'))
print(emp1)
str

list_data <- list(k1="Red", k2="Green", k3=c(21,32,11),
                  k4=TRUE, k5=51.23, k6=119.1)
print(list_data[3])

tracemem(emp1)
emp1$deptno <- 10 #리스트 객체에 새로운 data 추가
str(emp1)
tracemem(emp1)

emp1$age <- NULL      #리스트의 요소를 제거
str(emp1)

exam1<- list(1,0, 2,0, -3, 4, -5, 6, 7, -8, 9, 10)
exam1[exam1<0] <- NULL
print(exam1)


a <- list(c(1:5))
b <- list(6:10)
result <- lapply(c(a,b), max)
print(result)
str(result)

result <- sapply(c(a, b), max)
print(result)
str(result)


d1 <- data.frame(no=c(1,2,3,4,5),
                 name=c('kim', 'park', 'lee', 'song', 'hong'),
                 gender=c('F','W','M','W','W'))
str(d1)
print(d1)

print(d1$name)
df1 <- data.frame(sales1, stringsAsFactors=FALSE)
str(df1)
names(df1) <- c('No', 'Fruit', 'Price', 'Qty')
str(df1) 

#as.numeric()함수는 numeric변환
df1$Qty <- as.numeric(df1$Qty)
df1$Price <- as.numeric(df1$Price)
str(df1) 


subset.df1 <- subset(df1, Qty>5) 
print(subset.df1)

subset.df1 <- subset(df1, Price<=150)
print(subset.df1)

subset.df1 <- subset(df1, Fruit=='Banana')
print(subset.df1)

df2<-data.frame(x=c(1:5), y=seq(2, 10, 2), z=c('a', 'b', 'c', 'd', 'e'))
print(df2)
subset.df2 <- subset(df2, x >=2 & y<=6 )
subset.df2 <- subset(df2, x >=2 | y<=6 )
print(subset.df2)
print(subset.df2)

df6 <- subset(df1, select=-No)
str(df6)
print(df6)

emp.data <- data.frame(
  emp_id = c (1:5), 
  emp_name = c("Rick","Dan","Michelle","Ryan","Gary"),
  salary = c(623.3,515.2,611.0,729.0,843.25), 
  
  start_date = as.Date(c("2012-01-01", "2013-09-23", "2014-11-15", "2014-05-11",
                         "2015-03-27")),
  stringsAsFactors = FALSE
)
print(emp.data) 
str(emp.data)

result <- emp.data[c(3,5), c(2,4)]
print(result)

df4 <- data.frame(name=c('apple', 'banana', 'cherry'),
                  price=c(300,200,100))
df5 <- data.frame(name=c('apple', 'cherry', 'berry'),
                  qty=c(10,20,30))
result1 <- merge(df4, df5)

#첫번째 열 데이터 기준으로 일치하는 데이터의 열 결합
print(result1)
str(result1)

result2 <- merge(df4, df5, all=T)
print(result2)
str(result2)

str(mtcars)
head(mtcars) #1~6행만 출력해줌
head(mtcars, 20)
tail(mtcars) #last-5 ~ last행까지 출력해줌
data(mtcars)
view(mtcars)
summary(mtcars) #컬럼단위로 최소값, 1/4분위값, 중앙값, 평균, 3/4분위
summary(emp.data)


install.packages("stringr")
library(stringr)

fruits <- c('apple', 'banana', 'pineapple', 'berry', 'APPLE')
#패턴을 포함한 요소에서 패턴 출현 회수 리턴
print(str_count(fruits, "a"))

#문자열 결합 기본 R 함수
rs1 <- paste('hello', '~R')
print(rs1)

print(str_c('hello', '~R'))
print(str_c(fruits, " name is ", fruits))
print(str_c(fruits, collapse=" "))
print(str_c(fruits, collapse="-"))

print(str_detect(fruits, 'A')) #dataset객체의 요소별로 'A'포함 여부를 검사
print(str_detect(fruits, '^a')) #정규표현식의 형식문자^는 시작을 의미
print(str_detect(fruits, 'a$'))


print(str_detect(fruits, '^[aA]'))
print(str_detect(fruits, '[^a]')) # not의 의미

print(str_sub(fruits, start=1, end=3)) #부분 추출
print(str_sub(fruits, start=6, end=9))
print(str_sub(fruits, start=-5))

str_length("	apple  banana   ")
str_length(str_trim("  apple   banana    ")) # 앞뒤 공백 제거 trim()

# dataset 객체의 요소 문자열의 길이를 벡터로 리턴
print(str_length(fruits))
print(str_dup(fruits, 3))

print(str_replace(fruits, 'p', '**'))
print(str_replace_all(fruits, 'p', '**'))

fruits2 <- str_c(fruits, collapse="/")
print(fruits)
str(fruits)


str_extract("홍길동35이순신45유관순24", "[1-9]{2}")
str_extract_all("홍길동35이순신45유관순24", "[1-9]{2}")
str_extract_all("honggil305koreaseoul1004you25jeju-hanlasan2005", "[1-9]{2}")
str_extract_all("honggil305koreaseoul1004you25jeju-hanlasan2005", "[1-9]{2}")

str1 <- "korea123456-1234567seoul"
str_extract_all(str1, "[0-9]{6}-[0-9]{7}")

str_extract_all(str2, "\\w{7, }")
str2 <- "홍길동1357, 이순신, 유관순1012"

num <- scan()
num

name <- scan(what=character())
name

df = data.frame() # 빈 데이터프레임 생성
df = edit(df) # 데이터 편집기
print(df)

print(list.files(recursive=T))
print(list.files(all.files=T))

data1 <- read.csv("./data/emp.csv")
# data1 <- read.csv("c:/Rworkspace/data/emp.csv")
print(data1)
str(data1) #data.frame객체로 리턴

max_sal <- max(data1$pay) # pay만 추출
print(max_sal)

person1<-subset(data1, pay==max(pay)) # max pay를 가진 행 추출
print(person1)

data2 <- read.csv("./data/emp3.csv")
print(data2)
str(data2)

it_person <- subset(data2, salary>=600)
print(it_person)

startdate1<- subset(data2, as.list(startdate) > "2014-07-01")
print(startdate1)

startdate1 <- startdate[startdate$startdate >= "2014-07-01"]
           
itperson <- subset(data2, dept=="IT")
print(itperson)
write.csv(itperson, "./output/itperson.csv", row.names=FALSE)
list.files("./output/")
newdata <- read.csv("./output/itperson.csv")
print(newdata)             

install.packages("rJava")
install.packages("xlsx")
Sys.setenv(JAVA_HOME='C:\\Program Files\\Java\\jre1.8.0_151')

library(rJava)
library(xlsx)

installed.packages()

studentex <- read.xlsx(file.choose(),
                      sheetIndex=1, encoding="UTF-8")

itperson<-subset(data1, dept=="IT")

file1 <- readLines("./data/fruits.txt")  
print(file1)
str(file1)