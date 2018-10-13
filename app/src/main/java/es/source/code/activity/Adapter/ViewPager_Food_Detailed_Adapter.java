package es.source.code.activity.Adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.List;

import es.source.code.activity.Fragment.FoodDetailedFragment;

/**
 * Created by Administrator on 2018/10/4.
 */

public class ViewPager_Food_Detailed_Adapter extends FragmentPagerAdapter
{
    private List<FoodDetailedFragment> fragments;

    public ViewPager_Food_Detailed_Adapter(FragmentManager fm, List<FoodDetailedFragment> fragments)
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
}
