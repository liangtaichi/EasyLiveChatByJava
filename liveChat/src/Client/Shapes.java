package Client;

import java.awt.Graphics2D;

public class Shapes {
    //定义图形绘制方法
    public void rectShape(Graphics2D g,int x,int y,int width,int heigth) {
        //矩形
        g.drawRect(x, y, width, heigth);
    }
    public void lineShape(Graphics2D g,int x1,int y1,int x2,int y2) {
        //直线
        g.drawLine(x1, y1, x2, y2);
    }
    public void ovalShape(Graphics2D g,int x,int y, int width, int height) {
        //圆或椭圆
        g.drawOval(x, y, width, height);
    }
    public void trangleShape(Graphics2D g,int []x,int []y, int pointNum) {
        //三角形
        g.drawPolygon(x, y, pointNum);
    }

}