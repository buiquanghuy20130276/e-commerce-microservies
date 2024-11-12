-- V2__update_tables_to_bigint.sql

-- 1. Thay đổi kiểu dữ liệu của cột id trong bảng category và product
ALTER TABLE category
    ALTER COLUMN id SET DATA TYPE BIGINT;

ALTER TABLE product
    ALTER COLUMN id SET DATA TYPE BIGINT;

-- 2. Thay đổi kiểu dữ liệu của cột category_id trong bảng product (nếu cần)
ALTER TABLE product
    ALTER COLUMN category_id SET DATA TYPE BIGINT;

-- 3. Tạo lại sequence với kiểu BIGINT và bắt đầu từ giá trị thích hợp
CREATE SEQUENCE IF NOT EXISTS category_seq
    AS BIGINT
    START WITH 1
    INCREMENT BY 50;

CREATE SEQUENCE IF NOT EXISTS product_seq
    AS BIGINT
    START WITH 1
    INCREMENT BY 50;

-- 4. Đảm bảo rằng bảng sử dụng sequence mới
ALTER SEQUENCE category_seq OWNED BY category.id;
ALTER SEQUENCE product_seq OWNED BY product.id;
