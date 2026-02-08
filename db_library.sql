-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema mydb
-- -----------------------------------------------------
-- -----------------------------------------------------
-- Schema db_library
-- -----------------------------------------------------
DROP SCHEMA IF EXISTS `db_library` ;

-- -----------------------------------------------------
-- Schema db_library
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `db_library` DEFAULT CHARACTER SET utf8mb4 ;
USE `db_library` ;

-- -----------------------------------------------------
-- Table `db_library`.`books`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `db_library`.`books` ;

CREATE TABLE IF NOT EXISTS `db_library`.`books` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `title` VARCHAR(255) NOT NULL,
  `author` VARCHAR(255) NOT NULL DEFAULT 'Unknown',
  `category` INT NULL DEFAULT NULL,
  `number_of_pages` INT NOT NULL,
  `publication_year` YEAR NOT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4;


-- -----------------------------------------------------
-- Table `db_library`.`customer`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `db_library`.`customer` ;

CREATE TABLE IF NOT EXISTS `db_library`.`customer` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(255) NOT NULL,
  `email` VARCHAR(255) NULL DEFAULT NULL,
  `phone` VARCHAR(50) NOT NULL,
  `birthday` DATETIME NOT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4;


-- -----------------------------------------------------
-- Table `db_library`.`users`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `db_library`.`users` ;

CREATE TABLE IF NOT EXISTS `db_library`.`users` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `username` VARCHAR(100) NOT NULL,
  `email` VARCHAR(255) NOT NULL,
  `password` VARCHAR(255) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `email_UNIQUE` (`email` ASC) VISIBLE)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4;


-- -----------------------------------------------------
-- Table `db_library`.`loans`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `db_library`.`loans` ;

CREATE TABLE IF NOT EXISTS `db_library`.`loans` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `book_id` INT NOT NULL,
  `customer_id` INT NOT NULL,
  `user_id` INT NOT NULL,
  `loaned_at` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `due_at` DATETIME NULL,
  `returned_at` DATETIME NULL DEFAULT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_user_idx` (`user_id` ASC) VISIBLE,
  INDEX `fk_book_id_idx` (`book_id` ASC) VISIBLE,
  INDEX `fk_customer_id_idx` (`customer_id` ASC) VISIBLE,
  CONSTRAINT `fk_book_id`
    FOREIGN KEY (`book_id`)
    REFERENCES `db_library`.`books` (`id`)
    ON DELETE RESTRICT,
  CONSTRAINT `fk_customer_id`
    FOREIGN KEY (`customer_id`)
    REFERENCES `db_library`.`customer` (`id`)
    ON DELETE RESTRICT,
  CONSTRAINT `fk_user_id`
    FOREIGN KEY (`user_id`)
    REFERENCES `db_library`.`users` (`id`)
    ON DELETE RESTRICT)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4;

USE `db_library`;

DELIMITER $$

USE `db_library`$$
DROP TRIGGER IF EXISTS `db_library`.`loans_BEFORE_INSERT` $$
USE `db_library`$$
CREATE TRIGGER `db_library`.`loans_BEFORE_INSERT`
BEFORE INSERT ON `db_library`.`loans`
FOR EACH ROW
BEGIN
	SET NEW.due_at = COALESCE(NEW.due_at, NOW() + INTERVAL 30 DAY);
END$$


DELIMITER ;

SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
