/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import java.util.ArrayList;
import java.util.Arrays;

/**
 *
 * @author biidz
 */
public class Zoo {
    ArrayList<Chuong> DanhSachChuong = new ArrayList<>(Arrays.asList());
    
    public void themChuong(Chuong c) {
        DanhSachChuong.add(c);
        System.out.println("Thêm chuồng thành công!");
    }
    
    public void xoaChuong(int maChuong) {
        DanhSachChuong.remove(maChuong);
        System.out.println("Xóa chuồng thành công!");
    }
}
