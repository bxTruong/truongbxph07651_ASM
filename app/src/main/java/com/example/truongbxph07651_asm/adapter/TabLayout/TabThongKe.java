package com.example.truongbxph07651_asm.adapter.TabLayout;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import com.example.truongbxph07651_asm.fragment.Chi.ChiKhoanFragment;
import com.example.truongbxph07651_asm.fragment.Chi.ChiLoaiFragment;
import com.example.truongbxph07651_asm.fragment.ThongKe.ThongKeChiFragment;
import com.example.truongbxph07651_asm.fragment.ThongKe.ThongKeThuChiFragment;
import com.example.truongbxph07651_asm.fragment.ThongKe.ThongKeThuFragment;

public class TabThongKe extends FragmentStatePagerAdapter {

    private String lisTab []={"Thống Kê Thu Chi","Thống Kê Thu","Thống Kê Chi",};

    private ThongKeThuFragment thongKeThuFragment;
    private ThongKeChiFragment thongKeChiFragment;
    private ThongKeThuChiFragment thongKeThuChiFragment;

    public TabThongKe(FragmentManager fm) {
        super(fm);
        thongKeThuFragment=new ThongKeThuFragment();
        thongKeChiFragment=new ThongKeChiFragment();
        thongKeThuChiFragment=new ThongKeThuChiFragment();
    }

    @Override
    public Fragment getItem(int position) {
        if(position==0){
            return thongKeThuChiFragment;
        }
        else if(position==1){
            return thongKeThuFragment;
        }
        else if(position==2) {
            return  thongKeChiFragment;
        }
        else{

        }
        return null;
    }

    @Override
    public int getCount() {
        return lisTab.length;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return lisTab[position];
    }
}
