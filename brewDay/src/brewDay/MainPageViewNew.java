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
import javax.swing.table.DefaultTableModel;

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
import javax.swing.JTextArea;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

import java.awt.Panel;
import java.awt.ScrollPane;
import javax.swing.JInternalFrame;

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
		frame.setBounds(100, 100, 1010, 764);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		SpringLayout springLayout = new SpringLayout();
		frame.getContentPane().setLayout(springLayout);

		JPanel equipPanel = new JPanel();
		springLayout.putConstraint(SpringLayout.NORTH, equipPanel, 20, SpringLayout.NORTH, frame.getContentPane());
		springLayout.putConstraint(SpringLayout.WEST, equipPanel, 10, SpringLayout.WEST, frame.getContentPane());
		springLayout.putConstraint(SpringLayout.SOUTH, equipPanel, -27, SpringLayout.SOUTH, frame.getContentPane());
		equipPanel.setBackground(new Color(245, 222, 179));
		equipPanel.setBorder(new LineBorder(new Color(0, 0, 0)));
		frame.getContentPane().add(equipPanel);
		SpringLayout sl_equipPanel = new SpringLayout();
		equipPanel.setLayout(sl_equipPanel);

		JButton btnWhatToBrew = new JButton("what to brew today");
		sl_equipPanel.putConstraint(SpringLayout.NORTH, btnWhatToBrew, 10, SpringLayout.NORTH, equipPanel);
		sl_equipPanel.putConstraint(SpringLayout.WEST, btnWhatToBrew, 10, SpringLayout.WEST, equipPanel);
		sl_equipPanel.putConstraint(SpringLayout.EAST, btnWhatToBrew, -1, SpringLayout.EAST, equipPanel);
		btnWhatToBrew.setBackground(new Color(255, 215, 0));
		btnWhatToBrew.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				// create the new view to input batch size
				InputBatchSizeView brew = new InputBatchSizeView(w);
				w.addListener(brew);

			}
		});
		btnWhatToBrew.setFont(new Font("Tahoma", Font.BOLD, 17));
		equipPanel.add(btnWhatToBrew);

		JPanel recipePanel = new JPanel();
		springLayout.putConstraint(SpringLayout.EAST, equipPanel, -7, SpringLayout.WEST, recipePanel);
		springLayout.putConstraint(SpringLayout.SOUTH, recipePanel, 452, SpringLayout.NORTH, frame.getContentPane());
		springLayout.putConstraint(SpringLayout.EAST, recipePanel, 637, SpringLayout.WEST, frame.getContentPane());
		springLayout.putConstraint(SpringLayout.NORTH, recipePanel, 0, SpringLayout.NORTH, frame.getContentPane());
		springLayout.putConstraint(SpringLayout.WEST, recipePanel, 297, SpringLayout.WEST, frame.getContentPane());
		recipePanel.setBackground(new Color(245, 222, 179));
		recipePanel.setBorder(new LineBorder(new Color(0, 0, 0)));
		frame.getContentPane().add(recipePanel);

		// recipe update
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
		SpringLayout sl_recipePanel = new SpringLayout();
		sl_recipePanel.putConstraint(SpringLayout.NORTH, btnUpdate, 11, SpringLayout.NORTH, recipePanel);
		sl_recipePanel.putConstraint(SpringLayout.WEST, btnUpdate, 10, SpringLayout.WEST, recipePanel);
		sl_recipePanel.putConstraint(SpringLayout.SOUTH, btnUpdate, 65, SpringLayout.NORTH, recipePanel);
		sl_recipePanel.putConstraint(SpringLayout.EAST, btnUpdate, 114, SpringLayout.WEST, recipePanel);
		recipePanel.setLayout(sl_recipePanel);
		btnUpdate.setFont(new Font("Tahoma", Font.BOLD, 17));
		recipePanel.add(btnUpdate);

		JButton recipeDelete = new JButton("delete");
		sl_recipePanel.putConstraint(SpringLayout.NORTH, recipeDelete, 11, SpringLayout.NORTH, recipePanel);
		sl_recipePanel.putConstraint(SpringLayout.WEST, recipeDelete, 225, SpringLayout.WEST, recipePanel);
		sl_recipePanel.putConstraint(SpringLayout.SOUTH, recipeDelete, 65, SpringLayout.NORTH, recipePanel);
		sl_recipePanel.putConstraint(SpringLayout.EAST, recipeDelete, 329, SpringLayout.WEST, recipePanel);
		recipeDelete.setBackground(new Color(255, 140, 0));
		recipeDelete.setForeground(new Color(255, 255, 255));
		recipeDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		recipeDelete.setFont(new Font("Tahoma", Font.BOLD, 17));
		recipePanel.add(recipeDelete);

		JButton recipeAdd = new JButton("add");
		sl_recipePanel.putConstraint(SpringLayout.NORTH, recipeAdd, 11, SpringLayout.NORTH, recipePanel);
		sl_recipePanel.putConstraint(SpringLayout.WEST, recipeAdd, 123, SpringLayout.WEST, recipePanel);
		sl_recipePanel.putConstraint(SpringLayout.SOUTH, recipeAdd, 65, SpringLayout.NORTH, recipePanel);
		sl_recipePanel.putConstraint(SpringLayout.EAST, recipeAdd, 209, SpringLayout.WEST, recipePanel);
		recipeAdd.setBackground(new Color(255, 140, 0));
		recipeAdd.setForeground(new Color(255, 255, 255));
		recipeAdd.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				new AddRecipeView(w);
			}
		});
		recipeAdd.setFont(new Font("Tahoma", Font.BOLD, 17));
		recipePanel.add(recipeAdd);

		/********** recipe name combo **************/
		String[] recipeNames = ((RecipeController) controllers.get(2)).getRecipeNames();
		JComboBox recipeNameCombo = new JComboBox(recipeNames);
		sl_recipePanel.putConstraint(SpringLayout.SOUTH, recipeNameCombo, -278, SpringLayout.SOUTH, recipePanel);

		for (int i = 0; i < recipeNames.length; i++) {
			System.out.println(recipeNames[i]);
		}
		sl_recipePanel.putConstraint(SpringLayout.WEST, recipeNameCombo, 0, SpringLayout.WEST, btnUpdate);
		sl_recipePanel.putConstraint(SpringLayout.EAST, recipeNameCombo, -1, SpringLayout.EAST, recipeDelete);
		recipePanel.add(recipeNameCombo);

		JLabel selectRecipeLabel = new JLabel("Please select a recipe");
		sl_recipePanel.putConstraint(SpringLayout.SOUTH, selectRecipeLabel, -322, SpringLayout.SOUTH, recipePanel);
		sl_recipePanel.putConstraint(SpringLayout.NORTH, recipeNameCombo, 6, SpringLayout.SOUTH, selectRecipeLabel);
		sl_recipePanel.putConstraint(SpringLayout.WEST, selectRecipeLabel, 10, SpringLayout.WEST, recipePanel);
		selectRecipeLabel.setFont(new Font("Calibri", Font.PLAIN, 18));
		recipePanel.add(selectRecipeLabel);

		/********** recipe scroll panel *************/
		ScrollPane recipeScroll = new ScrollPane();
		sl_recipePanel.putConstraint(SpringLayout.NORTH, recipeScroll, -199, SpringLayout.SOUTH, recipePanel);
		sl_recipePanel.putConstraint(SpringLayout.WEST, recipeScroll, 4, SpringLayout.WEST, btnUpdate);
		sl_recipePanel.putConstraint(SpringLayout.SOUTH, recipeScroll, -5, SpringLayout.SOUTH, recipePanel);
		sl_recipePanel.putConstraint(SpringLayout.EAST, recipeScroll, -6, SpringLayout.EAST, recipePanel);
		recipePanel.add(recipeScroll);

		

		JPanel panel_3 = new JPanel();
		springLayout.putConstraint(SpringLayout.NORTH, panel_3, 0, SpringLayout.NORTH, frame.getContentPane());
		springLayout.putConstraint(SpringLayout.WEST, panel_3, 646, SpringLayout.WEST, frame.getContentPane());
		springLayout.putConstraint(SpringLayout.SOUTH, panel_3, 452, SpringLayout.NORTH, frame.getContentPane());
		springLayout.putConstraint(SpringLayout.EAST, panel_3, 986, SpringLayout.WEST, frame.getContentPane());
		panel_3.setBackground(new Color(245, 222, 179));
		panel_3.setBorder(new LineBorder(new Color(0, 0, 0)));
		frame.getContentPane().add(panel_3);

		// note update
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

		// note delete
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

		// note add
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

		JPanel ingrePanel = new JPanel();
		springLayout.putConstraint(SpringLayout.WEST, ingrePanel, 19, SpringLayout.EAST, equipPanel);
		springLayout.putConstraint(SpringLayout.SOUTH, ingrePanel, -38, SpringLayout.SOUTH, frame.getContentPane());
		springLayout.putConstraint(SpringLayout.EAST, ingrePanel, -18, SpringLayout.EAST, frame.getContentPane());
		frame.getContentPane().add(ingrePanel);

		JButton ingreDelete = new JButton("Delete");
		springLayout.putConstraint(SpringLayout.NORTH, ingrePanel, 6, SpringLayout.SOUTH, ingreDelete);
		springLayout.putConstraint(SpringLayout.NORTH, ingreDelete, 33, SpringLayout.SOUTH, panel_3);
		ingreDelete.setFont(new Font("Calibri", Font.PLAIN, 22));
		ingreDelete.setForeground(new Color(255, 140, 0));
		frame.getContentPane().add(ingreDelete);

		JButton ingreUpdate = new JButton("Update");
		springLayout.putConstraint(SpringLayout.NORTH, ingreUpdate, 33, SpringLayout.SOUTH, panel_3);
		
		ScrollPane noteScroll = new ScrollPane();
		sl_panel_3.putConstraint(SpringLayout.NORTH, noteScroll, -318, SpringLayout.SOUTH, panel_3);
		sl_panel_3.putConstraint(SpringLayout.WEST, noteScroll, 19, SpringLayout.WEST, panel_3);
		sl_panel_3.putConstraint(SpringLayout.SOUTH, noteScroll, -18, SpringLayout.SOUTH, panel_3);
		sl_panel_3.putConstraint(SpringLayout.EAST, noteScroll, 310, SpringLayout.WEST, panel_3);
		panel_3.add(noteScroll);
		springLayout.putConstraint(SpringLayout.WEST, ingreUpdate, 16, SpringLayout.EAST, ingreDelete);
		ingreUpdate.setForeground(new Color(255, 140, 0));
		ingreUpdate.setFont(new Font("Calibri", Font.PLAIN, 22));
		frame.getContentPane().add(ingreUpdate);

		JLabel lblStorageIngredients = new JLabel("Storage Ingredients");
		springLayout.putConstraint(SpringLayout.NORTH, lblStorageIngredients, 2, SpringLayout.NORTH, ingreDelete);
		springLayout.putConstraint(SpringLayout.WEST, lblStorageIngredients, 22, SpringLayout.EAST, equipPanel);

		/******* set equipment table ************/
		// JScrollPane equipSPane = new JScrollPane();
		// frame.getContentPane().add(equipSPane);
		Object[][] equipData = ((EquipmentController) controllers.get(1)).getData();
		String[] equipColNames = ((EquipmentController) controllers.get(1)).getColNames();
		TablePane equipInfoPane = new TablePane(controllers.get(1), equipData, equipColNames);
		sl_equipPanel.putConstraint(SpringLayout.NORTH, equipInfoPane, 143, SpringLayout.NORTH, equipPanel);
		sl_equipPanel.putConstraint(SpringLayout.WEST, equipInfoPane, 10, SpringLayout.WEST, equipPanel);
		sl_equipPanel.putConstraint(SpringLayout.SOUTH, equipInfoPane, -11, SpringLayout.SOUTH, equipPanel);
		sl_equipPanel.putConstraint(SpringLayout.EAST, equipInfoPane, -10, SpringLayout.EAST, equipPanel);
		equipPanel.add(equipInfoPane);

		JButton equipAdd = new JButton("Add");
		sl_equipPanel.putConstraint(SpringLayout.SOUTH, btnWhatToBrew, -43, SpringLayout.NORTH, equipAdd);
		sl_equipPanel.putConstraint(SpringLayout.WEST, equipAdd, 0, SpringLayout.WEST, btnWhatToBrew);
		sl_equipPanel.putConstraint(SpringLayout.SOUTH, equipAdd, -6, SpringLayout.NORTH, equipInfoPane);
		equipAdd.setBackground(new Color(255, 127, 80));
		equipAdd.setForeground(new Color(255, 255, 255));
		equipAdd.setFont(new Font("Calibri", Font.PLAIN, 15));
		equipPanel.add(equipAdd);

		equipAdd.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				AddEquipmentView addEquip = new AddEquipmentView(w, (EquipmentController) controllers.get(1));
				w.addListener(addEquip);

			}

		});

		JButton equipUpdate = new JButton("Update");
		sl_equipPanel.putConstraint(SpringLayout.WEST, equipUpdate, 23, SpringLayout.EAST, equipAdd);
		sl_equipPanel.putConstraint(SpringLayout.SOUTH, equipUpdate, -6, SpringLayout.NORTH, equipInfoPane);
		equipUpdate.setBackground(new Color(255, 127, 80));
		equipUpdate.setForeground(new Color(255, 255, 255));
		equipUpdate.setFont(new Font("Calibri", Font.PLAIN, 15));
		equipPanel.add(equipUpdate);

		equipUpdate.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				UpdateEquipmentView updateEquip = new UpdateEquipmentView(w, (EquipmentController) controllers.get(1),
						equipData);
				w.addListener(updateEquip);

			}

		});

		JButton equipDelete = new JButton("Delete");
		sl_equipPanel.putConstraint(SpringLayout.SOUTH, equipDelete, -6, SpringLayout.NORTH, equipInfoPane);
		sl_equipPanel.putConstraint(SpringLayout.EAST, equipDelete, 0, SpringLayout.EAST, equipInfoPane);
		equipDelete.setBackground(new Color(255, 127, 80));
		equipDelete.setForeground(new Color(255, 255, 255));
		equipDelete.setFont(new Font("Calibri", Font.PLAIN, 15));
		equipPanel.add(equipDelete);

		equipDelete.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				DeleteEquipView deleteEquip = new DeleteEquipView(w, (EquipmentController) controllers.get(1),
						equipData);
				w.addListener(deleteEquip);

			}

		});

		lblStorageIngredients.setFont(new Font("Calibri", Font.PLAIN, 24));
		lblStorageIngredients.setForeground(new Color(255, 127, 80));
		frame.getContentPane().add(lblStorageIngredients);

		JButton ingreAdd = new JButton("Add");
		springLayout.putConstraint(SpringLayout.EAST, ingreAdd, -248, SpringLayout.EAST, frame.getContentPane());
		springLayout.putConstraint(SpringLayout.WEST, ingreDelete, 20, SpringLayout.EAST, ingreAdd);
		springLayout.putConstraint(SpringLayout.SOUTH, ingreAdd, -6, SpringLayout.NORTH, ingrePanel);
		ingreAdd.setForeground(new Color(255, 127, 80));
		ingreAdd.setFont(new Font("Calibri", Font.PLAIN, 22));
		frame.getContentPane().add(ingreAdd);

		ingreAdd.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				AddStorIngreView addIngre = new AddStorIngreView(w, (StorageIngredientController) controllers.get(0));
				w.addListener(addIngre);

			}

		});

		/********* set ingredient table **************/
		JScrollPane StorageIngrePane = new JScrollPane();
		frame.getContentPane().add(StorageIngrePane);
		SpringLayout sl_ingrePanel = new SpringLayout();
		ingrePanel.setLayout(sl_ingrePanel);

		String[] colNames = ((StorageIngredientController) controllers.get(0)).getColNames();
		Object[][] ingreData = ((StorageIngredientController) controllers.get(0)).getData();
		TablePane ingreInfoPane = new TablePane(controllers.get(0), ingreData, colNames);
		sl_ingrePanel.putConstraint(SpringLayout.WEST, ingreInfoPane, 0, SpringLayout.WEST, ingrePanel);
		sl_ingrePanel.putConstraint(SpringLayout.EAST, ingreInfoPane, 661, SpringLayout.WEST, ingrePanel);

		for (int i = 0; i < ingreData.length; i++) {
			System.out.println((String) ingreData[i][1]);
		}

		ingreUpdate.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				UpdateStorIngreView updateIngreWindow = new UpdateStorIngreView(w,
						(StorageIngredientController) controllers.get(0), ingreData);
				w.addListener(updateIngreWindow);

			}

		});

		ingreDelete.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				DeleteIngreView deleteIngreWindow = new DeleteIngreView(w,
						(StorageIngredientController) controllers.get(0), ingreData);
				w.addListener(deleteIngreWindow);

			}

		});
		ingrePanel.add(ingreInfoPane);

		sl_ingrePanel.putConstraint(SpringLayout.NORTH, ingreInfoPane, 0, SpringLayout.NORTH, ingrePanel);
		sl_ingrePanel.putConstraint(SpringLayout.SOUTH, ingreInfoPane, 0, SpringLayout.SOUTH, ingrePanel);
		springLayout.putConstraint(SpringLayout.NORTH, ingreInfoPane, 0, SpringLayout.NORTH, ingrePanel);
		springLayout.putConstraint(SpringLayout.WEST, ingreInfoPane, 0, SpringLayout.WEST, ingrePanel);
		springLayout.putConstraint(SpringLayout.SOUTH, ingreInfoPane, 10, SpringLayout.SOUTH, ingrePanel);
		springLayout.putConstraint(SpringLayout.EAST, ingreInfoPane, 1426, SpringLayout.EAST, ingrePanel);

		JButton refreshIngre = new JButton("Refresh");
		refreshIngre.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

			}
		});
		refreshIngre.setForeground(new Color(255, 255, 255));
		refreshIngre.setBackground(new Color(255, 127, 80));
		refreshIngre.setFont(new Font("Calibri", Font.PLAIN, 15));
		springLayout.putConstraint(SpringLayout.SOUTH, refreshIngre, 0, SpringLayout.SOUTH, lblStorageIngredients);
		springLayout.putConstraint(SpringLayout.EAST, refreshIngre, -25, SpringLayout.WEST, ingreAdd);
		frame.getContentPane().add(refreshIngre);
		
		
		//	the recipe and note shown in main page
		recipeNameCombo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println("Selected index=" + ((JComboBox) e.getSource()).getItemCount());

				// create a table panel which show the recipe ingredient in selected recipe and
				// add it to scroll panel
				Object[][] recipeIngreData = ((RecipeController) controllers.get(2))
						.getRecipeIngreData((String) recipeNameCombo.getSelectedItem());
				String[] recipeIngreCol = ((RecipeController) controllers.get(2)).getRecipeIngreColNames();
				TablePane recipeInfoPane = new TablePane(controllers.get(1), recipeIngreData, recipeIngreCol);
				recipeScroll.add(recipeInfoPane);
				
				String[] notes = ((NoteController) controllers.get(3))
						.getNote((String)recipeNameCombo.getSelectedItem());
				
				//	prepare the string buffer to store all note contents
				StringBuffer sBuffer = new StringBuffer("Notes:\r\n");

			    System.out.println(sBuffer);
				
				
				//	combine all content together
				for(int i = 0; i < notes.length; i++) {
					sBuffer.append("No." + i + "\r\n");
					sBuffer.append(notes[i]);
					sBuffer.append("\r\n");
				}
				
				//	prepare the text area
				JTextArea noteText = new JTextArea(sBuffer.toString());
				//Automatically change line
				noteText.setLineWrap(true);
				noteText.setWrapStyleWord(true);
				noteText.setEditable(false);
				noteScroll.add(noteText);
				
			}
		});

		// TableDemo table = new TableDemo();

		// table = new JTable();
		// equipPane.setViewportView(table);

	}

	@Override
	public void update() {
		// TODO Auto-generated method stub

	}
}