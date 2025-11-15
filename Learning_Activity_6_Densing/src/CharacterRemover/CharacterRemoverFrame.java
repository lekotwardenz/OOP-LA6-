package CharacterRemover;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class CharacterRemoverFrame extends JFrame {
    private JPanel ContentPanel;
    private JTextField textLabel;
    private JCheckBox vowelCheckbox;
    private JCheckBox numberCheckbox;
    private JCheckBox consonantCheckbox;
    private JButton restoreButton;
    private JButton removeButton;
    private JPanel option_Panel;
    private JPanel buttonPanel;
    private JPanel setting_Panel;

    private boolean checkboxIsChecked = false;
    private String str = "Enter string here";

    private boolean isVowel(char c) {
        c = Character.toLowerCase(c);

        if (c == 'a' || c == 'e' || c == 'i' || c == 'o' || c == 'u') {
            return true;
        }

        return false;
    }

    private boolean isConsonant(char c) {
        c = Character.toLowerCase(c);
        return Character.isLetter(c) && !isVowel(c);
    }

    private void function() {
        StringBuilder result = new StringBuilder();

        textLabel.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (vowelCheckbox.isSelected() || numberCheckbox.isSelected() || consonantCheckbox.isSelected()) {
                    vowelCheckbox.setSelected(false);
                    numberCheckbox.setSelected(false);
                    consonantCheckbox.setSelected(false);
                    checkboxIsChecked = false;
                }
            }
        });

        ItemListener saveText = e -> {
            if (e.getStateChange() == ItemEvent.SELECTED) {
                if (!checkboxIsChecked) {
                    str = textLabel.getText();
                    System.out.println(str);
                    checkboxIsChecked = true;
                }
            }
        };
        vowelCheckbox.addItemListener(saveText);
        numberCheckbox.addItemListener(saveText);
        consonantCheckbox.addItemListener(saveText);

        restoreButton.addActionListener(e -> {
            textLabel.setText(str);
            vowelCheckbox.setSelected(false);
            numberCheckbox.setSelected(false);
            consonantCheckbox.setSelected(false);
            checkboxIsChecked = false;
        });

        removeButton.addActionListener(e -> {
            if (checkboxIsChecked) {
                String toConvert = textLabel.getText();

                for (char c : toConvert.toCharArray()) {
                    boolean append = true;

                    if (vowelCheckbox.isSelected() && isVowel(c)) {
                        append = false;
                    }

                    if (consonantCheckbox.isSelected() && isConsonant(c)) {
                        append = false;
                    }

                    if (numberCheckbox.isSelected() && Character.isDigit(c)) {
                        append = false;
                    }

                    if (append) {
                        result.append(c);
                    }
                }

                textLabel.setText(result.toString());
                result.setLength(0);
            }
        });

    }

    public CharacterRemoverFrame() {
        setTitle("Character Remover");
        setPreferredSize(new Dimension(500, 300));
        setBackground(Color.white);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(false);

        createUIComponents();
        function();

        add(ContentPanel);

        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }

    private void createUIComponents() {
        // TODO: place custom component creation code here
        vowelCheckbox.setName("vowelCheckbox");
        consonantCheckbox.setName("consonantCheckbox");
        numberCheckbox.setName("numberCheckbox");
        removeButton.setName("removeButton");
        restoreButton.setName("restoreButton");
        textLabel.setName("textLabel");

        textLabel.setBorder(null);
        textLabel.setPreferredSize(new Dimension(1,50));
    }
}
