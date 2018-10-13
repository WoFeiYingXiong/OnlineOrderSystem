package es.source.code.util;

import java.util.List;

import es.source.code.activity.Fragment.FoodOrderItem;
import es.source.code.model.User;

/**
 * Created by Administrator on 2018/10/5.
 */

public class Save_Foods_Data
{
    static List<FoodOrderItem> foodOrderItemList;

    static User food_view_user;

    static User food_order_view_user;

    static String[] food_drinks_names={"农夫山泉","海之蓝","泸州老窖","大红袍","铁观音","信阳毛尖","哈尔滨啤酒","普洱","怡宝","果粒橙","红茶","百岁山","芬达","黄山毛峰"};
    static String[] food_sea_names={"麻辣河蟹","麻辣大海虾","麻辣香螺","麻辣小龙虾","秘制扇贝肉","灌汤蟹钳","阿根廷红虾","麻辣基围虾","草鱼","黑鱼","鲢鱼","鱿鱼","鲍鱼","黄花鱼"};
    static String[] food_hot_names={"回锅肉","鱼香肉丝","辣子鸡丁","红烧大肠","木耳肉片","孜然羊肉","土豆牛肉","酸菜鱼","炒肉片","梅豆炒肉","青椒肉片","苦瓜炒肉","羊肉小炒","宫爆鸡丁"};
    static String[] food_cold_names={"黄瓜拉皮","凉拌金针菇","洋葱木耳","虾仁拌木耳","杭椒皮蛋","果仁蔬菜","拌土豆丝","菠菜粉丝","水晶肘子","口水鸡","酱香肘花","五香牛肉","日式鹅肝","手撕兔肉"};

    public static String[] getFood_cold_names()
    {
        return food_cold_names;
    }

    public static String[] getFood_drinks_names()
    {
        return food_drinks_names;
    }

    public static String[] getFood_hot_names()
    {
        return food_hot_names;
    }

    public static String[] getFood_sea_names()
    {
        return food_sea_names;
    }

    public static User getFood_order_view_user()
    {
        return food_order_view_user;
    }

    public static void setFood_order_view_user(User food_order_view_user)
    {
        Save_Foods_Data.food_order_view_user = food_order_view_user;
    }

    public static User getFood_view_user()
    {
        return food_view_user;
    }

    public static void setFood_view_user(User food_view_user)
    {
        Save_Foods_Data.food_view_user = food_view_user;
    }

    public static List<FoodOrderItem> getFoodOrderItemList()
    {
        return foodOrderItemList;
    }

    public static void setFoodOrderItemList(List<FoodOrderItem> foodOrderItemList)
    {
        Save_Foods_Data.foodOrderItemList = foodOrderItemList;
    }
}
