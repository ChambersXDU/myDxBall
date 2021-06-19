package menu;

import Test.TestGame;
import myDxBall.Map;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Starter  {

    public Starter() {
        initComponents();
    }

    private JButton jButton1;
    private JLabel jLabel1;
    private JPanel jPanel1;
    private JPanel jPanel2;
    private JFrame jFrame;
    private final int screenWidth = Toolkit.getDefaultToolkit().getScreenSize().width;
    private final int screenHeight = Toolkit.getDefaultToolkit().getScreenSize().height;

    private void initComponents() {
        jFrame = new JFrame("DxBall Starter");
        jFrame.setSize(500,500);
        jFrame.setVisible(true);
        jFrame.setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        jFrame.setLayout(new GridLayout(2,1));
        jFrame.setLocation((screenWidth - 200)/2, (screenHeight-200)/2);

        jPanel1 = new JPanel();
        jPanel2 = new JPanel();

        jButton1 = new JButton();

        jLabel1 = new JLabel();


        //jButton1
        jButton1.setText("¿ªÊ¼ÓÎÏ·");
        jButton1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new Thread() {
                    @Override
                    public void run() {
                        super.run();
                        Map map = new Map();
                        map.initGame();
                        map.initUI();
                        map.run();
                    }
                }.start();
            }
        });

        jLabel1.setIcon(new ImageIcon("materials\\DxBall.png"));
        jLabel1.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));

        jPanel1.add(jButton1);
        jPanel2.add(jLabel1);

        jFrame.add(jPanel2);
        jFrame.add(jPanel1);
        jFrame.pack();

    }

    public static void main(String[] args) {
        Starter starter = new Starter();
    }

}
