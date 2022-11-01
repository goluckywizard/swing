// Абдулкин Андрей Олегович
// группа ИТСС-О-19/1, вариант 1
// Работа №3. Создание программ с графическим интерфейсом, задание 2
/*
К созданному интерфейсу из задания 1 добавить интерактивность
Вариант 1
*/
import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

class clickListener implements MouseListener {
    Main mainFrame;
    int i;

    public clickListener(Main mainFrame, int i) {
        this.mainFrame = mainFrame;
        this.i = i;
    }

    public void mouseClicked(MouseEvent e) {
        mainFrame.textFields[0].setText("Кнопка " + i +" нажата!");
    }
    public void mousePressed(MouseEvent e) {}
    public void mouseReleased(MouseEvent e) {}
    public void mouseEntered(MouseEvent e) {}
    public void mouseExited(MouseEvent e) {}
}

class itemListener implements ItemListener {
    Main mainFrame;

    public itemListener(Main mainFrame) {
        this.mainFrame = mainFrame;
    }

    public void itemStateChanged(ItemEvent e) {
        if (mainFrame.textArea.getFont().isItalic()) {
            Font font = new Font("Times new roman", Font.PLAIN, 12);
            mainFrame.textArea.setFont(font);
        } else {
            Font font = new Font("Times new roman", Font.ITALIC, 12);
            mainFrame.textArea.setFont(font);
        }
    }
}

class sliderListener implements ChangeListener {
    Main mainFrame;

    public sliderListener(Main mainFrame) {
        this.mainFrame = mainFrame;
    }

    public void stateChanged(ChangeEvent e) {
        mainFrame.s1.setValue(mainFrame.slider.getValue());
    }
}

class MoveListener implements MouseListener {
    Main mainFrame;

    public MoveListener(Main mainFrame) {
        this.mainFrame = mainFrame;
    }

    public void mouseClicked(MouseEvent e) {}
    public void mousePressed(MouseEvent e) {}
    public void mouseReleased(MouseEvent e) {}
    public void mouseEntered(MouseEvent e) {
        for (int i = 1; i < 9; i++) {
            mainFrame.checkBoxes[i].setText("Не жми на нас!");
        }
    }
    public void mouseExited(MouseEvent e) {
        for (int i = 1; i < 9; i++) {
            mainFrame.checkBoxes[i].setText("Выбор" + (i+1));
        }
    }
}

public class Main extends JFrame {
    public JFrame myFrame;
    public JTextArea textArea;
    public JCheckBox[] checkBoxes;
    public JRadioButton b1;
    public JRadioButton b2;
    public JSpinner s1;
    public JSpinner s2;
    public JSlider slider;
    public JTextField[] textFields;
    public static void main(String[] args) {
        new Main();
    }
    public Main() {
        myFrame = new JFrame("Вариант 1");
        myFrame.setDefaultCloseOperation(EXIT_ON_CLOSE);
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout());
        JTextField[] textFields = new JTextField[6];
        for (int i = 0; i < 6; i++) {
            textFields[i] = new JTextField("Текстовое поле " + (i+1));
        }
        JPanel textPanel = new JPanel(new GridLayout(2, 3, 0, 0));
        for (int i = 0; i < 6; i++) {
            textPanel.add(textFields[i]);
        }
        JTextArea textArea = new JTextArea("Текстовая область");
        JSlider slider = new JSlider();
        slider.addChangeListener(new sliderListener(this));
        JPanel leftPanel = new JPanel();
        GroupLayout groupLayout = new GroupLayout(leftPanel);
        leftPanel.setLayout(groupLayout);

        JCheckBox[] checkBox = new JCheckBox[9];
        GroupLayout.ParallelGroup checkGroup = groupLayout.createParallelGroup();
        for (int i = 0; i < 9; i++) {
            checkBox[i] = new JCheckBox("Выбор" + (i+1));
            checkGroup.addComponent(checkBox[i]);
        }
        checkBox[0].addItemListener(new itemListener(this));
        for (int i = 1; i < 9; i++) {
            checkBox[i].addMouseListener(new MoveListener(this));
        }

        JSpinner spinner1 = new JSpinner();
        JSpinner spinner2 = new JSpinner();
        ButtonGroup radioButtons = new ButtonGroup();
        JRadioButton button1 = new JRadioButton("Радиокнопка 1");
        JRadioButton button2 = new JRadioButton("Радиокнопка 2");
        button1.addMouseListener(new clickListener(this, 1));
        button2.addMouseListener(new clickListener(this, 2));
        radioButtons.add(button1);
        radioButtons.add(button2);
        groupLayout.setHorizontalGroup(groupLayout.createSequentialGroup()
                .addGroup(checkGroup)
                .addGroup(groupLayout.createParallelGroup(GroupLayout.Alignment.LEADING, false)
                        .addComponent(spinner1))
                .addGroup(groupLayout.createParallelGroup(GroupLayout.Alignment.LEADING, false)
                        .addComponent(spinner2, 30, 30, 30)
                        .addComponent(button2))
                .addComponent(button1));
        groupLayout.setVerticalGroup(groupLayout.createSequentialGroup()
                .addGroup(groupLayout.createParallelGroup(GroupLayout.Alignment.LEADING, false)
                        .addComponent(checkBox[0])
                        .addComponent(spinner1)
                        .addComponent(spinner2)
                        .addComponent(button1))
                .addGroup(groupLayout.createParallelGroup()
                        .addComponent(checkBox[1])
                        .addComponent(button2))
                .addComponent(checkBox[2])
                .addComponent(checkBox[3])
                .addComponent(checkBox[4])
                .addComponent(checkBox[5])
                .addComponent(checkBox[6])
                .addComponent(checkBox[7])
                .addComponent(checkBox[8])
                );
        textArea.setColumns(30);
        JScrollPane areaPanel = new JScrollPane(textArea);
        //areaPanel.add(textArea);
        myFrame.setSize(650, 450);
        areaPanel.setMinimumSize(new Dimension(300, 350));
        areaPanel.setSize(325, 350);
        myFrame.getContentPane().add(textPanel, BorderLayout.NORTH);
        myFrame.getContentPane().add(slider, BorderLayout.SOUTH);
        myFrame.getContentPane().add(areaPanel, BorderLayout.EAST);
        myFrame.getContentPane().add(leftPanel, BorderLayout.WEST);
        myFrame.setResizable(false);
        myFrame.setVisible(true);

        this.textArea = textArea;
        this.checkBoxes = checkBox;
        this.b1 = button1;
        this.b2 = button2;
        this.textFields = textFields;
        this.s1 = spinner1;
        this.s2 = spinner2;
        this.slider = slider;
    }
}
