package main;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import net.miginfocom.swing.MigLayout;

public class ListItem extends javax.swing.JPanel {

    private final MigLayout mig;
    private final JPanel panel = new JPanel();
    private final Image imageDelete = new ImageIcon(getClass().getResource("/icon/delete.png")).getImage();
    private final Image imageDeleteDisable = new ImageIcon(getClass().getResource("/icon/delete_disable.png")).getImage();
    private final Image imageInsert = new ImageIcon(getClass().getResource("/icon/insert.png")).getImage();
    private final Image imageOpen = new ImageIcon(getClass().getResource("/icon/open.png")).getImage();
    private boolean deleteAble;

    public ListItem(Font font, String text) {
        initComponents();
        mig = new MigLayout("fillx", "0[fill]0", "0[]0");
        panel.setLayout(new MigLayout("fillx, filly"));
        setLayout(mig);
        JLabel lb = new JLabel(text);
        lb.setFont(font);
        lb.setForeground(new Color(60, 60, 60));
        panel.setBackground(Color.WHITE);
        panel.add(lb);
        this.add(panel, "h 40, w 100%");
    }

    public void setItemColor(Color color) {
        panel.setBackground(color);
    }

    public void movingX(int x) {
        //  Change Miglayout
        mig.setComponentConstraints(panel, "x " + x + ", h 40, w 100%");
        if (x < 0) {
            setBackground(new Color(246, 82, 75));
        } else {
            setBackground(new Color(59, 94, 221));
        }
    }

    @Override
    protected void paintComponent(Graphics grphcs) {
        super.paintComponent(grphcs);
        Graphics2D g2 = (Graphics2D) grphcs;
        g2.setFont(new java.awt.Font("sansserif", 0, 11));
        //  for draw image smooth
        g2.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
        if (deleteAble) {
            g2.drawImage(imageDelete, getWidth() - 33, 5, 20, 20, this);
            g2.setColor(Color.WHITE);
            g2.drawString("Delete", getWidth() - 38, 36);
        } else {
            g2.drawImage(imageDeleteDisable, getWidth() - 33, 5, 20, 20, this);
            g2.setColor(Color.GRAY);
            g2.drawString("Delete", getWidth() - 38, 36);
        }
        g2.setColor(new Color(12, 158, 225));
        g2.fillRect(0, 0, 50, getHeight());
        g2.drawImage(imageInsert, 15, 5, 20, 20, null);
        g2.setColor(Color.WHITE);
        g2.drawString("Insert", 12, 36);
        g2.drawImage(imageOpen, 65, 5, 20, 20, null);
        g2.drawString("Open", 63, 36);
    }

    public boolean isDeleteAble() {
        return deleteAble;
    }

    public void setDeleteAble(boolean deleteAble) {
        this.deleteAble = deleteAble;
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        setBackground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 291, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 40, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
