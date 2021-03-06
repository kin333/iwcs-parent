delete  from `s_authority`;
INSERT INTO `s_authority` VALUES (1, 'W_MAP', NULL, '地图管理', 'list', '地图管理', 0, 1, 0);
INSERT INTO `s_authority` VALUES (2, 'W_TASK', NULL, '任务管理', 'list', '任务管理', 0, 1, 0);
INSERT INTO `s_authority` VALUES (3, 'W_POD', NULL, '货架管理', 'list', '货架管理', 0, 1, 0);
INSERT INTO `s_authority` VALUES (4, 'W_LOG', NULL, '日志管理', 'list', '日志管理', 0, 1, 0);
INSERT INTO `s_authority` VALUES (5, 'W_SETTING', NULL, '系统管理', 'list', '系统管理', 0, 1, 0);
INSERT INTO `s_authority` VALUES (7, 'W_SUB_TASK', NULL, '子任务管理', 'list', '子任务管理', 2, 1, 0);
INSERT INTO `s_authority` VALUES (8, 'W_MAIN_TASK', NULL, '主任务管理', 'list', '主任务管理', 2, 1, 0);
INSERT INTO `s_authority` VALUES (9, 'W_POD_INFO', NULL, '货架信息', 'list', '货架信息', 3, 1, 0);
INSERT INTO `s_authority` VALUES (10, 'W_POD_INIT', NULL, '初始化货架', 'list', '初始化货架', 3, 1, 0);
INSERT INTO `s_authority` VALUES (11, 'W_LOG_INFO', NULL, '接口日志', 'list', '接口日志', 4, 1, 0);
INSERT INTO `s_authority` VALUES (12, 'W_LOG_TASK', NULL, '任务日志', 'list', '任务日志', 4, 1, 0);
INSERT INTO `s_authority` VALUES (13, 'W_SETTINGS_ACCOUNT', NULL, '账户开通', 'list', '账户开通', 5, 1, 0);
INSERT INTO `s_authority` VALUES (14, 'W_SETTINGS_PERSON', NULL, '个人信息', 'list', '个人信息', 5, 1, 0);
INSERT INTO `s_authority` VALUES (15, 'W_SETTINGS_PDA', NULL, 'PAD升级', 'list', 'PAD升级', 5, 1, 0);
INSERT INTO `s_authority` VALUES (16, 'W_SETTINGS_ROLE', NULL, '角色设置', 'list', '角色设置', 5, 1, 0);
-- INSERT INTO `s_authority` VALUES (17, 'W_ELEVATOR_VIEW', NULL, '梯控视图', 'list', '梯控视图', 57, 1, 0);
-- INSERT INTO `s_authority` VALUES (19, 'W_ELEVATOR_LOG', NULL, '梯控日志', 'list', '梯控日志', 4, 1, 0);
-- INSERT INTO `s_authority` VALUES (20, 'W_ELEVATOR_LINE_LOG', NULL, '线体日志', 'list', '线体日志', 4, 1, 0);
-- INSERT INTO `s_authority` VALUES (21, 'W_STORE', NULL, '监控管理', 'list', '监控管理', 0, 1, 0);
-- INSERT INTO `s_authority` VALUES (22, 'W_STORE_VIEW', NULL, '现场监控', 'list', '现场监控', 21, 1, 0);
INSERT INTO `s_authority` VALUES (23, 'W_OPERATION_SCHEDUL', NULL, '运维调度', 'list', '运维调度', 0, 1, 0);
INSERT INTO `s_authority` VALUES (24, 'W_OPERATION_BIND_POD', NULL, '货架绑定', 'list', '货架绑定', 23, 1, 0);
INSERT INTO `s_authority` VALUES (25, 'W_MAP_DATA', NULL, '地图数据', 'list', '地图数据', 1, 1, 0);
INSERT INTO `s_authority` VALUES (26, 'W_MAP_SET', NULL, '地图设置', 'list', '地图设置', 1, 1, 0);
INSERT INTO `s_authority` VALUES (29, 'W_MAP_DATA_LOCK', 0, '地图数据-锁定', NULL, '菜单管理', 25, 2, 1000);
INSERT INTO `s_authority` VALUES (30, 'W_MAP_DATA_UNLOCK', 0, '地图数据-解锁', NULL, '菜单管理', 25, 2, 1000);
INSERT INTO `s_authority` VALUES (31, 'W_MAP_DATA_UPDATE', 0, '地图数据-更多数据', NULL, '菜单管理', 25, 2, 1000);
INSERT INTO `s_authority` VALUES (32, 'W_MAP_SET_LOCK', 0, '地图设置-锁定', NULL, '菜单管理', 26, 2, 1000);
INSERT INTO `s_authority` VALUES (33, 'W_MAP_SET_UNLOCK', 0, '地图设置-解锁', NULL, '菜单管理', 26, 2, 1000);
INSERT INTO `s_authority` VALUES (34, 'W_MAP_SET_EDIT', 0, '地图设置-编辑', NULL, '菜单管理', 26, 2, 1000);
-- INSERT INTO `s_authority` VALUES (35, 'W_TASK_MODAL_NEW', 0, '任务模板-新增', NULL, '菜单管理', 27, 2, 1000);
-- INSERT INTO `s_authority` VALUES (36, 'W_TASK_MODAL_EDIT', 0, '任务模板-编辑', NULL, '菜单管理', 27, 2, 1000);
-- INSERT INTO `s_authority` VALUES (37, 'W_TASK_MODAL_DELETE', 0, '任务模板-删除', NULL, '菜单管理', 27, 2, 1000);
-- INSERT INTO `s_authority` VALUES (38, 'W_TASK_MODAL_GRAQHIC', 0, '任务模板-图形转化', NULL, '菜单管理', 27, 2, 1000);
-- INSERT INTO `s_authority` VALUES (39, 'W_TASK_MODAL_TEMPL_DELETE', 0, '任务模板列表-删除', NULL, '菜单管理', 27, 2, 1000);
-- INSERT INTO `s_authority` VALUES (40, 'W_TASK_MODAL_TEMPL_CONFIG', 0, '任务模板配置信息-保存', NULL, '菜单管理', 27, 2, 1000);
-- INSERT INTO `s_authority` VALUES (41, 'W_TASK_MODAL_CONFIG', 0, '任务模板-配置', NULL, '菜单管理', 27, 2, 1000);
-- INSERT INTO `s_authority` VALUES (42, 'W_SUB_TASK_MODAL_NEW', 0, '子任务类型-添加', NULL, '菜单管理', 51, 2, 1000);
-- INSERT INTO `s_authority` VALUES (43, 'W_SUB_TASK_MODAL_CONFIG', 0, '子任务类型-编辑', NULL, '菜单管理', 51, 2, 1000);
-- INSERT INTO `s_authority` VALUES (44, 'W_SUB_TASK_MODAL_DELETE', 0, '子任务类型-删除', NULL, '菜单管理', 51, 2, 1000);
INSERT INTO `s_authority` VALUES (45, 'W_MAIN_TASK_CONFIG', 0, '主任务管理-设置优先级', NULL, '菜单管理', 8, 2, 1000);
INSERT INTO `s_authority` VALUES (46, 'W_POD_INFO_LOCK', 0, '货架信息-锁定', NULL, '菜单管理', 9, 2, 1000);
INSERT INTO `s_authority` VALUES (47, 'W_POD_INFO_UNLOCK', 0, '货架信息-解锁', NULL, '菜单管理', 9, 2, 1000);
-- INSERT INTO `s_authority` VALUES (48, 'W_MAP_COR', NULL, '关联管理', 'list', '关联管理', 1, 1, 0);
-- INSERT INTO `s_authority` VALUES (49, 'W_TASK_SET', NULL, '任务配置', 'list', '任务配置', 0, 1, 0);
-- INSERT INTO `s_authority` VALUES (50, 'W_MAIN_TEMPLATE', NULL, '主任务配置', 'list', '主任务配置', 49, 1, 0);
-- INSERT INTO `s_authority` VALUES (51, 'W_SUB_TEMPLATE', NULL, '子任务配置', 'list', '子任务配置', 49, 1, 0);
-- INSERT INTO `s_authority` VALUES (52, 'W_TEMPLATE', NULL, '任务模板', 'list', '任务模板', 49, 1, 0);
-- INSERT INTO `s_authority` VALUES (53, 'W_TEMPLATE_ACTION', NULL, '任务模板动作', 'list', '任务模板动作', 49, 1, 0);
-- INSERT INTO `s_authority` VALUES (54, 'W_STRATEGIC', NULL, '属性策略配置', 'list', '属性策略配置', 49, 1, 0);
-- INSERT INTO `s_authority` VALUES (55, 'W_SUB_TASK_ACTION', NULL, '子任务动作', 'list', '子任务动作', 2, 1, 0);
-- INSERT INTO `s_authority` VALUES (56, 'W_SUB_TASK_CONTEXT', NULL, '任务上下文', 'list', '任务上下文', 2, 1, 0);
-- INSERT INTO `s_authority` VALUES (57, 'W_EQUIPMENT', NULL, '设备管理', 'list', '设备管理', 0, 1, 0);
-- INSERT INTO `s_authority` VALUES (58, 'W_EQUIPMENT_DOOR', NULL, '自动门管理', 'list', '自动门管理', 57, 1, 0);
-- INSERT INTO `s_authority` VALUES (59, 'W_STORE_BOARD', NULL, '仓储看板', 'list', '仓储看板', 21, 1, 0);
-- INSERT INTO `s_authority` VALUES (60, 'W_STORE_DIMENSIONAL', NULL, '立库监控', 'list', '立库监控', 21, 1, 0);
-- INSERT INTO `s_authority` VALUES (61, 'W_OPERATION_AGV_TASK', NULL, '任务调度', 'list', '任务调度', 23, 1, 0);
INSERT INTO `s_authority` VALUES (62, 'W_SETTINGS_DIC', NULL, '字典管理', 'list', '字典管理', 5, 1, 0);
-- INSERT INTO `s_authority` VALUES (64, 'W_MAP_COR_POINT', 0, '地图数据-设置关联点', NULL, '菜单管理', 25, 2, 1000);
INSERT INTO `s_authority` VALUES (65, 'W_MAP_EDIT_POINT', 0, '地图数据-编辑点位编号', NULL, '菜单管理', 25, 2, 1000);
-- INSERT INTO `s_authority` VALUES (66, 'W_MAP_CONNECT_DEL', 0, '关联管理-删除', NULL, '菜单管理', 48, 2, 1000);
-- INSERT INTO `s_authority` VALUES (67, 'W_MAP_CONNECT_EDIT', 0, '关联管理-编辑', NULL, '菜单管理', 48, 2, 1000);
INSERT INTO `s_authority` VALUES (68, 'W_TASK_MAIN_ADD', 0, '主任务配置-新增', NULL, '菜单管理', 50, 2, 1000);
INSERT INTO `s_authority` VALUES (69, 'W_TASK_MAIN_DEL', 0, '主任务配置-删除', NULL, '菜单管理', 50, 2, 1000);
INSERT INTO `s_authority` VALUES (70, 'W_TASK_MAIN_EDIT', 0, '主任务配置-编辑', NULL, '菜单管理', 50, 2, 1000);
INSERT INTO `s_authority` VALUES (71, 'W_SUB_TASK_SAVE', 0, '子任务配置-保存', NULL, '菜单管理', 51, 2, 1000);
-- INSERT INTO `s_authority` VALUES (72, 'W_ACTION_ADD', 0, '任务模板动作-新增', NULL, '菜单管理', 53, 2, 1000);
-- INSERT INTO `s_authority` VALUES (73, 'W_ACTION_EDIT', 0, '任务模板动作-编辑', NULL, '菜单管理', 53, 2, 1000);
-- INSERT INTO `s_authority` VALUES (74, 'W_ACTION_DEL', 0, '任务模板动作-删除', NULL, '菜单管理', 53, 2, 1000);
-- INSERT INTO `s_authority` VALUES (75, 'W_ACTION_SAVE', 0, '任务模板动作-保存', NULL, '菜单管理', 53, 2, 1000);
-- INSERT INTO `s_authority` VALUES (76, 'W_STRATEGIC_ADD', 0, '属性策略配置-新增', NULL, '菜单管理', 54, 2, 1000);
-- INSERT INTO `s_authority` VALUES (77, 'W_STRATEGIC_EDIT', 0, '属性策略配置-编辑', NULL, '菜单管理', 54, 2, 1000);
-- INSERT INTO `s_authority` VALUES (78, 'W_STRATEGIC_DEL', 0, '属性策略配置-删除', NULL, '菜单管理', 54, 2, 1000);
INSERT INTO `s_authority` VALUES (79, 'W_CANCEL_TASK', 0, '主任务管理-取消任务', NULL, '菜单管理', 8, 2, 1000);
-- INSERT INTO `s_authority` VALUES (80, 'W_CONTENT_EDIT', 0, '任务上下文-编辑', NULL, '菜单管理', 56, 2, 1000);
-- INSERT INTO `s_authority` VALUES (81, 'W_CONTENT_DEL', 0, '任务上下文-删除', NULL, '菜单管理', 56, 2, 1000);
-- INSERT INTO `s_authority` VALUES (82, 'W_DOOR_CANCEL_TASK', 0, '自动门管理-取消任务', NULL, '菜单管理', 58, 2, 1000);
-- INSERT INTO `s_authority` VALUES (83, 'W_AGV_GET', 0, '梯控管理-AGV接货', NULL, '菜单管理', 17, 2, 1000);
-- INSERT INTO `s_authority` VALUES (84, 'W_AGV_SEND', 0, '梯控管理-AGV送货', NULL, '菜单管理', 17, 2, 1000);

