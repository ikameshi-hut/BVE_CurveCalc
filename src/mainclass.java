import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.InvalidPropertiesFormatException;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JRadioButtonMenuItem;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;


public class mainclass extends JFrame implements ActionListener {

	private JPanel contentPane;
	private final ButtonGroup menu_radiobuttonGroup = new ButtonGroup();
	private final ButtonGroup radiobuttonGroupcant1 = new ButtonGroup();
	private final ButtonGroup radiobuttonGroupcant2 = new ButtonGroup();
	private final ButtonGroup radiobuttonGrouprailrank = new ButtonGroup();

	//メソッド間で共有
	public JTextField textbox_radius;
	public JTextField textbox_cant;
	public JRadioButtonMenuItem RadioMetal, RadioMotif, RadioLinux, RadioWindows, RadioWindowsclassic;
	public JLabel label_result, label_result_moreinfo;
	public JRadioButton radio_ippan, radio_densha, radio_kouseino, radio_furiko;
	public JTextField textbox2_velocity;
	public JTextField textbox2_cant;
	public JLabel label2_result, label2_result_moreinfo;
	public JRadioButton radio2_ippan, radio2_densha, radio2_kouseino, radio2_furiko;
	public JRadioButton radio2_railrank_1, radio2_railrank_2, radio2_railrank_3, radio2_railrank_4;

