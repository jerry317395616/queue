-- 注意：该页面对应的前台目录为views/device文件夹下
-- 如果你想更改到其他目录，请修改sql中component字段对应的值


INSERT INTO sys_permission(id, parent_id, name, url, component, component_name, redirect, menu_type, perms, perms_type, sort_no, always_show, icon, is_route, is_leaf, keep_alive, hidden, hide_tab, description, status, del_flag, rule_flag, create_by, create_time, update_by, update_time, internal_or_external) 
VALUES ('2024092310389060290', NULL, '医生叫号器', '/device/doctorDeviceList', 'device/DoctorDeviceList', NULL, NULL, 0, NULL, '1', 0.00, 0, NULL, 1, 0, 0, 0, 0, NULL, '1', 0, 0, 'admin', '2024-09-23 10:38:29', NULL, NULL, 0);

-- 权限控制sql
-- 新增
INSERT INTO sys_permission(id, parent_id, name, url, component, is_route, component_name, redirect, menu_type, perms, perms_type, sort_no, always_show, icon, is_leaf, keep_alive, hidden, hide_tab, description, create_by, create_time, update_by, update_time, del_flag, rule_flag, status, internal_or_external)
VALUES ('2024092310389060291', '2024092310389060290', '添加医生叫号器', NULL, NULL, 0, NULL, NULL, 2, 'device:doctor_device:add', '1', NULL, 0, NULL, 1, 0, 0, 0, NULL, 'admin', '2024-09-23 10:38:29', NULL, NULL, 0, 0, '1', 0);
-- 编辑
INSERT INTO sys_permission(id, parent_id, name, url, component, is_route, component_name, redirect, menu_type, perms, perms_type, sort_no, always_show, icon, is_leaf, keep_alive, hidden, hide_tab, description, create_by, create_time, update_by, update_time, del_flag, rule_flag, status, internal_or_external)
VALUES ('2024092310389060292', '2024092310389060290', '编辑医生叫号器', NULL, NULL, 0, NULL, NULL, 2, 'device:doctor_device:edit', '1', NULL, 0, NULL, 1, 0, 0, 0, NULL, 'admin', '2024-09-23 10:38:29', NULL, NULL, 0, 0, '1', 0);
-- 删除
INSERT INTO sys_permission(id, parent_id, name, url, component, is_route, component_name, redirect, menu_type, perms, perms_type, sort_no, always_show, icon, is_leaf, keep_alive, hidden, hide_tab, description, create_by, create_time, update_by, update_time, del_flag, rule_flag, status, internal_or_external)
VALUES ('2024092310389060293', '2024092310389060290', '删除医生叫号器', NULL, NULL, 0, NULL, NULL, 2, 'device:doctor_device:delete', '1', NULL, 0, NULL, 1, 0, 0, 0, NULL, 'admin', '2024-09-23 10:38:29', NULL, NULL, 0, 0, '1', 0);
-- 批量删除
INSERT INTO sys_permission(id, parent_id, name, url, component, is_route, component_name, redirect, menu_type, perms, perms_type, sort_no, always_show, icon, is_leaf, keep_alive, hidden, hide_tab, description, create_by, create_time, update_by, update_time, del_flag, rule_flag, status, internal_or_external)
VALUES ('2024092310389060294', '2024092310389060290', '批量删除医生叫号器', NULL, NULL, 0, NULL, NULL, 2, 'device:doctor_device:deleteBatch', '1', NULL, 0, NULL, 1, 0, 0, 0, NULL, 'admin', '2024-09-23 10:38:29', NULL, NULL, 0, 0, '1', 0);
-- 导出excel
INSERT INTO sys_permission(id, parent_id, name, url, component, is_route, component_name, redirect, menu_type, perms, perms_type, sort_no, always_show, icon, is_leaf, keep_alive, hidden, hide_tab, description, create_by, create_time, update_by, update_time, del_flag, rule_flag, status, internal_or_external)
VALUES ('2024092310389060295', '2024092310389060290', '导出excel_医生叫号器', NULL, NULL, 0, NULL, NULL, 2, 'device:doctor_device:exportXls', '1', NULL, 0, NULL, 1, 0, 0, 0, NULL, 'admin', '2024-09-23 10:38:29', NULL, NULL, 0, 0, '1', 0);
-- 导入excel
INSERT INTO sys_permission(id, parent_id, name, url, component, is_route, component_name, redirect, menu_type, perms, perms_type, sort_no, always_show, icon, is_leaf, keep_alive, hidden, hide_tab, description, create_by, create_time, update_by, update_time, del_flag, rule_flag, status, internal_or_external)
VALUES ('2024092310389060296', '2024092310389060290', '导入excel_医生叫号器', NULL, NULL, 0, NULL, NULL, 2, 'device:doctor_device:importExcel', '1', NULL, 0, NULL, 1, 0, 0, 0, NULL, 'admin', '2024-09-23 10:38:29', NULL, NULL, 0, 0, '1', 0);