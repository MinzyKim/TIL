# 파이썬 10일차

## 1. Imaging Resizing 하기

 	1. Pillow : PIL 프로젝트에서 fork 된 라이브러리
     	- 이미지 파일형식 지원
     	- 다양한 이미지를 처리
     	- ImageField 생성할 때 필수
	2. pilkit : Pillow를 쉽게 쓸 수 있도록 도와주는 패키지, 다양한 프로세서를 지원
    - Thumbnail
    - Resize
    - Crop
	3. Django-imagekit : 이미지 썸네일 Django app(실제로 처리할 때는 pillkit)
    - 이미지 썸네일 헬퍼 장고앱