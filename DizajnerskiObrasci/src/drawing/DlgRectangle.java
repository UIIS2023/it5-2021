package drawing;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JColorChooser;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import geometry.Point;
import geometry.Rectangle;
import geometry.Shape;

import java.awt.GridBagLayout;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class DlgRectangle extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField txtUpperY;
	private JTextField txtUpperX;
	private JTextField txtHeight;
	private JTextField txtWidth;
	private Color innerColor = null;
	private Color borderColor = null;
	private Rectangle rectangle = null;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			DlgRectangle dialog = new DlgRectangle();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public DlgRectangle() {
		getContentPane().setBackground(new Color(224, 255, 255));
		setTitle("Add or delete rectangle!");
		setModal(true);
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBackground(new Color(224, 255, 255));
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		GridBagLayout gbl_contentPanel = new GridBagLayout();
		gbl_contentPanel.columnWidths = new int[] { 114, 0, 0, 0, 0, 99, 0 };
		gbl_contentPanel.rowHeights = new int[] { 52, 14, 40, 32, 33, 38, 13, 0, 34, 13, 0 };
		gbl_contentPanel.columnWeights = new double[] { 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE };
		gbl_contentPanel.rowWeights = new double[] { 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0,
				Double.MIN_VALUE };
		contentPanel.setLayout(gbl_contentPanel);
		{
			JLabel lblUpperX = new JLabel("Upper left point X");
			GridBagConstraints gbc_lblUpperX = new GridBagConstraints();
			gbc_lblUpperX.insets = new Insets(0, 0, 5, 5);
			gbc_lblUpperX.gridx = 0;
			gbc_lblUpperX.gridy = 2;
			contentPanel.add(lblUpperX, gbc_lblUpperX);
		}
		{
			txtUpperX = new JTextField();
			GridBagConstraints gbc_txtUpperX = new GridBagConstraints();
			gbc_txtUpperX.insets = new Insets(0, 0, 5, 0);
			gbc_txtUpperX.fill = GridBagConstraints.HORIZONTAL;
			gbc_txtUpperX.gridx = 5;
			gbc_txtUpperX.gridy = 2;
			contentPanel.add(txtUpperX, gbc_txtUpperX);
			txtUpperX.setColumns(10);
		}
		{
			JLabel lblUpperY = new JLabel("Upper left point Y");
			GridBagConstraints gbc_lblUpperY = new GridBagConstraints();
			gbc_lblUpperY.insets = new Insets(0, 0, 5, 5);
			gbc_lblUpperY.gridx = 0;
			gbc_lblUpperY.gridy = 3;
			contentPanel.add(lblUpperY, gbc_lblUpperY);
		}
		{
			txtUpperY = new JTextField();
			GridBagConstraints gbc_txtUpperY = new GridBagConstraints();
			gbc_txtUpperY.insets = new Insets(0, 0, 5, 0);
			gbc_txtUpperY.fill = GridBagConstraints.HORIZONTAL;
			gbc_txtUpperY.gridx = 5;
			gbc_txtUpperY.gridy = 3;
			contentPanel.add(txtUpperY, gbc_txtUpperY);
			txtUpperY.setColumns(10);
		}
		{
			JLabel lblHeight = new JLabel("Height");
			GridBagConstraints gbc_lblHeight = new GridBagConstraints();
			gbc_lblHeight.insets = new Insets(0, 0, 5, 5);
			gbc_lblHeight.gridx = 0;
			gbc_lblHeight.gridy = 4;
			contentPanel.add(lblHeight, gbc_lblHeight);
		}
		{
			txtHeight = new JTextField();
			GridBagConstraints gbc_txtHeight = new GridBagConstraints();
			gbc_txtHeight.insets = new Insets(0, 0, 5, 0);
			gbc_txtHeight.fill = GridBagConstraints.HORIZONTAL;
			gbc_txtHeight.gridx = 5;
			gbc_txtHeight.gridy = 4;
			contentPanel.add(txtHeight, gbc_txtHeight);
			txtHeight.setColumns(10);
		}
		{
			JLabel lblWidth = new JLabel("Width");
			GridBagConstraints gbc_lblWidth = new GridBagConstraints();
			gbc_lblWidth.insets = new Insets(0, 0, 5, 5);
			gbc_lblWidth.gridx = 0;
			gbc_lblWidth.gridy = 5;
			contentPanel.add(lblWidth, gbc_lblWidth);
		}
		{
			txtWidth = new JTextField();
			GridBagConstraints gbc_txtWidth = new GridBagConstraints();
			gbc_txtWidth.insets = new Insets(0, 0, 5, 0);
			gbc_txtWidth.fill = GridBagConstraints.HORIZONTAL;
			gbc_txtWidth.gridx = 5;
			gbc_txtWidth.gridy = 5;
			contentPanel.add(txtWidth, gbc_txtWidth);
			txtWidth.setColumns(10);
		}
		{
			JButton btnInnerColor = new JButton("Inner color");
			btnInnerColor.setBackground(new Color(100, 149, 237));
			btnInnerColor.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					innerColor = JColorChooser.showDialog(null, "Choose a color", innerColor);
					if (innerColor == null)
						innerColor = Color.white;

				}
			});
			GridBagConstraints gbc_btnInnerColor = new GridBagConstraints();
			gbc_btnInnerColor.insets = new Insets(0, 0, 5, 5);
			gbc_btnInnerColor.gridx = 0;
			gbc_btnInnerColor.gridy = 6;
			contentPanel.add(btnInnerColor, gbc_btnInnerColor);
		}
		{
			JButton btnBorderColor = new JButton("Border color");
			btnBorderColor.setBackground(new Color(100, 149, 237));
			btnBorderColor.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					borderColor = JColorChooser.showDialog(null, "Choose a color", borderColor);
					if (borderColor == null)
						borderColor = Color.BLACK;
				}
			});
			GridBagConstraints gbc_btnBorderColor = new GridBagConstraints();
			gbc_btnBorderColor.insets = new Insets(0, 0, 5, 5);
			gbc_btnBorderColor.gridx = 0;
			gbc_btnBorderColor.gridy = 7;
			contentPanel.add(btnBorderColor, gbc_btnBorderColor);
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
							int newX = Integer.parseInt(txtUpperX.getText());
							int newY = Integer.parseInt(txtUpperY.getText());
							int newHeight = Integer.parseInt(txtHeight.getText());
							int newWidth = Integer.parseInt(txtWidth.getText());

							if (newX < 0 || newY < 0 || newHeight < 1 || newWidth < 1) {
								JOptionPane.showMessageDialog(null, "Values must be positive", "Error!",
										JOptionPane.ERROR_MESSAGE);
								return;
							}
							rectangle = new Rectangle(new Point(newX, newY), newHeight, newWidth, false, borderColor,
									innerColor);
							setVisible(false);
						} catch (Exception ex) {
							JOptionPane.showMessageDialog(null, "Enter integer values", "Error!",
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

	public void setRectangle(Rectangle rectangle) {

		txtUpperX.setText("" + rectangle.getUpperLeftPoint().getX());
		txtUpperY.setText("" + rectangle.getUpperLeftPoint().getY());
		txtHeight.setText("" + rectangle.getHeight());
		txtWidth.setText("" + rectangle.getWidth());
		innerColor = rectangle.getInnerColor();
		borderColor = rectangle.getColor();

	}

	public Shape getRectangle() {
		// TODO Auto-generated method stub
		return rectangle;
	}

	public void setUpperLeftPoint(Point point) {
		txtUpperX.setText("" + point.getX());
		txtUpperY.setText("" + point.getY());
	}

	public void setInnerColor(Color innerColor) {
		this.innerColor = innerColor;
	}

	public void setBorderColor(Color borderColor) {
		this.borderColor = borderColor;
	}

}