/*
 * ImageFrame.java
 *
 * Created on 2007-10-21, 15:29:35
 *
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package ui;

import java.awt.image.BufferedImage;
import javax.swing.ImageIcon;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 *
 * @author ag95300
 */
public class ImageFrame extends JInternalFrame {

    private ImageHolder image;
    private boolean modified = false;
    private ImageFramesContainer container;

    public ImageFrame(String title, ImageHolder img, ImageFramesContainer container, PreviewContainer preview) {
        super(title, true, true, true);

        this.image = img;
        this.container = container;

        JPanel panel = new JPanel();
        JLabel label = new JLabel();

        label.setIcon(new ImageIcon(image.getOriginal()));

        panel.add(label);
        add(panel);

        //TODO add icon
        setSize(image.getOriginal().getWidth(), image.getOriginal().getHeight());
        setVisible(true);
        setAutoscrolls(true);
        setFocusable(true);

        requestFocus();

        addInternalFrameListener(new ImageFrameEvents(this,preview));
        
        addMouseListener(new ImageMouseEvents());

        addMouseMotionListener(new ImageMouseEvents());
        
        System.out.println("ImageFrame.ImageFrame " + image.getOriginal().getWidth() + "x" + image.getOriginal().getHeight());
    }

    public boolean isModified() {
        return modified;
    }

    public BufferedImage getBufferedImage() {
        return image.getOriginal();
    }
          
    public ImageHolder getImage() {
        return image;
    }

    public void close() {
        container.remove(this);
    }
}