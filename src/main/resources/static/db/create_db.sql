-- Tạo cơ sở dữ liệu
# drop database  smart_cafe;
# CREATE DATABASE smart_cafe;
USE smart_cafe;


-- Bảng lưu thông tin các vai trò
CREATE TABLE Roles
(
    role_id     INT PRIMARY KEY AUTO_INCREMENT,
    role_name   VARCHAR(50) NOT NULL UNIQUE,
    description TEXT
);

-- Bảng lưu thông tin các vị trí
CREATE TABLE Positions
(
    position_id   INT PRIMARY KEY AUTO_INCREMENT,
    position_name VARCHAR(100) NOT NULL UNIQUE,
    description   TEXT
);

-- Bảng lưu thông tin nhân viên
CREATE TABLE Employees
(
    employee_id INT PRIMARY KEY AUTO_INCREMENT,
    position_id INT,
    full_name   VARCHAR(100),
    email       VARCHAR(100)   NOT NULL UNIQUE,
    address     VARCHAR(255),
    tel         VARCHAR(20),
    birthday    DATE,
    gender      ENUM ('male', 'female', 'unknown'),
    salary      DECIMAL(10, 2) NOT NULL,
    note        TEXT,
    image_url   VARCHAR(255),
    FOREIGN KEY (position_id) REFERENCES Positions (position_id)
);

-- Bảng lưu thông tin người dùng
CREATE TABLE Users
(
    user_id              INT PRIMARY KEY AUTO_INCREMENT,
    username             VARCHAR(50)  NOT NULL UNIQUE,
    password             VARCHAR(255) NOT NULL,
    employee_id          INT,
    is_verified          BOOLEAN DEFAULT FALSE,
    verification_token   VARCHAR(255),
    password_expiry_date DATE,
    FOREIGN KEY (employee_id) REFERENCES Employees (employee_id)
);

-- Bảng lưu thông tin mã thông báo khôi phục mật khẩu
CREATE TABLE Password_Reset_Tokens
(
    token_id   INT PRIMARY KEY AUTO_INCREMENT,
    user_id    INT          NOT NULL,
    token      VARCHAR(255) NOT NULL UNIQUE,
    created_at DATETIME DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (user_id) REFERENCES Users (user_id)
);

-- Bảng lưu thông tin mã thông báo của người dùng
CREATE TABLE User_Tokens
(
    token_id   INT PRIMARY KEY AUTO_INCREMENT,
    user_id    INT          NOT NULL,
    token      VARCHAR(255) NOT NULL UNIQUE,
    created_at DATETIME DEFAULT CURRENT_TIMESTAMP,
    expires_at DATETIME,
    FOREIGN KEY (user_id) REFERENCES Users (user_id)
);

-- Bảng lưu liên kết giữa người dùng và vai trò
CREATE TABLE User_Roles
(
    user_role_id INT PRIMARY KEY AUTO_INCREMENT,
    user_id      INT NOT NULL,
    role_id      INT NOT NULL,
    FOREIGN KEY (user_id) REFERENCES Users (user_id) ON DELETE CASCADE,
    FOREIGN KEY (role_id) REFERENCES Roles (role_id) ON DELETE CASCADE
);

-- Bảng lưu thông tin bàn
CREATE TABLE Tables
(
    table_id  INT PRIMARY KEY AUTO_INCREMENT,
    code      VARCHAR(50) NOT NULL UNIQUE,
    is_on     BOOLEAN     NOT NULL DEFAULT TRUE,
    state     VARCHAR(50)          DEFAULT 'good',
    is_delete BOOLEAN     NOT NULL DEFAULT FALSE
);

-- Bảng lưu thông tin nhóm món
CREATE TABLE Service_Types
(
    type_id     INT PRIMARY KEY AUTO_INCREMENT,
    type_name   VARCHAR(100) NOT NULL UNIQUE,
    code        VARCHAR(50)  NOT NULL UNIQUE,
    description TEXT
);

-- Bảng lưu thông tin món ăn
CREATE TABLE Services
(
    service_id   INT PRIMARY KEY AUTO_INCREMENT,
    service_code VARCHAR(50) NOT NULL UNIQUE,
    service_name VARCHAR(100),
    type_id      INT,
    price        DECIMAL(10, 2),
    description  TEXT,
    image_url    VARCHAR(255),
    wait_time    TIME,
    status       ENUM ('available', 'unavailable', 'out_of_stock'),
    is_delete    BOOLEAN     NOT NULL DEFAULT FALSE,
    FOREIGN KEY (type_id) REFERENCES Service_Types (type_id)
);

-- Bảng lưu thông tin hóa đơn
CREATE TABLE Bills
(
    bill_id      INT PRIMARY KEY AUTO_INCREMENT,
    table_id     INT,
    creator_id   INT,
    code         VARCHAR(50) NOT NULL UNIQUE,
    date_created DATETIME,
    status       ENUM ('pending', 'completed', 'canceled', 'on_processing'),
    FOREIGN KEY (table_id) REFERENCES Tables (table_id),
    FOREIGN KEY (creator_id) REFERENCES Users (user_id)
);


-- Bảng lưu chi tiết từng hóa đơn
CREATE TABLE Bill_Details
(
    bill_detail_id INT PRIMARY KEY AUTO_INCREMENT,
    bill_id        INT,
    service_id     INT,
    quantity       INT,
    FOREIGN KEY (bill_id) REFERENCES Bills (bill_id),
    FOREIGN KEY (service_id) REFERENCES Services (service_id)
);

-- Bảng lưu thông tin phản hồi của khách hàng
CREATE TABLE Feedbacks
(
    feedback_id   INT PRIMARY KEY AUTO_INCREMENT,
    creator_id    INT,
    code          VARCHAR(50) NOT NULL UNIQUE,
    email         VARCHAR(100),
    feedback_date DATE,
    content       TEXT,
    FOREIGN KEY (creator_id) REFERENCES Users (user_id)
);

-- Bảng lưu thông tin tin tức
CREATE TABLE News
(
    news_id      INT PRIMARY KEY AUTO_INCREMENT,
    title        VARCHAR(200) NOT NULL,
    content      TEXT         NOT NULL,
    publish_date DATE         NOT NULL,
    image_url    VARCHAR(255),
    creator_id   INT,
    status       ENUM ('published', 'draft', 'archived') DEFAULT 'draft',
    FOREIGN KEY (creator_id) REFERENCES Users (user_id)
);
