package com.example.truongbxph07651_asm.adapter.TabLayout;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import com.example.truongbxph07651_asm.fragment.Thu.ThuKhoanFragment;
import com.example.truongbxph07651_asm.fragment.Thu.ThuLoaiFragment;

public class TabThuAdapter extends FragmentStatePagerAdapter {
    //Mảng String chứa các title của các tab
    private String lisTab []={"Khoản Thu","Loại Thu"};
    private ThuKhoanFragment thuKhoanFragment;
    private ThuLoaiFragment thuLoaiFragment;

    public TabThuAdapter(FragmentManager fm) {
        super(fm);
        thuKhoanFragment=new ThuKhoanFragment();
        thuLoaiFragment=new ThuLoaiFragment();
    }

    @Override
    public Fragment getItem(int position) {
        if(position==0){
            return thuKhoanFragment;
        }
        else if(position==1){
            return thuLoaiFragment;
        }
        else {

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
