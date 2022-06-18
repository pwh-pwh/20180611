package com.lisonglin.service;

import com.lisonglin.dao.CarDao;
import com.lisonglin.model.Car;

import java.sql.SQLException;
import java.util.List;

/**
 * @author coderpwh
 * @version 1.0.0 v
 * @date 2022-06-16 19:25
 */
public class CarService {
    public List<Car> queryAll(){
        try {
            return new CarDao().queryAll();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public int update(Car s) {
        CarDao sDao =new CarDao();
        try {
            sDao.update(s);
            return 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return 1001;
        }
    }

    public int delete(String id) {
        CarDao sDao =new CarDao();
        try {
            sDao.detele(id);
            return 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return 1001;
        }
    }

    public int insert(Car s) {
        CarDao sDao =new CarDao();
        try {
            sDao.add(s);
            return 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return 1001;
        }
    }
}
