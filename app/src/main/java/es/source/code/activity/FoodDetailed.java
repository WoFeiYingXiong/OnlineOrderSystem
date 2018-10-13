package es.source.code.activity;

import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import java.util.ArrayList;
import java.util.List;

import es.source.code.activity.Adapter.ViewPager_Food_Detailed_Adapter;
import es.source.code.activity.Fragment.FoodDetailedFragment;
import es.source.code.activity.Fragment.FoodDetailedItem;

public class FoodDetailed extends AppCompatActivity {
    private ViewPager viewPager;
    private ViewPager_Food_Detailed_Adapter adapter;
    private List<FoodDetailedFragment> fragments;

    private List<FoodDetailedItem> foodDetailedItemList;

    private int[] food_cold_pictures={R.drawable.huang_gua_la_pi_1,R.drawable.liang_ban_jin_zheng_gu_2,R.drawable.yang_cong_mu_er_3,R.drawable.xia_ren_ban_mu_er_4,R.drawable.hang_jiao_pi_dan_5,
    R.drawable.guo_ren_shu_cai_6,R.drawable.ban_tu_dou_si_7,R.drawable.bo_cai_fen_si_8,R.drawable.shui_jing_zhou_zi_9,R.drawable.kou_shui_ji_10,R.drawable.jiang_xiang_zhou_hua_11,R.drawable.wu_xiang_niu_rou_12,
    R.drawable.ri_shi_e_gan_13,R.drawable.shou_si_tui_rou_14};
    private String[] food_cold_names={"黄瓜拉皮","凉拌金针菇","洋葱木耳","虾仁拌木耳","杭椒皮蛋","果仁蔬菜","拌土豆丝","菠菜粉丝","水晶肘子","口水鸡","酱香肘花","五香牛肉","日式鹅肝","手撕兔肉"};
    private String[] food_cold_prices={"8","12","12","18","12","10","8","10","22","22","18","22","20","26"};

    private int[] food_hot_pictures={R.drawable.hui_guo_rou_1,R.drawable.yu_xiang_rou_si_2,R.drawable.la_zi_ji_ding_3,R.drawable.hong_shao_da_chang_4,R.drawable.mu_er_rou_pian_5,R.drawable.zi_ran_yang_rou_6,
            R.drawable.tu_dou_niu_rou_7,R.drawable.suan_cai_yu_8,R.drawable.chao_rou_pian_9,R.drawable.mei_dou_chao_rou_10,R.drawable.qing_jiao_rou_pian_11,R.drawable.ku_gua_chao_rou_12,R.drawable.yang_rou_xiao_chao_13,
            R.drawable.gong_bao_ji_ding_14};
    private String[] food_hot_names={"回锅肉","鱼香肉丝","辣子鸡丁","红烧大肠","木耳肉片","孜然羊肉","土豆牛肉","酸菜鱼","炒肉片","梅豆炒肉","青椒肉片","苦瓜炒肉","羊肉小炒","宫爆鸡丁"};
    private String[] food_hot_prices={"22","20","20","30","20","38","40","28","58","32","32","36","38","32"};

    private int[] food_sea_pictures={R.drawable.ma_la_he_xie_1,R.drawable.ma_la_da_hai_xia_2,R.drawable.ma_la_xiang_luo_3,R.drawable.ma_la_xiao_long_xia_4,R.drawable.mi_zhi_shan_bei_rou_5,R.drawable.guan_tang_xie_qian_6,
            R.drawable.a_gen_ting_hong_xia_7,R.drawable.ma_la_ji_wei_xia_8,R.drawable.cao_yu_9,R.drawable.hei_yu_10,R.drawable.lian_yu_11,R.drawable.you_yu_12,R.drawable.bao_yu_13,R.drawable.huang_hua_yu_14};
    private String[] food_sea_names={"麻辣河蟹","麻辣大海虾","麻辣香螺","麻辣小龙虾","秘制扇贝肉","灌汤蟹钳","阿根廷红虾","麻辣基围虾","草鱼","黑鱼","鲢鱼","鱿鱼","鲍鱼","黄花鱼"};
    private String[] food_sea_prices={"80","60","60","60","60","40","60","40","48","58","38","50","28","58"};

