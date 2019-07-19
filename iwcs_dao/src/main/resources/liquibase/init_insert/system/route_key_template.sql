INSERT INTO `route_key_template` ( `id`,`route_key_temp_name`,`route_key_temp_code`,`route_key_template`) VALUES
(NULL ,'储位释放','posReleaseEvt','resources.mapcode.opea.pos.release'),
(NULL,'货架释放','podReleaseEvt','resources.mapcode.opea.pod.release'),
(NULL,'任务单创建','taskCreate','task.mapcode.tasknum.sys.create'),
(NULL,'任务单完结','taskEnd','task.mapcode.tasknum.sys.end'),
(NULL,'work任务开始','workerStart','ask.mapcode.tasknum.worker.start'),
(NULL,'work离开储位','workerLevstartpos','ask.mapcode.tasknum.worker.levstartpos'),
(NULL,'work任务结束','workerEnd','ask.mapcode.tasknum.worker.end');