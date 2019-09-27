package com.example.truongbxph07651_asm.adapter.TabLayout;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import com.example.truongbxph07651_asm.fragment.Chi.ChiKhoanFragment;
import com.example.truongbxph07651_asm.fragment.Chi.ChiLoaiFragment;

public class TabChiAdapter extends FragmentStatePagerAdapter {

    private String lisTab []={"Khoản Chi","Loại Chi"};
    private ChiKhoanFragment chiKhoanFragment;
    private ChiLoaiFragment chiLoaiFragment;

    public TabChiAdapter(FragmentManager fm) {
        super(fm);
        chiKhoanFragment=new ChiKhoanFragment();
        chiLoaiFragment=new ChiLoaiFragment();
    }

    @Override
    public Fragment getItem(int position) {
        if(position==0){
            return chiKhoanFragment;
        }
        else if(position==1){
            return chiLoaiFragment;
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
