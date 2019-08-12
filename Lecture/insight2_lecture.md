# 인사이트 특강2

빅데이터 분석

- 확률모델 - 셀 수 있냐 없냐만 따지면 됨

- Deep learning - Artificial Neural Network

- perceptron 

- Linear Clessifier

- 1세대 - 30년

- Occamz Razor

  ```
  오컴의 면도날(Occam's Razor 또는 Ockham's Razor)은 흔히 '경제성의 원리' (Principle of economy), 검약의 원리(lex parsimoniae), 또는 단순성의 원리
  어떤 현상을 설명할 때 불필요한 가정을 해서는 안 된다는 것이다. 좀 더 쉬운 말로 번역하자면, '같은 현상을 설명하는 두 개의 주장이 있다면, 간단한 쪽을 선택하라(given two equally accurate theories, choose the one that is less complex)'는 뜻
  ```

  

- y=ax+b 기울기와 절편을 구하시오
  - parameter learning
  - weighted sum -> 기울기만 구하면 됨

### SVM(Support Vector Machine)

- 성능이 좋은데, 시간이 오래걸리고 알고리즘이 어려워서 Deep learning에게 밀림
- 함수이용분류(Clessification via Mathoematical functions) - 차원을 늘려준다.
- 얼마나 많이 - 회귀분석



### Linear Regression

***기울기 찾기**

- 추세를 볼 때 사용
- 분류 문제인가 회귀 분석인가 목적을 바로 할 것
  - 분류의 직선은 위 아래의 성향을 나눠주는 것
  - 회귀 분석은 추세를 분석하는 것



### Logistic Regression

- 회귀 분석이지만 분류를 이용함
- 확률을 구하면 분류까지 되서 1타 쌍피



### Single Layer Perceptron

- perceptron을 가지고 있는 모든 알고리즘은 2진 분류다.
- -∞~∞
- Threshold를 정해놓고 거기를 넘으면 무조건 1, 아니면 0
- input layer, out layer 2가지가 존재
- 가중치라는 말이 들어가는 애들은 기울기에 임의값을 넣고 계산