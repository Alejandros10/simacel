-- Operator table
CREATE TABLE IF NOT EXISTS Operator (
                                        id_operator INT AUTO_INCREMENT PRIMARY KEY,
                                        name VARCHAR(50) NOT NULL
);

-- Seller table
CREATE TABLE IF NOT EXISTS Seller (
                                      id_seller INT AUTO_INCREMENT PRIMARY KEY,
                                      name VARCHAR(100) NOT NULL,
                                      document_number VARCHAR(20) NOT NULL
);

-- Sale table
CREATE TABLE IF NOT EXISTS Sale (
                                    id_sale INT AUTO_INCREMENT PRIMARY KEY,
                                    price DECIMAL(10, 2) NOT NULL,
                                    sale_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                                    phone_number VARCHAR(15) NOT NULL,
                                    id_seller INT,
                                    id_operator INT,
                                    FOREIGN KEY (id_seller) REFERENCES Seller(id_seller),
                                    FOREIGN KEY (id_operator) REFERENCES Operator(id_operator)
);