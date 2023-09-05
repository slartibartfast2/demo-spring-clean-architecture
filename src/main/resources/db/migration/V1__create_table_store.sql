CREATE TABLE store (
                       id INT NOT NULL AUTO_INCREMENT,
                       name VARCHAR(100) NOT NULL,
                       address VARCHAR(300) NOT NULL,
                       UNIQUE(name),
                       PRIMARY KEY (id)
);

INSERT INTO store (name, address)
VALUES ('Hai Shang', '2991 Pembina Hwy, Winnipeg, Manitoba R3T 2H5, Canada');

INSERT INTO store (name, address)
VALUES ('Za Pizza Bistro', 'E-1220 St Mary s Rd, Winnipeg, Manitoba R2M 3V6, Canada');