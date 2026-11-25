import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.print.PrinterException;
import java.util.ArrayList;
import java.util.List;

public class POSFrame extends JFrame {

    private final List<Product> products = new ArrayList<>();
    private final List<CartItem> cart = new ArrayList<>();

    private JTable productTable;
    private JTable cartTable;
    private JTextField qtyField;
    private JLabel selectedLabel;
    private JLabel totalLabel;
    private JLabel pointsLabel;
    private JTextArea receiptArea;

    private JButton addButton;
    private JButton checkoutButton;
    private JButton printButton;

    private DefaultTableModel productModel;
    private DefaultTableModel cartModel;

    public POSFrame() {
        setTitle("POIN Off-Sales - Java Swing");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        initProductData();
        initComponents();
        bindEvents();

        setSize(1000, 650);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    private void initProductData() {
        products.add(new Product("P001", "Air Mineral 600ml", 3000));
        products.add(new Product("P002", "Kopi Sachet", 5000));
        products.add(new Product("P003", "Roti isi", 8000));
        products.add(new Product("P004", "Snack Keripik", 6000));
        products.add(new Product("P005", "Minuman Botol", 12000));
    }

    private void initComponents() {
        setJMenuBar(createMenuBar());

        productModel = new DefaultTableModel(
            new Object[] {"ID", "Nama Produk", "Harga (Rp)"}, 0
        ) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        for (Product p : products) {
            productModel.addRow(new Object[] {
                p.getId(), p.getName(), p.getPrice()
            });
        }

        productTable = new JTable(productModel);

        cartModel = new DefaultTableModel(
            new Object[] {"Produk", "Qty", "Subtotal (Rp)"}, 0
        ) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        cartTable = new JTable(cartModel);

        selectedLabel = new JLabel("Dipilih: -");
        qtyField = new JTextField(5);
        addButton = new JButton("Add to Cart");

        JPanel selectionPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 8, 4));
        selectionPanel.add(selectedLabel);

