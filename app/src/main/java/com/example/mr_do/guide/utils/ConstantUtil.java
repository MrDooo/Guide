package com.example.mr_do.guide.utils;

/**
 * Created by Mr_Do on 2017/7/29.
 */

public class ConstantUtil {
    public static final int SUCCESSFUL_LOCATE = 0;
    private static String mParseResult;

    public static String parseGuideErrorCode(int error_code){
        switch (error_code){
            case 0:
                mParseResult = "定位返回成功";
                break;
            case 1:
                mParseResult = "一些重要参数为空";
                break;
            case 2:
                mParseResult = "定位失败,请重新尝试";
                break;
            case 3:
                mParseResult = "获取到的请求参数为空,请求可能被篡改";
                break;
            case 4:
                mParseResult = "请求服务器过程中的异常,请检查设备网络是否通畅";
                break;
            case 5:
                mParseResult = "检查网络链路是否存在异常";
                break;
            case 6:
                mParseResult = "定位服务返回定位失败";
                break;
            case 7:
                mParseResult = "KEY鉴权失败";
                break;
            case 8:
                mParseResult = "Android exception常规错误";
                break;
            case 9:
                mParseResult = "定位初始化时出现异常,请重启";
                break;
            case 10:
                mParseResult = "定位客户端启动失败";
                break;
            case 11:
                mParseResult = "定位时的基站信息错误";
                break;
            case 12:
                mParseResult = "缺少定位权限";
                break;
            case 13:
                mParseResult = "定位失败，由于未获得WIFI列表和基站信息，且GPS当前不可用";
                break;
            case 14:
                mParseResult = "GPS 定位失败，由于设备当前 GPS 状态差";
                break;
            case 15:
                mParseResult = "定位结果被模拟导致定位失败";
                break;
            case 16:
                mParseResult = "当前POI检索条件、行政区划检索条件下，无可用地理围栏";
                break;
            case 18:
                mParseResult = "定位失败，由于手机WIFI功能被关闭同时设置为飞行模式";
                break;
            case 19:
                mParseResult = "定位失败，由于手机没插sim卡且WIFI功能被关闭";
                break;
            default:
                mParseResult = "出现未知错误";
                break;
        }
        return mParseResult;
    }

    public static String parseBusErrorCode(int error_code){
        switch (error_code){
            case 1000:
                mParseResult = "请求正常";
                break;
            case 1001:
                mParseResult = "开发者签名未通过";
                break;
            case 1002:
                mParseResult = "用户Key不正确或过期";
                break;
            case 1003:
                mParseResult = "没有权限使用相应的接口";
                break;
            case 1008:
                mParseResult = "MD5安全码未通过验证";
                break;
            case 1009:
                mParseResult = "请求Key与绑定平台不符";
                break;
            case 1012:
                mParseResult = "权限不足，服务请求被拒绝";
                break;
            case 1013:
                mParseResult = "该Key被删除";
                break;
            case 1100:
                mParseResult = "引擎服务响应错误";
                break;
            case 1101:
                mParseResult = "引擎服务响应错误";
                break;
            case 1102:
                mParseResult = "高德服务端请求链接超时";
                break;
            case 1103:
                mParseResult = "读取服务结果返回超时";
                break;
            case 1200:
                mParseResult = "请求参数非法";
                break;
            case 1201:
                mParseResult = "请求条件中，缺少必填参数";
                break;
            case 1202:
                mParseResult = "服务请求协议非法";
                break;
            case 1203:
                mParseResult = "服务端未知错误";
                break;
            case 1800:
                mParseResult = "服务端新增错误";
                break;
            case 1801:
                mParseResult = "协议解析错误";
                break;
            case 1802:
                mParseResult = "socket 连接超时";
                break;
            case 1803:
                mParseResult = "url异常";
                break;
            case 1804:
                mParseResult = "未知主机";
                break;
            case 1806:
                mParseResult = "http或socket连接失败";
                break;
            case 1900:
                mParseResult = "未知错误";
                break;
            case 1901:
                mParseResult = "参数无效";
                break;
            case 1902:
                mParseResult = "IO 操作异常";
                break;
            case 1903:
                mParseResult = "空指针异常";
                break;
            case 2000:
                mParseResult = "Tableid格式不正确";
                break;
            case 2001:
                mParseResult = "数据ID不存在";
                break;
            case 2002:
                mParseResult = "云检索服务器维护中";
                break;
            case 2003:
                mParseResult = "Key对应的tableID不存在";
                break;
            case 2100:
                mParseResult = "找不到对应的userid信息";
                break;
            case 2101:
                mParseResult = "App Key未开通“附近”功能";
                break;
            case 2200:
                mParseResult = "在开启自动上传功能的同时对表进行清除或者开启单点上传的功能";
                break;
            case 2201:
                mParseResult = "USERID非法";
                break;
            case 2202:
                mParseResult = "NearbyInfo对象为空";
                break;
            case 2203:
                mParseResult = "两次单次上传的间隔低于7秒";
                break;
            case 2204:
                mParseResult = "Point为空，或与前次上传的相同";
                break;
            case 3000:
                mParseResult = "规划点（包括起点、终点、途经点）不在中国陆地范围内";
                break;
            case 3001:
                mParseResult = "规划点（包括起点、终点、途经点）附近搜不到路";
                break;
            case 3002:
                mParseResult = "路线计算失败，通常是由于道路连通关系导致";
                break;
            case 3003:
                mParseResult = "步行算路起点、终点距离过长导致算路失败";
                break;
            case 4000:
                mParseResult = "短串分享认证失败";
                break;
            case 4001:
                mParseResult = "短串请求失败";
                break;
            default:
                mParseResult = "出现未知错误";
                break;
        }
        return mParseResult;
    }
}
