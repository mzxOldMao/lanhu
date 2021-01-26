package cn.suancloud.lisheng.api.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 时间戳工具类
 * @author zx
 * @Date 2018年2月26日 下午5:07:32
 * @Class TimeStamp.java
 */
public class TimeStampUtils {

    /**
     * （int）时间戳转Date
     * @author zx
     * @date 2018年2月26日 下午5:10:40
     * @param timestamp
     * @return
     */
    public static Date stampForDate(Integer timestamp){
        return new Date((long) timestamp*1000);
    }

    /**
     * （long）时间戳转Date
     * @author zx
     * @date 2018年2月26日 下午5:16:46
     * @param timestamp
     * @return
     */
    public static Date longStampForDate(long timestamp){
        return new Date(timestamp);
    }

    /**
     * date转String
     * @author zx
     * @date 2018年2月26日 下午5:11:51
     * @param date
     * @return
     */
    public static String dateForString(Date date){
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//时间的格式
        return sdf.format(date);
    }

    /**
     * 时间戳转yyyy-MM-dd HH:mm:ss格式的字符串
     */
    public static String longForString(Long time){
        return dateForString(longStampForDate(time));
    }

    /**
     * yyyy-MM-dd HH:mm:ss格式的字符串转时间戳
     */
    public static Long stringForlong(String date){
        return dateForStamp1(stringForDate(date));
    }

    /**
     * yyyy-MM-dd格式的字符串转时间戳
     */
    public static Long stringForlong1(String date){
        return dateForStamp1(stringForDate1(date));
    }
    /**
     * String转Date
     * @author zx
     * @date 2018年2月26日 下午5:14:36
     * @param time
     * @return
     */
    public static Date stringForDate(String time){
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//时间的格式
        Date date = null;
        try {
            date = sdf.parse(time);
        } catch (ParseException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return date;
    }

    /**
     * String转Date
     * @author zx
     * @date 2018年2月26日 下午5:14:36
     * @param time
     * @return
     */
    public static Date stringForDate1(String time){
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");//时间的格式
        Date date = null;
        try {
            date = sdf.parse(time);
        } catch (ParseException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return date;
    }

    /**
     * Date转时间戳
     * @author zx
     * @date 2018年2月26日 下午5:14:54
     * @param data
     * @return
     */
    public static Integer dateForStamp(Date data){
        return (int) (data.getTime()/1000);
    }
    /**
     * Date转时间戳
     * @author zx
     * @date 2018年2月26日 下午5:14:54
     * @param data
     * @return
     */
    public static Long dateForStamp1(Date data){
        return data.getTime();
    }

    public static void main(String[] args) {
        System.out.println(longForString(1611132824621L));
    }
}