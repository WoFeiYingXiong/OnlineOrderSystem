package es.source.code.activity.GridView;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

import es.source.code.activity.R;

/**
 * Created by Administrator on 2018/10/2.
 */

public class GridViewAdapter extends BaseAdapter {
    private Context context;
    private boolean isHide;
    private List<MenuItem> menuItemList;
    private LayoutInflater inflater;

    public boolean isHide() {
        return isHide;
    }

    public void setHide(boolean hide) {
        isHide = hide;
    }

    public GridViewAdapter(Context context, List<MenuItem> menuItemList) {
        this.context = context;
        this.menuItemList = menuItemList;
        inflater=LayoutInflater.from(context);
    }

    class ViewHolder
    {
        public TextView txt_content;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder=null;
        if(convertView==null)
        {
            convertView=inflater.inflate(R.layout.gridview_item_layout,parent,false);
            viewHolder=new ViewHolder();
            viewHolder.txt_content= (TextView) convertView.findViewById(R.id.txt_menu);
            viewHolder.txt_content.setSelected(false);
            convertView.setTag(viewHolder);
        }
        else
        {
            viewHolder= (ViewHolder) convertView.getTag();
        }
        switch (position)
        {
            case 0:
                viewHolder.txt_content.setBackground(context.getResources().getDrawable(R.drawable.order_background));
                break;
            case 1:
                viewHolder.txt_content.setBackground(context.getResources().getDrawable(R.drawable.goods_background));
                break;
            case 2:
                viewHolder.txt_content.setBackground(context.getResources().getDrawable(R.drawable.user_background));
                break;
            case 3:
                viewHolder.txt_content.setBackground(context.getResources().getDrawable(R.drawable.help_background));
                break;
            default:
                break;
        }
        MenuItem menuItem=menuItemList.get(position);
        if(menuItem!=null)
        {
            Drawable drawable=context.getResources().getDrawable(menuItem.getDrawable_off());
            drawable.setBounds(0,0,drawable.getMinimumWidth(),drawable.getMinimumHeight());
            viewHolder.txt_content.setCompoundDrawables(null,drawable,null,null);
            viewHolder.txt_content.setText(menuItem.getContent());
//            viewHolder.txt_content.setBackgroundResource(menuItem.getColor());
            viewHolder.txt_content.setTextColor(context.getResources().getColor(R.color.colorGray));
            if((position==0 && isHide) || (position==1 && isHide))
            {
                viewHolder.txt_content.setVisibility(View.INVISIBLE);
            }
        }
        return convertView;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public Object getItem(int position) {
        return menuItemList.get(position);
    }

    @Override
    public int getCount() {
        return menuItemList.size();
    }
}
