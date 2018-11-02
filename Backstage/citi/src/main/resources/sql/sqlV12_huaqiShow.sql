-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL,ALLOW_INVALID_DATES';

-- -----------------------------------------------------
-- Schema mydb
-- -----------------------------------------------------
-- -----------------------------------------------------
-- Schema huaqiShow
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema huaqiShow
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `huaqiShow` DEFAULT CHARACTER SET latin1 ;
USE `huaqiShow` ;

-- -----------------------------------------------------
-- Table `huaqiShow`.`merchant`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `huaqiShow`.`merchant` (
  `MerchantID` VARCHAR(45) NOT NULL,
  `name` VARCHAR(45) NULL DEFAULT NULL,
  `password` VARCHAR(45) NULL DEFAULT NULL,
  `description` VARCHAR(512) NULL DEFAULT NULL,
  `cardDescription` VARCHAR(512) NULL DEFAULT NULL,
  `address` VARCHAR(45) NULL DEFAULT NULL,
  `merchantLogoURL` VARCHAR(256) NULL DEFAULT NULL,
  `cardLogoURL` VARCHAR(256) NULL DEFAULT NULL,
  `proportion` DOUBLE NULL DEFAULT NULL,
  `cardType` VARCHAR(45) NULL DEFAULT NULL,
  `businessType` VARCHAR(45) NULL DEFAULT NULL,
  PRIMARY KEY (`MerchantID`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `huaqiShow`.`Strategy`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `huaqiShow`.`Strategy` (
  `strategyID` VARCHAR(45) NOT NULL,
  `MerchantID` VARCHAR(45) NULL DEFAULT NULL,
  `full` INT(11) NULL DEFAULT NULL,
  `priceAfter` INT(11) NULL DEFAULT NULL,
  `points` INT(11) NULL DEFAULT NULL,
  PRIMARY KEY (`strategyID`),
  INDEX `MerchantID_FK_Strategy_idx` (`MerchantID` ASC),
  CONSTRAINT `MerchantID_FK_Strategy`
    FOREIGN KEY (`MerchantID`)
    REFERENCES `huaqiShow`.`merchant` (`MerchantID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `huaqiShow`.`Vcode`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `huaqiShow`.`Vcode` (
  `phoneNum` VARCHAR(45) NOT NULL,
  `VCode` VARCHAR(45) NOT NULL,
  `Time` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `huaqiShow`.`activity`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `huaqiShow`.`activity` (
  `activityID` VARCHAR(45) NOT NULL,
  `merchantID` VARCHAR(45) NULL DEFAULT NULL,
  `name` VARCHAR(45) NULL DEFAULT NULL,
  `description` VARCHAR(200) NULL DEFAULT NULL,
  `startDate` DATE NULL DEFAULT NULL,
  `endDate` DATE NULL DEFAULT NULL,
  `imageURL` VARCHAR(100) NULL DEFAULT NULL,
  PRIMARY KEY (`activityID`),
  INDEX `merchantID_activity_FK_idx` (`merchantID` ASC),
  CONSTRAINT `merchantID_activity_FK`
    FOREIGN KEY (`merchantID`)
    REFERENCES `huaqiShow`.`merchant` (`MerchantID`)
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `huaqiShow`.`user`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `huaqiShow`.`user` (
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
-- Table `huaqiShow`.`citicard`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `huaqiShow`.`citicard` (
  `citiCardID` VARCHAR(100) NOT NULL,
  `citiCardNum` VARCHAR(45) NOT NULL,
  `phoneNum` VARCHAR(45) NULL DEFAULT NULL,
  `userID` VARCHAR(45) NOT NULL,
  `miniExpense` DOUBLE NULL DEFAULT NULL,
  PRIMARY KEY (`citiCardID`),
  INDEX `UserID_FK_idx` (`userID` ASC),
  CONSTRAINT `UserID_FK`
    FOREIGN KEY (`userID`)
    REFERENCES `huaqiShow`.`user` (`UserID`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `huaqiShow`.`feedback`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `huaqiShow`.`feedback` (
  `userID` VARCHAR(45) NOT NULL,
  `content` VARCHAR(512) NULL DEFAULT NULL,
  `time` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  INDEX `userID_FK_feedback_idx` (`userID` ASC),
  CONSTRAINT `userID_FK_feedback`
    FOREIGN KEY (`userID`)
    REFERENCES `huaqiShow`.`user` (`UserID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `huaqiShow`.`item`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `huaqiShow`.`item` (
  `ItemID` VARCHAR(45) NOT NULL,
  `name` VARCHAR(45) NULL DEFAULT NULL,
  `description` VARCHAR(200) NULL DEFAULT NULL,
  `MerchantID` VARCHAR(45) NULL DEFAULT NULL,
  `logoURL` VARCHAR(256) NULL DEFAULT NULL,
  `originalPrice` DOUBLE NULL DEFAULT NULL,
  `points` INT(11) NULL DEFAULT NULL,
  `overdueTime` TIMESTAMP NULL DEFAULT NULL,
  `stock` BIGINT(20) NULL DEFAULT '9223372036854775807',
  `itemType` CHAR(128) NULL DEFAULT NULL,
  PRIMARY KEY (`ItemID`),
  INDEX `MerchantID_FK_idx` (`MerchantID` ASC),
  CONSTRAINT `MerchantID_FK5`
    FOREIGN KEY (`MerchantID`)
    REFERENCES `huaqiShow`.`merchant` (`MerchantID`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `huaqiShow`.`m_card`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `huaqiShow`.`m_card` (
  `UserID` VARCHAR(45) NOT NULL,
  `cardNum` VARCHAR(45) NULL DEFAULT NULL,
  `Points` INT(11) NULL DEFAULT NULL,
  `MerchantID` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`UserID`, `MerchantID`),
  INDEX `UserID_FK222_idx` (`UserID` ASC),
  INDEX `Merchant_FK_MSCard_idx` (`MerchantID` ASC),
  CONSTRAINT `Merchant_FK_MSCard`
    FOREIGN KEY (`MerchantID`)
    REFERENCES `huaqiShow`.`merchant` (`MerchantID`),
  CONSTRAINT `UserID_FK_MSCard`
    FOREIGN KEY (`UserID`)
    REFERENCES `huaqiShow`.`user` (`UserID`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `huaqiShow`.`order`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `huaqiShow`.`order` (
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
    REFERENCES `huaqiShow`.`merchant` (`MerchantID`),
  CONSTRAINT `UserID_FK2`
    FOREIGN KEY (`UserID`)
    REFERENCES `huaqiShow`.`user` (`UserID`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `huaqiShow`.`points_history`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `huaqiShow`.`points_history` (
  `userID` VARCHAR(45) NOT NULL,
  `merchantID` VARCHAR(45) NOT NULL,
  `points_card` INT(11) NULL DEFAULT NULL,
  `points_citi` DOUBLE NULL DEFAULT NULL,
  `cause` VARCHAR(45) NULL DEFAULT NULL,
  `time` TIMESTAMP NULL DEFAULT NULL,
  INDEX `userID_FK_points_history_idx` (`userID` ASC),
  CONSTRAINT `userID_FK_points_history`
    FOREIGN KEY (`userID`)
    REFERENCES `huaqiShow`.`user` (`UserID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `huaqiShow`.`userPref`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `huaqiShow`.`userPref` (
  `userID` VARCHAR(45) NOT NULL,
  `prefType` CHAR(128) NULL DEFAULT NULL,
  PRIMARY KEY (`userID`),
  CONSTRAINT `userID_pref_FK`
    FOREIGN KEY (`userID`)
    REFERENCES `huaqiShow`.`user` (`UserID`)
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `huaqiShow`.`user_coupon`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `huaqiShow`.`user_coupon` (
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
    REFERENCES `huaqiShow`.`item` (`ItemID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `userID_FK_coupon`
    FOREIGN KEY (`userID`)
    REFERENCES `huaqiShow`.`user` (`UserID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
AUTO_INCREMENT = 74
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `huaqiShow`.`user_token`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `huaqiShow`.`user_token` (
  `userID` VARCHAR(45) NOT NULL,
  `refreshToken` VARCHAR(512) NULL DEFAULT NULL,
  `time` TIMESTAMP NULL DEFAULT NULL,
  PRIMARY KEY (`userID`),
  CONSTRAINT `userID_FK_Token`
    FOREIGN KEY (`userID`)
    REFERENCES `huaqiShow`.`user` (`UserID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `huaqiShow`.`visitRecord`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `huaqiShow`.`visitRecord` (
  `userID` VARCHAR(45) NOT NULL,
  `itemID` VARCHAR(45) NOT NULL,
  `time` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  INDEX `userID_visit_FK_idx` (`userID` ASC),
  INDEX `itemID_visit_FK_idx` (`itemID` ASC),
  CONSTRAINT `itemID_visit_FK`
    FOREIGN KEY (`itemID`)
    REFERENCES `huaqiShow`.`item` (`ItemID`)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  CONSTRAINT `userID_visit_FK`
    FOREIGN KEY (`userID`)
    REFERENCES `huaqiShow`.`user` (`UserID`)
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;

USE `huaqiShow` ;

-- -----------------------------------------------------
-- Placeholder table for view `huaqiShow`.`coupon_view`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `huaqiShow`.`coupon_view` (`ItemID` INT, `getTime` INT);

-- -----------------------------------------------------
-- Placeholder table for view `huaqiShow`.`order_view`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `huaqiShow`.`order_view` (`PointsNeeded` INT, `MerchantID` INT, `time` INT);

-- -----------------------------------------------------
-- procedure MSCard_record_date
-- -----------------------------------------------------

DELIMITER $$
USE `huaqiShow`$$
CREATE DEFINER=`huaqi`@`%` PROCEDURE `MSCard_record_date`(IN IN_MerchantID VARCHAR(45), IN IN_start_date DATE, IN IN_end_date DATE, OUT totalPoints BIGINT)
BEGIN
SELECT SUM(points_card)
FROM points_history
WHERE merchantID = IN_MerchantID AND DATE(points_history.time) BETWEEN IN_start_date AND IN_end_date
AND cause = 'EXCHANGE'
INTO totalPoints;
SELECT totalPoints;
END$$

DELIMITER ;

-- -----------------------------------------------------
-- procedure addM
-- -----------------------------------------------------

DELIMITER $$
USE `huaqiShow`$$
CREATE DEFINER=`huaqi`@`%` PROCEDURE `addM`()
BEGIN
      DECLARE i INT Default 1;
      DECLARE OriginalPrice DOUBLE; 
      DECLARE PriceAfter DOUBLE;
         simple_loop: LOOP
         SET i=i+1;
         select i;
         IF i=40 THEN
            LEAVE simple_loop;
         END IF;
         SET OriginalPrice = FLOOR((RAND() * 9999) + 1);
         SET PriceAfter = OriginalPrice - 0.85 * FLOOR((RAND() * OriginalPrice) + 1);
			INSERT INTO huaqiShow.order
            VALUES(i, OriginalPrice, PriceAfter, FLOOR((RAND() * 999) + 1), '1337eb4d-3128-4edb-85f0-1e945f54c331', 'SUCCESS', 1, now());
   END LOOP simple_loop;
END$$

DELIMITER ;

-- -----------------------------------------------------
-- procedure coupon_record
-- -----------------------------------------------------

DELIMITER $$
USE `huaqiShow`$$
CREATE DEFINER=`huaqi`@`%` PROCEDURE `coupon_record`(IN IN_MerchantID VARCHAR(45), IN IN_intervalDate INT, OUT totalPoints BIGINT)
BEGIN
SELECT SUM(points)
FROM (SELECT ItemID, getTime FROM user_coupon) AS user_coupon_tmp NATURAL JOIN (SELECT ItemID, points FROM item WHERE MerchantID = IN_MerchantID) AS item_tmp
WHERE getTime BETWEEN (NOW() - INTERVAL IN_intervalDate DAY) AND NOW()
INTO totalPoints;
SELECT totalPoints;
END$$

DELIMITER ;

-- -----------------------------------------------------
-- procedure merchant_out_points_record
-- -----------------------------------------------------

DELIMITER $$
USE `huaqiShow`$$
CREATE DEFINER=`huaqi`@`%` PROCEDURE `merchant_out_points_record`(IN IN_MerchantID VARCHAR(45), IN IN_intervalDate INT, OUT totalPoints BIGINT)
BEGIN
SELECT SUM(points_card)
FROM points_history
WHERE merchantID = IN_MerchantID AND cause = 'EXCHANGE' AND points_history.time BETWEEN (NOW() - INTERVAL IN_intervalDate DAY) AND NOW()
INTO totalPoints;
SELECT totalPoints;
END$$

DELIMITER ;

-- -----------------------------------------------------
-- procedure order_record
-- -----------------------------------------------------

DELIMITER $$
USE `huaqiShow`$$
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
-- procedure order_record_date
-- -----------------------------------------------------

DELIMITER $$
USE `huaqiShow`$$
CREATE DEFINER=`huaqi`@`%` PROCEDURE `order_record_date`(IN IN_MerchantID VARCHAR(45), IN IN_start_date DATE, IN IN_end_date DATE, OUT totalPoints BIGINT)
BEGIN
SELECT SUM(PointsNeeded)
FROM huaqiShow.order
WHERE MerchantID = IN_MerchantID
AND DATE(time) BETWEEN IN_start_date AND IN_end_date
INTO totalPoints;
SELECT totalPoints;
END$$

DELIMITER ;

-- -----------------------------------------------------
-- procedure points_record_card
-- -----------------------------------------------------

DELIMITER $$
USE `huaqiShow`$$
CREATE DEFINER=`huaqi`@`%` PROCEDURE `points_record_card`(IN IN_userID VARCHAR(45), IN IN_merchantID VARCHAR(45), IN IN_intervalDate INT, OUT totalPoints_card BIGINT, OUT totalPoints_citi BIGINT)
BEGIN
SELECT SUM(points_card), SUM(points_citi)
FROM points_history
WHERE userID = IN_userID AND merchantID = IN_merchantID
AND points_history.time BETWEEN (NOW() - INTERVAL IN_intervalDate DAY) AND NOW()
INTO totalPoints_card, totalPoints_citi;
SELECT totalPoints_card, totalPoints_citi;
END$$

DELIMITER ;

-- -----------------------------------------------------
-- procedure points_record_user
-- -----------------------------------------------------

DELIMITER $$
USE `huaqiShow`$$
CREATE DEFINER=`huaqi`@`%` PROCEDURE `points_record_user`(IN IN_userID VARCHAR(45), IN IN_intervalDate INT, OUT totalPoints_card BIGINT, OUT totalPoints_citi BIGINT)
BEGIN
SELECT SUM(points_card), SUM(points_citi)
FROM points_history
WHERE userID = IN_userID
AND points_history.time BETWEEN (NOW() - INTERVAL IN_intervalDate DAY) AND NOW()
INTO totalPoints_card, totalPoints_citi;
SELECT totalPoints_card, totalPoints_citi;
END$$

DELIMITER ;

-- -----------------------------------------------------
-- procedure user_coupon_overdue_update
-- -----------------------------------------------------

DELIMITER $$
USE `huaqiShow`$$
CREATE DEFINER=`huaqi`@`%` PROCEDURE `user_coupon_overdue_update`(IN IN_userID VARCHAR(45))
BEGIN
	
	declare done int default false; # for loop cursor
    
    DECLARE _overdueTime Timestamp;
    DECLARE _itemID VARCHAR(45);
    DECLARE conpon_it CURSOR FOR SELECT ItemID FROM user_coupon WHERE userID = IN_userID;
    
	declare continue HANDLER for not found set done = true;
    
    OPEN conpon_it;
    REPEAT  
            FETCH conpon_it INTO _itemID;
            
            IF NOT done THEN
				SELECT overdueTime FROM item WHERE ItemID = _itemID INTO _overdueTime;
            
				IF NOW() > _overdueTime THEN #OVERDUED
					UPDATE user_coupon SET state = 'OVERDUED' 
					WHERE userID = IN_userID AND ItemID = _itemID;
				END IF;
			END IF;
            
	UNTIL done END REPEAT;  
	CLOSE conpon_it;
    
END$$

DELIMITER ;

-- -----------------------------------------------------
-- procedure user_coupon_update
-- -----------------------------------------------------

DELIMITER $$
USE `huaqiShow`$$
CREATE DEFINER=`huaqi`@`%` PROCEDURE `user_coupon_update`(IN IN_userID VARCHAR(45), IN IN_itemID VARCHAR(45), OUT ifUsed INTEGER)
BEGIN
	
    DECLARE _overdueTime Timestamp;
    SELECT overdueTime FROM item WHERE ItemID = IN_itemID INTO _overdueTime;
    SET ifUsed = 0;
    
    IF now() > _overdueTime THEN #OVERDUED
    
		UPDATE user_coupon SET state = 'OVERDUED' 
		WHERE userID = IN_userID AND ItemID = IN_itemID AND state = 'UNUSED' 
		AND now() > _overdueTime;
        
	ELSE # change 1 UNUSED to USED if possible
    
		IF EXISTS (SELECT * FROM user_coupon 
				   WHERE userID = IN_userID AND ItemID = IN_itemID AND state = 'UNUSED')
		THEN 
			SET ifUsed = 1;
			UPDATE user_coupon SET state = 'USED', useTime = now()
			WHERE userID = IN_userID AND ItemID = IN_itemID AND state = 'UNUSED'
			ORDER BY userID ASC LIMIT 1;
        END IF;
        
    END IF;
    
    SELECT ifUsed;
    
END$$

DELIMITER ;

-- -----------------------------------------------------
-- View `huaqiShow`.`coupon_view`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `huaqiShow`.`coupon_view`;
USE `huaqiShow`;
CREATE  OR REPLACE ALGORITHM=UNDEFINED DEFINER=`huaqi`@`%` SQL SECURITY DEFINER VIEW `huaqiShow`.`coupon_view` AS select `huaqiShow`.`user_coupon`.`ItemID` AS `ItemID`,`huaqiShow`.`user_coupon`.`getTime` AS `getTime` from `huaqiShow`.`user_coupon`;

-- -----------------------------------------------------
-- View `huaqiShow`.`order_view`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `huaqiShow`.`order_view`;
USE `huaqiShow`;
CREATE  OR REPLACE ALGORITHM=UNDEFINED DEFINER=`huaqi`@`%` SQL SECURITY DEFINER VIEW `huaqiShow`.`order_view` AS select `huaqiShow`.`order`.`PointsNeeded` AS `PointsNeeded`,`huaqiShow`.`order`.`MerchantID` AS `MerchantID`,`huaqiShow`.`order`.`time` AS `time` from `huaqiShow`.`order` where (`huaqiShow`.`order`.`state` = 'SUCCESS');
USE `huaqiShow`;

DELIMITER $$
USE `huaqiShow`$$
CREATE
DEFINER=`huaqi`@`%`
TRIGGER `huaqiShow`.`points_update`
AFTER UPDATE ON `huaqiShow`.`m_card`
FOR EACH ROW
BEGIN
	DECLARE _userID VARCHAR(45) DEFAULT NEW.UserID;
    DECLARE _merchantID VARCHAR(45) DEFAULT NEW.MerchantID;
	DECLARE _diff_points INT DEFAULT NEW.points - OLD.points;
    DECLARE _proportion VARCHAR(45);
    SELECT proportion FROM merchant WHERE MerchantID = _merchantID INTO _proportion;
	IF _diff_points > 0 THEN
		INSERT INTO points_history VALUES(_userID, _merchantID, _diff_points, 0, "GAIN", NOW());
	ELSEIF _diff_points < 0 THEN
		INSERT INTO points_history VALUES(_userID, _merchantID, 0 - _diff_points, 0 - _proportion * _diff_points, "EXCHANGE", NOW());
    END IF;
END$$

USE `huaqiShow`$$
CREATE
DEFINER=`huaqi`@`%`
TRIGGER `huaqiShow`.`user_points_delete`
AFTER DELETE ON `huaqiShow`.`m_card`
FOR EACH ROW
BEGIN
	DECLARE _userID VARCHAR(45) DEFAULT OLD.UserID;
    DECLARE _merchantID VARCHAR(45) DEFAULT OLD.MerchantID;
	DECLARE _diff_points INT DEFAULT OLD.Points;
    DECLARE _proportion VARCHAR(45);
    SELECT proportion FROM merchant WHERE MerchantID = _merchantID INTO _proportion;
	IF _diff_points <> 0 THEN
		UPDATE huaqiShow.user SET AvailablePoints = AvailablePoints - _proportion * _diff_points WHERE userID = _userID;
    END IF;
END$$

USE `huaqiShow`$$
CREATE
DEFINER=`huaqi`@`%`
TRIGGER `huaqiShow`.`user_points_insert`
AFTER INSERT ON `huaqiShow`.`m_card`
FOR EACH ROW
BEGIN
	DECLARE _userID VARCHAR(45) DEFAULT NEW.UserID;
    DECLARE _merchantID VARCHAR(45) DEFAULT NEW.MerchantID;
	DECLARE _diff_points INT DEFAULT NEW.Points;
    DECLARE _proportion VARCHAR(45);
    SELECT proportion FROM merchant WHERE MerchantID = _merchantID INTO _proportion;
	IF _diff_points <> 0 THEN
		UPDATE huaqiShow.user SET AvailablePoints = AvailablePoints + _proportion * _diff_points WHERE userID = _userID;
    END IF;
END$$

USE `huaqiShow`$$
CREATE
DEFINER=`huaqi`@`%`
TRIGGER `huaqiShow`.`user_points_update`
AFTER UPDATE ON `huaqiShow`.`m_card`
FOR EACH ROW
BEGIN
	DECLARE _userID VARCHAR(45) DEFAULT NEW.UserID;
    DECLARE _merchantID VARCHAR(45) DEFAULT NEW.MerchantID;
	DECLARE _diff_points INT DEFAULT NEW.points - OLD.points;
    DECLARE _proportion VARCHAR(45);
    SELECT proportion FROM merchant WHERE MerchantID = _merchantID INTO _proportion;
	IF _diff_points <> 0 THEN
		UPDATE huaqiShow.user SET AvailablePoints = AvailablePoints + _proportion * _diff_points WHERE userID = _userID;
    END IF;
END$$

USE `huaqiShow`$$
CREATE
DEFINER=`huaqi`@`%`
TRIGGER `huaqiShow`.`item_stock_update`
AFTER INSERT ON `huaqiShow`.`user_coupon`
FOR EACH ROW
BEGIN
	DECLARE _itemID VARCHAR(45) DEFAULT NEW.ItemID;
	UPDATE item SET stock = stock - 1 WHERE ItemID = _itemID;
END$$


DELIMITER ;

SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