	public static String VERSION = "1.0";

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		System.out.println("VERSION " + VERSION + "\nこれはデバッグ用コンソールです。\n問題が発生した場合、コンソールに表示されたログを\n作者「ikameshi」宛てに送り付けてください。\n詳しくは、「問題が発生した場合.txt」をご覧ください。\n===============\nUSER INFO\nJAVA VERSION=" + System.getProperty("java.version") + "\nOS NAME=" + System.getProperty("os.name") + "\nOS VERSION=" + System.getProperty("os.version") + "\n===============\n");
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					mainclass frame = new mainclass();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 *
	 *
	 */
	public mainclass() throws InvalidPropertiesFormatException, IOException {
		setTitle("曲線通過速度計算機 For BVE");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 700, 500);

		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);

		JMenu mnMenuSetting = new JMenu("設定・その他");
		menuBar.add(mnMenuSetting);

		JMenuItem menuItemVer = new JMenuItem("バージョン情報・お問い合わせ");
		menuItemVer.addActionListener(this); //アクションリスナーを設定
		menuItemVer.setActionCommand("menu_info"); //アクションリスナー内で、どこのコンポーネントか確認するためのコマンド
		mnMenuSetting.add(menuItemVer);

		JMenuItem menuItemSanko = new JMenuItem("参考文献");
		menuItemSanko.addActionListener(this); //アクションリスナーを設定
		menuItemSanko.setActionCommand("menu_sanko"); //アクションリスナー内で、どこのコンポーネントか確認するためのコマンド
		mnMenuSetting.add(menuItemSanko);

		JMenu mnMenuLooks = new JMenu("UIの見た目(Look and Feel)");
		mnMenuSetting.add(mnMenuLooks);

		RadioMotif = new JRadioButtonMenuItem("Motif");
		RadioMotif.setSelected(true); //初期値
		RadioMotif.addActionListener(this); //アクションリスナーを設定
		RadioMotif.setActionCommand("menu_radio"); //アクションリスナー内で、どこのコンポーネントか確認するためのコマンド
		menu_radiobuttonGroup.add(RadioMotif);
		mnMenuLooks.add(RadioMotif);

		RadioMetal = new JRadioButtonMenuItem("メタル");
		RadioMetal.addActionListener(this); //アクションリスナーを設定
		RadioMetal.setActionCommand("menu_radio"); //アクションリスナー内で、どこのコンポーネントか確認するためのコマンド
		menu_radiobuttonGroup.add(RadioMetal);
		mnMenuLooks.add(RadioMetal);

		RadioLinux = new JRadioButtonMenuItem("Linux (Linuxのみ)");
		RadioLinux.addActionListener(this); //アクションリスナーを設定
		RadioLinux.setActionCommand("menu_radio"); //アクションリスナー内で、どこのコンポーネントか確認するためのコマンド
		menu_radiobuttonGroup.add(RadioLinux);
		mnMenuLooks.add(RadioLinux);

		RadioWindows = new JRadioButtonMenuItem("Windows (Windowsのみ)");
		RadioWindows.addActionListener(this); //アクションリスナーを設定
		RadioWindows.setActionCommand("menu_radio"); //アクションリスナー内で、どこのコンポーネントか確認するためのコマンド
		menu_radiobuttonGroup.add(RadioWindows);
		mnMenuLooks.add(RadioWindows);

		RadioWindowsclassic = new JRadioButtonMenuItem("WindowsClassic (同上)");
		RadioWindowsclassic.addActionListener(this); //アクションリスナーを設定
		RadioWindowsclassic.setActionCommand("menu_radio"); //アクションリスナー内で、どこのコンポーネントか確認するためのコマンド
		menu_radiobuttonGroup.add(RadioWindowsclassic);
		mnMenuLooks.add(RadioWindowsclassic);

		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);

		/*
		//設定ロード
		try {
			Properties prop = new Properties();
        	InputStream is = new FileInputStream("settings.xml");

        	prop.loadFromXML(is); // is はこのメソッドが終了すると close される

        	for (Entry<Object, Object> entry : prop.entrySet()) {
        		LoadRadioState(entry.getValue());
        	}
		} catch(Exception e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(this, "設定ファイルが見つかりませんでした。\n「settings.xml」を追加してください。", "Error",
			        JOptionPane.ERROR_MESSAGE);
		}
        //ロード終わり*/

		setL_F(); //Look and Feel(UIの見た目)の設定

		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		contentPane.add(tabbedPane, BorderLayout.CENTER);

		JPanel sokudo = new JPanel();
		tabbedPane.addTab("カーブの大きさからカーブの制限速度を算出", null, sokudo, "曲線半径とカントからカーブの制限速度を算出します。");
		sokudo.setLayout(null);

		JLabel lblr = new JLabel("曲線半径 (R)");
		lblr.setBounds(12, 10, 72, 13);
		sokudo.add(lblr);

		JLabel label = new JLabel("カント");
		label.setBounds(213, 10, 72, 13);
		sokudo.add(label);

		JLabel label_1 = new JLabel("許容カント不足量");
		label_1.setBounds(442, 10, 118, 13);
		sokudo.add(label_1);

		textbox_radius = new JTextField();
		textbox_radius.setBounds(12, 33, 96, 19);
		sokudo.add(textbox_radius);
		textbox_radius.setColumns(10);

		JLabel lblM = new JLabel("m");
		lblM.setBounds(111, 36, 32, 13);
		sokudo.add(lblM);

		textbox_cant = new JTextField();
		textbox_cant.setColumns(10);
		textbox_cant.setBounds(213, 33, 96, 19);
		sokudo.add(textbox_cant);

		JLabel lblMm = new JLabel("mm");
		lblMm.setBounds(312, 36, 32, 13);
		sokudo.add(lblMm);

		radio_ippan = new JRadioButton("一般列車 (50mm)");
		radiobuttonGroupcant1.add(radio_ippan);
		radio_ippan.setBounds(442, 29, 201, 21);
		sokudo.add(radio_ippan);

		radio_densha = new JRadioButton("電車・気動車 (60mm)");
		radiobuttonGroupcant1.add(radio_densha);
		radio_densha.setBounds(442, 52, 201, 21);
		sokudo.add(radio_densha);

		radio_kouseino = new JRadioButton("高性能電車 (70mm)");
		radiobuttonGroupcant1.add(radio_kouseino);
		radio_kouseino.setBounds(442, 75, 201, 21);
		sokudo.add(radio_kouseino);

		radio_furiko = new JRadioButton("振り子車両 (110mm)");
		radiobuttonGroupcant1.add(radio_furiko);
		radio_furiko.setBounds(442, 98, 201, 21);
		sokudo.add(radio_furiko);

		JButton button_caluculate_sokudo = new JButton("制　　限　　速　　度　　計　　算");
		button_caluculate_sokudo.addActionListener(this); //アクションリスナーを設定
		button_caluculate_sokudo.setActionCommand("caluculate_sokudo"); //アクションリスナー内で、どこのコンポーネントか確認するためのコマンド
		button_caluculate_sokudo.setBounds(12, 170, 645, 64);
		sokudo.add(button_caluculate_sokudo);

		JLabel lblNote = new JLabel("NOTE");
		lblNote.setFont(new Font("MS UI Gothic", Font.BOLD, 12));
		lblNote.setBounds(12, 62, 72, 13);
		sokudo.add(lblNote);

		JLabel lblmm = new JLabel("<html>現実において許される最小曲線半径は、<br>地形上やむを得ない場合を除き160mです。<br>駅構内の場合は400mです。</html>");
		lblmm.setBounds(12, 75, 201, 81);
		sokudo.add(lblmm);

		JLabel label_2 = new JLabel("設定しない場合は0を入力");
		label_2.setBounds(213, 62, 166, 13);
		sokudo.add(label_2);

		JLabel label_3 = new JLabel("NOTE");
		label_3.setFont(new Font("MS UI Gothic", Font.BOLD, 12));
		label_3.setBounds(213, 102, 72, 13);
		sokudo.add(label_3);

		JLabel label_4 = new JLabel("<html>現実において許されるカントの最大値は105mmです。<br></html>");
		label_4.setBounds(213, 115, 206, 45);
		sokudo.add(label_4);

		JLabel label_5 = new JLabel("<html>わからない場合は電車・気動車を選択</html>");
		label_5.setBounds(442, 125, 166, 35);
		sokudo.add(label_5);

		label_result = new JLabel("<html></html>");
		label_result.setBounds(12, 251, 645, 72);
		sokudo.add(label_result);

		label_result_moreinfo = new JLabel("<html></html>");
		label_result_moreinfo.setBounds(12, 333, 645, 45);
		sokudo.add(label_result_moreinfo);

		JLabel label_6 = new JLabel("計算結果");
		label_6.setFont(new Font("MS UI Gothic", Font.BOLD, 12));
		label_6.setBounds(12, 238, 82, 13);
		sokudo.add(label_6);


		JPanel kanwa = new JPanel();
		kanwa.setLayout(null);
		tabbedPane.addTab("カントの大きさから緩和曲線の長さを算出", null, kanwa, "カントと速度から緩和曲線長を算出します。");

		JLabel label_7 = new JLabel("最高速度");
		label_7.setBounds(12, 10, 72, 13);
		kanwa.add(label_7);

		JLabel label_8 = new JLabel("カント");
		label_8.setBounds(213, 10, 72, 13);
		kanwa.add(label_8);

		JLabel label_9 = new JLabel("許容カント不足量");
		label_9.setBounds(442, 10, 118, 13);
		kanwa.add(label_9);

		textbox2_velocity = new JTextField();
		textbox2_velocity.setColumns(10);
		textbox2_velocity.setBounds(12, 33, 96, 19);
		kanwa.add(textbox2_velocity);

		JLabel lblKmh = new JLabel("km/h");
		lblKmh.setBounds(111, 36, 50, 13);
		kanwa.add(lblKmh);

		textbox2_cant = new JTextField();
		textbox2_cant.setColumns(10);
		textbox2_cant.setBounds(213, 33, 96, 19);
		kanwa.add(textbox2_cant);

		JLabel label_11 = new JLabel("mm");
		label_11.setBounds(312, 36, 32, 13);
		kanwa.add(label_11);

		radio2_ippan = new JRadioButton("一般列車 (50mm)");
		radiobuttonGroupcant2.add(radio2_ippan);
		radio2_ippan.setBounds(442, 29, 201, 21);
		kanwa.add(radio2_ippan);

		radio2_densha = new JRadioButton("電車・気動車 (60mm)");
		radiobuttonGroupcant2.add(radio2_densha);
		radio2_densha.setBounds(442, 52, 201, 21);
		kanwa.add(radio2_densha);

		radio2_kouseino = new JRadioButton("高性能電車 (70mm)");
		radiobuttonGroupcant2.add(radio2_kouseino);
		radio2_kouseino.setBounds(442, 75, 201, 21);
		kanwa.add(radio2_kouseino);

		radio2_furiko = new JRadioButton("振り子車両 (110mm)");
		radiobuttonGroupcant2.add(radio2_furiko);
		radio2_furiko.setBounds(442, 98, 201, 21);
		kanwa.add(radio2_furiko);

		JButton button_caluculate_kanwa = new JButton("緩　　和　　曲　　線　　長　　計　　算");
		button_caluculate_kanwa.addActionListener(this);
		button_caluculate_kanwa.setActionCommand("calculate_kanwa");
		button_caluculate_kanwa.setBounds(12, 170, 422, 64);
		kanwa.add(button_caluculate_kanwa);

		JLabel label_12 = new JLabel("NOTE");
		label_12.setFont(new Font("MS UI Gothic", Font.BOLD, 12));
		label_12.setBounds(12, 62, 72, 13);
		kanwa.add(label_12);

		JLabel label_13 = new JLabel("<html>「カーブの大きさからカーブの制限速度を<br>算出」のページで算出された値を入力してもよいです。</html>");
		label_13.setBounds(12, 76, 201, 84);
		kanwa.add(label_13);

		JLabel label_14 = new JLabel("設定しない場合は0を入力");
		label_14.setBounds(213, 62, 166, 13);
		kanwa.add(label_14);

		JLabel label_15 = new JLabel("NOTE");
		label_15.setFont(new Font("MS UI Gothic", Font.BOLD, 12));
		label_15.setBounds(213, 102, 72, 13);
		kanwa.add(label_15);

		JLabel label_16 = new JLabel("<html>現実において許されるカントの最大値は105mmです。<br></html>");
		label_16.setBounds(213, 115, 206, 45);
		kanwa.add(label_16);

		JLabel label_17 = new JLabel("<html>わからない場合は電車・気動車を選択</html>");
		label_17.setBounds(442, 125, 166, 35);
		kanwa.add(label_17);

		label2_result = new JLabel("<html></html>");
		label2_result.setBounds(12, 251, 422, 84);
		kanwa.add(label2_result);

		label2_result_moreinfo = new JLabel("<html></html>");
		label2_result_moreinfo.setBounds(12, 332, 422, 50);
		kanwa.add(label2_result_moreinfo);

		JLabel label_20 = new JLabel("計算結果");
		label_20.setFont(new Font("MS UI Gothic", Font.BOLD, 12));
		label_20.setBounds(12, 238, 82, 13);
		kanwa.add(label_20);

		JLabel label_10 = new JLabel("線路等級　　　　最高速度の目安");
		label_10.setBounds(442, 182, 201, 13);
		kanwa.add(label_10);

		radio2_railrank_1 = new JRadioButton("一等線　　　　120km/h以上");
		radiobuttonGrouprailrank.add(radio2_railrank_1);
		radio2_railrank_1.setBounds(442, 201, 201, 21);
		kanwa.add(radio2_railrank_1);

		radio2_railrank_2 = new JRadioButton("二等線　　　　105-120km/h");
		radiobuttonGrouprailrank.add(radio2_railrank_2);
		radio2_railrank_2.setBounds(442, 224, 201, 21);
		kanwa.add(radio2_railrank_2);

		radio2_railrank_3 = new JRadioButton("三等線　　　　 95-105km/h");
		radiobuttonGrouprailrank.add(radio2_railrank_3);
		radio2_railrank_3.setBounds(442, 246, 201, 21);
		kanwa.add(radio2_railrank_3);

		radio2_railrank_4 = new JRadioButton("四等線　　　　　85-95km/h");
		radiobuttonGrouprailrank.add(radio2_railrank_4);
		radio2_railrank_4.setBounds(442, 269, 201, 21);
		kanwa.add(radio2_railrank_4);

		JLabel label_21 = new JLabel("<html>運行本数の多い路線順に<br>1等線、2等線、3等線、4等線<br>(過密線)　　　　　　　(ローカル線)<br>こちらも目安としてご活用ください。</html>");
		label_21.setBounds(442, 314, 215, 79);
		kanwa.add(label_21);

		JLabel label_22 = new JLabel("NOTE");
		label_22.setFont(new Font("MS UI Gothic", Font.BOLD, 12));
		label_22.setBounds(442, 295, 72, 13);
		kanwa.add(label_22);

	}

	/*
	private void LoadRadioState(Object value) {

		//一度すべて非選択
		RadioMetal.setSelected(false);
		RadioMotif.setSelected(false);
		RadioLinux.setSelected(false);
		RadioWindows.setSelected(false);
		RadioWindowsclassic.setSelected(false);

		if(value.equals(1)) { //設定値が1だったら
			RadioMetal.setSelected(true); //radiobutton Metal有効化
		} else if(value.equals(2)) { //設定値が2だったら
			RadioMotif.setSelected(true);
		} else if(value.equals(3)) { //設定値が3だったら
			RadioLinux.setSelected(true);
		} else if(value.equals(4)) { //設定値が4だったら
			RadioWindows.setSelected(true);
		} else if(value.equals(5)) { //設定値が5だったら
			RadioWindowsclassic.setSelected(true);
		} else {
			RadioMotif.setSelected(true); //とりあえずMotif
		}
		setL_F(); //Look and Feel(UIの見た目)の設定

	}*/

	//Look and Feel(UIの見た目)のせってい
	public void setL_F() {
		//Properties prop = new Properties();

		try {
			if(RadioMetal.isSelected()) {
				UIManager.setLookAndFeel("javax.swing.plaf.metal.MetalLookAndFeel"); //metal
				SwingUtilities.updateComponentTreeUI(this); //画面更新

				/*prop.setProperty("Look and Feel", "1"); //設定 1をセーブ
				OutputStream os = new FileOutputStream("settings.xml");
		        prop.storeToXML(os, "Look and Feel 1=metal 2=motif 3=linx 4=windows 5=windowsclassic");*/
			} else if(RadioMotif.isSelected()) {
				UIManager.setLookAndFeel("com.sun.java.swing.plaf.motif.MotifLookAndFeel"); //motif
				SwingUtilities.updateComponentTreeUI(this); //画面更新

				/*prop.setProperty("Look and Feel", "2"); //設定 2をセーブ
				OutputStream os = new FileOutputStream("settings.xml");
		        prop.storeToXML(os, "Look and Feel 1=metal 2=motif 3=linx 4=windows 5=windowsclassic");*/
			} else if(RadioLinux.isSelected()) {
				UIManager.setLookAndFeel("com.sun.java.swing.plaf.gtk.GTKLookAndFeel"); //linux
				SwingUtilities.updateComponentTreeUI(this); //画面更新

				/*prop.setProperty("Look and Feel", "3"); //設定 3をセーブ
				OutputStream os = new FileOutputStream("settings.xml");
		        prop.storeToXML(os, "Look and Feel 1=metal 2=motif 3=linx 4=windows 5=windowsclassic");*/
			} else if(RadioWindows.isSelected()) {
				UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel"); //windows
				SwingUtilities.updateComponentTreeUI(this); //画面更新

				/*prop.setProperty("Look and Feel", "4"); //設定 4をセーブ
				OutputStream os = new FileOutputStream("settings.xml");
		        prop.storeToXML(os, "Look and Feel 1=metal 2=motif 3=linx 4=windows 5=windowsclassic");*/
			} else if(RadioWindowsclassic.isSelected()) {
				UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsClassicLookAndFeel"); //windows classic
				SwingUtilities.updateComponentTreeUI(this); //画面更新

				/*prop.setProperty("Look and Feel", "5"); //設定 5をセーブ
				OutputStream os = new FileOutputStream("settings.xml");
		        prop.storeToXML(os, "Look and Feel 1=metal 2=motif 3=linx 4=windows 5=windowsclassic");*/
			}

		} catch (Exception e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(this, "このオプションはお使いの環境ではご利用になれません。\n「Linux」はLinux系列、「Windows」「WindowsClassic」はWindowsでのみ動作します。", "Error",
					JOptionPane.ERROR_MESSAGE);
		}

	}

	@Override
	public void actionPerformed(ActionEvent e) { //アクションリスナー コンポーネントが押されたら呼ばれる

		String actcmd = e.getActionCommand();

		if (actcmd.equals("menu_radio")) { //押されたのがメニュー欄のラジオボタンのうちのいずれかだったら・・・
			setL_F();
		} else if (actcmd.equals("caluculate_sokudo")) { //押されたのが制限速度の計算ボタンだったら・・・
			//速度計算

			double R; //曲線半径(m)
			double Cm; //カント(mm)
			double Cd; //許容カント不足量 (mm)
			double V; //曲線通過許容速度 (km/h)

			label_result.setForeground(Color.RED);
			label_result_moreinfo.setText(""); //追加情報ラベルをリセット

			try {
				R = Double.valueOf( textbox_radius.getText() );
				Cm = Double.valueOf( textbox_cant.getText() );
			} catch(NumberFormatException e1) {
				e1.printStackTrace();
				label_result.setText("<html>入力された値が不正であるか、値が入力されていません。正しい数値を入力しているか確認してください。<br>専門的な情報<br>String型からDouble型へのキャストを行うメソッドvalueOf()から、例外NumberFormatExceptionがスローされました。</html>");
				return; //メソッドを抜ける
			}

			//ラジオボタン 許容カント不足量

			if(radio_ippan.isSelected()) {
				Cd = 50;
			} else if(radio_densha.isSelected()) {
				Cd = 60;
			} else if(radio_kouseino.isSelected()) {
				Cd = 70;
			} else if(radio_furiko.isSelected()) {
				Cd= 110;
			} else {
				label_result.setText("許容カント不足量を選択してください。");
				return; //かえってね
			}


			//マイナス値の処理
			if (R<0) {
				R = R*-1;
				textbox_radius.setText(Double.toString(R));
			}
			if (Cm<0) {
				Cm = Cm*-1;
				textbox_cant.setText(Double.toString(Cm));
			}

			//計算部
			//			      ______________
			//V=√R(Cm+Cd) / 8.4
			V = Math.sqrt(R*(Cm+Cd) / 8.4);

			//デバッグ用変数出力
			System.out.println("R=曲線半径　Cm=カント　Cd=許容カント不足量(ﾗｼﾞｵﾎﾞﾀﾝ)　V=速度(計算結果)");
			System.out.println("変数　R" + R + "　Cm" + Cm + "　Cd" + Cd + "　V" + V);

			//ラベルに計算結果を出力
			label_result.setForeground(Color.BLACK);
			label_result.setText("<html>カーブ制限速度(許容通過速度): 約" + Math.rint(V) + "km/h<br>(" + V + "km/h)</html>"); //rint()で小数点以下四捨五入
			//設定されたカントが異常に小さい場合
			if(Cm <= 1 && Cm != 0) {
				label_result_moreinfo.setForeground(Color.MAGENTA);
				label_result_moreinfo.setText("<html>カントの数値が極端に少ないようです。メートルの数値を入力していませんか? ここではミリメートルの数値が要求されます。<br>1000倍するとメートルからミリメートルへ換算できます。");
			}


		} else if (actcmd.equals("calculate_kanwa")) { //押されたのが緩和曲線長の計算ボタンだったら・・・
			//緩和計算

			double Cm; //カント(mm)
			double Cd; //許容カント不足量 (mm)
			double V; //最高速度 (km/h)
			double L1 , L2 , L3; //緩和曲線長(m)
			int railrank = 0; //線路等級

			label2_result.setForeground(Color.RED);
			label2_result_moreinfo.setText(""); //追加情報ラベルをリセット

			try {
				V= Double.valueOf( textbox2_velocity.getText() );
				Cm = Double.valueOf( textbox2_cant.getText() );
			} catch(NumberFormatException e1) {
				e1.printStackTrace();
				label2_result.setText("<html>入力された値が不正であるか、値が入力されていません。正しい数値を入力しているか確認してください。<br>専門的な情報<br>String型からDouble型へのキャストを行うメソッドvalueOf()から、例外NumberFormatExceptionがスローされました。</html>");
				return;
			}

			//ラジオボタン 許容カント不足量
			if(radio2_ippan.isSelected()) {
				Cd = 50;
			} else if(radio2_densha.isSelected()) {
				Cd = 60;
			} else if(radio2_kouseino.isSelected()) {
				Cd = 70;
			} else if(radio2_furiko.isSelected()) {
				Cd= 110;
			} else {
				label2_result.setText("許容カント不足量を選択してください。");
				return;
			}

			//ラジオボタン 線路等級
			if(radio2_railrank_1.isSelected()) {
				railrank = 1;
			} else if(radio2_railrank_2.isSelected()) {
				railrank = 2;
			} else if(radio2_railrank_3.isSelected()) {
				railrank = 3;
			} else if(radio2_railrank_4.isSelected()) {
				railrank = 4;
			} else {
				label2_result.setText("線路等級を選択してください。");
				return;
			}

			//マイナス値の処理
			if (V<0) {
				V=V*-1;
				textbox2_velocity.setText(Double.toString(V));
			}
			if (Cm<0) {
				Cm = Cm*-1;
				textbox2_cant.setText(Double.toString(Cm));
			}

			//計算部
			if(railrank == 1) {
				L1 = Cm;
				L2 = 0.01*Cm*V;
			} else if(railrank == 2) {
				L1 = 0.8*Cm;
				L2 = 0.008*Cm*V;
			} else if(railrank == 3) {
				L1 = 0.6*Cm;
				L2 = 0.007*Cm*V;
			} else if(railrank == 4) {
				L1 = 0.4*Cm;
				L2 = 0.006*Cm*V;
			} else {
				label2_result.setText("線路等級を選択してください。");
				return;
			}
			L3 = 0.009*Cd*V;

			//デバッグ用変数出力
			System.out.println("Cm=カント　Cd=許容カント不足量(ﾗｼﾞｵﾎﾞﾀﾝ)　V=速度　L1,L2,L3=緩和曲線長(計算結果)　railrank=線路等級(ﾗｼﾞｵﾎﾞﾀﾝ)");
			System.out.println("変数　Cm" + Cm + "　Cd" + Cd + "　V" + V + "　L1=" + L1 + "　L2=" + L2 + "　L3=" + L3 + "　railrank" + railrank);

			//ラベルに計算結果を出力
			label2_result.setForeground(Color.BLACK);
			label2_result.setText("<html>緩和曲線長: 約" + Math.rint(Math.max(Math.max(L1,L2),L3)) + "m<br>(" + Math.max(Math.max(L1,L2),L3) + "m)<br>超過遠心力による乗り心地への影響を考慮しない場合: 約" + Math.rint(Math.max(L1,L2)) + "m</html>"); //rint()で小数点以下四捨五入
			//設定されたカントが異常に小さい場合
			if(Cm <= 1 && Cm != 0) {
				label2_result_moreinfo.setForeground(Color.MAGENTA);
				label2_result_moreinfo.setText("<html>カントの数値が極端に少ないようです。メートルの数値を入力していませんか? ここではミリメートルの数値が要求されます。<br>1000倍するとメートルからミリメートルへ換算できます。</html>");
			}


		} else if (actcmd.equals("menu_info")) { //押されたのがメニュー欄のバージョン情報ボタンだったら・・・
			JOptionPane.showMessageDialog(this, "曲線通過速度計算機 for BVE     by ikameshi\nVer. " + VERSION + "\n\nお問い合わせ先\n下記URLの「Contact」からお問い合わせください。\nikameshiの小屋(ホームページ):https://ikameshibve.wixsite.com/ikameshi\n\n(C)2018 ikameshi All rights reserved.\nDO NOT DECOMPILE and DISTRIBUTE! / 逆コンパイル、二次配布を禁じます!", "バージョン情報・お問い合わせ",
					JOptionPane.INFORMATION_MESSAGE);
		} else if (actcmd.equals("menu_sanko")) { //押されたのがメニュー欄の参考文献ボタンだったら・・・
			JOptionPane.showMessageDialog(this, "参考文献\n\n鉄道路線の話 - 安部丹比の交通研究所様\nhttp://www6.plala.or.jp/abetanpidf50568/senro.pdf\n\n緩和曲線長 - 保線Wiki様\nhttp://hosenwiki.com/index.php?title=緩和曲線長\n\n鉄道線路の種類を調べてみる - 聖地巡礼に酒は必要(id:nichinichisou0808)様\nhttps://www.sakura0401.com/entry/2017/06/08/140827\n\nこの場を借りまして、御礼申し上げます。", "参考文献",
					JOptionPane.INFORMATION_MESSAGE);
		}
	}
}