-- INSERT INTO `s_authority` VALUES (85, 'W_BIN_CODE', NULL, '仓位管理', 'list', '仓位管理', 0, 1, 0);
-- INSERT INTO `s_authority` VALUES (86, 'W_BIN_CODE_DATA', NULL, '仓位数据', 'list', '仓位数据', 85, 1, 0);

-- 梯控系统
-- INSERT INTO `s_authority` VALUES (87, 'W_ELEVATOR', NULL, '梯控系统', 'list', '梯控系统', 0, 1, 0);
-- INSERT INTO `s_authority` VALUES (88, 'W_ELEVATOR_DATA', NULL, '电梯数据', 'list', '电梯数据', 87, 1, 0);
-- INSERT INTO `s_authority` VALUES (89, 'W_ELEVATOR_TASK', NULL, '电梯任务', 'list', '电梯任务', 87, 1, 0);
INSERT INTO `s_authority` VALUES (90, 'W_ELEVATOR_TASK_ORDER', NULL, '任务单据', 'list', '任务单据', 87, 1, 0);
INSERT INTO `s_authority` VALUES (91, 'W_ELEVATOR_TASK_PROCESS', NULL, '任务线程', 'list', '任务线程', 87, 1, 0);
-- INSERT INTO `s_authority` VALUES (92, 'W_ELEVATOR_ABNORMAL', NULL, '异常信息', 'list', '异常信息', 87, 1, 0);
-- INSERT INTO `s_authority` VALUES (93, 'W_ELEVATOR_RELATION', NULL, '接驳关联点', 'list', '接驳关联点', 87, 1, 0);
-- INSERT INTO `s_authority` VALUES (94, 'W_ELEVATOR_RELATION_ADD', 0, '接驳关联点-新增', null, '菜单管理', 93, 2, 1000);
-- INSERT INTO `s_authority` VALUES (95, 'W_ELEVATOR_RELATION_EDIT', 0, '接驳关联点-编辑', null, '菜单管理', 93, 2, 1000);
-- INSERT INTO `s_authority` VALUES (96, 'W_ELEVATOR_RELATION_DEL', 0, '接驳关联点-删除', null, '菜单管理', 93, 2, 1000);