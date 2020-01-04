# Create Databases
CREATE DATABASE maruszka_dev;
CREATE DATABASE maruszka_prod;

# Create database service accounts
CREATE USER 'maruszka_dev_user'@'localhost' IDENTIFIED BY 'maruszka';
CREATE USER 'maruszka_prod_user'@'localhost' IDENTIFIED BY 'maruszka';
# Docker configuration
CREATE USER 'maruszka_dev_user'@'%' IDENTIFIED BY 'root';
CREATE USER 'maruszka_prod_user'@'%' IDENTIFIED BY 'root';

# Database grants
GRANT SELECT ON maruszka_dev.* to 'maruszka_dev_user'@'localhost';
GRANT INSERT ON maruszka_dev.* to 'maruszka_dev_user'@'localhost';
GRANT DELETE ON maruszka_dev.* to 'maruszka_dev_user'@'localhost';
GRANT UPDATE ON maruszka_dev.* to 'maruszka_dev_user'@'localhost';
GRANT SELECT ON maruszka_prod.* to 'maruszka_prod_user'@'localhost';
GRANT INSERT ON maruszka_prod.* to 'maruszka_prod_user'@'localhost';
GRANT DELETE ON maruszka_prod.* to 'maruszka_prod_user'@'localhost';
GRANT UPDATE ON maruszka_prod.* to 'maruszka_prod_user'@'localhost';
## Docker configuration
GRANT SELECT ON maruszka_dev.* to 'maruszka_dev_user'@'%';
GRANT INSERT ON maruszka_dev.* to 'maruszka_dev_user'@'%';
GRANT DELETE ON maruszka_dev.* to 'maruszka_dev_user'@'%';
GRANT UPDATE ON maruszka_dev.* to 'maruszka_dev_user'@'%';
GRANT SELECT ON maruszka_prod.* to 'maruszka_prod_user'@'%';
GRANT INSERT ON maruszka_prod.* to 'maruszka_prod_user'@'%';
GRANT DELETE ON maruszka_prod.* to 'maruszka_prod_user'@'%';
GRANT UPDATE ON maruszka_prod.* to 'maruszka_prod_user'@'%';