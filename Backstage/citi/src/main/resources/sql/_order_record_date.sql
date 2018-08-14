DELIMITER $$
CREATE Procedure order_record_date(IN IN_MerchantID VARCHAR(45), IN IN_start_date DATE, IN IN_end_date DATE, OUT totalPoints BIGINT)
BEGIN
SELECT SUM(PointsNeeded)
FROM huaqi.order
WHERE MerchantID = IN_MerchantID
AND DATE(time) BETWEEN IN_start_date AND IN_end_date
INTO totalPoints;
SELECT totalPoints;
END$$
commit;
