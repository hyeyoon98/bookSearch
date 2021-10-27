# 도서조회 애플리케이션
도서목록을 불러와 도서에 대한 가격, 출판사, 줄거리 등의 정보를 제공하고, 회원가입 시 리뷰와 좋아요를 작성할 수 있는 프로그램

## 프로젝트 특징
* 회원가입, 로그인 및 컨텐츠에 대한 CRUD 중점으로 구현
* 로그인
  *JWT토큰, 로컬스토리지 활용
* CRUD 구현
  * RestAPI
  * DB에 저장되어 있는 도서에 대한 전체, 좋아요순, 별점순 조회
  * 별점 부여 및 리뷰 작성, 조회, 수정, 삭제
  * 도서에 좋아요 하기, 좋아요 취소
*페이징처리
  * 백엔드에서 페이징 처리된 전체 도서 목록을 scrollViewChangeLister를 통해 도서 목록을 10개씩 정렬
  
## 개요
* 애플리케이션명 : starBooks
* 개발 인원 : 4명
* 개발 기간 : 2021.08.23~2021.10.02
* 개발 결과물 : Android App, 웹사이트
* 개발 언어 : Java
* 협업툴 : notion


## 기능
1. DB에 저장되어 있는 도서 데이터를 서버를 통해 애플리케이션에서 정렬된 도서 리스트를 볼 수 있다.
2. 리스트에 있는 도서목록 중 1개의 도서를 클릭하면 도서의 상세정보(줄거리, 리뷰, 좋아요)를 볼 수 있다.
3. 회원 가입을 한 사용자에게는 리뷰 작성 기능과 좋아요 클릭 기능을 제공한다.

## 개발 환경
* mysql
* Android Studio @29.0.3

## 사용 라이브러리
* DataBinding
* Retrofit2
* ViewPager2
* RecyclerView
* glide
  * 이미지url을 이미지뷰에 넣기 위해 사용

