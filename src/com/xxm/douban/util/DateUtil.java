package com.xxm.douban.util;

import java.util.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;

/**
 * 日期工具类，比较时间和将日期转换成指定日期格式字符串
 */
public class DateUtil {
	
	//获取指定格式的当前时间字符串
	public static String currentTime() {
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		String time = format.format(new Date());
		return time;
	}

}
