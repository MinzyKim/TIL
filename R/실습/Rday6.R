data <- read.csv("./datas/homogenity.csv", header=TRUE)
head(data)
str(data)

data <- subset(data, !is.na(survey), c(method, survey))

data$method2[data$method==1]<-"방법1"
data$method2[data$method==2]<-"방법2"
data$method2[data$method==3]<-"방법3"

data$survey2[data$survey==1]<-"매우만족"
data$survey2[data$survey==2]<-"만족"
data$survey2[data$survey==3]<-"보통"
data$survey2[data$survey==4]<-"불만족"
data$survey2[data$survey==5]<-"매우불만족"

table(data$method2, data$survey2) #table(행, 열)
# 방법1, 방법2, 방법3의 관측치의 개수는 50으로 동일 = 반드시 각 집단의 길이가 동일해야 한다


#동질성 검정
chisq.test(data$method2, data$survey2)


smoke <- read.csv("./datas/smoke.csv", header=TRUE)
head(smoke)


smoke$education2[smoke$education==1]<-"대졸"
smoke$education2[smoke$education==2]<-"고졸"
smoke$education2[smoke$education==3]<-"중졸"

smoke$smoke2[smoke$smoking==1]<-"과다흡연"
smoke$smoke2[smoke$smoking==2]<-"보통흡연"
smoke$smoke2[smoke$smoking==3]<-"비흡연"

chisq.test(smoke$education2, smoke$smoke2)

data <- read.csv("./datas/cleanData.csv", header=TRUE)
head(data)

data$age3[data$age<=19]<-"미성년"
data$age3[data$age>=20 & data$age<=29]<-"청년"
data$age3[data$age>=30 & data$age<=39]<-"장년"
data$age3[data$age>=40 & data$age<=49]<-"중장년"
data$age3[data$age>=50 & data$age<=59]<-"중년"
data$age3[data$age>=60]<-"시니어"

data$position[data$job==1]<-"사장"
data$position[data$job==2]<-"부장"
data$position[data$job==3]<-"팀장"
data$position[data$job==4]<-"과장"
data$position[data$job==5]<-"대리"
data$position[data$job==6]<-"사원"

chisq.test(data$age, data$position)

response <- read.csv("./datas/response.csv", header=TRUE)
head(response) 

response$job2[response$job==1]<-"학생"
response$job2[response$job==2]<-"직장인"
response$job2[response$job==3]<-"주부"

response$response2[response$response==1]<-"무응답"
response$response2[response$response==2]<-"낮음"
response$response2[response$response==3]<-"높음"

chisq.test(response$job2, response$response2)

job : 1:학생, 2:직장인, 3:주부
response : 1:무응답, 2:낮음, 3:높음