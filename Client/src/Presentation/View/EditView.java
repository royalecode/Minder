package Presentation.View;

import Business.Entity.User;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * The type Edit view.
 */
public class EditView extends JPanel {
    private JLabel jname;
    private JLabel jage;
    private JComboBox<String> jlanguage;
    private JLabel jlanguageNotEditable;
    private JLabel jtype;
    private JLabel jemail;
    private JTextArea jdesc;
    private BufferedImage logoImage;
    private JLabel logoLabel;
    private JButton southSave;
    private JButton southEdit;
    private JButton southDelete;
    private JButton changebtn;

    private final String[] data = {"Java", "JS", "html", "C++", "Flutter", "C#", "Python", "C", "Android", "Rust"};
    /**
     * The constant EDIT_BTN.
     */
    public static final String EDIT_BTN = "EDIT_BTN";
    /**
     * The constant SAVE_BTN.
     */
    public static final String SAVE_BTN = "SAVE_BTN";
    /**
     * The constant DELETE_BTN.
     */
    public static final String DELETE_BTN = "DELETE_BTN";
    /**
     * The constant CHANGE_BTN.
     */
    public static final String CHANGE_BTN = "CHANGE_BTN";

    /**
     * Instantiates a new Edit view.
     */
    public EditView() {
        setLayout(new BorderLayout());
        setBackground(Color.WHITE);
        setOpaque(true);

        JPanel wrapper = new PanelCustomWrap(new BorderLayout());
        wrapper.add(profileInformation(), BorderLayout.NORTH);
        wrapper.add(centerTextArea(), BorderLayout.CENTER);
        wrapper.setBackground(Color.WHITE);

        JScrollPane sp = new JScrollPane(wrapper);
        sp.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
        sp.setBackground(Color.WHITE);

        add(southButtons(), BorderLayout.SOUTH);
        add(sp, BorderLayout.CENTER);
        updateData();
    }

    private JPanel profileInformation() {
        JPanel profile = new JPanel();
        profile.setBackground(Color.WHITE);

        profile.setLayout(new BoxLayout(profile, BoxLayout.PAGE_AXIS));
        profile.setBorder(new EmptyBorder(10, 10, 10, 10));

        setBorders();

        JPanel imagePanel = imageChange();
        imagePanel.setAlignmentX(Component.LEFT_ALIGNMENT);
        profile.add(imagePanel);
        profile.add(jname);
        profile.add(jage);
        profile.add(jlanguage);
        profile.add(jlanguageNotEditable);
        profile.add(jtype);
        profile.add(jemail);

        return profile;
    }

    private void setBorders() {
        jname = new JLabel();
        jname.setAlignmentX(Component.LEFT_ALIGNMENT);
        jname.setBorder(new EmptyBorder(25, 0, 15, 5));

        jage = new JLabel();
        jage.setAlignmentX(Component.LEFT_ALIGNMENT);
        jage.setBorder(new EmptyBorder(10, 0, 15, 5));

        jlanguage = new JComboBox<>(data);
        jlanguage.setAlignmentX(Component.LEFT_ALIGNMENT);
        jlanguage.setBackground(Color.white);
        jlanguage.setBorder(new EmptyBorder(5, 0, 5, 5));

        jlanguageNotEditable = new JLabel();
        jlanguageNotEditable.setAlignmentX(Component.LEFT_ALIGNMENT);
        jlanguageNotEditable.setBorder(new EmptyBorder(10, 0, 15, 5));

        jemail = new JLabel();
        jemail.setAlignmentX(Component.LEFT_ALIGNMENT);
        jemail.setBorder(new EmptyBorder(10, 0, 15, 5));

        jtype = new JLabel();
        jtype.setAlignmentX(Component.LEFT_ALIGNMENT);
        jtype.setBorder(new EmptyBorder(10, 0, 15, 5));
    }

    private JTextArea centerTextArea() {
        jdesc = new JTextArea(0, 0);
        jdesc.setLineWrap(true);
        jdesc.setWrapStyleWord(true);
        jdesc.setRows(2);
        jdesc.setAlignmentX(Component.LEFT_ALIGNMENT);
        jdesc.setBackground(Color.white);
        jdesc.setBorder(new CompoundBorder(BorderFactory.createLineBorder(Color.black, 1, true),
                BorderFactory.createEmptyBorder(5, 5, 5, 5)));
        return jdesc;
    }

    private JPanel imageChange() {
        JPanel changeImage = new JPanel();
        changeImage.setBackground(Color.WHITE);
        changeImage.setLayout(new BoxLayout(changeImage, BoxLayout.X_AXIS));

        logoImage = null;
        try {
            logoImage = ImageIO.read(new File("Client/Media/avatar.png"));
            logoImage = resize(logoImage, 70, 70);
        } catch (IOException e) {
            e.printStackTrace();
        }
        logoLabel = new JLabel(new ImageIcon(logoImage));

        changebtn = new JButton("Change");
        changebtn.setBackground(Color.decode("#DF4B74"));
        changebtn.setForeground(Color.WHITE);

        logoLabel.setAlignmentY(Component.CENTER_ALIGNMENT);
        changebtn.setAlignmentY(Component.CENTER_ALIGNMENT);
        changeImage.add(logoLabel);
        changeImage.add(Box.createRigidArea(new Dimension(20, 0)));
        changeImage.add(changebtn);

        return changeImage;
    }

