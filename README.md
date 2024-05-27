# pjymes

개발 기간 : 2024년 4월 8일 ~ 작업 중

웹 사이트 : [pjymes.site](http://pjymes.site/login)

## 프로젝트 소개
자재 발주부터 제품생산 및 제품 출고까지 관리 할 수 있는 간단한 MES 프로그램입니다.

전 회사에서 MES 프로그램을 만들면서 적용하지 못했던 기술들을 새롭게 적용시켜보고 싶어 만들게 되었습니다.

SpringBoot와 Spring Data JPA를 사용해 기본적인 REST API를 구현하였고,

생산활동에서 생산량을 기계로부터 받아 화면에 보여줄 수 있도록 SSE(Server-Sent-Events) 활용하여 실시간으로 view에 적용되도록 구현하였습니다.

또한 가비아에서 도메인을 구입, AWS EC2, RDS 등을 이용해 서버를 배포했습니다.

### 개발 환경 및 사용기술
 
백엔드

주요 프레임워크 / 라이브러리
 - Java 17 openjdk
 - SpringBoot 2.5.6
 - SpringBoot Security
 - Spring Data JPA
 - Querydsl
 - Lombok

Build tool
 - Gradle

Database
 - MariaDB

Infra
 - AWS EC2
 - AWS RDS
 - AWS Route53

프론트엔드
 - Javascript
 - HTML/CSS
 - Yhymeleaf
 - Bootstrap 5
 - Tabulator
 - Axios

Tools
 - IntelliJ
 - GitHub

## ERD 구조

👉 [ERD Cloud에서 직접 보기](https://www.erdcloud.com/d/hK7Y5aYaT7vWG6p6E)  

![pjymes erd 2](https://github.com/woduf2720/pjymes/assets/68207825/d35d6bf4-8316-491d-b791-76d49e7179df)

## 주요 기능

![pjymes 메뉴구조도](https://github.com/woduf2720/pjymes/assets/68207825/4153a0c2-0092-45b6-95a2-1f1df3c3d8f9)

### 메뉴 설명 (화살표 누르면 확장)

<details>
<summary>
시스템 관리 (관리자 전용)
</summary>

1. 메뉴 관리
    - 개발자가 메뉴를 추가할때 사용

2. 공통 코드 관리
    - 기준정보 및 시스템에 관련된 코드 등록 및 수정
</details>

<details>
<summary>
기준 정보 관리
</summary>

1. 사용자 관리
     - 프로그램을 사용할 수 있는 사용자 등록 및 수정

2. 품목 정보 관리
    - 프로그램에 사용될 품목 등록 및 수정

3. 거래처 관리
   - 프로그램에 사용될 거래처 등록 및 수정

4. BOM 관리
   - 자재 명세서 등록 및 수정
</details>
 
<details>
<summary>
자재 관리
</summary>

1. 발주 관리
   - 거래처에 따른 발주 및 세부 품목 내용 등록, 수정, 삭제

2. 자재 입고 관리
   - 발주 등록한 내용을 기준으로 입고 등록, (LOTNO 생성)

3. 자재 출고 관리
   - 창고에 있는 품목에서 LOTNO 선택하여 출고 등록

4. 자재 입고 현황
   - 자재 입고 관리에서 등록한 내역 조회

5. 자재 출고 현황
   - 자재 출고 관리에서 등록한 내역 조회
</details>

<details>
<summary>
생산 관리
</summary>

1. 생산 계획
   - 등록된 수주 내역을 바탕으로 생산 계획 등록

2. 생산 활동
   - 등록된 계획을 현장에서도 쉽게 시작, 완료 가능하도록 구성
   - 설비에서 보낸 생산신호를 받아 저장할 수 있도록 설계
   - SSE(Server-Sent-Events) 활용하여 생산신호가 오면 즉각 view에 반영될 수 있도록 구현
   - 설비가 없으므로 임시 테스트 기능 추가

3. 생산 실적
   - 등록된 생산 계획 중 생산 완료된 내역 조회
</details>

<details>
<summary>
제품 관리
</summary>

1. 수주 관리
   - 거래처에 따른 수주 및 세부 품목 내용 등록, 수정, 삭제

2. 제품 입고 관리
   - 기준 정보 관리에 등록된 품목을 기준으로 입고 등록, (LOTNO 생성)

3. 제품 출고 관리
   - 등록된 수주 내역을 기준으로 창고에 있는 품목에서 LOTNO 선택하여 출고 등록

4. 제품 입고 현황
   - 제품 입고 관리에서 등록한 내역 조회

5. 제품 출고 현황
   - 제품 출고 관리에서 등록한 내역 조회
</details>

## 추후 개선점

1. JWT적용
    - 개발 초기부터 적용 할 예정이었으나, 잘 해결이 되지않아 보류
    - 서버 사이드 렌더링 방식(SSR)인 thymeleaf를 사용할 경우 JWT을 사용하기 어려워 클라이언트 사이드 렌더링 방식(CSR)인 react 등 적용 필요

2. BOM 활용
    - 제품을 생산할때 제품에 등록된 BOM에 따라 자재를 소모시키려고 했으나, 개발 과정에서 여러가지 변수가 떠올라 보류
    - 생산 계획을 등록할 시 제품에 대한 BOM이 등록이 안된 경우 또는 자재가 부족한경우 어떻게 할 것인가?
    - 생산 과정에서 자재가 부족한 경우 어떻게 할 것인가?
    - 이와 같은 부분에서 좀 더 고려가 필요

3. 각종 편의성 개선
    - 기준 정보 관리 메뉴에서 데이터를 입력할때 코드나 전화번호, 이메일 등 입력 편의성 부족
    - 발주, 수주 등의 페이지에서 내용을 등록할때 모달창에서 선택 후 포커싱이 이어지지 않는 문제
