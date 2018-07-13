DELIMITER $$
CREATE Procedure order_record(IN IN_MerchantID VARCHAR(45), IN IN_intervalDate INT, OUT totalPoints BIGINT)
BEGIN
SELECT SUM(PointsNeeded)
FROM order_view
WHERE MerchantID = IN_MerchantID
AND order_view.time BETWEEN (NOW() - INTERVAL IN_intervalDate DAY) AND NOW()
INTO totalPoints;
END$$
commit;
