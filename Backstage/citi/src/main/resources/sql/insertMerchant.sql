DELIMITER $$  
CREATE PROCEDURE addM()
   BEGIN
      DECLARE i INT Default 0;
      DECLARE a0 VARCHAR(100) DEFAULT 'http://www.never-give-it-up.top/wp-content/uploads/2018/07/deng.png';
	  DECLARE a1 VARCHAR(100) DEFAULT 'http://www.never-give-it-up.top/wp-content/uploads/2018/06/pen1.png';
	  DECLARE a2 VARCHAR(100) DEFAULT 'http://www.never-give-it-up.top/wp-content/uploads/2018/07/qiang.png';
	  DECLARE a3 VARCHAR(100) DEFAULT 'http://www.never-give-it-up.top/wp-content/uploads/2018/07/huaji.png';
      simple_loop: LOOP
         SET i=i+1;
         select i;
         IF i=40 THEN
            LEAVE simple_loop;
         END IF;
         IF (i MOD 4)=0 THEN
			INSERT INTO merchant
            VALUES(i, i, SUBSTRING(MD5(RAND()) FROM 1 FOR 8), SUBSTRING(MD5(RAND()) FROM 1 FOR 8), 
            a0);
         END IF;
         
		IF (i MOD 4)=1 THEN
			INSERT INTO merchant
            VALUES(i, i, SUBSTRING(MD5(RAND()) FROM 1 FOR 8), SUBSTRING(MD5(RAND()) FROM 1 FOR 8), 
            a1);
         END IF;
         
         
		IF (i MOD 4)=2 THEN
			INSERT INTO merchant
            VALUES(i, i, SUBSTRING(MD5(RAND()) FROM 1 FOR 8), SUBSTRING(MD5(RAND()) FROM 1 FOR 8), 
            a2);
         END IF;
         
         
		IF (i MOD 4)=3 THEN
			INSERT INTO merchant
            VALUES(i, i, SUBSTRING(MD5(RAND()) FROM 1 FOR 8), SUBSTRING(MD5(RAND()) FROM 1 FOR 8), 
            a3);
         END IF;
         
         
   END LOOP simple_loop;
END $$