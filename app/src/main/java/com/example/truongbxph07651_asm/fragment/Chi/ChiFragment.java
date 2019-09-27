package com.example.truongbxph07651_asm.fragment.Chi;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.example.truongbxph07651_asm.R;
import com.example.truongbxph07651_asm.adapter.TabLayout.TabChiAdapter;
import com.google.android.material.tabs.TabLayout;

public class ChiFragment extends Fragment {
    private View view;
    private ViewPager vpChi;
    private TabLayout tbChi;
    @Nullable
    @Override
    //Fragment không phải là activity nên khác onCreate, Fragment cũng có vòng đời khác activity
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle bundle){
        view=inflater.inflate(R.layout.chi_fragment,container,false);

        vpChi= view.findViewById(R.id.vpChi);
        vpChi.setAdapter(new TabChiAdapter(getChildFragmentManager()));
        tbChi= view.findViewById(R.id.tbChi);
        tbChi.setupWithViewPager(vpChi);

        return view;
    }
}
