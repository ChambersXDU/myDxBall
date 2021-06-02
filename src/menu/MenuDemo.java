package menu;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
    /**
     * �˵�ʾ��
     *
     * @author jianggujin
     *
     */
public class MenuDemo extends JFrame
{
    public MenuDemo()
    {
        super("MenuDemo");
        // �˵���
        JMenuBar menuBar = new JMenuBar();
        JMenu file = new JMenu("�ļ�(F)");
        // �������Ƿ�ΪF������ALT + F ���Դ����ò˵�
        file.setMnemonic('F');

        JMenuItem open = new JMenuItem("��");
        JMenuItem quit = new JMenuItem("�˳�");

        file.add(open);
        // ���ò˵��ָ���
        file.addSeparator();
        file.add(quit);

        menuBar.add(file);

        // ���ò˵�����ʹ�����ַ�ʽ���ò˵������Բ�ռ�ò��ֿռ�
        setJMenuBar(menuBar);

        // ����Ϊ��ͨ�˵����˵�����һ��Ϊ����ʽ�˵���ͨ������һ�����
        final JPopupMenu menu = new JPopupMenu();
        JMenuItem pop = new JMenuItem("�༭");
        menu.add(pop);

        JTextArea textArea = new JTextArea();
        // �������¼���������ʹ��������
        textArea.addMouseListener(new MouseAdapter() {
            public void mouseReleased(MouseEvent e)
            {
                if (e.isPopupTrigger())
                {
                    // ��ʾ����ʽ�˵�
                    menu.show(e.getComponent(), e.getX(), e.getY());
                }
            }
        });
        add(new JScrollPane(textArea));

        setSize(400, 300);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);
    }

    public static void main(String[] args)
    {
        new MenuDemo();
    }
}
