package com.ui.JTextField;

import javax.swing.*;
import javax.swing.plaf.metal.MetalTextFieldUI;
import java.awt.*;

/**
 * Created by 356 on 14-8-31.
 */
public class FreeTextField extends JTextField {
    Image left;
    Image right;
    Image center;
    Color BLACK = new Color(0,0,0);

    public FreeTextField() {
        super();
//        this.left = left;
//        this.right = right;
//        this.center = center;
//        init();
        this.getInsets();
        this.setForeground(BLACK);
        this.setBorder(null);
    }

    @Override
    public Insets getInsets() {
        return new Insets(0, 3, 0, 10);
    }

    private void init() {

        this.setUI(new MetalTextFieldUI() {


            protected void paintBackground(Graphics g) {

                Graphics2D g2d = (Graphics2D) g;
                g2d.fillRect(0, 0, getWidth(), getHeight());

                // 每一副图像的位置坐标
                int x = 3;

                // 平铺背景图片
                while (true) {
                    // 绘制图片
                    g2d.drawImage(center, x, 0, null);

                    // 如果绘制完毕
                    if (x > getSize().width)
                        break;

                        // 如果在当前行，得到下图片的坐标位
                    else
                        x += center.getWidth(null);
                }
                g2d.drawImage(left, 0, 0, null);

                g2d.drawImage(right, getWidth()
                        - right.getWidth(null), 0, null);

            }
        });
    }
}
