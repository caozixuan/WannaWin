SET @temp_card = 0, @temp_citi = 0;
CALL points_record_user('3c5de2b2-54fd-4175-8af7-ff9a8ebea497', 7, @temp_card, @temp_citi);
CALL points_record_card('fff04ada8ef1aa498017', 7, @temp_card, @temp_citi);
SELECT @temp_card, @temp_citi;

