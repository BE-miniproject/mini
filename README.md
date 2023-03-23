# mini
### TO-GETHER

- 항해99 게더 내에서 발생하는 세션 및 스터디 등의 알림사항이나 관련 정보를 공유하는 커뮤니티 사이트 입니다.

## API 및 팀 노션
https://www.notion.so/2-dd05f16cb2c0414c8238ae1b74b83cb0

## 주요기능
- 회원가입
- 로그인 & 로그아웃
- 게시글 전체 조회(페이지)
- 게시글 상세 조회(작성, 수정, 삭제, 조회)
- 댓글(작성, 수정, 삭제, 조회)

## 기술 스택
### **Application**
- JAVA 17
- Spring Boot 2.7.5
- Spring Security 5.7.4
- JPA

### **DATA**
- AWS RDS
- MySQL

### **DevOps**
- AWS EC2

## 팀원
### Back End
- 장동희(팀장)
- 김건율
- 이승빈

### Front End
- 이은형
- 박지혜

## Trouble Shooting

### CI/CD 구현
- GITHUB ACTIONS AWS S3 로 CI/CD 구현하려고 했으나 실패 
- GITHUB ACTIONS & ELESTIC BEANSTALK 사용하여 구현 
- Build with Gradle 부분에서 MiniApplicationTests > contextLoads() FAILED java.lang.IllegalStateException at DefaultCacheAwareContextLoaderDelegate.java:98 오류 발생 
- intelij gradle 설정에서 Build and run using : intelij IDEA, Run tests using : intelij IDEA 으로 설정 후 오류 해결 
- Deploy to EB 부분에서 Error: Deployment failed: AWS Access Key not specified!: github actions를 실행시키는데 aws키가 정상적으로 인식되지 않았다. 
- fork한 팀원은 organizaion의 secrets에 pull request로 접근하지 못하여 발생한 오류 main에서 푸쉬를 통해 오류 해결 
- Deploy to EB 부분에서 08:09:16 INFO: Still updating, status is "Updating", health is "Red", health status is "Severe" 오류 발생 
- application.properties를 ignore하여 secrets.에 application.properties를 넣어서 구현을 했으나 인식을 하지 못하여 발생한 오류 
- application.properties을 push하여 문제 해결 
- CI/CD 구현 
- 하지만 구현은 성공하였지만 DB정보가 노출되어 .gitignore로 다시 가린 뒤 CI/CD를 유지하지않고 수동배포

### DB 타입 에러
- 게시글 작성시 일정 글자수 초과하면 DB 에러 발생
- UTF-8로 DB 인코딩 방법 변경해봤으나 에러 해결 x
- DB 테이블의 칼럼 타입 확인
- content 칼럼이 varchar(255) 타입으로 되어있어 PostRequestDto에 @Size(min = 10, max = 2000)로 걸어둔 제한에 맞게 타입 재설정
- 에러 해결 완료
