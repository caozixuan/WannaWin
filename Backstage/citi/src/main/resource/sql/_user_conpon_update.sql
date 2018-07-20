DELIMITER $$
CREATE Procedure user_coupon_update(IN IN_userID VARCHAR(45), IN IN_itemID INT, OUT ifUsed INTEGER)
BEGIN
	
    UPDATE user_coupon SET state = 'OVERDUED' 
    WHERE userID = IN_userID AND ItemID = IN_itemID AND state = 'UNUSED' 
    AND now() < (SELECT overdueTime FROM item WHERE ItemID = IN_itemID);

	SELECT COUNT(*) FROM user_coupon 
    WHERE userID = IN_userID AND ItemID = IN_itemID AND state = 'UNUSED'
    INTO ifUsed;
    
    IF ifUsed > 0 THEN
		SET ifUsed = 1;
	END IF;
    
    SELECT ifUsed;

	UPDATE user_coupon SET state = 'USED', useTime = now()
	WHERE userID = IN_userID AND ItemID = IN_itemID AND state = 'UNUSED'
	ORDER BY userID ASC LIMIT 1;
    
END$$
commit;
