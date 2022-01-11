/**
 * Copyright (c) 2018 人人开源 All rights reserved.
 * <p>
 * https://www.renren.io
 * <p>
 * 版权所有，侵权必究！
 */

package com.cygs.gsweb.utils.result;

/**
 * 错误编码，由5位数字组成，前2位为模块编码，后3位为业务编码
 * <p>
 * 如：10001（10代表系统模块，001代表业务代码）
 * </p>
 *
 * @author Mark sunlightcs@gmail.com
 * @since 1.0.0
 */
public interface ErrorCode {
    int INTERNAL_SERVER_ERROR = 500;
    //接口错误
    int INTERFACE_ERROR = 501;
    int UNAUTHORIZED = 401;
    int FORBIDDEN = 403;
    int AESKEY_INVALID = 408;
    int RSAKEY_INVALID = 409;
    int RSA_ERROR = 10000;
    int NOT_NULL = 10001;
    int DB_RECORD_EXISTS = 10002;
    int PARAMS_GET_ERROR = 10003;
    int ACCOUNT_PASSWORD_ERROR = 10004;
    int ACCOUNT_DISABLE = 10005;
    int IDENTIFIER_NOT_NULL = 10006;
    int CAPTCHA_ERROR = 10007;
    int SUB_MENU_EXIST = 10008;
    int PASSWORD_ERROR = 10009;
    int ACCOUNT_NOT_EXIST = 10010;
    int SUPERIOR_DEPT_ERROR = 10011;
    int SUPERIOR_MENU_ERROR = 10012;
    int DATA_SCOPE_PARAMS_ERROR = 10013;
    int DEPT_SUB_DELETE_ERROR = 10014;
    int DEPT_USER_DELETE_ERROR = 10015;
    int ACT_DEPLOY_ERROR = 10016;
    int ACT_MODEL_IMG_ERROR = 10017;
    int ACT_MODEL_EXPORT_ERROR = 10018;
    int UPLOAD_FILE_EMPTY = 10019;
    int TOKEN_NOT_EMPTY = 10020;
    int TOKEN_INVALID = 10021;
    int ACCOUNT_LOCK = 10022;
    int ACT_DEPLOY_FORMAT_ERROR = 10023;
    int OSS_UPLOAD_FILE_ERROR = 10024;
    int SEND_SMS_ERROR = 10025;
    int MAIL_TEMPLATE_NOT_EXISTS = 10026;
    int REDIS_ERROR = 10027;
    int JOB_ERROR = 10028;
    int INVALID_SYMBOL = 10029;
    int JSON_FORMAT_ERROR = 10030;
    int SMS_CONFIG = 10031;
    int DEPT_CONTACT_BOOK_DELETE_ERROR = 10032;
    //注释工作流内部错误码总定义
    int ACT_IN_ERROR = 10033;
    //没有操作权限
    int AUTH_IS_ERROR = 10034;

    int SERVICE_DO_POST_NULL_ERROR_10036 = 10036;//接口返回为空
    int SERVICE_DO_POST_CONNECT_TIMEOUT_10037 = 10037;//接口超时
    int SERVICE_DO_POST_RESPONSE_TIMEOUT_10038 = 10038;//响应超时
    int SERVICE_DO_POST_DATA_PARSE_ERROR_10039 = 10039;//接口报文解析异常
    int SERVICE_DO_POST_CONNECT_ERROR_10040 = 10040;//接口连接异常
    int SIGN_PARAM_VALID_FAIL = 10041;//签名参数验证失败
    int SUPERIOR_REGION_ERROR = 10042;//
    int REGION_SUB_DELETE_ERROR = 10043;
    int APE_TENANT_IS_EXIT = 10044;//租户已存在
    int SYNOCHAR_FAIL = 10045;//同步失败
    int TYPE_ERROR = 10046;//类型错误


}
