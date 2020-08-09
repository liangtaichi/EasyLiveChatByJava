package Client;


import java.awt.Canvas;
import java.awt.Graphics;
import java.awt.Image;

public class DrawCanvas extends Canvas {
    //继承Canvas类，重写绘制方法
    private Image image=null;

    public void setImage(Image image) {
        this.image = image;
    }
    @Override
    public void paint(Graphics g) {
        // TODO Auto-generated method stub
        //绘制图像
        g.drawImage(image, 0, 0, null);

    }
    @Override
    public void update(Graphics g) {
        // TODO Auto-generated method stub
        paint(g);
    }







}