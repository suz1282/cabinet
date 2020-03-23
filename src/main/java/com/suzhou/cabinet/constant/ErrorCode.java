package com.suzhou.cabinet.constant;

/**
 * 错误代码枚举
 * @author leo
 * @since 2018/7/27 8:49
 */
public enum ErrorCode {
    NOT_REFRESH_POINT("not_refresh_point", "未刷新测点倍率"),

    /**
     * JSON格式错误
     */
    JSON_ILEEGAL_FORMAT("json_illegal_format", "JSON格式错误，请检查请求参数的格式是否符合标准的JSOn格式"),

    /**
     * token不存在
     */
    JWT_NOT_EXIST("jwt_not_exist", "token不存在"),
    /**
     * 不是合法的jwt token
     */
    JWT_ILLEGAL_TOKEN("jwt_illegal_token", "非法的token"),
    /**
     * Jwt token超时
     */
    JWT_TOKEN_EXPIRED("jwt_token_expired", "token超时"),

    /**
     * 切换上一级组织失败，上一级组织不完全包含当前组织的所有区域
     */
    SYS_MOD_PARENT_ID_ERROR("sys_mod_parent_id_error", "切换上一级组织失败，上一级组织不完全包含当前组织的所有区域"),

    /**
     * 删除区域分组失败，子组织含有被删除的区域，请先操作自组织！
     */
    SYS_DEL_REGION_ERROR("sys_del_region_error", "删除区域分组失败，子组织含有被删除的区域，请先操作自组织！"),

    /**
     * 修改区域分组失败，子组织含有被删除的区域，请先操作自组织！
     */
    SYS_MOD_REGION_ERROR("sys_mod_region_error", "修改区域分组失败，子组织含有被删除的区域，请先操作自组织！"),

    /**
     * 非法参数
     */
    EXCEPTION_ILLEGAL_ARGUMENT("illegal_argument", "非法参数"),

    /**
     * 非法状态
     */
    EXCEPTION_ILLEGAL_STATE("illegal_state", "非法状态"),

    /**
     * 用户不存在
     */
    USER_NOT_EXIST("user_not_exist", "用户不存在"),

    ROOT_NOT_LGOIN("root_not_login", "超级管理员不允许登录平台"),

    DEVICE_NUM_EMPTY("device_num_empty", "该账户无访问设备的权限"),

    /**
     * 用户被禁止登陆
     */
    USER_NOT_ENABLE("user_not_enable", "用户被禁止登陆，请联系管理员"),

    USER_NOT_ROOT("user_not_root", "超级管理员不允许登陆"),

    DEVICE_NULL("device_null", "该用户下没有设备数据,请联系管理员"),


    /**
     * 用户没有分配角色
     */
    USER_NOT_HAVE_ROLE("user_not_have_role", "用户没有分配角色，请联系管理员"),

    /**
     * 用户认证失败
     */
    USER_AUTH_FAILED("user_auth_failed", "用户名或密码错误"),

    USER_ORG_AUTH_FAILED("user_org_auth_failed", "用户没有该组织的访问权限"),

    /**
     * 更新操作失败
     */
    CRUD_UPDATE_NO_RECORD("update_no_record", "更新操作失败"),

    CRUD_UPDATE_IS_EXIST("update_accountNumber_is_exist","更新的用户已经存在，无法修改"),

    EXIST_CONFIG("exist_config", "已配置相关信息"),

    CTRL_ERROR("ctrl_error", "下控失败"),

    CTRL_FORBIDDEN("ctrl_forbidden", "没有下控权限"),

    CTRL_INVALID_VALUE("ctrl_invalid_value", "数据超出设定范围"),

    CTRL_CHANEL_EMPTY("ctrl_chanel_empty", "下控渠道未确定"),

    /**
     * 确认密码不一致
     */
    USER_CONFIRM_PASSWORD_FAIL("user_confirm_password_fail", "两次输入密码不一致"),

    /**
     * 密码为空
     */
    USER_OLD_PASSWORD_IS_EMPTY("user_password_is_empty", "旧密码为空"),
    /**
     * 密码为空
     */
    USER_PASSWORD_IS_EMPTY("user_password_is_empty", "新密码为空"),
    /**
     * 用户名已存在
     */
    USER_DUPLICATE_LOGIN_NAME("user_duplicate_login_name", "用户名已存在"),

