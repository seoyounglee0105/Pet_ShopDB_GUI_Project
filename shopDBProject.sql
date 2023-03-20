CREATE DATABASE my_shopdb;
USE my_shopdb;

SELECT * FROM member;
SELECT * FROM `order`;

DELETE FROM `order` WHERE id = 12;

SELECT * FROM review WHERE product_id = 1 ORDER BY id DESC LIMIT 7;

-- 회원 등급 테이블
CREATE TABLE grade (


	name VARCHAR(10) PRIMARY KEY
);

-- 회원 등급은 미리 데이터 생성해둠
INSERT INTO grade
VALUES
    ('Gold'),
    ('Silver'),
    ('Bronze');

-- 회원 테이블
CREATE TABLE member (
    id VARCHAR(15) PRIMARY KEY,  -- 아이디
    password VARCHAR(20) NOT NULL,  -- 비밀번호
    member_grade VARCHAR(10) NOT NULL DEFAULT 'Bronze',  -- 회원 등급 (외래키)
    name VARCHAR(30) NOT NULL,  -- 이름
    phone_number VARCHAR(13) NOT NULL UNIQUE,  -- 전화번호
    address VARCHAR(100) NOT NULL,  -- 주소
    point INT DEFAULT 0,
    FOREIGN KEY (member_grade) REFERENCES grade(name)
);

-- 상품 분류 테이블
CREATE TABLE category (
	id int PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(15) NOT NULL UNIQUE
);

-- 상품 종류 테이블은 미리 데이터 삽입
INSERT INTO category (name)
VALUES 
	('Clothes'),
    ('Food'),
    ('Living'),
    ('Toy'),
    ('etc.');

-- 상품 테이블
CREATE TABLE product (
	id int PRIMARY KEY AUTO_INCREMENT, 
    name VARCHAR(50) NOT NULL UNIQUE, -- 상품명
    price int NOT NULL, -- 가격
    category_id int NOT NULL, -- 상품 분류 (외래키)
    main_photo VARCHAR(50) NOT NULL UNIQUE, -- 사진1 (필수)
    sales int DEFAULT 0, -- 판매량 : 주문 기능 구현할 때 +수량 되도록
	insert_date DATE, -- 등록일자 : CURDATE()로 자동 생성
    FOREIGN KEY (category_id) REFERENCES category(id)
);

