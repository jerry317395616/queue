-- 注意：该页面对应的前台目录为views/screen文件夹下
-- 如果你想更改到其他目录，请修改sql中component字段对应的值


INSERT INTO sys_permission(id, parent_id, name, url, component, component_name, redirect, menu_type, perms, perms_type, sort_no, always_show, icon, is_route, is_leaf, keep_alive, hidden, hide_tab, description, status, del_flag, rule_flag, create_by, create_time, update_by, update_time, internal_or_external) 
VALUES ('2024100502402750550', NULL, '显示屏配置', '/screen/displayScreensList', 'screen/DisplayScreensList', NULL, NULL, 0, NULL, '1', 0.00, 0, NULL, 1, 0, 0, 0, 0, NULL, '1', 0, 0, 'admin', '2024-10-05 14:40:55', NULL, NULL, 0);

-- 权限控制sql
-- 新增
INSERT INTO sys_permission(id, parent_id, name, url, component, is_route, component_name, redirect, menu_type, perms, perms_type, sort_no, always_show, icon, is_leaf, keep_alive, hidden, hide_tab, description, create_by, create_time, update_by, update_time, del_flag, rule_flag, status, internal_or_external)
VALUES ('2024100502402760551', '2024100502402750550', '添加显示屏配置', NULL, NULL, 0, NULL, NULL, 2, 'screen:display_screens:add', '1', NULL, 0, NULL, 1, 0, 0, 0, NULL, 'admin', '2024-10-05 14:40:55', NULL, NULL, 0, 0, '1', 0);
-- 编辑
INSERT INTO sys_permission(id, parent_id, name, url, component, is_route, component_name, redirect, menu_type, perms, perms_type, sort_no, always_show, icon, is_leaf, keep_alive, hidden, hide_tab, description, create_by, create_time, update_by, update_time, del_flag, rule_flag, status, internal_or_external)
VALUES ('2024100502402760552', '2024100502402750550', '编辑显示屏配置', NULL, NULL, 0, NULL, NULL, 2, 'screen:display_screens:edit', '1', NULL, 0, NULL, 1, 0, 0, 0, NULL, 'admin', '2024-10-05 14:40:55', NULL, NULL, 0, 0, '1', 0);
-- 删除
INSERT INTO sys_permission(id, parent_id, name, url, component, is_route, component_name, redirect, menu_type, perms, perms_type, sort_no, always_show, icon, is_leaf, keep_alive, hidden, hide_tab, description, create_by, create_time, update_by, update_time, del_flag, rule_flag, status, internal_or_external)
VALUES ('2024100502402760553', '2024100502402750550', '删除显示屏配置', NULL, NULL, 0, NULL, NULL, 2, 'screen:display_screens:delete', '1', NULL, 0, NULL, 1, 0, 0, 0, NULL, 'admin', '2024-10-05 14:40:55', NULL, NULL, 0, 0, '1', 0);
-- 批量删除
INSERT INTO sys_permission(id, parent_id, name, url, component, is_route, component_name, redirect, menu_type, perms, perms_type, sort_no, always_show, icon, is_leaf, keep_alive, hidden, hide_tab, description, create_by, create_time, update_by, update_time, del_flag, rule_flag, status, internal_or_external)
VALUES ('2024100502402760554', '2024100502402750550', '批量删除显示屏配置', NULL, NULL, 0, NULL, NULL, 2, 'screen:display_screens:deleteBatch', '1', NULL, 0, NULL, 1, 0, 0, 0, NULL, 'admin', '2024-10-05 14:40:55', NULL, NULL, 0, 0, '1', 0);
-- 导出excel
INSERT INTO sys_permission(id, parent_id, name, url, component, is_route, component_name, redirect, menu_type, perms, perms_type, sort_no, always_show, icon, is_leaf, keep_alive, hidden, hide_tab, description, create_by, create_time, update_by, update_time, del_flag, rule_flag, status, internal_or_external)
VALUES ('2024100502402760555', '2024100502402750550', '导出excel_显示屏配置', NULL, NULL, 0, NULL, NULL, 2, 'screen:display_screens:exportXls', '1', NULL, 0, NULL, 1, 0, 0, 0, NULL, 'admin', '2024-10-05 14:40:55', NULL, NULL, 0, 0, '1', 0);
-- 导入excel
INSERT INTO sys_permission(id, parent_id, name, url, component, is_route, component_name, redirect, menu_type, perms, perms_type, sort_no, always_show, icon, is_leaf, keep_alive, hidden, hide_tab, description, create_by, create_time, update_by, update_time, del_flag, rule_flag, status, internal_or_external)
VALUES ('2024100502402760556', '2024100502402750550', '导入excel_显示屏配置', NULL, NULL, 0, NULL, NULL, 2, 'screen:display_screens:importExcel', '1', NULL, 0, NULL, 1, 0, 0, 0, NULL, 'admin', '2024-10-05 14:40:55', NULL, NULL, 0, 0, '1', 0);