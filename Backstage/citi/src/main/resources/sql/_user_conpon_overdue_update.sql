DELIMITER $$
CREATE Procedure user_coupon_overdue_update(IN IN_userID VARCHAR(45))
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
commit;
