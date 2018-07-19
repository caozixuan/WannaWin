DELIMITER $$
CREATE TRIGGER item_stock_update AFTER INSERT
ON user_coupon
FOR EACH ROW
BEGIN
	DECLARE _itemID VARCHAR(45) DEFAULT NEW.ItemID;
	UPDATE item SET stock = stock - 1 WHERE ItemID = _itemID;
END;$$
commit;
