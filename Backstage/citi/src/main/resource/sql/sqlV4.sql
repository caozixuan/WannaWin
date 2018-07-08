-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL,ALLOW_INVALID_DATES';

-- -----------------------------------------------------
-- Schema mydb
-- -----------------------------------------------------
-- -----------------------------------------------------
-- Schema huaqi
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema huaqi
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `huaqi` DEFAULT CHARACTER SET latin1 ;
USE `huaqi` ;

-- -----------------------------------------------------
-- Table `huaqi`.`merchant`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `huaqi`.`merchant` (
  `MerchantID` VARCHAR(45) NOT NULL,
  `name` VARCHAR(45) NULL DEFAULT NULL,
  `description` VARCHAR(45) NULL DEFAULT NULL,
  `address` VARCHAR(45) NULL DEFAULT NULL,
  `logoURL` VARCHAR(200) NULL DEFAULT NULL,
  PRIMARY KEY (`MerchantID`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `huaqi`.`cardtype`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `huaqi`.`cardtype` (
  `MerchantID` VARCHAR(45) NULL DEFAULT NULL,
  `Mtype` VARCHAR(45) NULL DEFAULT NULL,
  `CardType` VARCHAR(45) NOT NULL,
  `Proportion` FLOAT NULL DEFAULT NULL,
  `miniExpanse` VARCHAR(45) NULL DEFAULT NULL,
  PRIMARY KEY (`CardType`),
  INDEX `MerchantID_FK333_idx` (`MerchantID` ASC),
  CONSTRAINT `MerchantID_FK333`
    FOREIGN KEY (`MerchantID`)
    REFERENCES `huaqi`.`merchant` (`MerchantID`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `huaqi`.`user`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `huaqi`.`user` (
  `UserID` VARCHAR(45) NOT NULL,
  `Password` VARCHAR(45) NULL DEFAULT NULL,
  `CitiCard` VARCHAR(45) NULL DEFAULT NULL,
  `PhoneNum` VARCHAR(45) NOT NULL,
  `GeneralPoints` INT(11) NULL DEFAULT NULL,
  `AvailablePoints` INT(11) NULL DEFAULT NULL,
  PRIMARY KEY (`UserID`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `huaqi`.`citicard`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `huaqi`.`citicard` (
  `citiCardNum` VARCHAR(45) NOT NULL,
  `phoneNum` VARCHAR(45) NULL DEFAULT NULL,
  `idCardNum` VARCHAR(45) NULL DEFAULT NULL,
  `userID` VARCHAR(45) NULL DEFAULT NULL,
  `password` VARCHAR(45) NULL DEFAULT NULL,
  PRIMARY KEY (`citiCardNum`),
  INDEX `UserID_FK_idx` (`userID` ASC),
  CONSTRAINT `UserID_FK`
    FOREIGN KEY (`userID`)
    REFERENCES `huaqi`.`user` (`UserID`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `huaqi`.`item`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `huaqi`.`item` (
  `ItemID` VARCHAR(45) NOT NULL,
  `description` VARCHAR(200) NULL DEFAULT NULL,
  `MerchantID` VARCHAR(45) NULL DEFAULT NULL,
  `Points` VARCHAR(45) NULL DEFAULT NULL,
  `Type` VARCHAR(45) NULL DEFAULT NULL,
  `OriginalPrice` DOUBLE NULL DEFAULT NULL,
  `AfterPrice` DOUBLE NULL DEFAULT NULL,
  `Discount` FLOAT NULL DEFAULT NULL,
  PRIMARY KEY (`ItemID`),
  INDEX `MerchantID_FK_idx` (`MerchantID` ASC),
  CONSTRAINT `MerchantID_FK5`
    FOREIGN KEY (`MerchantID`)
    REFERENCES `huaqi`.`merchant` (`MerchantID`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `huaqi`.`m_card`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `huaqi`.`m_card` (
  `CardID` VARCHAR(45) NOT NULL,
  `UserID` VARCHAR(45) NULL DEFAULT NULL,
  `Card_No` VARCHAR(45) NULL DEFAULT NULL,
  `Points` INT(11) NULL DEFAULT NULL,
  `CardType` VARCHAR(45) NULL DEFAULT NULL,
  PRIMARY KEY (`CardID`),
  INDEX `UserID_FK222_idx` (`UserID` ASC),
  INDEX `CardType_FK333_idx` (`CardType` ASC),
  CONSTRAINT `CardType_FK333`
    FOREIGN KEY (`CardType`)
    REFERENCES `huaqi`.`cardtype` (`CardType`),
  CONSTRAINT `UserID_FK222`
    FOREIGN KEY (`UserID`)
    REFERENCES `huaqi`.`user` (`UserID`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `huaqi`.`order`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `huaqi`.`order` (
  `OrderID` VARCHAR(45) NOT NULL,
  `OriginalPrice` DOUBLE NULL DEFAULT NULL,
  `PriceAfter` DOUBLE NULL DEFAULT NULL,
  `PointsNeeded` INT(11) NULL DEFAULT NULL,
  `UserID` VARCHAR(45) NULL DEFAULT NULL,
  `MerchantID` VARCHAR(45) NULL DEFAULT NULL,
  PRIMARY KEY (`OrderID`),
  INDEX `UserID_FK_idx` (`UserID` ASC),
  INDEX `MerchantID_FK_idx` (`MerchantID` ASC),
  CONSTRAINT `MerchantID_FK2`
    FOREIGN KEY (`MerchantID`)
    REFERENCES `huaqi`.`merchant` (`MerchantID`),
  CONSTRAINT `UserID_FK2`
    FOREIGN KEY (`UserID`)
    REFERENCES `huaqi`.`user` (`UserID`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `huaqi`.`orderitem`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `huaqi`.`orderitem` (
  `OrderID` VARCHAR(45) NOT NULL,
  `ItemID` VARCHAR(45) NOT NULL,
  `Num` INT(11) NULL DEFAULT NULL,
  PRIMARY KEY (`OrderID`, `ItemID`),
  INDEX `ItemID_FK_idx` (`ItemID` ASC),
  CONSTRAINT `ItemID_FK`
    FOREIGN KEY (`ItemID`)
    REFERENCES `huaqi`.`item` (`ItemID`),
  CONSTRAINT `OrderID_FK`
    FOREIGN KEY (`OrderID`)
    REFERENCES `huaqi`.`order` (`OrderID`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `huaqi`.`Vcode`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `huaqi`.`Vcode` (
  `UserID` VARCHAR(45) NOT NULL,
  `VCode` VARCHAR(45) NOT NULL,
  `Time` DATETIME NOT NULL,
  PRIMARY KEY (`UserID`),
  CONSTRAINT `VCode_userID_FK`
    FOREIGN KEY (`UserID`)
    REFERENCES `huaqi`.`user` (`UserID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `huaqi`.`Strategy`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `huaqi`.`Strategy` (
  `CardType` VARCHAR(45) NOT NULL,
  `full` INT NULL,
  `discount` INT NULL,
  PRIMARY KEY (`CardType`),
  CONSTRAINT `CardType_FK`
    FOREIGN KEY (`CardType`)
    REFERENCES `huaqi`.`cardtype` (`CardType`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
