DELIMITER $$
CREATE Procedure coupon_record(IN IN_MerchantID VARCHAR(45), IN IN_intervalDate INT)
BEGIN
SELECT SUM(points)
FROM coupon_view NATURAL JOIN (SELECT ItemID, points FROM item WHERE MerchantID = IN_MerchantID) AS item_tmp
WHERE getTime BETWEEN (NOW() - INTERVAL IN_intervalDate DAY) AND NOW();
END$$
commit;
