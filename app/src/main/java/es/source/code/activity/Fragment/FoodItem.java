package es.source.code.activity.Fragment;

/**
 * Created by Administrator on 2018/10/4.
 */

public class FoodItem {
    private String food_name;
    private String food_price;
    private boolean isOrder;
    private int counts;

    public int getCounts()
    {
        return counts;
    }

    public void setCounts(int counts)
    {
        this.counts = counts;
    }

    public boolean isOrder()
    {
        return isOrder;
    }

    public void setOrder(boolean order)
    {
        isOrder = order;
    }

    public String getFood_name() {
        return food_name;

    }

    public void setFood_name(String food_name) {
        this.food_name = food_name;
    }

    public String getFood_price() {
        return food_price;
    }

    public void setFood_price(String food_price) {
        this.food_price = food_price;
    }
}
