DELIMITER $$
CREATE Procedure points_record_card(IN IN_userID VARCHAR(45), IN IN_merchantID VARCHAR(45), IN IN_intervalDate INT, OUT totalPoints_card BIGINT, OUT totalPoints_citi BIGINT)
BEGIN
SELECT SUM(points_card), SUM(points_citi)
FROM points_history
WHERE userID = IN_userID AND merchantID = IN_merchantID
AND points_history.time BETWEEN (NOW() - INTERVAL IN_intervalDate DAY) AND NOW()
INTO totalPoints_card, totalPoints_citi;
SELECT totalPoints_card, totalPoints_citi;
END$$
commit;
