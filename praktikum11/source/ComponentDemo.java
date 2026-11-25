import javax.swing.*;
import java.awt.*;

public class ComponentDemo extends JFrame {

    public ComponentDemo() {
        setTitle("Swing Component Demo");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JTextField nameField = new JTextField(15);

        JComboBox<String> deptCombo = new JComboBox<>(
            new String[] {"Informatika", "Elektronika", "Mesin"}
        );

        JCheckBox activeCheck = new JCheckBox("Mahasiswa aktif", true);

        JRadioButton regulerRadio = new JRadioButton("Reguler", true);
        JRadioButton ekstensiRadio = new JRadioButton("Ekstensi");
        ButtonGroup group = new ButtonGroup();
        group.add(regulerRadio);
        group.add(ekstensiRadio);

        JPanel classPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 0, 0));
        classPanel.add(regulerRadio);
        classPanel.add(ekstensiRadio);

        JPanel formPanel = new JPanel(new GridLayout(0, 2, 8, 6));
        formPanel.add(new JLabel("Nama:"));
        formPanel.add(nameField);
        formPanel.add(new JLabel("Jurusan:"));
        formPanel.add(deptCombo);
        formPanel.add(new JLabel("Status:"));
        formPanel.add(activeCheck);
        formPanel.add(new JLabel("Kelas:"));
        formPanel.add(classPanel);

        JTextArea outputArea = new JTextArea(6, 25);
        outputArea.setEditable(false);

        JButton showButton = new JButton("Tampilkan");
        showButton.addActionListener(e -> {
            if (nameField.getText().isBlank()) {
                JOptionPane.showMessageDialog(
                    this,
                    "Nama wajib diisi.",
                    "Input tidak valid",
                    JOptionPane.WARNING_MESSAGE
                );
                return;
            }

            String nama = nameField.getText();
            String jurusan = (String) deptCombo.getSelectedItem();
            boolean aktif = activeCheck.isSelected();
            String kelas = regulerRadio.isSelected()
                ? "Reguler"
                : "Ekstensi";

            outputArea.setText(
                "Nama: " + nama +
                "\nJurusan: " + jurusan +
                "\nAktif: " + aktif +
                "\nKelas: " + kelas
            );
        });

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        buttonPanel.add(showButton);

        JPanel contentPanel = new JPanel(new BorderLayout(0, 8));
        contentPanel.setBorder(BorderFactory.createEmptyBorder(8, 8, 8, 8));
        contentPanel.add(formPanel, BorderLayout.NORTH);
        contentPanel.add(new JScrollPane(outputArea), BorderLayout.CENTER);
        contentPanel.add(buttonPanel, BorderLayout.SOUTH);

        setLayout(new BorderLayout());
        add(contentPanel, BorderLayout.CENTER);

        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(ComponentDemo::new);
    }
}
