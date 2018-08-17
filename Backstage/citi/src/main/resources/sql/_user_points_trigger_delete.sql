DELIMITER $$
CREATE TRIGGER user_points_delete AFTER DELETE
ON m_card
FOR EACH ROW
BEGIN
	DECLARE _userID VARCHAR(45) DEFAULT OLD.UserID;
    DECLARE _merchantID VARCHAR(45) DEFAULT OLD.MerchantID;
	DECLARE _diff_points INT DEFAULT OLD.Points;
    DECLARE _proportion VARCHAR(45);
    SELECT proportion FROM merchant WHERE MerchantID = _merchantID INTO _proportion;
	IF _diff_points <> 0 THEN
		UPDATE huaqi.user SET AvailablePoints = AvailablePoints - _proportion * _diff_points WHERE userID = _userID;
    END IF;
END;$$
commit;
