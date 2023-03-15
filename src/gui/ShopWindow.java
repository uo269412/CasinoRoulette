package gui;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.SystemColor;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.net.URL;

import javax.help.HelpBroker;
import javax.help.HelpSet;
import javax.swing.ButtonGroup;
import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.SpinnerNumberModel;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import logic.Drink;
import logic.Player;
import logic.Shop;
import logic.UserManagement;
import javax.swing.UIManager;

public class ShopWindow extends JDialog {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel pnlHeader;
	private JButton btnLogOff;
	private JPanel pnlShop;
	private JPanel pnlBalance;
	private JPanel pnlChips;
	private JPanel pnlDrinks;
	private JPanel pnlBalanceChanger;
	private JPanel pnlCurrentBalance;
	private JLabel imgIcon1;
	private JLabel lblCurrentBalance;
	private JTextField txtCurrentBalance;
	private JPanel pnlMoneyAdd;
	private JLabel imgIcon2;
	private JLabel lblMoneyToAdd;
	private JSpinner spnMoneyAdder;
	private JButton btnAddMoney;
	private JPanel pnlCurrentChips;
	private JLabel imgCurrentChip5;
	private JTextField txtCurrentChip5;
	private JLabel imgCurrentChip10;
	private JTextField txtCurrentChip10;
	private JLabel imgCurrentChip20;
	private JTextField txtCurrentChip20;
	private JLabel imgCurrentChip50;
	private JTextField txtCurrentChip50;
	private JLabel imgCurrentChip100;
	private JTextField txtCurrentChip100;
	private JPanel pnlBuyChipsByType;
	private JPanel pnlBuy5;
	private JPanel pnlBuy10;
	private JPanel pnlBuy20;
	private JPanel pnlBuy50;
	private JPanel pnlBuy100;
	private JPanel pnlBuy;
	private JButton btnBuyChips;
	private JPanel pnlBuyChips;
	private JLabel lblPriceChips;
	private JTextField txtPriceChips;
	private JSpinner spnBuy5;
	private JSpinner spnBuy10;
	private JSpinner spnBuy20;
	private JSpinner spnBuy50;
	private JSpinner spnBuy100;
	private JLabel imgBuy5;
	private JLabel imgBuy10;
	private JLabel imgBuy20;
	private JLabel imgBuy50;
	private JLabel imgBuy100;
	private JLabel imgDrink;
	private JPanel pnlAllDrinkInfo;
	private JPanel pnlDrinkSelection;
	private JPanel pnlAlcoholic;
	private JRadioButton rbtnAlcoholic;
	private JRadioButton rbtnNonAlcoholic;
	private JLabel lblDrinkToBuy;
	private JComboBox<Drink> cmbDrinks;
	private JCheckBox cboxIce;
	private JCheckBox cboxWarm;
	private JButton btnAddToOrder;
	private JPanel pnlConfirmOrder;
	private JButton btnConfirmOrder;
	private JPanel pnlOrder;
	private JScrollPane scpOrder;
	private JList<Drink> listOrder;
	private JTextField txtYourOrder;
	private JPanel pnlButtons;
	private JButton btnBack;
	private JPanel pnlIceWarm;
	private JButton btnRemoveFromOrder;
	private JPanel pnlDrinkChooser;
	private JPanel pnlButtonsDrinks;
	private JPanel pnlMoneyUpdater;
	private JTabbedPane tabbedPane;
	private JLabel imgMoney;
	private JLabel lblPriceDrinks;
	private JPanel pnlPriceOrder;
	private JTextField txtPriceOrder;
	private JPanel pnlReturnChips;
	private JPanel pnlReturn5;
	private JSpinner spnReturn5;
	private JLabel imgReturn5;
	private JPanel pnlReturn10;
	private JSpinner spnReturn10;
	private JLabel imgReturn10;
	private JPanel pnlReturn20;
	private JPanel pnlReturn50;
	private JPanel pnlReturn100;
	private JPanel pnlReturn;
	private JButton btnExchange;
	private JPanel pnlReturnLabels;
	private JLabel lblBalanceToBeReturned;
	private JTextField txtReturnBalance;
	private JLabel imgReturn20;
	private JLabel imgReturn50;
	private JLabel imgReturn100;
	private JSpinner spnReturn20;
	private JSpinner spnReturn50;
	private JSpinner spnReturn100;
	private JPanel pnlChipsBuyReturn;
	private JButton btnHelp;
	private JButton btnCancelOrder;

	private final ButtonGroup alcoholicButtonGroup = new ButtonGroup();
	private DefaultListModel<Drink> drinkModel = null;

	// Windows
	private JDialog parent = null;

	// Logic
	private Shop shop = new Shop();
	private Player buyer = null;
	private UserManagement userManagement = null;

	// Listeners
	private CalcTotalChips ctc = new CalcTotalChips();
	private CalcReturningChips crc = new CalcReturningChips();
	private JPanel pnlReturnMoneyToBank;
	private JButton btnRetireMoneyToAccount;

