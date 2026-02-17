-- ====================================
-- Spring Boot SOAP 웹 서비스 데이터베이스 설정
-- ====================================

-- 데이터베이스 생성
CREATE DATABASE IF NOT EXISTS albert;

-- 데이터베이스 선택
USE albert;

-- videos 테이블 생성
CREATE TABLE IF NOT EXISTS videos (
    id BIGINT AUTO_INCREMENT PRIMARY KEY COMMENT '비디오 ID',
    user_id VARCHAR(255) COMMENT '사용자 ID',
    engine VARCHAR(255) COMMENT '엔진 정보',
    source_image VARCHAR(500) COMMENT '원본 이미지 URL',
    prompt TEXT COMMENT '프롬프트 텍스트',
    video_url VARCHAR(500) COMMENT '비디오 URL',
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP COMMENT '생성 시간',
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '수정 시간'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='비디오 정보 테이블';

-- 인덱스 생성 (선택사항 - 성능 향상)
CREATE INDEX idx_user_id ON videos(user_id);
CREATE INDEX idx_engine ON videos(engine);

-- 테스트 데이터 삽입 (선택사항)
INSERT INTO videos (user_id, engine, source_image, prompt, video_url) VALUES
('user001', 'KlingAI', 'https://example.com/images/sample1.jpg', 'A beautiful sunset over the ocean', 'https://example.com/videos/video1.mp4'),
('user002', 'deAPI', 'https://example.com/images/sample2.jpg', 'A cat playing with a ball', 'https://example.com/videos/video2.mp4'),
('user001', 'KlingAI', 'https://example.com/images/sample3.jpg', 'Mountains in the morning', 'https://example.com/videos/video3.mp4'),
('user003', 'deAPI', 'https://example.com/images/sample4.jpg', 'City lights at night', 'https://example.com/videos/video4.mp4'),
('user002', 'KlingAI', 'https://example.com/images/sample5.jpg', 'Flowers blooming in spring', 'https://example.com/videos/video5.mp4');

-- 데이터 확인
SELECT id, user_id, engine, source_image, prompt, video_url FROM videos;

-- 테이블 정보 확인
DESCRIBE videos;

-- 레코드 수 확인
SELECT COUNT(*) as total_videos FROM videos;
