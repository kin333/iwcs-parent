delete  from `s_authority`;
INSERT INTO `s_authority` VALUES (1, 'W_MAP', NULL, '地图管理', 'list', '地图管理', 0, 1, 0);
INSERT INTO `s_authority` VALUES (2, 'W_TASK', NULL, '任务管理', 'list', '任务管理', 0, 1, 0);
INSERT INTO `s_authority` VALUES (3, 'W_POD', NULL, '货架管理', 'list', '货架管理', 0, 1, 0);
INSERT INTO `s_authority` VALUES (4, 'W_LOG', NULL, '日志管理', 'list', '日志管理', 0, 1, 0);
INSERT INTO `s_authority` VALUES (5, 'W_SETTING', NULL, '系统管理', 'list', '系统管理', 0, 1, 0);
INSERT INTO `s_authority` VALUES (6, 'W_ELEVATOR', NULL, '梯控管理', 'list', '梯控管理', 0, 1, 0);
INSERT INTO `s_authority` VALUES (7, 'W_SUB_TASK', NULL, '子任务管理', 'list', '子任务管理', 2, 1, 0);
INSERT INTO `s_authority` VALUES (8, 'W_MAIN_TASK', NULL, '主任务管理', 'list', '主任务管理', 2, 1, 0);
INSERT INTO `s_authority` VALUES (9, 'W_POD_INFO', NULL, '货架信息', 'list', '货架信息', 3, 1, 0);
INSERT INTO `s_authority` VALUES (10, 'W_POD_INIT', NULL, '初始化货架', 'list', '初始化货架', 3, 1, 0);
INSERT INTO `s_authority` VALUES (11, 'W_LOG_INFO', NULL, '日志表', 'list', '日志表', 4, 1, 0);
INSERT INTO `s_authority` VALUES (12, 'W_LOG_TASK', NULL, '任务日志', 'list', '任务日志', 4, 1, 0);
INSERT INTO `s_authority` VALUES (13, 'W_SETTINGS_ACCOUNT', NULL, '账户开通', 'list', '账户开通', 5, 1, 0);
INSERT INTO `s_authority` VALUES (14, 'W_SETTINGS_PERSON', NULL, '个人信息', 'list', '个人信息', 5, 1, 0);
INSERT INTO `s_authority` VALUES (15, 'W_SETTINGS_PDA', NULL, 'PAD升级', 'list', 'PAD升级', 5, 1, 0);
INSERT INTO `s_authority` VALUES (16, 'W_SETTINGS_ROLE', NULL, '角色设置', 'list', '角色设置', 5, 1, 0);
INSERT INTO `s_authority` VALUES (17, 'W_ELEVATOR_VIEW', NULL, '梯控视图', 'list', '梯控视图', 6, 1, 0);
-- INSERT INTO `s_authority` VALUES (18, 'W_ELEVATOR_HISTORY_INFO', NULL, '历史记录', 'list', '历史记录', 6, 1, 0);
INSERT INTO `s_authority` VALUES (19, 'W_ELEVATOR_LOG', NULL, '梯控日志', 'list', '梯控日志', 4, 1, 0);
INSERT INTO `s_authority` VALUES (20, 'W_ELEVATOR_LINE_LOG', NULL, '线体日志', 'list', '线体日志', 4, 1, 0);
INSERT INTO `s_authority` VALUES (21, 'W_STORE', NULL, '仓库管理', 'list', '仓库管理', 0, 1, 0);
INSERT INTO `s_authority` VALUES (22, 'W_STORE_VIEW', NULL, '仓库监控', 'list', '仓库监控', 21, 1, 0);
INSERT INTO `s_authority` VALUES (23, 'W_OPERATION_SCHEDUL', NULL, '运维调度', 'list', '仓库管理', 0, 1, 0);
INSERT INTO `s_authority` VALUES (24, 'W_OPERATION_BIND_POD', NULL, '货架绑定', 'list', '仓库监控', 23, 1, 0);
INSERT INTO `s_authority` VALUES (25, 'W_MAP_DATA', NULL, '地图数据', 'list', '地图数据', 1, 1, 0);
INSERT INTO `s_authority` VALUES (26, 'W_MAP_SET', NULL, '地图设置', 'list', '地图设置', 1, 1, 0);
INSERT INTO `s_authority` VALUES (27, 'W_TASK_MODAL', NULL, '任务模板', 'list', '任务模板', 2, 1, 0);
INSERT INTO `s_authority` VALUES (28, 'W_SUB_TASK_MODAL', NULL, '子任务类型', 'list', '子任务类型', 2, 1, 0);
-- 菜单按钮

