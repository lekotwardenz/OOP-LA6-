package FoodOrderingSystem;

import javax.swing.*;
import java.awt.*;

import java.awt.event.ItemEvent;

public class FoodOrderingSystem extends JFrame {
    private JCheckBox cPizza;
    private JPanel contentPanel;
    private JPanel contentLeft;
    private JLabel foodLabel;
    private JLabel pizza_Cost;
    private JCheckBox cBurger;
    private JLabel burger_cost;
    private JCheckBox cFries;
    private JLabel fries_Cost;
    private JCheckBox cSoftDrinks;
    private JLabel sotf_Drinks_Cost;
    private JCheckBox cTea;
    private JLabel tea_Cost;
    private JCheckBox cSundae;
    private JLabel sundae_Cost;
    private JPanel contentRight;
    private JLabel discountLabel;
    private JButton btnOrder;
    private JRadioButton rbNone;
    private JRadioButton rb5;
    private JRadioButton rb10;
    private JRadioButton rb15;

    private double total = 0;
    private double totalDiscount = 0;

    private void implementation() {
        cPizza.addItemListener(e -> updateTotal(e, 100));
        cBurger.addItemListener(e -> updateTotal(e, 80));
        cFries.addItemListener(e -> updateTotal(e, 65));
        cSoftDrinks.addItemListener(e -> updateTotal(e, 55));
        cTea.addItemListener(e -> updateTotal(e, 50));
        cSundae.addItemListener(e -> updateTotal(e, 40));

        ButtonGroup discount = new ButtonGroup();
        discount.add(rbNone);
        discount.add(rb5);
        discount.add(rb10);
        discount.add(rb15);

        rbNone.addItemListener(e -> updateDiscount(e, 0));
        rb5.addItemListener(e -> updateDiscount(e, 0.05));
        rb10.addItemListener(e -> updateDiscount(e, 0.1));
        rb15.addItemListener(e -> updateDiscount(e, 0.15));

        btnOrder.addActionListener(e -> {
            double finalCost = total - (total * totalDiscount);

            JOptionPane.showMessageDialog(
                    this,
                    "The total price is Php " + String.format("%.2f", finalCost),
                    "Total Cost",
                    JOptionPane.INFORMATION_MESSAGE
            );

        });
    }

    private void updateTotal(ItemEvent e, double price) {
        if (e.getStateChange() == ItemEvent.SELECTED) {
            total = total + price;
        } else {
            total = total - price;
        }

        System.out.println("Total: " + total);
    }

    private void updateDiscount(ItemEvent e, double discount) {
        if (e.getStateChange() == ItemEvent.SELECTED) {
            totalDiscount = discount;
        }

        System.out.println("Discount: " + totalDiscount);
    }

    private void setVariableNames() {
        cPizza.setName("cPizza");
        cTea.setName("cTea");
        cSundae.setName("cSundae");
        cFries.setName("cFries");
        cBurger.setName("cBurger");
        cSoftDrinks.setName("cSoftDrinks");

        rbNone.setName("rbNone");
        rb5.setName("rb5");
        rb10.setName("rb10");
        rb15.setName("rb15");
    }

    public FoodOrderingSystem() {
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setTitle("Food Ordering System");
        setPreferredSize(new Dimension(500,400));
        setResizable(false);
        add(contentPanel);
        implementation();
        setVariableNames();

        pack();

        setVisible(true);
    }
}
