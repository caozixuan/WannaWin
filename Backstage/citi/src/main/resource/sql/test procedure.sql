SET @temp_card = 0, @temp_citi = 0, @temp = 0;
call points_record_user('3c5de2b2-54fd-4175-8af7-ff9a8ebea497', 7, @temp_card, @temp_citi);
CALL merchant_out_points_record('2', 7, @temp);
SELECT @temp_card, @temp_citi;


DELIMITER $$  
CREATE PROCEDURE addM()
   BEGIN
      DECLARE i INT Default 0;
      DECLARE a0 VARCHAR(100) DEFAULT 'http://www.never-give-it-up.top/wp-content/uploads/2018/07/nike_logo.png';
	  DECLARE a1 VARCHAR(100) DEFAULT 'http://www.never-give-it-up.top/wp-content/uploads/2018/07/dianxin_logo.png';
	  DECLARE a2 VARCHAR(100) DEFAULT 'http://www.never-give-it-up.top/wp-content/uploads/2018/07/yidong_logo.png';
	  DECLARE a3 VARCHAR(100) DEFAULT 'http://www.never-give-it-up.top/wp-content/uploads/2018/07/apple_logo.png';
      simple_loop: LOOP
         SET i=i+1;
         select i;
         IF i=40 THEN
            LEAVE simple_loop;
         END IF;
         IF (i MOD 4)=0 THEN
			UPDATE merchant
			SET merchantLogoURL = a0
            WHERE merchantID = i;
         END IF;
         
         IF (i MOD 4)=1 THEN
			UPDATE merchant
			SET merchantLogoURL = a1
            WHERE merchantID = i;
         END IF;
         
         IF (i MOD 4)=2 THEN
			UPDATE merchant
			SET merchantLogoURL = a2
            WHERE merchantID = i;
         END IF;
         
        IF (i MOD 4)=3 THEN
			UPDATE merchant
			SET merchantLogoURL = a3
            WHERE merchantID = i;
         END IF;
         
         
   END LOOP simple_loop;
END $$