流程信息文件夹
日志表：
1. act_evt_log                        存储事件处理日志，方便管理员跟踪处理。
通用数据表
2. act_ge_bytearray               二进制数据表，一些文件存在这个表。
3. act_ge_property                属性数据表存储整个流程引擎级别的数据,初始化表结构时，会默认插入三条记录
历史数据表
4. act_hi_actinst                    历史节点表
5. act_hi_attachment             历史附件表
6. act_hi_comment                历史意见表
7. act_hi_detail                      历史详情表，提供历史变量的查询
8. act_hi_identitylink              历史流程人员表
9. act_hi_procinst                  历史流程实例表
10.act_hi_taskinst                 历史任务实例表
11.act_hi_varinst                   历史变量表
用户组织表
12.act_id_group                    用户组信息表
13.act_id_info                       用户扩展信息表
14.act_id_membership         用户与用户组对应信息表
15.act_id_user                      用户信息表
资源流程规则表
16.act_procdef_info              流程定义信息
17.act_re_deployment          部署信息表
18.act_re_model                   流程设计模型部署表
19.act_re_procdef                 流程定义数据表
运行时数据库表
20.act_ru_event_subscr       监听表
21.act_ru_execution             运行时流程执行实例表
22.act_ru_identitylink           运行时流程人员表，主要存储任务节点与参与者的相关信息
23.act_ru_job                       运行时定时任务数据表
24.act_ru_task                     运行时任务节点表
25.act_ru_variable               运行时流程变量数据表