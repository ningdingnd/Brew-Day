package brewDay;

import java.awt.EventQueue;
import javax.swing.JFrame;
import java.awt.GridLayout;
import java.awt.FlowLayout;
import java.awt.BorderLayout;
import javax.swing.JPanel;
import javax.swing.JSplitPane;
import javax.swing.BoxLayout;
import javax.swing.ComboBoxModel;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
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
		frame.setBounds(100, 100, 1050, 760);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		SpringLayout springLayout = new SpringLayout();
		frame.getContentPane().setLayout(springLayout);

		JPanel equipPanel = new JPanel();
		springLayout.putConstraint(SpringLayout.NORTH, equipPanel, 20, SpringLayout.NORTH, frame.getContentPane());
		springLayout.putConstraint(SpringLayout.WEST, equipPanel, 10, SpringLayout.WEST, frame.getContentPane());
		springLayout.putConstraint(SpringLayout.SOUTH, equipPanel, -27, SpringLayout.SOUTH, frame.getContentPane());
		equipPanel.setBackground(new Color(245, 222, 179));
		equipPanel.setBorder(null);
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
				InputBatchSizeView brew = new InputBatchSizeView(w, (NoteController) controllers.get(3));
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
		recipePanel.setBorder(null);
		frame.getContentPane().add(recipePanel);

		/********** recipe name combo **************/
		String[] recipeNames = ((RecipeController) controllers.get(2)).getRecipeNames();
		
		
		
		for (int i = 0; i < recipeNames.length; i++) {
			System.out.println(recipeNames[i]);
		}
		
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
		recipePanel.setLayout(sl_recipePanel);
		btnUpdate.setFont(new Font("Calibri", Font.PLAIN, 20));
		recipePanel.add(btnUpdate);

		JButton recipeDelete = new JButton("delete");
		sl_recipePanel.putConstraint(SpringLayout.NORTH, btnUpdate, -3, SpringLayout.NORTH, recipeDelete);
		sl_recipePanel.putConstraint(SpringLayout.WEST, recipeDelete, 233, SpringLayout.WEST, recipePanel);
		recipeDelete.setBackground(new Color(255, 140, 0));
		recipeDelete.setForeground(new Color(255, 255, 255));
		recipeDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		recipeDelete.setFont(new Font("Calibri", Font.PLAIN, 19));
		recipePanel.add(recipeDelete);

		JButton recipeAdd = new JButton("add");
		sl_recipePanel.putConstraint(SpringLayout.EAST, btnUpdate, -3, SpringLayout.WEST, recipeAdd);
		sl_recipePanel.putConstraint(SpringLayout.NORTH, recipeAdd, -1, SpringLayout.NORTH, recipeDelete);
		sl_recipePanel.putConstraint(SpringLayout.WEST, recipeAdd, 147, SpringLayout.WEST, recipePanel);
		sl_recipePanel.putConstraint(SpringLayout.EAST, recipeAdd, -6, SpringLayout.WEST, recipeDelete);
		recipeAdd.setBackground(new Color(255, 140, 0));
		recipeAdd.setForeground(new Color(255, 255, 255));
		
		recipeAdd.setFont(new Font("Calibri", Font.PLAIN, 20));
		recipePanel.add(recipeAdd);


		
		
		recipeAdd.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				new AddRecipeView(w, (RecipeController) controllers.get(2));
			}
		});

		JLabel selectRecipeLabel = new JLabel("Please select a recipe");
		sl_recipePanel.putConstraint(SpringLayout.WEST, btnUpdate, 0, SpringLayout.WEST, selectRecipeLabel);
		sl_recipePanel.putConstraint(SpringLayout.WEST, selectRecipeLabel, 22, SpringLayout.WEST, recipePanel);
		sl_recipePanel.putConstraint(SpringLayout.SOUTH, selectRecipeLabel, -359, SpringLayout.SOUTH, recipePanel);
		selectRecipeLabel.setFont(new Font("Calibri", Font.PLAIN, 18));
		recipePanel.add(selectRecipeLabel);

		/********** recipe scroll panel *************/
		ScrollPane recipeScroll = new ScrollPane();
		sl_recipePanel.putConstraint(SpringLayout.SOUTH, btnUpdate, -54, SpringLayout.NORTH, recipeScroll);
		sl_recipePanel.putConstraint(SpringLayout.EAST, recipeDelete, 0, SpringLayout.EAST, recipeScroll);
		sl_recipePanel.putConstraint(SpringLayout.WEST, recipeScroll, 14, SpringLayout.WEST, recipePanel);
		sl_recipePanel.putConstraint(SpringLayout.EAST, recipeScroll, -6, SpringLayout.EAST, recipePanel);
		sl_recipePanel.putConstraint(SpringLayout.NORTH, recipeScroll, -204, SpringLayout.SOUTH, recipePanel);
		sl_recipePanel.putConstraint(SpringLayout.SOUTH, recipeScroll, -10, SpringLayout.SOUTH, recipePanel);
		recipePanel.add(recipeScroll);
		
		JLabel lblRecipe = new JLabel("Recipe");
		sl_recipePanel.putConstraint(SpringLayout.SOUTH, lblRecipe, -399, SpringLayout.SOUTH, recipePanel);
		sl_recipePanel.putConstraint(SpringLayout.WEST, lblRecipe, 23, SpringLayout.WEST, recipePanel);
		lblRecipe.setFont(new Font("Calibri", Font.BOLD, 20));
		recipePanel.add(lblRecipe);

		

		JPanel panel_3 = new JPanel();
		springLayout.putConstraint(SpringLayout.NORTH, panel_3, 0, SpringLayout.NORTH, frame.getContentPane());
		springLayout.putConstraint(SpringLayout.WEST, panel_3, 33, SpringLayout.EAST, recipePanel);
		springLayout.putConstraint(SpringLayout.SOUTH, panel_3, 0, SpringLayout.SOUTH, recipePanel);
		
		Panel rNamePanel = new Panel();
		sl_recipePanel.putConstraint(SpringLayout.NORTH, rNamePanel, 8, SpringLayout.SOUTH, selectRecipeLabel);
		sl_recipePanel.putConstraint(SpringLayout.SOUTH, rNamePanel, -318, SpringLayout.SOUTH, recipePanel);
		sl_recipePanel.putConstraint(SpringLayout.WEST, rNamePanel, 10, SpringLayout.WEST, recipePanel);
		sl_recipePanel.putConstraint(SpringLayout.EAST, rNamePanel, -107, SpringLayout.EAST, recipeDelete);
		recipePanel.add(rNamePanel);
		SpringLayout sl_rNamePanel = new SpringLayout();
		rNamePanel.setLayout(sl_rNamePanel);
		
		
		
		
		
		
		
		String[] recipeNames1 = ((RecipeController) controllers.get(2)).getRecipeNames();
		ComboBoxModel updateModel = new DefaultComboBoxModel(recipeNames1);
		JComboBox rNameCombo = new JComboBox(updateModel);
		sl_rNamePanel.putConstraint(SpringLayout.NORTH, rNameCombo, 0, SpringLayout.NORTH, rNamePanel);
		sl_rNamePanel.putConstraint(SpringLayout.WEST, rNameCombo, 0, SpringLayout.WEST, rNamePanel);
		sl_rNamePanel.putConstraint(SpringLayout.SOUTH, rNameCombo, 28, SpringLayout.NORTH, rNamePanel);
		sl_rNamePanel.putConstraint(SpringLayout.EAST, rNameCombo, 217, SpringLayout.WEST, rNamePanel);
		rNamePanel.add(rNameCombo);
		
		JButton rNameRefresh = new JButton("refresh");
		sl_recipePanel.putConstraint(SpringLayout.SOUTH, rNameRefresh, -324, SpringLayout.SOUTH, recipePanel);
		sl_recipePanel.putConstraint(SpringLayout.NORTH, recipeDelete, 31, SpringLayout.SOUTH, rNameRefresh);
		sl_recipePanel.putConstraint(SpringLayout.WEST, rNameRefresh, 24, SpringLayout.EAST, rNamePanel);
		rNameRefresh.setBackground(new Color(255, 127, 80));
		rNameRefresh.setForeground(Color.WHITE);
		recipePanel.add(rNameRefresh);
		
		JTextArea rQuantity = new JTextArea();
		sl_recipePanel.putConstraint(SpringLayout.NORTH, rQuantity, -30, SpringLayout.NORTH, recipeScroll);
		sl_recipePanel.putConstraint(SpringLayout.WEST, rQuantity, 159, SpringLayout.WEST, recipePanel);
		sl_recipePanel.putConstraint(SpringLayout.SOUTH, rQuantity, -6, SpringLayout.NORTH, recipeScroll);
		sl_recipePanel.putConstraint(SpringLayout.EAST, rQuantity, 0, SpringLayout.EAST, recipeAdd);
		rQuantity.setEditable(false);
		recipePanel.add(rQuantity);
		
		JLabel lblNewLabel = new JLabel("quantity");
		sl_recipePanel.putConstraint(SpringLayout.NORTH, lblNewLabel, 22, SpringLayout.SOUTH, btnUpdate);
		sl_recipePanel.putConstraint(SpringLayout.EAST, lblNewLabel, -54, SpringLayout.WEST, rQuantity);
		lblNewLabel.setFont(new Font("Calibri", Font.PLAIN, 20));
		recipePanel.add(lblNewLabel);
		
		JTextArea rUnit = new JTextArea();
		rUnit.setEditable(false);
		sl_recipePanel.putConstraint(SpringLayout.NORTH, rUnit, -28, SpringLayout.NORTH, recipeScroll);
		sl_recipePanel.putConstraint(SpringLayout.WEST, rUnit, 7, SpringLayout.EAST, rQuantity);
		sl_recipePanel.putConstraint(SpringLayout.SOUTH, rUnit, -6, SpringLayout.NORTH, recipeScroll);
		sl_recipePanel.putConstraint(SpringLayout.EAST, rUnit, -26, SpringLayout.EAST, recipePanel);
		recipePanel.add(rUnit);
		panel_3.setBackground(new Color(245, 222, 179));
		panel_3.setBorder(null);
		frame.getContentPane().add(panel_3);

		rNameRefresh.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				String[] recipeNames = ((RecipeController) controllers.get(2)).getRecipeNames();
				ComboBoxModel updateModel = new DefaultComboBoxModel(recipeNames);
				rNameCombo.setModel(updateModel);
				
				rNamePanel.add(rNameCombo);
			}
		});
		
		
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
		sl_panel_3.putConstraint(SpringLayout.WEST, button_1, 19, SpringLayout.WEST, panel_3);
		panel_3.setLayout(sl_panel_3);
		button_1.setFont(new Font("Calibri", Font.PLAIN, 20));
		panel_3.add(button_1);

		// note delete
		JButton button_2 = new JButton("delete");
		sl_panel_3.putConstraint(SpringLayout.NORTH, button_2, 0, SpringLayout.NORTH, button_1);
		sl_panel_3.putConstraint(SpringLayout.WEST, button_2, 247, SpringLayout.WEST, panel_3);
		sl_panel_3.putConstraint(SpringLayout.EAST, button_2, -10, SpringLayout.EAST, panel_3);
		button_2.setBackground(new Color(255, 140, 0));
		button_2.setForeground(new Color(255, 255, 255));
		button_2.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				new DeleteRecipeListView(w);
			}
		});
		button_2.setFont(new Font("Calibri", Font.PLAIN, 20));
		panel_3.add(button_2);

		// note add
		JButton button_3 = new JButton("add");
		sl_panel_3.putConstraint(SpringLayout.WEST, button_3, 151, SpringLayout.WEST, panel_3);
		sl_panel_3.putConstraint(SpringLayout.EAST, button_1, -16, SpringLayout.WEST, button_3);
		sl_panel_3.putConstraint(SpringLayout.NORTH, button_3, 0, SpringLayout.NORTH, button_1);
		sl_panel_3.putConstraint(SpringLayout.SOUTH, button_3, 0, SpringLayout.SOUTH, button_1);
		sl_panel_3.putConstraint(SpringLayout.EAST, button_3, -17, SpringLayout.WEST, button_2);
		button_3.setBackground(new Color(255, 140, 0));
		button_3.setForeground(new Color(255, 255, 255));
		button_3.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				new AddNoteView(w);
			}
		});
		button_3.setFont(new Font("Calibri", Font.PLAIN, 20));
		panel_3.add(button_3);

		JButton ingreDelete = new JButton("Delete");
		springLayout.putConstraint(SpringLayout.NORTH, ingreDelete, 33, SpringLayout.SOUTH, panel_3);
		ingreDelete.setBackground(new Color(255, 127, 80));
		ingreDelete.setFont(new Font("Calibri", Font.PLAIN, 22));
		ingreDelete.setForeground(new Color(255, 255, 255));
		frame.getContentPane().add(ingreDelete);

		JButton ingreUpdate = new JButton("Update");
		springLayout.putConstraint(SpringLayout.NORTH, ingreUpdate, 33, SpringLayout.SOUTH, panel_3);
		springLayout.putConstraint(SpringLayout.EAST, panel_3, 12, SpringLayout.EAST, ingreUpdate);
		ingreUpdate.setBackground(new Color(255, 127, 80));
		
		ScrollPane noteScroll = new ScrollPane();
		sl_panel_3.putConstraint(SpringLayout.SOUTH, button_1, -12, SpringLayout.NORTH, noteScroll);
		sl_panel_3.putConstraint(SpringLayout.SOUTH, noteScroll, -10, SpringLayout.SOUTH, panel_3);
		sl_panel_3.putConstraint(SpringLayout.EAST, noteScroll, 0, SpringLayout.EAST, button_2);
		sl_panel_3.putConstraint(SpringLayout.NORTH, noteScroll, -318, SpringLayout.SOUTH, panel_3);
		sl_panel_3.putConstraint(SpringLayout.WEST, noteScroll, 19, SpringLayout.WEST, panel_3);
		panel_3.add(noteScroll);
		
		JLabel lblNote = new JLabel("Note");
		sl_panel_3.putConstraint(SpringLayout.NORTH, lblNote, 26, SpringLayout.NORTH, panel_3);
		sl_panel_3.putConstraint(SpringLayout.WEST, lblNote, 19, SpringLayout.WEST, panel_3);
		lblNote.setFont(new Font("Calibri", Font.BOLD, 20));
		panel_3.add(lblNote);
		springLayout.putConstraint(SpringLayout.WEST, ingreUpdate, 16, SpringLayout.EAST, ingreDelete);
		ingreUpdate.setForeground(new Color(255, 255, 255));
		ingreUpdate.setFont(new Font("Calibri", Font.PLAIN, 22));
		frame.getContentPane().add(ingreUpdate);

		JLabel lblStorageIngredients = new JLabel("Storage Ingredients");
		springLayout.putConstraint(SpringLayout.NORTH, lblStorageIngredients, 2, SpringLayout.NORTH, ingreDelete);
		springLayout.putConstraint(SpringLayout.WEST, lblStorageIngredients, 22, SpringLayout.EAST, equipPanel);



		JButton equipAdd = new JButton("Add");
		sl_equipPanel.putConstraint(SpringLayout.SOUTH, btnWhatToBrew, -76, SpringLayout.NORTH, equipAdd);
		sl_equipPanel.putConstraint(SpringLayout.WEST, equipAdd, 0, SpringLayout.WEST, btnWhatToBrew);
		equipAdd.setBackground(new Color(255, 127, 80));
		equipAdd.setForeground(new Color(255, 255, 255));
		equipAdd.setFont(new Font("Calibri", Font.PLAIN, 20));
		equipPanel.add(equipAdd);

		

		JButton equipUpdate = new JButton("Update");
		sl_equipPanel.putConstraint(SpringLayout.NORTH, equipUpdate, 0, SpringLayout.NORTH, equipAdd);
		sl_equipPanel.putConstraint(SpringLayout.WEST, equipUpdate, 6, SpringLayout.EAST, equipAdd);
		equipUpdate.setBackground(new Color(255, 127, 80));
		equipUpdate.setForeground(new Color(255, 255, 255));
		equipUpdate.setFont(new Font("Calibri", Font.PLAIN, 20));
		equipPanel.add(equipUpdate);

		

		JButton equipDelete = new JButton("Delete");
		sl_equipPanel.putConstraint(SpringLayout.NORTH, equipDelete, 0, SpringLayout.NORTH, equipAdd);
		sl_equipPanel.putConstraint(SpringLayout.EAST, equipDelete, -10, SpringLayout.EAST, equipPanel);
		equipDelete.setBackground(new Color(255, 127, 80));
		equipDelete.setForeground(new Color(255, 255, 255));
		equipDelete.setFont(new Font("Calibri", Font.PLAIN, 20));
		equipPanel.add(equipDelete);
		
		
		/********* set equip table************/
		ScrollPane equipScroll = new ScrollPane();
		sl_equipPanel.putConstraint(SpringLayout.NORTH, equipScroll, 193, SpringLayout.NORTH, equipPanel);
		sl_equipPanel.putConstraint(SpringLayout.SOUTH, equipScroll, -10, SpringLayout.SOUTH, equipPanel);
		sl_equipPanel.putConstraint(SpringLayout.SOUTH, equipAdd, -23, SpringLayout.NORTH, equipScroll);
		Object[][] equipData = ((EquipmentController) controllers.get(1)).getData();
		String[] equipColNames = ((EquipmentController) controllers.get(1)).getColNames();
		TablePane equipInfoPane = new TablePane(controllers.get(1), equipData, equipColNames);
		
		equipScroll.add(equipInfoPane);
		sl_equipPanel.putConstraint(SpringLayout.WEST, equipScroll, 0, SpringLayout.WEST, btnWhatToBrew);
		sl_equipPanel.putConstraint(SpringLayout.EAST, equipScroll, 0, SpringLayout.EAST, btnWhatToBrew);
		equipPanel.add(equipScroll);
		
		JLabel lblEquipment = new JLabel("Equipment");
		sl_equipPanel.putConstraint(SpringLayout.WEST, lblEquipment, 10, SpringLayout.WEST, equipPanel);
		sl_equipPanel.putConstraint(SpringLayout.SOUTH, lblEquipment, -18, SpringLayout.NORTH, equipAdd);
		lblEquipment.setFont(new Font("Calibri", Font.BOLD, 20));
		equipPanel.add(lblEquipment);
		
		equipAdd.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				AddEquipmentView addEquip = new AddEquipmentView(w, (EquipmentController) controllers.get(1), equipScroll);
				w.addListener(addEquip);

			}

		});

		equipDelete.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				Object[][] data = ((EquipmentController) controllers.get(1)).getData();
				DeleteEquipView deleteEquip = new DeleteEquipView(w, (EquipmentController) controllers.get(1),
						data, equipScroll);
				w.addListener(deleteEquip);

			}

		});
		
		
		equipUpdate.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				Object[][] data = ((EquipmentController) controllers.get(1)).getData();
				UpdateEquipmentView updateEquip = new UpdateEquipmentView(w, (EquipmentController) controllers.get(1),
						data, equipScroll);
				w.addListener(updateEquip);

			}

		});

		lblStorageIngredients.setFont(new Font("Calibri", Font.BOLD, 24));
		lblStorageIngredients.setForeground(Color.BLACK);
		frame.getContentPane().add(lblStorageIngredients);

		JButton ingreAdd = new JButton("Add");
		ingreAdd.setBackground(new Color(255, 127, 80));
		springLayout.putConstraint(SpringLayout.SOUTH, ingreAdd, -197, SpringLayout.SOUTH, frame.getContentPane());
		springLayout.putConstraint(SpringLayout.EAST, ingreAdd, -248, SpringLayout.EAST, frame.getContentPane());
		springLayout.putConstraint(SpringLayout.WEST, ingreDelete, 20, SpringLayout.EAST, ingreAdd);
		ingreAdd.setForeground(new Color(255, 255, 255));
		ingreAdd.setFont(new Font("Calibri", Font.PLAIN, 22));
		frame.getContentPane().add(ingreAdd);
		
		
		/*************set storage ingredient table *************/
		ScrollPane storageScroll = new ScrollPane();
		springLayout.putConstraint(SpringLayout.NORTH, storageScroll, 3, SpringLayout.SOUTH, ingreDelete);
		springLayout.putConstraint(SpringLayout.WEST, storageScroll, 10, SpringLayout.WEST, lblStorageIngredients);
		springLayout.putConstraint(SpringLayout.SOUTH, storageScroll, 145, SpringLayout.SOUTH, ingreDelete);
		springLayout.putConstraint(SpringLayout.EAST, storageScroll, 0, SpringLayout.EAST, ingreUpdate);
		frame.getContentPane().add(storageScroll);

		//	set the data of storage ingredients
		String[] colNames = ((StorageIngredientController) controllers.get(0)).getColNames();
		Object[][] ingreData = ((StorageIngredientController) controllers.get(0)).getData();
		TablePane ingreInfoTable = new TablePane(controllers.get(1), ingreData, colNames);
	
		storageScroll.add(ingreInfoTable);
		
		for (int i = 0; i < ingreData.length; i++) {
			System.out.println((String) ingreData[i][1]);
		}
		
		ingreAdd.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				
				AddStorIngreView addIngre = new AddStorIngreView(w, (StorageIngredientController) controllers.get(0), storageScroll);
				w.addListener(addIngre);

			}

		});

		ingreUpdate.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				Object[][] data = ((StorageIngredientController) controllers.get(0)).getData();
				UpdateStorIngreView updateIngreWindow = new UpdateStorIngreView(w,
						(StorageIngredientController) controllers.get(0), data, storageScroll);
				w.addListener(updateIngreWindow);

			}

		});

		ingreDelete.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				Object[][] data = ((StorageIngredientController) controllers.get(0)).getData();
				DeleteIngreView deleteIngreWindow = new DeleteIngreView(w,
						(StorageIngredientController) controllers.get(0), data, storageScroll);
				w.addListener(deleteIngreWindow);

			}

		});
		
		
	
		
		rNameCombo.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				
				// TODO Auto-generated method stub
				System.out.println("Action detected!");
				
				
				
				System.out.println("Selected index=" + ((JComboBox) e.getSource()).getItemCount());

				
				//	set amount information of recipe
				Object[] recipeInfo = ((RecipeController) controllers.get(2)).getRecipeAmount((String)rNameCombo.getSelectedItem());
				double quantity = (double)recipeInfo[0];
				String unit = (String)recipeInfo[1];
				rQuantity.setText(quantity + "");
				rUnit.setText(unit);
				
				// create a table panel which show the recipe ingredient in selected recipe and
				// add it to scroll panel
				
				Object[][] recipeIngreData = ((RecipeController) controllers.get(2))
						.getRecipeIngreData((String) rNameCombo.getSelectedItem());
				
				
				String[] recipeIngreCol = ((RecipeController) controllers.get(2)).getRecipeIngreColNames();
				TablePane recipeInfoPane = new TablePane(controllers.get(1), recipeIngreData, recipeIngreCol);
				recipeScroll.add(recipeInfoPane);
				
				String[] notes = ((NoteController) controllers.get(3))
						.getNote((String)rNameCombo.getSelectedItem());
				
				//	prepare the string buffer to store all note contents
				StringBuffer sBuffer = new StringBuffer("Notes:\r\n");

			    
				
				
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