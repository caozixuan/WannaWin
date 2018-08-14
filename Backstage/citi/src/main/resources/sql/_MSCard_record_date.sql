DELIMITER $$
CREATE Procedure MSCard_record_date(IN IN_MerchantID VARCHAR(45), IN IN_start_date DATE, IN IN_end_date DATE, OUT totalPoints BIGINT)
BEGIN
SELECT SUM(points_card)
FROM points_history
WHERE merchantID = IN_MerchantID AND DATE(points_history.time) BETWEEN IN_start_date AND IN_end_date
AND cause = 'EXCHANGE'
INTO totalPoints;
SELECT totalPoints;
END$$
commit;
