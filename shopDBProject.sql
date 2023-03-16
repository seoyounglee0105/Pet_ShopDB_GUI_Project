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
    -- 적립금 추가할까
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
    name VARCHAR(50) NOT NULL UNIQUE, -- 상품명
    price int NOT NULL, -- 가격
    category_id int NOT NULL, -- 상품 분류 (외래키)
    main_photo VARCHAR(50) NOT NULL, -- 사진1 (필수)
    sub_photo VARCHAR(50), -- 사진2
    sales int DEFAULT 0, -- 판매량 : 주문 기능 구현할 때 +수량 되도록
	insert_date DATE, -- 등록일자 : CURDATE()로 자동 생성
    FOREIGN KEY (category_id) REFERENCES category(id)
);

DROP TABLE product;
DROP TABLE category;

-- 상품 분류 테이블
CREATE TABLE category (
	id int PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(15) NOT NULL UNIQUE
);

SELECT * FROM product;

-- 상품 종류 테이블은 미리 데이터 삽입
INSERT INTO category (name)
VALUES 
	('Clothes'),
    ('Food'),
    ('Living'),
    ('Toy'),
    ('etc.');

DESC product;
-- 테스트 데이터
INSERT INTO product (name, price, category_id, main_photo, insert_date)
VALUES
	('체크 스카프', 3000, 1, "images/1.jpg", "2023-03-01"),
    ('스트라이프 폴라티', 13000, 1, "images/2.jpg", "2023-03-01"),
	('치카 칫솔', 2000, 5, "images/3.jpg", "2023-03-01"),
    ('산타 망토', 9000, 1, "images/4.jpg", "2023-03-02"),
    ('우유맛 껌', 1000, 2, "images/5.jpg", "2023-03-02"),
	('강아지 유모차', 119000, 5, "images/6.jpg", "2023-03-02"),
    ('뽀글이 토끼 머리띠', 4000, 1, "images/7.jpg", "2023-03-03"),
	('노즈워크 당근밭 장난감', 4000, 4, "images/8.jpg", "2023-03-03"),
    ('당근 후드티', 15000, 1, "images/9.jpg", "2023-03-03"),
    ('맥도날드 모자', 4000, 1, "images/10.jpg", "2023-03-04"),
    ('왕방울 후드티', 15000, 1, "images/11.jpg", "2023-03-04"),
    ('얼룩무늬 반자동 목줄', 8000, 5, "images/12.jpg", "2023-03-04"),
    ('허그미 강아지 방석', 19000, 3, "images/13.jpg", "2023-03-05"),
    ('수제 육포', 2000, 2, "images/14.jpg", "2023-03-05"),
    ('네모 식기', 4000, 3, "images/15.jpg", "2023-03-05"),
    ('뽀송 목도리', 4000, 1, "images/16.jpg", "2023-03-06"),
    ('짱구 잠옷 (겨울용)', 12000, 1, "images/17.jpg", "2023-03-06"),
    ('짱구 잠옷 (여름용)', 10000, 1, "images/18.jpg", "2023-03-06"),
    ('삑삑이 가재 장난감', 2000, 4, "images/19.jpg", "2023-03-07"),
    ('논슬립 슬라이드 계단', 34900, 3, "images/20.jpg", "2023-03-07"),
    ('호피무늬 커플 후드 티', 32000, 1, "images/21.jpg", "2023-03-07"),
    ('알로하 꽃 목걸이', 2500, 1, "images/22.jpg", "2023-03-08"),
    ('잘 밀리는 이발기', 29000, 5, "images/23.jpg", "2023-03-08"),
    ('개구리 후드티', 15000, 1, "images/24.jpg", "2023-03-08"),
    ('피카츄 목욕 가운', 13000, 1, "images/25.jpg", "2023-03-09"),
    ('강아지 안전문 펫도어', 29900, 3, "images/26.jpg", "2023-03-09"),
	('관절건강 강아지 츄르', 4500, 2, "images/27.jpg", "2023-03-09"),
	('미니 라이언 인형', 3000, 4, "images/28.jpg", "2023-03-10"),
    ('파인애플 인형', 3000, 4, "images/29.jpg", "2023-03-10"),
    ('고양이 스크래쳐', 19000, 4, "images/30.jpg", "2023-03-10"),
    ('일회용 신발 30개입', 10000, 5, "images/31.jpg", "2023-03-11"),
    ('강아지 뽀송 한복', 16000, 1, "images/32.jpg", "2023-03-11"),
    ('포근한 넥카라', 8000, 5, "images/33.jpg", "2023-03-11"),
    ('블랙 하네스', 9900, 5, "images/34.jpg", "2023-03-12"),
    ('고양이 마약 츄르', 4500, 2, "images/35.jpg", "2023-03-12"),
    ('하네스 일체형 패딩', 40000, 1, "images/36.jpg", "2023-03-12"),
    ('뽀글이 양털 후드망토', 14000, 1, "images/37.jpg", "2023-03-13"),
    ('걷기 싫어 강아지 가방', 21000, 5, "images/38.jpg", "2023-03-13"),
    ('강아지 도령 한복', 19000, 1, "images/39.jpg", "2023-03-13");

SELECT * FROM product;
    
    
    
    
    
    
    
    
    
    
    
    
    
    


DELETE FROM product;
DROP TABLE product;



-- 장바구니 테이블
-- 사용자 id(외래키), 상품 id(외래키)


-- 찜목록 테이블


-- 주문 테이블
-- id, 상품 id(외래키), 주문자 id(외래키), 수량, 주문일자, 배송완료여부(bool)

-- 리뷰 테이블 (작성자id + 상품id가 주문 테이블에 존재하는지 확인하고 리뷰 작성 가능하게
-- 상품 id, 작성자 id, 별점(int : 1~5), 제목, 본문, 사진(NULL)
-- 별점은 JAVA에서 해당 상품에 해당하는 리뷰에서 별점 가져와서 평균값