    /**
     * 组织不存在
     */
    ORG_NOT_EXIST("org_not_exist", "组织不存在"),

    /**
     * 当前组织不能创建下级组织
     */
    ORG_CAN_NOT_CREATE_CHILD("org_can_not_create_child", "当前组织不能创建下级组织"),

    /**
     * 当前组织不能创建用户
     */
    ORG_CAN_NOT_CREATE_USER("org_can_not_create_user", "当前组织不能创建用户"),

    /**
     * 当前组织不能被删除，存在下级组织、用户或者项目
     */
    ORG_CAN_NOT_BE_DELETED("org_can_not_be_deleted", "当前组织不能被删除，存在下级组织、用户或项目"),//或者项目

    /**
     * 当前组织的所能创建的子组织级别超过最大值
     */
    ORG_CHILD_MAX_LEVEL_EXCEED("org_child_max_level_exceed", "创建的子组织级别超过最大值"),

    /**
     * 当前组织的所能创建的子组织数目超过最大值
     */
    ORG_CHILD_MAX_CHILD_EXCEED("org_child_max_child_exceed", "创建的子组织数目超过最大值"),

    /**
     * 部门不存在
     */
    DEPT_NOT_EXIST("dept_not_exist", "部门不存在"),

    /**
     * 部门不能移动到下级部门
     */
    DEPT_CAN_NOT_MOVE_CHILD("dept_can_not_move_child", "部门不能移动到下级部门"),

    /**
     * 部门不能移动到其它组织
     */
    DEPT_CAN_NOT_MOVE_OUTTER_ORG("dept_can_not_move_outter_org", "部门不能移动到其它组织"),

    /**
     * 员工不存在
     */
    EMP_NOT_EXIST("emp_not_exist", "员工不存在"),

    /**
     * 组织管理员已经存在
     */
    ORG_ADMIN_USER_EXIST("org_admin_user_exist", "组织管理员已经存在"),

    /**
     * 用户权限不足
     */
    USER_LACK_OF_RIGHT("user_lack_of_right", "用户权限不足"),

    /**
     * 删除操作失败
     */
    CRUD_DELETE_NO_RECORD("delete_no_record", "删除操作失败"),

    /**
     * 导入失败，设备ID不存在
     */
    IMP_DEVICE_ID_NULL("imp_device_id_null", "导入失败，设备ID不存在"),

    /**
     * 区域编号已经存在
     */
    SYS_REGION_ID_EXIT("sys_region_id_exit", "区域编号已经存在"),

    /**
     * 删除失败，该项目含有设备位置
     */
    ORG_PROJECT_DEL_ERROR("org_project_del_error", "删除失败，该项目含有设备位置"),

    /**
     * 删除失败，该设备位置含有设备
     */
    DEVICE_LOCATION_DEL_ERROR("device_location_del_error", "删除失败，该设备位置含有设备"),

    /**
     * 导入失败，设备不存在
     */
    IMP_DEVICE_ID_NOT_EXIT("imp_device_id_not_exit", "导入失败，设备不存在"),

    IMP_DEVICE_PARAM_iILLEGAL("imp_device_param_illegal", "导入失败,点表信息匹配失败"),

    IMP_DEVICE_PARAM_NULL("imp_device_param_null", "导入失败,点表列表为空"),

    DEVICE_SERVICE_POINT_EXYT("device_service_point_exit", "设备测点已经存在"),

    SYS_NOT_EXIST("sys_not_exist", "请求数据不存在"),

    SYS_NOT_ROLE("sys_not_role", "没有操作权限"),

    DELETE_HAS_CHILD("delete_has_child", "删除区域失败,该区域含有附属区域"),

    OLD_PWD_ERROR("old_pwd_error","修改失败，原密码输入错误"),

    DATA_ALREADY_EXISTS("data_already_exists","数据已存在"),

    USER_FAVORITE_TYPE_EMPTY("user_favorite_type_empty", "收藏类型为空"),

    USER_FAVORITE_COLLECT_ID_EMPTY("user_favorite_collect_id_empty", "用户收藏的泵房或设备id为空"),

