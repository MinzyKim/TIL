# 파이썬 11일차

## 1. Form

- Form class = html의 form tag

- Model class와 유사

-  일반 Form vs Model Form

  | 일반 Form            | Model Form                         |
  | -------------------- | ---------------------------------- |
  | 항목을 일일이 지정함 | 모델을 기반으로 항목이 정해져 있음 |



- ```python
  class (모델명)Form(forms.Form)
  
  class BoardForm(forms.Form):
  
  		title=forms.CharField()
  
  		content=forms.CharField()
  ```

  

- 모델 폼 선언 방식

  ```python
  Class ArticleForm(forms.ModelForm):
  
  	class Meta:
  
  			model=Article
  
  			field=['title','content'] # 리스트형식으로
  ```



- Form 주요역할
  - 입력 폼 html을 알아서 생성
  - 입력 폼의 값을 검증
  - 검증에 통과된 값을 Dictionary 타입으로 제공