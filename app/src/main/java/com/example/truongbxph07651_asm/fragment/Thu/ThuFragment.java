package com.example.truongbxph07651_asm.fragment.Thu;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.example.truongbxph07651_asm.R;
import com.example.truongbxph07651_asm.adapter.TabLayout.TabThuAdapter;
import com.google.android.material.tabs.TabLayout;

public class ThuFragment extends Fragment {
    private  View view;
    private ViewPager vpThu;
    private TabLayout tbThu;
    @Nullable

    @Override
    //Fragment không phải là activity nên khác onCreate, Fragment cũng có vòng đời khác activity
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle bundle){
        view=inflater.inflate(R.layout.thu_fragment,container,false);

        vpThu= view.findViewById(R.id.vpThu);
        vpThu.setAdapter(new TabThuAdapter(getChildFragmentManager()));
        tbThu= view.findViewById(R.id.tbThu);
        tbThu.setupWithViewPager(vpThu);

        return view;
    }


}