    private JPanel southButtons() {
        JPanel butconatiner = new JPanel(new BorderLayout());
        butconatiner.setBackground(Color.WHITE);

        southEdit = new JButton("EDIT");
        southEdit.setBackground(Color.decode("#DF4B74"));
        southEdit.setForeground(Color.WHITE);

        southSave = new JButton("SAVE");
        southSave.setBackground(Color.decode("#DF4B74"));
        southSave.setForeground(Color.WHITE);

        southDelete = new JButton("Delete");
        southDelete.setBackground(Color.WHITE);
        southDelete.setForeground(Color.decode("#DF4B74"));

        JPanel eastBtns = new JPanel();
        eastBtns.add(southEdit);
        eastBtns.add(southSave);
        eastBtns.setBackground(Color.WHITE);

        butconatiner.setBorder(new EmptyBorder(10, 10, 10, 10));
        butconatiner.add(eastBtns, BorderLayout.EAST);
        butconatiner.add(southDelete, BorderLayout.WEST);
        return butconatiner;
    }

    /**
     * Update data.
     *
     * @param user  the user
     * @param image the image
     */
    public void updateData(User user, BufferedImage image) {
        if (image != null) {
            logoImage = null;
            logoImage = resize(image, 100, 100);
            logoLabel.setIcon(new ImageIcon(logoImage));
        }
        jname.setText(user.getFirstName());
        jage.setText(user.getAge() + " years");
        jlanguageNotEditable.setText(user.getProgrammingLanguage());
        jlanguage.setSelectedItem(user.getProgrammingLanguage());
        jemail.setText(user.getEmail());
        jtype.setText(user.getType());
        jdesc.setText(user.getDescription());
    }

    /**
     * Update data.
     */
    public void updateData() {
        jname.setText("Test Test");
        jage.setText("21 years");
        jlanguage.setSelectedItem("Javascript");
        jlanguageNotEditable.setText("JavaScript");
        jemail.setText("test@gmail.com");
        jtype.setText("Premium");
        jdesc.setText("S??c la persona per fer testos.");
    }

    /**
     * Transform to editable.
     */
    public void transformToEditable() {
        jdesc.setEditable(true);
        changebtn.setVisible(true);
        southEdit.setVisible(false);
        southDelete.setVisible(true);
        southSave.setVisible(true);
        jlanguageNotEditable.setVisible(false);
        jlanguage.setVisible(true);
    }

    /**
     * Transfrom to not editable.
     */
    public void transfromToNotEditable() {
        jdesc.setEditable(false);
        changebtn.setVisible(false);
        southDelete.setVisible(false);
        southSave.setVisible(false);
        southEdit.setVisible(true);
        jlanguageNotEditable.setVisible(true);
        jlanguage.setVisible(false);
    }

    /**
     * Gets language.
     *
     * @return the language
     */
    public String getLanguage() {
        return jlanguage.getSelectedItem().toString();
    }

    /**
     * Gets description.
     *
     * @return the description
     */
    public String getDescription() {
        return jdesc.getText();
    }

    /**
     * Sets new image.
     *
     * @param image the image
     */
    public void setNewImage(BufferedImage image) {
        logoImage = null;
        logoImage = resize(image, 100, 100);
        logoLabel.setIcon(new ImageIcon(logoImage));
    }

    /**
     * Register controller.
     *
     * @param listener the listener
     */
    public void registerController(ActionListener listener) {
        southEdit.setActionCommand(EDIT_BTN);
        southEdit.addActionListener(listener);

        southSave.setActionCommand(SAVE_BTN);
        southSave.addActionListener(listener);

        southDelete.setActionCommand(DELETE_BTN);
        southDelete.addActionListener(listener);

        changebtn.setActionCommand(CHANGE_BTN);
        changebtn.addActionListener(listener);
    }

    /**
     * Resize buffered image.
     *
     * @param img  the img
     * @param newW the new w
     * @param newH the new h
     * @return the buffered image
     */
    public static BufferedImage resize(BufferedImage img, int newW, int newH) {
        Image tmp = img.getScaledInstance(newW, newH, Image.SCALE_SMOOTH);
        BufferedImage dimg = new BufferedImage(newW, newH, BufferedImage.TYPE_INT_ARGB);

        Graphics2D g2d = dimg.createGraphics();
        g2d.drawImage(tmp, 0, 0, null);
        g2d.dispose();

        return dimg;
    }
}