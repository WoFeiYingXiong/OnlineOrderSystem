package es.source.code.activity.GridView;

/**
 * Created by Administrator on 2018/10/2.
 */

public class MenuItem {
    private String content;
    private int color;
    private int drawable_on;
    private int drawable_off;
    public String background;

    public String getBackground() {
        return background;
    }

    public void setBackground(String background) {
        this.background = background;
    }

    public int getDrawable_off() {
        return drawable_off;
    }

    public void setDrawable_off(int drawable_off) {
        this.drawable_off = drawable_off;
    }

    public int getDrawable_on() {
        return drawable_on;
    }

    public void setDrawable_on(int drawable_on) {
        this.drawable_on = drawable_on;
    }

    public int getColor() {
        return color;
    }

    public void setColor(int color) {
        this.color = color;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