    private int[] food_drinks_pictures={R.drawable.nong_fu_shan_quan_1,R.drawable.hai_zhi_lan_2,R.drawable.lu_zhou_lao_jiao_3,R.drawable.da_hong_pao_4,R.drawable.tie_guan_yin_5,R.drawable.xin_yang_mao_jian_6,R.drawable.ha_er_bin_pi_jiu_7,
            R.drawable.pu_er_8,R.drawable.yi_bao_9,R.drawable.guo_li_cheng_10,R.drawable.hong_cha_11,R.drawable.bai_sui_shan_12,R.drawable.fen_da_13,R.drawable.huang_shan_mao_feng_14};
    private String[] food_drinks_names={"农夫山泉","海之蓝","泸州老窖","大红袍","铁观音","信阳毛尖","哈尔滨啤酒","普洱","怡宝","果粒橙","红茶","百岁山","芬达","黄山毛峰"};
    private String[] food_drinks_prices={"3","188","88","128","88","88","12","60","3","6","6","5","5","50"};

    public void initiateData()
    {
        foodDetailedItemList=new ArrayList<>();
        for(int i=0;i<food_cold_names.length;i++)
        {
            FoodDetailedItem foodDetailedItem=new FoodDetailedItem();
            foodDetailedItem.setFood_name(food_cold_names[i]);
            foodDetailedItem.setFood_price(food_cold_prices[i]);
            foodDetailedItem.setDrawable(food_cold_pictures[i]);
            foodDetailedItemList.add(foodDetailedItem);
        }
        for(int i=0;i<food_hot_names.length;i++)
        {
            FoodDetailedItem foodDetailedItem=new FoodDetailedItem();
            foodDetailedItem.setFood_name(food_hot_names[i]);
            foodDetailedItem.setFood_price(food_hot_prices[i]);
            foodDetailedItem.setDrawable(food_hot_pictures[i]);
            foodDetailedItemList.add(foodDetailedItem);
        }
        for(int i=0;i<food_sea_names.length;i++)
        {
            FoodDetailedItem foodDetailedItem=new FoodDetailedItem();
            foodDetailedItem.setFood_name(food_sea_names[i]);
            foodDetailedItem.setFood_price(food_sea_prices[i]);
            foodDetailedItem.setDrawable(food_sea_pictures[i]);
            foodDetailedItemList.add(foodDetailedItem);
        }
        for(int i=0;i<food_drinks_names.length;i++)
        {
            FoodDetailedItem foodDetailedItem=new FoodDetailedItem();
            foodDetailedItem.setFood_name(food_drinks_names[i]);
            foodDetailedItem.setFood_price(food_drinks_prices[i]);
            foodDetailedItem.setDrawable(food_drinks_pictures[i]);
            foodDetailedItemList.add(foodDetailedItem);
        }
        fragments=new ArrayList<>();
        for(int i=0;i<foodDetailedItemList.size();i++)
        {
            FoodDetailedFragment fragment=FoodDetailedFragment.NewInstance(foodDetailedItemList.get(i));
//            FoodDetailedFragment fragment=new FoodDetailedFragment(foodDetailedItemList.get(i));
            fragments.add(fragment);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.food_detailed);
        viewPager= (ViewPager) findViewById(R.id.viewPager);
        initiateData();
        adapter=new ViewPager_Food_Detailed_Adapter(getSupportFragmentManager(),fragments);
        viewPager.setAdapter(adapter);
        String position=getIntent().getStringExtra("position");
        if(position!=null)
        {
            viewPager.setCurrentItem(Integer.parseInt(position));
        }
    }
}
