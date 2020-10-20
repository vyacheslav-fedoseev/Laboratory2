package bsu.rfe.java.group7.lab2.Fedoseev.varB6;

// Импортируются классы, используемые в приложении
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

@SuppressWarnings("serial")
// Главный класс приложения, он же класс фрейма
public class MainFrame extends JFrame {
    // Размеры окна приложения в виде констант
    private static final int WIDTH = 400;
    private static final int HEIGHT = 320;
    // Текстовые поля для считывания значений переменных,
// как компоненты, совместно используемые в различных методах
    private JTextField textFieldX;
    private JTextField textFieldY;
    private JTextField textFieldZ;
    // Текстовое поле для отображения результата,
// как компонент, совместно используемый в различных методах
    private JTextField textFieldResult;
    // Группа радио-кнопок для обеспечения уникальности выделения в группе
    private ButtonGroup radioButtons = new ButtonGroup();
    // Контейнер для отображения радио-кнопок
    private Box hboxFormulaType = Box.createHorizontalBox();
    private int formulaId = 1;
    // Формула №1 для рассчѐта
    /*public Double calculate1(Double x, Double y) {
        return x*x + y*y;
    }
    // Формула №2 для рассчѐта
    public Double calculate2(Double x, Double y) {
        return x*x*x + 1/y;
    }*/

    public Double calculate1(Double x, Double y, Double z){
        return Math.pow(Math.log((1+z)*(1+z)) + Math.cos(Math.PI*y*y*y),0.25) /
                Math.pow((Math.cos(Math.exp(x)) + Math.sqrt(1/x) + Math.exp(x*x)),Math.sin(x));
    }
    public Double calculate2(Double x, Double y, Double z){
        return (Math.tan(x*x)+Math.sqrt(y))/(z*Math.log10(x+y));
    }

