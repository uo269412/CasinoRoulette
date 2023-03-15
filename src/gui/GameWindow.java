package gui;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.SystemColor;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.net.URL;

import javax.help.HelpBroker;
import javax.help.HelpSet;
import javax.swing.ButtonGroup;
import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;

import logic.Bet;
import logic.Chip;
import logic.Game;
import logic.Player;
import logic.UserManagement;
import logic.board.NumberCell;

public class GameWindow extends JDialog {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel pnlHeader;
	private JButton btnLogOff;
	private JButton btnShop;
	private JPanel pnlButtons;
	private JButton btnPlay;
	private JButton btnBack;
	private JPanel pnlGame;
	private JPanel pnlChooseBet;
	private JPanel pnlChip5;
	private JLabel imgChip5;
	private JTextField txtCurrent5;
	private JPanel pnlChip10;
	private JLabel imgChip10;
	private JPanel pnlChip20;
	private JLabel imgChip20;
	private JPanel pnlChip50;
	private JLabel imgChip50;
	private JPanel pnlChip100;
	private JLabel imgChip100;
	private JPanel pnlPlaceBet;
	private JPanel pnlConfirmAction;
	private JButton btnBet;
	private JButton btnCancel;
	private JPanel pnlBoard;
	private JPanel pnlChipSelector5;
	private JRadioButton rdbtnBet5;
	private JPanel pnlCurrent5Chips;
	private JLabel lblCurrent5;
	private JPanel pnlChipSelector10;
	private JPanel pnlChipSelector20;
	private JPanel pnlChipSelector50;
	private JPanel pnlChipSelector100;
	private JRadioButton rdbtnBet10;
	private JRadioButton rdbtnBet20;
	private JRadioButton rdbtnBet50;
	private JRadioButton rdbtnBet100;
	private JPanel pnlCurrent10Chips;
	private JLabel lblCurrent10;
	private JTextField txtCurrent10;
	private JPanel pnlCurrent20Chips;
	private JLabel lblCurrent20;
	private JTextField txtCurrent20;
	private JPanel pnlCurrent50;
	private JLabel lblCurrent50;
	private JTextField txtCurrent50;
	private JPanel pnlCurrent100Chips;
	private JLabel lblCurrent100;
	private JTextField txtCurrent100;
	private JPanel pnlYourBets;
	private JScrollPane scpYourBets;
	private JList<Bet> listYourBets;
	private JTabbedPane tabbedPane;
	private JPanel pnlMainAction;
	private JPanel pnlResults;
	private JLabel imgRoulette1;
	private JPanel pnlWinner;
	private JLabel lblWinner;
	private JTextField txtWinner;
	private JButton btnRedeemChips;
	private JScrollPane scpWiningBets;
	private JPanel pnlInfoWin;
	private JTextField txtBetsResults;
	private JLabel imgChips;
	private JButton btnCancelAllBets;
	private JButton btnPlayAgain;
	private JButton btnHelp;

	// BOARD

	private JRadioButton rbtn3;
	private JRadioButton rbtn6;
	private JRadioButton rbtn9;
	private JRadioButton rbtn12;
	private JRadioButton rbtn15;
	private JRadioButton rbtn18;
	private JRadioButton rbtn21;
	private JRadioButton rbtn24;
	private JRadioButton rbtn27;
	private JRadioButton rbtn30;
	private JRadioButton rbtn33;
	private JRadioButton rbtn36;
	private JRadioButton rbtnC1;
	private JRadioButton rbtn0;
	private JRadioButton rbtn2;
	private JRadioButton rbtn5;
	private JRadioButton rbtn8;
	private JRadioButton rbtn11;
	private JRadioButton rbtn14;
	private JRadioButton rbtn17;
	private JRadioButton rbtn20;
	private JRadioButton rbtn23;
	private JRadioButton rbtn26;
	private JRadioButton rbtn29;
	private JRadioButton rbtn32;
	private JRadioButton rbtn35;
	private JRadioButton rbtnC2;
	private JRadioButton rbtn1;
	private JRadioButton rbtn4;
	private JRadioButton rbtn7;
	private JRadioButton rbtn10;
	private JRadioButton rbtn13;
	private JRadioButton rbtn16;
	private JRadioButton rbtn19;
	private JRadioButton rbtn22;
	private JRadioButton rbtn25;
	private JRadioButton rbtn28;
	private JRadioButton rbtn31;
	private JRadioButton rbtn34;
	private JRadioButton rbtnC3;
	private JRadioButton rbtn1stDozen;
	private JRadioButton rbtn2ndDozen;
	private JRadioButton rbtn3rdDozen;
	private JRadioButton rbtn1_18;
	private JRadioButton rbtnEven;
	private JRadioButton rbtnBlack;
	private JRadioButton rbtnRed;
	private JRadioButton rbtnOdd;
	private JRadioButton rbtn19_36;

	private final ButtonGroup boardButtonGroup = new ButtonGroup();
	private final ButtonGroup chipSelectorGroup = new ButtonGroup();
	private DefaultListModel<Bet> betModel = null;

	// Windows

	private WelcomeWindow parent = null;
	private ShopWindow shopWindow = null;

	// Logic

	private Player currentPlayer = null;
	private Game game = new Game();
	private UserManagement userManagement = null;