	/**
	 * Create the dialog.
	 */
	protected ShopWindow(JDialog dialog) {
		parent = dialog;
		if (dialog instanceof WelcomeWindow) {
			buyer = ((WelcomeWindow) parent).getUserManagement().getPlayer();
			userManagement = ((WelcomeWindow) parent).getUserManagement();
		} else if (dialog instanceof GameWindow) {
			buyer = ((GameWindow) parent).getParent().getUserManagement().getPlayer();
			userManagement = ((GameWindow) parent).getUserManagement();
		}

		getContentPane().setBackground(Color.WHITE);
		setIconImage(Toolkit.getDefaultToolkit().getImage(ShopWindow.class.getResource("/img/DiceIcon.png")));
		setTitle("Casino Roulette: Shop");
		setBounds(100, 100, 806, 619);
		getContentPane().setLayout(new BorderLayout());
		getContentPane().add(getPnlHeader(), BorderLayout.NORTH);
		getContentPane().add(getPnlShop(), BorderLayout.CENTER);
		getContentPane().add(getPnlButtons(), BorderLayout.SOUTH);

		loadHelp();
		updateStatus();

		// Resize drinks image

		this.addComponentListener(new ComponentAdapter() {
			@Override
			public void componentResized(ComponentEvent e) {
				showPicture();
				showPictureBalance();
			}
		});

		this.addWindowListener(new WindowAdapter() {
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

		hb.enableHelpKey(getRootPane(), "shop", hs);
		hb.enableHelpOnButton(btnHelp, "shop", hs);
	}

	private JPanel getPnlHeader() {
		if (pnlHeader == null) {
			pnlHeader = new JPanel();
			pnlHeader.setBackground(new Color(178, 34, 34));
			pnlHeader.setLayout(new FlowLayout(FlowLayout.TRAILING, 0, 0));
			pnlHeader.add(getBtnLogOff());
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
					if (parent instanceof GameWindow) {
						((GameWindow) parent).dispose();
					} else {
						((WelcomeWindow) parent).dispose();
					}
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

	private JPanel getPnlShop() {
		if (pnlShop == null) {
			pnlShop = new JPanel();
			pnlShop.setBounds(new Rectangle(20, 20, 20, 20));
			pnlShop.setBackground(Color.WHITE);
			pnlShop.setLayout(new CardLayout(0, 0));
			pnlShop.add(getTabbedPane(), "name_118274925953400");
		}
		return pnlShop;
	}

	private JPanel getPnlBalance() {
		if (pnlBalance == null) {
			pnlBalance = new JPanel();
			pnlBalance.setBackground(Color.WHITE);
			pnlBalance.setLayout(new BorderLayout(0, 0));
			pnlBalance.add(getPnlBalanceChanger(), BorderLayout.CENTER);
			pnlBalance.add(getImgMoney(), BorderLayout.EAST);
		}
		return pnlBalance;
	}

	private JPanel getPnlChips() {
		if (pnlChips == null) {
			pnlChips = new JPanel();
			pnlChips.setBackground(Color.WHITE);
			pnlChips.setLayout(new BorderLayout(0, 5));
			pnlChips.add(getPnlCurrentChips(), BorderLayout.NORTH);
			pnlChips.add(getPnlChipsBuyReturn(), BorderLayout.CENTER);
		}
		return pnlChips;
	}

	private JPanel getPnlDrinks() {
		if (pnlDrinks == null) {
			pnlDrinks = new JPanel();
			pnlDrinks.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 16));
			pnlDrinks.setBackground(Color.WHITE);
			pnlDrinks.setLayout(new BorderLayout(5, 5));
			pnlDrinks.add(getImgDrink(), BorderLayout.EAST);
			pnlDrinks.add(getPnAllDrinkInfo());
		}
		return pnlDrinks;
	}

	private JPanel getPnlBalanceChanger() {
		if (pnlBalanceChanger == null) {
			pnlBalanceChanger = new JPanel();
			pnlBalanceChanger.setLayout(new GridLayout(3, 0, 0, 0));
			pnlBalanceChanger.add(getPnlCurrentBalance());
			pnlBalanceChanger.add(getPnlMoneyAdd());
			pnlBalanceChanger.add(getPnlReturnMoneyToBank());
		}
		return pnlBalanceChanger;
	}

	private JPanel getPnlCurrentBalance() {
		if (pnlCurrentBalance == null) {
			pnlCurrentBalance = new JPanel();
			FlowLayout flowLayout = (FlowLayout) pnlCurrentBalance.getLayout();
			flowLayout.setHgap(10);
			flowLayout.setAlignment(FlowLayout.LEFT);
			pnlCurrentBalance.setBackground(Color.WHITE);
			pnlCurrentBalance.add(getImgIcon1());
			pnlCurrentBalance.add(getLblCurrentBalance());
			pnlCurrentBalance.add(getTxtCurrentBalance());
		}
		return pnlCurrentBalance;
	}

	private JLabel getImgIcon1() {
		if (imgIcon1 == null) {
			imgIcon1 = new JLabel("");
			imgIcon1.setIcon(new ImageIcon(ShopWindow.class.getResource("/img/ChipIcon.png")));
		}
		return imgIcon1;
	}

	private JLabel getLblCurrentBalance() {
		if (lblCurrentBalance == null) {
			lblCurrentBalance = new JLabel("Current balance: ");
			lblCurrentBalance.setLabelFor(getTxtCurrentBalance());
			lblCurrentBalance.setVerticalAlignment(SwingConstants.BOTTOM);
			lblCurrentBalance.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 14));
		}
		return lblCurrentBalance;
	}

