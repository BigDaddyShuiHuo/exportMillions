-- 造数存储过程，注意自己的数据库和用户
CREATE DEFINER=`root`@`%` PROCEDURE `learn_demo`.`generate_test_data`()
BEGIN
  DECLARE i INT DEFAULT 1;
  WHILE i <= 1000000 DO
    INSERT INTO `million_data_export` (`name`, `age`, `data1`, `data2`, `data3`, `data4`, `data5`, `data6`, `data7`)
    VALUES (
      CONCAT('User', i),
      FLOOR(18 + RAND() * 50),
      CONCAT('Data1-', FLOOR(RAND()*1000)),
      CONCAT('Data2-', FLOOR(RAND()*1000)),
      CONCAT('Data3-', FLOOR(RAND()*1000)),
      CONCAT('Data4-', FLOOR(RAND()*1000)),
      CONCAT('Data5-', FLOOR(RAND()*1000)),
      CONCAT('Data6-', FLOOR(RAND()*1000)),
      CONCAT('Data7-', FLOOR(RAND()*100))
    );

    -- 每10000条提交一次
    IF i % 10000 = 0 THEN
      COMMIT;
END IF;

    SET i = i + 1;
END WHILE;
END