INSERT INTO product (name, price, category_id, main_photo, insert_date)
VALUES
	('체크 스카프', 3000, 1, "images/1.jpg", "2023-02-01"),
    ('스트라이프 폴라티', 13000, 1, "images/2.jpg", "2023-02-01"),
	('치카 칫솔', 2000, 5, "images/3.jpg", "2023-02-01"),
    ('산타 망토', 9000, 1, "images/4.jpg", "2023-02-02"),
    ('우유맛 껌', 1000, 2, "images/5.jpg", "2023-02-02"),
	('강아지 유모차', 119000, 5, "images/6.jpg", "2023-02-02"),
    ('뽀글이 토끼 머리띠', 4000, 1, "images/7.jpg", "2023-02-03"),
	('노즈워크 당근밭 장난감', 22000, 4, "images/8.jpg", "2023-02-03"),
    ('당근 후드티', 15000, 1, "images/9.jpg", "2023-02-03"),
    ('맥도날드 모자', 4000, 1, "images/10.jpg", "2023-02-04"),
    ('왕방울 후드티', 15000, 1, "images/11.jpg", "2023-02-04"),
    ('얼룩무늬 반자동 목줄', 8000, 5, "images/12.jpg", "2023-02-04"),
    ('허그미 강아지 방석', 19000, 3, "images/13.jpg", "2023-02-05"),
    ('수제 육포', 2000, 2, "images/14.jpg", "2023-02-05"),
    ('네모 식기', 4000, 3, "images/15.jpg", "2023-02-05"),
    ('뽀송 목도리', 4000, 1, "images/16.jpg", "2023-02-06"),
    ('짱구 잠옷 (겨울용)', 12000, 1, "images/17.jpg", "2023-02-06"),
    ('짱구 잠옷 (여름용)', 10000, 1, "images/18.jpg", "2023-02-06"),
    ('삑삑이 가재 장난감', 2000, 4, "images/19.jpg", "2023-02-07"),
    ('논슬립 슬라이드 계단', 34900, 3, "images/20.jpg", "2023-02-07"),
    ('호피무늬 커플 후드티', 32000, 1, "images/21.jpg", "2023-02-07"),
    ('알로하 꽃 목걸이', 2500, 1, "images/22.jpg", "2023-02-08"),
    ('잘 밀리는 이발기', 29000, 5, "images/23.jpg", "2023-02-08"),
    ('개구리 후드티', 15000, 1, "images/24.jpg", "2023-02-08"),
    ('피카츄 목욕 가운', 13000, 1, "images/25.jpg", "2023-02-09"),
    ('안전문 펫도어', 29900, 3, "images/26.jpg", "2023-02-09"),
	('강아지 관절건강 츄르', 4500, 2, "images/27.jpg", "2023-02-09"),
	('미니 라이언 인형', 3000, 4, "images/28.jpg", "2023-02-10"),
    ('파인애플 인형', 3000, 4, "images/29.jpg", "2023-02-10"),
    ('고양이 스크래쳐', 19000, 4, "images/30.jpg", "2023-02-10"),
    ('일회용 신발 30개입', 10000, 5, "images/31.jpg", "2023-02-11"),
    ('뽀송따끈 한복', 16000, 1, "images/32.jpg", "2023-02-11"),
    ('포근한 넥카라', 8000, 5, "images/33.jpg", "2023-02-11"),
    ('블랙 하네스', 9900, 5, "images/34.jpg", "2023-02-12"),
    ('고양이 마약 츄르', 4500, 2, "images/35.jpg", "2023-02-12"),
    ('하네스 일체형 패딩', 40000, 1, "images/36.jpg", "2023-02-12"),
    ('뽀글이 양털 후드망토', 14000, 1, "images/37.jpg", "2023-02-13"),
    ('걷기 싫어 강아지 가방', 21000, 5, "images/38.jpg", "2023-02-13"),
    ('도령 한복', 19000, 1, "images/39.jpg", "2023-02-13"),
	('앵두 달랑 목걸이', 2500, 1, "images/40.jpg", "2023-02-14"),
    ('악어 스카프', 3000, 1, "images/41.jpg", "2023-02-14"),
    ('푹신 강아지 방석', 18000, 3, "images/42.jpg", "2023-02-14"),
    ('삑삑이 탱탱볼', 2000, 4, "images/43.jpg", "2023-02-15"),
    ('지방이 인형', 3000, 4, "images/44.jpg", "2023-02-15"),
    ('크리스마스 목도리', 4000, 1, "images/45.jpg", "2023-02-15"),
    ('체크 벙거지 모자', 3500, 1, "images/46.jpg", "2023-02-16"),
    ('소두 토끼 모자', 3000, 1, "images/47.jpg", "2023-02-16"),
    ('루돌프 후드티', 11000, 1, "images/48.jpg", "2023-02-16"),
    ('초코비 스카프', 3000, 1, "images/49.jpg", "2023-02-16"),
    ('봄 꽃 나시', 9000, 1, "images/50.jpg", "2023-02-17"),
    ('양털 토끼 후드티', 15000, 1, "images/51.jpg", "2023-02-17"),
    ('고양이향 상자', 100, 5, "images/52.jpg", "2023-02-17"),
    ('캣 타워', 59000, 3, "images/53.jpg", "2023-02-17"),
    ('반려동물 이름표', 8000, 5, "images/54.jpg", "2023-02-18"),
    ('미니 강아지 방석', 9000, 3, "images/55.jpg", "2023-02-18"),
    ('뱃살 한바가지 방석', 20000, 3, "images/56.jpg", "2023-02-18"),
    ('터그놀이 장난감', 3000, 4, "images/57.jpg", "2023-02-18");

-- 장바구니 테이블
CREATE TABLE cart (
	member_id VARCHAR(15) NOT NULL, -- 회원 아이디 (외래키)
	product_id INT NOT NULL, -- 상품 아이디 (외래키)
    amount INT NOT NULL DEFAULT 1, -- 수량
    PRIMARY KEY (member_id, product_id),
    FOREIGN KEY (member_id) REFERENCES member(id),
    FOREIGN KEY (product_id) REFERENCES product(id)
);

CREATE TABLE `order` (
	id INT PRIMARY KEY AUTO_INCREMENT,
    member_id VARCHAR(15) NOT NULL, 
    product_id INT NOT NULL,
    amount INT NOT NULL DEFAULT 1,
    order_date DATE NOT NULL,
    state INT DEFAULT 0 -- 배송 여부
);

CREATE TABLE review (
	id INT PRIMARY KEY AUTO_INCREMENT,
    writer_id VARCHAR(15) NOT NULL, -- 작성자 이름
    product_id INT NOT NULL,
    star int NOT NULL,
    title VARCHAR(50) NOT NULL,
    content TEXT NOT NULL,
    photo VARCHAR(50),
    FOREIGN KEY (writer_id) REFERENCES member(id),
    FOREIGN KEY (product_id) REFERENCES product(id)
);


