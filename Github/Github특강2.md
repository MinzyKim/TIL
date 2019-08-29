# Github 특강 2

## 1. Learning Git

- Git이란?

  - (분산)버전관리 시스템
  - 코드의 history를 관리하는 도구
  - 개발된 과정과 역사를 볼 수 있고, 특정 시점으로 복구가 가능

- Git, DVCS(Distrubuted Vesion Control System) vs CVCS

  ### 1.1 다른 PC에서 내 레파지토리 접속

- git init할 때는 잘

- touch README.md - README만들 때
- touch 파일명 - 파일 생성할 때
- git status - 상태 확인

![1567042213469](C:\Users\student\AppData\Roaming\Typora\typora-user-images\1567042213469.png)

- git log - 커밋이 어떻게 이루어졌는지 확인

  ![1567042186441](C:\Users\student\AppData\Roaming\Typora\typora-user-images\1567042186441.png)

  git 커밋을 하기 위해서는 초기에 작성자설정을 반드시 해야 한다.

  ```bash
  $ git config --global user.name {사용자이름}
  $ git config --global user.eamil {사용자이메일}
  ```

  현재 global로 설정된 환경설정을 확인하기 위해서는 아래의 명령어를 작성한다.

  ```bash
  $ git config --global --list
  user.email=kimminji728@naver.com
  user.name=MinzyKim
  winupdater.recentlyseenversion=2.23.0.windows.1
  filter.lfs.process=git-lfs filter-process
  filter.lfs.required=true
  filter.lfs.clean=git-lfs clean -- %f
  filter.lfs.smudge=git-lfs smudge -- %f
  ```

  >Git은 분산관리버전시스템(DVCS)이다.
  >
  >소스코드의 이력을 관리한다.

- 참고 문서
  - (링크 걸 때)[Git scm](git-scm.com/book)
  - [Git 입문](<https://backlog.com/git-tutorial/kr/>)

## 2. Git 활용 기초

1. 로컬 git 저장소 설정

   ```bash
   $ git init
   Reinitialized existing Git repository 
   in C:/Users/student/algorithms/.git/
   ```

   - 해당 디렉토리에 `.git/` 폴더가 생성 된다.
   - 항상 `git init`하기 전에는 해당 폴더가 이미 로컬 저장소인지((`master`) 여부) 확인 하여야 한다.

2. add

   ```bash
   $ git add .
   $ git add README.md a.txt
   $ git add folder/
   
   $ git status
   On branch master
   Your branch is ahead of 'origin/master' by 1 commit.
     (use "git push" to publish your local commits)
   
   nothing to commit, working tree clean
   
   ```

   - `add` 명령어를 통해서 `working directory`에서 `INDEX(staging area)`로 특정 파일들을 이동시킨다.
   - 커밋을 할 목록에 쌓는 것

3. commit

   ```bash
   $ git commit -m '커밋메세지'
   $ git commit
   [master alsadf]
   1 file changed, 1 insertion(+)
   $ git log
   ```

4. 커밋 히스토리 확인하기(log)

   ```bash
   $ git log
   $ git log -2
   $ git log --oneline
   ```

5. 현재 git 상태 알아보기(status)***중요! 자주 입력해서 확인하자!**

   ```bash
   $ git status
   ```



## 3. 원격저장소(remote) 활용하기

### 1. 기초

1. remote 저장소 등록

   ```bash
   $ git remote add origin {github URL}
   ```

   - 원격 저장소를 `origin` 이라는 이름으로 `URL`을 등록한다.

2. remote 저장소 확인

   ```bash
   $ git remote -v
   ```

3. remote 저장소 삭제

   ```bash
   $ git remote rm {저장소 이름}
   ```



### 2. push - pull

1. 원격 저장소로  보내기(`push`)	

```bash
$ git push origin master
```

2. 원격 저장소로부터 가져오기(`pull`)

```bash
$ git pull origin master
```



### 3. Git Conflict 시나리오

Local A, Local B, Github로 활용을 하는 경우 원격저장소 이력과 달라져서 충돌이 발생할 수 있다. 따라서, 항상 작업을 시작하기 전에 `pull`을 받고, 작업을 완료한 이후에 `push`를 진행하면 충돌 사항이 발생하지 않는다.

 1. auto-merge

    - 동일한 파일을 수정하지 않은 경우 자동으로 `merge commit`이 발생한다.

    ```bash
    1. Local A에서 작업 후 push
    2. Local B에서 작업 후 pull을 받지 않음.
    3. Local B에서 다른 파일 작업 후 commit -> push
    4. 오류 발생(~~git pull을 받으세요~~)
    5. Local B에서 git pull
    6. 자동으로 vim commit 할 수 있도록 뜸
    7. 저장하면, merge commit 발생
    8. Local B에서 git push!
    ```

2. merge conflict

   - 다른 이력(커밋)으로 동일한 파일이 수정되는 경우 merge conflict 발생
   - 직접 충돌 파일을 해결 해야 한다.

   ```bash
   1. Local A에서 작업 후 push
   2. Local B에서 작업 후 pull을 받지 않음.
   3. Local B에서 동일 파일 작업 후 commit -> push
   4. 오류 발생(~~git pull을 받으세요~~)
   5. Local B에서 git pull
   6. 충돌 발생(merge conflict)
   7. 직접 오류 수정 및 add, commit
   8. Local B에서 git push
   ```

   - `git status` 명령어를 통해 어느 파일에서 충돌이 발생하였는지 확인 가능
   - 실제 파일 예시

   ```bash
   <<<<<<<<<< HEAD
   Local B 작업
   ================
   원격 저장소에 기록된 작업
   >>>>>>>>>>>>> asdf7sdf8dsfas9sdf0fdsa9asdf3 (해쉬값)
   ```

   
   
   ## 4. 되돌리기
   
   1. `Staging area` 에서 unstage
   
      ```bash
      $ git status
      On branch master
      Your branch is ahead of 'origin/master' by 1 commit.
        (use "git push" to publish your local commits)
      
      Changes to be committed:
        (use "git reset HEAD <file>..." to unstage)
      
              deleted:    b.txt
      $ git reset HEAD b.txt
      ```
   
   2. commit 메시지 수정하기
   
      ```bash
      $ git commit --amend
      ```
   
      - 커밋 메시지를 수정하게 되면 해시값이 변경되어 이력이 변화하게 된다.
   
      - 따라서 원격 저장소에 push된 이력이라면 절대 변경하면 안된다!
   
      - 커밋을 하는 과정에서 파일을 빠뜨렸다면, 위의 명령어를 통해서 수정할 수도 있다!
   
        ```bash
        $ git add omit_file.txt
        $ git commit --amend
        ```
   
   3. `working directory` 변경사항 버리기
   
      ```bash
      $ git checkout -- 파일명
      ```
   
      - 변경사항이 모두 삭제 되고, 해당 파일의 이전 커밋 상태로 변화한다!