    USER_FAVORITE_ID_EMPTY("user_favorite_id_empty", "用户收藏列表id为空"),

    SEND_MESSAGE_FAIL("send_message_fail", "发送验证码失败"),

    SEND_MESSAGE_CODE_EMPTY("send_message_code_empty", "验证码不存在"),

    SEND_MESSAGE_CODE_ERROR("SEND_MESSAGE_CODE_ERROR", "验证码错误"),

    PHONE_EMPTY("phone_empty", "手机号和登录用户名都为空"),

    MSG_EMPTY("msg_empty", "消息内容为空"),

    PHONE_IS_NOT("PHONE_IS_NOT", "该手机号未注册"),

    GET_USER_FAILED("get_user_failed", "获取当前用户失败"),

    PIC_NAME_EMPTY("pic_name_empty","文件名称为空"),

    UPLOAD_FILE_ERROR("upload_file_error","上传文件异常"),

    TWO_PWD_NOT_EQUAL("two_pwd_not_equal","两次密码不一致"),


    GET_VERSION_EMPTY("get_version_empty", "获取的版本号为空"),

    VERSION_NOT_EXIST("version_not_exist", "当前版本不存在"),

    LATEST_VERSION_ERROR("latest_version_error", "新版本号比旧版本号小"),

    PUMPHOUSEID_IS_EMPTY("pumphouseid_is_empty", "泵房ID为空"),

    PUMPHOUSE_IS_EMPTY("pumphouse_is_empty", "泵房为空"),

    DEVICELIST_IS_EMPTY("devicelist_is_empty", "获取的设备列表为空"),

    VIDEOLIST_IS_EMPTY("videolist_is_empty", "泵房视频列表为空"),

    TWO_PASSWORD_EQUAL("two_password_equal", "新密码与旧密码一致"),
    
    NEW_PASSWORD_EQUAL("new_password_rex", "新密码至少8位，必须包含大小写字母数字"),

    MOBILE_NOT_UPDATE("mobile_not_update", "登录手机号不能修改"),

    ALARM_LEVEL_EMPTY("alarm_level_empty", "报警级别不能为空"),

    DEVICEID_IS_EMPTY("deviceid_is_empty", "设备ID为空"),

    LONGI_IS_EMPTY("longi_is_empty", "经度为空"),

    LATI_IS_EMPTY("lati_is_empty", "纬度为空"),

    TOP_ERROR("top_error", "置顶失败"),

    PUMP_COLLETED("pump_colleted", "该泵房已经被收藏"),

    USER_NO_DEVICE("user_no_device", "用户未有查看该设备权限"),

    DEVICE_EMPTY("device_empty","该设备不存在"),

    USER_NO_PUMP("user_no_pump", "用户未有查看该泵房权限"),

    PUMP_EMPTY("pump_empty","该泵房不存在"),

    POS_EMPTY("pos_empty", "泵房组态未配置"),

    DOOR_EMPTY("door_empty","门禁编码不能为空"),

    DOOR_PWD_EMPTY("door_pwd_empty","门禁密码不能为空"),

    DOOR_NO_CONFIG("door_no_config","门禁未配置，请联系管理员"),

    DOOR_NO_AUTHORITY("door_no_authority","该用户没有权限扫一扫该泵房门禁"),

    REMOTE_DOOR("remote_door","该用户没有门禁下控权限"),

    DOOR_PWD_ERROR("door_pwd_error","门禁密码错误或二维码失效"),

    REMOTE_NO_RESPONSE("remote_no_response","远程下控没有响应"),

    REQUEST_ERROR("request_error","远程请求失败"),

    REQUEST_TICKET_ERROR("request_ticket_error","请求工单系统失败"),

    VIDEOID_ID_EMPTY("video_is_empty", "视频id不能为空"),

    HKVIDEODEVICEID_IS_EMPTY("hkvideodeviceid_is_empty", "视频不存在，请联系管理员配置"),

    PUMP_NODE_NULL("pump_node_null", "泵房组态图未配置"),

	CTRL_ERROR_NULL("ctrl_error", "下控失败,无该测点"),

	NO_MODEL("no_model", "该设备未配置水泵型号"),

