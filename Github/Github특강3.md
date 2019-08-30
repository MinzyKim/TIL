# Github 3일차

## 1. 오전 수업 흐름

Desktop > web 폴더 생성

web에 README.md 파일 생성

web에 index.html 파일 생성

### 상황 1. fast-foward

1. feature/index branch 생성 및 이동
2. 작업 한 이후 commit
3. master 이동
4. master에 병합
5. 결과 - fast-forwarding (단순히 HEAD를 이동)

-----------------------------------------

### 상황 2. merge commit

1. feature/signin branch 생성 및 이동
2. 작업을 한 이후 commit
3. master 이동
4. master에 추가 commit이 발생되어 있음
5. master에 병합
6. merge commit 발생

7. graph 생성

--------------------------------------------

### 상황 3. merge commit 충돌

1. feature/board branch 생성 및 이동

   ```bash
   $ git checkout -b feature/board
   ```

2. 작업 완료 후 commit

   * `.gitignore` 수정

     ```bash
     $ vi .gitignore
     $ git add .
     $ git commit -m 'Edit .gitignore'
     ```

3. master 이동

   ```bash
   $ git checkout master
   ```

   

4. *master에 추가 commit 이 발생시키기!!*

   - 다른 branch에서 작업한 파일을 같이

   - `.gitignore` 수정

     ```bash
     $ vi .gitignore
     ```

     

5. master에 병합

   ```bash
   $ git merge feature/board
   ```

6. 결과 -> *merge conflict발생*

   ```bash
   $ git merge feature/board
   Auto-merging .gitignore
   CONFLICT (content): Merge conflict in .gitignore
   Automatic merge failed; fix conflicts and then commit the result.
   (master|MERGING) $
   ```

7. 충돌 확인 및 해결

   ```bash
   <<<<<<< HEAD
   *.xlsx
   =======
   *.csv
   >>>>>>> feature/board
   ```

   * 충돌 mark 를 확인하여, 코드를 알맞게 수정한다!
   * `git status` 명령어 통해서 어느 파일이 충돌인지 확인한다.

8. merge commit 진행*

   ```bash
   $ git add .
   $ git commit
   ```

   * commit 메시지는 미리 작성되어 있다!

9. 그래프 확인하기

   ```bash
   $ git log --oneline --graph
   ```

10. branch 삭제

    ```bash
    $ git branch -d feature/board
    ```

### 	3. 코드

- `master` - 항상 동작하는 코드, 운영되고 있는 코드
- 브랜치 생성/변경

```bash
$ git branch # 현재 브랜치 조회
* master

$ git branch fueature/index # 브랜치 생성

$ git branch # 만들어졌는지 확인
 feature/index
 * master
 
 $ git checkout feature/index # 브랜치 이동
Switched to branch 'feature/index'

$ git checkout master
Switched to branch 'master'

$ git merge feature/index # master -> feature/index와 merge
Updating 1f3a530..a376e9c
Fast-forward
 index.css | 0
 index.js  | 0
 2 files changed, 0 insertions(+), 0 deletions(-)
 create mode 100644 index.css
 create mode 100644 index.js

$ git branch -d {브랜치명} #브랜치 삭제

$ git checkout -b {브랜치명} #브랜치 생성 및 이동

$ git merge feature/signin # merge
Merge made by the 'recursive' strategy.
 signin.css  | 0
 signin.html | 0
 2 files changed, 0 insertions(+), 0 deletions(-)
 create mode 100644 signin.css
 create mode 100644 signin.html

$ git log --oneline
5c57f9f (HEAD -> master) Merge branch 'feature/signin'
171202f Hotfix ko -> en
9cbe563 (feature/signin) Init Signin
a376e9c (feature/index) Complete index page
1f3a530 Init index.html

$ git log --oneline --graph # graph형식으로 보여줌
*   5c57f9f (HEAD -> master) Merge branch 'feature/signin'
|\
| * 9cbe563 (feature/signin) Init Signin
* | 171202f Hotfix ko -> en
|/
* a376e9c (feature/index) Complete index page
* 1f3a530 Init index.html
```

- `git checkout {브랜치명}` - 브랜치 생성