        JPanel qtyPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 8, 4));
        qtyPanel.add(new JLabel("Qty:"));
        qtyPanel.add(qtyField);
        qtyPanel.add(addButton);

        JPanel controlPanel = new JPanel(new BorderLayout());
        controlPanel.add(selectionPanel, BorderLayout.NORTH);
        controlPanel.add(qtyPanel, BorderLayout.SOUTH);

        JPanel productPanel = new JPanel(new BorderLayout(0, 4));
        productPanel.setBorder(BorderFactory.createTitledBorder("Produk"));
        productPanel.add(new JScrollPane(productTable), BorderLayout.CENTER);
        productPanel.add(controlPanel, BorderLayout.SOUTH);

        totalLabel = new JLabel("Total: Rp0");
        pointsLabel = new JLabel("Points: 0");
        checkoutButton = new JButton("Checkout");
        printButton = new JButton("Cetak");

        JPanel summaryPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 12, 4));
        summaryPanel.add(totalLabel);
        summaryPanel.add(pointsLabel);

        JPanel cartButtonPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 8, 4));
        cartButtonPanel.add(checkoutButton);
        cartButtonPanel.add(printButton);

        receiptArea = new JTextArea(8, 30);
        receiptArea.setEditable(false);
        receiptArea.setFont(new Font(Font.MONOSPACED, Font.PLAIN, 12));

        JPanel cartBottomPanel = new JPanel(new BorderLayout(0, 4));
        cartBottomPanel.add(summaryPanel, BorderLayout.NORTH);
        cartBottomPanel.add(new JScrollPane(receiptArea), BorderLayout.CENTER);
        cartBottomPanel.add(cartButtonPanel, BorderLayout.SOUTH);

        JPanel cartPanel = new JPanel(new BorderLayout(0, 4));
        cartPanel.setBorder(BorderFactory.createTitledBorder("Keranjang"));
        cartPanel.add(new JScrollPane(cartTable), BorderLayout.CENTER);
        cartPanel.add(cartBottomPanel, BorderLayout.SOUTH);

        JPanel mainPanel = new JPanel(new GridLayout(1, 2, 8, 0));
        mainPanel.add(productPanel);
        mainPanel.add(cartPanel);

        add(mainPanel, BorderLayout.CENTER);
    }

    private JMenuBar createMenuBar() {
        JMenuBar menuBar = new JMenuBar();

        JMenu fileMenu = new JMenu("File");
        JMenuItem exitItem = new JMenuItem("Exit");

        JMenu helpMenu = new JMenu("Help");
        JMenuItem aboutItem = new JMenuItem("About");

        exitItem.addActionListener(e -> dispose());
        aboutItem.addActionListener(e ->
            JOptionPane.showMessageDialog(
                this,
                "POIN Off-Sales - Praktikum Java Swing",
                "About",
                JOptionPane.INFORMATION_MESSAGE
            )
        );

        fileMenu.add(exitItem);
        helpMenu.add(aboutItem);
        menuBar.add(fileMenu);
        menuBar.add(helpMenu);

        return menuBar;
    }

    private void bindEvents() {
        productTable.getSelectionModel().addListSelectionListener(e -> {
            int row = productTable.getSelectedRow();

            if (row >= 0) {
                Product selected = products.get(row);
                selectedLabel.setText(
                    "Dipilih: " + selected.getId() +
                    " - " + selected.getName() +
                    " (Rp" + selected.getPrice() + ")"
                );
            }
        });

        addButton.addActionListener(e -> addSelectedProductToCart());
        checkoutButton.addActionListener(e -> checkout());
        printButton.addActionListener(e -> printReceipt());
    }

    private void addSelectedProductToCart() {
        int row = productTable.getSelectedRow();

        if (row < 0) {
            JOptionPane.showMessageDialog(
                this,
                "Pilih produk terlebih dahulu.",
                "Input tidak valid",
                JOptionPane.WARNING_MESSAGE
            );
            return;
        }

        String qtyText = qtyField.getText().trim();
        int qty;

        try {
            qty = Integer.parseInt(qtyText);
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(
                this,
                "Qty harus berupa angka.",
                "Input tidak valid",
                JOptionPane.WARNING_MESSAGE
            );
            return;
        }

        if (qty <= 0) {
            JOptionPane.showMessageDialog(
                this,
                "Qty harus lebih dari 0.",
                "Input tidak valid",
                JOptionPane.WARNING_MESSAGE
            );
            return;
        }

        Product product = products.get(row);
        cart.add(new CartItem(product, qty));
        refreshCartTable();
        updateSummary();
        qtyField.setText("");
    }

    private void refreshCartTable() {
        cartModel.setRowCount(0);

        for (CartItem item : cart) {
            cartModel.addRow(new Object[] {
                item.getProduct().getName(),
                item.getQty(),
                item.getSubtotal()
            });
        }
    }

    private void updateSummary() {
        int total = 0;

        for (CartItem item : cart) {
            total += item.getSubtotal();
        }

        totalLabel.setText("Total: Rp" + total);
        pointsLabel.setText("Points: " + (total / 1000));
    }

    private void checkout() {
        if (cart.isEmpty()) {
            JOptionPane.showMessageDialog(
                this,
                "Keranjang masih kosong.",
                "Checkout",
                JOptionPane.WARNING_MESSAGE
            );
            return;
        }

        int total = 0;
        StringBuilder receipt = new StringBuilder();

        receipt.append("===== POIN OFF-SALES =====\n");
        receipt.append("Toko: Demo Toko\n");
        receipt.append("==========================\n");

        for (CartItem item : cart) {
            int subtotal = item.getSubtotal();
            total += subtotal;
            receipt.append(item.getProduct().getName())
                .append(" x").append(item.getQty())
                .append(" = Rp").append(subtotal)
                .append("\n");
        }

        receipt.append("--------------------------\n");
        receipt.append("Total   : Rp").append(total).append("\n");
        receipt.append("Points  : ").append(total / 1000).append("\n");
        receipt.append("Terima kasih telah berbelanja.\n");

        receiptArea.setText(receipt.toString());

        cart.clear();
        refreshCartTable();
        updateSummary();
    }

    private void printReceipt() {
        if (receiptArea.getText().isBlank()) {
            JOptionPane.showMessageDialog(
                this,
                "Belum ada struk yang dapat dicetak."
            );
            return;
        }

        try {
            receiptArea.print();
        } catch (PrinterException ex) {
            JOptionPane.showMessageDialog(
                this,
                "Gagal mencetak: " + ex.getMessage()
            );
        }
    }
}
