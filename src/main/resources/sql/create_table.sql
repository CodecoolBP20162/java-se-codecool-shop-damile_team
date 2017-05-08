DROP TABLE IF EXISTS productCategories, suppliers, products, orders;

CREATE TABLE productCategories
(
  productCategoryId INT NOT NULL PRIMARY KEY,
  name VARCHAR(40),
  department VARCHAR(40),
  description VARCHAR(100)
);

CREATE TABLE suppliers
(
  supplierId INT NOT NULL PRIMARY KEY,
  name VARCHAR(40),
  description VARCHAR(100)
);

CREATE TABLE products
(
  productId INT NOT NULL PRIMARY KEY,
  name VARCHAR(40),
  description VARCHAR(10),
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

INSERT INTO productCategories(name, department, description) VALUES ('Tablet', 'Hardware', 'A tablet computer, commonly shortened to tablet, is a thin, flat mobile computer with a touchscreen display.');
INSERT INTO productCategories(name, department, description) VALUES ('Mobile', 'Hardware', 'A mobile phone is a portable telephone that can make and receive calls over a radio frequency link while the user is moving within a telephone service area.');
INSERT INTO productCategories(name, department, description) VALUES ('TV', 'Hardware', 'A flat panel LCD TV set that uses LEDs (light emitting diodes) for its backlight source rather than the earlier cold cathode fluorescent lamps.');

INSERT INTO suppliers(name, description) VALUES ('Amazon', 'Digital content and services');
INSERT INTO suppliers(name, description) VALUES ('Lenovo', 'Computers');
INSERT INTO suppliers(name, description) VALUES ('Apple', 'Mobile phones');
INSERT INTO suppliers(name, description) VALUES ('Samsung', 'Computers and mobile phones');
INSERT INTO suppliers(name, description) VALUES ('Others', 'Digital content and services');

INSERT INTO products(productId, name, description, defaultPrice, currencyString, supplierId, productCategoryId) VALUES ('Amazon Fire', 49, 'USD', 'Fantastic price. Large content ecosystem. Good parental controls. Helpful technical support.', 1, 1);
INSERT INTO products(productId, name, description, defaultPrice, currencyString, supplierId, productCategoryId) VALUES ();
INSERT INTO products(productId, name, description, defaultPrice, currencyString, supplierId, productCategoryId) VALUES ();
INSERT INTO products(productId, name, description, defaultPrice, currencyString, supplierId, productCategoryId) VALUES ();
INSERT INTO products(productId, name, description, defaultPrice, currencyString, supplierId, productCategoryId) VALUES ();
INSERT INTO products(productId, name, description, defaultPrice, currencyString, supplierId, productCategoryId) VALUES ();
INSERT INTO products(productId, name, description, defaultPrice, currencyString, supplierId, productCategoryId) VALUES ();
INSERT INTO products(productId, name, description, defaultPrice, currencyString, supplierId, productCategoryId) VALUES ();
INSERT INTO products(productId, name, description, defaultPrice, currencyString, supplierId, productCategoryId) VALUES ();



