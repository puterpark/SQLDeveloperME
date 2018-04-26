package com.kosmo.sql;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.InputEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JTree;
import javax.swing.KeyStroke;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;
import javax.swing.event.TreeExpansionEvent;
import javax.swing.event.TreeExpansionListener;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreePath;

import com.kosmo.member.MemberCrud;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class SqlDeveloperME extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private JTable table;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
					SqlDeveloperME frame = new SqlDeveloperME();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public SqlDeveloperME() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(800, 300, 800, 600);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		setTitle("SQL Developer ME");


		DefaultMutableTreeNode rootNode = new DefaultMutableTreeNode("XE");

		MemberCrud m = new MemberCrud();
		ArrayList<String> list = m.objectList();
		
		
		for (int i = 0; i < list.size(); i++) {
			DefaultMutableTreeNode childNode1 = new DefaultMutableTreeNode(list.get(i));
			rootNode.add(childNode1);
			childNode1.add(new DefaultMutableTreeNode("")); //펼침을 알아보기 위한 효과
		}
		
		
//		DefaultMutableTreeNode childNode1 = new DefaultMutableTreeNode("Child1");
//		DefaultMutableTreeNode childNode11 = new DefaultMutableTreeNode("Child11");
//		
//		DefaultMutableTreeNode childNode2 = new DefaultMutableTreeNode("Child2");
//
//		rootNode.add(childNode1);
//		childNode1.add(childNode11);
//		rootNode.add(childNode2);
		
		JTree tree = new JTree(rootNode);
		tree.setPreferredSize(new Dimension(180, 16));
//		JScrollPane scroll = new JScrollPane();

		tree.addTreeExpansionListener(new TreeExpansionListener() {
			public void treeCollapsed(TreeExpansionEvent event) { //접혔을 때
				System.out.println("트리 접힘");
			}
			public void treeExpanded(TreeExpansionEvent event) { //펼쳤을때
				System.out.println("트리 펼침");
				
				tree.setSelectionPath(event.getPath());
				
				TreePath path = tree.getSelectionPath();
				DefaultMutableTreeNode selNode = (DefaultMutableTreeNode)path.getLastPathComponent();
				MemberCrud m = new MemberCrud();
				
				if(!selNode.toString().equals("")) {
					ArrayList<String> list = m.selectObjectList(selNode.toString());
					
					selNode.remove(0);
					
					for (int i = 0; i < list.size(); i++) {
						DefaultTreeModel model = (DefaultTreeModel)tree.getModel();
						
						
						selNode.add(new DefaultMutableTreeNode(list.get(i)));

						model.reload(selNode); //새로고침
						tree.expandPath(path); //트리펼침
					}
				}
			}
		});
		
		tree.addTreeSelectionListener(new TreeSelectionListener() {
			public void valueChanged(TreeSelectionEvent e) {
				System.out.println("TreeSelect Click");
				if(e.getSource() == tree) {
					System.out.println("Selected Tree");
					
					DefaultMutableTreeNode selNode = (DefaultMutableTreeNode)tree.getLastSelectedPathComponent();
					if(selNode == null) {
						return;
					}
					
					TreePath path = tree.getSelectionPath();
					System.out.println("위에서부터>" + path.getPathCount());
					System.out.println("경로>" + path.getParentPath());
					System.out.println("나 자신>" + path.getLastPathComponent()); //선택된 노드 이름
					System.out.println("선택 노드>" + selNode.toString());
					System.out.println("선택 노드 깊이>" + selNode.getPath().length);
					if(selNode.getPath().length > 2) {
						DefaultTableModel model = new MemberCrud().resultSetToTableModel("select * from " + selNode.toString() );
						table.setModel(model);
					}
					
				}
			}
		});
		
		tree.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				System.out.println("Mouse Click");
			}
		});
		
		
		JScrollPane leftScrollPane = new JScrollPane(tree);
		contentPane.add(leftScrollPane, BorderLayout.WEST);
		
		JPanel topPane = new JPanel();
		contentPane.add(topPane, BorderLayout.NORTH);
		
		textField = new JTextField();
		topPane.add(textField);
		textField.setColumns(10);
		
		JButton addBtn = new JButton("추가");
		addBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println("추가");
				
				/*<!-- 같은 깊이의 노드 추가 --!>*/
