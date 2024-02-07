package FilmPida;


import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.io.IOException;
import java.net.URL;


public class View extends JFrame {

	public static final long serialVersionUID = 1L;
	public JPanel contentPane;
	public JList filmList;
	public JButton Absteigend;
	public JButton Aufsteigend;
	public JTextField searchBar;
	public JButton Search;
	protected JButton Zurueck;
	private JComboBox catCombo;
	private JButton Duration;
	private JLabel labltoimg;

	/**
	 * Create the frame.
	 */
	public View() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 650, 412);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		contentPane.add(getFilmList());
		contentPane.add(getAbsteigend());
		contentPane.add(getAufsteigend());
		contentPane.add(getSearchBar());
		contentPane.add(getSearch());
		contentPane.add(getZurueck());
		contentPane.add(getCatCombo());
		contentPane.add(getDuration());
		contentPane.add(getLabltoimg());

		setVisible(true);
	}

	protected JList getFilmList() {
		if (filmList == null) {
			filmList = new JList();
			filmList.setBounds(10, 11, 205, 351);
		}
		return filmList;
	}
	protected JButton getAbsteigend() {
		if (Absteigend == null) {
			Absteigend = new JButton("Absteigend");

			Absteigend.setBounds(225, 8, 153, 23);
		}
		return Absteigend;
	}
	protected JButton getAufsteigend() {
		if (Aufsteigend == null) {
			Aufsteigend = new JButton("Aufsteigend");
			Aufsteigend.setBounds(225, 45, 153, 23);
		}
		return Aufsteigend;
	}
	protected JTextField getSearchBar() {
		if (searchBar == null) {
			searchBar = new JTextField();
			searchBar.setBounds(225, 190, 399, 20);
			searchBar.setColumns(10);
		}
		return searchBar;
	}
	protected JButton getSearch() {
		if (Search == null) {
			Search = new JButton("Search");
			Search.setBounds(225, 221, 399, 23);
		}
		return Search;
	}
	protected JButton getZurueck() {
		if (Zurueck == null) {
			Zurueck = new JButton("Zurück");
			Zurueck.setBounds(500, 339, 124, 23);
		}
		return Zurueck;
	}
	protected JComboBox getCatCombo() {
		if (catCombo == null) {
			catCombo = new JComboBox();

			catCombo.setBounds(225, 117, 153, 22);
		}
		return catCombo;
	}

	protected JButton getDuration() {
		if (Duration == null) {
			Duration = new JButton("Duration");
			Duration.setBounds(225, 83, 153, 23);
		}
		return Duration;
	}
	protected JLabel getLabltoimg() {
		if (labltoimg == null) {
			labltoimg = new JLabel(" ");
		}
		return labltoimg;
	}
}
