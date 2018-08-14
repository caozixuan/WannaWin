DELIMITER $$  
CREATE PROCEDURE addCoupon()
   BEGIN
      DECLARE i INT Default 0;
      simple_loop: LOOP
         SET i=i+1;
         select i;
         IF i=40 THEN
            LEAVE simple_loop;
         END IF;
 
			INSERT INTO user_coupon
            VALUES(i, '3c5de2b2-54fd-4175-8af7-ff9a8ebea497', 
			1, 'UNUSED');
         
   END LOOP simple_loop;
END $$