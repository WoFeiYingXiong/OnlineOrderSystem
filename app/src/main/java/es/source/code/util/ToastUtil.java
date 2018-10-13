package es.source.code.util;

import android.content.Context;
import android.widget.Toast;

/**
 * Created by Administrator on 2018/10/4.
 */

public class ToastUtil {
    public static Toast toast;
    public static void showToast(Context context,String message)
    {
        if(toast==null)
        {
            toast=Toast.makeText(context,message,Toast.LENGTH_LONG);
        }
        else
        {
            toast.setText(message);
        }
        toast.show();
    }
}
