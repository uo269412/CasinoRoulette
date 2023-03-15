package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.net.URL;

import javax.help.HelpBroker;
import javax.help.HelpSet;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import logic.UserManagement;
import logic.Player;

public class WelcomeWindow extends JDialog {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel pnlHeader;
	private JLabel lblWelcomeUser;
	private JButton btnLogOff;
	private JPanel pnlLabels;
	private JLabel imgUser;
	private JPanel pnlInfo;
	private JLabel lblBalance;
	private JLabel lblChips;
	private JTextField txtBalance;
	private JTextField txtChips;
	private JLabel imgIcon2;
	private JLabel imgIcon1;
	private JPanel pnlButtons;
	private JButton btnShop;
	private JButton btnPlay;
	private JPanel pnlButtonsHeader;
	private JButton btnHelp;

	// Windows

	private JDialog parent = null;
	private ShopWindow shopWindow = null;
	private GameWindow gameWindow = null;

	// Logic
	private UserManagement userManagement = null;

	/**
	 * Create the dialog.
	 */
	public WelcomeWindow(JDialog dialog, UserManagement userManagement) {
		setResizable(false);
		parent = dialog;
		this.userManagement = userManagement;

		if (parent instanceof RegisterWindow) {
			JOptionPane.showMessageDialog(null, "Congratulations! You've earned 100€ for registering!");
		}

		getContentPane().setBackground(Color.WHITE);
		setIconImage(Toolkit.getDefaultToolkit().getImage(WelcomeWindow.class.getResource("/img/DiceIcon.png")));
		setTitle("Casino Roulette: Welcome");
		setBounds(100, 100, 595, 319);
		getContentPane().setLayout(new BorderLayout());
		getContentPane().add(getPnlHeader(), BorderLayout.NORTH);
		getContentPane().add(getPnlLabels(), BorderLayout.CENTER);
		getContentPane().add(getPnlButtons(), BorderLayout.SOUTH);

		loadHelp();
		updateBalanceAndChips();

		this.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				logOffOperation();
			}
		});
	}
	
	private void loadHelp(){

		   URL hsURL;
		   HelpSet hs;

		    try {
			    	File fichero = new File("help/Ayuda.hs");
			    	hsURL = fichero.toURI().toURL();
			        hs = new HelpSet(null, hsURL);
			      }

		    catch (Exception e){
		      System.out.println("Help support not found");
		      return;
		   }

		   HelpBroker hb = hs.createHelpBroker();

		   hb.enableHelpKey(getRootPane(),"welcome", hs);
		   hb.enableHelpOnButton(btnHelp, "welcome", hs);
		 }

	private JPanel getPnlHeader() {
		if (pnlHeader == null) {
			pnlHeader = new JPanel();
			pnlHeader.setBackground(new Color(178, 34, 34));
			pnlHeader.setLayout(new BorderLayout(0, 0));
			pnlHeader.add(getLblWelcomeUser(), BorderLayout.WEST);
			pnlHeader.add(getPnlButtonsHeader(), BorderLayout.EAST);
		}
		return pnlHeader;
	}

	private JLabel getLblWelcomeUser() {
		if (lblWelcomeUser == null) {
			lblWelcomeUser = new JLabel("   Welcome, " + userManagement.getPlayer().getName());
			lblWelcomeUser.setForeground(Color.WHITE);
			lblWelcomeUser.setFont(new Font("Segoe UI Semibold", Font.BOLD, 16));
		}
		return lblWelcomeUser;
	}

	private JButton getBtnLogOff() {
		if (btnLogOff == null) {
			btnLogOff = new JButton("Log off");
			btnLogOff.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					logOffOperation();
					dispose();
				}
			});
			btnLogOff.setMnemonic('L');
			btnLogOff.setForeground(Color.WHITE);
			btnLogOff.setFont(new Font("Segoe UI Semibold", Font.ITALIC, 13));
			btnLogOff.setBackground(new Color(178, 34, 34));
		}
		return btnLogOff;
	}

	private JPanel getPnlLabels() {
		if (pnlLabels == null) {
			pnlLabels = new JPanel();
			pnlLabels.setBackground(Color.WHITE);
			pnlLabels.setLayout(new BorderLayout(0, 0));
			pnlLabels.add(getImgUser(), BorderLayout.EAST);
			pnlLabels.add(getPnlInfo(), BorderLayout.CENTER);
		}
		return pnlLabels;
	}

	private JLabel getImgUser() {
		if (imgUser == null) {
			imgUser = new JLabel("");
			imgUser.setIcon(new ImageIcon(WelcomeWindow.class.getResource("/img/UserProfileIcon.png")));
			imgUser.setBackground(Color.BLACK);
		}
		return imgUser;
	}

	private JPanel getPnlInfo() {
		if (pnlInfo == null) {
			pnlInfo = new JPanel();
			pnlInfo.setLayout(null);
			pnlInfo.setBackground(Color.WHITE);
			pnlInfo.add(getLblBalance());
			pnlInfo.add(getLblChips());
			pnlInfo.add(getTxtBalance());
			pnlInfo.add(getTxtChips());
			pnlInfo.add(getImgIcon2());
			pnlInfo.add(getImgIcon1());
		}
		return pnlInfo;
	}

	private JLabel getLblBalance() {
		if (lblBalance == null) {
			lblBalance = new JLabel("Your current balance is:");
			lblBalance.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 18));
			lblBalance.setBounds(40, 65, 225, 26);
		}
		return lblBalance;
	}

	private JLabel getLblChips() {
		if (lblChips == null) {
			lblChips = new JLabel("Your current number of chips is:");
			lblChips.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 18));
			lblChips.setBounds(38, 123, 267, 26);
		}
		return lblChips;
	}

	private JTextField getTxtBalance() {
		if (txtBalance == null) {
			txtBalance = new JTextField();
			txtBalance.setBackground(Color.WHITE);
			txtBalance.setEditable(false);
			txtBalance.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 14));
			txtBalance.setColumns(10);
			txtBalance.setBounds(249, 72, 113, 20);
		}
		return txtBalance;
	}

	private JTextField getTxtChips() {
		if (txtChips == null) {
			txtChips = new JTextField();
			txtChips.setBackground(Color.WHITE);
			txtChips.setEditable(false);
			txtChips.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 14));
			txtChips.setColumns(10);
			txtChips.setBounds(311, 130, 68, 20);
		}
		return txtChips;
	}

	private JLabel getImgIcon2() {
		if (imgIcon2 == null) {
			imgIcon2 = new JLabel("");
			imgIcon2.setIcon(new ImageIcon(WelcomeWindow.class.getResource("/img/ChipIcon.png")));
			imgIcon2.setBounds(10, 123, 20, 26);
		}
		return imgIcon2;
	}

	private JLabel getImgIcon1() {
		if (imgIcon1 == null) {
			imgIcon1 = new JLabel("");
			imgIcon1.setIcon(new ImageIcon(WelcomeWindow.class.getResource("/img/ChipIcon.png")));
			imgIcon1.setBounds(10, 66, 20, 26);
		}
		return imgIcon1;
	}

	private JPanel getPnlButtons() {
		if (pnlButtons == null) {
			pnlButtons = new JPanel();
			FlowLayout flowLayout = (FlowLayout) pnlButtons.getLayout();
			flowLayout.setHgap(10);
			flowLayout.setAlignment(FlowLayout.TRAILING);
			pnlButtons.setBackground(Color.WHITE);
			pnlButtons.add(getBtnShop());
			pnlButtons.add(getBtnPlay());
		}
		return pnlButtons;
	}

	private JButton getBtnShop() {
		if (btnShop == null) {
			btnShop = new JButton("Shop");
			btnShop.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					openShopWindow();
				}
			});
			btnShop.setMnemonic('S');
			btnShop.setForeground(Color.WHITE);
			btnShop.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 14));
			btnShop.setBackground(new Color(100, 149, 237));
			btnShop.setActionCommand("OK");
		}
		return btnShop;
	}

	private JButton getBtnPlay() {
		if (btnPlay == null) {
			btnPlay = new JButton("Play");
			checkStatus();
			btnPlay.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					openGameWindow();
				}
			});
			btnPlay.setMnemonic('P');
			btnPlay.setForeground(Color.WHITE);
			btnPlay.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 14));
			btnPlay.setBackground(new Color(0, 128, 0));
			btnPlay.setActionCommand("Cancel");
		}
		return btnPlay;
	}

	private JPanel getPnlButtonsHeader() {
		if (pnlButtonsHeader == null) {
			pnlButtonsHeader = new JPanel();
			pnlButtonsHeader.setBackground(new Color(178, 34, 34));
			pnlButtonsHeader.add(getBtnLogOff());
			pnlButtonsHeader.add(getBtnHelp());
		}
		return pnlButtonsHeader;
	}

	private JButton getBtnHelp() {
		if (btnHelp == null) {
			btnHelp = new JButton("");
			btnHelp.setIcon(new ImageIcon(WelcomeWindow.class.getResource("/img/btnhelp.png")));
			btnHelp.setBorder(null);
		}
		return btnHelp;
	}

	// Methods

	protected UserManagement getUserManagement() {
		return userManagement;
	}

	protected Player getCurrentPlayer() {
		return userManagement.getPlayer();
	}

	protected void updateBalanceAndChips() {
		txtBalance.setText(Double.toString(userManagement.getPlayer().getBalance()));
		txtChips.setText(Integer.toString(userManagement.getPlayer().getChipsTotalValue()));
		if ((userManagement.getPlayer().getChipsTotalValue() == 0)) {
			btnPlay.setEnabled(false);
		} else {
			btnPlay.setEnabled(true);
		}
	}

	protected void checkStatus() {
		if (userManagement.getPlayer().getChipsTotalValue() == 0) {
			btnPlay.setEnabled(false);
		} else {
			btnPlay.setEnabled(true);
		}
	}

	private void logOffOperation() {
		userManagement.logOff(userManagement.getPlayer());
		JOptionPane.showMessageDialog(null, "Thanks for playing " + userManagement.getPlayer().getUsername() + "!");
	}

	protected void openGameWindow() {
		gameWindow = new GameWindow(this);
		gameWindow.setModal(true);
		gameWindow.setLocationRelativeTo(this);
		gameWindow.setVisible(true);

	}

	private void openShopWindow() {
		shopWindow = new ShopWindow(this);
		shopWindow.setModal(true);
		shopWindow.setLocationRelativeTo(this);
		shopWindow.setVisible(true);
	}
}
