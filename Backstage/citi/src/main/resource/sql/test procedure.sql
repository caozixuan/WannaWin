SET @temp_card = 0, @temp_citi = 0, @temp = 0;
call points_record_user('3c5de2b2-54fd-4175-8af7-ff9a8ebea497', 7, @temp_card, @temp_citi);
CALL merchant_out_points_record('2', 7, @temp);
CALL order_record_date('00002', '2018-07-09', '2018-07-15', @temp);
SELECT @temp;
SELECT @temp_card, @temp_citi;