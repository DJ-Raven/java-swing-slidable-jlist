package main;

import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import javax.swing.DefaultListCellRenderer;
import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JList;
import javax.swing.ListCellRenderer;
import javax.swing.ListModel;

public class MyList<E extends Object> extends JList<E> {

    public Color getSelectedColor() {
        return selectedColor;
    }

    public void setSelectedColor(Color selectedColor) {
        this.selectedColor = selectedColor;
    }

    private final DefaultListModel model;
    private Color selectedColor;
    private int movingIndex = -1;
    private final int deleteAble = -50;
    private final int optionAble = 100;
    private int x;
    private int mx;

    public MyList() {
        model = new DefaultListModel();
        super.setModel(model);
        selectedColor = new Color(237, 237, 237);
        addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent me) {
                x = me.getX();
                int index = locationToIndex(me.getPoint());
                if (index != movingIndex) {
                    movingIndex = index;
                    mx = 0;
                } else {
                    if (mx != 0) {
                        x -= optionAble;
                    }
                }
                repaint();
            }

            @Override
            public void mouseReleased(MouseEvent me) {
                if (mx <= deleteAble) {
                    //  remove item
                    model.removeElementAt(movingIndex);
                }
                if (mx >= optionAble) {

                } else {
                    movingIndex = -1;
                    mx = 0;
                }
                setSelectedIndex(movingIndex);
                repaint();
            }

        });
        addMouseMotionListener(new MouseMotionAdapter() {
            @Override
            public void mouseDragged(MouseEvent me) {
                if (movingIndex != -1) {
                    mx = me.getX() - x;
                    repaint();
                }
            }
        });
    }

    @Override
    public ListCellRenderer getCellRenderer() {
        return new DefaultListCellRenderer() {
            @Override
            public Component getListCellRendererComponent(JList<?> jlist, Object o, int index, boolean selected, boolean focus) {
                ListItem item = new ListItem(MyList.this.getFont(), o + "");
                if (index == movingIndex) {
                    item.setItemColor(selectedColor);
                    if (mx <= deleteAble) {
                        mx = deleteAble;
                    }
                    if (mx >= optionAble) {
                        mx = optionAble;
                    }
                    item.movingX(mx);
                    item.setDeleteAble(mx <= deleteAble);
                }
                return item;
            }
        };
    }

    @Override
    public void setModel(ListModel<E> lm) {
        for (int i = 0; i < lm.getSize(); i++) {
            model.addElement(lm.getElementAt(i));
        }
    }

    public void addItem(Object obj) {
        model.addElement(obj);
    }

    private final Image image = new ImageIcon(getClass().getResource("/icon/empty.png")).getImage();

    @Override
    public void paint(Graphics grphcs) {
        super.paint(grphcs);
        if (model.isEmpty()) {
            Graphics2D g2 = (Graphics2D) grphcs;
            //  for draw image smooth
            g2.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
            g2.drawImage(image, getWidth() / 2 - 35, getHeight() / 2 - 35, 70, 70, null);
            g2.setColor(Color.GRAY);
            g2.drawString("Empty Data", getWidth() / 2 - 40, getHeight() / 2 + 45);
        }
    }

}