	/**
	 * Create the dialog.
	 */
	public GameWindow(WelcomeWindow dialog) {
		parent = dialog;
		currentPlayer = getParent().getCurrentPlayer();
		userManagement = parent.getUserManagement();

		getContentPane().setBackground(Color.WHITE);
		setIconImage(Toolkit.getDefaultToolkit().getImage(GameWindow.class.getResource("/img/DiceIcon.png")));
		setTitle("Casino Roulette: Game");
		setBounds(100, 100, 770, 611);
		getContentPane().setLayout(new BorderLayout());
		getContentPane().add(getPnlHeader(), BorderLayout.NORTH);
		getContentPane().add(getPanel_2(), BorderLayout.SOUTH);
		getContentPane().add(getPanel_1_1(), BorderLayout.CENTER);

		btnPlayAgain = getBtnPlayAgain();
		btnCancel = getBtnCancel();
		loadHelp();
		updateNumberOfChips();

		addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				backOperation();
			}
		});
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

		hb.enableHelpKey(getRootPane(), "game", hs);
		hb.enableHelpOnButton(btnHelp, "game", hs);
	}

	private JPanel getPnlHeader() {
		if (pnlHeader == null) {
			pnlHeader = new JPanel();
			pnlHeader.setBackground(new Color(178, 34, 34));
			pnlHeader.setLayout(new FlowLayout(FlowLayout.TRAILING, 5, 0));
			pnlHeader.add(getBtnLogOff());
			pnlHeader.add(getBtnShop());
			pnlHeader.add(getBtnHelp());
		}
		return pnlHeader;
	}

	private JButton getBtnLogOff() {
		if (btnLogOff == null) {
			btnLogOff = new JButton("Log off");
			btnLogOff.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					logOffOperation();
					getParent().dispose();
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

	private JButton getBtnShop() {
		if (btnShop == null) {
			btnShop = new JButton("Shop");
			btnShop.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					openShopWindow();
				}
			});
			btnShop.setMnemonic('S');
			btnShop.setForeground(Color.WHITE);
			btnShop.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 13));
			btnShop.setBackground(new Color(100, 149, 237));
		}
		return btnShop;
	}

	private JPanel getPanel_2() {
		if (pnlButtons == null) {
			pnlButtons = new JPanel();
			pnlButtons.setBackground(Color.WHITE);
			pnlButtons.setLayout(new FlowLayout(FlowLayout.RIGHT));
			pnlButtons.add(getBtnBack());
			pnlButtons.add(getBtnPlay());
		}
		return pnlButtons;
	}

	private JButton getBtnPlay() {
		if (btnPlay == null) {
			btnPlay = new JButton("Play");
			btnPlay.setEnabled(false);
			btnPlay.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					playAction();
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

	private JButton getBtnBack() {
		if (btnBack == null) {
			btnBack = new JButton("Back");
			btnBack.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					backOperation();
					dispose();
				}
			});
			btnBack.setMnemonic('K');
			btnBack.setForeground(Color.WHITE);
			btnBack.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 14));
			btnBack.setBackground(Color.RED);
			btnBack.setActionCommand("Cancel");
		}
		return btnBack;
	}

	private JPanel getPanel_1_1() {
		if (pnlGame == null) {
			pnlGame = new JPanel();
			pnlGame.setBackground(Color.WHITE);
			pnlGame.setLayout(new CardLayout(0, 0));
			pnlGame.add(getTabbedPane(), "name_184598701635800");
		}
		return pnlGame;
	}

	private JPanel getPanel_2_6() {
		if (pnlChooseBet == null) {
			pnlChooseBet = new JPanel();
			pnlChooseBet.setBorder(
					new TitledBorder(null, "1. Choose your bet:", TitledBorder.LEADING, TitledBorder.TOP, null, null));
			pnlChooseBet.setBackground(Color.WHITE);
			pnlChooseBet.setLayout(new GridLayout(0, 3, 10, 10));
			pnlChooseBet.add(getPnlChip5());
			pnlChooseBet.add(getPnlChip10());
			pnlChooseBet.add(getPnlChip20());
			pnlChooseBet.add(getPnlChip50());
			pnlChooseBet.add(getPnlChip100());
		}
		return pnlChooseBet;
	}

	private JPanel getPnlChip5() {
		if (pnlChip5 == null) {
			pnlChip5 = new JPanel();
			pnlChip5.setBorder(new LineBorder(SystemColor.activeCaption, 1, true));
			pnlChip5.setBackground(Color.WHITE);
			pnlChip5.setLayout(new BorderLayout(0, 0));
			pnlChip5.add(getImgChip5(), BorderLayout.WEST);
			pnlChip5.add(getPnlChipSelector5(), BorderLayout.CENTER);
		}
		return pnlChip5;
	}

	private JLabel getImgChip5() {
		if (imgChip5 == null) {
			imgChip5 = new JLabel("");
			imgChip5.setIcon(new ImageIcon(GameWindow.class.getResource("/img/Chip5Small.png")));
			imgChip5.setHorizontalAlignment(SwingConstants.CENTER);
		}
		return imgChip5;
	}

	private JTextField getTxtCurrent5() {
		if (txtCurrent5 == null) {
			txtCurrent5 = new JTextField();
			txtCurrent5.setBackground(Color.WHITE);
			txtCurrent5.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 14));
			txtCurrent5.setEditable(false);
			txtCurrent5.setColumns(10);
		}
		return txtCurrent5;
	}

	private JPanel getPnlChip10() {
		if (pnlChip10 == null) {
			pnlChip10 = new JPanel();
			pnlChip10.setBorder(new LineBorder(SystemColor.activeCaption, 1, true));
			pnlChip10.setBackground(Color.WHITE);
			pnlChip10.setLayout(new BorderLayout(0, 0));
			pnlChip10.add(getImgChip10(), BorderLayout.WEST);
			pnlChip10.add(getPnlChipSelector10(), BorderLayout.CENTER);
		}
		return pnlChip10;
	}

	private JLabel getImgChip10() {
		if (imgChip10 == null) {
			imgChip10 = new JLabel("");
			imgChip10.setIcon(new ImageIcon(GameWindow.class.getResource("/img/Chip10Small.png")));
			imgChip10.setHorizontalAlignment(SwingConstants.CENTER);
		}
		return imgChip10;
	}

	private JPanel getPnlChip20() {
		if (pnlChip20 == null) {
			pnlChip20 = new JPanel();
			pnlChip20.setBorder(new LineBorder(SystemColor.activeCaption, 1, true));
			pnlChip20.setBackground(Color.WHITE);
			pnlChip20.setLayout(new BorderLayout(0, 0));
			pnlChip20.add(getImgChip20(), BorderLayout.WEST);
			pnlChip20.add(getPnlChipSelector20(), BorderLayout.CENTER);
		}
		return pnlChip20;
	}

	private JLabel getImgChip20() {
		if (imgChip20 == null) {
			imgChip20 = new JLabel("");
			imgChip20.setIcon(new ImageIcon(GameWindow.class.getResource("/img/Chip20Small.png")));
			imgChip20.setHorizontalAlignment(SwingConstants.CENTER);
		}
		return imgChip20;
	}

	private JPanel getPnlChip50() {
		if (pnlChip50 == null) {
			pnlChip50 = new JPanel();
			pnlChip50.setBorder(new LineBorder(SystemColor.activeCaption, 1, true));
			pnlChip50.setBackground(Color.WHITE);
			pnlChip50.setLayout(new BorderLayout(0, 0));
			pnlChip50.add(getImgChip50(), BorderLayout.WEST);
			pnlChip50.add(getPanel_2_1(), BorderLayout.CENTER);
		}
		return pnlChip50;
	}

	private JLabel getImgChip50() {
		if (imgChip50 == null) {
			imgChip50 = new JLabel("");
			imgChip50.setIcon(new ImageIcon(GameWindow.class.getResource("/img/Chip50Small.png")));
			imgChip50.setHorizontalAlignment(SwingConstants.CENTER);
		}
		return imgChip50;
	}

	private JPanel getPnlChip100() {
		if (pnlChip100 == null) {
			pnlChip100 = new JPanel();
			pnlChip100.setBorder(new LineBorder(SystemColor.activeCaption, 1, true));
			pnlChip100.setBackground(Color.WHITE);
			pnlChip100.setLayout(new BorderLayout(0, 0));
			pnlChip100.add(getImgChip100(), BorderLayout.WEST);
			pnlChip100.add(getPnlChipSelector100(), BorderLayout.CENTER);
		}
		return pnlChip100;
	}

	private JLabel getImgChip100() {
		if (imgChip100 == null) {
			imgChip100 = new JLabel("");
			imgChip100.setIcon(new ImageIcon(GameWindow.class.getResource("/img/Chip100Small.png")));
			imgChip100.setHorizontalAlignment(SwingConstants.CENTER);
		}
		return imgChip100;
	}

	private JPanel getPnlPlaceBet() {
		if (pnlPlaceBet == null) {
			pnlPlaceBet = new JPanel();
			pnlPlaceBet.setBorder(
					new TitledBorder(null, "2. Place your bet:", TitledBorder.LEADING, TitledBorder.TOP, null, null));
			pnlPlaceBet.setBackground(Color.WHITE);
			pnlPlaceBet.setLayout(new BorderLayout(0, 0));
			pnlPlaceBet.add(getPanel_10(), BorderLayout.SOUTH);
			pnlPlaceBet.add(getPnlBoard(), BorderLayout.CENTER);
		}
		return pnlPlaceBet;
	}

	private JPanel getPanel_10() {
		if (pnlConfirmAction == null) {
			pnlConfirmAction = new JPanel();
			pnlConfirmAction.setLayout(new GridLayout(0, 2, 0, 0));
			pnlConfirmAction.add(getBtnBet());
			pnlConfirmAction.add(getBtnCancel());
		}
		return pnlConfirmAction;
	}

	private JButton getBtnBet() {
		if (btnBet == null) {
			btnBet = new JButton("Bet");
			btnBet.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					betAction();
				}
			});
			btnBet.setMnemonic('B');
			btnBet.setForeground(Color.WHITE);
			btnBet.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 14));
			btnBet.setBackground(new Color(0, 128, 0));
			btnBet.setActionCommand("Cancel");
		}
		return btnBet;

	}

	private JButton getBtnCancel() {
		if (btnCancel == null) {
			btnCancel = new JButton("Cancel");
			btnCancel.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					clearRadioButtons();
				}
			});
			btnCancel.setMnemonic('C');
			btnCancel.setForeground(Color.WHITE);
			btnCancel.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 14));
			btnCancel.setBackground(Color.RED);
			btnCancel.setActionCommand("Cancel");
		}
		return btnCancel;
	}

	private JPanel getPnlBoard() {
		if (pnlBoard == null) {
			pnlBoard = new JPanel();
			pnlBoard.setBorder(null);
			pnlBoard.setBackground(new Color(0, 100, 0));
			GridBagLayout gbl_pnlBoard = new GridBagLayout();
			gbl_pnlBoard.columnWidths = new int[] { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 30, 30, 0, 30, 30, 30, 30, 0 };
			gbl_pnlBoard.rowHeights = new int[] { 0, 0, 0, 0, 0, 0 };
			gbl_pnlBoard.columnWeights = new double[] { 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0,
					0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE };
			gbl_pnlBoard.rowWeights = new double[] { 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE };
			pnlBoard.setLayout(gbl_pnlBoard);
			GridBagConstraints gbc_rbtn0 = new GridBagConstraints();
			gbc_rbtn0.gridheight = 3;
			gbc_rbtn0.insets = new Insets(0, 0, 5, 5);
			gbc_rbtn0.gridx = 1;
			gbc_rbtn0.gridy = 0;
			pnlBoard.add(getRbtn0(), gbc_rbtn0);
			GridBagConstraints gbc_rbtn3 = new GridBagConstraints();
			gbc_rbtn3.insets = new Insets(0, 0, 5, 5);
			gbc_rbtn3.gridx = 2;
			gbc_rbtn3.gridy = 0;
			pnlBoard.add(getRbtn3(), gbc_rbtn3);
			GridBagConstraints gbc_rbtn6 = new GridBagConstraints();
			gbc_rbtn6.insets = new Insets(0, 0, 5, 5);
			gbc_rbtn6.gridx = 3;
			gbc_rbtn6.gridy = 0;
			pnlBoard.add(getRbtn6(), gbc_rbtn6);
			GridBagConstraints gbc_rbtn9 = new GridBagConstraints();
			gbc_rbtn9.insets = new Insets(0, 0, 5, 5);
			gbc_rbtn9.gridx = 4;
			gbc_rbtn9.gridy = 0;
			pnlBoard.add(getRbtn9(), gbc_rbtn9);
			GridBagConstraints gbc_rbtn12 = new GridBagConstraints();
			gbc_rbtn12.insets = new Insets(0, 0, 5, 5);
			gbc_rbtn12.gridx = 5;
			gbc_rbtn12.gridy = 0;
			pnlBoard.add(getRbtn12(), gbc_rbtn12);
			GridBagConstraints gbc_rbtn15 = new GridBagConstraints();
			gbc_rbtn15.insets = new Insets(0, 0, 5, 5);
			gbc_rbtn15.gridx = 6;
			gbc_rbtn15.gridy = 0;
			pnlBoard.add(getRbtn15(), gbc_rbtn15);
			GridBagConstraints gbc_rbtn18 = new GridBagConstraints();
			gbc_rbtn18.insets = new Insets(0, 0, 5, 5);
			gbc_rbtn18.gridx = 7;
			gbc_rbtn18.gridy = 0;
			pnlBoard.add(getRbtn18(), gbc_rbtn18);
			GridBagConstraints gbc_rbtn21 = new GridBagConstraints();
			gbc_rbtn21.insets = new Insets(0, 0, 5, 5);
			gbc_rbtn21.gridx = 8;
			gbc_rbtn21.gridy = 0;
			pnlBoard.add(getRbtn21(), gbc_rbtn21);
			GridBagConstraints gbc_rbtn24 = new GridBagConstraints();
			gbc_rbtn24.insets = new Insets(0, 0, 5, 5);
			gbc_rbtn24.gridx = 9;
			gbc_rbtn24.gridy = 0;
			pnlBoard.add(getRbtn24(), gbc_rbtn24);
			GridBagConstraints gbc_rbtn27 = new GridBagConstraints();
			gbc_rbtn27.insets = new Insets(0, 0, 5, 5);
			gbc_rbtn27.gridx = 10;
			gbc_rbtn27.gridy = 0;
			pnlBoard.add(getRbtn27(), gbc_rbtn27);
			GridBagConstraints gbc_rbtn30 = new GridBagConstraints();
			gbc_rbtn30.insets = new Insets(0, 0, 5, 5);
			gbc_rbtn30.gridx = 11;
			gbc_rbtn30.gridy = 0;
			pnlBoard.add(getRbtn30(), gbc_rbtn30);
			GridBagConstraints gbc_rbtn33 = new GridBagConstraints();
			gbc_rbtn33.insets = new Insets(0, 0, 5, 5);
			gbc_rbtn33.gridx = 12;
			gbc_rbtn33.gridy = 0;
			pnlBoard.add(getRbtn33(), gbc_rbtn33);
			GridBagConstraints gbc_rbtn36 = new GridBagConstraints();
			gbc_rbtn36.insets = new Insets(0, 0, 5, 5);
			gbc_rbtn36.gridx = 13;
			gbc_rbtn36.gridy = 0;
			pnlBoard.add(getRbtn36(), gbc_rbtn36);
			GridBagConstraints gbc_rbtnC1 = new GridBagConstraints();
			gbc_rbtnC1.fill = GridBagConstraints.HORIZONTAL;
			gbc_rbtnC1.gridwidth = 3;
			gbc_rbtnC1.insets = new Insets(0, 0, 5, 5);
			gbc_rbtnC1.gridx = 14;
			gbc_rbtnC1.gridy = 0;
			pnlBoard.add(getRbtnC1(), gbc_rbtnC1);
			GridBagConstraints gbc_rbtn2 = new GridBagConstraints();
			gbc_rbtn2.insets = new Insets(0, 0, 5, 5);
			gbc_rbtn2.gridx = 2;
			gbc_rbtn2.gridy = 1;
			pnlBoard.add(getRbtn2(), gbc_rbtn2);
			GridBagConstraints gbc_rbtn5 = new GridBagConstraints();
			gbc_rbtn5.insets = new Insets(0, 0, 5, 5);
			gbc_rbtn5.gridx = 3;
			gbc_rbtn5.gridy = 1;
			pnlBoard.add(getRbtn5(), gbc_rbtn5);
			GridBagConstraints gbc_rbtn8 = new GridBagConstraints();
			gbc_rbtn8.insets = new Insets(0, 0, 5, 5);
			gbc_rbtn8.gridx = 4;
			gbc_rbtn8.gridy = 1;
			pnlBoard.add(getRbtn8(), gbc_rbtn8);
			GridBagConstraints gbc_rbtn11 = new GridBagConstraints();
			gbc_rbtn11.fill = GridBagConstraints.HORIZONTAL;
			gbc_rbtn11.insets = new Insets(0, 0, 5, 5);
			gbc_rbtn11.gridx = 5;
			gbc_rbtn11.gridy = 1;
			pnlBoard.add(getRbtn11(), gbc_rbtn11);
			GridBagConstraints gbc_rbtn14 = new GridBagConstraints();
			gbc_rbtn14.fill = GridBagConstraints.HORIZONTAL;
			gbc_rbtn14.insets = new Insets(0, 0, 5, 5);
			gbc_rbtn14.gridx = 6;
			gbc_rbtn14.gridy = 1;
			pnlBoard.add(getRbtn14(), gbc_rbtn14);
			GridBagConstraints gbc_rbtn17 = new GridBagConstraints();
			gbc_rbtn17.insets = new Insets(0, 0, 5, 5);
			gbc_rbtn17.gridx = 7;
			gbc_rbtn17.gridy = 1;
			pnlBoard.add(getRbtn17(), gbc_rbtn17);
			GridBagConstraints gbc_rbtn20 = new GridBagConstraints();
			gbc_rbtn20.insets = new Insets(0, 0, 5, 5);
			gbc_rbtn20.gridx = 8;
			gbc_rbtn20.gridy = 1;
			pnlBoard.add(getRbtn20(), gbc_rbtn20);
			GridBagConstraints gbc_rbtn23 = new GridBagConstraints();
			gbc_rbtn23.insets = new Insets(0, 0, 5, 5);
			gbc_rbtn23.gridx = 9;
			gbc_rbtn23.gridy = 1;
			pnlBoard.add(getRbtn23(), gbc_rbtn23);
			GridBagConstraints gbc_rbtn26 = new GridBagConstraints();
			gbc_rbtn26.insets = new Insets(0, 0, 5, 5);
			gbc_rbtn26.gridx = 10;
			gbc_rbtn26.gridy = 1;
			pnlBoard.add(getRbtn26(), gbc_rbtn26);
			GridBagConstraints gbc_rbtn29 = new GridBagConstraints();
			gbc_rbtn29.insets = new Insets(0, 0, 5, 5);
			gbc_rbtn29.gridx = 11;
			gbc_rbtn29.gridy = 1;
			pnlBoard.add(getRbtn29(), gbc_rbtn29);
			GridBagConstraints gbc_rbtn32 = new GridBagConstraints();
			gbc_rbtn32.insets = new Insets(0, 0, 5, 5);
			gbc_rbtn32.gridx = 12;
			gbc_rbtn32.gridy = 1;
			pnlBoard.add(getRbtn32(), gbc_rbtn32);
			GridBagConstraints gbc_rbtn35 = new GridBagConstraints();
			gbc_rbtn35.insets = new Insets(0, 0, 5, 5);
			gbc_rbtn35.gridx = 13;
			gbc_rbtn35.gridy = 1;
			pnlBoard.add(getRbtn35(), gbc_rbtn35);
			GridBagConstraints gbc_rbtnC2 = new GridBagConstraints();
			gbc_rbtnC2.fill = GridBagConstraints.HORIZONTAL;
			gbc_rbtnC2.gridwidth = 3;
			gbc_rbtnC2.insets = new Insets(0, 0, 5, 5);
			gbc_rbtnC2.gridx = 14;
			gbc_rbtnC2.gridy = 1;
			pnlBoard.add(getRbtnC2(), gbc_rbtnC2);
			GridBagConstraints gbc_rbtn1 = new GridBagConstraints();
			gbc_rbtn1.insets = new Insets(0, 0, 5, 5);
			gbc_rbtn1.gridx = 2;
			gbc_rbtn1.gridy = 2;
			pnlBoard.add(getRbtn1(), gbc_rbtn1);
			GridBagConstraints gbc_rbtn4 = new GridBagConstraints();
			gbc_rbtn4.insets = new Insets(0, 0, 5, 5);
			gbc_rbtn4.gridx = 3;
			gbc_rbtn4.gridy = 2;
			pnlBoard.add(getRbtn4(), gbc_rbtn4);
			GridBagConstraints gbc_rbtn7 = new GridBagConstraints();
			gbc_rbtn7.insets = new Insets(0, 0, 5, 5);
			gbc_rbtn7.gridx = 4;
			gbc_rbtn7.gridy = 2;
			pnlBoard.add(getRbtn7(), gbc_rbtn7);
			GridBagConstraints gbc_rbtn10 = new GridBagConstraints();
			gbc_rbtn10.fill = GridBagConstraints.HORIZONTAL;
			gbc_rbtn10.insets = new Insets(0, 0, 5, 5);
			gbc_rbtn10.gridx = 5;
			gbc_rbtn10.gridy = 2;
			pnlBoard.add(getRbtn10(), gbc_rbtn10);
			GridBagConstraints gbc_rbtn13 = new GridBagConstraints();
			gbc_rbtn13.fill = GridBagConstraints.HORIZONTAL;
			gbc_rbtn13.insets = new Insets(0, 0, 5, 5);
			gbc_rbtn13.gridx = 6;
			gbc_rbtn13.gridy = 2;
			pnlBoard.add(getRbtn13(), gbc_rbtn13);
			GridBagConstraints gbc_rbtn16 = new GridBagConstraints();
			gbc_rbtn16.insets = new Insets(0, 0, 5, 5);
			gbc_rbtn16.gridx = 7;
			gbc_rbtn16.gridy = 2;
			pnlBoard.add(getRbtn16(), gbc_rbtn16);
			GridBagConstraints gbc_rbtn19 = new GridBagConstraints();
			gbc_rbtn19.insets = new Insets(0, 0, 5, 5);
			gbc_rbtn19.gridx = 8;
			gbc_rbtn19.gridy = 2;
			pnlBoard.add(getRbtn19(), gbc_rbtn19);
			GridBagConstraints gbc_rbtn22 = new GridBagConstraints();
			gbc_rbtn22.insets = new Insets(0, 0, 5, 5);
			gbc_rbtn22.gridx = 9;
			gbc_rbtn22.gridy = 2;
			pnlBoard.add(getRbtn22(), gbc_rbtn22);
			GridBagConstraints gbc_rbtn25 = new GridBagConstraints();
			gbc_rbtn25.insets = new Insets(0, 0, 5, 5);
			gbc_rbtn25.gridx = 10;
			gbc_rbtn25.gridy = 2;
			pnlBoard.add(getRbtn25(), gbc_rbtn25);
			GridBagConstraints gbc_rbtn28 = new GridBagConstraints();
			gbc_rbtn28.insets = new Insets(0, 0, 5, 5);
			gbc_rbtn28.gridx = 11;
			gbc_rbtn28.gridy = 2;
			pnlBoard.add(getRbtn28(), gbc_rbtn28);
			GridBagConstraints gbc_rbtn31 = new GridBagConstraints();
			gbc_rbtn31.insets = new Insets(0, 0, 5, 5);
			gbc_rbtn31.gridx = 12;
			gbc_rbtn31.gridy = 2;
			pnlBoard.add(getRbtn31(), gbc_rbtn31);
			GridBagConstraints gbc_rbtn34 = new GridBagConstraints();
			gbc_rbtn34.insets = new Insets(0, 0, 5, 5);
			gbc_rbtn34.gridx = 13;
			gbc_rbtn34.gridy = 2;
			pnlBoard.add(getRbtn34(), gbc_rbtn34);
			GridBagConstraints gbc_rbtnC3 = new GridBagConstraints();
			gbc_rbtnC3.fill = GridBagConstraints.BOTH;
			gbc_rbtnC3.gridwidth = 3;
			gbc_rbtnC3.insets = new Insets(0, 0, 5, 5);
			gbc_rbtnC3.gridx = 14;
			gbc_rbtnC3.gridy = 2;
			pnlBoard.add(getRbtnC3(), gbc_rbtnC3);
			GridBagConstraints gbc_rbtn1stDozen = new GridBagConstraints();
			gbc_rbtn1stDozen.fill = GridBagConstraints.HORIZONTAL;
			gbc_rbtn1stDozen.gridwidth = 4;
			gbc_rbtn1stDozen.insets = new Insets(0, 0, 5, 5);
			gbc_rbtn1stDozen.gridx = 2;
			gbc_rbtn1stDozen.gridy = 3;
			pnlBoard.add(getRbtn1stDozen(), gbc_rbtn1stDozen);
			GridBagConstraints gbc_rbtn2ndDozen = new GridBagConstraints();
			gbc_rbtn2ndDozen.fill = GridBagConstraints.HORIZONTAL;
			gbc_rbtn2ndDozen.gridwidth = 4;
			gbc_rbtn2ndDozen.insets = new Insets(0, 0, 5, 5);
			gbc_rbtn2ndDozen.gridx = 6;
			gbc_rbtn2ndDozen.gridy = 3;
			pnlBoard.add(getRbtn2ndDozen(), gbc_rbtn2ndDozen);
			GridBagConstraints gbc_rbtn3rdDozen = new GridBagConstraints();
			gbc_rbtn3rdDozen.fill = GridBagConstraints.HORIZONTAL;
			gbc_rbtn3rdDozen.gridwidth = 4;
			gbc_rbtn3rdDozen.insets = new Insets(0, 0, 5, 5);
			gbc_rbtn3rdDozen.gridx = 10;
			gbc_rbtn3rdDozen.gridy = 3;
			pnlBoard.add(getRbtn3rdDozen(), gbc_rbtn3rdDozen);
			GridBagConstraints gbc_rbtn1_18 = new GridBagConstraints();
			gbc_rbtn1_18.fill = GridBagConstraints.HORIZONTAL;
			gbc_rbtn1_18.gridwidth = 2;
			gbc_rbtn1_18.insets = new Insets(0, 0, 0, 5);
			gbc_rbtn1_18.gridx = 2;
			gbc_rbtn1_18.gridy = 4;
			pnlBoard.add(getRbtn1_18(), gbc_rbtn1_18);
			GridBagConstraints gbc_rbtnEven = new GridBagConstraints();
			gbc_rbtnEven.fill = GridBagConstraints.HORIZONTAL;
			gbc_rbtnEven.gridwidth = 2;
			gbc_rbtnEven.insets = new Insets(0, 0, 0, 5);
			gbc_rbtnEven.gridx = 4;
			gbc_rbtnEven.gridy = 4;
			pnlBoard.add(getRbtnEven(), gbc_rbtnEven);
			GridBagConstraints gbc_rbtnBlack = new GridBagConstraints();
			gbc_rbtnBlack.fill = GridBagConstraints.HORIZONTAL;
			gbc_rbtnBlack.gridwidth = 2;
			gbc_rbtnBlack.insets = new Insets(0, 0, 0, 5);
			gbc_rbtnBlack.gridx = 6;
			gbc_rbtnBlack.gridy = 4;
			pnlBoard.add(getRbtnBlack(), gbc_rbtnBlack);
			GridBagConstraints gbc_rbtnRed = new GridBagConstraints();
			gbc_rbtnRed.fill = GridBagConstraints.HORIZONTAL;
			gbc_rbtnRed.gridwidth = 2;
			gbc_rbtnRed.insets = new Insets(0, 0, 0, 5);
			gbc_rbtnRed.gridx = 8;
			gbc_rbtnRed.gridy = 4;
			pnlBoard.add(getRbtnRed(), gbc_rbtnRed);
			GridBagConstraints gbc_rbtnOdd = new GridBagConstraints();
			gbc_rbtnOdd.fill = GridBagConstraints.HORIZONTAL;
			gbc_rbtnOdd.gridwidth = 2;
			gbc_rbtnOdd.insets = new Insets(0, 0, 0, 5);
			gbc_rbtnOdd.gridx = 10;
			gbc_rbtnOdd.gridy = 4;
			pnlBoard.add(getRbtnOdd(), gbc_rbtnOdd);
			GridBagConstraints gbc_rbtn19_36 = new GridBagConstraints();
			gbc_rbtn19_36.fill = GridBagConstraints.HORIZONTAL;
			gbc_rbtn19_36.gridwidth = 2;
			gbc_rbtn19_36.insets = new Insets(0, 0, 0, 5);
			gbc_rbtn19_36.gridx = 12;
			gbc_rbtn19_36.gridy = 4;
			pnlBoard.add(getRbtn19_36(), gbc_rbtn19_36);
		}
		return pnlBoard;
	}

	private JRadioButton getRbtn3() {
		if (rbtn3 == null) {
			rbtn3 = new JRadioButton("3");
			rbtn3.setForeground(Color.WHITE);
			rbtn3.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 14));
			rbtn3.setBackground(new Color(139, 0, 0));
			boardButtonGroup.add(rbtn3);
			rbtn3.setActionCommand("3");
		}
		return rbtn3;
	}

	private JRadioButton getRbtn6() {
		if (rbtn6 == null) {
			rbtn6 = new JRadioButton("6");
			rbtn6.setForeground(Color.WHITE);
			rbtn6.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 14));
			rbtn6.setBackground(new Color(0, 0, 0));
			boardButtonGroup.add(rbtn6);
			rbtn6.setActionCommand("6");
		}
		return rbtn6;
	}

	private JRadioButton getRbtn9() {
		if (rbtn9 == null) {
			rbtn9 = new JRadioButton("9");
			rbtn9.setForeground(Color.WHITE);
			rbtn9.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 14));
			rbtn9.setBackground(new Color(128, 0, 0));
			boardButtonGroup.add(rbtn9);
			rbtn9.setActionCommand("9");
		}
		return rbtn9;
	}

	private JRadioButton getRbtn12() {
		if (rbtn12 == null) {
			rbtn12 = new JRadioButton("12");
			rbtn12.setForeground(Color.WHITE);
			rbtn12.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 14));
			rbtn12.setBackground(new Color(0, 0, 0));
			boardButtonGroup.add(rbtn12);
			rbtn12.setActionCommand("12");
		}
		return rbtn12;
	}

	private JRadioButton getRbtn15() {
		if (rbtn15 == null) {
			rbtn15 = new JRadioButton("15");
			rbtn15.setForeground(Color.WHITE);
			rbtn15.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 14));
			rbtn15.setBackground(new Color(139, 0, 0));
			boardButtonGroup.add(rbtn15);
			rbtn15.setActionCommand("15");
		}
		return rbtn15;
	}

	private JRadioButton getRbtn18() {
		if (rbtn18 == null) {
			rbtn18 = new JRadioButton("18");
			rbtn18.setForeground(Color.WHITE);
			rbtn18.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 14));
			rbtn18.setBackground(new Color(0, 0, 0));
			boardButtonGroup.add(rbtn18);
			rbtn18.setActionCommand("18");
		}
		return rbtn18;
	}

	private JRadioButton getRbtn21() {
		if (rbtn21 == null) {
			rbtn21 = new JRadioButton("21");
			rbtn21.setForeground(Color.WHITE);
			rbtn21.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 14));
			rbtn21.setBackground(new Color(139, 0, 0));
			boardButtonGroup.add(rbtn21);
			rbtn21.setActionCommand("21");
		}
		return rbtn21;
	}

	private JRadioButton getRbtn24() {
		if (rbtn24 == null) {
			rbtn24 = new JRadioButton("24");
			rbtn24.setForeground(Color.WHITE);
			rbtn24.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 14));
			rbtn24.setBackground(new Color(0, 0, 0));
			boardButtonGroup.add(rbtn24);
			rbtn24.setActionCommand("24");
		}
		return rbtn24;
	}

	private JRadioButton getRbtn27() {
		if (rbtn27 == null) {
			rbtn27 = new JRadioButton("27");
			rbtn27.setForeground(Color.WHITE);
			rbtn27.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 14));
			rbtn27.setBackground(new Color(139, 0, 0));
			boardButtonGroup.add(rbtn27);
			rbtn27.setActionCommand("27");
		}
		return rbtn27;
	}

	private JRadioButton getRbtn30() {
		if (rbtn30 == null) {
			rbtn30 = new JRadioButton("30");
			rbtn30.setForeground(Color.WHITE);
			rbtn30.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 14));
			rbtn30.setBackground(new Color(0, 0, 0));
			boardButtonGroup.add(rbtn30);
			rbtn30.setActionCommand("30");
		}
		return rbtn30;
	}

	private JRadioButton getRbtn33() {
		if (rbtn33 == null) {
			rbtn33 = new JRadioButton("33");
			rbtn33.setForeground(Color.WHITE);
			rbtn33.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 14));
			rbtn33.setBackground(new Color(139, 0, 0));
			boardButtonGroup.add(rbtn33);
			rbtn33.setActionCommand("33");
		}
		return rbtn33;
	}

	private JRadioButton getRbtn36() {
		if (rbtn36 == null) {
			rbtn36 = new JRadioButton("36");
			rbtn36.setForeground(Color.WHITE);
			rbtn36.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 14));
			rbtn36.setBackground(new Color(0, 0, 0));
			boardButtonGroup.add(rbtn36);
			rbtn36.setActionCommand("36");
		}
		return rbtn36;
	}

	private JRadioButton getRbtnC1() {
		if (rbtnC1 == null) {
			rbtnC1 = new JRadioButton("C1");
			rbtnC1.setForeground(Color.WHITE);
			rbtnC1.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 14));
			rbtnC1.setBackground(new Color(0, 0, 139));
			boardButtonGroup.add(rbtnC1);
			rbtnC1.setActionCommand("C1");
		}
		return rbtnC1;
	}

	private JRadioButton getRbtn0() {
		if (rbtn0 == null) {
			rbtn0 = new JRadioButton("0");
			rbtn0.setForeground(Color.WHITE);
			rbtn0.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 14));
			rbtn0.setBackground(new Color(0, 0, 139));
			rbtn0.setAlignmentX(0.5f);
			boardButtonGroup.add(rbtn0);
			rbtn0.setActionCommand("0");
		}
		return rbtn0;
	}

	private JRadioButton getRbtn2() {
		if (rbtn2 == null) {
			rbtn2 = new JRadioButton("2");
			rbtn2.setForeground(Color.WHITE);
			rbtn2.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 14));
			rbtn2.setBackground(new Color(0, 0, 0));
			boardButtonGroup.add(rbtn2);
			rbtn2.setActionCommand("2");
		}
		return rbtn2;
	}

	private JRadioButton getRbtn5() {
		if (rbtn5 == null) {
			rbtn5 = new JRadioButton("5");
			rbtn5.setForeground(Color.WHITE);
			rbtn5.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 14));
			rbtn5.setBackground(new Color(139, 0, 0));
			boardButtonGroup.add(rbtn5);
			rbtn5.setActionCommand("5");
		}
		return rbtn5;
	}

	private JRadioButton getRbtn8() {
		if (rbtn8 == null) {
			rbtn8 = new JRadioButton("8");
			rbtn8.setForeground(Color.WHITE);
			rbtn8.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 14));
			rbtn8.setBackground(new Color(0, 0, 0));
			boardButtonGroup.add(rbtn8);
			rbtn8.setActionCommand("8");
		}
		return rbtn8;
	}

	private JRadioButton getRbtn11() {
		if (rbtn11 == null) {
			rbtn11 = new JRadioButton("11");
			rbtn11.setForeground(Color.WHITE);
			rbtn11.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 14));
			rbtn11.setBackground(new Color(139, 0, 0));
			boardButtonGroup.add(rbtn11);
			rbtn11.setActionCommand("11");
		}
		return rbtn11;
	}

	private JRadioButton getRbtn14() {
		if (rbtn14 == null) {
			rbtn14 = new JRadioButton("14");
			rbtn14.setForeground(Color.WHITE);
			rbtn14.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 14));
			rbtn14.setBackground(new Color(0, 0, 0));
			boardButtonGroup.add(rbtn14);
			rbtn14.setActionCommand("14");
		}
		return rbtn14;
	}

	private JRadioButton getRbtn17() {
		if (rbtn17 == null) {
			rbtn17 = new JRadioButton("17");
			rbtn17.setForeground(Color.WHITE);
			rbtn17.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 14));
			rbtn17.setBackground(new Color(139, 0, 0));
			boardButtonGroup.add(rbtn17);
			rbtn17.setActionCommand("17");
		}
		return rbtn17;
	}

	private JRadioButton getRbtn20() {
		if (rbtn20 == null) {
			rbtn20 = new JRadioButton("20");
			rbtn20.setForeground(Color.WHITE);
			rbtn20.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 14));
			rbtn20.setBackground(new Color(0, 0, 0));
			boardButtonGroup.add(rbtn20);
			rbtn20.setActionCommand("20");
		}
		return rbtn20;
	}

	private JRadioButton getRbtn23() {
		if (rbtn23 == null) {
			rbtn23 = new JRadioButton("23");
			rbtn23.setForeground(Color.WHITE);
			rbtn23.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 14));
			rbtn23.setBackground(new Color(139, 0, 0));
			boardButtonGroup.add(rbtn23);
			rbtn23.setActionCommand("23");
		}
		return rbtn23;
	}

	private JRadioButton getRbtn26() {
		if (rbtn26 == null) {
			rbtn26 = new JRadioButton("26");
			rbtn26.setForeground(Color.WHITE);
			rbtn26.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 14));
			rbtn26.setBackground(new Color(0, 0, 0));
			boardButtonGroup.add(rbtn26);
			rbtn26.setActionCommand("26");
		}
		return rbtn26;
	}

	private JRadioButton getRbtn29() {
		if (rbtn29 == null) {
			rbtn29 = new JRadioButton("29");
			rbtn29.setForeground(Color.WHITE);
			rbtn29.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 14));
			rbtn29.setBackground(new Color(139, 0, 0));
			boardButtonGroup.add(rbtn29);
			rbtn29.setActionCommand("29");
		}
		return rbtn29;
	}

	private JRadioButton getRbtn32() {
		if (rbtn32 == null) {
			rbtn32 = new JRadioButton("32");
			rbtn32.setForeground(Color.WHITE);
			rbtn32.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 14));
			rbtn32.setBackground(new Color(0, 0, 0));
			boardButtonGroup.add(rbtn32);
			rbtn32.setActionCommand("32");
		}
		return rbtn32;
	}

	private JRadioButton getRbtn35() {
		if (rbtn35 == null) {
			rbtn35 = new JRadioButton("35");
			rbtn35.setForeground(Color.WHITE);
			rbtn35.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 14));
			rbtn35.setBackground(new Color(139, 0, 0));
			boardButtonGroup.add(rbtn35);
			rbtn35.setActionCommand("35");
		}
		return rbtn35;
	}

	private JRadioButton getRbtnC2() {
		if (rbtnC2 == null) {
			rbtnC2 = new JRadioButton("C2");
			rbtnC2.setForeground(Color.WHITE);
			rbtnC2.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 14));
			rbtnC2.setBackground(new Color(0, 0, 139));
			boardButtonGroup.add(rbtnC2);
			rbtnC2.setActionCommand("C2");
		}
		return rbtnC2;
	}

	private JRadioButton getRbtn1() {
		if (rbtn1 == null) {
			rbtn1 = new JRadioButton("1");
			rbtn1.setForeground(Color.WHITE);
			rbtn1.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 14));
			rbtn1.setBackground(new Color(139, 0, 0));
			boardButtonGroup.add(rbtn1);
			rbtn1.setActionCommand("1");
		}
		return rbtn1;
	}

	private JRadioButton getRbtn4() {
		if (rbtn4 == null) {
			rbtn4 = new JRadioButton("4");
			rbtn4.setForeground(Color.WHITE);
			rbtn4.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 14));
			rbtn4.setBackground(new Color(0, 0, 0));
			boardButtonGroup.add(rbtn4);
			rbtn4.setActionCommand("4");
		}
		return rbtn4;
	}

	private JRadioButton getRbtn7() {
		if (rbtn7 == null) {
			rbtn7 = new JRadioButton("7");
			rbtn7.setForeground(Color.WHITE);
			rbtn7.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 14));
			rbtn7.setBackground(new Color(139, 0, 0));
			boardButtonGroup.add(rbtn7);
			rbtn7.setActionCommand("7");
		}
		return rbtn7;
	}

	private JRadioButton getRbtn10() {
		if (rbtn10 == null) {
			rbtn10 = new JRadioButton("10");
			rbtn10.setForeground(Color.WHITE);
			rbtn10.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 14));
			rbtn10.setBackground(new Color(0, 0, 0));
			boardButtonGroup.add(rbtn10);
			rbtn10.setActionCommand("10");
		}
		return rbtn10;
	}

	private JRadioButton getRbtn13() {
		if (rbtn13 == null) {
			rbtn13 = new JRadioButton("13");
			rbtn13.setForeground(Color.WHITE);
			rbtn13.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 14));
			rbtn13.setBackground(new Color(139, 0, 0));
			boardButtonGroup.add(rbtn13);
			rbtn13.setActionCommand("13");
		}
		return rbtn13;
	}

	private JRadioButton getRbtn16() {
		if (rbtn16 == null) {
			rbtn16 = new JRadioButton("16");
			rbtn16.setForeground(Color.WHITE);
			rbtn16.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 14));
			rbtn16.setBackground(new Color(0, 0, 0));
			boardButtonGroup.add(rbtn16);
			rbtn16.setActionCommand("16");
		}
		return rbtn16;
	}

	private JRadioButton getRbtn19() {
		if (rbtn19 == null) {
			rbtn19 = new JRadioButton("19");
			rbtn19.setForeground(Color.WHITE);
			rbtn19.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 14));
			rbtn19.setBackground(new Color(139, 0, 0));
			boardButtonGroup.add(rbtn19);
			rbtn19.setActionCommand("19");
		}
		return rbtn19;
	}

	private JRadioButton getRbtn22() {
		if (rbtn22 == null) {
			rbtn22 = new JRadioButton("22");
			rbtn22.setForeground(Color.WHITE);
			rbtn22.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 14));
			rbtn22.setBackground(new Color(0, 0, 0));
			boardButtonGroup.add(rbtn22);
			rbtn22.setActionCommand("22");
		}
		return rbtn22;
	}

	private JRadioButton getRbtn25() {
		if (rbtn25 == null) {
			rbtn25 = new JRadioButton("25");
			rbtn25.setForeground(Color.WHITE);
			rbtn25.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 14));
			rbtn25.setBackground(new Color(139, 0, 0));
			boardButtonGroup.add(rbtn25);
			rbtn25.setActionCommand("25");
		}
		return rbtn25;
	}

	private JRadioButton getRbtn28() {
		if (rbtn28 == null) {
			rbtn28 = new JRadioButton("28");
			rbtn28.setForeground(Color.WHITE);
			rbtn28.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 14));
			rbtn28.setBackground(new Color(0, 0, 0));
			boardButtonGroup.add(rbtn28);
			rbtn28.setActionCommand("28");
		}
		return rbtn28;
	}

	private JRadioButton getRbtn31() {
		if (rbtn31 == null) {
			rbtn31 = new JRadioButton("31");
			rbtn31.setForeground(Color.WHITE);
			rbtn31.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 14));
			rbtn31.setBackground(new Color(139, 0, 0));
			boardButtonGroup.add(rbtn31);
			rbtn31.setActionCommand("31");
		}
		return rbtn31;
	}

	private JRadioButton getRbtn34() {
		if (rbtn34 == null) {
			rbtn34 = new JRadioButton("34");
			rbtn34.setForeground(Color.WHITE);
			rbtn34.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 14));
			rbtn34.setBackground(new Color(0, 0, 0));
			boardButtonGroup.add(rbtn34);
			rbtn34.setActionCommand("34");
		}
		return rbtn34;
	}

	private JRadioButton getRbtnC3() {
		if (rbtnC3 == null) {
			rbtnC3 = new JRadioButton("C3");
			rbtnC3.setForeground(Color.WHITE);
			rbtnC3.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 14));
			rbtnC3.setBackground(new Color(0, 0, 139));
			boardButtonGroup.add(rbtnC3);
			rbtnC3.setActionCommand("C3");
		}
		return rbtnC3;
	}

	private JRadioButton getRbtn1stDozen() {
		if (rbtn1stDozen == null) {
			rbtn1stDozen = new JRadioButton("1st dozen");
			rbtn1stDozen.setHorizontalAlignment(SwingConstants.CENTER);
			rbtn1stDozen.setForeground(Color.WHITE);
			rbtn1stDozen.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 14));
			rbtn1stDozen.setBackground(new Color(100, 149, 237));
			boardButtonGroup.add(rbtn1stDozen);
			rbtn1stDozen.setActionCommand("1st dozen");
		}
		return rbtn1stDozen;
	}

	private JRadioButton getRbtn2ndDozen() {
		if (rbtn2ndDozen == null) {
			rbtn2ndDozen = new JRadioButton("2nd dozen");
			rbtn2ndDozen.setHorizontalAlignment(SwingConstants.CENTER);
			rbtn2ndDozen.setForeground(Color.WHITE);
			rbtn2ndDozen.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 14));
			rbtn2ndDozen.setBackground(new Color(100, 149, 237));
			boardButtonGroup.add(rbtn2ndDozen);
			rbtn2ndDozen.setActionCommand("2nd dozen");
		}
		return rbtn2ndDozen;
	}

	private JRadioButton getRbtn3rdDozen() {
		if (rbtn3rdDozen == null) {
			rbtn3rdDozen = new JRadioButton("3rd dozen");
			rbtn3rdDozen.setHorizontalAlignment(SwingConstants.CENTER);
			rbtn3rdDozen.setForeground(Color.WHITE);
			rbtn3rdDozen.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 14));
			rbtn3rdDozen.setBackground(new Color(100, 149, 237));
			boardButtonGroup.add(rbtn3rdDozen);
			rbtn3rdDozen.setActionCommand("3rd dozen");
		}
		return rbtn3rdDozen;
	}

	private JRadioButton getRbtn1_18() {
		if (rbtn1_18 == null) {
			rbtn1_18 = new JRadioButton("1-18");
			rbtn1_18.setHorizontalAlignment(SwingConstants.CENTER);
			rbtn1_18.setForeground(Color.WHITE);
			rbtn1_18.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 14));
			rbtn1_18.setBackground(new Color(0, 0, 139));
			boardButtonGroup.add(rbtn1_18);
			rbtn1_18.setActionCommand("1-18");
		}
		return rbtn1_18;
	}

	private JRadioButton getRbtnEven() {
		if (rbtnEven == null) {
			rbtnEven = new JRadioButton("Even");
			rbtnEven.setHorizontalAlignment(SwingConstants.CENTER);
			rbtnEven.setForeground(Color.WHITE);
			rbtnEven.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 14));
			rbtnEven.setBackground(new Color(0, 0, 139));
			boardButtonGroup.add(rbtnEven);
			rbtnEven.setActionCommand("Even");
		}
		return rbtnEven;
	}

	private JRadioButton getRbtnBlack() {
		if (rbtnBlack == null) {
			rbtnBlack = new JRadioButton("Black");
			rbtnBlack.setHorizontalAlignment(SwingConstants.CENTER);
			rbtnBlack.setForeground(Color.WHITE);
			rbtnBlack.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 14));
			rbtnBlack.setBackground(new Color(0, 0, 139));
			boardButtonGroup.add(rbtnBlack);
			rbtnBlack.setActionCommand("Black");
		}
		return rbtnBlack;
	}

	private JRadioButton getRbtnRed() {
		if (rbtnRed == null) {
			rbtnRed = new JRadioButton("Red");
			rbtnRed.setHorizontalAlignment(SwingConstants.CENTER);
			rbtnRed.setForeground(Color.WHITE);
			rbtnRed.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 14));
			rbtnRed.setBackground(new Color(0, 0, 139));
			boardButtonGroup.add(rbtnRed);
			rbtnRed.setActionCommand("Red");
		}
		return rbtnRed;
	}

	private JRadioButton getRbtnOdd() {
		if (rbtnOdd == null) {
			rbtnOdd = new JRadioButton("Odd");
			rbtnOdd.setHorizontalAlignment(SwingConstants.CENTER);
			rbtnOdd.setForeground(Color.WHITE);
			rbtnOdd.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 14));
			rbtnOdd.setBackground(new Color(0, 0, 139));
			boardButtonGroup.add(rbtnOdd);
			rbtnOdd.setActionCommand("Odd");
		}
		return rbtnOdd;
	}

	private JRadioButton getRbtn19_36() {
		if (rbtn19_36 == null) {
			rbtn19_36 = new JRadioButton("19-36");
			rbtn19_36.setHorizontalAlignment(SwingConstants.CENTER);
			rbtn19_36.setForeground(Color.WHITE);
			rbtn19_36.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 14));
			rbtn19_36.setBackground(new Color(0, 0, 139));
			boardButtonGroup.add(rbtn19_36);
			rbtn19_36.setActionCommand("19-36");
		}
		return rbtn19_36;
	}

	private JPanel getPnlYourBets() {
		if (pnlYourBets == null) {
			pnlYourBets = new JPanel();
			pnlYourBets.setBorder(null);
			pnlYourBets.setBackground(Color.WHITE);
			pnlYourBets.setLayout(new BorderLayout(30, 0));
			pnlYourBets.add(getScpYourBets(), BorderLayout.CENTER);
			pnlYourBets.add(getBtnCancelAllBets(), BorderLayout.SOUTH);
		}
		return pnlYourBets;
	}

	private JScrollPane getScpYourBets() {
		if (scpYourBets == null) {
			scpYourBets = new JScrollPane();
			scpYourBets.setViewportView(getListYourBets());
		}
		return scpYourBets;
	}

	private JList<Bet> getListYourBets() {
		if (listYourBets == null) {
			betModel = new DefaultListModel<Bet>();
			listYourBets = new JList<Bet>(betModel);
			listYourBets.setBackground(Color.WHITE);
			listYourBets.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 14));
		}
		return listYourBets;
	}

	private JPanel getPnlChipSelector5() {
		if (pnlChipSelector5 == null) {
			pnlChipSelector5 = new JPanel();
			pnlChipSelector5.setBackground(Color.WHITE);
			pnlChipSelector5.setLayout(new BorderLayout(0, 0));
			pnlChipSelector5.add(getRdbtnBet5(), BorderLayout.NORTH);
			pnlChipSelector5.add(getPnlCurrent5Chips(), BorderLayout.CENTER);
		}
		return pnlChipSelector5;
	}

	private JRadioButton getRdbtnBet5() {
		if (rdbtnBet5 == null) {
			rdbtnBet5 = new JRadioButton("Bet a 5 chip");
			rdbtnBet5.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 14));
			rdbtnBet5.setBackground(Color.WHITE);
			chipSelectorGroup.add(rdbtnBet5);
			rdbtnBet5.setActionCommand("5");
		}
		return rdbtnBet5;
	}

	private JPanel getPnlCurrent5Chips() {
		if (pnlCurrent5Chips == null) {
			pnlCurrent5Chips = new JPanel();
			pnlCurrent5Chips.setBackground(Color.WHITE);
			pnlCurrent5Chips.add(getLblCurrent5());
			pnlCurrent5Chips.add(getTxtCurrent5());
		}
		return pnlCurrent5Chips;
	}

	private JLabel getLblCurrent5() {
		if (lblCurrent5 == null) {
			lblCurrent5 = new JLabel("Current 5 Chips:");
			lblCurrent5.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 14));
		}
		return lblCurrent5;
	}

	private JPanel getPnlChipSelector10() {
		if (pnlChipSelector10 == null) {
			pnlChipSelector10 = new JPanel();
			pnlChipSelector10.setBackground(Color.WHITE);
			pnlChipSelector10.setLayout(new BorderLayout(0, 0));
			pnlChipSelector10.add(getRdbtnBet10(), BorderLayout.NORTH);
			pnlChipSelector10.add(getPnlCurrent10Chips(), BorderLayout.CENTER);
		}
		return pnlChipSelector10;
	}

	private JPanel getPnlChipSelector20() {
		if (pnlChipSelector20 == null) {
			pnlChipSelector20 = new JPanel();
			pnlChipSelector20.setBackground(Color.WHITE);
			pnlChipSelector20.setLayout(new BorderLayout(0, 0));
			pnlChipSelector20.add(getRdbtnBet20(), BorderLayout.NORTH);
			pnlChipSelector20.add(getPnlCurrent20Chips(), BorderLayout.CENTER);
		}
		return pnlChipSelector20;
	}

	private JPanel getPanel_2_1() {
		if (pnlChipSelector50 == null) {
			pnlChipSelector50 = new JPanel();
			pnlChipSelector50.setBackground(Color.WHITE);
			pnlChipSelector50.setLayout(new BorderLayout(0, 0));
			pnlChipSelector50.add(getRdbtnBet50(), BorderLayout.NORTH);
			pnlChipSelector50.add(getPnlCurrent50(), BorderLayout.CENTER);
		}
		return pnlChipSelector50;
	}

	private JPanel getPnlChipSelector100() {
		if (pnlChipSelector100 == null) {
			pnlChipSelector100 = new JPanel();
			pnlChipSelector100.setBackground(Color.WHITE);
			pnlChipSelector100.setLayout(new BorderLayout(0, 0));
			pnlChipSelector100.add(getRdbtnBet100(), BorderLayout.NORTH);
			pnlChipSelector100.add(getPnlCurrent100Chips(), BorderLayout.CENTER);
		}
		return pnlChipSelector100;
	}

	private JRadioButton getRdbtnBet10() {
		if (rdbtnBet10 == null) {
			rdbtnBet10 = new JRadioButton("Bet a 10 chip");
			rdbtnBet10.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 14));
			rdbtnBet10.setBackground(Color.WHITE);
			chipSelectorGroup.add(rdbtnBet10);
			rdbtnBet10.setActionCommand("10");
		}
		return rdbtnBet10;
	}

	private JRadioButton getRdbtnBet20() {
		if (rdbtnBet20 == null) {
			rdbtnBet20 = new JRadioButton("Bet a 20 chip");
			rdbtnBet20.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 14));
			rdbtnBet20.setBackground(Color.WHITE);
			chipSelectorGroup.add(rdbtnBet20);
			rdbtnBet20.setActionCommand("20");
		}
		return rdbtnBet20;
	}

	private JRadioButton getRdbtnBet50() {
		if (rdbtnBet50 == null) {
			rdbtnBet50 = new JRadioButton("Bet a 50 chip");
			rdbtnBet50.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 14));
			rdbtnBet50.setBackground(Color.WHITE);
			chipSelectorGroup.add(rdbtnBet50);
			rdbtnBet50.setActionCommand("50");
		}
		return rdbtnBet50;
	}

	private JRadioButton getRdbtnBet100() {
		if (rdbtnBet100 == null) {
			rdbtnBet100 = new JRadioButton("Bet a 100 chip");
			rdbtnBet100.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 14));
			rdbtnBet100.setBackground(Color.WHITE);
			chipSelectorGroup.add(rdbtnBet100);
			rdbtnBet100.setActionCommand("100");
		}
		return rdbtnBet100;
	}

	private JPanel getPnlCurrent10Chips() {
		if (pnlCurrent10Chips == null) {
			pnlCurrent10Chips = new JPanel();
			pnlCurrent10Chips.setBackground(Color.WHITE);
			pnlCurrent10Chips.add(getLblCurrent10());
			pnlCurrent10Chips.add(getTxtCurrent10());
		}
		return pnlCurrent10Chips;
	}

	private JLabel getLblCurrent10() {
		if (lblCurrent10 == null) {
			lblCurrent10 = new JLabel("Current 10 Chips:");
			lblCurrent10.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 14));
		}
		return lblCurrent10;
	}

	private JTextField getTxtCurrent10() {
		if (txtCurrent10 == null) {
			txtCurrent10 = new JTextField();
			txtCurrent10.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 14));
			txtCurrent10.setEditable(false);
			txtCurrent10.setColumns(10);
			txtCurrent10.setBackground(Color.WHITE);
		}
		return txtCurrent10;
	}

	private JPanel getPnlCurrent20Chips() {
		if (pnlCurrent20Chips == null) {
			pnlCurrent20Chips = new JPanel();
			pnlCurrent20Chips.setBackground(Color.WHITE);
			pnlCurrent20Chips.add(getLblCurrent20());
			pnlCurrent20Chips.add(getTxtCurrent20());
		}
		return pnlCurrent20Chips;
	}

	private JLabel getLblCurrent20() {
		if (lblCurrent20 == null) {
			lblCurrent20 = new JLabel("Current 20 Chips:");
			lblCurrent20.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 14));
		}
		return lblCurrent20;
	}

	private JTextField getTxtCurrent20() {
		if (txtCurrent20 == null) {
			txtCurrent20 = new JTextField();
			txtCurrent20.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 14));
			txtCurrent20.setEditable(false);
			txtCurrent20.setColumns(10);
			txtCurrent20.setBackground(Color.WHITE);
		}
		return txtCurrent20;
	}

	private JPanel getPnlCurrent50() {
		if (pnlCurrent50 == null) {
			pnlCurrent50 = new JPanel();
			pnlCurrent50.setBackground(Color.WHITE);
			pnlCurrent50.add(getLblCurrent50());
			pnlCurrent50.add(getTxtCurrent50());
		}
		return pnlCurrent50;
	}

	private JLabel getLblCurrent50() {
		if (lblCurrent50 == null) {
			lblCurrent50 = new JLabel("Current 50 Chips:");
			lblCurrent50.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 14));
		}
		return lblCurrent50;
	}

	private JTextField getTxtCurrent50() {
		if (txtCurrent50 == null) {
			txtCurrent50 = new JTextField();
			txtCurrent50.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 14));
			txtCurrent50.setEditable(false);
			txtCurrent50.setColumns(10);
			txtCurrent50.setBackground(Color.WHITE);
		}
		return txtCurrent50;
	}

	private JPanel getPnlCurrent100Chips() {
		if (pnlCurrent100Chips == null) {
			pnlCurrent100Chips = new JPanel();
			pnlCurrent100Chips.setBackground(Color.WHITE);
			pnlCurrent100Chips.add(getLblCurrent100());
			pnlCurrent100Chips.add(getTxtCurrent100());
		}
		return pnlCurrent100Chips;
	}

	private JLabel getLblCurrent100() {
		if (lblCurrent100 == null) {
			lblCurrent100 = new JLabel("Current 100 Chips:");
			lblCurrent100.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 14));
		}
		return lblCurrent100;
	}

	private JTextField getTxtCurrent100() {
		if (txtCurrent100 == null) {
			txtCurrent100 = new JTextField();
			txtCurrent100.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 14));
			txtCurrent100.setEditable(false);
			txtCurrent100.setColumns(10);
			txtCurrent100.setBackground(Color.WHITE);
		}
		return txtCurrent100;
	}

	private void openShopWindow() {
		shopWindow = new ShopWindow(this);
		shopWindow.setModal(true);
		shopWindow.setLocationRelativeTo(this);
		shopWindow.setVisible(true);
	}

	public UserManagement getUserManagement() {
		return userManagement;
	}

	private JTabbedPane getTabbedPane() {
		if (tabbedPane == null) {
			tabbedPane = new JTabbedPane(JTabbedPane.TOP);
			tabbedPane.addTab("Make a bet", null, getPnlMainAction(), null);
//			tabbedPane.addTab("Your bets", null, getPnlYourBets(), null);
//			tabbedPane.addTab("Game results", null, getPnlResults(), null);
		}
		return tabbedPane;
	}

	private JPanel getPnlMainAction() {
		if (pnlMainAction == null) {
			pnlMainAction = new JPanel();
			pnlMainAction.setLayout(new GridLayout(2, 0, 0, 0));
			pnlMainAction.add(getPanel_2_6());
			pnlMainAction.add(getPnlPlaceBet());
		}
		return pnlMainAction;
	}

	private JPanel getPnlResults() {
		if (pnlResults == null) {
			pnlResults = new JPanel();
			pnlResults.setBackground(new Color(255, 255, 255));
			pnlResults.setLayout(new BorderLayout(10, 10));
			pnlResults.add(getImgRoulette1(), BorderLayout.EAST);
			pnlResults.add(getPnlWinner(), BorderLayout.NORTH);
			pnlResults.add(getBtnRedeemChips(), BorderLayout.SOUTH);
			pnlResults.add(getPanel_1(), BorderLayout.CENTER);
			pnlResults.add(getImgChips(), BorderLayout.WEST);
		}
		return pnlResults;
	}

	private JLabel getImgRoulette1() {
		if (imgRoulette1 == null) {
			imgRoulette1 = new JLabel("");
			imgRoulette1.setIcon(new ImageIcon(GameWindow.class.getResource("/img/CasinoRoulette2D.png")));
			imgRoulette1.setHorizontalAlignment(SwingConstants.CENTER);
		}
		return imgRoulette1;
	}

	private JPanel getPnlWinner() {
		if (pnlWinner == null) {
			pnlWinner = new JPanel();
			pnlWinner.setBackground(new Color(255, 255, 255));
			pnlWinner.add(getLblWinner());
			pnlWinner.add(getTxtWinner());
		}
		return pnlWinner;
	}

	private JLabel getLblWinner() {
		if (lblWinner == null) {
			lblWinner = new JLabel("The winner number is: ");
			lblWinner.setFont(new Font("Segoe UI Semibold", Font.BOLD, 19));
		}
		return lblWinner;
	}

	private JTextField getTxtWinner() {
		if (txtWinner == null) {
			txtWinner = new JTextField();
			txtWinner.setBackground(new Color(255, 255, 255));
			txtWinner.setEditable(false);
			txtWinner.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 14));
			txtWinner.setColumns(10);
		}
		return txtWinner;
	}

	private JTextField getTxtBetsResults() {
		if (txtBetsResults == null) {
			txtBetsResults = new JTextField();
			txtBetsResults.setHorizontalAlignment(SwingConstants.CENTER);
			txtBetsResults.setBackground(new Color(255, 255, 255));
			txtBetsResults.setEditable(false);
			txtBetsResults.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 14));
			txtBetsResults.setColumns(10);
		}
		return txtBetsResults;
	}

	private JLabel getImgChips() {
		if (imgChips == null) {
			imgChips = new JLabel("");
			imgChips.setIcon(new ImageIcon(GameWindow.class.getResource("/img/Chips.png")));
			imgChips.setHorizontalAlignment(SwingConstants.CENTER);
		}
		return imgChips;
	}

	private JScrollPane getScpWiningBets() {
		if (scpWiningBets == null) {
			scpWiningBets = new JScrollPane();
			scpWiningBets.setViewportView(getTxtBetsResults());
		}
		return scpWiningBets;
	}

	private JPanel getPanel_1() {
		if (pnlInfoWin == null) {
			pnlInfoWin = new JPanel();
			pnlInfoWin.setBackground(new Color(255, 255, 255));
			pnlInfoWin.setLayout(new BorderLayout(0, 0));
			pnlInfoWin.add(getScpWiningBets());
		}
		return pnlInfoWin;
	}

	private JButton getBtnCancelAllBets() {
		if (btnCancelAllBets == null) {
			btnCancelAllBets = new JButton("Cancel bets");
			btnCancelAllBets.setMnemonic('t');
			btnCancelAllBets.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					int yesNo = JOptionPane.showConfirmDialog(null,
							"Are you sure you want to cancel all your bets made in this game?");
					if (yesNo == JOptionPane.YES_OPTION) {
						cancelAllBetsAction();
					}

				}
			});
			btnCancelAllBets.setForeground(Color.WHITE);
			btnCancelAllBets.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 14));
			btnCancelAllBets.setBackground(Color.RED);
		}
		return btnCancelAllBets;
	}

	private JButton getBtnPlayAgain() {
		if (btnPlayAgain == null) {
			btnPlayAgain = new JButton("Play Again");
			btnPlayAgain.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					resetGame();
				}
			});
			btnPlayAgain.setMnemonic('P');
			btnPlayAgain.setForeground(Color.WHITE);
			btnPlayAgain.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 14));
			btnPlayAgain.setEnabled(false);
			btnPlayAgain.setBackground(new Color(0, 128, 0));
			btnPlayAgain.setActionCommand("Cancel");
		}
		return btnPlayAgain;
	}

	private JButton getBtnRedeemChips() {
		if (btnRedeemChips == null) {
			btnRedeemChips = new JButton("Redeem chips ");
			btnRedeemChips.setMnemonic('R');
			btnRedeemChips.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					redeemChipsAction();
				}
			});
			btnRedeemChips.setForeground(new Color(255, 255, 255));
			btnRedeemChips.setBackground(new Color(0, 0, 139));
			btnRedeemChips.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 14));
		}
		return btnRedeemChips;
	}

	private JButton getBtnHelp() {
		if (btnHelp == null) {
			btnHelp = new JButton("");
			btnHelp.setBorder(null);
			btnHelp.setIcon(new ImageIcon(GameWindow.class.getResource("/img/btnhelp.png")));
		}
		return btnHelp;
	}

	// Auxiliary methods

	public Game getGame() {
		return game;
	}

	public WelcomeWindow getParent() {
		return parent;
	}

	private void updateNumberOfChips() {
		txtCurrent5.setText(Integer.toString(currentPlayer.getChipsByValue(5)));
		txtCurrent10.setText(Integer.toString(currentPlayer.getChipsByValue(10)));
		txtCurrent20.setText(Integer.toString(currentPlayer.getChipsByValue(20)));
		txtCurrent50.setText(Integer.toString(currentPlayer.getChipsByValue(50)));
		txtCurrent100.setText(Integer.toString(currentPlayer.getChipsByValue(100)));
	}

	protected void updateStatus() {
		updateNumberOfChips();
		if (userManagement.getPlayer().getChipsTotalValue() == 0) {
			btnPlayAgain.setEnabled(false);
		} else {
			btnPlayAgain.setEnabled(true);
		}
	}

	protected void updateBetModel() {
		betModel.clear();
		for (Bet bet : game.getBets()) {
			betModel.addElement(bet);
		}
	}

	private void clearRadioButtons() {
		boardButtonGroup.clearSelection();
		chipSelectorGroup.clearSelection();
	}

	private void resetTxtChips() {
		game.resetBets(userManagement.getPlayer());
		updateStatus();
	}

	private void resetGame() {
		this.game = new Game();
		updateStatus();
		clearRadioButtons();

		tabbedPane.remove(pnlYourBets);
		tabbedPane.remove(pnlResults);

		pnlButtons.add(btnPlay);
		pnlButtons.remove(btnPlayAgain);

		btnPlay.setEnabled(false);
		btnBack.setEnabled(true);
		btnBet.setEnabled(true);
		btnCancel.setEnabled(true);
		btnCancelAllBets.setEnabled(true);
	}

	// Main methods

	private void logOffOperation() {
		userManagement.logOff(userManagement.getPlayer());
		game.resetBets(userManagement.getPlayer());
		JOptionPane.showMessageDialog(null, "Thanks for playing " + userManagement.getPlayer().getUsername() + " !");
	}

	private void playAction() {
		NumberCell winner = game.getWinnerCell();
		game.computeEndOfGame(winner);
		tabbedPane.addTab("Game results", null, getPnlResults(), null);
		txtWinner.setText(Integer.toString(winner.getNumber()));
		pnlButtons.remove(btnPlay);
		pnlButtons.add(btnPlayAgain);
		btnPlayAgain.setEnabled(false);
		btnShop.setEnabled(false);
		btnLogOff.setEnabled(false);
		btnBack.setEnabled(false);
		btnBet.setEnabled(false);
		btnCancel.setEnabled(false);
		btnCancelAllBets.setEnabled(false);
		btnRedeemChips.setEnabled(true);
		String winnerBetsText = game.getWinnerBetsString();
		if (winnerBetsText.isEmpty()) {
			txtBetsResults.setText("None of your bets won any chip! Sorry!");
		} else {
			txtBetsResults.setText("Your winning bets:" + System.lineSeparator() + game.getWinnerBetsString());
		}
		tabbedPane.setSelectedComponent(pnlResults);
	}

	private void backOperation() {
		game.resetBets(userManagement.getPlayer());
		getParent().updateBalanceAndChips();
	}

	private void betAction() {
		try {
			String selectedCell = boardButtonGroup.getSelection().getActionCommand();
			String selectedChip = chipSelectorGroup.getSelection().getActionCommand();
			if (game.alreadyBetCell(selectedCell)) {
				JOptionPane.showMessageDialog(null, "You can't bet on a cell where you've already bet!");
			} else if (currentPlayer.getChipsByValue(Integer.parseInt(selectedChip)) == 0) {
				JOptionPane.showMessageDialog(null, "You have no chips of that type to bet!");
			} else {
				game.addBet(game.searchInBoard(selectedCell), new Chip(Integer.parseInt(selectedChip)));
				tabbedPane.addTab("Your bets", null, getPnlYourBets(), null);
				currentPlayer.removeChip(Integer.parseInt(selectedChip));
				btnPlay.setEnabled(true);
				updateNumberOfChips();
				updateBetModel();
				JOptionPane.showMessageDialog(null,
						"You've succesfully bet on cell " + selectedCell + " a total of " + selectedChip);
			}
		} catch (NullPointerException ex) {
			JOptionPane.showMessageDialog(null, "Please, pick a chip and a cell");
		}
	}

	private void cancelAllBetsAction() {
		clearRadioButtons();
		resetTxtChips();
		updateBetModel();
		tabbedPane.remove(pnlYourBets);
	}

	private void redeemChipsAction() {
		game.redeemChips(userManagement.getPlayer());
		btnRedeemChips.setEnabled(false);
		btnBack.setEnabled(true);
		btnShop.setEnabled(true);
		btnLogOff.setEnabled(true);
		btnPlayAgain.setEnabled(true);
		updateStatus();
		int totalPrize = game.calcTotalPrize();
		if (totalPrize != 0) {
			JOptionPane.showMessageDialog(null,
					"You have gained a total of " + totalPrize + " chips. Congratulations!");
		} else {
			JOptionPane.showMessageDialog(null,
					"Unfortunately, you've gained no chips, but we encourage you to try again!");
		}
	}
}
