package mvc;

import java.awt.BorderLayout;
import java.awt.Color;

import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.ButtonGroup;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;

import javax.swing.JList;

import javax.swing.JPanel;
import javax.swing.JToggleButton;
import javax.swing.border.EmptyBorder;

import geometry.Point;



import javax.swing.JScrollPane;

public class DrawingFrame extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	
	DrawingController controller;

	private JPanel contentPane;
	private DrawingView view = new DrawingView();
	private final ButtonGroup buttonGroup = new ButtonGroup();
	private final ButtonGroup buttonGroup_1 = new ButtonGroup();

	private int s = 0;
	private int d = 1;
	private JToggleButton tglbtnPoint;
	private JToggleButton tglbtnLine;
	private JToggleButton tglbtnCircle;
	private JToggleButton tglbtnRectangle;
	private JToggleButton tglbtnDonut;
	private JToggleButton tglbtnSelect;
	private JButton btnDelete;
	private JButton btnModify;
	private JToggleButton tglbtnHexagon;
	private JButton btnUndo;
	private JButton btnRedo;
	private JButton btnToFront;
	private JButton btnToBack;
	private JButton btnBringToFront;
	private JButton btnBringToBack;
	private Color buttonColor = Color.white;

	private JButton btnInnerColor;
	private JButton btnBorderColor;
	private JScrollPane scrollPane;
	private JList<String> log;
	private DefaultListModel<String> dlmList;
	private JButton btnSaveDrawing;
	private JButton btnImportDrawing;

	public DrawingFrame() {
	   setTitle("Danijela Simic IT5/2021");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 950, 650);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		view.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {

				Point mouseClicked = new Point(e.getX(), e.getY());
				if(s==1)
				{
					System.out.println("Selekcija");
					controller.SelectionControle(mouseClicked);
					return;
				}
				if(d==1)
				{
					System.out.println("Crtanje");
				controller.drawingShape(mouseClicked);
				view.repaint();
				return;
				}
//				
			}
		});

		getContentPane().add(view, BorderLayout.CENTER);
		view.setBackground(new Color(255, 255, 255));
		view.setBounds(200, 60, 500, 300);
		dlmList = new DefaultListModel<String>();
		scrollPane = new JScrollPane();

		JPanel panel = new JPanel();
		panel.setBackground(new Color(255, 250, 205));
		contentPane.add(panel, BorderLayout.CENTER);

		GridBagLayout gbl_panel = new GridBagLayout();
		gbl_panel.columnWidths = new int[] { 137, 0, 0, 0, 0, 0, 0, 0, 421, 334, 0 };
		gbl_panel.rowHeights = new int[] { 13, 0, 21, 21, 39, 72, 15, 0, 31, 20, 0, 0, 179, 0, 0, 0, 0, 0 };
		gbl_panel.columnWeights = new double[] { 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0, 1.0, Double.MIN_VALUE };
		gbl_panel.rowWeights = new double[] { 1.0, 0.0, 0.0, 0.0, 1.0, 1.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0, 0.0, 0.0, 0.0, 0.0,
				Double.MIN_VALUE };
		panel.setLayout(gbl_panel);
		
		
		
		
		btnInnerColor = new JButton("Inner color");
		btnInnerColor.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				buttonColor=controller.InnerColor();
				 if(buttonColor!=null)
				 {
					 btnInnerColor.setBackground(buttonColor);
				 }
				 getView().repaint();

			}
		});
		
		btnBorderColor = new JButton("Border color");
		btnBorderColor.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				buttonColor=controller.BorderColor();
				 if(buttonColor!=null)
				 {
					 btnBorderColor.setBackground(buttonColor);
				 }
				 getView().repaint();
			}
		});
		
		btnSaveDrawing = new JButton("Save Drawing");
		btnSaveDrawing.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				controller.SaveDrawing();
				
			}
		});
		GridBagConstraints gbc_btnSaveDrawing = new GridBagConstraints();
		gbc_btnSaveDrawing.insets = new Insets(0, 0, 5, 5);
		gbc_btnSaveDrawing.gridx = 1;
		gbc_btnSaveDrawing.gridy = 0;
		panel.add(btnSaveDrawing, gbc_btnSaveDrawing);
		
		btnImportDrawing = new JButton("Import Drawing");
		btnImportDrawing.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				controller.OpenDrawing();
				repaint();
			}
		});
		GridBagConstraints gbc_btnImportDrawing = new GridBagConstraints();
		gbc_btnImportDrawing.insets = new Insets(0, 0, 5, 5);
		gbc_btnImportDrawing.gridx = 3;
		gbc_btnImportDrawing.gridy = 0;
		panel.add(btnImportDrawing, gbc_btnImportDrawing);
		GridBagConstraints gbc_btnBorderColor = new GridBagConstraints();
		gbc_btnBorderColor.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnBorderColor.insets = new Insets(0, 0, 5, 5);
		gbc_btnBorderColor.gridx = 0;
		gbc_btnBorderColor.gridy = 3;
		panel.add(btnBorderColor, gbc_btnBorderColor);
		GridBagConstraints gbc_btnInnerColor = new GridBagConstraints();
		gbc_btnInnerColor.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnInnerColor.insets = new Insets(0, 0, 5, 5);
		gbc_btnInnerColor.gridx = 0;
		gbc_btnInnerColor.gridy = 4;
		panel.add(btnInnerColor, gbc_btnInnerColor);

		tglbtnPoint = new JToggleButton("Point");
		tglbtnPoint.setBackground(Color.ORANGE);
		tglbtnPoint.setFont(new Font("Tahoma", Font.BOLD, 14));
		tglbtnPoint.setSelected(true);
		buttonGroup_1.add(tglbtnPoint);
		GridBagConstraints gbc_tglbtnPoint = new GridBagConstraints();
		gbc_tglbtnPoint.fill = GridBagConstraints.HORIZONTAL;
		gbc_tglbtnPoint.insets = new Insets(0, 0, 5, 5);
		gbc_tglbtnPoint.gridx = 0;
		gbc_tglbtnPoint.gridy = 6;
		panel.add(tglbtnPoint, gbc_tglbtnPoint);

		tglbtnLine = new JToggleButton("Line");
		tglbtnLine.setBackground(Color.ORANGE);
		tglbtnLine.setFont(new Font("Tahoma", Font.BOLD, 14));
		buttonGroup_1.add(tglbtnLine);
		GridBagConstraints gbc_tglbtnLine = new GridBagConstraints();
		gbc_tglbtnLine.fill = GridBagConstraints.HORIZONTAL;
		gbc_tglbtnLine.insets = new Insets(0, 0, 5, 5);
		gbc_tglbtnLine.gridx = 0;
		gbc_tglbtnLine.gridy = 7;
		panel.add(tglbtnLine, gbc_tglbtnLine);

		tglbtnRectangle = new JToggleButton("Rectangle");
		tglbtnRectangle.setBackground(Color.ORANGE);
		tglbtnRectangle.setFont(new Font("Tahoma", Font.BOLD, 14));
		buttonGroup_1.add(tglbtnRectangle);
		GridBagConstraints gbc_tglbtnRectangle = new GridBagConstraints();
		gbc_tglbtnRectangle.fill = GridBagConstraints.HORIZONTAL;
		gbc_tglbtnRectangle.insets = new Insets(0, 0, 5, 5);
		gbc_tglbtnRectangle.gridx = 0;
		gbc_tglbtnRectangle.gridy = 8;
		panel.add(tglbtnRectangle, gbc_tglbtnRectangle);

		tglbtnCircle = new JToggleButton("Circle");
		tglbtnCircle.setBackground(Color.ORANGE);
		tglbtnCircle.setFont(new Font("Tahoma", Font.BOLD, 14));
		buttonGroup_1.add(tglbtnCircle);
		GridBagConstraints gbc_tglbtnCircle = new GridBagConstraints();
		gbc_tglbtnCircle.fill = GridBagConstraints.HORIZONTAL;
		gbc_tglbtnCircle.insets = new Insets(0, 0, 5, 5);
		gbc_tglbtnCircle.gridx = 0;
		gbc_tglbtnCircle.gridy = 9;
		panel.add(tglbtnCircle, gbc_tglbtnCircle);

		tglbtnDonut = new JToggleButton("Donut");
		tglbtnDonut.setBackground(Color.ORANGE);
		tglbtnDonut.setFont(new Font("Tahoma", Font.BOLD, 14));
		buttonGroup_1.add(tglbtnDonut);
		GridBagConstraints gbc_tglbtnDonut = new GridBagConstraints();
		gbc_tglbtnDonut.fill = GridBagConstraints.HORIZONTAL;
		gbc_tglbtnDonut.insets = new Insets(0, 0, 5, 5);
		gbc_tglbtnDonut.gridx = 0;
		gbc_tglbtnDonut.gridy = 10;
		panel.add(tglbtnDonut, gbc_tglbtnDonut);
		
		tglbtnHexagon = new JToggleButton("Hexagon");
		
		buttonGroup_1.add(tglbtnHexagon);
		tglbtnHexagon.setBackground(Color.ORANGE);
		tglbtnHexagon.setFont(new Font("Tahoma", Font.BOLD, 14));
		GridBagConstraints gbc_tglbtnHexagon = new GridBagConstraints();
		gbc_tglbtnHexagon.fill = GridBagConstraints.HORIZONTAL;
		gbc_tglbtnHexagon.insets = new Insets(0, 0, 5, 5);
		gbc_tglbtnHexagon.gridx = 0;
		gbc_tglbtnHexagon.gridy = 11;
		panel.add(tglbtnHexagon, gbc_tglbtnHexagon);
		log = new JList<String>();
		GridBagConstraints gbc_log = new GridBagConstraints();
		gbc_log.insets = new Insets(0, 0, 5, 5);
		gbc_log.gridx = 3;
		gbc_log.gridy = 12;
		//panel.add(log, gbc_log);
		
		
		scrollPane = new JScrollPane();
		GridBagConstraints gbc_scrollPane = new GridBagConstraints();
		gbc_scrollPane.gridwidth = 5;
		gbc_scrollPane.insets = new Insets(0, 0, 5, 5);
		gbc_scrollPane.fill = GridBagConstraints.BOTH;
		gbc_scrollPane.gridx = 4;
		gbc_scrollPane.gridy = 12;
		panel.add(scrollPane, gbc_scrollPane);
		log.setEnabled(false);
		log.setModel(dlmList);
		log.setFont(new Font("Lucida Console", Font.BOLD, 12));
		scrollPane.setViewportView(log);


		JPanel panel_1 = new JPanel();
		panel_1.setBackground(new Color(255, 250, 240));
		contentPane.add(panel_1, BorderLayout.SOUTH);

		JToggleButton tglbtnSelect = new JToggleButton("Select");
		tglbtnSelect.setBackground(Color.YELLOW);
		tglbtnSelect.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
				tglbtnPoint.setEnabled(false);
				tglbtnLine.setEnabled(false);
				tglbtnRectangle.setEnabled(false);
				tglbtnCircle.setEnabled(false);
				tglbtnDonut.setEnabled(false);
				tglbtnHexagon.setEnabled(false);
			
				s=1;
				d=0;
				
				
				System.out.println("DUGME");
				
			}
		});
		buttonGroup.add(tglbtnSelect);
		panel_1.add(tglbtnSelect);

		JToggleButton tglbtnDraw = new JToggleButton("Draw");
		tglbtnDraw.setBackground(Color.YELLOW);
		tglbtnDraw.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				s=0;
				d=1;
				repaint();
				tglbtnPoint.setEnabled(true);
				tglbtnLine.setEnabled(true);
				tglbtnRectangle.setEnabled(true);
				tglbtnCircle.setEnabled(true);
				tglbtnDonut.setEnabled(true);
				tglbtnHexagon.setEnabled(true);
				btnModify.setEnabled(false);
				btnDelete.setEnabled(false);
				btnUndo.setEnabled(false);
				btnRedo.setEnabled(false);
				btnToFront.setEnabled(false);
				btnToBack.setEnabled(false);
				btnBringToFront.setEnabled(false);
				btnBringToBack.setEnabled(false);
			

			}
		});
		tglbtnDraw.setSelected(true);
		buttonGroup.add(tglbtnDraw);
		panel_1.add(tglbtnDraw);

		btnDelete = new JButton("Delete");
		btnDelete.setEnabled(false);
		btnDelete.setBackground(Color.YELLOW);
		btnDelete.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (view.model.isEmpty()) {
					btnDelete.setEnabled(false);
					btnModify.setEnabled(false);
					return;
				}
				controller.deleteShapes();
				
			}

		});
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		panel_1.add(btnDelete);

		btnModify = new JButton("Modify");
		btnModify.setEnabled(false);
		btnModify.setBackground(Color.YELLOW);
		btnModify.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (view.model.isEmpty()) {
					btnDelete.setEnabled(false);
					btnModify.setEnabled(false);
					return;
				}
				controller.modifyShapes();
				repaint();
			}
		});
		panel_1.add(btnModify);
		
		btnUndo = new JButton("Undo");
		btnUndo.setEnabled(false);
		btnUndo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				controller.Undo();
				
			}
		});
		btnUndo.setBackground(Color.YELLOW);
		panel_1.add(btnUndo);
		
		btnRedo = new JButton("Redo");
		btnRedo.setEnabled(false);
		btnRedo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				controller.Redo();
			}
		});
		btnRedo.setBackground(Color.YELLOW);
		panel_1.add(btnRedo);
		
		btnToFront = new JButton("To front");
		btnToFront.setEnabled(false);
		btnToFront.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				controller.ToFront();
			}
		});
		btnToFront.setBackground(Color.YELLOW);
		btnToFront.setForeground(Color.BLACK);
		panel_1.add(btnToFront);
		
		btnToBack = new JButton("To back");
		btnToBack.setEnabled(false);
		btnToBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				controller.ToBack();
			}
		});
		btnToBack.setBackground(Color.YELLOW);
		panel_1.add(btnToBack);
		
		btnBringToFront = new JButton("Bring to front");
		btnBringToFront.setEnabled(false);
		btnBringToFront.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				controller.BringToFront();
			}
		});
		btnBringToFront.setBackground(Color.YELLOW);
		panel_1.add(btnBringToFront);
		
		btnBringToBack = new JButton("Bring to back");
		btnBringToBack.setEnabled(false);
		btnBringToBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				controller.BringToBack();
				
			}
		});
		btnBringToBack.setBackground(Color.YELLOW);
		panel_1.add(btnBringToBack);

	}

	public DefaultListModel<String> getList() {
		return dlmList;
	}

	public JButton getBtnSaveDrawing() {
		return btnSaveDrawing;
	}

	public void setBtnSaveDrawing(JButton btnSaveDrawing) {
		this.btnSaveDrawing = btnSaveDrawing;
	}

	public JButton getBtnImportDrawing() {
		return btnImportDrawing;
	}

	public void setBtnImportDrawing(JButton btnImportDrawing) {
		this.btnImportDrawing = btnImportDrawing;
	}

	public JButton getBtnUndo() {
		return btnUndo;
	}

	public void setBtnUndo(JButton btnUndo) {
		this.btnUndo = btnUndo;
	}

	public JButton getBtnRedo() {
		return btnRedo;
	}

	public void setBtnRedo(JButton btnRedo) {
		this.btnRedo = btnRedo;
	}

	public JButton getBtnToFront() {
		return btnToFront;
	}

	public void setBtnToFront(JButton btnToFront) {
		this.btnToFront = btnToFront;
	}

	public JButton getBtnToBack() {
		return btnToBack;
	}

	public void setBtnToBack(JButton btnToBack) {
		this.btnToBack = btnToBack;
	}

	public JButton getBtnBringToFront() {
		return btnBringToFront;
	}

	public void setBtnBringToFront(JButton btnBringToFront) {
		this.btnBringToFront = btnBringToFront;
	}

	public JButton getBtnBringToBack() {
		return btnBringToBack;
	}

	public void setBtnBringToBack(JButton btnBringToBack) {
		this.btnBringToBack = btnBringToBack;
	}

	public JToggleButton getTglbtnPoint() {
		return tglbtnPoint;
	}

	public JToggleButton getTglbtnSelect() {
		return tglbtnSelect;
	}

	public void setTglbtnPoint(JToggleButton tglbtnPoint) {
		this.tglbtnPoint = tglbtnPoint;
	}

	public JToggleButton getTglbtnLine() {
		return tglbtnLine;
	}

	public void setTglbtnLine(JToggleButton tglbtnLine) {
		this.tglbtnLine = tglbtnLine;
	}

	public JToggleButton getTglbtnCircle() {
		return tglbtnCircle;
	}

	public void setTglbtnCircle(JToggleButton tglbtnCircle) {
		this.tglbtnCircle = tglbtnCircle;
	}

	public JToggleButton getTglbtnHexagon() {
		return tglbtnHexagon;
	}

	public void setTglbtnHexagon(JToggleButton tglbtnHexagon) {
		this.tglbtnCircle = tglbtnHexagon;
	}

	public JToggleButton getTglbtnRectangle() {
		return tglbtnRectangle;
	}

	public void setTglbtnRectangle(JToggleButton tglbtnRectangle) {
		this.tglbtnRectangle = tglbtnRectangle;
	}

	public JToggleButton getTglbtnDonut() {
		return tglbtnDonut;
	}

	public void setTglbtnDonut(JToggleButton tglbtnDonut) {
		this.tglbtnDonut = tglbtnDonut;
	}

	public JButton getBtnDelete() {
		return btnDelete;
	}

	public void setBtnDelete(JButton btnDelete) {
		this.btnDelete = btnDelete;
	}

	public JButton getBtnModify() {
		return btnModify;
	}

	public void setBtnModify(JButton btnModify) {
		this.btnModify = btnModify;
	}

	public DrawingView getView() {
		return view;
	}

	public void setController(DrawingController controller) {
		this.controller = controller;
	}

//	private class SwingAction extends AbstractAction {
//		public SwingAction() {
//			putValue(NAME, "SwingAction");
//			putValue(SHORT_DESCRIPTION, "Some short description");
//	}

		public void actionPerformed(ActionEvent e) {
		}
	

}