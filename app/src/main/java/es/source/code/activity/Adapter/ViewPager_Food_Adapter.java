package es.source.code.activity.Adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.List;

import es.source.code.activity.Fragment.FoodFragment;

/**
 * Created by Administrator on 2018/10/3.
 */

public class ViewPager_Food_Adapter extends FragmentPagerAdapter {

    private final List<FoodFragment> fragments;

    public ViewPager_Food_Adapter(FragmentManager fm, List<FoodFragment> fragments) {
        super(fm);
        this.fragments=fragments;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return fragments.get(position).getTitle();
    }

    @Override
    public Fragment getItem(int position) {
        return fragments.get(position);
    }

    @Override
    public int getCount() {
        return fragments.size();
    }
}
