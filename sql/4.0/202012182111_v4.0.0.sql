ALTER TABLE `dagschedulex_4_0_x`.`schedule_job`
ADD INDEX `idx_exec_start_time`(`exec_start_time`) USING BTREE;
