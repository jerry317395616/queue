-- 注意：该页面对应的前台目录为views/check文件夹下
-- 如果你想更改到其他目录，请修改sql中component字段对应的值


INSERT INTO sys_permission(id, parent_id, name, url, component, component_name, redirect, menu_type, perms, perms_type, sort_no, always_show, icon, is_route, is_leaf, keep_alive, hidden, hide_tab, description, status, del_flag, rule_flag, create_by, create_time, update_by, update_time, internal_or_external) 
VALUES ('2024100504151710590', NULL, '签到机', '/check/checkMachineList', 'check/CheckMachineList', NULL, NULL, 0, NULL, '1', 0.00, 0, NULL, 1, 0, 0, 0, 0, NULL, '1', 0, 0, 'admin', '2024-10-05 16:15:59', NULL, NULL, 0);

-- 权限控制sql
-- 新增
INSERT INTO sys_permission(id, parent_id, name, url, component, is_route, component_name, redirect, menu_type, perms, perms_type, sort_no, always_show, icon, is_leaf, keep_alive, hidden, hide_tab, description, create_by, create_time, update_by, update_time, del_flag, rule_flag, status, internal_or_external)
VALUES ('2024100504151710591', '2024100504151710590', '添加签到机', NULL, NULL, 0, NULL, NULL, 2, 'check:check_machine:add', '1', NULL, 0, NULL, 1, 0, 0, 0, NULL, 'admin', '2024-10-05 16:15:59', NULL, NULL, 0, 0, '1', 0);
-- 编辑
INSERT INTO sys_permission(id, parent_id, name, url, component, is_route, component_name, redirect, menu_type, perms, perms_type, sort_no, always_show, icon, is_leaf, keep_alive, hidden, hide_tab, description, create_by, create_time, update_by, update_time, del_flag, rule_flag, status, internal_or_external)
VALUES ('2024100504151710592', '2024100504151710590', '编辑签到机', NULL, NULL, 0, NULL, NULL, 2, 'check:check_machine:edit', '1', NULL, 0, NULL, 1, 0, 0, 0, NULL, 'admin', '2024-10-05 16:15:59', NULL, NULL, 0, 0, '1', 0);
-- 删除
INSERT INTO sys_permission(id, parent_id, name, url, component, is_route, component_name, redirect, menu_type, perms, perms_type, sort_no, always_show, icon, is_leaf, keep_alive, hidden, hide_tab, description, create_by, create_time, update_by, update_time, del_flag, rule_flag, status, internal_or_external)
VALUES ('2024100504151710593', '2024100504151710590', '删除签到机', NULL, NULL, 0, NULL, NULL, 2, 'check:check_machine:delete', '1', NULL, 0, NULL, 1, 0, 0, 0, NULL, 'admin', '2024-10-05 16:15:59', NULL, NULL, 0, 0, '1', 0);
-- 批量删除
INSERT INTO sys_permission(id, parent_id, name, url, component, is_route, component_name, redirect, menu_type, perms, perms_type, sort_no, always_show, icon, is_leaf, keep_alive, hidden, hide_tab, description, create_by, create_time, update_by, update_time, del_flag, rule_flag, status, internal_or_external)
VALUES ('2024100504151710594', '2024100504151710590', '批量删除签到机', NULL, NULL, 0, NULL, NULL, 2, 'check:check_machine:deleteBatch', '1', NULL, 0, NULL, 1, 0, 0, 0, NULL, 'admin', '2024-10-05 16:15:59', NULL, NULL, 0, 0, '1', 0);
-- 导出excel
INSERT INTO sys_permission(id, parent_id, name, url, component, is_route, component_name, redirect, menu_type, perms, perms_type, sort_no, always_show, icon, is_leaf, keep_alive, hidden, hide_tab, description, create_by, create_time, update_by, update_time, del_flag, rule_flag, status, internal_or_external)
VALUES ('2024100504151710595', '2024100504151710590', '导出excel_签到机', NULL, NULL, 0, NULL, NULL, 2, 'check:check_machine:exportXls', '1', NULL, 0, NULL, 1, 0, 0, 0, NULL, 'admin', '2024-10-05 16:15:59', NULL, NULL, 0, 0, '1', 0);
-- 导入excel
INSERT INTO sys_permission(id, parent_id, name, url, component, is_route, component_name, redirect, menu_type, perms, perms_type, sort_no, always_show, icon, is_leaf, keep_alive, hidden, hide_tab, description, create_by, create_time, update_by, update_time, del_flag, rule_flag, status, internal_or_external)
VALUES ('2024100504151710596', '2024100504151710590', '导入excel_签到机', NULL, NULL, 0, NULL, NULL, 2, 'check:check_machine:importExcel', '1', NULL, 0, NULL, 1, 0, 0, 0, NULL, 'admin', '2024-10-05 16:15:59', NULL, NULL, 0, 0, '1', 0);