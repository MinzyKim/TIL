# Linux4일차

### 1. 명령어 번호

```
리눅스 고유의 디스크 기반 파일 시스템 종류 : ext, ext2, ext3, ext4, XFS
파일 시스템을 디렉토리 계층 구조의 특정 디렉토리와 연결하는 것 : mount
리눅스 시스템이 부팅될때 자동으로 파일 시스템이 마운트되게 하려면 /etc/fstab 파일에 설정
디스크 파티션 생성, 삭제 , 보기등 파티션 관리 명령 : fdisk
리눅스 시스템이 부팅될때 스크립트들을 순차적으로 실행시켜서 다른 프로세스를 동작시키는 프로세스 : init 프로세스
init은 시스템의 단계를 일곱개로 정의, 각 단계별로 셸 스크립트를 실행 : Runlevel
```

0 - 시스템 종료 - /etc/rc0.d

1, S - 응급 복구 모드(싱글 사용자 모드) - /etc/rc1.d

2, 3, 4 - 다중 사용자 모드

5 - 그래피컬 다중 사용자 모드

6 - 재시작



### 2. 리눅스 종료 

- poweroff, shutdown, halt, init 0, init 6, reboot



### 3. 파일 아카이브

파일을 묶어서 하나로 만드는 것 : 파일 아카이브



#### 	3.1 관련 명령어

다른 시스템과 파일을 주고 받거나, 백업을 하기 위해 여러 파일이나 디렉토리를 하나의 아카이브 파일로 생성, 추출, 업데이트하는 명령 : tar

새 아카이브 생성 : tar cvf

아카이브 파일 내용 확인 : tar tvf

아카이브 풀기 : tar xvf

아카이브 업데이트 :  tar uvf 

아카이브 파일 추가 :  tar rvf

아카이브를 생성, 동시에 압축 :  tar cvzf (gzip), tar cvjf(bzip2)

압축 하기/풀기 : gzip/gunzip (.gz), bzip2/bunzip2 (.bz)

압축 파일의 내용 보기 : zcat, / bzcat





### 4. User

사용자 계정 정보가 저장된 기본 파일 : /etc/passwd

사용자 계정의 암호화된 비밀번호 정보가 저장된 기본 파일 : /etc/shadow



사용자가 속한 그룹 정보가 저장된 기본 파일 : /etc/group

사용자가 속한 그룹의 암호화된 비밀번호 정보가 저장된 기본 파일 : 

/etc/gshadow



사용자 계정 생성 - useradd, adduser

사용자 계정 수정 - usermod

사용자 계정 삭제 - userdel

사용자 계정의 패스워드 에이징 관리 - chage



사용자 그룹 생성 - groupadd, addgroup

사용자 그룹 수정 - groupmod

사용자 그룹 삭제 - groupdel



사용자 로그인 정보 확인 - who

사용자 이름, 로그인한 시간, 로그아웃한 시간, 터미널번호, IP 주소 확인 - last

사용자가 소속된 그룹 확인 - groups



파일이나 디렉토리의 소유자, 그룹 변경 - chown -R 소유자:그룹 ~

​																	chown 소유자 ~

​																	chgrp 그룹 ~