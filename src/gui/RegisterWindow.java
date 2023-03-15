package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.net.URL;

import javax.help.HelpBroker;
import javax.help.HelpSet;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import logic.exceptions.AlreadyRegisteredException;
import logic.exceptions.EmptyFieldsException;
import logic.exceptions.PasswordsDontMatchException;

public class RegisterWindow extends JDialog {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel pnlButtons;
	private JButton btnCreateAccount;
	private JButton btnReturn;
	private JPanel pnlLabels;
	private JLabel lblUsername;
	private JLabel lblPassword;
	private JLabel lblRepeatPassword;
	private JTextField txtUsername;
	private JPasswordField pwdPassword;
	private JPasswordField pwdRepeatPassword;
	private JLabel lblDNI;
	private JLabel lblBankAccount;
	private JTextField txtDNI;
	private JTextField txtBankAccount;
	private JCheckBox cbox18years;
	private JCheckBox cboxTermsConditions;

	// Windows

	private MainWindow parent = null;
	private WelcomeWindow child = null;
	private JLabel lblName;
	private JTextField txtName;

	/**
	 * Create the dialog.
	 */
	public RegisterWindow(MainWindow mainWindow) {
		setResizable(false);
		parent = mainWindow;

		getContentPane().setBackground(Color.WHITE);
		setIconImage(Toolkit.getDefaultToolkit().getImage(RegisterWindow.class.getResource("/img/DiceIcon.png")));
		setTitle("Casino Roulette: Register");
		setBounds(100, 100, 510, 392);
		getContentPane().setLayout(new BorderLayout());
		getContentPane().add(getPnlButtons(), BorderLayout.SOUTH);
		getContentPane().add(getPnlLabels(), BorderLayout.CENTER);
		loadHelp();
	}

	private void loadHelp() {

		URL hsURL;
		HelpSet hs;

		try {
			File fichero = new File("help/Ayuda.hs");
			hsURL = fichero.toURI().toURL();
			hs = new HelpSet(null, hsURL);
		}

		catch (Exception e) {
			System.out.println("Help support not found");
			return;
		}

		HelpBroker hb = hs.createHelpBroker();

		hb.enableHelpKey(getRootPane(), "register", hs);
	}

	private JPanel getPnlButtons() {
		if (pnlButtons == null) {
			pnlButtons = new JPanel();
			pnlButtons.setBackground(Color.WHITE);
			pnlButtons.setLayout(new FlowLayout(FlowLayout.RIGHT));
			pnlButtons.add(getBtnCreateAccount());
			pnlButtons.add(getBtnReturn());
		}
		return pnlButtons;
	}

