package component;

import javax.swing.*;
import java.awt.*;

/**
 * 实现一个带背景的JPanel组件
 */
public class BackGroundPanel extends JPanel {
    private Image backIcon;

    /**
     * @param backIcon 接收图片当作背景
     */
    public BackGroundPanel(Image backIcon) {
        this.backIcon = backIcon;
    }

    /**
     * 先绘制上一次的组件，再添加背景
     * @param g 上一层的组件内容
     */
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(backIcon, 0, 0, this.getWidth(), this.getHeight(), null);
    }
}
