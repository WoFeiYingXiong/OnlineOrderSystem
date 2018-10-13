package es.source.code.activity.Fragment;

import android.content.Intent;
import android.graphics.Rect;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import es.source.code.activity.Adapter.recyclerview_food_adapter;
import es.source.code.activity.FoodDetailed;
import es.source.code.activity.R;
import es.source.code.util.Save_Foods_Data;
import es.source.code.util.ToastUtil;

/**
 * Created by Administrator on 2018/10/3.
 */
public class FoodFragment extends Fragment {
    private String title;
    LinearLayoutManager layoutManager;
    private RecyclerView recyclerView;
    private recyclerview_food_adapter adapter;
    private boolean isCold,isHot,isSea,isDrinks;
    private static List<FoodOrderItem> foodOrderItemList;

    private static List<FoodItem> foodColdItemList;
    private String[] food_cold_names={"黄瓜拉皮","凉拌金针菇","洋葱木耳","虾仁拌木耳","杭椒皮蛋","果仁蔬菜","拌土豆丝","菠菜粉丝","水晶肘子","口水鸡","酱香肘花","五香牛肉","日式鹅肝","手撕兔肉"};
    private String[] food_cold_prices={"8","12","12","18","12","10","8","10","22","22","18","22","20","26"};

    private static List<FoodItem> foodHotItemList;
    private String[] food_hot_names={"回锅肉","鱼香肉丝","辣子鸡丁","红烧大肠","木耳肉片","孜然羊肉","土豆牛肉","酸菜鱼","炒肉片","梅豆炒肉","青椒肉片","苦瓜炒肉","羊肉小炒","宫爆鸡丁"};
    private String[] food_hot_prices={"22","20","20","30","20","38","40","28","58","32","32","36","38","32"};

    private static List<FoodItem> foodSeaItemList;
    private String[] food_sea_names={"麻辣河蟹","麻辣大海虾","麻辣香螺","麻辣小龙虾","秘制扇贝肉","灌汤蟹钳","阿根廷红虾","麻辣基围虾","草鱼","黑鱼","鲢鱼","鱿鱼","鲍鱼","黄花鱼"};
    private String[] food_sea_prices={"80","60","60","60","60","40","60","40","48","58","38","50","28","58"};

    private static List<FoodItem> foodDrinksItemList;
    private String[] food_drinks_names={"农夫山泉","海之蓝","泸州老窖","大红袍","铁观音","信阳毛尖","哈尔滨啤酒","普洱","怡宝","果粒橙","红茶","百岁山","芬达","黄山毛峰"};
    private String[] food_drinks_prices={"3","188","88","128","88","88","12","60","3","6","6","5","5","50"};

    public FoodFragment()
    {

    }

    public static FoodFragment NewInstance(boolean isCold, boolean isHot, boolean isSea, boolean isDrinks)
    {
        FoodFragment fragment=new FoodFragment();
        Bundle bundle=new Bundle();
        bundle.putBoolean("isCold",isCold);
        bundle.putBoolean("isHot",isHot);
        bundle.putBoolean("isSea",isSea);
        bundle.putBoolean("isDrinks",isDrinks);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        Bundle bundle=getArguments();
        if(bundle!=null)
        {
            isCold=bundle.getBoolean("isCold");
            isHot=bundle.getBoolean("isHot");
            isSea=bundle.getBoolean("isSea");
            isDrinks=bundle.getBoolean("isDrinks");
        }
    }

//    public FoodFragment(boolean isCold, boolean isHot, boolean isSea, boolean isDrinks)
//    {
//        this.isCold = isCold;
//        this.isDrinks = isDrinks;
//        this.isHot = isHot;
//        this.isSea = isSea;
//    }

    public void initiateData()
    {
        if(foodColdItemList==null)
        {
            foodColdItemList=new ArrayList<>();
            for(int i=0;i<food_cold_names.length;i++)
            {
                FoodItem foodItem=new FoodItem();
                foodItem.setFood_name(food_cold_names[i]);
                foodItem.setFood_price(food_cold_prices[i]);
                foodColdItemList.add(foodItem);
            }
        }
        if(foodHotItemList==null)
        {
            foodHotItemList=new ArrayList<>();
            for(int i=0;i<food_hot_names.length;i++)
            {
                FoodItem foodItem=new FoodItem();
                foodItem.setFood_name(food_hot_names[i]);
                foodItem.setFood_price(food_hot_prices[i]);
                foodHotItemList.add(foodItem);
            }
        }
        if(foodSeaItemList==null)
        {
            foodSeaItemList=new ArrayList<>();
            for(int i=0;i<food_sea_names.length;i++)
            {
                FoodItem foodItem=new FoodItem();
                foodItem.setFood_name(food_sea_names[i]);
                foodItem.setFood_price(food_sea_prices[i]);
                foodSeaItemList.add(foodItem);
            }
        }
        if(foodDrinksItemList==null)
        {
            foodDrinksItemList=new ArrayList<>();
            for(int i=0;i<food_drinks_names.length;i++)
            {
                FoodItem foodItem=new FoodItem();
                foodItem.setFood_name(food_drinks_names[i]);
                foodItem.setFood_price(food_drinks_prices[i]);
                foodDrinksItemList.add(foodItem);
            }
        }
        if(foodOrderItemList==null)
        {
            foodOrderItemList=new ArrayList<>();
        }
    }

