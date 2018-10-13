package es.source.code.activity.Fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import es.source.code.activity.R;
import es.source.code.util.Save_Foods_Data;

/**
 * Created by Administrator on 2018/10/4.
 */
public class FoodDetailedFragment extends Fragment
{
    private ImageView image_food_picture;
    private TextView txt_food_name,txt_food_price;
    private EditText edit_food_comments;
    private Button btn_order;
    private FoodDetailedItem foodDetailedItem;

    public FoodDetailedFragment()
    {

    }

//    public FoodDetailedFragment(FoodDetailedItem foodDetailedItem)
//    {
//        this.foodDetailedItem = foodDetailedItem;
//    }

    public static FoodDetailedFragment NewInstance(FoodDetailedItem foodDetailedItem)
    {
        FoodDetailedFragment fragment=new FoodDetailedFragment();
        Bundle bundle=new Bundle();
        bundle.putSerializable("FoodDetailedItem",foodDetailedItem);
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
            foodDetailedItem= (FoodDetailedItem) bundle.getSerializable("FoodDetailedItem");
        }
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState)
    {
        View view=inflater.inflate(R.layout.food_detailed_fragment_container_layout,container,false);
        image_food_picture= (ImageView) view.findViewById(R.id.image_food_picture);
        txt_food_name= (TextView) view.findViewById(R.id.txt_food_name);
        txt_food_price= (TextView) view.findViewById(R.id.txt_food_price);
        edit_food_comments= (EditText) view.findViewById(R.id.edit_food_comments);
        txt_food_name.setText(foodDetailedItem.getFood_name());
        txt_food_price.setText(foodDetailedItem.getFood_price()+"元");
        image_food_picture.setImageResource(foodDetailedItem.getDrawable());
        btn_order= (Button) view.findViewById(R.id.btn_order);
        String foodName=foodDetailedItem.getFood_name();
        String foodPrice=foodDetailedItem.getFood_price();
        for(int i = 0;Save_Foods_Data.getFoodOrderItemList()!=null && i< Save_Foods_Data.getFoodOrderItemList().size(); i++)
        {
            String name=Save_Foods_Data.getFoodOrderItemList().get(i).getFood_name();
            String price=Save_Foods_Data.getFoodOrderItemList().get(i).getFood_price();
            if(name.equals(foodName) && price.equals(foodPrice))
            {
                btn_order.setText("退点");
            }
        }
        return view;
    }
}
