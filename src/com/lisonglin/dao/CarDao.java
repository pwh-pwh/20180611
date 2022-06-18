package com.lisonglin.dao;

import com.lisonglin.model.Car;
import com.lisonglin.util.DateUtil;
import com.lisonglin.util.JdbcUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author coderpwh
 * @version 1.0.0 v
 * @date 2022-06-16 19:11
 */
public class CarDao {
    public void add(Car car) throws SQLException {
        //获取连接
        Connection conn = JdbcUtil.getConnection();
        //准备sql
        //insert into car values('mycar1','2020-1-1','未归还');
        String sql="insert into car values(?,?,?)";
        //获取PreparedStatement
        PreparedStatement ps=conn.prepareStatement(sql);
        //填充占位符
        ps.setString(1, car.getCarID());
        ps.setString(2,car.getDate());
        String str = car.isRented()?"未租借":"未归还";
        ps.setString(3,str);
        //执行sql
        ps.executeUpdate();
        //关闭连接
        JdbcUtil.release(conn);
    }

    public void detele(String id) throws SQLException {
        //获取连接
        Connection conn = JdbcUtil.getConnection();
        //准备sql
        String sql="delete from car where id=?";
        //获取PreparedStatement
        PreparedStatement ps=conn.prepareStatement(sql);
        //填充占位符
        ps.setString(1, id);
        //执行sql
        ps.executeUpdate();
        //关闭连接
        JdbcUtil.release(conn);
    }

    public void update(Car car) throws SQLException {
        Connection conn = JdbcUtil.getConnection();
        String sql="update car set date=?,is_return=? where id=?";
        PreparedStatement ps=conn.prepareStatement(sql);
        ps.setString(1, car.getDate());
        String str = car.isRented()?"未租借":"未归还";
        ps.setString(2,str);
        ps.setString(3,car.getCarID());
        ps.executeUpdate();
        JdbcUtil.release(conn);
    }

    public List<Car> queryAll() throws SQLException{
        List<Car> cars=new ArrayList<>();
        //获取连接
        Connection conn = JdbcUtil.getConnection();
        //准备sql
        String sql="select * from car";
        //获取PreparedStatement
        PreparedStatement ps=conn.prepareStatement(sql);
        //执行sql
        ResultSet rs = ps.executeQuery();
        while(rs.next()) {
            Car car = new Car();
            car.setCarID(rs.getString(1));
            car.setDate(rs.getString(2));
            String string = rs.getString(3);
            boolean flag = false;
            if (string.equals("未租借")) {
                flag = true;
            }
            car.setRented(flag);
            cars.add(car);
        }
        //关闭连接
        JdbcUtil.release(conn);
        return cars;
    }

    public static void main(String[] args) throws SQLException {
        Car car2 = new Car("car2", "2020-1-2",true);
        CarDao carDao = new CarDao();
        carDao.add(car2);
        carDao.queryAll().forEach(System.out::println);
        car2.setDate("222");
        carDao.update(car2);
        carDao.queryAll().forEach(System.out::println);
    }

}
