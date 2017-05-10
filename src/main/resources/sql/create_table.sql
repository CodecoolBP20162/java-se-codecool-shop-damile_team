DROP TABLE IF EXISTS productCategories, suppliers, products, orders;

CREATE TABLE productCategories
(
  productCategoryId INT NOT NULL PRIMARY KEY,
  name VARCHAR(40),
  department VARCHAR(40),
  description VARCHAR(200)
);

CREATE TABLE suppliers
(
  supplierId INT NOT NULL PRIMARY KEY,
  name VARCHAR(40),
  description VARCHAR(200)
);

CREATE TABLE products
(
  productId INT NOT NULL PRIMARY KEY,
  name VARCHAR(40),
  description VARCHAR(200),
  defaultPrice INT,
  currencyString VARCHAR(5),
  supplierId  INT REFERENCES suppliers(supplierId),
  productCategoryId INT REFERENCES productCategories(productCategoryId)
);

CREATE TABLE orders
(
  orderId INT NOT NULL PRIMARY KEY,
  orderedProductId INT REFERENCES products(productId),
  quantity INT,
  defaultPrice INT,
  currencyString VARCHAR(5)
);

INSERT INTO productCategories(productCategoryId, name, department, description) VALUES (1, 'Tablet', 'Hardware', 'A tablet computer, commonly shortened to tablet, is a thin, flat mobile computer with a touchscreen display.');
INSERT INTO productCategories(productCategoryId, name, department, description) VALUES (2, 'Mobile', 'Hardware', 'A mobile phone is a portable telephone that can make and receive calls over a radio frequency link while the user is moving within a telephone service area.');
INSERT INTO productCategories(productCategoryId, name, department, description) VALUES (3, 'TV', 'Hardware', 'A flat panel LCD TV set that uses LEDs (light emitting diodes) for its backlight source rather than the earlier cold cathode fluorescent lamps.');

INSERT INTO suppliers(supplierId, name, description) VALUES (1, 'Amazon', 'Digital content and services');
INSERT INTO suppliers(supplierId, name, description) VALUES (2, 'Lenovo', 'Computers');
INSERT INTO suppliers(supplierId, name, description) VALUES (3, 'Apple', 'Mobile phones');
INSERT INTO suppliers(supplierId, name, description) VALUES (4, 'Samsung', 'Computers and mobile phones');
INSERT INTO suppliers(supplierId, name, description) VALUES (5, 'Others', 'Digital content and services');

INSERT INTO products(productId, name, defaultPrice, currencyString, description, productCategoryId, supplierId) VALUES (1, 'Amazon Fire', 49, 'USD', 'Fantastic price. Large content ecosystem. Good parental controls. Helpful technical support.', 1, 1);
INSERT INTO products(productId, name, defaultPrice, currencyString, description, productCategoryId, supplierId) VALUES (2, 'Lenovo IdeaPad Miix 700', 479, 'USD', 'Keyboard cover is included. Fanless Core m5 processor. Full-size USB ports. Adjustable kickstand.', 1, 2);
INSERT INTO products(productId, name, defaultPrice, currencyString, description, productCategoryId, supplierId) VALUES (3, 'Amazon Fire HD 8', 89, 'USD', 'Amazon''s latest Fire HD 8 tablet is a great value for media consumption.', 1, 1);
INSERT INTO products(productId, name, defaultPrice, currencyString, description, productCategoryId, supplierId) VALUES (4, 'Iphone 7', 649, 'USD', 'Keyboard cover is included. Fanless Core m5 processor. Full-size USB ports. Adjustable kickstand.', 2, 3);
INSERT INTO products(productId, name, defaultPrice, currencyString, description, productCategoryId, supplierId) VALUES (5, 'HTC Desire', 90, 'USD', 'Our HTC Desire is a great value for media consumption.', 2, 5);
INSERT INTO products(productId, name, defaultPrice, currencyString, description, productCategoryId, supplierId) VALUES (6, 'Samsung Galaxy Note 7', 969, 'USD', '"Keyboard cover is included. Fanless Core m5 processor. Full-size USB ports. Adjustable kickstand.', 2, 4);
INSERT INTO products(productId, name, defaultPrice, currencyString, description, productCategoryId, supplierId) VALUES (7, 'Samsung UN28H4000', 464, 'USD', 'Fantastic price. Large content ecosystem. Good parental controls. Helpful technical support.', 3, 4);
INSERT INTO products(productId, name, defaultPrice, currencyString, description, productCategoryId, supplierId) VALUES (8, 'Vizio D24-D1 24 Smart LCD TV', 849, 'USD', 'Keyboard cover is included. Fanless Core m5 processor. Full-size USB ports.', 1, 5);
INSERT INTO products(productId, name, defaultPrice, currencyString, description, productCategoryId, supplierId) VALUES (9, 'Element ELEFW328R', 128, 'USD', 'Hey handsome, you are beautiful, buy me, I know you want me.', 1, 5);