	private JTextField getTxtCurrentBalance() {
		if (txtCurrentBalance == null) {
			txtCurrentBalance = new JTextField();
			txtCurrentBalance.setBackground(Color.WHITE);
			txtCurrentBalance.setEditable(false);
			txtCurrentBalance.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 14));
//			updateBalance();
			txtCurrentBalance.setColumns(10);
		}
		return txtCurrentBalance;
	}

	private JPanel getPnlMoneyAdd() {
		if (pnlMoneyAdd == null) {
			pnlMoneyAdd = new JPanel();
			FlowLayout fl_pnlMoneyAdd = (FlowLayout) pnlMoneyAdd.getLayout();
			fl_pnlMoneyAdd.setHgap(10);
			fl_pnlMoneyAdd.setAlignment(FlowLayout.LEFT);
			pnlMoneyAdd.setBackground(Color.WHITE);
			pnlMoneyAdd.add(getImgIcon2());
			pnlMoneyAdd.add(getLblMoneyToAdd());
			pnlMoneyAdd.add(getPnlMoneyUpdater());
		}
		return pnlMoneyAdd;
	}

	private JLabel getImgIcon2() {
		if (imgIcon2 == null) {
			imgIcon2 = new JLabel("");
			imgIcon2.setIcon(new ImageIcon(ShopWindow.class.getResource("/img/ChipIcon.png")));
		}
		return imgIcon2;
	}

	private JLabel getLblMoneyToAdd() {
		if (lblMoneyToAdd == null) {
			lblMoneyToAdd = new JLabel("Money to add:");
			lblMoneyToAdd.setVerticalAlignment(SwingConstants.BOTTOM);
			lblMoneyToAdd.setHorizontalAlignment(SwingConstants.LEFT);
			lblMoneyToAdd.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 14));
			lblMoneyToAdd.setDisplayedMnemonic('M');
		}
		return lblMoneyToAdd;
	}

	private JSpinner getSpnMoneyAdder() {
		if (spnMoneyAdder == null) {
			spnMoneyAdder = new JSpinner();
			spnMoneyAdder.setModel(new SpinnerNumberModel(new Integer(1), new Integer(0), null, new Integer(1)));
			spnMoneyAdder.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 14));
		}
		return spnMoneyAdder;
	}

	private JButton getBtnAddMoney() {
		if (btnAddMoney == null) {
			btnAddMoney = new JButton("Add money");
			btnAddMoney.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					addMoneyOperation();
				}
			});
			btnAddMoney.setMnemonic('A');
			btnAddMoney.setForeground(Color.WHITE);
			btnAddMoney.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 14));
			btnAddMoney.setBackground(new Color(0, 128, 0));
		}
		return btnAddMoney;
	}

	private JPanel getPnlCurrentChips() {
		if (pnlCurrentChips == null) {
			pnlCurrentChips = new JPanel();
			pnlCurrentChips.setBorder(new TitledBorder(null, "Current chips you own", TitledBorder.LEADING,
					TitledBorder.TOP, null, null));
			pnlCurrentChips.setBackground(Color.WHITE);
			pnlCurrentChips.setLayout(new GridLayout(0, 10, 10, 10));
			pnlCurrentChips.add(getImgCurrentChip5());
			pnlCurrentChips.add(getTxtCurrentChip5());
			pnlCurrentChips.add(getImgCurrentChip10());
			pnlCurrentChips.add(getTxtCurrentChip10());
			pnlCurrentChips.add(getImgCurrentChip20());
			pnlCurrentChips.add(getTxtCurrentChip20());
			pnlCurrentChips.add(getImgCurrentChip50());
			pnlCurrentChips.add(getTxtCurrentChip50());
			pnlCurrentChips.add(getImgCurrentChip100());
			pnlCurrentChips.add(getTxtCurrentChip100());
		}
		return pnlCurrentChips;
	}

	private JLabel getImgCurrentChip5() {
		if (imgCurrentChip5 == null) {
			imgCurrentChip5 = new JLabel("");
			imgCurrentChip5.setIcon(new ImageIcon(ShopWindow.class.getResource("/img/Chip5Small.png")));
			imgCurrentChip5.setHorizontalAlignment(SwingConstants.CENTER);
		}
		return imgCurrentChip5;
	}

	private JTextField getTxtCurrentChip5() {
		if (txtCurrentChip5 == null) {
			txtCurrentChip5 = new JTextField();
			txtCurrentChip5.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 14));
			txtCurrentChip5.setEditable(false);
			txtCurrentChip5.setColumns(10);
			txtCurrentChip5.setBackground(Color.WHITE);
		}
		return txtCurrentChip5;
	}

	private JLabel getImgCurrentChip10() {
		if (imgCurrentChip10 == null) {
			imgCurrentChip10 = new JLabel("");
			imgCurrentChip10.setIcon(new ImageIcon(ShopWindow.class.getResource("/img/Chip10Small.png")));
			imgCurrentChip10.setHorizontalAlignment(SwingConstants.CENTER);
		}
		return imgCurrentChip10;
	}

	private JTextField getTxtCurrentChip10() {
		if (txtCurrentChip10 == null) {
			txtCurrentChip10 = new JTextField();
			txtCurrentChip10.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 14));
			txtCurrentChip10.setEditable(false);
			txtCurrentChip10.setColumns(10);
			txtCurrentChip10.setBackground(Color.WHITE);
		}
		return txtCurrentChip10;
	}

	private JLabel getImgCurrentChip20() {
		if (imgCurrentChip20 == null) {
			imgCurrentChip20 = new JLabel("");
			imgCurrentChip20.setIcon(new ImageIcon(ShopWindow.class.getResource("/img/Chip20Small.png")));
			imgCurrentChip20.setHorizontalAlignment(SwingConstants.CENTER);
		}
		return imgCurrentChip20;
	}

	private JTextField getTxtCurrentChip20() {
		if (txtCurrentChip20 == null) {
			txtCurrentChip20 = new JTextField();
			txtCurrentChip20.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 14));
			txtCurrentChip20.setEditable(false);
			txtCurrentChip20.setColumns(10);
			txtCurrentChip20.setBackground(Color.WHITE);
		}
		return txtCurrentChip20;
	}

	private JLabel getImgCurrentChip50() {
		if (imgCurrentChip50 == null) {
			imgCurrentChip50 = new JLabel("");
			imgCurrentChip50.setIcon(new ImageIcon(ShopWindow.class.getResource("/img/Chip50Small.png")));
			imgCurrentChip50.setHorizontalAlignment(SwingConstants.CENTER);
		}
		return imgCurrentChip50;
	}

	private JTextField getTxtCurrentChip50() {
		if (txtCurrentChip50 == null) {
			txtCurrentChip50 = new JTextField();
			txtCurrentChip50.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 14));
			txtCurrentChip50.setEditable(false);
			txtCurrentChip50.setColumns(10);
			txtCurrentChip50.setBackground(Color.WHITE);
		}
		return txtCurrentChip50;
	}

	private JLabel getImgCurrentChip100() {
		if (imgCurrentChip100 == null) {
			imgCurrentChip100 = new JLabel("");
			imgCurrentChip100.setIcon(new ImageIcon(ShopWindow.class.getResource("/img/Chip100Small.png")));
			imgCurrentChip100.setHorizontalAlignment(SwingConstants.CENTER);
		}
		return imgCurrentChip100;
	}

	private JTextField getTxtCurrentChip100() {
		if (txtCurrentChip100 == null) {
			txtCurrentChip100 = new JTextField();
			txtCurrentChip100.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 14));
			txtCurrentChip100.setEditable(false);
			txtCurrentChip100.setColumns(10);
			txtCurrentChip100.setBackground(Color.WHITE);
		}
		return txtCurrentChip100;
	}

	private JPanel getPnlBuyChipsByType() {
		if (pnlBuyChipsByType == null) {
			pnlBuyChipsByType = new JPanel();
			pnlBuyChipsByType
					.setBorder(new TitledBorder(null, "Buy more?", TitledBorder.LEADING, TitledBorder.TOP, null, null));
			pnlBuyChipsByType.setBackground(Color.WHITE);
			pnlBuyChipsByType.setLayout(new GridLayout(2, 3, 5, 5));
			pnlBuyChipsByType.add(getPnlBuy5());
			pnlBuyChipsByType.add(getPnlBuy10());
			pnlBuyChipsByType.add(getPnlBuy20());
			pnlBuyChipsByType.add(getPnlBuy50());
			pnlBuyChipsByType.add(getPnlBuy100());
			pnlBuyChipsByType.add(getPnlBuy());
		}
		return pnlBuyChipsByType;
	}

	private JPanel getPnlBuy5() {
		if (pnlBuy5 == null) {
			pnlBuy5 = new JPanel();
			pnlBuy5.setBorder(new LineBorder(new Color(153, 180, 209), 1, true));
			pnlBuy5.setBackground(Color.WHITE);
			pnlBuy5.setLayout(new BorderLayout(0, 0));
			pnlBuy5.add(getSpnBuy5(), BorderLayout.SOUTH);
			pnlBuy5.add(getImgBuy5(), BorderLayout.CENTER);
		}
		return pnlBuy5;
	}

	private JPanel getPnlBuy10() {
		if (pnlBuy10 == null) {
			pnlBuy10 = new JPanel();
			pnlBuy10.setBorder(new LineBorder(SystemColor.activeCaption, 1, true));
			pnlBuy10.setBackground(Color.WHITE);
			pnlBuy10.setLayout(new BorderLayout(0, 0));
			pnlBuy10.add(getSpnBuy10(), BorderLayout.SOUTH);
			pnlBuy10.add(getImgBuy10(), BorderLayout.CENTER);
		}
		return pnlBuy10;
	}

	private JPanel getPnlBuy20() {
		if (pnlBuy20 == null) {
			pnlBuy20 = new JPanel();
			pnlBuy20.setBorder(new LineBorder(SystemColor.activeCaption, 1, true));
			pnlBuy20.setBackground(Color.WHITE);
			pnlBuy20.setLayout(new BorderLayout(0, 0));
			pnlBuy20.add(getSpnBuy20(), BorderLayout.SOUTH);
			pnlBuy20.add(getImgBuy20(), BorderLayout.CENTER);
		}
		return pnlBuy20;
	}

	private JPanel getPnlBuy50() {
		if (pnlBuy50 == null) {
			pnlBuy50 = new JPanel();
			pnlBuy50.setBorder(new LineBorder(SystemColor.activeCaption, 1, true));
			pnlBuy50.setBackground(Color.WHITE);
			pnlBuy50.setLayout(new BorderLayout(0, 0));
			pnlBuy50.add(getSpnBuy50(), BorderLayout.SOUTH);
			pnlBuy50.add(getImgBuy50(), BorderLayout.CENTER);
		}
		return pnlBuy50;
	}

	private JPanel getPnlBuy100() {
		if (pnlBuy100 == null) {
			pnlBuy100 = new JPanel();
			pnlBuy100.setBorder(new LineBorder(SystemColor.activeCaption, 1, true));
			pnlBuy100.setBackground(Color.WHITE);
			pnlBuy100.setLayout(new BorderLayout(0, 0));
			pnlBuy100.add(getSpnBuy100(), BorderLayout.SOUTH);
			pnlBuy100.add(getImgBuy100(), BorderLayout.CENTER);
		}
		return pnlBuy100;
	}

	private JPanel getPnlBuy() {
		if (pnlBuy == null) {
			pnlBuy = new JPanel();
			pnlBuy.setBackground(Color.WHITE);
			pnlBuy.setLayout(new BorderLayout(5, 5));
			pnlBuy.add(getBtnBuyChips(), BorderLayout.EAST);
			pnlBuy.add(getPnlBuyChips(), BorderLayout.CENTER);
		}
		return pnlBuy;
	}

	private JButton getBtnBuyChips() {
		if (btnBuyChips == null) {
			btnBuyChips = new JButton("Buy chips");
			btnBuyChips.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					buyChipsOperation();
				}
			});
			btnBuyChips.setMnemonic('y');
			btnBuyChips.setForeground(Color.WHITE);
			btnBuyChips.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 14));
			btnBuyChips.setBackground(new Color(0, 128, 0));
		}
		return btnBuyChips;
	}

	protected void updateChips() {
		txtCurrentChip5.setText(Integer.toString(updateChips(5)));
		txtCurrentChip10.setText(Integer.toString(updateChips(10)));
		txtCurrentChip20.setText(Integer.toString(updateChips(20)));
		txtCurrentChip50.setText(Integer.toString(updateChips(50)));
		txtCurrentChip100.setText(Integer.toString(updateChips(100)));

	}

	private JPanel getPnlBuyChips() {
		if (pnlBuyChips == null) {
			pnlBuyChips = new JPanel();
			pnlBuyChips.setBackground(Color.WHITE);
			pnlBuyChips.setLayout(new GridLayout(2, 0, 0, 0));
			pnlBuyChips.add(getLblPriceChips());
			pnlBuyChips.add(getTxtPriceChips());
		}
		return pnlBuyChips;
	}

	private JLabel getLblPriceChips() {
		if (lblPriceChips == null) {
			lblPriceChips = new JLabel("Price of chips:");
			lblPriceChips.setHorizontalAlignment(SwingConstants.CENTER);
			lblPriceChips.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 14));
			lblPriceChips.setDisplayedMnemonic('C');
		}
		return lblPriceChips;
	}

	private JTextField getTxtPriceChips() {
		if (txtPriceChips == null) {
			txtPriceChips = new JTextField();
			txtPriceChips.setBackground(Color.WHITE);
			txtPriceChips.setEditable(false);
			txtPriceChips.setHorizontalAlignment(SwingConstants.TRAILING);
			txtPriceChips.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 14));
			txtPriceChips.setColumns(10);
		}
		return txtPriceChips;
	}

	private JSpinner getSpnBuy5() {
		if (spnBuy5 == null) {
			spnBuy5 = new JSpinner();
			spnBuy5.setModel(new SpinnerNumberModel(new Integer(0), new Integer(0), null, new Integer(1)));
			spnBuy5.addChangeListener(ctc);
			spnBuy5.setSize(new Dimension(100, 100));
			spnBuy5.setMinimumSize(new Dimension(100, 100));
			spnBuy5.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 14));
		}
		return spnBuy5;
	}

	private JSpinner getSpnBuy10() {
		if (spnBuy10 == null) {
			spnBuy10 = new JSpinner();
			spnBuy10.setModel(new SpinnerNumberModel(new Integer(0), new Integer(0), null, new Integer(1)));
			spnBuy10.addChangeListener(ctc);
			spnBuy10.setSize(new Dimension(100, 100));
			spnBuy10.setMinimumSize(new Dimension(100, 100));
			spnBuy10.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 14));
		}
		return spnBuy10;
	}

	private JSpinner getSpnBuy20() {
		if (spnBuy20 == null) {
			spnBuy20 = new JSpinner();
			spnBuy20.setModel(new SpinnerNumberModel(new Integer(0), new Integer(0), null, new Integer(1)));
			spnBuy20.addChangeListener(ctc);
			spnBuy20.setSize(new Dimension(100, 100));
			spnBuy20.setMinimumSize(new Dimension(100, 100));
			spnBuy20.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 14));
		}
		return spnBuy20;
	}

	private JSpinner getSpnBuy50() {
		if (spnBuy50 == null) {
			spnBuy50 = new JSpinner();
			spnBuy50.setModel(new SpinnerNumberModel(new Integer(0), new Integer(0), null, new Integer(1)));
			spnBuy50.addChangeListener(ctc);
			spnBuy50.setSize(new Dimension(100, 100));
			spnBuy50.setMinimumSize(new Dimension(100, 100));
			spnBuy50.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 14));
		}
		return spnBuy50;
	}

	private JSpinner getSpnBuy100() {
		if (spnBuy100 == null) {
			spnBuy100 = new JSpinner();
			spnBuy100.setModel(new SpinnerNumberModel(new Integer(0), new Integer(0), null, new Integer(1)));
			spnBuy100.addChangeListener(ctc);
			spnBuy100.setSize(new Dimension(100, 100));
			spnBuy100.setMinimumSize(new Dimension(100, 100));
			spnBuy100.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 14));
		}
		return spnBuy100;
	}

	private JLabel getImgBuy5() {
		if (imgBuy5 == null) {
			imgBuy5 = new JLabel("");
			imgBuy5.setIcon(new ImageIcon(ShopWindow.class.getResource("/img/Chip5Small.png")));
			imgBuy5.setHorizontalAlignment(SwingConstants.CENTER);
		}
		return imgBuy5;
	}

	private JLabel getImgBuy10() {
		if (imgBuy10 == null) {
			imgBuy10 = new JLabel("");
			imgBuy10.setIcon(new ImageIcon(ShopWindow.class.getResource("/img/Chip10Small.png")));
			imgBuy10.setHorizontalAlignment(SwingConstants.CENTER);
		}
		return imgBuy10;
	}

	private JLabel getImgBuy20() {
		if (imgBuy20 == null) {
			imgBuy20 = new JLabel("");
			imgBuy20.setIcon(new ImageIcon(ShopWindow.class.getResource("/img/Chip20Small.png")));
			imgBuy20.setHorizontalAlignment(SwingConstants.CENTER);
		}
		return imgBuy20;
	}

	private JLabel getImgBuy50() {
		if (imgBuy50 == null) {
			imgBuy50 = new JLabel("");
			imgBuy50.setIcon(new ImageIcon(ShopWindow.class.getResource("/img/Chip50Small.png")));
			imgBuy50.setHorizontalAlignment(SwingConstants.CENTER);
		}
		return imgBuy50;
	}

	private JLabel getImgBuy100() {
		if (imgBuy100 == null) {
			imgBuy100 = new JLabel("");
			imgBuy100.setIcon(new ImageIcon(ShopWindow.class.getResource("/img/Chip100Small.png")));
			imgBuy100.setHorizontalAlignment(SwingConstants.CENTER);
		}
		return imgBuy100;
	}

	private JLabel getImgDrink() {
		if (imgDrink == null) {
			imgDrink = new JLabel("");
			imgDrink.setHorizontalAlignment(SwingConstants.CENTER);
			imgDrink.setIcon(new ImageIcon(ShopWindow.class.getResource("/img/BE01.png")));
		}
		return imgDrink;
	}

	private JPanel getPnAllDrinkInfo() {
		if (pnlAllDrinkInfo == null) {
			pnlAllDrinkInfo = new JPanel();
			pnlAllDrinkInfo.setBackground(Color.WHITE);
			pnlAllDrinkInfo.setBorder(null);
			pnlAllDrinkInfo.setLayout(new BorderLayout(0, 0));
			pnlAllDrinkInfo.add(getPnlDrinkSelection(), BorderLayout.EAST);
			pnlAllDrinkInfo.add(getPnlConfirmOrder(), BorderLayout.SOUTH);
			pnlAllDrinkInfo.add(getPnlOrder(), BorderLayout.CENTER);
		}
		return pnlAllDrinkInfo;
	}

	private JPanel getPnlDrinkSelection() {
		if (pnlDrinkSelection == null) {
			pnlDrinkSelection = new JPanel();
			pnlDrinkSelection.setBackground(Color.WHITE);
			pnlDrinkSelection.setLayout(new GridLayout(4, 1, 0, 0));
			pnlDrinkSelection.add(getPnlAlcoholic());
			pnlDrinkSelection.add(getPnlDrinkChooser());
			pnlDrinkSelection.add(getPnlIceWarm());
			pnlDrinkSelection.add(getPnlButtonsDrinks());
		}
		return pnlDrinkSelection;
	}

	private JPanel getPnlAlcoholic() {
		if (pnlAlcoholic == null) {
			pnlAlcoholic = new JPanel();
			pnlAlcoholic.setBackground(Color.WHITE);
			pnlAlcoholic.add(getRbtnAlcoholic());
			pnlAlcoholic.add(getRbtnNonAlcoholic());
		}
		return pnlAlcoholic;
	}

	private JRadioButton getRbtnAlcoholic() {
		if (rbtnAlcoholic == null) {
			rbtnAlcoholic = new JRadioButton("Alcoholic");
			alcoholicButtonGroup.add(rbtnAlcoholic);
			rbtnAlcoholic.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					cmbDrinks.setModel(new DefaultComboBoxModel<Drink>(shop.getDrinksByType(false)));
					showPicture();
				}
			});
			rbtnAlcoholic.setMnemonic('H');
			rbtnAlcoholic.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 14));
			rbtnAlcoholic.setBackground(Color.WHITE);
		}
		return rbtnAlcoholic;
	}

	private JRadioButton getRbtnNonAlcoholic() {
		if (rbtnNonAlcoholic == null) {
			rbtnNonAlcoholic = new JRadioButton("Non-alcoholic");
			alcoholicButtonGroup.add(rbtnNonAlcoholic);
			rbtnNonAlcoholic.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					cmbDrinks.setModel(new DefaultComboBoxModel<Drink>(shop.getDrinksByType(true)));
					showPicture();
				}
			});
			rbtnNonAlcoholic.setMnemonic('N');
			rbtnNonAlcoholic.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 14));
			rbtnNonAlcoholic.setBackground(Color.WHITE);
		}
		return rbtnNonAlcoholic;
	}

	private JLabel getLblDrinkToBuy() {
		if (lblDrinkToBuy == null) {
			lblDrinkToBuy = new JLabel("Drink to buy: ");
			lblDrinkToBuy.setLabelFor(getCmbDrinks());
			lblDrinkToBuy.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 14));
			lblDrinkToBuy.setDisplayedMnemonic('K');
		}
		return lblDrinkToBuy;
	}

	private JComboBox<Drink> getCmbDrinks() {
		if (cmbDrinks == null) {
			cmbDrinks = new JComboBox<Drink>();
			cmbDrinks.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					showPicture();
				}
			});
			cmbDrinks.setModel(new DefaultComboBoxModel<Drink>(shop.getDrinks()));
			cmbDrinks.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 14));
		}
		return cmbDrinks;
	}

	private JCheckBox getCboxIce() {
		if (cboxIce == null) {
			cboxIce = new JCheckBox("Ice");
			cboxIce.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 14));
			cboxIce.setBackground(Color.WHITE);
		}
		return cboxIce;
	}

	private JCheckBox getCboxWarm() {
		if (cboxWarm == null) {
			cboxWarm = new JCheckBox("Warm");
			cboxWarm.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 14));
			cboxWarm.setBackground(Color.WHITE);
		}
		return cboxWarm;
	}

	private JButton getBtnAddToOrder() {
		if (btnAddToOrder == null) {
			btnAddToOrder = new JButton("Add to order");
			btnAddToOrder.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					addDrinkToOrderOperation();
				}
			});
			btnAddToOrder.setMnemonic('D');
			btnAddToOrder.setForeground(Color.WHITE);
			btnAddToOrder.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 14));
			btnAddToOrder.setBackground(new Color(0, 128, 0));
		}
		return btnAddToOrder;
	}

	private JPanel getPnlConfirmOrder() {
		if (pnlConfirmOrder == null) {
			pnlConfirmOrder = new JPanel();
			pnlConfirmOrder.setBackground(Color.WHITE);
			pnlConfirmOrder.setLayout(new GridLayout(0, 2, 0, 5));
			pnlConfirmOrder.add(getBtnCancelOrder());
			pnlConfirmOrder.add(getBtnConfirmOrder());
		}
		return pnlConfirmOrder;
	}

	private JButton getBtnConfirmOrder() {
		if (btnConfirmOrder == null) {
			btnConfirmOrder = new JButton("Confirm order");
			btnConfirmOrder.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					confirmOrderOperation();

				}
			});
			btnConfirmOrder.setMnemonic('m');
			btnConfirmOrder.setForeground(Color.WHITE);
			btnConfirmOrder.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 14));
			btnConfirmOrder.setBackground(new Color(0, 128, 0));
			btnConfirmOrder.setAlignmentX(0.5f);
		}
		return btnConfirmOrder;
	}

	private JPanel getPnlOrder() {
		if (pnlOrder == null) {
			pnlOrder = new JPanel();
			pnlOrder.setBorder(new LineBorder(SystemColor.activeCaption, 1, true));
			pnlOrder.setBackground(Color.WHITE);
			pnlOrder.setLayout(new BorderLayout(0, 0));
			pnlOrder.add(getScpOrder(), BorderLayout.CENTER);
			pnlOrder.add(getPnlPriceOrder(), BorderLayout.SOUTH);
		}
		return pnlOrder;
	}

	private JScrollPane getScpOrder() {
		if (scpOrder == null) {
			scpOrder = new JScrollPane();
			scpOrder.setViewportView(getListOrder());
			scpOrder.setColumnHeaderView(getTxtYourOrder());
		}
		return scpOrder;
	}

	private JList<Drink> getListOrder() {
		if (listOrder == null) {
			drinkModel = new DefaultListModel<Drink>();
			listOrder = new JList<Drink>(drinkModel);
			listOrder.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 14));
			listOrder.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		}
		return listOrder;
	}

	private JTextField getTxtYourOrder() {
		if (txtYourOrder == null) {
			txtYourOrder = new JTextField();
			txtYourOrder.setText("    * Your order:");
			txtYourOrder.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 14));
			txtYourOrder.setEditable(false);
			txtYourOrder.setColumns(10);
			txtYourOrder.setBackground(Color.WHITE);
		}
		return txtYourOrder;
	}

	private JPanel getPnlButtons() {
		if (pnlButtons == null) {
			pnlButtons = new JPanel();
			pnlButtons.setBackground(Color.WHITE);
			pnlButtons.setLayout(new FlowLayout(FlowLayout.RIGHT));
			pnlButtons.add(getBtnBack());
		}
		return pnlButtons;
	}

	private JButton getBtnBack() {
		if (btnBack == null) {
			btnBack = new JButton("Back");
			btnBack.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					backOperation();
					dispose();
				}
			});
			btnBack.setMnemonic('B');
			btnBack.setForeground(Color.WHITE);
			btnBack.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 14));
			btnBack.setBackground(new Color(100, 149, 237));
			btnBack.setActionCommand("Cancel");
		}
		return btnBack;
	}

	private JPanel getPnlIceWarm() {
		if (pnlIceWarm == null) {
			pnlIceWarm = new JPanel();
			pnlIceWarm.setBackground(Color.WHITE);
			pnlIceWarm.add(getCboxIce());
			pnlIceWarm.add(getCboxWarm());
		}
		return pnlIceWarm;
	}

	private JButton getBtnRemoveFromOrder() {
		if (btnRemoveFromOrder == null) {
			btnRemoveFromOrder = new JButton("Remove from order");
			btnRemoveFromOrder.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					int yesNo = JOptionPane.showConfirmDialog(null,
							"Are you sure you want to remove this drink from your order?");
					if (yesNo == JOptionPane.YES_OPTION) {
						removeFromOrderOperation();
					}
				}
			});
			btnRemoveFromOrder.setMnemonic('R');
			btnRemoveFromOrder.setForeground(Color.WHITE);
			btnRemoveFromOrder.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 14));
			btnRemoveFromOrder.setBackground(Color.RED);
		}
		return btnRemoveFromOrder;
	}

	private JPanel getPnlDrinkChooser() {
		if (pnlDrinkChooser == null) {
			pnlDrinkChooser = new JPanel();
			pnlDrinkChooser.setBackground(Color.WHITE);
			pnlDrinkChooser.add(getLblDrinkToBuy());
			pnlDrinkChooser.add(getCmbDrinks());
		}
		return pnlDrinkChooser;
	}

	private JPanel getPnlButtonsDrinks() {
		if (pnlButtonsDrinks == null) {
			pnlButtonsDrinks = new JPanel();
			pnlButtonsDrinks.setBackground(Color.WHITE);
			pnlButtonsDrinks.add(getBtnRemoveFromOrder());
			pnlButtonsDrinks.add(getBtnAddToOrder());
		}
		return pnlButtonsDrinks;
	}

	private JPanel getPnlMoneyUpdater() {
		if (pnlMoneyUpdater == null) {
			pnlMoneyUpdater = new JPanel();
			pnlMoneyUpdater.setBackground(Color.WHITE);
			pnlMoneyUpdater.setLayout(new GridLayout(0, 2, 20, 0));
			pnlMoneyUpdater.add(getSpnMoneyAdder());
			pnlMoneyUpdater.add(getBtnAddMoney());
		}
		return pnlMoneyUpdater;
	}

	private JTabbedPane getTabbedPane() {
		if (tabbedPane == null) {
			tabbedPane = new JTabbedPane(JTabbedPane.TOP);
			tabbedPane.addTab("Balance", null, getPnlBalance(), null);
			tabbedPane.addTab("Chips", null, getPnlChips(), null);
			tabbedPane.addTab("Drinks", null, getPnlDrinks(), null);
		}
		return tabbedPane;
	}

	private JLabel getImgMoney() {
		if (imgMoney == null) {
			imgMoney = new JLabel("");
			imgMoney.setIcon(new ImageIcon(ShopWindow.class.getResource("/img/BalanceImage.png")));
		}
		return imgMoney;
	}

	private JLabel getLblPriceDrinks() {
		if (lblPriceDrinks == null) {
			lblPriceDrinks = new JLabel("Price:");
			lblPriceDrinks.setBackground(Color.WHITE);
			lblPriceDrinks.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 14));
		}
		return lblPriceDrinks;
	}

	private JPanel getPnlPriceOrder() {
		if (pnlPriceOrder == null) {
			pnlPriceOrder = new JPanel();
			pnlPriceOrder.setBackground(Color.WHITE);
			pnlPriceOrder.add(getLblPriceDrinks());
			pnlPriceOrder.add(getTxtPriceOrder());
		}
		return pnlPriceOrder;
	}

	private JTextField getTxtPriceOrder() {
		if (txtPriceOrder == null) {
			txtPriceOrder = new JTextField();
			txtPriceOrder.setForeground(new Color(34, 139, 34));
			txtPriceOrder.setBackground(Color.WHITE);
			txtPriceOrder.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 14));
			txtPriceOrder.setEditable(false);
			txtPriceOrder.setColumns(10);
		}
		return txtPriceOrder;
	}

	private JPanel getPnlReturnChips() {
		if (pnlReturnChips == null) {
			pnlReturnChips = new JPanel();
			pnlReturnChips.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"),
					"Exchange chips for balance", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
			pnlReturnChips.setBackground(Color.WHITE);
			pnlReturnChips.setLayout(new GridLayout(2, 3, 5, 5));
			pnlReturnChips.add(getPnlReturn5());
			pnlReturnChips.add(getPnlReturn10());
			pnlReturnChips.add(getPnlReturn20());
			pnlReturnChips.add(getPnlReturn50());
			pnlReturnChips.add(getPnlReturn100());
			pnlReturnChips.add(getPnlReturn());
		}
		return pnlReturnChips;
	}

	private JPanel getPnlReturn5() {
		if (pnlReturn5 == null) {
			pnlReturn5 = new JPanel();
			pnlReturn5.setBorder(new LineBorder(new Color(153, 180, 209), 1, true));
			pnlReturn5.setBackground(Color.WHITE);
			pnlReturn5.setLayout(new BorderLayout(0, 0));
			pnlReturn5.add(getSpnReturn5(), BorderLayout.SOUTH);
			pnlReturn5.add(getImgReturn5(), BorderLayout.CENTER);
		}
		return pnlReturn5;
	}

	private JSpinner getSpnReturn5() {
		if (spnReturn5 == null) {
			spnReturn5 = new JSpinner();
			spnReturn5.addChangeListener(crc);
			spnReturn5.setSize(new Dimension(100, 100));
			spnReturn5.setMinimumSize(new Dimension(100, 100));
			spnReturn5.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 14));
		}
		return spnReturn5;
	}

	private JLabel getImgReturn5() {
		if (imgReturn5 == null) {
			imgReturn5 = new JLabel("");
			imgReturn5.setIcon(new ImageIcon(ShopWindow.class.getResource("/img/Chip5Small.png")));
			imgReturn5.setHorizontalAlignment(SwingConstants.CENTER);
		}
		return imgReturn5;
	}

	private JPanel getPnlReturn10() {
		if (pnlReturn10 == null) {
			pnlReturn10 = new JPanel();
			pnlReturn10.setBorder(new LineBorder(new Color(153, 180, 209), 1, true));
			pnlReturn10.setBackground(Color.WHITE);
			pnlReturn10.setLayout(new BorderLayout(0, 0));
			pnlReturn10.add(getSpnReturn10(), BorderLayout.SOUTH);
			pnlReturn10.add(getImgReturn10(), BorderLayout.CENTER);
		}
		return pnlReturn10;
	}

	private JSpinner getSpnReturn10() {
		if (spnReturn10 == null) {
			spnReturn10 = new JSpinner();
			spnReturn10.addChangeListener(crc);
			spnReturn10.setSize(new Dimension(100, 100));
			spnReturn10.setMinimumSize(new Dimension(100, 100));
			spnReturn10.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 14));
		}
		return spnReturn10;
	}

	private JLabel getImgReturn10() {
		if (imgReturn10 == null) {
			imgReturn10 = new JLabel("");
			imgReturn10.setIcon(new ImageIcon(ShopWindow.class.getResource("/img/Chip10Small.png")));
			imgReturn10.setHorizontalAlignment(SwingConstants.CENTER);
		}
		return imgReturn10;
	}

	private JPanel getPnlReturn20() {
		if (pnlReturn20 == null) {
			pnlReturn20 = new JPanel();
			pnlReturn20.setBorder(new LineBorder(new Color(153, 180, 209), 1, true));
			pnlReturn20.setBackground(Color.WHITE);
			pnlReturn20.setLayout(new BorderLayout(0, 0));
			pnlReturn20.add(getImgReturn20(), BorderLayout.CENTER);
			pnlReturn20.add(getSpnReturn20(), BorderLayout.SOUTH);
		}
		return pnlReturn20;
	}

	private JPanel getPnlReturn50() {
		if (pnlReturn50 == null) {
			pnlReturn50 = new JPanel();
			pnlReturn50.setBorder(new LineBorder(new Color(153, 180, 209), 1, true));
			pnlReturn50.setBackground(Color.WHITE);
			pnlReturn50.setLayout(new BorderLayout(0, 0));
			pnlReturn50.add(getImgReturn50(), BorderLayout.CENTER);
			pnlReturn50.add(getSpnReturn50(), BorderLayout.SOUTH);
		}
		return pnlReturn50;
	}

	private JPanel getPnlReturn100() {
		if (pnlReturn100 == null) {
			pnlReturn100 = new JPanel();
			pnlReturn100.setBorder(new LineBorder(new Color(153, 180, 209), 1, true));
			pnlReturn100.setBackground(Color.WHITE);
			pnlReturn100.setLayout(new BorderLayout(0, 0));
			pnlReturn100.add(getImgReturn100(), BorderLayout.CENTER);
			pnlReturn100.add(getSpnReturn100(), BorderLayout.SOUTH);
		}
		return pnlReturn100;
	}

	private JPanel getPnlReturn() {
		if (pnlReturn == null) {
			pnlReturn = new JPanel();
			pnlReturn.setBackground(Color.WHITE);
			pnlReturn.setLayout(new BorderLayout(5, 5));
			pnlReturn.add(getBtnExchange(), BorderLayout.EAST);
			pnlReturn.add(getPnlReturnLabels(), BorderLayout.CENTER);
		}
		return pnlReturn;
	}

	private JButton getBtnExchange() {
		if (btnExchange == null) {
			btnExchange = new JButton("Exchange");
			btnExchange.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					exchangeChipsOperation();
				}
			});
			btnExchange.setMnemonic('E');
			btnExchange.setForeground(Color.WHITE);
			btnExchange.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 14));
			btnExchange.setBackground(new Color(0, 128, 0));
		}
		return btnExchange;
	}

	private JPanel getPnlReturnLabels() {
		if (pnlReturnLabels == null) {
			pnlReturnLabels = new JPanel();
			pnlReturnLabels.setBackground(Color.WHITE);
			pnlReturnLabels.setLayout(new GridLayout(2, 0, 0, 0));
			pnlReturnLabels.add(getLblBalanceToBeReturned());
			pnlReturnLabels.add(getTxtReturnBalance());
		}
		return pnlReturnLabels;
	}

	private JLabel getLblBalanceToBeReturned() {
		if (lblBalanceToBeReturned == null) {
			lblBalanceToBeReturned = new JLabel("Balance to be returned:");
			lblBalanceToBeReturned.setHorizontalAlignment(SwingConstants.CENTER);
			lblBalanceToBeReturned.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 14));
			lblBalanceToBeReturned.setDisplayedMnemonic('C');
		}
		return lblBalanceToBeReturned;
	}

	private JTextField getTxtReturnBalance() {
		if (txtReturnBalance == null) {
			txtReturnBalance = new JTextField();
			txtReturnBalance.setBackground(Color.WHITE);
			txtReturnBalance.setEditable(false);
			txtReturnBalance.setHorizontalAlignment(SwingConstants.TRAILING);
			txtReturnBalance.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 14));
			txtReturnBalance.setColumns(10);
		}
		return txtReturnBalance;
	}

	private JLabel getImgReturn20() {
		if (imgReturn20 == null) {
			imgReturn20 = new JLabel("");
			imgReturn20.setIcon(new ImageIcon(ShopWindow.class.getResource("/img/Chip20Small.png")));
			imgReturn20.setHorizontalAlignment(SwingConstants.CENTER);
		}
		return imgReturn20;
	}

	private JLabel getImgReturn50() {
		if (imgReturn50 == null) {
			imgReturn50 = new JLabel("");
			imgReturn50.setIcon(new ImageIcon(ShopWindow.class.getResource("/img/Chip50Small.png")));
			imgReturn50.setHorizontalAlignment(SwingConstants.CENTER);
		}
		return imgReturn50;
	}

	private JLabel getImgReturn100() {
		if (imgReturn100 == null) {
			imgReturn100 = new JLabel("");
			imgReturn100.setIcon(new ImageIcon(ShopWindow.class.getResource("/img/Chip100Small.png")));
			imgReturn100.setHorizontalAlignment(SwingConstants.CENTER);
		}
		return imgReturn100;
	}

	private JSpinner getSpnReturn20() {
		if (spnReturn20 == null) {
			spnReturn20 = new JSpinner();
			spnReturn20.addChangeListener(crc);
			spnReturn20.setSize(new Dimension(100, 100));
			spnReturn20.setMinimumSize(new Dimension(100, 100));
			spnReturn20.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 14));
		}
		return spnReturn20;
	}

	private JSpinner getSpnReturn50() {
		if (spnReturn50 == null) {
			spnReturn50 = new JSpinner();
			spnReturn50.addChangeListener(crc);
			spnReturn50.setSize(new Dimension(100, 100));
			spnReturn50.setMinimumSize(new Dimension(100, 100));
			spnReturn50.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 14));
		}
		return spnReturn50;
	}

	private JSpinner getSpnReturn100() {
		if (spnReturn100 == null) {
			spnReturn100 = new JSpinner();
			spnReturn100.addChangeListener(crc);
			spnReturn100.setSize(new Dimension(100, 100));
			spnReturn100.setMinimumSize(new Dimension(100, 100));
			spnReturn100.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 14));
		}
		return spnReturn100;
	}

	private JPanel getPnlChipsBuyReturn() {
		if (pnlChipsBuyReturn == null) {
			pnlChipsBuyReturn = new JPanel();
			pnlChipsBuyReturn.setBackground(new Color(255, 255, 255));
			pnlChipsBuyReturn.setLayout(new GridLayout(2, 0, 0, 0));
			pnlChipsBuyReturn.add(getPnlBuyChipsByType());
			pnlChipsBuyReturn.add(getPnlReturnChips());
		}
		return pnlChipsBuyReturn;
	}

	private JButton getBtnCancelOrder() {
		if (btnCancelOrder == null) {
			btnCancelOrder = new JButton("Cancel order");
			btnCancelOrder.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					int yesNo = JOptionPane.showConfirmDialog(null, "Are you sure you want to cancel your order?");
					if (yesNo == JOptionPane.YES_OPTION) {
						cancelOrderOperation();
					}
				}
			});
			btnCancelOrder.setMnemonic('l');
			btnCancelOrder.setForeground(Color.WHITE);
			btnCancelOrder.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 14));
			btnCancelOrder.setBackground(Color.RED);
			btnCancelOrder.setAlignmentX(0.5f);
		}
		return btnCancelOrder;
	}

	private JButton getBtnHelp() {
		if (btnHelp == null) {
			btnHelp = new JButton("");
			btnHelp.setIcon(new ImageIcon(ShopWindow.class.getResource("/img/btnhelp.png")));
			btnHelp.setBorder(null);
		}
		return btnHelp;
	}

	private JPanel getPnlReturnMoneyToBank() {
		if (pnlReturnMoneyToBank == null) {
			pnlReturnMoneyToBank = new JPanel();
			pnlReturnMoneyToBank.setBackground(new Color(255, 255, 255));
			pnlReturnMoneyToBank.setLayout(new BorderLayout(0, 0));
			pnlReturnMoneyToBank.add(getBtnRetireMoneyToAccount(), BorderLayout.SOUTH);
		}
		return pnlReturnMoneyToBank;
	}

	private JButton getBtnRetireMoneyToAccount() {
		if (btnRetireMoneyToAccount == null) {
			btnRetireMoneyToAccount = new JButton("Retire money to bank");
			btnRetireMoneyToAccount.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					transportMoneyToBank();
				}
			});
			btnRetireMoneyToAccount.setMnemonic('R');
			btnRetireMoneyToAccount.setForeground(Color.WHITE);
			btnRetireMoneyToAccount.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 14));
			btnRetireMoneyToAccount.setBackground(new Color(0, 0, 139));
		}
		return btnRetireMoneyToAccount;
	}

	// Listener classes

	class CalcTotalChips implements ChangeListener {

		@Override
		public void stateChanged(ChangeEvent e) {
			txtPriceChips.setText(Integer.toString(updateTotalChips()));
		}

		protected int updateTotalChips() {
			return shop.calcTotalPriceChips((Integer) spnBuy5.getValue(), (Integer) spnBuy10.getValue(),
					(Integer) spnBuy20.getValue(), (Integer) spnBuy50.getValue(), (Integer) spnBuy100.getValue());
		}
	}

	class CalcReturningChips implements ChangeListener {

		@Override
		public void stateChanged(ChangeEvent e) {
			txtReturnBalance.setText(Integer.toString(updateTotalChips()));
		}

		protected int updateTotalChips() {
			return shop.calcTotalPriceChips((Integer) spnReturn5.getValue(), (Integer) spnReturn10.getValue(),
					(Integer) spnReturn20.getValue(), (Integer) spnReturn50.getValue(),
					(Integer) spnReturn100.getValue());
		}
	}

	// Methods
	private void showPicture() {
		String pictureName = "/img/" + ((Drink) cmbDrinks.getSelectedItem()).getCode() + ".png";
		adaptImage(imgDrink, pictureName);
//		imgDrink.setIcon(new ImageIcon(MainWindow.class.getResource(pictureName)));
	}

	private void showPictureBalance() {
		adaptImage(imgMoney, "/img/BalanceImage.png");
	}

	private void adaptImage(JLabel label, String imagePath) {
		Image imgOriginal = new ImageIcon(getClass().getResource(imagePath)).getImage();
		Image imgScaled = imgOriginal.getScaledInstance(label.getWidth(), label.getHeight(), Image.SCALE_FAST);
		ImageIcon icon = new ImageIcon(imgScaled);
		label.setIcon(icon);
	}

	private void backOperation() {
		if (parent instanceof WelcomeWindow) {
			((WelcomeWindow) parent).updateBalanceAndChips();
			((WelcomeWindow) parent).checkStatus();
		}
		if (parent instanceof GameWindow) {
			((GameWindow) parent).updateStatus();
		}
	}

	private void logOffOperation() {
		if (parent instanceof GameWindow) {
			((GameWindow) parent).getGame().resetBets(userManagement.getPlayer());
		}
		userManagement.logOff(userManagement.getPlayer());
		JOptionPane.showMessageDialog(null, "Thanks for playing " + userManagement.getPlayer().getUsername() + "!");
	}

	protected void updateStatus() {
		updateBalance();
		updateChips();
		setSpnBuyValue();
		setReturnSpinnerModels();
	}

	private void setSpnBuyValue() {
		spnBuy5.setValue(0);
		spnBuy10.setValue(0);
		spnBuy20.setValue(0);
		spnBuy50.setValue(0);
		spnBuy100.setValue(0);
	}

	private void setReturnSpinnerModels() {
		spnReturn5.setValue(0);
		spnReturn10.setValue(0);
		spnReturn20.setValue(0);
		spnReturn50.setValue(0);
		spnReturn100.setValue(0);
		spnReturn5.setModel(new SpinnerNumberModel(0, 0, buyer.getChipsByValue(5), 1));
		spnReturn10.setModel(new SpinnerNumberModel(0, 0, buyer.getChipsByValue(10), 1));
		spnReturn20.setModel(new SpinnerNumberModel(0, 0, buyer.getChipsByValue(20), 1));
		spnReturn50.setModel(new SpinnerNumberModel(0, 0, buyer.getChipsByValue(50), 1));
		spnReturn100.setModel(new SpinnerNumberModel(0, 0, buyer.getChipsByValue(100), 1));
	}

	// Balance
	protected void updateBalance() {
		txtCurrentBalance.setText(Double.toString(buyer.getBalance()) + " €");
	}

	private void addMoneyOperation() {
		String password = JOptionPane.showInputDialog("Please enter the bank account password");
		try {
			Integer.parseInt(password);
			shop.addMoney(Double.valueOf((Integer) spnMoneyAdder.getValue()), buyer);
			spnMoneyAdder.setValue(1);
			updateBalance();
		} catch (Exception ex) {
			JOptionPane.showMessageDialog(null, "Please, enter a valid number for the password");
		}
	}

	// Chips
	private int updateChips(int i) {
		return buyer.getChipsByValue(i);
	}

	private void addChips() {
		shop.addChips(5, (Integer) spnBuy5.getValue(), buyer);
		shop.addChips(10, (Integer) spnBuy10.getValue(), buyer);
		shop.addChips(20, (Integer) spnBuy20.getValue(), buyer);
		shop.addChips(50, (Integer) spnBuy50.getValue(), buyer);
		shop.addChips(100, (Integer) spnBuy100.getValue(), buyer);
		updateStatus();
	}

	private void buyChipsOperation() {
		if (txtPriceChips.getText().isEmpty() || txtPriceChips.getText().equals("0")) {
			JOptionPane.showMessageDialog(null, "You first need to select the chips you want to buy!");
		} else {
			if (Double.parseDouble(txtPriceChips.getText()) <= buyer.getBalance()) {
				shop.addMoney(-Double.parseDouble(txtPriceChips.getText()), buyer);
				updateBalance();
				addChips();
			} else {
				JOptionPane.showMessageDialog(null, "You don't have enough cash to buy the chips!");
			}
		}
	}

	private void exchangeChipsOperation() {
		if (txtReturnBalance.getText().isEmpty() || txtReturnBalance.getText().equals("0")) {
			JOptionPane.showMessageDialog(null, "You first need to select the chips you want to exchange!");
		} else {
			shop.addMoney(Double.parseDouble(txtReturnBalance.getText()), buyer);
			shop.returnChips(5, (Integer) spnReturn5.getValue(), buyer);
			shop.returnChips(10, (Integer) spnReturn10.getValue(), buyer);
			shop.returnChips(20, (Integer) spnReturn20.getValue(), buyer);
			shop.returnChips(50, (Integer) spnReturn50.getValue(), buyer);
			shop.returnChips(100, (Integer) spnReturn100.getValue(), buyer);

			updateStatus();
			txtReturnBalance.setText("0");
		}
	}

	// Drinks
	protected void updateDrinkModel() {
		drinkModel.clear();
		for (Drink drink : shop.getDrinkOrder()) {
			drinkModel.addElement(drink);
		}
	}

	private void addDrinkToOrderOperation() {
		Drink drinkToAdd = new Drink((Drink) cmbDrinks.getSelectedItem(), cboxWarm.isSelected(), cboxIce.isSelected());
		shop.addDrinkToOrder(drinkToAdd);
		if (shop.getPriceOrder() > buyer.getBalance()) {
			txtPriceOrder.setForeground(Color.RED);
		} else {
			txtPriceOrder.setForeground(new Color(34, 139, 34));
		}
		txtPriceOrder.setText(Double.toString(shop.getPriceOrder()));
		updateDrinkModel();
	}

	private void removeFromOrderOperation() {
		shop.removeDrinkFromOrder(listOrder.getSelectedValue());
		if (shop.getPriceOrder() < buyer.getBalance()) {
			txtPriceOrder.setForeground(new Color(34, 139, 34));
		}
		txtPriceOrder.setText(Double.toString(shop.getPriceOrder()));
		updateDrinkModel();
	}

	private void confirmOrderOperation() {
		if (shop.getDrinkOrder().isEmpty()) {
			JOptionPane.showMessageDialog(null, "You have to add a drink to the order to buy them!");
		} else if (shop.getPriceOrder() <= buyer.getBalance()) {
			JOptionPane.showMessageDialog(null, "Your order will come in a few minutes. Please, wait patiently");
			shop.addMoney(-shop.getPriceOrder(), buyer);
			updateBalance();
			shop.confirmDrinkOrder();
			updateDrinkModel();
			txtPriceOrder.setText("");
		} else {
			JOptionPane.showMessageDialog(null, "You don't have enough cash");
		}
	}

	private void cancelOrderOperation() {
		shop.resetDrinkOrder();
		updateDrinkModel();
		txtPriceOrder.setText("");
	}

	protected void transportMoneyToBank() {
		userManagement.getPlayer().setBalance(0.0);
		updateBalance();

	}
}
