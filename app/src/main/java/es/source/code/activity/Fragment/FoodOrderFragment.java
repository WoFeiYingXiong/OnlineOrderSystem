package es.source.code.activity.Fragment;

import android.app.Activity;
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

import es.source.code.activity.Adapter.recyclerview_foodorder_adapter;
import es.source.code.activity.FoodOrderView;
import es.source.code.activity.R;
import es.source.code.util.Save_Foods_Data;
import es.source.code.util.ToastUtil;

/**
 * Created by Administrator on 2018/10/4.
 */
public class FoodOrderFragment extends Fragment {
    private String title;
    private TextView txt_total_goods,txt_total_prices;
    private Button btn_check,btn_submit;
    private boolean isNeverOrder,isOrder;
    private RecyclerView recyclerView;
    private LinearLayoutManager layoutManager;
    private recyclerview_foodorder_adapter adapter;
    private List<FoodOrderItem> foodOrderItemList;
    private static List<FoodOrderItem> foodUnorderItemList;

    public String getTitle()
    {
        return title;
    }

    public void setTitle(String title)
    {
        this.title = title;
    }

    public FoodOrderFragment()
    {
    }

//    public FoodOrderFragment(boolean isOrder, boolean isNeverOrder)
//    {
//        this.isOrder = isOrder;
//        this.isNeverOrder = isNeverOrder;
//    }

    public static FoodOrderFragment NewInstance(boolean isOrder, boolean isNeverOrder)
    {
        FoodOrderFragment fragment=new FoodOrderFragment();
        Bundle bundle=new Bundle();
        bundle.putBoolean("isOrder",isOrder);
        bundle.putBoolean("isNeverOrder",isNeverOrder);
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
            isOrder=bundle.getBoolean("isOrder");
            isNeverOrder=bundle.getBoolean("isNeverOrder");
        }
    }

    public void initiateData()
    {
        foodOrderItemList=new ArrayList<>();
        foodOrderItemList= Save_Foods_Data.getFoodOrderItemList();
        if(foodUnorderItemList==null)
        {
            foodUnorderItemList=new ArrayList<>();
        }
        int totalCounts=0;
        int totalPrices=0;
        for(int i=0;foodOrderItemList!=null && i<foodOrderItemList.size();i++)
        {
            totalCounts+=foodOrderItemList.get(i).getFood_nums();
            totalPrices+=(Integer.parseInt(foodOrderItemList.get(i).getFood_price()))*(foodOrderItemList.get(i).getFood_nums());
        }
        txt_total_goods.setText("总计"+String.valueOf(totalCounts)+"份");
        txt_total_prices.setText("总计"+String.valueOf(totalPrices)+"元");
        btn_submit.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                foodUnorderItemList=Save_Foods_Data.getFoodOrderItemList();
                getActivity().finish();
                ((Activity)getContext()).overridePendingTransition(0, 0);
                Intent intent=new Intent(getContext(), FoodOrderView.class);
                intent.putExtra("isShowOrder","true");
                startActivity(intent);
//                adapter=new recyclerview_foodorder_adapter(getContext(),foodUnorderItemList,true);
//                recyclerView.setAdapter(adapter);
//                adapter.notifyDataSetChanged();
            }
        });
        btn_check.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                if(Save_Foods_Data.getFood_order_view_user()!=null)
                {
                    if(Save_Foods_Data.getFood_order_view_user().isOldUser()==true)
                    {
                        ToastUtil.showToast(getContext(),"您好,老顾客本次你可享受7折优惠");
                    }
                }
            }
        });
    }

    public void setAdapter()
    {
        if(isOrder)
        {
            btn_check.setVisibility(View.VISIBLE);
            adapter=new recyclerview_foodorder_adapter(getContext(),foodUnorderItemList,true);
        }
        if(isNeverOrder)
        {
            btn_submit.setVisibility(View.VISIBLE);
            adapter=new recyclerview_foodorder_adapter(getContext(),foodOrderItemList,false);
            adapter.setButtonClickListener(new recyclerview_foodorder_adapter.onButtonClickListener()
            {
                @Override
                public void click(View view, int positon)
                {
                    View view1=layoutManager.findViewByPosition(positon);
                    TextView txt_food_counts= (TextView) view1.findViewById(R.id.txt_foodorder_nums);
                    TextView txt_food_name= (TextView) view1.findViewById(R.id.txt_foodorder_name);
                    TextView txt_food_price= (TextView) view1.findViewById(R.id.txt_foodorder_price);
                    String foodName=txt_food_name.getText().toString().trim();
                    String foodPrice=txt_food_price.getText().toString().replace("元","").trim();
                    String counts=txt_food_counts.getText().toString().trim();
                    counts=counts.replace("份","").trim();
                    if(Integer.parseInt(counts)>1)
                    {
                        int tempCount=Integer.parseInt(counts);
                        tempCount--;
                        for(int i=0;i<Save_Foods_Data.getFoodOrderItemList().size();i++)
                        {
                            if(Save_Foods_Data.getFoodOrderItemList().get(i).getFood_name().equals(foodName) && Save_Foods_Data.getFoodOrderItemList().get(i).getFood_price().equals(foodPrice))
                            {
                                Save_Foods_Data.getFoodOrderItemList().get(i).setFood_nums(tempCount);
                            }
                        }
                        txt_food_counts.setText(String.valueOf(tempCount)+"份");
                    }
                    else
                    {
                        adapter.removeData(positon);
                        for(int i=0;i<Save_Foods_Data.getFoodOrderItemList().size();i++)
                        {
                            if(Save_Foods_Data.getFoodOrderItemList().get(i).getFood_name().equals(foodName) && Save_Foods_Data.getFoodOrderItemList().get(i).getFood_price().equals(foodPrice))
                            {
                                Save_Foods_Data.getFoodOrderItemList().remove(i);
                            }
                        }
                    }
                    initiateData();
                }
            });
        }
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState)
    {
        View view=inflater.inflate(R.layout.food_order_fragment_container_layout,container,false);
        txt_total_goods= (TextView) view.findViewById(R.id.txt_total_goods);
        txt_total_prices= (TextView) view.findViewById(R.id.txt_total_prices);
        btn_check= (Button) view.findViewById(R.id.btn_check);
        btn_submit= (Button) view.findViewById(R.id.btn_submit);
        recyclerView= (RecyclerView) view.findViewById(R.id.rcv_foodorderview);
        layoutManager=new LinearLayoutManager(getContext(),LinearLayoutManager.VERTICAL,false);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.addItemDecoration(new RecyclerView.ItemDecoration()
        {
            @Override
            public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state)
            {
                super.getItemOffsets(outRect, view, parent, state);
                outRect.set(0,0,0,getResources().getDimensionPixelOffset(R.dimen.dividerHeight));
            }
        });
        initiateData();
        setAdapter();
        recyclerView.setAdapter(adapter);
        return view;
    }
}
