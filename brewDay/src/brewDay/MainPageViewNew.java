package brewDay;

import java.awt.EventQueue;
import javax.swing.JFrame;
import java.awt.GridLayout;
import java.awt.FlowLayout;
import java.awt.BorderLayout;
import javax.swing.JPanel;
import javax.swing.JSplitPane;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTextPane;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import javax.swing.JComboBox;
import java.awt.TextArea;
import javax.swing.JScrollPane;
import javax.swing.SpringLayout;
import javax.swing.JTable;
import javax.swing.JLabel;

public class MainPageViewNew extends View {
	private ArrayList<Controller> controllers;
	private JFrame frame;
	private JTable table;

	/**
	 * Launch the application.
	 */
	/*
	 * public static void main(String[] args) { EventQueue.invokeLater(new
	 * Runnable() { public void run() { try { MainPageView window = new
	 * MainPageView(); } catch (Exception e) { e.printStackTrace(); } } }); }
	 */
	/**
	 * Create the application.
	 */
	public MainPageViewNew(Workbench w, ArrayList<Controller> controller) {
		super(w);
		this.controllers = controller;
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.getContentPane().setBackground(new Color(245, 222, 179));
		frame.setVisible(true);
		frame.setBounds(100, 100, 1010, 815);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		SpringLayout springLayout = new SpringLayout();
		frame.getContentPane().setLayout(springLayout);

		JPanel panel = new JPanel();
		springLayout.putConstraint(SpringLayout.NORTH, panel, 10, SpringLayout.NORTH, frame.getContentPane());
		springLayout.putConstraint(SpringLayout.WEST, panel, 4, SpringLayout.WEST, frame.getContentPane());
		springLayout.putConstraint(SpringLayout.SOUTH, panel, -38, SpringLayout.SOUTH, frame.getContentPane());
		panel.setBackground(new Color(245, 222, 179));
		panel.setBorder(new LineBorder(new Color(0, 0, 0)));
		frame.getContentPane().add(panel);
		SpringLayout sl_panel = new SpringLayout();
		panel.setLayout(sl_panel);

		
		//	equipment text area
		TextArea textArea = new TextArea();
		sl_panel.putConstraint(SpringLayout.SOUTH, textArea, 707, SpringLayout.NORTH, panel);
		sl_panel.putConstraint(SpringLayout.EAST, textArea, 274, SpringLayout.WEST, panel);
		textArea.setFont(new Font("Arial", Font.PLAIN, 12));
		textArea.setText("equipment");
		panel.add(textArea);

		//	equipment button add
		JButton btnAdd = new JButton("add");
		sl_panel.putConstraint(SpringLayout.NORTH, textArea, 6, SpringLayout.SOUTH, btnAdd);
		sl_panel.putConstraint(SpringLayout.WEST, textArea, 0, SpringLayout.WEST, btnAdd);
		sl_panel.putConstraint(SpringLayout.NORTH, btnAdd, 79, SpringLayout.NORTH, panel);
		sl_panel.putConstraint(SpringLayout.WEST, btnAdd, 10, SpringLayout.WEST, panel);
		sl_panel.putConstraint(SpringLayout.SOUTH, btnAdd, 120, SpringLayout.NORTH, panel);
		sl_panel.putConstraint(SpringLayout.EAST, btnAdd, 123, SpringLayout.WEST, panel);
		btnAdd.setBackground(new Color(255, 140, 0));
		btnAdd.setForeground(new Color(255, 255, 255));
		btnAdd.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
				InputNameCapacityTextBox addEquip = new InputNameCapacityTextBox(w);
				w.addListener(addEquip);
			}
		});
		btnAdd.setFont(new Font("Tahoma", Font.BOLD, 17));
		panel.add(btnAdd);

		//	equipment button modify
		JButton btnModify = new JButton("update");
		sl_panel.putConstraint(SpringLayout.NORTH, btnModify, 79, SpringLayout.NORTH, panel);
		sl_panel.putConstraint(SpringLayout.WEST, btnModify, 164, SpringLayout.WEST, panel);
		sl_panel.putConstraint(SpringLayout.SOUTH, btnModify, 120, SpringLayout.NORTH, panel);
		sl_panel.putConstraint(SpringLayout.EAST, btnModify, 277, SpringLayout.WEST, panel);
		btnModify.setBackground(new Color(255, 140, 0));
		btnModify.setForeground(new Color(255, 255, 255));
		btnModify.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				new EnquipmentUpdateView(w);
			}
		});
		btnModify.setFont(new Font("Tahoma", Font.BOLD, 17));
		panel.add(btnModify);

		JButton btnWhatToBrew = new JButton("what to brew today");
		sl_panel.putConstraint(SpringLayout.NORTH, btnWhatToBrew, 11, SpringLayout.NORTH, panel);
		sl_panel.putConstraint(SpringLayout.WEST, btnWhatToBrew, 10, SpringLayout.WEST, panel);
		sl_panel.putConstraint(SpringLayout.SOUTH, btnWhatToBrew, 68, SpringLayout.NORTH, panel);
		sl_panel.putConstraint(SpringLayout.EAST, btnWhatToBrew, 277, SpringLayout.WEST, panel);
		btnWhatToBrew.setBackground(new Color(255, 215, 0));
		btnWhatToBrew.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				//	create the new view to input batch size
				InputBatchSizeView brew = new InputBatchSizeView(w);
				w.addListener(brew);
				
			}
		});
		btnWhatToBrew.setFont(new Font("Tahoma", Font.BOLD, 17));
		panel.add(btnWhatToBrew);

		JPanel panel_2 = new JPanel();
		springLayout.putConstraint(SpringLayout.EAST, panel, -6, SpringLayout.WEST, panel_2);
		springLayout.putConstraint(SpringLayout.SOUTH, panel_2, 452, SpringLayout.NORTH, frame.getContentPane());
		springLayout.putConstraint(SpringLayout.EAST, panel_2, 637, SpringLayout.WEST, frame.getContentPane());
		springLayout.putConstraint(SpringLayout.NORTH, panel_2, 0, SpringLayout.NORTH, frame.getContentPane());
		springLayout.putConstraint(SpringLayout.WEST, panel_2, 297, SpringLayout.WEST, frame.getContentPane());
		panel_2.setBackground(new Color(245, 222, 179));
		panel_2.setBorder(new LineBorder(new Color(0, 0, 0)));
		frame.getContentPane().add(panel_2);

		
		//	recipe update
		JButton btnUpdate = new JButton("update");
		btnUpdate.setBackground(new Color(255, 140, 0));
		btnUpdate.setForeground(new Color(255, 255, 255));
		btnUpdate.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				new UpdateListView(w);
			}
		});
		btnUpdate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
			}
		});
		SpringLayout sl_panel_2 = new SpringLayout();
		sl_panel_2.putConstraint(SpringLayout.NORTH, btnUpdate, 11, SpringLayout.NORTH, panel_2);
		sl_panel_2.putConstraint(SpringLayout.WEST, btnUpdate, 10, SpringLayout.WEST, panel_2);
		sl_panel_2.putConstraint(SpringLayout.SOUTH, btnUpdate, 65, SpringLayout.NORTH, panel_2);
		sl_panel_2.putConstraint(SpringLayout.EAST, btnUpdate, 114, SpringLayout.WEST, panel_2);
		panel_2.setLayout(sl_panel_2);
		btnUpdate.setFont(new Font("Tahoma", Font.BOLD, 17));
		panel_2.add(btnUpdate);

		JButton recipeDelete = new JButton("delete");
		sl_panel_2.putConstraint(SpringLayout.NORTH, recipeDelete, 11, SpringLayout.NORTH, panel_2);
		sl_panel_2.putConstraint(SpringLayout.WEST, recipeDelete, 225, SpringLayout.WEST, panel_2);
		sl_panel_2.putConstraint(SpringLayout.SOUTH, recipeDelete, 65, SpringLayout.NORTH, panel_2);
		sl_panel_2.putConstraint(SpringLayout.EAST, recipeDelete, 329, SpringLayout.WEST, panel_2);
		recipeDelete.setBackground(new Color(255, 140, 0));
		recipeDelete.setForeground(new Color(255, 255, 255));
		recipeDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		recipeDelete.setFont(new Font("Tahoma", Font.BOLD, 17));
		panel_2.add(recipeDelete);

		JButton recipeAdd = new JButton("add");
		sl_panel_2.putConstraint(SpringLayout.NORTH, recipeAdd, 11, SpringLayout.NORTH, panel_2);
		sl_panel_2.putConstraint(SpringLayout.WEST, recipeAdd, 123, SpringLayout.WEST, panel_2);
		sl_panel_2.putConstraint(SpringLayout.SOUTH, recipeAdd, 65, SpringLayout.NORTH, panel_2);
		sl_panel_2.putConstraint(SpringLayout.EAST, recipeAdd, 209, SpringLayout.WEST, panel_2);
		recipeAdd.setBackground(new Color(255, 140, 0));
		recipeAdd.setForeground(new Color(255, 255, 255));
		recipeAdd.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				new AddRecipeView(w);
			}
		});
		recipeAdd.setFont(new Font("Tahoma", Font.BOLD, 17));
		panel_2.add(recipeAdd);

		//	recipe text area
		TextArea textArea_1 = new TextArea();
		sl_panel_2.putConstraint(SpringLayout.NORTH, textArea_1, 71, SpringLayout.NORTH, panel_2);
		sl_panel_2.putConstraint(SpringLayout.WEST, textArea_1, 10, SpringLayout.WEST, panel_2);
		sl_panel_2.putConstraint(SpringLayout.SOUTH, textArea_1, 442, SpringLayout.NORTH, panel_2);
		sl_panel_2.putConstraint(SpringLayout.EAST, textArea_1, 329, SpringLayout.WEST, panel_2);
		textArea_1.setText("recipe");
		textArea_1.setFont(new Font("Arial", Font.PLAIN, 12));
		panel_2.add(textArea_1);

		JPanel panel_3 = new JPanel();
		springLayout.putConstraint(SpringLayout.NORTH, panel_3, 0, SpringLayout.NORTH, frame.getContentPane());
		springLayout.putConstraint(SpringLayout.WEST, panel_3, 646, SpringLayout.WEST, frame.getContentPane());
		springLayout.putConstraint(SpringLayout.SOUTH, panel_3, 452, SpringLayout.NORTH, frame.getContentPane());
		springLayout.putConstraint(SpringLayout.EAST, panel_3, 986, SpringLayout.WEST, frame.getContentPane());
		panel_3.setBackground(new Color(245, 222, 179));
		panel_3.setBorder(new LineBorder(new Color(0, 0, 0)));
		frame.getContentPane().add(panel_3);

		
		//	note update
		JButton button_1 = new JButton("update");
		button_1.setBackground(new Color(255, 140, 0));
		button_1.setForeground(new Color(255, 255, 255));
		button_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				new NoteListView(w);
			}
		});
		SpringLayout sl_panel_3 = new SpringLayout();
		sl_panel_3.putConstraint(SpringLayout.NORTH, button_1, 11, SpringLayout.NORTH, panel_3);
		sl_panel_3.putConstraint(SpringLayout.WEST, button_1, 10, SpringLayout.WEST, panel_3);
		sl_panel_3.putConstraint(SpringLayout.SOUTH, button_1, 65, SpringLayout.NORTH, panel_3);
		sl_panel_3.putConstraint(SpringLayout.EAST, button_1, 114, SpringLayout.WEST, panel_3);
		panel_3.setLayout(sl_panel_3);
		button_1.setFont(new Font("Tahoma", Font.BOLD, 17));
		panel_3.add(button_1);

		
		//	note delete
		JButton button_2 = new JButton("delete");
		sl_panel_3.putConstraint(SpringLayout.NORTH, button_2, 11, SpringLayout.NORTH, panel_3);
		sl_panel_3.putConstraint(SpringLayout.WEST, button_2, 225, SpringLayout.WEST, panel_3);
		sl_panel_3.putConstraint(SpringLayout.SOUTH, button_2, 65, SpringLayout.NORTH, panel_3);
		sl_panel_3.putConstraint(SpringLayout.EAST, button_2, 329, SpringLayout.WEST, panel_3);
		button_2.setBackground(new Color(255, 140, 0));
		button_2.setForeground(new Color(255, 255, 255));
		button_2.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				new DeleteRecipeListView(w);
			}
		});
		button_2.setFont(new Font("Tahoma", Font.BOLD, 17));
		panel_3.add(button_2);

		
		//	note add
		JButton button_3 = new JButton("add");
		sl_panel_3.putConstraint(SpringLayout.NORTH, button_3, 11, SpringLayout.NORTH, panel_3);
		sl_panel_3.putConstraint(SpringLayout.WEST, button_3, 123, SpringLayout.WEST, panel_3);
		sl_panel_3.putConstraint(SpringLayout.SOUTH, button_3, 65, SpringLayout.NORTH, panel_3);
		sl_panel_3.putConstraint(SpringLayout.EAST, button_3, 209, SpringLayout.WEST, panel_3);
		button_3.setBackground(new Color(255, 140, 0));
		button_3.setForeground(new Color(255, 255, 255));
		button_3.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				new AddNoteView(w);
			}
		});
		button_3.setFont(new Font("Tahoma", Font.BOLD, 17));
		panel_3.add(button_3);
		
		//	note text area
		TextArea textArea_2 = new TextArea();
		sl_panel_3.putConstraint(SpringLayout.NORTH, textArea_2, 71, SpringLayout.NORTH, panel_3);
		sl_panel_3.putConstraint(SpringLayout.WEST, textArea_2, 10, SpringLayout.WEST, panel_3);
		sl_panel_3.putConstraint(SpringLayout.SOUTH, textArea_2, 449, SpringLayout.NORTH, panel_3);
		sl_panel_3.putConstraint(SpringLayout.EAST, textArea_2, 337, SpringLayout.WEST, panel_3);
		textArea_2.setText("recipe");
		textArea_2.setFont(new Font("Arial", Font.PLAIN, 12));
		panel_3.add(textArea_2);
		
		JPanel panel_1 = new JPanel();
		springLayout.putConstraint(SpringLayout.WEST, panel_1, 18, SpringLayout.EAST, panel);
		springLayout.putConstraint(SpringLayout.SOUTH, panel_1, 0, SpringLayout.SOUTH, panel);
		springLayout.putConstraint(SpringLayout.EAST, panel_1, -18, SpringLayout.EAST, frame.getContentPane());
		frame.getContentPane().add(panel_1);
		
		JScrollPane IngrePane = new JScrollPane();
		frame.getContentPane().add(IngrePane);
		SpringLayout sl_panel_1 = new SpringLayout();
		panel_1.setLayout(sl_panel_1);
		
		String[] colNames = ((StorageIngredientController)controllers.get(0)).getColNames();
		Object[][] equipData = ((StorageIngredientController)controllers.get(0)).getData(IngrePane);
		IngrePane ingrePane = new IngrePane(controllers.get(0), equipData, colNames);
		sl_panel_1.putConstraint(SpringLayout.WEST, ingrePane, 0, SpringLayout.WEST, panel_1);
		sl_panel_1.putConstraint(SpringLayout.EAST, ingrePane, 661, SpringLayout.WEST, panel_1);
		panel_1.add(ingrePane);
		
		
		sl_panel_1.putConstraint(SpringLayout.NORTH, ingrePane, 0, SpringLayout.NORTH, panel_1);
		sl_panel_1.putConstraint(SpringLayout.SOUTH, ingrePane, 0, SpringLayout.SOUTH, panel_1);
		springLayout.putConstraint(SpringLayout.NORTH, ingrePane, 0, SpringLayout.NORTH, panel_1);
		springLayout.putConstraint(SpringLayout.WEST, ingrePane, 0, SpringLayout.WEST, panel_1);
		springLayout.putConstraint(SpringLayout.SOUTH, ingrePane, 10, SpringLayout.SOUTH, panel_1);
		springLayout.putConstraint(SpringLayout.EAST, ingrePane, 1426, SpringLayout.EAST, panel_1);
		
		

		
		
		JButton ingreDelete = new JButton("Delete");
		springLayout.putConstraint(SpringLayout.NORTH, panel_1, 6, SpringLayout.SOUTH, ingreDelete);
		springLayout.putConstraint(SpringLayout.NORTH, ingreDelete, 33, SpringLayout.SOUTH, panel_3);
		springLayout.putConstraint(SpringLayout.WEST, ingreDelete, 473, SpringLayout.EAST, panel);
		ingreDelete.setFont(new Font("Calibri", Font.PLAIN, 22));
		ingreDelete.setForeground(new Color(255, 140, 0));
		frame.getContentPane().add(ingreDelete);
		
		JButton ingreUpdate = new JButton("Update");
		springLayout.putConstraint(SpringLayout.NORTH, ingreUpdate, 33, SpringLayout.SOUTH, panel_3);
		springLayout.putConstraint(SpringLayout.WEST, ingreUpdate, 16, SpringLayout.EAST, ingreDelete);
		ingreUpdate.setForeground(new Color(255, 140, 0));
		ingreUpdate.setFont(new Font("Calibri", Font.PLAIN, 22));
		frame.getContentPane().add(ingreUpdate);
		
		
		
		
		JLabel lblStorageIngredients = new JLabel("Storage Ingredients");
		springLayout.putConstraint(SpringLayout.NORTH, lblStorageIngredients, 2, SpringLayout.NORTH, ingreDelete);
		springLayout.putConstraint(SpringLayout.WEST, lblStorageIngredients, 21, SpringLayout.EAST, panel);
		lblStorageIngredients.setFont(new Font("Calibri", Font.PLAIN, 24));
		lblStorageIngredients.setForeground(new Color(255, 127, 80));
		frame.getContentPane().add(lblStorageIngredients);
		
		JButton ingreAdd = new JButton("Add");
		springLayout.putConstraint(SpringLayout.SOUTH, ingreAdd, -6, SpringLayout.NORTH, panel_1);
		springLayout.putConstraint(SpringLayout.EAST, ingreAdd, -20, SpringLayout.WEST, ingreDelete);
		ingreAdd.setForeground(new Color(255, 127, 80));
		ingreAdd.setFont(new Font("Calibri", Font.PLAIN, 22));
		frame.getContentPane().add(ingreAdd);
		
		ingreAdd.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				AddStorIngreView addIngre = new AddStorIngreView(w, (StorageIngredientController)controllers.get(0));
				w.addListener(addIngre);
				
			}

		});
		
		
		
		
		
		
	

		
		//TableDemo table = new TableDemo();
		
		
		
		//table = new JTable();
		//equipPane.setViewportView(table);
		
		
	}

	@Override
	public void update() {
		// TODO Auto-generated method stub

	}
}
