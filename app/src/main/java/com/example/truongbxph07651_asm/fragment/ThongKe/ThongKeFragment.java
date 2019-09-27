package com.example.truongbxph07651_asm.fragment.ThongKe;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.example.truongbxph07651_asm.R;
import com.example.truongbxph07651_asm.adapter.TabLayout.TabThongKe;
import com.google.android.material.tabs.TabLayout;

public class ThongKeFragment extends Fragment {

private View view;
    @Nullable
    @Override
    //Fragment không phải là activity nên khác onCreate, Fragment cũng có vòng đời khác activity
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle bundle){
        view=inflater.inflate(R.layout.thong_ke_fragment,container,false);
        TabLayout tabTHongKe=view.findViewById(R.id.tabThongKe);
        ViewPager vpThongKe=view.findViewById(R.id.vpThongKe);
        vpThongKe.setAdapter(new TabThongKe(getChildFragmentManager()));
        tabTHongKe.setupWithViewPager(vpThongKe);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

    }
}
