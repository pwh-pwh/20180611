package com.lisonglin.util;

import java.io.FileInputStream;
import java.io.InputStream;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.Properties;

import com.lisonglin.model.Car;

public class DateUtil {
	
	public static final Properties errors =new Properties();
	static {
		try {
			InputStream is=new FileInputStream("errors.config");
			errors.load(is);
			is.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	//将日期转换为字符串
	public static LocalDate dateToLocalDate(Date date) {
		return LocalDate.ofEpochDay(date.getTime()/86400000).plusDays(1);
	}



	public static Object[][] listToArray2(List<Car> cars){
		Object[][] data=new Object[cars.size()][];
		int size=cars.size();
		for(int i=0;i<size;i++) {
			Car car = cars.get(i);
			Object[] info =new Object[] {car.getCarID(),car.getDate(),car.isRented()?"未租借":"未归还"};
			data[i]=info;
		}
		return data;
	}
}