//				TreePath path = tree.getSelectionPath();
//				DefaultMutableTreeNode node = (DefaultMutableTreeNode)path.getLastPathComponent();
//				int idx = 0;
//				if(node.getParent() != null) {
//					DefaultMutableTreeNode pnode = (DefaultMutableTreeNode)node.getParent();
//					idx = pnode.getIndex(node) + 1;
//					node = pnode;
//					
//				}
//				
//				DefaultMutableTreeNode newNode = new DefaultMutableTreeNode(textField.getText());
//				DefaultTreeModel model = (DefaultTreeModel)tree.getModel();
//				model.insertNodeInto(newNode, node, idx);
				
				
				
				/*<!-- 같은 깊이보다 하나 큰 노드 추가 --!>*/
				TreePath path = tree.getSelectionPath(); //어딜 선택했는지 알 수 있음
				DefaultMutableTreeNode node = (DefaultMutableTreeNode)path.getLastPathComponent(); //그리고 노드 이름을 잡음
				node.add(new DefaultMutableTreeNode(textField.getText()));
				DefaultTreeModel model = (DefaultTreeModel)tree.getModel();
				model.reload(node); //새로고침
				tree.expandPath(path); //트리펼침
				
				
				
			}
		});
		topPane.add(addBtn);
		
		JButton delBtn = new JButton("삭제");
		delBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println("삭제");
				
				/*<!-- 같은 깊이의 노드 추가 --!>*/
				TreePath path = tree.getSelectionPath();
				
				DefaultMutableTreeNode node = (DefaultMutableTreeNode)path.getLastPathComponent();
				
				if(path == null) {
					System.out.println("아무것도 선택하지 않음");
					return;
				}
				
				if(node.isRoot()) {
					System.out.println("ROOT는 지울 수 없음");
					return;
				}
				
//				System.out.println(node.getIndex(node));
////				node.remove(node.getIndex(node));
//				node.remove(node);
//				DefaultTreeModel model = (DefaultTreeModel)tree.getModel();
//				model.reload(node); //새로고침
//				tree.expandPath(path); //트리펼침
				
				TreePath ppath = path.getParentPath();
				node.removeFromParent();
				DefaultTreeModel model = (DefaultTreeModel)tree.getModel();
				model.reload(node); //새로고침
				tree.expandPath(ppath); //트리펼침
				
				
				/*<!-- 같은 깊이보다 하나 큰 노드 추가 --!>*/
				
			}
		});
		topPane.add(delBtn);
		
		JButton editBtn = new JButton("수정");
		editBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println("수정");
			
				TreePath path = tree.getSelectionPath();
				DefaultMutableTreeNode node = (DefaultMutableTreeNode)path.getLastPathComponent();
				
				node.setUserObject(textField.getText());
				DefaultTreeModel model = (DefaultTreeModel)tree.getModel();
				model.nodeChanged(node); //노드 값을 변경
				
				
				
			}
		});
		topPane.add(editBtn);
		
		JPanel panel = new JPanel();
		contentPane.add(panel, BorderLayout.CENTER);
		panel.setLayout(new BorderLayout(0, 0));
		
		JTextArea sqlText = new JTextArea();
		sqlText.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if(e.getKeyCode() == KeyEvent.VK_ENTER && e.isControlDown()) {
					String sql = sqlText.getText();
					
					if(sql.lastIndexOf(";") > 0) {
						sql = sql.substring(0, sql.length()-1);
					}			
					
					DefaultTableModel model = new MemberCrud().resultSetToTableModel(sql);
					table.setModel(model);
				}
				
			}
		});
		
		JScrollPane RightScrollPane = new JScrollPane(sqlText);
		panel.add(RightScrollPane, BorderLayout.CENTER);
		
		table = new JTable();
		JScrollPane scrollPane = new JScrollPane(table);
		panel.add(scrollPane, BorderLayout.SOUTH);
		scrollPane.setPreferredSize(new Dimension(452, 200));
		
		JPanel panel_1 = new JPanel();
		panel.add(panel_1, BorderLayout.NORTH);

		
		ImageIcon fi = new ImageIcon(SqlDeveloperME.class.getResource("/com/kosmo/sql/run.png"));
		Image si = fi.getImage();
		Image ti = si.getScaledInstance(20, 20, java.awt.Image.SCALE_SMOOTH);
		ImageIcon li = new ImageIcon(ti);
		panel_1.setLayout(new BorderLayout(0, 0));
		JButton runBtn = new JButton(li);
		runBtn.setHorizontalTextPosition(SwingConstants.LEADING);
		runBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String sql = sqlText.getText();
				
				if(sql.lastIndexOf(";") > 0) {
					sql = sql.substring(0, sql.length()-1);
				}			
				
				DefaultTableModel model = new MemberCrud().resultSetToTableModel(sql);
				table.setModel(model);
			}
		});
		panel_1.add(runBtn, BorderLayout.WEST);
		
		JSeparator separator = new JSeparator();
		panel_1.add(separator, BorderLayout.NORTH);
		
	}

}
