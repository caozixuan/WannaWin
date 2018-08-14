DELIMITER $$
CREATE Procedure merchant_out_points_record(IN IN_MerchantID VARCHAR(45), IN IN_intervalDate INT, OUT totalPoints BIGINT)
BEGIN
SELECT SUM(points_card)
FROM points_history
WHERE merchantID = IN_MerchantID AND cause = 'EXCHANGE' AND points_history.time BETWEEN (NOW() - INTERVAL IN_intervalDate DAY) AND NOW()
INTO totalPoints;
SELECT totalPoints;
END$$
commit;
