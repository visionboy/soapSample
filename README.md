# Spring Boot SOAP Web Service

Spring Boot 2.x 기반 SOAP 웹 서비스 애플리케이션입니다. Apache CXF를 사용하여 MariaDB의 videos 테이블 데이터를 SOAP 프로토콜로 제공합니다.

## 기술 스택

- **Java**: 1.8+
- **Spring Boot**: 2.7.18
- **Apache CXF**: 3.5.5
- **Database**: MariaDB
- **ORM**: Spring Data JPA

## 프로젝트 구조

```
soap/
├── src/main/java/com/example/soap/
│   ├── SoapApplication.java          # 메인 애플리케이션
│   ├── config/
│   │   └── CxfConfig.java            # CXF SOAP 엔드포인트 설정
│   ├── entity/
│   │   └── Video.java                # JPA 엔티티
│   ├── repository/
│   │   └── VideoRepository.java      # 데이터 접근 레이어
│   ├── dto/
│   │   └── VideoDTO.java             # SOAP 응답 DTO
│   └── service/
│       ├── VideoService.java         # SOAP 서비스 인터페이스
│       └── VideoServiceImpl.java     # SOAP 서비스 구현
├── src/main/resources/
│   └── application.properties        # 애플리케이션 설정
└── pom.xml                           # Maven 의존성 설정
```

## 설정 방법

### 1. 데이터베이스 설정

MariaDB에 데이터베이스와 테이블을 생성합니다:

```sql
-- 데이터베이스 생성
CREATE DATABASE albert;
USE albert;

-- videos 테이블 생성
CREATE TABLE videos (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    user_id VARCHAR(255),
    engine VARCHAR(255),
    source_image VARCHAR(500),
    prompt TEXT,
    video_url VARCHAR(500)
);

-- 테스트 데이터 삽입 (선택사항)
INSERT INTO videos (user_id, engine, source_image, prompt, video_url) VALUES
('user1', 'engine1', 'http://example.com/image1.jpg', 'Test prompt 1', 'http://example.com/video1.mp4'),
('user2', 'engine2', 'http://example.com/image2.jpg', 'Test prompt 2', 'http://example.com/video2.mp4');
```

### 2. application.properties 수정

`src/main/resources/application.properties` 파일에서 데이터베이스 접속 정보를 수정합니다:

```properties
spring.datasource.url=jdbc:mariadb://localhost:3306/albert
spring.datasource.username=your_username
spring.datasource.password=your_password
```

### 3. 빌드 및 실행

```bash
# 프로젝트 빌드
mvn clean install

# 애플리케이션 실행
mvn spring-boot:run
```

## SOAP 서비스 사용 방법

### WSDL 확인

애플리케이션 실행 후 다음 URL에서 WSDL을 확인할 수 있습니다:

```
http://localhost:8080/services/VideoService?wsdl
```

### SOAP 요청 예제

**getVideos 메서드 호출:**

```xml
<soapenv:Envelope xmlns:soapenv="http://schemas.xmlsoap.org/soap/envelope/" 
                  xmlns:ser="http://service.soap.example.com/">
   <soapenv:Header/>
   <soapenv:Body>
      <ser:getVideos/>
   </soapenv:Body>
</soapenv:Envelope>
```

**응답 예제:**

```xml
<soap:Envelope xmlns:soap="http://schemas.xmlsoap.org/soap/envelope/">
   <soap:Body>
      <ns2:getVideosResponse xmlns:ns2="http://service.soap.example.com/">
         <return>
            <id>1</id>
            <userId>user1</userId>
            <engine>engine1</engine>
            <sourceImage>http://example.com/image1.jpg</sourceImage>
            <prompt>Test prompt 1</prompt>
            <videoUrl>http://example.com/video1.mp4</videoUrl>
         </return>
         <return>
            <id>2</id>
            <userId>user2</userId>
            <engine>engine2</engine>
            <sourceImage>http://example.com/image2.jpg</sourceImage>
            <prompt>Test prompt 2</prompt>
            <videoUrl>http://example.com/video2.mp4</videoUrl>
         </return>
      </ns2:getVideosResponse>
   </soap:Body>
</soap:Envelope>
```

### 테스트 도구

다음 도구들을 사용하여 SOAP 서비스를 테스트할 수 있습니다:

- **SoapUI**: GUI 기반 SOAP 테스트 도구
- **Postman**: REST/SOAP API 테스트 도구
- **curl**: 커맨드라인 도구

**curl 예제:**

```bash
curl -X POST http://localhost:8080/services/VideoService \
  -H "Content-Type: text/xml" \
  -d '<soapenv:Envelope xmlns:soapenv="http://schemas.xmlsoap.org/soap/envelope/" xmlns:ser="http://service.soap.example.com/"><soapenv:Header/><soapenv:Body><ser:getVideos/></soapenv:Body></soapenv:Envelope>'
```

## API 명세

### getVideos

videos 테이블의 모든 레코드를 조회합니다.

**쿼리:**
```sql
SELECT id, user_id, engine, source_image, prompt, video_url FROM videos;
```

**응답 필드:**
- `id`: 비디오 ID (Long)
- `userId`: 사용자 ID (String)
- `engine`: 엔진 정보 (String)
- `sourceImage`: 원본 이미지 URL (String)
- `prompt`: 프롬프트 텍스트 (String)
- `videoUrl`: 비디오 URL (String)

## 문제 해결

### 데이터베이스 연결 오류

- MariaDB 서버가 실행 중인지 확인
- `application.properties`의 접속 정보가 올바른지 확인
- 방화벽 설정 확인

### WSDL 접근 불가

- 애플리케이션이 정상적으로 시작되었는지 확인
- 포트 8080이 사용 중이 아닌지 확인
- 로그에서 CXF 엔드포인트 발행 메시지 확인

## 라이선스

MIT License