    // Вспомогательный метод для добавления кнопок на панель
    private void addRadioButton(String buttonName, final int formulaId) {
        JRadioButton button = new JRadioButton(buttonName);
        button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ev) {
                MainFrame.this.formulaId = formulaId;
            }
        });
        radioButtons.add(button);
        hboxFormulaType.add(button);
    }

    // Конструктор класса
    public MainFrame() {
        super("Вычисление формулы");
        setSize(WIDTH, HEIGHT);
        Toolkit kit = Toolkit.getDefaultToolkit(); //для взаимодействия с ОС, получения информации и т.п.
// Отцентрировать окно приложения на экране
        setLocation((kit.getScreenSize().width - WIDTH)/2,
                (kit.getScreenSize().height - HEIGHT)/2);

        hboxFormulaType.add(Box.createHorizontalGlue());
        addRadioButton("Формула 1", 1);
        addRadioButton("Формула 2", 2);
        radioButtons.setSelected(
                radioButtons.getElements().nextElement().getModel(), true);
        hboxFormulaType.add(Box.createHorizontalGlue());
        hboxFormulaType.setBorder(
                BorderFactory.createLineBorder(Color.YELLOW));
// Создать область с полями ввода для X и Y
        JLabel labelForX = new JLabel("X:");
        textFieldX = new JTextField("0", 5);
        textFieldX.setMaximumSize(textFieldX.getPreferredSize());
        JLabel labelForY = new JLabel("Y:");
        textFieldY = new JTextField("0", 5);
        textFieldY.setMaximumSize(textFieldY.getPreferredSize());
        JLabel labelForZ = new JLabel("Z:");
        textFieldZ = new JTextField("0", 5);
        textFieldZ.setMaximumSize(textFieldZ.getPreferredSize());
        Box hboxVariables = Box.createHorizontalBox();
        hboxVariables.setBorder(
                BorderFactory.createLineBorder(Color.RED));
        hboxVariables.add(Box.createHorizontalGlue());
        hboxVariables.add(labelForX);
        hboxVariables.add(Box.createHorizontalStrut(10));
        hboxVariables.add(textFieldX);
        hboxVariables.add(Box.createHorizontalStrut(30));
        hboxVariables.add(labelForY);
        hboxVariables.add(Box.createHorizontalStrut(10));
        hboxVariables.add(textFieldY);
        hboxVariables.add(Box.createHorizontalStrut(30));
        hboxVariables.add(labelForZ);
        hboxVariables.add(Box.createHorizontalStrut(10));
        hboxVariables.add(textFieldZ);
        hboxVariables.add(Box.createHorizontalGlue());
// Создать область для вывода результата
        JLabel labelForResult = new JLabel("Результат:");
//labelResult = new JLabel("0");
        textFieldResult = new JTextField("0", 10);
        textFieldResult.setMaximumSize(
                textFieldResult.getPreferredSize());
        Box hboxResult = Box.createHorizontalBox();
        hboxResult.add(Box.createHorizontalGlue());
        hboxResult.add(labelForResult);
        hboxResult.add(Box.createHorizontalStrut(10));
        hboxResult.add(textFieldResult);
        hboxResult.add(Box.createHorizontalGlue());
        hboxResult.setBorder(BorderFactory.createLineBorder(Color.BLUE));
// Создать область для кнопок
        JButton buttonCalc = new JButton("Вычислить");
        buttonCalc.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ev) {
                try {
                    Double x = Double.parseDouble(textFieldX.getText());
                    Double y = Double.parseDouble(textFieldY.getText());
                    Double z = Double.parseDouble(textFieldZ.getText());
                    Double result;
                    if (formulaId==1)
                        result = calculate1(x, y, z);
                    else
                        result = calculate2(x, y, z);
                    textFieldResult.setText(result.toString());
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(MainFrame.this,
                            "Ошибка в формате записи числа с плавающей точкой", "Ошибочный формат числа",
                            JOptionPane.WARNING_MESSAGE);
                }
            }
        });

        JButton buttonReset = new JButton("Очистить поля");
        buttonReset.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ev) {
                textFieldX.setText("0");
                textFieldY.setText("0");
                textFieldZ.setText("0");
                textFieldResult.setText("0");
            }
        });

        Box hboxButtons = Box.createHorizontalBox();
        hboxButtons.add(Box.createHorizontalGlue());
        hboxButtons.add(buttonCalc);
        hboxButtons.add(Box.createHorizontalStrut(30));
        hboxButtons.add(buttonReset);
        hboxButtons.add(Box.createHorizontalGlue());
        hboxButtons.setBorder(
                BorderFactory.createLineBorder(Color.GREEN));

        ////////////////////
        JButton buttonMem1 = new JButton("mem1");
        buttonMem1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ev) {
                textFieldResult.setText("text1");
            }
        });
        JButton buttonMem2 = new JButton("mem2");
        buttonMem2.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ev) {
                textFieldResult.setText("text2");
            }
        });
        JButton buttonMem3 = new JButton("mem3");
        buttonMem3.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ev) {
                textFieldResult.setText("text3");
            }
        });
        Box hboxMems = Box.createHorizontalBox();
        hboxMems.add(Box.createHorizontalGlue());
        hboxMems.add(buttonMem1);
        hboxMems.add(Box.createHorizontalStrut(10));
        hboxMems.add(buttonMem2);
        hboxMems.add(Box.createHorizontalStrut(10));
        hboxMems.add(buttonMem3);
        hboxMems.add(Box.createHorizontalGlue());
        hboxMems.setBorder(
                BorderFactory.createLineBorder(Color.BLACK));
        ////////////////////

        ///////////
        JButton buttonMC = new JButton("MC");
        buttonMC.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ev) {
                textFieldResult.setText("text MC");
            }
        });
        JButton buttonMPlus = new JButton("M+");
        buttonMPlus.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ev) {
                textFieldResult.setText("text M+");
            }
        });

        Box hboxButtonsM = Box.createHorizontalBox();
        hboxButtonsM.add(Box.createHorizontalGlue());
        hboxButtonsM.add(buttonMC);
        hboxButtonsM.add(Box.createHorizontalStrut(15));
        hboxButtonsM.add(buttonMPlus);
        hboxButtonsM.add(Box.createHorizontalGlue());
        hboxButtonsM.setBorder(
                BorderFactory.createLineBorder(Color.PINK));
        ///////////


// Связать области воедино в компоновке BoxLayout
        Box contentBox = Box.createVerticalBox();
        contentBox.add(Box.createVerticalGlue());
        contentBox.add(hboxFormulaType);
        contentBox.add(hboxVariables);
        contentBox.add(hboxResult);
        contentBox.add(hboxButtons);
        contentBox.add(hboxMems);
        contentBox.add(hboxButtonsM);
        contentBox.add(Box.createVerticalGlue());
        getContentPane().add(contentBox, BorderLayout.CENTER);
    }
    // Главный метод класса
    public static void main(String[] args) {
        MainFrame frame = new MainFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
}