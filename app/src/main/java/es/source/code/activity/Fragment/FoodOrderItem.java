package es.source.code.activity.Fragment;

/**
 * Created by Administrator on 2018/10/4.
 */

public class FoodOrderItem {
    private String food_name;
    private String food_price;
    private int food_nums;
    private String food_comments;

    public String getFood_comments() {
        return food_comments;
    }

    public void setFood_comments(String food_comments) {
        this.food_comments = food_comments;
    }

    public String getFood_name() {
        return food_name;
    }

    public void setFood_name(String food_name) {
        this.food_name = food_name;
    }

    public int getFood_nums()
    {
        return food_nums;
    }

    public void setFood_nums(int food_nums)
    {
        this.food_nums = food_nums;
    }

    public String getFood_price() {
        return food_price;
    }

    public void setFood_price(String food_price) {
        this.food_price = food_price;
    }
}
