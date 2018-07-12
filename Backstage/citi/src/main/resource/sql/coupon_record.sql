DELIMITER $$
CREATE Procedure coupon_record(IN IN_MerchantID VARCHAR(45), IN IN_intervalDate INT, OUT totalPoints BIGINT)
BEGIN
SELECT SUM(points)
FROM (SELECT ItemID, getTime FROM user_coupon) AS user_coupon_tmp NATURAL JOIN (SELECT ItemID, points FROM item WHERE MerchantID = IN_MerchantID) AS item_tmp
WHERE getTime BETWEEN (NOW() - INTERVAL IN_intervalDate DAY) AND NOW()
INTO totalPoints;
SELECT totalPoints;
END$$
