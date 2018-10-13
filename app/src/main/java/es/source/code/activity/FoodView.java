package es.source.code.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import es.source.code.activity.Adapter.ViewPager_Food_Adapter;
import es.source.code.activity.Fragment.FoodFragment;
import es.source.code.model.User;
import es.source.code.util.Save_Foods_Data;

public class FoodView extends AppCompatActivity
{
    private ViewPager viewPager;
    private TabLayout tabLayout;
    private TextView txt_title;
    private ViewPager_Food_Adapter adapter;
    private List<FoodFragment> fragments;
    private List<String> listTitles;
    private User user;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.food_view);
//        ActionBar actionBar=getSupportActionBar();
//        if(actionBar!=null)
//        {
//            actionBar.hide();
//        }
        viewPager = (ViewPager) findViewById(R.id.viewPager);
        tabLayout = (TabLayout) findViewById(R.id.tabLayout);
//        txt_title= (TextView) findViewById(R.id.txt_title);
//        txt_title.setText("TabLayout & ViewPager");
        viewPager.setPageMargin(10);
        viewPager.setPageMarginDrawable(R.color.colorOrange);

        initiate();
        adapter = new ViewPager_Food_Adapter(getSupportFragmentManager(), fragments);
        viewPager.setAdapter(adapter);
        tabLayout.setupWithViewPager(viewPager);

        LinearLayout linearLayout= (LinearLayout) tabLayout.getChildAt(0);
        linearLayout.setShowDividers(LinearLayout.SHOW_DIVIDER_MIDDLE);
        linearLayout.setDividerDrawable(ContextCompat.getDrawable(this,R.drawable.tablayout_divider));
        linearLayout.setDividerPadding(20);

        if(getIntent().getSerializableExtra("food_view_user")!=null)
        {
            user= (User) getIntent().getSerializableExtra("food_view_user");
            Save_Foods_Data.setFood_view_user(user);
        }

    }

    public void initiate()
    {
        user=new User();
        fragments = new ArrayList<>();
        listTitles = new ArrayList<>();
        listTitles.add("冷菜");
        listTitles.add("热菜");
        listTitles.add("海鲜");
        listTitles.add("酒水");
        for (int i = 0; i < listTitles.size(); i++)
        {
            FoodFragment fragment = null;
            switch (i)
            {
                case 0:
                    fragment=FoodFragment.NewInstance(true, false, false, false);
//                    fragment = new FoodFragment(true, false, false, false);
                    break;
                case 1:
                    fragment=FoodFragment.NewInstance(false, true, false, false);
//                    fragment = new FoodFragment(false, true, false, false);
                    break;
                case 2:
                    fragment=FoodFragment.NewInstance(false, false, true, false);
//                    fragment = new FoodFragment(false, false, true, false);
                    break;
                case 3:
                    fragment=FoodFragment.NewInstance(false, false, false, true);
//                    fragment = new FoodFragment(false, false, false, true);
                    break;
            }
            fragment.setTitle(listTitles.get(i));
            fragments.add(fragment);
        }
        for (int i = 0; i < listTitles.size(); i++)
        {
            tabLayout.addTab(tabLayout.newTab().setText(listTitles.get(i)));
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        getMenuInflater().inflate(R.menu.food_view_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        switch (item.getItemId())
        {
            case R.id.order_goods:
                Intent intent=new Intent(FoodView.this,FoodOrderView.class);
                intent.putExtra("isOrder","false");
                Bundle bundle = new Bundle();
                bundle.putSerializable("food_unorder_user",user);
                intent.putExtras(bundle);
                startActivity(intent);
                break;
            case R.id.search_goods:
                Intent intent1=new Intent(FoodView.this,FoodOrderView.class);
                intent1.putExtra("isOrder","true");
                Bundle bundle1 = new Bundle();
                bundle1.putSerializable("food_order_user",user);
                intent1.putExtras(bundle1);
                startActivity(intent1);
                break;
            case R.id.request_services:
                break;
            default:
                break;
        }
        return true;
    }

    @Override
    public boolean onMenuOpened(int featureId, Menu menu)
    {
        if (menu != null)
        {
            if (menu.getClass().getSimpleName().equalsIgnoreCase("MenuBuilder"))
            {
                try
                {
                    Method method = menu.getClass().getDeclaredMethod("setOptionalIconsVisible", Boolean.TYPE);
                    method.setAccessible(true);
                    method.invoke(menu, true);
                } catch (Exception e)
                {
                    e.printStackTrace();
                }
            }
        }
        return super.onMenuOpened(featureId, menu);
    }
}