- `Fast-forward` - 곧장 merge가 가능하다는 얘기. master branch 에 커밋한 내용과 feature/index branch의 커밋한 내용에 충돌이 없어서 바로 merge가능, 브랜치의 이력이 변화하지 않았기 때문! (feature/test 브랜치 생성 이후에 커밋이 추가되지 않음)

- `recursive` - git이 알아서 merge해줬다.

  

### 4. Branch 관리

```bash
$ git branch {브랜치명} # 브랜치 생성
$ git branch -d {브랜치명} # 브랜치 삭제
$ git branch -b {브랜치명} # 브랜치 생성&이동
$ git checkout {브랜치명} # 브랜치 이동
```

### 5. Stash - 임시 공간

> 작업 중에 작업이 완료되지 않아서 커밋을 하기 애매한 상황에서 임시적으로 현재의 변경사항을 저장할 수 있는 공간이 있다.

1. 현재 작업 파일 stash로 이동

   - `working directory`작업 이력을 stash로 이동시킨다.

   ```bash
   $ git stash
   ```

2. `working directory`에 반영

   - 다시 작업 이력을 불러온다.

   ```bash
   $ git stash pop # apply + drop
   ```

   - 위의 명령어는 아래의 두 개의 명령어를 실행시키는 것과 동일하다.

   ```bash
   $ git stash apply # 불러오기
   $ git stash drop # 삭제하기
   ```

3. stash 확인

   ```bash
   $ git stash list
   ```

   

### 6. Reset vs Revert

### 1. Reset

> 특정 시점의 이력으로 되돌릴 수 있다.

1. 특정 시점 + 변경사항을 Staging Area

```bash
$ git reset {커밋해시코드}
```

2. 특정 시점

```bash
$ git reset --hard {커밋해시코드}
```

* Working directory에 기존의 변경사항을 남겨주지 않음!

### 2. Revert

> 특정 시점의 이력으로 돌아갔다는 커밋과 함께 되돌릴 수 있다.

```bash
$ git revert {커밋해시코드}
```



## 2. 오후 수업 흐름

### 1. 꿀팁

- chrome webstore - json - json viewer
- 오픈 api사용시 아이디와 비밀번호를 깃에 공개하면 문제가 될 수 있다!!
  - 때문에 .env 파일 설정 후 .gitignore에 등록, 환경변수를 불러와 변수에 삽입 후 사용할 것.
- pip install -r requirements.txt - txt파일에 적힌 pip를 모두 install해준다.

### 2. 파이썬으로 실습

	#### 1. 네이버 파파고 api 활용

```python
import requests
from decouple import config

# 1. 환경변수 설정
naver_client_id = config('NAVER_CLIENT_ID')
naver_client_secret = config('NAVER_CLIENT_SECRET')
print(naver_client_id)

# 2. URL 설정
url = 'https://openapi.naver.com/v1/papago/n2mt'
# 3. 헤더 및 data 설정
headers = {
    'X-Naver-Client-Id': naver_client_id,
    'X-Naver-Client-Secret': naver_client_secret
}
data = {
    'source':'ko',
    'target':'en',
    'text':'댕댕이'
}
# 4. 요청
# url에 헤더와 데이터를 포함해서 POST 요청을 보내고
# 그 결과(json)을 파싱
response = requests.post(url, headers=headers, data=data).json()
print(response)
```

#### 2. telegram으로 chatbot 만들어서 주기적으로 lotto번호 전송하기

```python
import requests
import random
from decouple import config

# 0. 로또 번호 추출

numbers = range(1,46) ## 반복문
text = sorted(random.sample(numbers,6))
#print(lotto)

# 1. 토큰 값 설정
token = config('TELEGRAM_TOKEN')
# 2. url 설정
# chat_id, text 요청변수 설정
# string interpolation - 문자열 내에 변수 값 삽입(f-string)
base_url = f'https://api.telegram.org/bot{token}'
chat_id=712426337
# text='안녕'
url = f'{base_url}/sendMessage?chat_id={chat_id}&text={text}'


# 3. 메시지 보내기
requests.get(url)

```

*`github` `telegram-chatbot`레파지토리에 올려두었음

