-- 注意：该页面对应的前台目录为views/dept文件夹下
-- 如果你想更改到其他目录，请修改sql中component字段对应的值


INSERT INTO sys_permission(id, parent_id, name, url, component, component_name, redirect, menu_type, perms, perms_type, sort_no, always_show, icon, is_route, is_leaf, keep_alive, hidden, hide_tab, description, status, del_flag, rule_flag, create_by, create_time, update_by, update_time, internal_or_external) 
VALUES ('2024090812126670060', NULL, '诊区科室', '/dept/clinicAreaDeptList', 'dept/ClinicAreaDeptList', NULL, NULL, 0, NULL, '1', 0.00, 0, NULL, 1, 0, 0, 0, 0, NULL, '1', 0, 0, 'admin', '2024-09-08 00:12:06', NULL, NULL, 0);

-- 权限控制sql
-- 新增
INSERT INTO sys_permission(id, parent_id, name, url, component, is_route, component_name, redirect, menu_type, perms, perms_type, sort_no, always_show, icon, is_leaf, keep_alive, hidden, hide_tab, description, create_by, create_time, update_by, update_time, del_flag, rule_flag, status, internal_or_external)
VALUES ('2024090812126670061', '2024090812126670060', '添加诊区科室', NULL, NULL, 0, NULL, NULL, 2, 'dept:clinic_area_dept:add', '1', NULL, 0, NULL, 1, 0, 0, 0, NULL, 'admin', '2024-09-08 00:12:06', NULL, NULL, 0, 0, '1', 0);
-- 编辑
INSERT INTO sys_permission(id, parent_id, name, url, component, is_route, component_name, redirect, menu_type, perms, perms_type, sort_no, always_show, icon, is_leaf, keep_alive, hidden, hide_tab, description, create_by, create_time, update_by, update_time, del_flag, rule_flag, status, internal_or_external)
VALUES ('2024090812126670062', '2024090812126670060', '编辑诊区科室', NULL, NULL, 0, NULL, NULL, 2, 'dept:clinic_area_dept:edit', '1', NULL, 0, NULL, 1, 0, 0, 0, NULL, 'admin', '2024-09-08 00:12:06', NULL, NULL, 0, 0, '1', 0);
-- 删除
INSERT INTO sys_permission(id, parent_id, name, url, component, is_route, component_name, redirect, menu_type, perms, perms_type, sort_no, always_show, icon, is_leaf, keep_alive, hidden, hide_tab, description, create_by, create_time, update_by, update_time, del_flag, rule_flag, status, internal_or_external)
VALUES ('2024090812126670063', '2024090812126670060', '删除诊区科室', NULL, NULL, 0, NULL, NULL, 2, 'dept:clinic_area_dept:delete', '1', NULL, 0, NULL, 1, 0, 0, 0, NULL, 'admin', '2024-09-08 00:12:06', NULL, NULL, 0, 0, '1', 0);
-- 批量删除
INSERT INTO sys_permission(id, parent_id, name, url, component, is_route, component_name, redirect, menu_type, perms, perms_type, sort_no, always_show, icon, is_leaf, keep_alive, hidden, hide_tab, description, create_by, create_time, update_by, update_time, del_flag, rule_flag, status, internal_or_external)
VALUES ('2024090812126670064', '2024090812126670060', '批量删除诊区科室', NULL, NULL, 0, NULL, NULL, 2, 'dept:clinic_area_dept:deleteBatch', '1', NULL, 0, NULL, 1, 0, 0, 0, NULL, 'admin', '2024-09-08 00:12:06', NULL, NULL, 0, 0, '1', 0);
-- 导出excel
INSERT INTO sys_permission(id, parent_id, name, url, component, is_route, component_name, redirect, menu_type, perms, perms_type, sort_no, always_show, icon, is_leaf, keep_alive, hidden, hide_tab, description, create_by, create_time, update_by, update_time, del_flag, rule_flag, status, internal_or_external)
VALUES ('2024090812126670065', '2024090812126670060', '导出excel_诊区科室', NULL, NULL, 0, NULL, NULL, 2, 'dept:clinic_area_dept:exportXls', '1', NULL, 0, NULL, 1, 0, 0, 0, NULL, 'admin', '2024-09-08 00:12:06', NULL, NULL, 0, 0, '1', 0);
-- 导入excel
INSERT INTO sys_permission(id, parent_id, name, url, component, is_route, component_name, redirect, menu_type, perms, perms_type, sort_no, always_show, icon, is_leaf, keep_alive, hidden, hide_tab, description, create_by, create_time, update_by, update_time, del_flag, rule_flag, status, internal_or_external)
VALUES ('2024090812126670066', '2024090812126670060', '导入excel_诊区科室', NULL, NULL, 0, NULL, NULL, 2, 'dept:clinic_area_dept:importExcel', '1', NULL, 0, NULL, 1, 0, 0, 0, NULL, 'admin', '2024-09-08 00:12:06', NULL, NULL, 0, 0, '1', 0);