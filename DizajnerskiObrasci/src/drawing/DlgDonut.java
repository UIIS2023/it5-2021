package drawing;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JColorChooser;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import geometry.Donut;
import geometry.Point;
import geometry.Circle;
import geometry.Shape;

import java.awt.GridBagLayout;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class DlgDonut extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField txtCenterX;
	private JTextField txtRadius;
	private JTextField txtCenterY;
	private JTextField txtInnerRadius;
	private Color innerColor = null;
	private Color borderColor = null;
	private Donut donut = null;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			DlgDonut dialog = new DlgDonut();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public DlgDonut() {
		setTitle("Add or edit donut!");
		setModal(true);
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBackground(new Color(224, 255, 255));
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		GridBagLayout gbl_contentPanel = new GridBagLayout();
		gbl_contentPanel.columnWidths = new int[] { 90, 0, 0, 0, 0, 0, 0 };
		gbl_contentPanel.rowHeights = new int[] { 34, 25, 27, 35, 0, 0, 0, 0 };
		gbl_contentPanel.columnWeights = new double[] { 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE };
		gbl_contentPanel.rowWeights = new double[] { 0.0, 1.0, 1.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE };
		contentPanel.setLayout(gbl_contentPanel);
		{
			JLabel lblCenterX = new JLabel("X Center");
			GridBagConstraints gbc_lblCenterX = new GridBagConstraints();
			gbc_lblCenterX.insets = new Insets(0, 0, 5, 5);
			gbc_lblCenterX.gridx = 0;
			gbc_lblCenterX.gridy = 0;
			contentPanel.add(lblCenterX, gbc_lblCenterX);
		}
		{
			txtCenterX = new JTextField();
			GridBagConstraints gbc_txtCenterX = new GridBagConstraints();
			gbc_txtCenterX.insets = new Insets(0, 0, 5, 0);
			gbc_txtCenterX.fill = GridBagConstraints.HORIZONTAL;
			gbc_txtCenterX.gridx = 5;
			gbc_txtCenterX.gridy = 0;
			contentPanel.add(txtCenterX, gbc_txtCenterX);
			txtCenterX.setColumns(10);
		}
		{
			JLabel lblCenterY = new JLabel("New label");
			GridBagConstraints gbc_lblCenterY = new GridBagConstraints();
			gbc_lblCenterY.insets = new Insets(0, 0, 5, 5);
			gbc_lblCenterY.gridx = 0;
			gbc_lblCenterY.gridy = 1;
			contentPanel.add(lblCenterY, gbc_lblCenterY);
		}
		{
			txtCenterY = new JTextField();
			GridBagConstraints gbc_txtCenterY = new GridBagConstraints();
			gbc_txtCenterY.insets = new Insets(0, 0, 5, 0);
			gbc_txtCenterY.fill = GridBagConstraints.HORIZONTAL;
			gbc_txtCenterY.gridx = 5;
			gbc_txtCenterY.gridy = 1;
			contentPanel.add(txtCenterY, gbc_txtCenterY);
			txtCenterY.setColumns(10);
		}
		{
			JLabel lbllnnerRadius = new JLabel("InnerRadius");
			GridBagConstraints gbc_lbllnnerRadius = new GridBagConstraints();
			gbc_lbllnnerRadius.insets = new Insets(0, 0, 5, 5);
			gbc_lbllnnerRadius.gridx = 0;
			gbc_lbllnnerRadius.gridy = 2;
			contentPanel.add(lbllnnerRadius, gbc_lbllnnerRadius);
		}
		{
			txtInnerRadius = new JTextField();
			GridBagConstraints gbc_txtInnerRadius = new GridBagConstraints();
			gbc_txtInnerRadius.insets = new Insets(0, 0, 5, 0);
			gbc_txtInnerRadius.fill = GridBagConstraints.HORIZONTAL;
			gbc_txtInnerRadius.gridx = 5;
			gbc_txtInnerRadius.gridy = 2;
			contentPanel.add(txtInnerRadius, gbc_txtInnerRadius);
			txtInnerRadius.setColumns(10);
		}
		{
			JLabel lblRadius = new JLabel("Radius");
			GridBagConstraints gbc_lblRadius = new GridBagConstraints();
			gbc_lblRadius.insets = new Insets(0, 0, 5, 5);
			gbc_lblRadius.gridx = 0;
			gbc_lblRadius.gridy = 3;
			contentPanel.add(lblRadius, gbc_lblRadius);
		}
		{
			txtRadius = new JTextField();
			GridBagConstraints gbc_txtRadius = new GridBagConstraints();
			gbc_txtRadius.insets = new Insets(0, 0, 5, 0);
			gbc_txtRadius.fill = GridBagConstraints.HORIZONTAL;
			gbc_txtRadius.gridx = 5;
			gbc_txtRadius.gridy = 3;
			contentPanel.add(txtRadius, gbc_txtRadius);
			txtRadius.setColumns(10);
		}
		{
			JButton btnInnerColor = new JButton("Inner Color");
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
			gbc_btnInnerColor.gridy = 5;
			contentPanel.add(btnInnerColor, gbc_btnInnerColor);
		}
		{
			JButton btnBorderColor = new JButton("Border Color");
			btnBorderColor.setBackground(new Color(100, 149, 237));
			btnBorderColor.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					borderColor = JColorChooser.showDialog(null, "Choose a color", borderColor);
					if (borderColor == null)
						borderColor = Color.BLACK;
				}
			});
			GridBagConstraints gbc_btnBorderColor = new GridBagConstraints();
			gbc_btnBorderColor.insets = new Insets(0, 0, 0, 5);
			gbc_btnBorderColor.gridx = 0;
			gbc_btnBorderColor.gridy = 6;
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
							int newX = Integer.parseInt(txtCenterX.getText());
							int newY = Integer.parseInt(txtCenterY.getText());
							int newRadius = Integer.parseInt(txtRadius.getText());
							int newInnerRadius = Integer.parseInt(txtInnerRadius.getText());

							if (newX < 0 || newY < 0 || newRadius < 1 || newInnerRadius < 1
									|| newInnerRadius >= newRadius) {
								JOptionPane.showMessageDialog(null, "Values must be positive and radius must be larger then inner radius", "Error!",
										JOptionPane.ERROR_MESSAGE);
								return;
							}
							donut = new Donut(new Point(newX, newY), newRadius, newInnerRadius, false, borderColor,
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

	public Donut getDonut() {

		return donut;
	}

	public void setDonut(Donut donut) {

		txtCenterX.setText("" + donut.getCenter().getX());
		txtCenterY.setText("" + donut.getCenter().getY());
		txtRadius.setText("" + donut.getRadius());
		txtInnerRadius.setText("" + donut.getInnerRadius());
		borderColor = donut.getColor();
		innerColor = donut.getInnerColor();

	}

	public void setCenter(Point point) {
		txtCenterX.setText("" + point.getX());
		txtCenterY.setText("" + point.getY());
	}

	public void setInnerColor(Color innerColor) {
		this.innerColor = innerColor;
	}

	public void setBorderColor(Color borderColor) {
		this.borderColor = borderColor;
	}

}