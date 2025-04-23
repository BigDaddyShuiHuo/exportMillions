
-- 表创建脚本
CREATE TABLE `million_data_export` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `name` varchar(100) COLLATE utf8mb4_bin NOT NULL DEFAULT '' COMMENT '姓名',
  `age` varchar(100) COLLATE utf8mb4_bin NOT NULL DEFAULT '' COMMENT '年龄',
  `data1` varchar(500) COLLATE utf8mb4_bin DEFAULT NULL,
  `data2` varchar(500) COLLATE utf8mb4_bin DEFAULT NULL,
  `data3` varchar(500) COLLATE utf8mb4_bin DEFAULT NULL,
  `data4` varchar(500) COLLATE utf8mb4_bin DEFAULT NULL,
  `data5` varchar(500) COLLATE utf8mb4_bin DEFAULT NULL,
  `data6` varchar(500) COLLATE utf8mb4_bin DEFAULT NULL,
  `data7` varchar(100) COLLATE utf8mb4_bin DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1000001 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin;