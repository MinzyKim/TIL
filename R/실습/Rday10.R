install.packages("party")
library(party)
library(datasets)
str(airquality) # 관측 154개, 변수 6개

formula <- Temp ~ Solar.R+Wind+Ozone
air_ctree <- ctree(formula, data=airquality)
air_ctree
plot(air_ctree)

set.seed(1234) # 시드값을 적용하면 랜덤 값이 동일하게 생성
idx <- sample(1:nrow(iris), nrow(iris)*0.7)
train <- iris[idx, ] # 학습 데이터
test <- iris[-idx, ] # -는 제외, 검정 데이터

# 종속변수는 Species, 독립변수는 나머지
formula <- Species ~ Sepal.Length + Sepal.Width + Petal.Length + Petal.Width #(~는 영향을 주는 요소)

#분류모델 생성
iris_ctree <- ctree(formula, data=train)
iris_ctree
plot(iris_ctree, type="simple")

pred <- predict(iris_ctree, test)
table(pred, test$Species) # 예측값이 행, Species가 열

( 16 + 15 + 12) / nrow(test)

nrow(test)

# 3겹, 2회 반복을 위한 샘플링
install.packages("cvTools")
library(cvTools)
cross <- cvFolds(nrow(iris), K=3, R=2)
str(cross)

cross #교차검정 데이터 확인
length(cross$which)
dim(cross$subsets)
table(cross$which)

R=1:2	#2회 반복
K=1:3	#k겹(3겹)
CNT=0	#카운트 변수
ACC <- numeric() #정확도 저장

for(r in R){
  cat('\n R=',r,'\n')
  for(k in K){
    datas_idx <- cross$subsets[cross$which==k, r]
    test <- iris[datas_idx, ] # test 데이터 생성
    cat('test:', nrow(test), '\n')
    
    formula <- Species ~ . # 나머지 모든 변수
    train <- iris[-datas_idx, ] # test행 뺀 훈련데이터
    cat('train:', nrow(train), '\n')
    
    model <- ctree(formula, data=train)
    pred <- predict(model, test)
    t <- table(pred, test$Species)
    print(t)
    CNT <- CNT+1
    ACC[CNT] <- (t[1,1]+t[2,2]+t[3,3])/sum(t)
  }
}
CNT
ACC
mean(ACC, na.rm=T)

# 분류분석 연습문제
# ggplot2 :: mpg 데이터 셋
# model(모델), displ(엔진 크기), cyl(실린더 수), drv(구동 방식)
# 종속 변수 : 고속도로 주행거리(hwy)
# 고속도로 주행 거리에 가장 영향을 많이 미치는 요소 찾기

library(ggplot2)
data(mpg)
t <-sample(1:nrow(mpg), 120)
train <- mpg[t, ]
test <- mpg[-t, ]
test$drv <- factor(test$drv)  #구동방식 범주형 변환
formula <- hwy ~ displ+cyl+drv
hwy_ctree <- ctree(formula, data=test)
plot(hwy_ctree)

install.packages("arules")
library(arules)
data("AdultUCI")
#성인 대상 인구 소득에 관한 설문 조사 데이터
#48,842 관측치와 15개변수
age, workclass(직업 :4개), education(교육수준: 16개), marital-status(결혼상태: 6개), occupation(직업:12개), relationship(관계: 6개), race(인종:아시아계, 백인), sex(성별), capital-gain(자본이득), capital-loss(자본손실), fnlwgt(미지의 변수), hours-per-week(주당 근무시간), native-country(국가), income(소득)

#10,000개 관측치를 샘플링해서
자본이득에 영향을 미치는 변수를 분석하기 위해 
capital-gain, hours-per-week, education-num, race, age, income 변수로만 구성된 데이터프레임을 생성한후 분류모델 생성하고 예측하시오

names(AdultUCI)
set.seed(1234)
choice <- sample(1:nrow(AdultUCI), 10000)
choice

adult.df <- AdultUCI[choice, ]
str(adult.df)

capital <- adult.df$'capital-gain'
hours <- adult.df$'hours-per-week'
education <- adult.df$'education-num'
race <- adult.df$race
age <- adult.df$age
income <- adult.df$income

adult_df <- data.frame(capital=capital, age=age , hours=hours,
                       education=education, income=income)
str(adult_df)

formula <- capital ~ income+education+hours+age

adult_ctree <- ctree(formula, data=adult_df)

plot(adult_ctree)

#분석결과 : 자본이득(capital)에 가장 큰 영향을 미치는 변수는 income이고, 두번째는 education 변수이다.
수입이 많고 교육수준이 높을수록 자본이득이 많은 것으로 분석된다.

#분류 모델의 조건에 맞는 subset 생성
adultResult <- subset(adult_df, adult_df$income=='large' &  adult_df$education > 14)
length(adultResult$education)
summary(adultResult$capital) 
boxplot(adultResult$capital)

install.packages("rpart")
library(rpart)
data(iris)

iris.df <- rpart(Species ~., data=iris)
iris.df

plot(iris.df)
text(iris.df, use.n=T, cex=0.6)
post(iris.df, file="")

weather <- read.csv("./data/weather.csv", header=TRUE)

#RainTomorrow 컬럼을 종속변수로 
# 날씨 요인과 관련없는 Date와 RainToday컬럼을 제외한 나머지 변수를 x변수로 지정하여 분류 모델 생성하고 모델을 평가하시오

