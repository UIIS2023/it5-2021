package drawing;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JColorChooser;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import geometry.Line;
import geometry.Point;

import java.awt.GridBagLayout;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class DlgLine extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField txtEndX;
	private JTextField txtEndY;
	private JTextField txtStartX;
	private JTextField txtStartY;
	private Color color = null;
	private Line line = null;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			DlgLine dialog = new DlgLine();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public DlgLine() {
		setTitle("Add or delete line!");
		getContentPane().setBackground(new Color(224, 255, 255));
		setModal(true);
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBackground(new Color(224, 255, 255));
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		GridBagLayout gbl_contentPanel = new GridBagLayout();
		gbl_contentPanel.columnWidths = new int[] { 20, 69, -8, -6, 44, 34, 39, 61, 24, 67, 84, 15, 19, 0, 0, 0 };
		gbl_contentPanel.rowHeights = new int[] { 56, 17, 21, 36, 20, 0, 0, 0 };
		gbl_contentPanel.columnWeights = new double[] { 0.0, 0.0, 1.0, 1.0, 1.0, 1.0, 1.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0,
				0.0, 1.0, Double.MIN_VALUE };
		gbl_contentPanel.rowWeights = new double[] { 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE };
		contentPanel.setLayout(gbl_contentPanel);
		{
			JLabel lblStartX = new JLabel("Start Point X");
			GridBagConstraints gbc_lblStartX = new GridBagConstraints();
			gbc_lblStartX.fill = GridBagConstraints.HORIZONTAL;
			gbc_lblStartX.insets = new Insets(0, 0, 5, 5);
			gbc_lblStartX.gridx = 1;
			gbc_lblStartX.gridy = 0;
			contentPanel.add(lblStartX, gbc_lblStartX);
		}
		{
			txtStartX = new JTextField();
			GridBagConstraints gbc_txtStartX = new GridBagConstraints();
			gbc_txtStartX.fill = GridBagConstraints.HORIZONTAL;
			gbc_txtStartX.gridwidth = 3;
			gbc_txtStartX.insets = new Insets(0, 0, 5, 5);
			gbc_txtStartX.gridx = 5;
			gbc_txtStartX.gridy = 0;
			contentPanel.add(txtStartX, gbc_txtStartX);
			txtStartX.setColumns(10);
		}
		{
			JLabel lblEndX = new JLabel("End Point X");
			GridBagConstraints gbc_lblEndX = new GridBagConstraints();
			gbc_lblEndX.insets = new Insets(0, 0, 5, 5);
			gbc_lblEndX.gridx = 9;
			gbc_lblEndX.gridy = 0;
			contentPanel.add(lblEndX, gbc_lblEndX);
		}
		{
			txtEndX = new JTextField();
			GridBagConstraints gbc_txtEndX = new GridBagConstraints();
			gbc_txtEndX.fill = GridBagConstraints.HORIZONTAL;
			gbc_txtEndX.insets = new Insets(0, 0, 5, 5);
			gbc_txtEndX.gridx = 10;
			gbc_txtEndX.gridy = 0;
			contentPanel.add(txtEndX, gbc_txtEndX);
			txtEndX.setColumns(10);
		}
		{
			JLabel lblStartY = new JLabel("Start Point Y");
			GridBagConstraints gbc_lblStartY = new GridBagConstraints();
			gbc_lblStartY.gridwidth = 6;
			gbc_lblStartY.insets = new Insets(0, 0, 5, 5);
			gbc_lblStartY.gridx = 1;
			gbc_lblStartY.gridy = 3;
			contentPanel.add(lblStartY, gbc_lblStartY);
		}
		{
			txtStartY = new JTextField();
			GridBagConstraints gbc_txtStartY = new GridBagConstraints();
			gbc_txtStartY.insets = new Insets(0, 0, 5, 5);
			gbc_txtStartY.fill = GridBagConstraints.HORIZONTAL;
			gbc_txtStartY.gridx = 7;
			gbc_txtStartY.gridy = 3;
			contentPanel.add(txtStartY, gbc_txtStartY);
			txtStartY.setColumns(10);
		}
		{
			JLabel lblEndY = new JLabel("End Point Y");
			GridBagConstraints gbc_lblEndY = new GridBagConstraints();
			gbc_lblEndY.insets = new Insets(0, 0, 5, 5);
			gbc_lblEndY.gridx = 9;
			gbc_lblEndY.gridy = 3;
			contentPanel.add(lblEndY, gbc_lblEndY);
		}
		{
			txtEndY = new JTextField();
			GridBagConstraints gbc_txtEndY = new GridBagConstraints();
			gbc_txtEndY.insets = new Insets(0, 0, 5, 5);
			gbc_txtEndY.fill = GridBagConstraints.HORIZONTAL;
			gbc_txtEndY.gridx = 10;
			gbc_txtEndY.gridy = 3;
			contentPanel.add(txtEndY, gbc_txtEndY);
			txtEndY.setColumns(10);
		}
		{
			JButton btnLineColor = new JButton("Line color");
			btnLineColor.setBackground(new Color(100, 149, 237));
			btnLineColor.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					color = JColorChooser.showDialog(null, "Choose a color", color);
					if (color == null)
						color = Color.BLACK;

				}
			});
			GridBagConstraints gbc_btnLineColor = new GridBagConstraints();
			gbc_btnLineColor.insets = new Insets(0, 0, 5, 5);
			gbc_btnLineColor.gridx = 10;
			gbc_btnLineColor.gridy = 5;
			contentPanel.add(btnLineColor, gbc_btnLineColor);
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setBackground(new Color(224, 255, 255));
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("OK");
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {

						try {
							int newStartX = Integer.parseInt(txtStartX.getText());
							int newStartY = Integer.parseInt(txtStartY.getText());
							int newEndX = Integer.parseInt(txtEndX.getText());
							int newEndY = Integer.parseInt(txtEndY.getText());

							if (newStartX < 0 || newStartY < 0 || newEndX < 0 || newEndY < 0) {
								JOptionPane.showMessageDialog(null, "Values must be positive!", "Error!",
										JOptionPane.ERROR_MESSAGE);
								return;
							}
							line = new Line(new Point(newStartX, newStartY), new Point(newEndX, newEndY), false, color);
							setVisible(false);

						} catch (Exception ex) {
							JOptionPane.showMessageDialog(null, "Enter integer values", "Error",
									JOptionPane.ERROR_MESSAGE);
						}
					}
				});
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
			{
				JButton cancelButton = new JButton("Cancel");
				cancelButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						setVisible(false);

					}
				});
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
	}

	public Line getLine() {
		return line;
	}

	public void setLine(Line line) {
		txtStartX.setText("" + line.getStartPoint().getX());
		txtStartY.setText("" + line.getStartPoint().getY());
		txtEndX.setText("" + line.getEndPoint().getX());
		txtEndY.setText("" + line.getEndPoint().getY());
		color = line.getColor();

	}

	public void setColor(Color color) {
		this.color = color;
	}

}