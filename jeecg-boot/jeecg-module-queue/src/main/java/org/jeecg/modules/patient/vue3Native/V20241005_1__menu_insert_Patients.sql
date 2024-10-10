-- 注意：该页面对应的前台目录为views/patient文件夹下
-- 如果你想更改到其他目录，请修改sql中component字段对应的值


INSERT INTO sys_permission(id, parent_id, name, url, component, component_name, redirect, menu_type, perms, perms_type, sort_no, always_show, icon, is_route, is_leaf, keep_alive, hidden, hide_tab, description, status, del_flag, rule_flag, create_by, create_time, update_by, update_time, internal_or_external) 
VALUES ('202410051057120210', NULL, '患者信息', '/patient/patientsList', 'patient/PatientsList', NULL, NULL, 0, NULL, '1', 0.00, 0, NULL, 1, 0, 0, 0, 0, NULL, '1', 0, 0, 'admin', '2024-10-05 10:57:21', NULL, NULL, 0);

-- 权限控制sql
-- 新增
INSERT INTO sys_permission(id, parent_id, name, url, component, is_route, component_name, redirect, menu_type, perms, perms_type, sort_no, always_show, icon, is_leaf, keep_alive, hidden, hide_tab, description, create_by, create_time, update_by, update_time, del_flag, rule_flag, status, internal_or_external)
VALUES ('202410051057120211', '202410051057120210', '添加患者信息', NULL, NULL, 0, NULL, NULL, 2, 'patient:patients:add', '1', NULL, 0, NULL, 1, 0, 0, 0, NULL, 'admin', '2024-10-05 10:57:21', NULL, NULL, 0, 0, '1', 0);
-- 编辑
INSERT INTO sys_permission(id, parent_id, name, url, component, is_route, component_name, redirect, menu_type, perms, perms_type, sort_no, always_show, icon, is_leaf, keep_alive, hidden, hide_tab, description, create_by, create_time, update_by, update_time, del_flag, rule_flag, status, internal_or_external)
VALUES ('202410051057120212', '202410051057120210', '编辑患者信息', NULL, NULL, 0, NULL, NULL, 2, 'patient:patients:edit', '1', NULL, 0, NULL, 1, 0, 0, 0, NULL, 'admin', '2024-10-05 10:57:21', NULL, NULL, 0, 0, '1', 0);
-- 删除
INSERT INTO sys_permission(id, parent_id, name, url, component, is_route, component_name, redirect, menu_type, perms, perms_type, sort_no, always_show, icon, is_leaf, keep_alive, hidden, hide_tab, description, create_by, create_time, update_by, update_time, del_flag, rule_flag, status, internal_or_external)
VALUES ('202410051057120213', '202410051057120210', '删除患者信息', NULL, NULL, 0, NULL, NULL, 2, 'patient:patients:delete', '1', NULL, 0, NULL, 1, 0, 0, 0, NULL, 'admin', '2024-10-05 10:57:21', NULL, NULL, 0, 0, '1', 0);
-- 批量删除
INSERT INTO sys_permission(id, parent_id, name, url, component, is_route, component_name, redirect, menu_type, perms, perms_type, sort_no, always_show, icon, is_leaf, keep_alive, hidden, hide_tab, description, create_by, create_time, update_by, update_time, del_flag, rule_flag, status, internal_or_external)
VALUES ('202410051057120214', '202410051057120210', '批量删除患者信息', NULL, NULL, 0, NULL, NULL, 2, 'patient:patients:deleteBatch', '1', NULL, 0, NULL, 1, 0, 0, 0, NULL, 'admin', '2024-10-05 10:57:21', NULL, NULL, 0, 0, '1', 0);
-- 导出excel
INSERT INTO sys_permission(id, parent_id, name, url, component, is_route, component_name, redirect, menu_type, perms, perms_type, sort_no, always_show, icon, is_leaf, keep_alive, hidden, hide_tab, description, create_by, create_time, update_by, update_time, del_flag, rule_flag, status, internal_or_external)
VALUES ('202410051057120215', '202410051057120210', '导出excel_患者信息', NULL, NULL, 0, NULL, NULL, 2, 'patient:patients:exportXls', '1', NULL, 0, NULL, 1, 0, 0, 0, NULL, 'admin', '2024-10-05 10:57:21', NULL, NULL, 0, 0, '1', 0);
-- 导入excel
INSERT INTO sys_permission(id, parent_id, name, url, component, is_route, component_name, redirect, menu_type, perms, perms_type, sort_no, always_show, icon, is_leaf, keep_alive, hidden, hide_tab, description, create_by, create_time, update_by, update_time, del_flag, rule_flag, status, internal_or_external)
VALUES ('202410051057120216', '202410051057120210', '导入excel_患者信息', NULL, NULL, 0, NULL, NULL, 2, 'patient:patients:importExcel', '1', NULL, 0, NULL, 1, 0, 0, 0, NULL, 'admin', '2024-10-05 10:57:21', NULL, NULL, 0, 0, '1', 0);