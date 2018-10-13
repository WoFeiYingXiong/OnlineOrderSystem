package es.source.code.activity;

import android.content.Intent;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import es.source.code.activity.GridView.GridViewAdapter;
import es.source.code.activity.GridView.MenuItem;
import es.source.code.model.User;

public class MainScreen extends AppCompatActivity {
    private GridView gridView;
    private GridViewAdapter adapter;
    private boolean isHide;
    private User myUser;
    private boolean isUserNull=true;
    private String[] menus={"点菜","查看订单","登录/注册","系统帮助"};
    private int[] drawables_on={R.drawable.icon_order_on,R.drawable.icon_goods_on,R.drawable.icon_user_on,R.drawable.icon_help_on};
    private int[] drawables_off={R.drawable.icon_order_off,R.drawable.icon_goods_off,R.drawable.icon_user_off,R.drawable.icon_help_off};
    private int[] colors={R.color.colorAccent,R.color.colorOrange,R.color.colorRed,R.color.colorPrimary};
    private String[] backgrounds={"order_background","goods_background","user_background","help_background"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_screen_layout);
        initiate();
    }

    /**
     * 获得返回的数据
     * @param requestCode 启动活动时传入的请求码
     * @param resultCode 返回数据时传入的处理结果
     * @param data 携带返回数据的Intent
     */
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (requestCode)
        {
            case 1:
                if(resultCode==RESULT_OK)
                {
                    User user= (User) data.getSerializableExtra("login_user");
                    String returnData=data.getExtras().getString("from_login_button");
                    if(returnData!=null && returnData.equals("LoginSuccess"))
                    {
                        isUserNull=false;
                        myUser=user;
                        if(isHide)
                        {
                            adapter.setHide(false);
                            gridView.setAdapter(adapter);
                        }
                    }
                }
                if(resultCode==RESULT_CANCELED)
                {
                    String returnData=data.getExtras().getString("from_return_button");
                    if(returnData!=null && returnData.equals("Return"))
                    {
                        gridView.setAdapter(adapter);
                        Toast.makeText(MainScreen.this,returnData,Toast.LENGTH_LONG).show();
                    }
                }
                if(resultCode ==2)
                {
                    User user= (User) data.getSerializableExtra("register_user");
                    String returnData=data.getExtras().getString("from_register_button");
                    if(returnData !=null && returnData.equals("RegisterSuccess"))
                    {
                        myUser=user;
                        isUserNull=false;
                        showToast("欢迎您成为 SCOS 新用户");
                        if(isHide)
                        {
                            adapter.setHide(false);
                            gridView.setAdapter(adapter);
                        }
                        gridView.setAdapter(adapter);
                        Toast.makeText(MainScreen.this,returnData,Toast.LENGTH_LONG).show();
                    }
                }
                break;
            default:
                break;
        }
    }

    /**
     * 自定义Toast
     * @param message 要显示的信息文本
     */
    public void showToast(String message)
    {
        Toast toastCustom=new Toast(MainScreen.this);
        View view= LayoutInflater.from(MainScreen.this).inflate(R.layout.toast_custom_layout,null);
        ImageView imageView= (ImageView) view.findViewById(R.id.image_toastCustom);
        TextView textView= (TextView) view.findViewById(R.id.txt_toastCustom);
        imageView.setImageResource(R.drawable.welcome);
        textView.setText(message);
        toastCustom.setView(view);
        toastCustom.setDuration(Toast.LENGTH_LONG);
        toastCustom.setGravity(Gravity.CENTER,0,0);
        toastCustom.show();
    }

    /**
     * 初始化数据
     */
    public void initiate()
    {
        myUser=new User();
        gridView= (GridView) findViewById(R.id.gridView_menu);
        final List<MenuItem> menuItemList=new ArrayList<>();
        for (int i=0;i<menus.length;i++)
        {
            MenuItem menuItem=new MenuItem();
            menuItem.setContent(menus[i]);
            menuItem.setColor(colors[i]);
            menuItem.setDrawable_on(drawables_on[i]);
            menuItem.setDrawable_off(drawables_off[i]);
            menuItem.setBackground(backgrounds[i]);
            menuItemList.add(menuItem);
        }
        final Intent intent=getIntent();
        String message=intent.getExtras().getString("information");
//        Toast.makeText(MainScreen.this,message,Toast.LENGTH_LONG).show();
        if(message!=null && !message.equals("FromEntry"))
        {
            isHide=true;
        }
        adapter=new GridViewAdapter(MainScreen.this,menuItemList);
        adapter.setHide(isHide);
        gridView.setAdapter(adapter);
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                MenuItem menuItem=menuItemList.get(position);
                Resources resources=getResources();
//                TextView textView= (TextView) gridView.getChildAt(position).findViewById(R.id.txt_menu);
                LinearLayout linearLayout= (LinearLayout) gridView.getAdapter().getView(position,view,null);
                TextView textView= (TextView) linearLayout.getChildAt(0);
//                Toast.makeText(MainScreen.this,textView.getText().toString(),Toast.LENGTH_LONG).show();
                Drawable drawable=resources.getDrawable(menuItem.getDrawable_on());
                drawable.setBounds(0,0,drawable.getMinimumWidth(),drawable.getMinimumHeight());
                textView.setSelected(true);
                textView.setCompoundDrawables(null,drawable,null,null);
                textView.setTextColor(getResources().getColor(R.color.colorWhite));
//                String data=textView.getText().toString().trim();
//                ToastUtil.showToast(MainScreen.this,String.valueOf(position));
                switch (position)
                {
                    case 0:
                        Intent intent1=new Intent(MainScreen.this,FoodView.class);
                        Bundle bundle = new Bundle();
                        bundle.putSerializable("food_view_user",myUser);
                        intent1.putExtras(bundle);
                        startActivity(intent1);
                        break;
                    case 1:
                        Intent intent2=new Intent(MainScreen.this,FoodOrderView.class);
                        Bundle bundle1 = new Bundle();
                        bundle1.putSerializable("food_order_view_user",myUser);
                        intent2.putExtras(bundle1);
                        startActivity(intent2);
                        break;
                    case 2:
                        Intent intent3=new Intent(MainScreen.this,LoginOrRegister.class);
                        startActivityForResult(intent3,1);
                        break;
                    case 3:
                        break;
                    default:
                        break;
                }
//                if(data!=null && data.equals("登录/注册"))
//                {
//                    Intent intent1=new Intent(MainScreen.this,LoginOrRegister.class);
//                    startActivityForResult(intent1,1);
//                }
//                if(data!=null && data.equals("点菜"))
//                {
//                    Intent intent1=new Intent(MainScreen.this,FoodView.class);
//                    Bundle bundle = new Bundle();
//                    bundle.putSerializable("food_view_user",myUser);
//                    intent1.putExtras(bundle);
//                    startActivity(intent1);
//                }
//                if(data!=null && data.equals("查看订单"))
//                {
//                    Intent intent1=new Intent(MainScreen.this,FoodOrderView.class);
//                    Bundle bundle = new Bundle();
//                    bundle.putSerializable("food_order_view_user",myUser);
//                    intent1.putExtras(bundle);
//                    startActivity(intent1);
//                }
            }
        });
        if(isUserNull)
        {
            myUser=null;
        }
    }
}
