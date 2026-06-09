# Spring Boot Practice

Spring Boot를 학습하며 단계적으로 기능을 확장한 실습 프로젝트 모음입니다.  
기초 웹 구성부터 DB 연동, 파일 업로드, Spring Security까지 직접 구현하며 익혔습니다.

---

## Projects

### 1. `premium` — Spring Boot 기초 실습
> DB 없이 Controller, Thymeleaf 템플릿, 레이아웃 구성을 익히는 입문 프로젝트

- 로그인 · 회원가입 폼 처리 (세션 없이 뷰 흐름 연습)
- 로또 번호 랜덤 생성 (Java Random → Model → View 흐름)
- Thymeleaf Fragment로 공통 헤더 · 푸터 레이아웃 분리

**Stack:** `Spring Boot 3.5` `Thymeleaf` `Java 17`

---

### 2. `shop` — 게시판 CRUD + 파일 업로드
> MyBatis와 MariaDB를 연동하여 실제 데이터가 오가는 게시판을 구현한 프로젝트

- 게시글 목록 · 작성 · 상세 · 수정 · 삭제 (CRUD 전체)
- 파일 첨부 업로드 및 다운로드
- 페이지네이션 (`PageVO`)
- MyBatis XML Mapper로 SQL 분리 관리
- Lombok으로 VO 보일러플레이트 제거

**Stack:** `Spring Boot 3.5` `Thymeleaf` `MyBatis` `MariaDB` `Lombok` `Java 17`

---

### 3. `board/board` — 게시판 + Spring Security
> shop 프로젝트를 기반으로 Spring Security를 추가 적용한 확장 프로젝트

- shop의 게시판 CRUD · 파일 업로드 · 페이지네이션 기능 포함
- Spring Security 설정 (`SecurityFilterChain`) 직접 구성
- 검색 기능 추가 (`SearchVO`)
- 파일 서비스 레이어 분리 (`BoardFileService`)

**Stack:** `Spring Boot 3.5` `Thymeleaf` `MyBatis` `MariaDB` `Spring Security` `Lombok` `Java 17`

---

## 학습 흐름

```
premium         →        shop          →      board/board
(뷰·컨트롤러)      (DB·파일·페이지네이션)     (Security·서비스분리·검색)
```

---

## 공통 기술 스택

| 분류 | 사용 기술 |
|------|-----------|
| Language | Java 17 |
| Framework | Spring Boot 3.5 |
| Template | Thymeleaf |
| ORM | MyBatis 3.0 |
| DB | MariaDB |
| Security | Spring Security 6 |
| Build | Gradle |
| Etc | Lombok |
