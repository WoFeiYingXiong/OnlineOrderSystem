package es.source.code.activity.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import java.util.List;

import es.source.code.activity.Fragment.FoodItem;
import es.source.code.activity.R;
import es.source.code.util.Save_Foods_Data;

/**
 * Created by Administrator on 2018/10/3.
 */

public class recyclerview_food_adapter extends RecyclerView.Adapter<recyclerview_food_adapter.ViewHolder> {
    private Context context;
    private List<FoodItem> foodItemList;
    private onItemClickListener itemClickListener;
    private onButtonClickListener buttonClickListener;

    public interface onItemClickListener{
        void click(View view,int position);
    }
    public interface onButtonClickListener{
        void click(View view,int positon);
    }

    public void setButtonClickListener(onButtonClickListener buttonClickListener) {
        this.buttonClickListener = buttonClickListener;
    }

    public void setItemClickListener(onItemClickListener itemClickListener) {
        this.itemClickListener = itemClickListener;
    }

    public recyclerview_food_adapter(Context context,List<FoodItem> foodItemList) {
        this.context = context;
        this.foodItemList=foodItemList;
    }



    public class ViewHolder extends RecyclerView.ViewHolder{
        private TextView txt_name,txt_price;
        private Button btn_order;
        public ViewHolder(View itemView) {
            super(itemView);
            txt_name= (TextView) itemView.findViewById(R.id.txt_food_name);
            txt_price= (TextView) itemView.findViewById(R.id.txt_food_price);
            btn_order= (Button) itemView.findViewById(R.id.btn_food_order);
        }
    }

    @Override
    public recyclerview_food_adapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context).inflate(R.layout.food_item_layout,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(recyclerview_food_adapter.ViewHolder holder, final int position) {
        holder.txt_name.setText(foodItemList.get(position).getFood_name());
        holder.txt_price.setText(foodItemList.get(position).getFood_price()+"元");
        String name=foodItemList.get(position).getFood_name();
        String price=foodItemList.get(position).getFood_price();
        for(int i=0;Save_Foods_Data.getFoodOrderItemList()!=null && i< Save_Foods_Data.getFoodOrderItemList().size();i++)
        {
            if(Save_Foods_Data.getFoodOrderItemList().get(i).getFood_name().equals(name) && Save_Foods_Data.getFoodOrderItemList().get(i).getFood_price().equals(price))
            {
                holder.btn_order.setText("退点");
            }
        }
        holder.btn_order.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(buttonClickListener!=null)
                {
                    buttonClickListener.click(v,position);
                }
            }
        });
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(itemClickListener!=null)
                {
                    itemClickListener.click(v,position);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return foodItemList.size();
    }
}