    public void setAdapter()
    {
        if(isCold)
        {
            adapter=new recyclerview_food_adapter(getContext(),foodColdItemList);
        }
        if(isHot)
        {
            adapter=new recyclerview_food_adapter(getContext(),foodHotItemList);
        }
        if(isSea)
        {
            adapter=new recyclerview_food_adapter(getContext(),foodSeaItemList);
        }
        if(isDrinks)
        {
            adapter=new recyclerview_food_adapter(getContext(),foodDrinksItemList);
        }
        adapter.setButtonClickListener(new recyclerview_food_adapter.onButtonClickListener() {
            @Override
            public void click(View view,int positon) {
                ToastUtil.showToast(getContext(),"用户点菜成功");
                Button btn_food_order= (Button) view;
//                Button btn_food_order= (Button) view.findViewById(R.id.btn_food_order);
                btn_food_order.setText("退点");
                View view1=layoutManager.findViewByPosition(positon);
                TextView txt_food_name= (TextView) view1.findViewById(R.id.txt_food_name);
                TextView txt_food_price= (TextView) view1.findViewById(R.id.txt_food_price);
                String foodName=txt_food_name.getText().toString().trim();
                String foodPrice=txt_food_price.getText().toString().replace("元","").trim();
//                ToastUtil.showToast(getContext(),foodName+foodPrice);
                FoodOrderItem foodOrderItem=new FoodOrderItem();
                foodOrderItem.setFood_name(foodName);
                foodOrderItem.setFood_price(foodPrice);
                foodOrderItem.setFood_nums(1);
                boolean isAdd=false;
                for(int i=0;foodOrderItemList!=null && i<foodOrderItemList.size();i++)
                {
                    if(foodOrderItemList.get(i).getFood_name().equals(foodName) && foodOrderItemList.get(i).getFood_price().equals(foodPrice))
                    {
                        isAdd=true;
                        int count=foodOrderItemList.get(i).getFood_nums();
                        count++;
                        foodOrderItemList.get(i).setFood_nums(count);
                    }
                }
                if(!isAdd)
                {
                    foodOrderItemList.add(foodOrderItem);
                }
                Save_Foods_Data.setFoodOrderItemList(foodOrderItemList);
            }
        });
        adapter.setItemClickListener(new recyclerview_food_adapter.onItemClickListener() {
            @Override
            public void click(View view,int position) {
                TextView txt_food_name= (TextView) view.findViewById(R.id.txt_food_name);
                String foodName=txt_food_name.getText().toString().trim();
                int toPosition=0;
                for(int i=0;i<Save_Foods_Data.getFood_cold_names().length;i++)
                {
                    if(Save_Foods_Data.getFood_cold_names()[i].equals(foodName))
                    {
                        toPosition=0;
                    }
                }
                for(int i=0;i<Save_Foods_Data.getFood_hot_names().length;i++)
                {
                    if(Save_Foods_Data.getFood_hot_names()[i].equals(foodName))
                    {
                        toPosition=14;
                    }
                }
                for(int i=0;i<Save_Foods_Data.getFood_sea_names().length;i++)
                {
                    if(Save_Foods_Data.getFood_sea_names()[i].equals(foodName))
                    {
                        toPosition=28;
                    }
                }
                for(int i=0;i<Save_Foods_Data.getFood_drinks_names().length;i++)
                {
                    if(Save_Foods_Data.getFood_drinks_names()[i].equals(foodName))
                    {
                        toPosition=42;
                    }
                }
                position+=toPosition;
                Intent intent=new Intent(getContext(), FoodDetailed.class);
                intent.putExtra("position",String.valueOf(position));
                startActivity(intent);
            }
        });
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.food_fragment_container_layout,container,false);
        recyclerView= (RecyclerView) view.findViewById(R.id.rcv_foodview);
        layoutManager=new LinearLayoutManager(getContext(),LinearLayoutManager.VERTICAL,false);
        recyclerView.setLayoutManager(layoutManager);
        initiateData();
        recyclerView.addItemDecoration(new MyDecration());
        setAdapter();
        recyclerView.setAdapter(adapter);
        return view;
    }

    class MyDecration extends RecyclerView.ItemDecoration{

        @Override
        public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
            super.getItemOffsets(outRect, view, parent, state);
            outRect.set(0,0,0,getResources().getDimensionPixelOffset(R.dimen.dividerHeight));
        }
    }
}
