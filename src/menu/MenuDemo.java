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
     * 菜单示例
     *
     * @author jianggujin
     *
     */
public class MenuDemo extends JFrame
{
    public MenuDemo()
    {
        super("MenuDemo");
        // 菜单栏
        JMenuBar menuBar = new JMenuBar();
        JMenu file = new JMenu("文件(F)");
        // 设置助记符为F，按下ALT + F 可以触发该菜单
        file.setMnemonic('F');

        JMenuItem open = new JMenuItem("打开");
        JMenuItem quit = new JMenuItem("退出");

        file.add(open);
        // 设置菜单分隔符
        file.addSeparator();
        file.add(quit);

        menuBar.add(file);

        // 设置菜单栏，使用这种方式设置菜单栏可以不占用布局空间
        setJMenuBar(menuBar);

        // 以上为普通菜单，菜单还有一种为弹出式菜单，通过鼠标右击弹出
        final JPopupMenu menu = new JPopupMenu();
        JMenuItem pop = new JMenuItem("编辑");
        menu.add(pop);

        JTextArea textArea = new JTextArea();
        // 添加鼠标事件监听器，使用适配器
        textArea.addMouseListener(new MouseAdapter() {
            public void mouseReleased(MouseEvent e)
            {
                if (e.isPopupTrigger())
                {
                    // 显示弹出式菜单
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
