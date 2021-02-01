package cn.suancloud.api.postgresqltest.utils;

import cn.suancloud.api.postgresqltest.model.Book;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.parser.Feature;

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
        /*//Long a = 1611138719000L;//2021-01-20 18:31:59
        //Long a = 1611138719342L; //2021-01-20 18:31:59----->1611138719000
        Long a = 1611194738000L;
        String s = dateForString(longStampForDate(a));
        System.out.println(s);
        String s1 = "1970-01-02 07:59:59";//1970-01-01 08:00:00    --->0        1970-01-02 07:59:59 ---->   86399000
        Long aLong = stringForlong(s1);
        System.out.println(aLong);

        System.out.println(stringForDate1("2021-01-20"));
        System.out.println(stringForDate1("2021-01-21"));
        System.out.println(stringForlong1("2021-01-20"));//1611072000000
        System.out.println(stringForlong1("2021-01-21"));//1611158400000
        System.out.println(stringForlong("2021-01-21 23:59:59"));//1611244799000
        System.out.println(stringForlong("2021-01-21 09:30:30"));//1611192630000
        System.out.println(stringForlong("2021-01-20 09:30:30"));//1611106230000*/
        Book book = new Book();
        book.setId(1L);
        book.setName("肖申克的救赎");
        book.setDesc("udgf粤语");
        book.setAuthor("fhdi");
        System.out.println("book:::::"+book+"类型："+book.getClass());
        //实体类转换成json字符串格式
        String jsonBook = JSON.toJSONString(book);
        System.out.println("jsonBook:::::::"+jsonBook+"类型："+jsonBook.getClass());

        //字符串转换成json格式
        JSONObject object = JSONObject.parseObject(jsonBook);
        System.out.println("object:::::"+object+"类型："+object.getClass());
        /*String author = object.getString("author");
        String name = object.getString("name");
        String desc = object.getString("desc");
        Long id = object.getLong("id");*/
        /*
        //转换成javabean
        Book book2 = JSON.toJavaObject(object,Book.class);
        System.out.println("book2::::::::"+book2);
        String jsonV = "[{\"tp\":1611553816540,\"DeviceID\":\"12012141540002\",\"Deviccetype\":\"PMCMeter\"," +
                "\"point1\":[{\"id\":36,\"val\":9950},{\"id\":37,\"val\":0},{\"id\":38,\"val\":0},{\"id\":39,\"val\":0},{\"id\":40,\"val\":0},{\"id\":41,\"val\":0},{\"id\":42,\"val\":4746},{\"id\":43,\"val\":0},{\"id\":44,\"val\":816},{\"id\":45,\"val\":24},{\"id\":46,\"val\":4362},{\"id\":47,\"val\":0},{\"id\":48,\"val\":1092},{\"id\":49,\"val\":60},{\"id\":50,\"val\":4956},{\"id\":51,\"val\":0},{\"id\":52,\"val\":1014},{\"id\":53,\"val\":108},{\"id\":54,\"val\":0},{\"id\":55,\"val\":0},{\"id\":56,\"val\":0},{\"id\":57,\"val\":18.75},{\"id\":58,\"val\":21.42},{\"id\":59,\"val\":25},{\"id\":60,\"val\":0},{\"id\":61,\"val\":707788.8},{\"id\":62,\"val\":0},{\"id\":63,\"val\":0.35},{\"id\":64,\"val\":27.27},{\"id\":65,\"val\":0},{\"id\":66,\"val\":0},{\"id\":67,\"val\":0},{\"id\":68,\"val\":0},{\"id\":69,\"val\":0},{\"id\":70,\"val\":0},{\"id\":71,\"val\":0},{\"id\":72,\"val\":0}],"+
                "\"point\":[{\"id\":36,\"val\":9960},{\"id\":37,\"val\":0},{\"id\":38,\"val\":0},{\"id\":39,\"val\":0},{\"id\":40,\"val\":0},{\"id\":41,\"val\":0},{\"id\":42,\"val\":4746},{\"id\":43,\"val\":0},{\"id\":44,\"val\":816},{\"id\":45,\"val\":24},{\"id\":46,\"val\":4362},{\"id\":47,\"val\":0},{\"id\":48,\"val\":1092},{\"id\":49,\"val\":60},{\"id\":50,\"val\":4956},{\"id\":51,\"val\":0},{\"id\":52,\"val\":1014},{\"id\":53,\"val\":108},{\"id\":54,\"val\":0},{\"id\":55,\"val\":0},{\"id\":56,\"val\":0},{\"id\":57,\"val\":18.75},{\"id\":58,\"val\":21.42},{\"id\":59,\"val\":25},{\"id\":60,\"val\":0},{\"id\":61,\"val\":707788.8},{\"id\":62,\"val\":0},{\"id\":63,\"val\":0.35},{\"id\":64,\"val\":27.27},{\"id\":65,\"val\":0},{\"id\":66,\"val\":0},{\"id\":67,\"val\":0},{\"id\":68,\"val\":0},{\"id\":69,\"val\":0},{\"id\":70,\"val\":0},{\"id\":71,\"val\":0},{\"id\":72,\"val\":0}]" +
                "}]";
        JSONArray objects1 = JSONArray.parseArray(jsonV);
        JSONObject jsonObject = objects1.getJSONObject(0);
        //System.out.println(jsonObject);
        if (jsonObject.getString("point") != null) {
            System.out.println(jsonObject.getString("tp"));
            JSONArray point = JSONArray.parseArray(jsonObject.getString("point"));
            String id1 = point.getJSONObject(1).getString("id");
            System.out.println(id1);
        }*/

        String json = "{\"msg\": { \"data\": [{\"tgUa\": 191, \"tgUb\": 11,\"tgUc\": 12,\"tgUab\": 0,\"tgUbc\": 0,\"tgUac\": 0,\"tgIa\": 0,\"tglb\": 0,\"tgIc\": 0,\"tgPa\": 0,\"tgPb\": 0,\"tgPc\": 0,\"tgP\": 0,\"tgQa\": 0,\"tgQb\": 0,\"tgQc\": 0,\"tgQ\": 0,\"tgSa\": 0,\"tgSb\": 0,\"tgSc\": 0,\"tgSs\": 0,\"tgPFa\": 1,\"tgPFb\": 1,\"tgPFc\": 1,\"tgPF\": 1,\"tgHz\": 0,\"tgRSSI\": 22,\"tgSupWh\": 1.61,\"tgRtlWh\": 0,\"tgVarh1\": 0.01,\"tgVarh2\": 0,\"tgCwd\": 0,\"tgSIMCard\": \"898604510919C0452912\",\"tgPT\": 1,\"tgCT\": 1}],\"eventTime\": 1607950019180,\"iotdevicename\": \"Test Device\"},\"metadata\": {\"deviceType\": \"default\",\"deviceName\": \"Test Device\",\"ts\": \"1611915126745\"},\"msgType\": \"POST_TELEMETRY_REQUEST\"}";
        //String json1 = "{\"tgUa\": 191, \"tgUb\": 11,\"tgUc\": 12,\"tgUab\": 0,\"tgUbc\": 0,\"tgUac\": 0,\"tgIa\": 0,\"tglb\": 0,\"tgIc\": 0,\"tgPa\": 0,\"tgPb\": 0,\"tgPc\": 0,\"tgP\": 0,\"tgQa\": 0,\"tgQb\": 0,\"tgQc\": 0,\"tgQ\": 0,\"tgSa\": 0,\"tgSb\": 0,\"tgSc\": 0,\"tgSs\": 0,\"tgPFa\": 1,\"tgPFb\": 1,\"tgPFc\": 1,\"tgPF\": 1,\"tgHz\": 0,\"tgRSSI\": 22,\"tgSupWh\": 1.61,\"tgRtlWh\": 0,\"tgVarh1\": 0.01,\"tgVarh2\": 0,\"tgCwd\": 0,\"tgSIMCard\": \"898604510919C0452912\",\"tgPT\": 1,\"tgCT\": 1}";
        JSONObject objects = JSON.parseObject(json, Feature.OrderedField);
        JSONObject jsonObject1 = JSON.parseObject(objects.getString("msg"), Feature.OrderedField);
        /*JSONArray jsonArray = JSONArray.parseArray(jsonObject1.getString("data"));
        System.out.println(jsonArray);*/
        JSONArray jsonArray1 = jsonObject1.getJSONArray("data");
        //System.out.println(jsonArray1);
        JSONObject jsonObj = JSON.parseObject(String.valueOf(jsonArray1.getJSONObject(0)), Feature.OrderedField);
        System.out.println(jsonObj);
        //https://blog.csdn.net/a18827547638/article/details/80272099
        //https://blog.csdn.net/a18827547638/article/details/80777366
    }
}