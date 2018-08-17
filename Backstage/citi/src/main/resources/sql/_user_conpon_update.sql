DELIMITER $$
CREATE Procedure user_coupon_update(IN IN_userID VARCHAR(45), IN IN_itemID VARCHAR(45), OUT ifUsed INTEGER)
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
commit;