	private JButton getBtnCreateAccount() {
		if (btnCreateAccount == null) {
			btnCreateAccount = new JButton("Create account");
			btnCreateAccount.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					createAccountOperation();
				}
			});
			btnCreateAccount.setMnemonic('C');
			btnCreateAccount.setForeground(Color.WHITE);
			btnCreateAccount.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 14));
			btnCreateAccount.setBackground(new Color(0, 128, 0));
			btnCreateAccount.setActionCommand("OK");
		}
		return btnCreateAccount;
	}

	private JButton getBtnReturn() {
		if (btnReturn == null) {
			btnReturn = new JButton("Return");
			btnReturn.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					dispose();
				}
			});
			btnReturn.setMnemonic('T');
			btnReturn.setForeground(Color.WHITE);
			btnReturn.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 14));
			btnReturn.setBackground(Color.RED);
			btnReturn.setActionCommand("Cancel");
		}
		return btnReturn;
	}

	private JPanel getPnlLabels() {
		if (pnlLabels == null) {
			pnlLabels = new JPanel();
			pnlLabels.setLayout(null);
			pnlLabels.setBackground(Color.WHITE);
			pnlLabels.add(getLblUsername());
			pnlLabels.add(getLblPassword());
			pnlLabels.add(getLblRepeatPassword());
			pnlLabels.add(getTxtUsername());
			pnlLabels.add(getPwdPassword());
			pnlLabels.add(getPwdRepeatPassword());
			pnlLabels.add(getLblDNI());
			pnlLabels.add(getLblBankAccount());
			pnlLabels.add(getTxtDNI());
			pnlLabels.add(getTxtBankAccount());
			pnlLabels.add(getCbox18years());
			pnlLabels.add(getCboxTermsConditions());
			pnlLabels.add(getLblName());
			pnlLabels.add(getTxtName());
		}
		return pnlLabels;
	}

	private JLabel getLblUsername() {
		if (lblUsername == null) {
			lblUsername = new JLabel("Username:");
			lblUsername.setLabelFor(getTxtUsername());
			lblUsername.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 18));
			lblUsername.setDisplayedMnemonic('U');
			lblUsername.setBounds(25, 11, 138, 26);
		}
		return lblUsername;
	}

	private JLabel getLblPassword() {
		if (lblPassword == null) {
			lblPassword = new JLabel("Password:");
			lblPassword.setLabelFor(getPwdPassword());
			lblPassword.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 18));
			lblPassword.setDisplayedMnemonic('P');
			lblPassword.setBounds(25, 85, 153, 25);
		}
		return lblPassword;
	}

	private JLabel getLblRepeatPassword() {
		if (lblRepeatPassword == null) {
			lblRepeatPassword = new JLabel("Repeat Password:");
			lblRepeatPassword.setLabelFor(getPwdRepeatPassword());
			lblRepeatPassword.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 18));
			lblRepeatPassword.setDisplayedMnemonic('R');
			lblRepeatPassword.setBounds(25, 121, 188, 25);
		}
		return lblRepeatPassword;
	}

	private JTextField getTxtUsername() {
		if (txtUsername == null) {
			txtUsername = new JTextField();
			txtUsername.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 14));
			txtUsername.setColumns(10);
			txtUsername.setBounds(255, 16, 205, 20);
		}
		return txtUsername;
	}

	private JPasswordField getPwdPassword() {
		if (pwdPassword == null) {
			pwdPassword = new JPasswordField();
			pwdPassword.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 14));
			pwdPassword.setBounds(255, 89, 205, 20);
		}
		return pwdPassword;
	}

	private JPasswordField getPwdRepeatPassword() {
		if (pwdRepeatPassword == null) {
			pwdRepeatPassword = new JPasswordField();
			pwdRepeatPassword.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 14));
			pwdRepeatPassword.setBounds(255, 125, 205, 20);
		}
		return pwdRepeatPassword;
	}

	private JLabel getLblDNI() {
		if (lblDNI == null) {
			lblDNI = new JLabel("DNI:");
			lblDNI.setLabelFor(getTxtDNI());
			lblDNI.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 18));
			lblDNI.setDisplayedMnemonic('D');
			lblDNI.setBounds(25, 157, 188, 25);
		}
		return lblDNI;
	}

	private JLabel getLblBankAccount() {
		if (lblBankAccount == null) {
			lblBankAccount = new JLabel("Bank Account:");
			lblBankAccount.setLabelFor(getTxtBankAccount());
			lblBankAccount.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 18));
			lblBankAccount.setDisplayedMnemonic('B');
			lblBankAccount.setBounds(25, 193, 188, 25);
		}
		return lblBankAccount;
	}

	private JTextField getTxtDNI() {
		if (txtDNI == null) {
			txtDNI = new JTextField();
			txtDNI.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 14));
			txtDNI.setColumns(10);
			txtDNI.setBounds(255, 161, 205, 20);
		}
		return txtDNI;
	}

	private JTextField getTxtBankAccount() {
		if (txtBankAccount == null) {
			txtBankAccount = new JTextField();
			txtBankAccount.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 14));
			txtBankAccount.setColumns(10);
			txtBankAccount.setBounds(255, 197, 205, 20);
		}
		return txtBankAccount;
	}

	private JCheckBox getCbox18years() {
		if (cbox18years == null) {
			cbox18years = new JCheckBox("If you accept this, you agree you are are older than 18 years old");
			cbox18years.setMnemonic('I');
			cbox18years.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 13));
			cbox18years.setBackground(Color.WHITE);
			cbox18years.setBounds(21, 250, 413, 23);
		}
		return cbox18years;
	}

	private JCheckBox getCboxTermsConditions() {
		if (cboxTermsConditions == null) {
			cboxTermsConditions = new JCheckBox("Accept terms and conditions");
			cboxTermsConditions.setMnemonic('A');
			cboxTermsConditions.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 13));
			cboxTermsConditions.setBackground(Color.WHITE);
			cboxTermsConditions.setBounds(21, 276, 413, 23);
		}
		return cboxTermsConditions;
	}

	private JLabel getLblName() {
		if (lblName == null) {
			lblName = new JLabel("Name and surname:");
			lblName.setLabelFor(getTxtName());
			lblName.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 18));
			lblName.setDisplayedMnemonic('N');
			lblName.setBounds(25, 48, 174, 26);
		}
		return lblName;
	}

	private JTextField getTxtName() {
		if (txtName == null) {
			txtName = new JTextField();
			txtName.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 14));
			txtName.setColumns(10);
			txtName.setBounds(255, 53, 205, 20);
		}
		return txtName;
	}

	// Methods

	public MainWindow getParent() {
		return parent;
	}

	private void openWelcomeWindow() {
		dispose();
		child = new WelcomeWindow(this, getParent().getUserManagement());
		child.setModal(true);
		child.setLocationRelativeTo(this);
		child.setVisible(true);
	}

	@SuppressWarnings("deprecation")
	private void createAccountOperation() {
		try {
			if (txtUsername.getText().isEmpty() || txtName.getText().isEmpty() || pwdPassword.getText().isEmpty()
					|| pwdRepeatPassword.getText().isEmpty() || txtDNI.getText().isEmpty()
					|| txtBankAccount.getText().isEmpty() || !cbox18years.isSelected()
					|| !cboxTermsConditions.isSelected()) {
				throw new EmptyFieldsException();
			}
			getParent().getUserManagement().register(txtDNI.getText(), txtName.getText(), txtUsername.getText(),
					pwdPassword.getText().toString(), pwdRepeatPassword.getText().toString());
			openWelcomeWindow();
		} catch (EmptyFieldsException ex) {
			JOptionPane.showMessageDialog(null, "Please, check every field is filled and you've accepted the terms");
		} catch (PasswordsDontMatchException ex) {
			JOptionPane.showMessageDialog(null, "Passwords don't match");
		} catch (AlreadyRegisteredException ex) {
			JOptionPane.showMessageDialog(null, "Such user is already used. If yours, please, sign in");
		}
	}
}