	MODEL_DATA_EMPTY("model_data_empty", "该水泵未配置基础数据"),

	OPEN_FAIL_ERROR("open_fail_error", "门禁下控失败"),

	OPEN_WG_FAIL_ERROR("open_fail_error", "微耕门禁暂时不支持下控"),

	OPEN_NO_FAIL_ERROR("open_fail_error", "暂无确定门禁下控渠道"),

	PUMP_NO_DEVICE("pump_no_device", "该泵房无该设备"),

	DEVICE_ID_EMPTY("device_id_empty", "设备Id为空"),

	POINT_VALUE_EMPTY("point_value_empty", "下控参数值为空"),

	PROJECT_NO_PUMP("project_no_pump", "该项目下无泵房数据"),

    DEVICE_TYPE_NO_COMPONENT("device_type_no_component", "该类型设备没有配件，请联系管理员配置"),

    APP_LOGIN_NAME_EMPTY("app_login_name_empty", "APP用户为空"),

    SYS_LOGIN_NAME_EMPTY("sys_login_name_empty", "平台用户为空"),

    APP_SYS_NAME_NOT_QEUAL("app_sys_name_not_equal", "当前操作用户不是本人，无法继续操作"),

    SEND_MESSAGE_CODE_ERROR_CRTL("send_message_code_error_crtl", "验证码错误，无法继续操作"),

    PHONE_NOT_QEUAL("phone_not_qeual", "手机号不是本人，无法继续操作"),

    PARAMETER_ERROR("parameter_error", "参数错误"),

    SVG_DELETED("svg_deleted","组态对象已被删除"),

    SVG_NOT_EXIST("svg_deleted", "组态不存在"),

    NOT_FIND_POINTNAME("not_find_pointname","没有找到相关的测点"),

    MANUAL_CREAT_TICKET_ERROR("manual_create_ticket_error","手动创建工单失败"),

    INSPECTION_CREAT_TICKET_ERROR("Inspection_create_ticket_error","电子巡检工单创建失败"),

    LOGIN_NAME_EMPTY("login_name_empty", "用户名为空"),

    APP_ROUTE_TICKET_REQUEST_FAIL("app_route_request_fail", "APP转发工单请求失败"),

    APP_IP_NOT_EXIST("app_ip_not_exist", "ip不存在"),

    PLAN_NOT_EXIST("plan_not_exist", "该水箱蓄水计划不存在"),

    SYS_ERROR("sys_error","系统异常！"),

    QRCODE_IS_EXPIRE("qrcode_is_expire", "二维码已过期，请刷新后重试"),

    CRUD_INSERT_NO_RECORD("update_no_record", "新增操作失败"),

    CRUD_INSERT_ACCOUNTNUMBER_IS_NULL("accountNumber_is_null","用户户号不能为空"),

    CRUD_INSERT_IS_REPETITION("insert_is_repetition","新增的数据是重复的，无法再插入"),

    MENU_CAN_NOT_UPDATE("menu_can_not_Update", "目录不能更新"),

    CRUD_INSERT_NUMBER_IS_NULL("phoneNumber_or_landlineNumber_is_null","手机号和座机号不能同时为空"),

    CRUD_INSERT_NAME_IS_NULL("name_is_null","姓名不能为空"),

    PARENT_ID_EMPTY("parent_id_empty", "上级为空"),

    TITLE_IS_EMPTY("title_is_empty", "输入名称为空"),

    EXCEL_IMPORT_FAIL("excel_import_null","excel文件为空，导入失败"),

    EXCEL_IMPORT_NULL("excel_import_null","导入Excel文件信息为空"),

    START_TIME_IS_EMPTY("start_time_is_empty","开始时间为空"),

    EMD_TIME_IS_EMPTY("end_time_is_empty","结束时间为空"),

    EXCEL_EXPORT_FAIL("excel_export_fail","excel文件导出失败"),

    EXCEL_IMPORT_IS_NULL("excel_export_is_null","客户列表无数据"),

    CRUD_INSERT_REPEAT("insert_is_repeat","新增数据已存在"),
    ;


    private final String code;

    private final String message;

    public String getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

    ErrorCode(String code, String message) {
        this.code = code;
        this.message = message;
    }
}
