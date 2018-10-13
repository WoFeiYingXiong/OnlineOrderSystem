package es.source.code.activity.Adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.List;

import es.source.code.activity.Fragment.FoodOrderFragment;

/**
 * Created by Administrator on 2018/10/4.
 */

public class ViewPager_Food_Order_Adapter extends FragmentPagerAdapter
{
    private List<FoodOrderFragment> fragments;

    public ViewPager_Food_Order_Adapter(FragmentManager fm, List<FoodOrderFragment> fragments)
    {
        super(fm);
        this.fragments = fragments;
    }

    @Override
    public Fragment getItem(int position)
    {
        return fragments.get(position);
    }

    @Override
    public int getCount()
    {
        return fragments.size();
    }

    @Override
    public CharSequence getPageTitle(int position)
    {
        return fragments.get(position).getTitle();
    }
}
