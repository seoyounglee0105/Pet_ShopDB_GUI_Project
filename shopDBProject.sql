CREATE DATABASE my_shopdb;
USE my_shopdb;

-- 목표
-- 1. 회원가입 기능 만들기 (INSERT)
-- 2. 로그인 기능 만들기
-- 3. 회원 탈퇴 기능 만들기 (DELETE)
-- 4. 비밀번호 변경 기능 만들기 (UPDATE)

-- 그다음에 상품 카테고리 만들어서 해당하는 상품을 주르르 나오게

DROP TABLE member;
DROP TABLE grade;

-- 회원 테이블
CREATE TABLE member (
    id VARCHAR(15) PRIMARY KEY,  -- 아이디
    password VARCHAR(20) NOT NULL,  -- 비밀번호
    member_grade VARCHAR(10) NOT NULL DEFAULT 'Bronze',  -- 회원 등급 (외래키)
    name VARCHAR(30) NOT NULL,  -- 이름
    phone_number VARCHAR(13) NOT NULL UNIQUE,  -- 전화번호
    address VARCHAR(100) NOT NULL,  -- 주소
    FOREIGN KEY (member_grade) REFERENCES grade(name) 
);

-- 회원 등급 테이블
CREATE TABLE grade (
	name VARCHAR(10) PRIMARY KEY
);

DELETE FROM member;
DELETE FROM grade;

-- 회원 등급은 미리 데이터 생성해둠
INSERT INTO grade
VALUES
    ('Gold'),
    ('Silver'),
    ('Bronze');

-- 회원 가입

INSERT INTO member(id, password, name, phone_number, address)
VALUES ('abc', '1234', '홍길동', '010-1111-1111', '부산광역시');

SELECT * FROM member;



