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

-- 상품 테이블
-- id, 상품이름, 상품종류(외래키), 가격, 메인사진(NOT NULL), 서브사진(NULL), 판매량(기본값 0), 등록일자
CREATE TABLE product (
	id int PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(15) NOT NULL UNIQUE,
    price int NOT NULL,
    category_id int NOT NULL, 
    main_photo VARCHAR(50) NOT NULL,
    sub_photo VARCHAR(50),
    sales int DEFAULT 0,
	insert_date DATE,
    FOREIGN KEY (category_id) REFERENCES category(id)
);

DROP TABLE product;
DROP TABLE category;

-- 상품 분류 테이블
CREATE TABLE category (
	id int PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(15) NOT NULL UNIQUE
);

-- 상품 종류 테이블은 미리 데이터 삽입
INSERT INTO category (name)
VALUES 
	('의류'),
    ('음식'),
    ('실내'),
    ('놀이'),
	('미용'),
    ('기타');

DESC product;
-- 테스트 데이터
INSERT INTO product (name, price, category_id, main_photo, insert_date)
VALUES
	('발바닥', 2000, 6, "images/paws.png", CURDATE());

-- 장바구니 테이블
-- 사용자 id(외래키), 상품 id(외래키)


-- 찜목록 테이블


-- 주문 테이블
-- id, 상품 id(외래키), 주문자 id(외래키), 수량, 주문일자, 배송완료여부(bool)

-- 리뷰 테이블 (작성자id + 상품id가 주문 테이블에 존재하는지 확인하고 리뷰 작성 가능하게
-- 상품 id, 작성자 id, 별점(int : 1~5), 제목, 본문, 사진(NULL)
-- 별점은 JAVA에서 해당 상품에 해당하는 리뷰에서 별점 가져와서 평균값

