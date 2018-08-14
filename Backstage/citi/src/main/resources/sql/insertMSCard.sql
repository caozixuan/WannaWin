DELIMITER $$  
CREATE PROCEDURE addMSCard()
   BEGIN
      DECLARE i INT Default 0;
      simple_loop: LOOP
         SET i=i+1;
         select i;
         IF i=40 THEN
            LEAVE simple_loop;
         END IF;
 
			INSERT INTO m_card
            VALUES(SUBSTRING(MD5(RAND()) FROM 1 FOR 20), '3c5de2b2-54fd-4175-8af7-ff9a8ebea497', 
			FLOOR((RAND() * 99999) + 1), FLOOR((RAND() * 999) + 1), i);
         
   END LOOP simple_loop;
END $$