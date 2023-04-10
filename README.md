# skkuting

## 개발 환경

- Intellij IDEA Ultimate 2022.1.1 ~ & Visual Studio Code
- Java 17
- Spring Boot 3.0.5
- gradle 7.6.1
- PostMan

### 개발환경 셋업
1. 개발 설정파일 셋업
    1. .env 파일에서 개인별 Gmail SMTP 설정 필수.
    2. 그 외 커스텀은 권장하지 않음
    ```shell
    ./scripts/setup.sh
    ```

2. 실행
    1. docker-compose 단독
        ```shell
        # 애플리케이션 포함한 전체 실행
        docker-compose -f docker-compose.yml -f .devcontainer/docker-compose.yml up
        # DB만 실행 후 애플리케이션은 별개 실행
        docker-compose up
        gradle bootRun
        ````
    2. vscode : 일반 실행 혹은 devcontainer로 실행 가능
    3. intellij : 일반 실행 혹은 docker-compose로 실행 가능
        - docker-compose 실행 시 일반 실행의 docker-compose 파일 두개를 지정해야 함

## 기술 세부 스택

Spring Boot

- Spring Web
- Spring Data JPA
- Spring Security
- H2 Database
- MySQL Driver
- Lombok
- Spring Boot DevTools
- Spring Configuration Processor

## 개발 일지

- 📙 [3/28 정책 수립 및 ERD 작성]( https://www.notion.so/3-28-ERD-baca9ebb97b74d8eb2b5874e67fd7a49?pvs=4 )
- 📋 [4/2 Issue #5 - 물리 테이블 및 도메인 생성]( https://www.notion.so/4-2-Issue-5-8c76bcf617a84a5d810eb24c1cde9a15?pvs=4 )
- 📙 [4/4 Issue #1 - 이메일 인증]( https://www.notion.so/4-4-Issue-1-daca4079d7a34fa0ab5ef1f737d7a496?pvs=4 )
- 📙 [4/5 Issue #1 - 이메일 인증 코드 검증 서버 구현]( https://www.notion.so/4-5-Issue-1-551c5b2e9e1c42d9b5c633dcf093a19d?pvs=4 )
