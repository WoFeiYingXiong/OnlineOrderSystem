package es.source.code.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;

public class SCOSEntry extends AppCompatActivity {
    private float x_from,y_from,x_to,y_to;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.entry);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if(event.getAction()==MotionEvent.ACTION_DOWN)
        {
            x_from=event.getX();
            y_from=event.getY();
        }
        if(event.getAction()==MotionEvent.ACTION_UP)
        {
            x_to=event.getX();
            y_to=event.getY();
            if(x_from-x_to>10)
            {
                Intent intent=new Intent(SCOSEntry.this,MainScreen.class);
                Bundle bundle=new Bundle();
                bundle.putString("information","FromEntry1");
                intent.putExtras(bundle);
                startActivity(intent);
            }
        }
        return super.onTouchEvent(event);
    }
}
