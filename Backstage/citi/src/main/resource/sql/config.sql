DROP PROCEDURE addM;

DROP PROCEDURE coupon_record;
DROP PROCEDURE order_record;

DROP TRIGGER points_update;

DROP PROCEDURE points_record_user;
DROP PROCEDURE points_record_card; 
DROP PROCEDURE merchant_out_points_record;
DROP PROCEDURE order_record_date;
DROP PROCEDURE MSCard_record_date;
## deprecated## DROP PROCEDURE coupon_record_date; 

DROP TRIGGER user_points_update;
DROP TRIGGER user_points_insert;
DROP TRIGGER user_points_delete;

SET SQL_SAFE_UPDATES = 0;