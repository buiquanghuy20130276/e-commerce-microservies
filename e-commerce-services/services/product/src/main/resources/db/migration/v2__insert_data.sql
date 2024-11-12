INSERT INTO category (id, description, name)
VALUES
    (nextval('category_seq'), 'Electronics and gadgets', 'Electronics'),
    (nextval('category_seq'), 'Books and literature', 'Books'),
    (nextval('category_seq'), 'Clothing and fashion items', 'Clothing'),
    (nextval('category_seq'), 'Sports and outdoor equipment', 'Sports'),
    (nextval('category_seq'), 'Home and kitchen appliances', 'Home');
INSERT INTO product (id, description, name, available_quantity, price, category_id)
VALUES
    (nextval('product_seq'), 'Smartphone with 64GB storage', 'Smartphone', 100, 299.9, 1),
    (nextval('product_seq'), 'Laptop with 8GB RAM', 'Laptop', 50, 799.9, 1),
    (nextval('product_seq'), 'Bluetooth headphones', 'Headphones', 200, 49.9, 1),
    (nextval('product_seq'), 'E-book on programming', 'Programming Book', 300, 19.9, 2),
    (nextval('product_seq'), 'Mystery novel', 'Novel', 150, 12.9, 2),
    (nextval('product_seq'), 'Cotton T-shirt', 'T-shirt', 500, 9.9, 3),
    (nextval('product_seq'), 'Jeans for men', 'Jeans', 400, 29.9, 3),
    (nextval('product_seq'), 'Running shoes', 'Running Shoes', 250, 59.9, 4),
    (nextval('product_seq'), 'Basketball', 'Basketball', 300, 14.9, 4),
    (nextval('product_seq'), 'Tennis racket', 'Tennis Racket', 150, 79.9, 4),
    (nextval('product_seq'), 'Blender for smoothies', 'Blender', 100, 39.9, 5),
    (nextval('product_seq'), 'Microwave oven', 'Microwave', 80, 99.9, 5),
    (nextval('product_seq'), 'Vacuum cleaner', 'Vacuum Cleaner', 60, 149.9, 5),
    (nextval('product_seq'), 'Coffee maker', 'Coffee Maker', 70, 49.9, 5),
    (nextval('product_seq'), 'Digital camera', 'Camera', 40, 299.9, 1),
    (nextval('product_seq'), 'Tablet with 10-inch display', 'Tablet', 120, 199.9, 1),
    (nextval('product_seq'), 'Fiction book series', 'Book Series', 100, 25.9, 2),
    (nextval('product_seq'), 'Woolen sweater', 'Sweater', 200, 19.9, 3),
    (nextval('product_seq'), 'Soccer ball', 'Soccer Ball', 400, 15.9, 4),
    (nextval('product_seq'), 'Air fryer', 'Air Fryer', 90, 79.9, 5);
