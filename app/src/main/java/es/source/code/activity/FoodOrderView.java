package es.source.code.activity;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.widget.LinearLayout;

import java.util.ArrayList;
import java.util.List;

import es.source.code.activity.Adapter.ViewPager_Food_Order_Adapter;
import es.source.code.activity.Fragment.FoodOrderFragment;
import es.source.code.model.User;
import es.source.code.util.Save_Foods_Data;

public class FoodOrderView extends AppCompatActivity {
    private User user;
    private ViewPager viewPager;
    private TabLayout tabLayout;
    private ViewPager_Food_Order_Adapter adapter;
    private List<String> listTitles;
    private List<FoodOrderFragment> fragments;

    public void initiateData()
    {
        user=new User();
        fragments=new ArrayList<>();
        listTitles=new ArrayList<>();
        listTitles.add("未下单菜");
        listTitles.add("已下单菜");
        for(int i=0;i<listTitles.size();i++)
        {
            FoodOrderFragment fragment=null;
            switch (i)
            {
                case 0:
                    fragment=FoodOrderFragment.NewInstance(false,true);
//                    fragment=new FoodOrderFragment(false,true);
                    break;
                case 1:
                    fragment= FoodOrderFragment.NewInstance(true,false);
//                    fragment=new FoodOrderFragment(true,false);
                    break;
                default:
                    break;
            }
            fragment.setTitle(listTitles.get(i));
            fragments.add(fragment);
        }
        for(int i=0;i<listTitles.size();i++)
        {
            tabLayout.addTab(tabLayout.newTab().setText(listTitles.get(i)));
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.food_order_view);
        viewPager= (ViewPager) findViewById(R.id.viewPager);
        tabLayout= (TabLayout) findViewById(R.id.tabLayout);
        initiateData();
        adapter=new ViewPager_Food_Order_Adapter(getSupportFragmentManager(),fragments);
        viewPager.setAdapter(adapter);
        tabLayout.setupWithViewPager(viewPager);

        LinearLayout linearLayout= (LinearLayout) tabLayout.getChildAt(0);
        linearLayout.setShowDividers(LinearLayout.SHOW_DIVIDER_MIDDLE);
        linearLayout.setDividerDrawable(ContextCompat.getDrawable(this,R.drawable.tablayout_divider));
        linearLayout.setDividerPadding(20);

        String isOrder=getIntent().getStringExtra("isOrder");
        String isShowOrder=getIntent().getStringExtra("isShowOrder");
        if(getIntent().getSerializableExtra("food_order_view_user")!=null)
        {
            user= (User) getIntent().getSerializableExtra("food_order_view_user");
            Save_Foods_Data.setFood_order_view_user(user);
        }
        if(getIntent().getSerializableExtra("food_unorder_user")!=null)
        {
            user= (User) getIntent().getSerializableExtra("food_unorder_user");
            Save_Foods_Data.setFood_order_view_user(user);
        }
        if(getIntent().getSerializableExtra("food_order_user")!=null)
        {
            user= (User) getIntent().getSerializableExtra("food_order_user");
            Save_Foods_Data.setFood_order_view_user(user);
        }
        if(isOrder!=null && isOrder.equals("true"))
        {
            tabLayout.getTabAt(1).select();
        }
        if(isOrder!=null && isOrder.equals("false"))
        {
            tabLayout.getTabAt(0).select();
        }
        if(isShowOrder!=null && isShowOrder.equals("true"))
        {
            tabLayout.getTabAt(1).select();
        }
    }
}