str(weather)
names(weather)
weather.df <- rpart(RainTomorrow ~ ., data=weather[, c(-1, -14)], cp=0.01) 
X11()
plot(weather.df)
text(weather.df, use.n=T, cex=0.7)

#분석 결과 : 분기조건이 True이면 왼쪽으로 분류되고, False
이면 오른쪽으로 분류된다.
#rpart()함수의 cp속성값을 높이면 가지 수가 적어지고, 낮추면 가지 수가 많아진다. cp 기본값은 0.01

weather_pred <- predict(weather.df , weather)
weather_pred

#y의 범주로 코딩 변환 : Yes(0.5이상), No(0.5미만)
#rpart의 분류모델 예측치는 비 유무를 0~1사이의 확률값으로 예측하다 
# 혼돈매트릭스를 이용하여 분류정확도를 구하기 위해 범주화 코딩 변경
weather_pred2 <- ifelse(weather_pred[,2] >= 0.5, 'Yes', 'No')
table(weather_pred2, weather$RainTomorrow)

install.packages("nnet")
library(nnet)

df=data.frame(x2=c(1:6),
              x1=c(6:1),
              y=factor(c('no','no','no','yes','yes','yes')))
str(df)

# 인공신경망 모델 생성
model_net <- nnet(y~., df, size=1) 

# 결과는 5개의 가중치 생성, 오차는 점차적으로 줄어드는 결과를 확인할 수 있다.
model_net 
# 신경망(a 2-1-1)은 (경계값-입력변수-은닉층-출력변수)

summary(model_net) # 가중치 요약 정보 확인
# 입력층의 경계값(b) 1개와 입력변수(i1, i2)2개가 은닉층(h1)으로 연결되는 가중치
# 은닉층의 경계값(b) 1개와 은닉층의 결과값이 출력층으로 연결되는 가중치 

model_net$fitted.values  # 분류모델의 적합값 

#분류모델의 예측치 생성, 정확도 확인
#type="class"는 예측 결과를 출력변수 y의 범주('no','yes')로 분류
p <- predict(model_net, df, type="class")
table(p, df$y)


idx<-sample(1:nrow(iris), 0.7*nrow(iris))
training <- iris[idx, ]
testing <- iris[-idx, ]

model_net_iris <- nnet(Species ~ ., training, size=1)
#은닉층 1개, 11개의 가중치 , 출력값 3개


model_net_iris3 <- nnet(Species ~ ., training, size=3)
#은닉층 3개,  27개의 가중치, 출력값 3개

※ 입력변수의 값들이 일정하지 않으면 과적합(overfitting)을 피하기 위해서 정규화 과정을 수행해야 함.

#가중치 확인
summary(model_net_iris) 
summary(model_net_iris3)

#분류모델의 정확도 평가
table(predic(model_net_iris, testing, type="class"), testing$Species)
#정확률은 
table(predic(model_net_iris3, testing, type="class"), testing$Species)
#정확률은

install.packages("neuralnet")
library(neuralnet)
idx<-sample(1:nrow(iris), 0.7*nrow(iris))
training <- iris[idx, ]
testing <- iris[-idx, ]

※neuralnet()함수는 종속변수(출력변수y)가 수치형이어야 합니다.

training$Species2[training$Species=='setosa'] <- 1
training$Species2[training$Species=='versicolor'] <- 2
training$Species2[training$Species=='virginica'] <- 3
head(training)

testing$Species2[testing$Species=='setosa'] <- 1
testing$Species2[testing$Species=='versicolor'] <- 2
testing$Species2[testing$Species=='virginica'] <- 3
head(testing)

training$Species2 <- NULL
testing$Species2 <- NULL
# 정규화 함수를 이용하여 학습데이터와 검정데이터를 정규화
# 0과 1사이의 범위로 컬럼값을 정규화 
normal <- function(x){
  return ((x-min(x))/(max(x)-min(x)))
}

training_nor <- as.data.frame(lapply(training, normal))
summary(training_nor)

testing_nor <- as.data.frame(lapply(testing, normal))
summary(testing_nor)

#인공신경망 분류 모델 생성
model_net <- neuralnet(Species2 ~ Sepal.Length+ Sepal.Width+Petal.Length+Petal.Width, data=training_nor, hidden=1)
model_net
plot(model_net)

#분류모델 정확도(성능) 평가
model_result <- compute(model_net, testing_nor[c(1:4)])
model_result$net.result

#상관관계분석 : 상관계수로 두 변수 간의 선형관계의 강도 측정
#예측된 꼭 종류와 실제 관측치 사이의 상관관계 측정
cor(model_result$net.result, testing_nor$Species2)

#은닉층 2개
model_net2 <- neuralnet(Species2 ~ Sepal.Length+ Sepal.Width+Petal.Length+Petal.Width, 
                        data=training_nor, hidden=2, algorithm="backprop", learningrate=0.01)
model_net2
plot(model_net2)


#분류모델 정확도(성능) 평가
model_result2 <- compute(model_net, testing_nor[c(1:4)])
model_result2$net.result
cor(model_result2$net.result, testing_nor$Species2)

data(mpg)
idx<-sample(1:nrow(iris), 0.7*nrow(iris))
str(mpg)

training <- mpg[idx, ]
testing <- mpg[-idx, ]
formula <- cty ~ displ + cyl + year
