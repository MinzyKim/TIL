rm(list=ls())
# 1. 실행환경으로 데이터 로딩
dau <- read.csv('./workDatas/ws3-dau.csv')
head(dau)

user_info <- read.csv('./workDatas/ws3-user_info.csv')
head(user_info)
# 2. DAU데이터에 user_info 데이터 결합
dao.user.info <- merge(dau, user_info, by = c("user_id", "app_name"))
head(userDao)

# 2.5 month 추가하기

dao.user.info$log_month <- substr(dao.user.info$log_date, 1, 7)
tail(dao.user.info)
# 3. 세그먼트 분석
table(dao.user.info[, c("log_month","gender")]) # 성별로 집계
table(dao.user.info[ ,c("log_month", "generation")]) # 연령대별 집계
library(reshape2)
setRepositories()
install.packages("plyr")
library(plyr)

dcast(dao.user.info, log_month ~ gender + generation, 
      value.var = "user_id",length)

dau.user.info.device.summary <- ddply(dao.user.info, .(log_date, device_type), summarize, dau = length(user_id))

# 날짜별 데이터 형식으로 변환하기

dau.user.info.device.summary$log_date <- as.Date(dau.user.info.device.summary$log_date)


library(ggplot2)
library(scales)

limits <- c(0,max(dau.user.info.device.summary$dau))
    

ggplot(dau.user.info.device.summary, aes(x=log_date, y=dau, col=device_type, lty=device_type, shape=device_type)) +
  geom_line(lwd=1) +
  geom_point(size=4) +
  scale_y_continuous(label=comma, limits=limits)


ab.test.imp <- read.csv("./abData/ab_test_imp.csv")
ab.test.goal<-read.csv("./abData/ab_test_goal.csv")

ab.test.imp <- merge(ab.test.imp, ab.test.goal, by="transaction_id", all.x=T, suffixes=c("",".g"))
head(ab.test.imp)

ab.test.imp$is.goal <- ifelse(is.na(ab.test.imp$user_id.g), 0, 1)

ddply(ab.test.imp,
      .(test_case),
      summarize,
      cvr=sum(is.goal)/length(user_id))

ad.result <- read.csv("./data/ad_result.csv")
head(ad.result)


plot(ad.result$tvcm, ad.result$install, xnames="TV 광고비용", ynames="신규 유저수")
plot(ad.result$magazine, ad.result$install)

ggplot(ad.result,aes(x=magazine,y=install))+geom_point()+xlab('잡지 광고비')+ylab('신규유저수')+scale_x_continuous(label=comma)+scale_y_continuous(label=comma)
ggplot(ad.result, aes(x=tvcm, y=install))+geom_point()+xlab('TV 광고비')+ylab('신규 유저수')+scale_x_continuous(label=comma)+scale_y_continuous(label=comma)

fit <- lm(install~.,data=ad.result[ , c('install','tvcm','magazine')])
fit

summary(fit)

# ab.test.imp 데이터에 ab.test.goal 데이터를 결합시키기
# 데이터를 읽어들이기
ab.test.imp <- read.csv("./abData/ab_test_imp.csv",header=T, stringsAsFactors=F)
ab.test.goal <- read.csv("./abdata/ab_test_goal.csv",header=T, stringsAsFactors=F)
# ab.test.imp에 ab.test.goal를 결합시키기
ab.test.imp <- merge(ab.test.imp, ab.test.goal, by="transaction_id", all.x=T, suffixes=c("",".g"))
head(ab.test.imp)



# 클릭 플래그를 추가
ab.test.imp$is.goal <- ifelse(is.na(ab.test.imp$user_id.g),0,1)
head(ab.test.imp)


# 클릭율을 계산하기
library(plyr)
ddply(ab.test.imp, .(test_case), summarize,
      cvr=sum(is.goal)/length(user_id))

# χ2 검정을 실행하기
chisq.test(ab.test.imp$test_case, ab.test.imp$is.goal)


# 날짜별, 테스트 케이스별로 클릭율을 산출하기
ab.test.imp.summary <-
  ddply(ab.test.imp, .(log_date, test_case), summarise,
        imp=length(user_id),
        cv=sum(is.goal),
        cvr=sum(is.goal)/length(user_id))

# 테스트 케이스별로 클릭율을 산출하기
ab.test.imp.summary <-
  ddply(ab.test.imp.summary, .(test_case), transform,
        cvr.avg=sum(cv)/sum(imp))
head(ab.test.imp.summary)

# 테스트 케이스별 클릭율의 시계열추이 그래프
library(ggplot2)
library(scales)

ab.test.imp.summary$log_date <- as.Date(ab.test.imp.summary$log_date)
limits <- c(0, max(ab.test.imp.summary$cvr))
ggplot(ab.test.imp.summary,aes(x=log_date,y=cvr, col=test_case,lty=test_case, shape=test_case)) +
  geom_line(lwd=1) +
  geom_point(size=4) +
  geom_line(aes(y=cvr.avg,col=test_case)) +
  scale_y_continuous(label=percent, limits=limits)

dau <- read.csv("./data/ws6-dau.csv", header=T)
head(dau)

