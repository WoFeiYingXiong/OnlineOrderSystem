package es.source.code.activity.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import java.util.List;

import es.source.code.activity.Fragment.FoodOrderItem;
import es.source.code.activity.R;
import es.source.code.util.Save_Foods_Data;

/**
 * Created by Administrator on 2018/10/4.
 */

public class recyclerview_foodorder_adapter extends RecyclerView.Adapter<recyclerview_foodorder_adapter.ViewHolder>
{
    private Context context;
    private boolean isOrder;
    private List<FoodOrderItem> foodOrderOrUnorderItemList;
    private recyclerview_foodorder_adapter.onItemClickListener itemClickListener;
    private recyclerview_foodorder_adapter.onButtonClickListener buttonClickListener;

    public interface onItemClickListener
    {
        void click(View view, int position);
    }

    public interface onButtonClickListener
    {
        void click(View view, int positon);
    }

    public void setButtonClickListener(onButtonClickListener buttonClickListener)
    {
        this.buttonClickListener = buttonClickListener;
    }

    public void setItemClickListener(onItemClickListener itemClickListener)
    {
        this.itemClickListener = itemClickListener;
    }

    public recyclerview_foodorder_adapter(Context context, List<FoodOrderItem> foodOrderOrUnorderItemList, boolean isOrder)
    {
        this.context = context;
        this.isOrder=isOrder;
        this.foodOrderOrUnorderItemList = foodOrderOrUnorderItemList;
    }

    public void removeData(int position)
    {
        FoodOrderItem foodOrderItem= foodOrderOrUnorderItemList.get(position);
        String foodName=foodOrderItem.getFood_name();
        String foodPrice=foodOrderItem.getFood_price();
        foodOrderOrUnorderItemList.remove(position);
        for(int i=0;i< Save_Foods_Data.getFoodOrderItemList().size();i++)
        {
            if(Save_Foods_Data.getFoodOrderItemList().get(i).getFood_name().equals(foodName) && Save_Foods_Data.getFoodOrderItemList().get(i).getFood_price().equals(foodPrice))
            {
                Save_Foods_Data.getFoodOrderItemList().remove(i);
            }
        }
        notifyItemRemoved(position);
        notifyDataSetChanged();
    }

    @Override
    public recyclerview_foodorder_adapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType)
    {
        View view = LayoutInflater.from(context).inflate(R.layout.food_order_item_layout, parent, false);
        return new recyclerview_foodorder_adapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(recyclerview_foodorder_adapter.ViewHolder holder, final int position)
    {
        holder.txt_name.setText(foodOrderOrUnorderItemList.get(position).getFood_name());
        holder.txt_price.setText(foodOrderOrUnorderItemList.get(position).getFood_price()+"元");
        int count= foodOrderOrUnorderItemList.get(position).getFood_nums();
        holder.txt_nums.setText(String.valueOf(count)+"份");
        if(isOrder)
        {
            holder.btn_unorder.setVisibility(View.GONE);
        }
//        holder.txt_comments.setText(foodOrderOrUnorderItemList.get(position).getFood_comments());
        holder.btn_unorder.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                if (buttonClickListener != null)
                {
                    buttonClickListener.click(v, position);
                }
            }
        });
        holder.itemView.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                if (itemClickListener != null)
                {
                    itemClickListener.click(v, position);
                }
            }
        });
    }

    @Override
    public int getItemCount()
    {
        if(foodOrderOrUnorderItemList !=null)
        {
            return foodOrderOrUnorderItemList.size();
        }
        else return 0;
    }

    public class ViewHolder extends RecyclerView.ViewHolder
    {
        private TextView txt_name, txt_price, txt_nums, txt_comments;
        private Button btn_unorder;

        public ViewHolder(View itemView)
        {
            super(itemView);
            txt_name = (TextView) itemView.findViewById(R.id.txt_foodorder_name);
            txt_price = (TextView) itemView.findViewById(R.id.txt_foodorder_price);
            txt_nums = (TextView) itemView.findViewById(R.id.txt_foodorder_nums);
            txt_comments = (TextView) itemView.findViewById(R.id.txt_foodorder_comments);
            btn_unorder = (Button) itemView.findViewById(R.id.btn_foodorder_unorder);
        }
    }
}
