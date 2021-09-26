-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema workout
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema workout
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `workout` DEFAULT CHARACTER SET utf8 COLLATE utf8_bin ;
USE `workout` ;

-- -----------------------------------------------------
-- Table `workout`.`user`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `workout`.`user` ;

CREATE TABLE IF NOT EXISTS `workout`.`user` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `username` VARCHAR(45) NOT NULL,
  `email` VARCHAR(60) NOT NULL,
  `password` VARCHAR(60) NOT NULL,
  `date_created` DATETIME NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `email_UNIQUE` (`email` ASC) VISIBLE);


-- -----------------------------------------------------
-- Table `workout`.`excercise`
-- -----------------------------------------------------

DROP TABLE IF EXISTS `workout`.`exercise`;

CREATE TABLE IF NOT EXISTS `workout`.`exercise` (
  `exercise_id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) NOT NULL,
  `fk_user_id` INT NOT NULL,
  PRIMARY KEY (`exercise_id`),
  INDEX `fk_user_id_idx` (`FK_user_id` ASC) VISIBLE,
  CONSTRAINT `fk_exercise_to_user`
    FOREIGN KEY (`FK_user_id`)
    REFERENCES `workout`.`user` (`id`)
    ON DELETE CASCADE
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `workout`.`set`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `workout`.`set` ;

CREATE TABLE IF NOT EXISTS `workout`.`set` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `date` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `weight` INT NOT NULL,
  `reps` INT NOT NULL,
  `fk_user_id` INT NOT NULL,
  `fk_exercise_id` INT NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_user_id_idx` (`fk_user_id` ASC) VISIBLE,
  INDEX `fk_exercise_id_idx` (`fk_exercise_id` ASC) VISIBLE,
  CONSTRAINT `fk_set_to_user`
    FOREIGN KEY (`fk_user_id`)
    REFERENCES `workout`.`user` (`id`)
    ON DELETE CASCADE
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_set_id_exercise_id`
    FOREIGN KEY (`fk_exercise_id`)
    REFERENCES `workout`.`excercise` (`exercise_id`)
    ON DELETE CASCADE
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `workout`.`userRole`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `workout`.`userrole` ;

CREATE TABLE IF NOT EXISTS `workout`.`userrole` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `role` VARCHAR(20) NULL,
  `fk_user_id` INT NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_user_id_idx` (`fk_user_id` ASC) VISIBLE,
  CONSTRAINT `fk_userrole_id_user_id`
    FOREIGN KEY (`fk_user_id`)
    REFERENCES `workoutStudent`.`user` (`id`)
    ON DELETE CASCADE
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `workout`.`tutorials`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `workout`.`tutorial` ;

CREATE TABLE IF NOT EXISTS `workout`.`tutorial` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(30) NOT NULL,
  `url` VARCHAR(50) NOT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;

SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
