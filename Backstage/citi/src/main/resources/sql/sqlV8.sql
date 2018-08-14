-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL,ALLOW_INVALID_DATES';

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
  `password` VARCHAR(45) NULL DEFAULT NULL,
  `description` VARCHAR(512) NULL DEFAULT NULL,
  `cardDescription` VARCHAR(512) NULL DEFAULT NULL,
  `address` VARCHAR(45) NULL DEFAULT NULL,
  `merchantLogoURL` VARCHAR(256) NULL DEFAULT NULL,
  `cardLogoURL` VARCHAR(256) NULL DEFAULT NULL,
  `proportion` DOUBLE NULL DEFAULT NULL,
  PRIMARY KEY (`MerchantID`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `huaqi`.`Strategy`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `huaqi`.`Strategy` (
  `strategyID` VARCHAR(45) NOT NULL,
  `MerchantID` VARCHAR(45) NULL DEFAULT NULL,
  `full` INT(11) NULL DEFAULT NULL,
  `discount` INT(11) NULL DEFAULT NULL,
  `points` INT(11) NULL DEFAULT NULL,
  PRIMARY KEY (`strategyID`),
  INDEX `MerchantID_FK_Strategy_idx` (`MerchantID` ASC),
  CONSTRAINT `MerchantID_FK_Strategy`
    FOREIGN KEY (`MerchantID`)
    REFERENCES `huaqi`.`merchant` (`MerchantID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `huaqi`.`Vcode`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `huaqi`.`Vcode` (
  `phoneNum` VARCHAR(45) NOT NULL,
  `VCode` VARCHAR(45) NOT NULL,
  `Time` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `huaqi`.`cardtype`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `huaqi`.`cardtype` (
  `MerchantID` VARCHAR(45) NULL DEFAULT NULL,
  `Mtype` VARCHAR(45) NULL DEFAULT NULL,
  `CardTypeID` VARCHAR(45) NOT NULL,
  `Proportion` DOUBLE NULL DEFAULT NULL,
  `miniExpense` VARCHAR(45) NULL DEFAULT NULL,
  PRIMARY KEY (`CardTypeID`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `huaqi`.`user`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `huaqi`.`user` (
  `UserID` VARCHAR(45) NOT NULL,
  `Password` VARCHAR(45) NULL DEFAULT NULL,
  `CitiCardID` VARCHAR(100) NULL DEFAULT NULL,
  `PhoneNum` VARCHAR(45) NOT NULL,
  `GeneralPoints` DOUBLE NULL DEFAULT NULL,
  `AvailablePoints` DOUBLE NULL DEFAULT NULL,
  `rewardLinkCode` VARCHAR(20) NULL DEFAULT NULL,
  PRIMARY KEY (`UserID`),
  UNIQUE INDEX `PhoneNum_UNIQUE` (`PhoneNum` ASC))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `huaqi`.`citicard`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `huaqi`.`citicard` (
  `citiCardID` VARCHAR(100) NOT NULL,
  `citiCardNum` VARCHAR(45) NOT NULL,
  `phoneNum` VARCHAR(45) NULL DEFAULT NULL,
  `userID` VARCHAR(45) NOT NULL,
  `miniExpense` DOUBLE NULL DEFAULT NULL,
  PRIMARY KEY (`citiCardID`),
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
  `name` VARCHAR(45) NULL DEFAULT NULL,
  `description` VARCHAR(200) NULL DEFAULT NULL,
  `MerchantID` VARCHAR(45) NULL DEFAULT NULL,
  `logoURL` VARCHAR(256) NULL DEFAULT NULL,
  `originalPrice` DOUBLE NULL DEFAULT NULL,
  `points` INT(11) NULL DEFAULT NULL,
  `overdueTime` TIMESTAMP NULL DEFAULT NULL,
  `stock` BIGINT(20) NULL DEFAULT '9223372036854775807',
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
  `cardNum` VARCHAR(45) NULL DEFAULT NULL,
  `Points` INT(11) NULL DEFAULT NULL,
  `MerchantID` VARCHAR(45) NULL DEFAULT NULL,
  PRIMARY KEY (`CardID`),
  INDEX `UserID_FK222_idx` (`UserID` ASC),
  INDEX `Merchant_FK_MSCard_idx` (`MerchantID` ASC),
  CONSTRAINT `Merchant_FK_MSCard`
    FOREIGN KEY (`MerchantID`)
    REFERENCES `huaqi`.`merchant` (`MerchantID`),
  CONSTRAINT `UserID_FK_MSCard`
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
  `PointsNeeded` DOUBLE NULL DEFAULT NULL,
  `UserID` VARCHAR(45) NULL DEFAULT NULL,
  `state` VARCHAR(45) NULL DEFAULT NULL,
  `MerchantID` VARCHAR(45) NULL DEFAULT NULL,
  `time` TIMESTAMP NULL DEFAULT NULL,
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
-- Table `huaqi`.`user_coupon`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `huaqi`.`user_coupon` (
  `couponID` BIGINT(20) NOT NULL AUTO_INCREMENT,
  `userID` VARCHAR(45) NOT NULL,
  `ItemID` VARCHAR(45) NULL DEFAULT NULL,
  `state` VARCHAR(45) NULL DEFAULT NULL,
  `getTime` TIMESTAMP NULL DEFAULT NULL,
  `useTime` TIMESTAMP NULL DEFAULT NULL,
  PRIMARY KEY (`couponID`),
  INDEX `userID_FK_coupon_idx` (`userID` ASC),
  INDEX `ItemID_FK_coupon_idx` (`ItemID` ASC),
  CONSTRAINT `ItemID_FK_coupon`
    FOREIGN KEY (`ItemID`)
    REFERENCES `huaqi`.`item` (`ItemID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `userID_FK_coupon`
    FOREIGN KEY (`userID`)
    REFERENCES `huaqi`.`user` (`UserID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
AUTO_INCREMENT = 40
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `huaqi`.`user_token`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `huaqi`.`user_token` (
  `userID` VARCHAR(45) NOT NULL,
  `refreshToken` VARCHAR(512) NULL DEFAULT NULL,
  `time` TIMESTAMP NULL DEFAULT NULL,
  PRIMARY KEY (`userID`),
  CONSTRAINT `userID_FK_Token`
    FOREIGN KEY (`userID`)
    REFERENCES `huaqi`.`user` (`UserID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;

USE `huaqi` ;

-- -----------------------------------------------------
-- Placeholder table for view `huaqi`.`coupon_view`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `huaqi`.`coupon_view` (`ItemID` INT, `getTime` INT);

-- -----------------------------------------------------
-- Placeholder table for view `huaqi`.`order_view`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `huaqi`.`order_view` (`PointsNeeded` INT, `MerchantID` INT, `time` INT);

-- -----------------------------------------------------
-- procedure coupon_record
-- -----------------------------------------------------

DELIMITER $$
USE `huaqi`$$
CREATE DEFINER=`huaqi`@`%` PROCEDURE `coupon_record`(IN IN_MerchantID VARCHAR(45), IN IN_intervalDate INT, OUT totalPoints BIGINT)
BEGIN
SELECT SUM(points)
FROM coupon_view NATURAL JOIN (SELECT ItemID, points FROM item WHERE MerchantID = IN_MerchantID) AS item_tmp
WHERE getTime BETWEEN (NOW() - INTERVAL IN_intervalDate DAY) AND NOW()
INTO totalPoints;
SELECT totalPoints;
END$$

DELIMITER ;

-- -----------------------------------------------------
-- procedure order_record
-- -----------------------------------------------------

DELIMITER $$
USE `huaqi`$$
CREATE DEFINER=`huaqi`@`%` PROCEDURE `order_record`(IN IN_MerchantID VARCHAR(45), IN IN_intervalDate INT, OUT totalPoints BIGINT)
BEGIN
SELECT SUM(PointsNeeded)
FROM order_view
WHERE MerchantID = IN_MerchantID
AND order_view.time BETWEEN (NOW() - INTERVAL IN_intervalDate DAY) AND NOW()
INTO totalPoints;
SELECT totalPoints;
END$$

DELIMITER ;

-- -----------------------------------------------------
-- View `huaqi`.`coupon_view`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `huaqi`.`coupon_view`;
USE `huaqi`;
CREATE  OR REPLACE ALGORITHM=UNDEFINED DEFINER=`huaqi`@`%` SQL SECURITY DEFINER VIEW `huaqi`.`coupon_view` AS select `huaqi`.`user_coupon`.`ItemID` AS `ItemID`,`huaqi`.`user_coupon`.`getTime` AS `getTime` from `huaqi`.`user_coupon`;

-- -----------------------------------------------------
-- View `huaqi`.`order_view`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `huaqi`.`order_view`;
USE `huaqi`;
CREATE  OR REPLACE ALGORITHM=UNDEFINED DEFINER=`huaqi`@`%` SQL SECURITY DEFINER VIEW `huaqi`.`order_view` AS select `huaqi`.`order`.`PointsNeeded` AS `PointsNeeded`,`huaqi`.`order`.`MerchantID` AS `MerchantID`,`huaqi`.`order`.`time` AS `time` from `huaqi`.`order`;

SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
