package gui;

import java.awt.BorderLayout;
import javax.help.*;
import java.net.*;
import java.io.*;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import logic.UserManagement;

public class MainWindow extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JPanel pnlTitle;
	private JLabel lblWelcome;
	private JLabel lblCasino;
	private JLabel lblRoulette;
	private JLabel imgRoulette;
	private JPanel pnlButtons;
	private JButton btnSignIn;
	private JButton btnRegister;
	private JButton btnExit;

	// Windows

	private SignInWindow signInWindow = null;
	private RegisterWindow registerWindow = null;

	// Logic

	private UserManagement userManagement = new UserManagement();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainWindow frame = new MainWindow();
					frame.setLocationRelativeTo(null);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
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

		   hb.enableHelpKey(getRootPane(),"introduction", hs);
		 }
	/**
	 * Create the frame.
	 */
	public MainWindow() {
		setResizable(false);
		setIconImage(Toolkit.getDefaultToolkit().getImage(MainWindow.class.getResource("/img/DiceIcon.png")));
		setTitle("Casino Roulette");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 582, 469);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		contentPane.add(getPnlTitle(), BorderLayout.NORTH);
		contentPane.add(getImgRoulette(), BorderLayout.CENTER);
		contentPane.add(getPnlButtons(), BorderLayout.SOUTH);
		loadHelp();
	}

	private JPanel getPnlTitle() {
		if (pnlTitle == null) {
			pnlTitle = new JPanel();
			pnlTitle.setBackground(Color.WHITE);
			pnlTitle.setLayout(new GridLayout(3, 1, 0, 0));
			pnlTitle.add(getLblWelcome());
			pnlTitle.add(getLblCasino());
			pnlTitle.add(getLblRoulette());
		}
		return pnlTitle;
	}

	private JLabel getLblWelcome() {
		if (lblWelcome == null) {
			lblWelcome = new JLabel("Welcome ");
			lblWelcome.setHorizontalAlignment(SwingConstants.CENTER);
			lblWelcome.setForeground(Color.BLACK);
			lblWelcome.setFont(new Font("Segoe UI Semibold", Font.BOLD | Font.ITALIC, 34));
			lblWelcome.setBackground(Color.BLACK);
		}
		return lblWelcome;
	}

	private JLabel getLblCasino() {
		if (lblCasino == null) {
			lblCasino = new JLabel("* CASINO * ");
			lblCasino.setHorizontalAlignment(SwingConstants.CENTER);
			lblCasino.setForeground(Color.BLACK);
			lblCasino.setFont(new Font("Segoe UI Semibold", Font.BOLD | Font.ITALIC, 34));
			lblCasino.setBackground(Color.BLACK);
		}
		return lblCasino;
	}

	private JLabel getLblRoulette() {
		if (lblRoulette == null) {
			lblRoulette = new JLabel("Roulette");
			lblRoulette.setHorizontalAlignment(SwingConstants.CENTER);
			lblRoulette.setForeground(Color.BLACK);
			lblRoulette.setFont(new Font("Segoe UI Semibold", Font.BOLD | Font.ITALIC, 34));
			lblRoulette.setBackground(Color.BLACK);
		}
		return lblRoulette;
	}

	private JLabel getImgRoulette() {
		if (imgRoulette == null) {
			imgRoulette = new JLabel("");
			imgRoulette.setHorizontalAlignment(SwingConstants.CENTER);
			imgRoulette.setIcon(new ImageIcon(MainWindow.class.getResource("/img/rouletteImg2.png")));
		}
		return imgRoulette;
	}

	private JPanel getPnlButtons() {
		if (pnlButtons == null) {
			pnlButtons = new JPanel();
			pnlButtons.setBorder(null);
			pnlButtons.setBackground(Color.WHITE);
			FlowLayout fl_pnlButtons = new FlowLayout(FlowLayout.RIGHT);
			fl_pnlButtons.setVgap(8);
			fl_pnlButtons.setHgap(10);
			pnlButtons.setLayout(fl_pnlButtons);
			pnlButtons.add(getBtnSignIn());
			pnlButtons.add(getBtnRegister());
			pnlButtons.add(getBtnExit());
		}
		return pnlButtons;
	}

	private JButton getBtnSignIn() {
		if (btnSignIn == null) {
			btnSignIn = new JButton("Sign In");
			btnSignIn.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					openSignInWindow();
				}
			});
			btnSignIn.setMnemonic('S');
			btnSignIn.setForeground(Color.WHITE);
			btnSignIn.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 14));
			btnSignIn.setBackground(new Color(65, 105, 225));
			btnSignIn.setActionCommand("OK");
		}
		return btnSignIn;
	}

	private JButton getBtnRegister() {
		if (btnRegister == null) {
			btnRegister = new JButton("Register");
			btnRegister.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					openRegisterWindow();
				}
			});
			btnRegister.setMnemonic('R');
			btnRegister.setForeground(Color.WHITE);
			btnRegister.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 14));
			btnRegister.setBackground(new Color(46, 139, 87));
			btnRegister.setActionCommand("OK");
		}
		return btnRegister;
	}

	private JButton getBtnExit() {
		if (btnExit == null) {
			btnExit = new JButton("Exit");
			btnExit.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					System.exit(0);
				}
			});
			btnExit.setMnemonic('X');
			btnExit.setForeground(Color.WHITE);
			btnExit.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 14));
			btnExit.setBackground(Color.RED);
			btnExit.setActionCommand("Cancel");
		}
		return btnExit;
	}

	// Methods

	protected UserManagement getUserManagement() {
		return userManagement;
	}

	private void openSignInWindow() {
		signInWindow = new SignInWindow(this);
		signInWindow.setModal(true);
		signInWindow.setLocationRelativeTo(this);
		signInWindow.setVisible(true);
	}

	private void openRegisterWindow() {
		registerWindow = new RegisterWindow(this);
		registerWindow.setModal(true);
		registerWindow.setLocationRelativeTo(this);
		registerWindow.setVisible(true);
	}
}
