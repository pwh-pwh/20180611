package com.lisonglin.frame;

import com.lisonglin.model.Car;
import com.lisonglin.service.CarService;
import com.lisonglin.util.DateUtil;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

/**
 * @author coderpwh
 * @version 1.0.0 v
 * @date 2022-06-16 19:29
 */
public class CarMainFrame extends JFrame {
    private JPanel contentPane;
    private JTable table;
    private String[] columnCount= {"序号","日期","状态"};
    private List<Car> list;
    public static Car car;
    public static CarMainFrame frame;

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    frame = new CarMainFrame();
                    //窗口居中
                    frame.setLocationRelativeTo(null);
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    /**
     * Create the frame.
     */
    public CarMainFrame() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 764, 469);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setBounds(29, 58, 692, 332);
        contentPane.add(scrollPane);

        table = new JTable();
        scrollPane.setViewportView(table);

        JButton button = new JButton("查询");
        button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                quaryAll();
            }
        });
        button.setBounds(58, 22, 93, 23);
        contentPane.add(button);

        JButton button_1 = new JButton("添加");
        button_1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                new CarFromFrame().setVisible(true);
            }
        });
        button_1.setBounds(205, 22, 93, 23);
        contentPane.add(button_1);
        //全屏
//		setExtendedState(JFrame.MAXIMIZED_BOTH);

        JButton button_2 = new JButton("修改");
        button_2.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                update();
                quaryAll();
            }
        });
        button_2.setBounds(357, 22, 93, 23);
        contentPane.add(button_2);

        JButton button_3 = new JButton("删除");
        button_3.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                remove();
                quaryAll();
            }
        });
        button_3.setBounds(539, 22, 93, 23);
        contentPane.add(button_3);

    }
    //查询
    public void quaryAll() {

        CarService carService = new CarService();
        list = carService.queryAll();
        if(list==null) {
            JOptionPane.showMessageDialog(null, "服务器繁忙");
            return;
        }
        Object[][] data = DateUtil.listToArray2(list);
        table.setModel(new DefaultTableModel(data, columnCount));
    }

    //删除
    private void remove() {
        int i = table.getSelectedRow();
        Car car = list.get(i);
        int code = new CarService().delete(car.getCarID());
        if(code==0) {
            JOptionPane.showMessageDialog(null, "删除成功");
            return;
        }else {
            JOptionPane.showMessageDialog(null,DateUtil.errors.get(code) );
        }
        quaryAll();
    }

    //修改
    private void update() {
        int i = table.getSelectedRow();
        car = list.get(i);
        new CarFromFrame().setVisible(true);
    }
}
