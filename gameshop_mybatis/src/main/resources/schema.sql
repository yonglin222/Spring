-- 권한 테이블
CREATE TABLE authority (
    authority_name VARCHAR(50) PRIMARY KEY
);

-- 유저 테이블
CREATE TABLE user (
    user_name VARCHAR(255) PRIMARY KEY,
    password VARCHAR(255) NOT NULL,
    email VARCHAR(255) NOT NULL UNIQUE,
    real_name VARCHAR(255) NOT NULL,
    user_authority VARCHAR(50),
    created_at DATETIME,
    FOREIGN KEY (user_authority) REFERENCES authority(authority_name)
);

-- 게임 테이블
CREATE TABLE games (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    title VARCHAR(255) UNIQUE,
    genre VARCHAR(255),
    price INT,
    image_url VARCHAR(255),
    text VARCHAR(3000)
);

-- 게시판 테이블
CREATE TABLE board (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    title VARCHAR(255) NOT NULL,
    content TEXT NOT NULL,
    user_name VARCHAR(255) NOT NULL,
    created_date DATETIME NOT NULL,
    modified_date DATETIME NOT NULL,
    is_active BOOLEAN,
    FOREIGN KEY (user_name) REFERENCES user(user_name)
);

-- 구매 정보 테이블
CREATE TABLE purchase (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    game_id BIGINT,
    user_name VARCHAR(255),
    purchase_time DATETIME,
    FOREIGN KEY (game_id) REFERENCES games(id),
    FOREIGN KEY (user_name) REFERENCES user(user_name)
);

-- 리뷰 테이블
CREATE TABLE review (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    game_id BIGINT,
    user_name VARCHAR(255),
    point VARCHAR(255) NOT NULL,
    review_text TEXT,
    created_at DATETIME,
    FOREIGN KEY (game_id) REFERENCES games(id),
    FOREIGN KEY (user_name) REFERENCES user(user_name)
);