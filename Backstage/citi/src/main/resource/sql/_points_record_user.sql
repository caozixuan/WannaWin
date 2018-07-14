DELIMITER $$
CREATE Procedure points_record_user(IN IN_userID VARCHAR(45), IN IN_intervalDate INT, OUT totalPoints_card BIGINT, OUT totalPoints_citi BIGINT)
BEGIN
SELECT SUM(points_card), SUM(points_citi)
FROM points_history
WHERE userID = IN_userID
AND points_history.time BETWEEN (NOW() - INTERVAL IN_intervalDate DAY) AND NOW()
INTO totalPoints_card, totalPoints_citi;
SELECT totalPoints_card, totalPoints_citi;
END$$
commit;
