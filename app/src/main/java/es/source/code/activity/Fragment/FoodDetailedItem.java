package es.source.code.activity.Fragment;

import java.io.Serializable;

/**
 * Created by Administrator on 2018/10/4.
 */

public class FoodDetailedItem implements Serializable
{
    private String food_name,food_price;
    private int drawable;
    private String food_comments;

    public int getDrawable()
    {
        return drawable;
    }

    public void setDrawable(int drawable)
    {
        this.drawable = drawable;
    }

    public String getFood_comments()
    {
        return food_comments;
    }

    public void setFood_comments(String food_comments)
    {
        this.food_comments = food_comments;
    }

    public String getFood_name()
    {
        return food_name;
    }

    public void setFood_name(String food_name)
    {
        this.food_name = food_name;
    }

    public String getFood_price()
    {
        return food_price;
    }

    public void setFood_price(String food_price)
    {
        this.food_price = food_price;
    }
}
