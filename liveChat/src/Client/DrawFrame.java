package Client;

import java.awt.BasicStroke;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JColorChooser;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JToolBar;
import javax.swing.filechooser.FileNameExtensionFilter;

public class DrawFrame extends JFrame {
    /**
     * 定义私有型变量
     * 保证数据安全
     */
    private static final long serialVersionUID = 1L;
    BufferedImage image = new BufferedImage(1024, 768, BufferedImage.TYPE_INT_BGR);
    Graphics gr = image.getGraphics();
    Graphics2D g = (Graphics2D) gr;
    DrawCanvas canvas = new DrawCanvas();
    Shapes shape = new Shapes();
    Color foreColor = Color.BLACK;
    Color backColor = Color.WHITE;

    private int x1 = 0;
    private int y1 = 0;
    private int x2 = 0;
    private int y2 = 0;
    private boolean rubber = false;
    private boolean brush = true;
    private boolean lineFlag = false;
    private boolean rectFlag = false;
    private boolean trangleFlag = false;
    private boolean ovilFlag = false;
    private boolean fillovil = false;
    private boolean fillrect = false;
    private boolean cliRect = false;

    private JToolBar toolBar;
    private JButton saveButton;
    private JButton openButton;
    private JButton exitButton;
    private JButton brushButton;
    private JButton eraserButton;
    private JButton clearButton;
    private JButton brushButton1;
    private JButton brushButton2;
    private JButton brushButton3;
    private JButton lineButton;
    private JButton trangleButton;
    private JButton rectButton;
    private JButton ovilButton;
    private JButton fillRect;
    private JButton fillOvil;
    private JButton clipRect;
    private JButton foreButton;
    private JButton backButton;
    private JMenu fileMenu;
    private JMenu helpMenu;
    private JMenuItem saveItem;
    private JMenuItem exitItem;
    private JMenuBar menuBar;
    private JMenuItem openItem;
    private JMenuItem aboutItem;
    private JPanel downPanel;
    private JLabel timer;

    public DrawFrame() {
        //初始化窗口
        setTitle("画图版");
        setBounds(0, 0, 1024, 768);
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        init();
        addListener();

    }

    private void init() {
        // 初始化方法，添加菜单和按钮
        g.setColor(backColor);
        g.fillRect(0, 0, 1024, 768);
        g.setColor(foreColor);
        canvas.setImage(image);
        getContentPane().add(canvas, BorderLayout.CENTER);

        toolBar = new JToolBar();
        getContentPane().add(toolBar, BorderLayout.NORTH);
        downPanel = new JPanel();
        timer = new JLabel("画图板  梁鹏毅3118005329 @ 2020");
        timer.setForeground(Color.BLUE);
        timer.setFont(new Font("楷体", Font.BOLD, 18));
        timer.setSize(200, 30);
        downPanel.add(timer);

        getContentPane().add(downPanel, BorderLayout.SOUTH);
        menuBar = new JMenuBar();
        setJMenuBar(menuBar);
        fileMenu = new JMenu("文件");
        helpMenu = new JMenu("帮助");
        menuBar.add(fileMenu);
        menuBar.add(helpMenu);
        aboutItem = new JMenuItem("关于");
        helpMenu.add(aboutItem);
        openItem = new JMenuItem("打开");
        saveItem = new JMenuItem("保存");
        fileMenu.add(openItem);
        fileMenu.add(saveItem);
        exitItem = new JMenuItem("退出");
        fileMenu.addSeparator();
        fileMenu.add(exitItem);
        saveButton = new JButton("保存");
        saveButton.setToolTipText("保存");
        toolBar.add(saveButton);
        openButton = new JButton("打开文件");
        openButton.setToolTipText("打开文件");
        toolBar.add(openButton);
        toolBar.addSeparator();
        exitButton = new JButton("退出");
        exitButton.setToolTipText("退出");
        toolBar.add(exitButton);
        toolBar.addSeparator();
        eraserButton = new JButton("橡皮");
        eraserButton.setToolTipText("橡皮");
        toolBar.add(eraserButton);
        clearButton = new JButton("清除");
        clearButton.setToolTipText("清除");
        toolBar.add(clearButton);
        brushButton = new JButton("笔刷");
        brushButton.setToolTipText("笔刷");
        toolBar.add(brushButton);
        toolBar.addSeparator();
        brushButton1 = new JButton("小笔刷");
        brushButton1.setToolTipText("小笔刷");
        toolBar.add(brushButton1);
        brushButton2 = new JButton("中笔刷");
        brushButton2.setToolTipText("中笔刷");
        toolBar.add(brushButton2);
        brushButton3 = new JButton("大笔刷");
        brushButton3.setToolTipText("大笔刷");
        toolBar.add(brushButton3);
        toolBar.addSeparator();
        lineButton = new JButton("直线");
        lineButton.setToolTipText("直线");
        toolBar.add(lineButton);
        rectButton = new JButton("空心矩形");
        rectButton.setToolTipText("空心矩形");
        toolBar.add(rectButton);
        fillRect = new JButton("实心矩形");
        fillRect.setToolTipText("实心矩形");
        toolBar.add(fillRect);
        trangleButton = new JButton("三角形");
        toolBar.add(trangleButton);
        ovilButton = new JButton("空心圆");
        ovilButton.setToolTipText("空心圆");
        toolBar.add(ovilButton);
        fillOvil = new JButton("实心圆");
        fillOvil.setToolTipText("实心圆");
        toolBar.add(fillOvil);
        toolBar.addSeparator();
        foreButton = new JButton("前景色");
        foreButton.setToolTipText("前景色");
        toolBar.add(foreButton);
        backButton = new JButton("背景色");
        backButton.setToolTipText("背景色");
        toolBar.add(backButton);
        toolBar.addSeparator();
        clipRect = new JButton("矩形裁剪");
        toolBar.add(clipRect);
        //设置鼠标光标形状
        canvas.setCursor(Cursor.getPredefinedCursor(Cursor.CROSSHAIR_CURSOR));
    }

