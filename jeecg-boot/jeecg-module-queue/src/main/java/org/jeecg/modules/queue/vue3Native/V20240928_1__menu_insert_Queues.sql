-- 注意：该页面对应的前台目录为views/queue文件夹下
-- 如果你想更改到其他目录，请修改sql中component字段对应的值


INSERT INTO sys_permission(id, parent_id, name, url, component, component_name, redirect, menu_type, perms, perms_type, sort_no, always_show, icon, is_route, is_leaf, keep_alive, hidden, hide_tab, description, status, del_flag, rule_flag, create_by, create_time, update_by, update_time, internal_or_external) 
VALUES ('2024092810506330200', NULL, '队列管理', '/queue/queuesList', 'queue/QueuesList', NULL, NULL, 0, NULL, '1', 0.00, 0, NULL, 1, 0, 0, 0, 0, NULL, '1', 0, 0, 'admin', '2024-09-28 10:50:20', NULL, NULL, 0);

-- 权限控制sql
-- 新增
INSERT INTO sys_permission(id, parent_id, name, url, component, is_route, component_name, redirect, menu_type, perms, perms_type, sort_no, always_show, icon, is_leaf, keep_alive, hidden, hide_tab, description, create_by, create_time, update_by, update_time, del_flag, rule_flag, status, internal_or_external)
VALUES ('2024092810506340201', '2024092810506330200', '添加队列管理', NULL, NULL, 0, NULL, NULL, 2, 'queue:queues:add', '1', NULL, 0, NULL, 1, 0, 0, 0, NULL, 'admin', '2024-09-28 10:50:20', NULL, NULL, 0, 0, '1', 0);
-- 编辑
INSERT INTO sys_permission(id, parent_id, name, url, component, is_route, component_name, redirect, menu_type, perms, perms_type, sort_no, always_show, icon, is_leaf, keep_alive, hidden, hide_tab, description, create_by, create_time, update_by, update_time, del_flag, rule_flag, status, internal_or_external)
VALUES ('2024092810506340202', '2024092810506330200', '编辑队列管理', NULL, NULL, 0, NULL, NULL, 2, 'queue:queues:edit', '1', NULL, 0, NULL, 1, 0, 0, 0, NULL, 'admin', '2024-09-28 10:50:20', NULL, NULL, 0, 0, '1', 0);
-- 删除
INSERT INTO sys_permission(id, parent_id, name, url, component, is_route, component_name, redirect, menu_type, perms, perms_type, sort_no, always_show, icon, is_leaf, keep_alive, hidden, hide_tab, description, create_by, create_time, update_by, update_time, del_flag, rule_flag, status, internal_or_external)
VALUES ('2024092810506340203', '2024092810506330200', '删除队列管理', NULL, NULL, 0, NULL, NULL, 2, 'queue:queues:delete', '1', NULL, 0, NULL, 1, 0, 0, 0, NULL, 'admin', '2024-09-28 10:50:20', NULL, NULL, 0, 0, '1', 0);
-- 批量删除
INSERT INTO sys_permission(id, parent_id, name, url, component, is_route, component_name, redirect, menu_type, perms, perms_type, sort_no, always_show, icon, is_leaf, keep_alive, hidden, hide_tab, description, create_by, create_time, update_by, update_time, del_flag, rule_flag, status, internal_or_external)
VALUES ('2024092810506340204', '2024092810506330200', '批量删除队列管理', NULL, NULL, 0, NULL, NULL, 2, 'queue:queues:deleteBatch', '1', NULL, 0, NULL, 1, 0, 0, 0, NULL, 'admin', '2024-09-28 10:50:20', NULL, NULL, 0, 0, '1', 0);
-- 导出excel
INSERT INTO sys_permission(id, parent_id, name, url, component, is_route, component_name, redirect, menu_type, perms, perms_type, sort_no, always_show, icon, is_leaf, keep_alive, hidden, hide_tab, description, create_by, create_time, update_by, update_time, del_flag, rule_flag, status, internal_or_external)
VALUES ('2024092810506340205', '2024092810506330200', '导出excel_队列管理', NULL, NULL, 0, NULL, NULL, 2, 'queue:queues:exportXls', '1', NULL, 0, NULL, 1, 0, 0, 0, NULL, 'admin', '2024-09-28 10:50:20', NULL, NULL, 0, 0, '1', 0);
-- 导入excel
INSERT INTO sys_permission(id, parent_id, name, url, component, is_route, component_name, redirect, menu_type, perms, perms_type, sort_no, always_show, icon, is_leaf, keep_alive, hidden, hide_tab, description, create_by, create_time, update_by, update_time, del_flag, rule_flag, status, internal_or_external)
VALUES ('2024092810506340206', '2024092810506330200', '导入excel_队列管理', NULL, NULL, 0, NULL, NULL, 2, 'queue:queues:importExcel', '1', NULL, 0, NULL, 1, 0, 0, 0, NULL, 'admin', '2024-09-28 10:50:20', NULL, NULL, 0, 0, '1', 0);