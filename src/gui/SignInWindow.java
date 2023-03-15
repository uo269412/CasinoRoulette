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
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import logic.exceptions.EmptyFieldsException;
import logic.exceptions.PasswordsDontMatchException;
import logic.exceptions.UserNotFoundException;

public class SignInWindow extends JDialog {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel pnlButtons;
	private JButton btnAccess;
	private JButton btnCancel;
	private JPanel pnlLabels;
	private JLabel lblUsername;
	private JLabel lblPassword;
	private JLabel lblRepeatPassword;
	private JTextField txtUsername;
	private JPasswordField pwdPassword;
	private JPasswordField pwdRepeatPassword;

	// Windows

	private MainWindow parent = null;
	private WelcomeWindow child = null;

	/**
	 * Create the dialog.
	 */
	public SignInWindow(MainWindow mainWindow) {
		setResizable(false);
		parent = mainWindow;

		getContentPane().setBackground(Color.WHITE);
		setIconImage(Toolkit.getDefaultToolkit().getImage(SignInWindow.class.getResource("/img/DiceIcon.png")));
		setTitle("Casino Roulette: Sign In");
		setBounds(100, 100, 468, 202);
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

		hb.enableHelpKey(getRootPane(), "signIn", hs);
	}

	private JPanel getPnlButtons() {
		if (pnlButtons == null) {
			pnlButtons = new JPanel();
			pnlButtons.setBackground(Color.WHITE);
			pnlButtons.setLayout(new FlowLayout(FlowLayout.RIGHT));
			pnlButtons.add(getBtnAccess());
			pnlButtons.add(getBtnCancel());
		}
		return pnlButtons;
	}

	private JButton getBtnAccess() {
		if (btnAccess == null) {
			btnAccess = new JButton("Access");
			btnAccess.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					signInOperation();
				}
			});
			btnAccess.setMnemonic('A');
			btnAccess.setForeground(Color.WHITE);
			btnAccess.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 14));
			btnAccess.setBackground(new Color(0, 128, 0));
		}
		return btnAccess;
	}

	private JButton getBtnCancel() {
		if (btnCancel == null) {
			btnCancel = new JButton("Cancel");
			btnCancel.setMnemonic('C');
			btnCancel.setForeground(Color.WHITE);
			btnCancel.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 14));
			btnCancel.setBackground(Color.RED);
			btnCancel.setActionCommand("Cancel");
			btnCancel.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					dispose();
				}
			});
		}
		return btnCancel;
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
			lblPassword.setBounds(25, 48, 153, 25);
		}
		return lblPassword;
	}

	private JLabel getLblRepeatPassword() {
		if (lblRepeatPassword == null) {
			lblRepeatPassword = new JLabel("Repeat Password:");
			lblRepeatPassword.setLabelFor(getPwdRepeatPassword());
			lblRepeatPassword.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 18));
			lblRepeatPassword.setDisplayedMnemonic('R');
			lblRepeatPassword.setBounds(25, 84, 188, 25);
		}
		return lblRepeatPassword;
	}

	private JTextField getTxtUsername() {
		if (txtUsername == null) {
			txtUsername = new JTextField();
			txtUsername.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 14));
			txtUsername.setColumns(10);
			txtUsername.setBounds(209, 18, 205, 20);
		}
		return txtUsername;
	}

	private JPasswordField getPwdPassword() {
		if (pwdPassword == null) {
			pwdPassword = new JPasswordField();
			pwdPassword.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 14));
			pwdPassword.setBounds(209, 54, 205, 20);
		}
		return pwdPassword;
	}

	private JPasswordField getPwdRepeatPassword() {
		if (pwdRepeatPassword == null) {
			pwdRepeatPassword = new JPasswordField();
			pwdRepeatPassword.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 14));
			pwdRepeatPassword.setBounds(209, 90, 205, 20);
		}
		return pwdRepeatPassword;
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
	private void signInOperation() {
		try {
			if (txtUsername.getText().isEmpty() || pwdPassword.getText().isEmpty()
					|| pwdRepeatPassword.getText().isEmpty()) {
				throw new EmptyFieldsException();
			}
			getParent().getUserManagement().signIn(txtUsername.getText(), pwdPassword.getText(),
					pwdRepeatPassword.getText());
			openWelcomeWindow();
		} catch (EmptyFieldsException ex) {
			JOptionPane.showMessageDialog(null, "Please, check every field is filled");
		} catch (UserNotFoundException ex) {
			JOptionPane.showMessageDialog(null,
					"Such user doesn't exist. If you want to create an account, please register first");
		} catch (PasswordsDontMatchException ex) {
			JOptionPane.showMessageDialog(null, "Passwords don't match");
		}
	}
}
