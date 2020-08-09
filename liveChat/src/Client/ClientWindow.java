package Client;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/*客户端主窗实现*/
public class ClientWindow extends JFrame {
    private JPanel contentPanel;
    private JTextField ip;
    private JTextField user;
    private JTextField password;
    private JTextField send;
    private JTextArea textArea;
    public void ClientWindow(){
        setTitle("Client");
        setAlwaysOnTop(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(500,500,700,500);
        contentPanel = new JPanel();
        contentPanel.setBorder(new EmptyBorder(5,5,5,5));
        setContentPane(contentPanel);
        /*
        *ip栏、账号密码栏
        *备注：其中127.0.0.1是回送地址，即本机ip
        */
        ip = new JTextField();
        ip.setText("127.0.0.1");
        ip.setColumns(10);
        user = new JTextField();
        user.setText("账号");
        user.setColumns(10);
        password = new JTextField();
        password.setText("密码");
        password.setColumns(10);

        /*发送栏*/
        send = new JTextField();
        /*回车发送*/
        send.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER){
                    ChatManage.getCM().send(send.getText());
                    appendText("我： " + send.getText());
                    send.setText("");
                }
            }
        });
        send.setText("");
        send.setColumns(10);
        /*登陆/发送键*/
        JButton sendButton = new JButton("登陆/发送");
        sendButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                ChatManage.getCM().send(send.getText());
                appendText("我： \n" + send.getText());
                send.setText("");
            }
        });
        /*连接按钮*/
        JButton loginButton = new JButton("连接服务器");
        loginButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                ChatManage.getCM().connect(ip.getText(),user.getText(),password.getText()
                );
            }
        });
        /*画图按钮*/
        JButton drawButton = new JButton("画图");
        drawButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                DrawFrame draw = new DrawFrame();
                draw.setVisible(true);
            }
        });
        /*整体界面设计*/
        JScrollPane scrollPane = new JScrollPane();
        GroupLayout groupLayoutContentPane = new GroupLayout(contentPanel);
        groupLayoutContentPane.setHorizontalGroup(
                groupLayoutContentPane.createParallelGroup(GroupLayout.Alignment.TRAILING)
                        .addGroup(groupLayoutContentPane.createSequentialGroup()
                                .addGroup(groupLayoutContentPane.createParallelGroup(GroupLayout.Alignment.TRAILING)
                                        .addComponent(scrollPane, GroupLayout.Alignment.LEADING,GroupLayout.DEFAULT_SIZE,600,Short.MAX_VALUE)
                                        .addGroup(groupLayoutContentPane.createSequentialGroup()
                                                        /*ip、账号密码以及登陆按钮*/
                                                        .addComponent(ip,GroupLayout.DEFAULT_SIZE,120,Short.MAX_VALUE)
                                                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                                        .addComponent(user,GroupLayout.DEFAULT_SIZE,120,Short.MAX_VALUE)
                                                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                                        .addComponent(password,GroupLayout.DEFAULT_SIZE,120,Short.MAX_VALUE)
                                                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                                        .addComponent(loginButton,GroupLayout.DEFAULT_SIZE,80,Short.MAX_VALUE))
                                        .addGroup(groupLayoutContentPane.createSequentialGroup()
                                                /*发送栏以及发送按钮*/
                                                .addComponent(send,GroupLayout.DEFAULT_SIZE,400,Short.MAX_VALUE)
                                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(sendButton,GroupLayout.DEFAULT_SIZE,150,Short.MAX_VALUE)
                                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(drawButton,GroupLayout.DEFAULT_SIZE,20,Short.MAX_VALUE)
                                        )
                                )
                                .addGap(0))
        );
        groupLayoutContentPane.setVerticalGroup(
                groupLayoutContentPane.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(groupLayoutContentPane.createSequentialGroup()
                                .addGroup(groupLayoutContentPane.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                        .addComponent(ip,GroupLayout.PREFERRED_SIZE,36,GroupLayout.PREFERRED_SIZE)
                                        .addComponent(user,GroupLayout.PREFERRED_SIZE,36,GroupLayout.PREFERRED_SIZE)
                                        .addComponent(password,GroupLayout.PREFERRED_SIZE,36,GroupLayout.PREFERRED_SIZE)
                                        .addComponent(loginButton,GroupLayout.PREFERRED_SIZE,36,GroupLayout.PREFERRED_SIZE))
                                .addGap(4)
                                .addComponent(scrollPane,GroupLayout.DEFAULT_SIZE,300,Short.MAX_VALUE)
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(groupLayoutContentPane.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                        .addComponent(send,GroupLayout.PREFERRED_SIZE,40,GroupLayout.PREFERRED_SIZE)
                                        .addComponent(sendButton,GroupLayout.PREFERRED_SIZE,30,GroupLayout.PREFERRED_SIZE)
                                        .addComponent(drawButton,GroupLayout.PREFERRED_SIZE,30,GroupLayout.PREFERRED_SIZE)
                                )
                        )
        );
        textArea = new JTextArea();
        scrollPane.setViewportView(textArea);
        textArea.setText("准备就绪");
        contentPanel.setLayout(groupLayoutContentPane);

    }
    /*追加文本*/
    public void appendText(String text) {
        textArea.append("\n" + text);
    }
}
