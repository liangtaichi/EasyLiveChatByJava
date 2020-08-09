package Client;

import java.awt.*;

public class Client {
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                try {
                    ClientWindow frame = new ClientWindow();
                    frame.ClientWindow();
                    frame.setVisible(true);
                    ChatManage.getCM().setWindow(frame);
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        });
    }
}