    private void addListener() {
        canvas.addMouseMotionListener(new MouseMotionAdapter() {
            @Override
            public void mouseDragged(final MouseEvent e) {
                if (brush) {
                    //判断是否是图形绘制模式
                    if (x1 > 0 && y1 > 0) {
                        //判断鼠标是否进入画板区域

                        if (rubber) {
                            //判断是否是橡皮擦
                            g.setColor(backColor);
                            g.fillRect(x1, y1, 20, 20);
                        } else {
                            //不规则线段绘制
                            x2 = e.getX();
                            y2 = e.getY();
                            g.setColor(foreColor);
                            g.drawLine(x1, y1, x2, y2);
                        }
                    }
                    //获取坐标
                    x1 = e.getX();
                    y1 = e.getY();
                    canvas.repaint();
                }
            }
        });

        canvas.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                //获取鼠标按下时的坐标，为图形绘制作准备
                x1 = e.getX();
                y1 = e.getY();
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                //获取鼠标释放时的坐标，并绘制相关图形
                x2 = e.getX();
                y2 = e.getY();

                if (lineFlag) {
                    //直线绘制
                    shape.lineShape(g, x1, y1, x2, y2);
                    canvas.setCursor(Cursor.getPredefinedCursor(Cursor.CROSSHAIR_CURSOR));
                } else if (rectFlag) {
                    //空心矩形绘制
                    shape.rectShape(g, x1, y1, x2 - x1, y2 - y1);
                    canvas.setCursor(Cursor.getPredefinedCursor(Cursor.CROSSHAIR_CURSOR));
                } else if (fillrect) {
                    //实心矩形绘制
                    g.fillRect(x1, y1, x2 - x1, y2 - y1);
                    canvas.setCursor(Cursor.getPredefinedCursor(Cursor.CROSSHAIR_CURSOR));
                } else if (trangleFlag) {
                    //三角形绘制
                    int x[] = new int[3];
                    int y[] = new int[3];
                    x[0] = x1;
                    y[0] = y1;
                    x2 = e.getX();
                    y2 = e.getY();
                    x[1] = x2;
                    x[2] = x1 - x2 / 2;
                    y[1] = y2;
                    y[2] = y2;
                    shape.trangleShape(g, x, y, 3);
                    canvas.setCursor(Cursor.getPredefinedCursor(Cursor.CROSSHAIR_CURSOR));
                } else if (ovilFlag) {
                    //空心圆绘制
                    shape.ovalShape(g, x1, y1, x2 - x1, y2 - y1);
                    canvas.setCursor(Cursor.getPredefinedCursor(Cursor.CROSSHAIR_CURSOR));
                } else if (fillovil) {
                    //实心圆绘制
                    g.fillOval(x1, y1, x2 - x1, y2 - y1);
                    canvas.setCursor(Cursor.getPredefinedCursor(Cursor.CROSSHAIR_CURSOR));
                } else if (cliRect) {
                    //矩形剪裁，覆盖为背景色
                    g.setColor(backColor);
                    g.fillRect(x1, y1, x2 - x1, y2 - y1);
                    g.setColor(foreColor);
                    canvas.setCursor(Cursor.getPredefinedCursor(Cursor.CROSSHAIR_CURSOR));
                }

                else {
                    //坐标归零
                    x1 = 0;
                    y1 = 0;
                }
                //画板重绘
                canvas.repaint();
            }

        });
        //退出按钮
        exitButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {

                int i = JOptionPane.showConfirmDialog(DrawFrame.this, "您确定要退出吗？", "提示", JOptionPane.YES_NO_OPTION);
                if (i == JOptionPane.YES_OPTION) {
                    System.exit(0);
                }
            }

        });
        //退出菜单项
        exitItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int i = JOptionPane.showConfirmDialog(DrawFrame.this, "您确定要退出吗？", "提示", JOptionPane.YES_NO_OPTION);
                if (i == JOptionPane.YES_OPTION) {
                    System.exit(0);
                }
            }
        });
        //保存按钮
        saveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFileChooser chooser = new JFileChooser();
                FileNameExtensionFilter jpgFilter = new FileNameExtensionFilter(".png", "png");
                chooser.setFileFilter(jpgFilter);
                int returnVal = chooser.showSaveDialog(DrawFrame.this);
                if (returnVal == JFileChooser.APPROVE_OPTION) {
                    String selectPath = chooser.getSelectedFile().getPath() + ".png";
                    try {
                        ImageIO.write(image, "png", new File(selectPath));
                        File f = new File(selectPath);
                        if (f.exists()) {
                            JOptionPane.showMessageDialog(DrawFrame.this, "保存成功！", "提示",
                                    JOptionPane.INFORMATION_MESSAGE);
                        }
                    } catch (IOException e1) {
                        e1.printStackTrace();
                    }
                }
            }
        });
        //笔刷设置
        brushButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                brush = true;
                rubber = false;
                rectFlag = false;
                lineFlag = false;
                trangleFlag = false;
                ovilFlag = false;
                cliRect = false;
                canvas.setCursor(Cursor.getPredefinedCursor(Cursor.CROSSHAIR_CURSOR));
            }
        });
        //橡皮擦绘制
        eraserButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                rubber = true;
                brush = true;
                rectFlag = false;
                lineFlag = false;
                trangleFlag = false;
                ovilFlag = false;
                cliRect = false;
                Toolkit kit = Toolkit.getDefaultToolkit();
                Image img = kit.createImage("lib/cursor.png");
                Cursor c = kit.createCustomCursor(img, new Point(0, 0), "clear");
                canvas.setCursor(c);
            }
        });
        //清除画板内容
        clearButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                g.setColor(backColor);
                g.fillRect(0, 0, 1024, 768);
                g.setColor(foreColor);
                canvas.repaint();
            }

        });

        brushButton1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                BasicStroke s = new BasicStroke(1);
                g.setStroke(s);
            }
        });

        brushButton2.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                BasicStroke s = new BasicStroke(5);
                g.setStroke(s);
            }
        });
        //设置笔刷粗细为大笔刷
        brushButton3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                BasicStroke s = new BasicStroke(10);
                g.setStroke(s);

            }

        });
        //直线绘制
        lineButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // TODO Auto-generated method stub
                lineFlag = true;
                rectFlag = false;
                trangleFlag = false;
                ovilFlag = false;
                fillovil = false;
                fillrect = false;
                brush = false;
                cliRect = false;
            }
        });
        //空心矩形绘制
        rectButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // TODO Auto-generated method stub
                rectFlag = true;
                lineFlag = false;
                trangleFlag = false;
                ovilFlag = false;
                fillovil = false;
                fillrect = false;
                brush = false;
                cliRect = false;
            }
        });
        //三角形绘制
        trangleButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // TODO Auto-generated method stub
                trangleFlag = true;
                rectFlag = false;
                lineFlag = false;
                ovilFlag = false;
                fillovil = false;
                fillrect = false;
                brush = false;
                cliRect = false;
            }
        });
        //空心圆绘制
        ovilButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // TODO Auto-generated method stub
                ovilFlag = true;
                trangleFlag = false;
                rectFlag = false;
                lineFlag = false;
                fillovil = false;
                fillrect = false;
                brush = false;
                cliRect = false;
            }
        });
        //背景色设置
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Color bgColor = JColorChooser.showDialog(DrawFrame.this, "背景色", Color.WHITE);
                if (!(bgColor == null)) {
                    backColor = bgColor;
                }
                g.setColor(backColor);
                g.fillRect(0, 0, 1024, 768);
                g.setColor(foreColor);
                canvas.repaint();
            }
        });
        //前景色设置
        foreButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Color frColor = JColorChooser.showDialog(DrawFrame.this, "前景色", Color.BLACK);
                if (!(frColor == null)) {
                    foreColor = frColor;
                }
                g.setColor(foreColor);
                canvas.repaint();
            }
        });
        //打开文件
        openButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                // TODO Auto-generated method stub
                JFileChooser chooser = new JFileChooser();
                FileNameExtensionFilter jpgFilter = new FileNameExtensionFilter(".png", "png");
                chooser.setFileFilter(jpgFilter);
                int returnVal = chooser.showOpenDialog(DrawFrame.this);
                if (returnVal == JFileChooser.APPROVE_OPTION) {
                    File path = chooser.getSelectedFile();
                    Image[] array = new Image[10];
                    try {
                        image = ImageIO.read(path);
                    } catch (IOException e1) {
                        e1.printStackTrace();
                    }
                    array[0] = image;
                    canvas.setImage(array[0]);
                    canvas.repaint();
                    System.out.println(path);
                }
            }
        });
        //实心圆绘制
        fillOvil.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // TODO Auto-generated method stub
                fillovil = true;
                ovilFlag = false;
                trangleFlag = false;
                rectFlag = false;
                lineFlag = false;
                fillrect = false;
                cliRect = false;
                brush = false;
            }
        });
        //实心矩形绘制
        fillRect.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // TODO Auto-generated method stub
                fillrect = true;
                ovilFlag = false;
                trangleFlag = false;
                rectFlag = false;
                lineFlag = false;
                fillovil = false;
                cliRect = false;
                brush = false;
            }
        });
        //打开菜单项
        openItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFileChooser chooser = new JFileChooser();
                FileNameExtensionFilter jpgFilter = new FileNameExtensionFilter(".png", "png");
                chooser.setFileFilter(jpgFilter);
                int returnVal = chooser.showOpenDialog(DrawFrame.this);
                if (returnVal == JFileChooser.APPROVE_OPTION) {
                    File path = chooser.getSelectedFile();
                    Image[] array = new Image[10];
                    try {
                        image = ImageIO.read(path);
                    } catch (IOException e1) {
                        e1.printStackTrace();
                    }
                    array[0] = image;
                    canvas.setImage(array[0]);
                    canvas.repaint();
                    System.out.println(path);
                }
            }
        });
        //矩形裁剪
        clipRect.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // TODO Auto-generated method stub
                cliRect = true;
                fillrect = false;
                ovilFlag = false;
                trangleFlag = false;
                rectFlag = false;
                lineFlag = false;
                fillovil = false;
                brush = false;

            }
        });
        //关于菜单项
        aboutItem.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(DrawFrame.this,
                        "矢量图保存未实现！", "关于",
                        JOptionPane.INFORMATION_MESSAGE);
            }
        });
        //保存图片，菜单项
        saveItem.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                // TODO Auto-generated method stub
                JFileChooser chooser = new JFileChooser();
                FileNameExtensionFilter jpgFilter = new FileNameExtensionFilter(".png", "png");
                chooser.setFileFilter(jpgFilter);
                int returnVal = chooser.showSaveDialog(DrawFrame.this);
                if (returnVal == JFileChooser.APPROVE_OPTION) {
                    String selectPath = chooser.getSelectedFile().getPath() + ".png";
                    try {
                        ImageIO.write(image, "png", new File(selectPath));
                        File f = new File(selectPath);
                        if (f.exists()) {
                            JOptionPane.showMessageDialog(DrawFrame.this, "保存成功！", "提示",
                                    JOptionPane.INFORMATION_MESSAGE);
                        }
                    } catch (IOException e1) {
                        e1.printStackTrace();
                    }
                }
            }
        });
        //窗口事件监听
        this.addWindowListener(new WindowAdapter() {

            @Override
            public void windowClosing(WindowEvent e) {
                //弹出确认框
                int i = JOptionPane.showConfirmDialog(DrawFrame.this, "您确定要退出吗？", "提示", JOptionPane.YES_NO_OPTION);
                if (i == JOptionPane.YES_OPTION) {
                    System.exit(0);
                } else {
                }
            }
        });
    }

}