INSERT INTO `s_authority` VALUES (29, 'W_MAP_DATA_LOCK', 0, '地图数据-锁定', null, '菜单管理', 25, 2, 1000);
INSERT INTO `s_authority` VALUES (30, 'W_MAP_DATA_UNLOCK', 0, '地图数据-解锁', null, '菜单管理', 25, 2, 1000);
INSERT INTO `s_authority` VALUES (31, 'W_MAP_DATA_UPDATE', 0, '地图数据-更多数据', null, '菜单管理', 25, 2, 1000);

INSERT INTO `s_authority` VALUES (32, 'W_MAP_SET_LOCK', 0, '地图设置-锁定', null, '菜单管理', 26, 2, 1000);
INSERT INTO `s_authority` VALUES (33, 'W_MAP_SET_UNLOCK', 0, '地图设置-解锁', null, '菜单管理', 26, 2, 1000);
INSERT INTO `s_authority` VALUES (34, 'W_MAP_SET_EDIT', 0, '地图设置-编辑', null, '菜单管理', 26, 2, 1000);

INSERT INTO `s_authority` VALUES (35, 'W_TASK_MODAL_NEW', 0, '任务模板-新增', null, '菜单管理', 27, 2, 1000);
INSERT INTO `s_authority` VALUES (36, 'W_TASK_MODAL_EDIT', 0, '任务模板-编辑', null, '菜单管理', 27, 2, 1000);
INSERT INTO `s_authority` VALUES (37, 'W_TASK_MODAL_DELETE', 0, '任务模板-删除', null, '菜单管理', 27, 2, 1000);
INSERT INTO `s_authority` VALUES (38, 'W_TASK_MODAL_GRAQHIC', 0, '任务模板-图形转化', null, '菜单管理', 27, 2, 1000);
INSERT INTO `s_authority` VALUES (39, 'W_TASK_MODAL_TEMPL_DELETE', 0, '任务模板列表-删除', null, '菜单管理', 27, 2, 1000);
INSERT INTO `s_authority` VALUES (40, 'W_TASK_MODAL_TEMPL_CONFIG', 0, '任务模板配置信息-保存', null, '菜单管理', 27, 2, 1000);
INSERT INTO `s_authority` VALUES (41, 'W_TASK_MODAL_CONFIG', 0, '任务模板-配置', null, '菜单管理', 27, 2, 1000);

INSERT INTO `s_authority` VALUES (42, 'W_SUB_TASK_MODAL_NEW', 0, '子任务类型-添加', null, '菜单管理', 28, 2, 1000);
INSERT INTO `s_authority` VALUES (43, 'W_SUB_TASK_MODAL_CONFIG', 0, '子任务类型-编辑', null, '菜单管理', 28, 2, 1000);
INSERT INTO `s_authority` VALUES (44, 'W_SUB_TASK_MODAL_DELETE', 0, '子任务类型-删除', null, '菜单管理', 28, 2, 1000);

INSERT INTO `s_authority` VALUES (45, 'W_MAIN_TASK_CONFIG', 0, '主任务管理-设置优先级', null, '菜单管理', 8, 2, 1000);

INSERT INTO `s_authority` VALUES (46, 'W_POD_INFO_LOCK', 0, '货架信息-锁定', null, '菜单管理', 9, 2, 1000);
INSERT INTO `s_authority` VALUES (47, 'W_POD_INFO_UNLOCK', 0, '货架信息-解锁', null, '菜单管理', 9, 2, 1000);