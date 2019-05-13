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

public class MainPageViewNew extends View {
	private ArrayList<Controller> controllers;
	private JFrame frame;

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
		frame.setBounds(100, 100, 1000, 657);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);

		JPanel panel = new JPanel();
		panel.setBackground(new Color(245, 222, 179));
		panel.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel.setBounds(0, 0, 287, 611);
		frame.getContentPane().add(panel);
		SpringLayout sl_panel = new SpringLayout();
		panel.setLayout(sl_panel);

		
		//	equipment text area
		TextArea textArea = new TextArea();
		sl_panel.putConstraint(SpringLayout.NORTH, textArea, 126, SpringLayout.NORTH, panel);
		sl_panel.putConstraint(SpringLayout.WEST, textArea, 10, SpringLayout.WEST, panel);
		sl_panel.putConstraint(SpringLayout.SOUTH, textArea, 601, SpringLayout.NORTH, panel);
		sl_panel.putConstraint(SpringLayout.EAST, textArea, 277, SpringLayout.WEST, panel);
		textArea.setFont(new Font("Arial", Font.PLAIN, 12));
		textArea.setText("equipment");
		panel.add(textArea);

		//	equipment button add
		JButton btnAdd = new JButton("add");
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
		panel_2.setBackground(new Color(245, 222, 179));
		panel_2.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel_2.setBounds(297, 0, 339, 452);
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
		panel_3.setBackground(new Color(245, 222, 179));
		panel_3.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel_3.setBounds(646, 0, 332, 452);
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
		sl_panel_3.putConstraint(SpringLayout.SOUTH, textArea_2, 442, SpringLayout.NORTH, panel_3);
		sl_panel_3.putConstraint(SpringLayout.EAST, textArea_2, 329, SpringLayout.WEST, panel_3);
		textArea_2.setText("recipe");
		textArea_2.setFont(new Font("Arial", Font.PLAIN, 12));
		panel_3.add(textArea_2);

	}

	@Override
	public void update() {
		// TODO Auto-generated method stub

	}
}
