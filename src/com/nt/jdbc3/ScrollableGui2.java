package com.nt.jdbc3;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

@SuppressWarnings("serial")
public class ScrollableGui2 extends JFrame implements ActionListener,WindowListener{
	private static final String STUDENT_QUERY = "SELECT SID,SNAME,LOC,AVERAGE FROM STUDENT";
	private JLabel lsno,lsname,laddrs,lavg;
	private JTextField tsno,tsname,taddrs,tavg;
	private JButton bFirst,bLast,bPrevious,bNext;
	private Connection con;
	private PreparedStatement ps;
	private ResultSet rs;
	public ScrollableGui2() {
		System.out.println("ScrollableGui2");
		setTitle("GUI-FrontEnd Scroll frame");
		setSize(300,300);
		setLayout(new FlowLayout());
		
		lsno = new JLabel("sno");
		add(lsno);
		tsno = new JTextField(10);
		add(tsno);
		
		lsname = new JLabel("sname");
		add(lsname);
		tsname = new JTextField(10);
		add(tsname);
		
		laddrs = new JLabel("addrs");
		add(laddrs);
		taddrs = new JTextField(10);
		add(taddrs);
		
		lavg = new JLabel("avg");
		add(lavg);
		tavg = new JTextField(10);
		add(tavg);
		
		bFirst = new JButton("First");
		bNext = new JButton("Next");
		bPrevious = new JButton("Previous");
		bLast = new JButton("Last");
		add(bFirst);add(bNext);add(bPrevious);add(bLast);
		
		//register and activate active listener for all 4 button
		bFirst.addActionListener(this);
		bNext.addActionListener(this);
		bPrevious.addActionListener(this);
		bLast.addActionListener(this);
		//add windowlistner to the frame
		this.addWindowListener(this);  //first this represents "Jframe" and 2nd one represents WindowListener
		
		//disable editing on textboxes
		tsno.setEditable(false);
		tsname.setEditable(false);
		taddrs.setEditable(false);
		tavg.setEditable(false);
		
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		InitializeJDBC();
	}
	
	public static void main(String[] args) {
		System.out.println("GuiScrollFrameTest main()");
		new ScrollableGui2();
		System.out.println("End of main");
	}
	
	private void InitializeJDBC() {
		//establish the connection
		try{
			
			con = DriverManager.getConnection("jdbc:mysql:///ntaj","root","root");
			//create PreparedStatement obj to execute the pre-compiled query
			ps = con.prepareStatement(STUDENT_QUERY,ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_UPDATABLE);
			//	execute the query and process the results
			rs = ps.executeQuery();	
					
		}
		catch(SQLException se) {
			se.printStackTrace();
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}
	@Override
	public void actionPerformed(ActionEvent ae) {
		// TODO Auto-generated method stub
		System.out.println("GUIScrollTEst.action performed");
		boolean flag = false;
		if(ae.getSource()==bFirst) {
			try {
				rs.first();
				flag = true;
				System.out.println("First button clicked");
			}catch(SQLException se) {
				se.printStackTrace();
			}
		}else if(ae.getSource()==bNext) {
			try {
				if(!rs.isLast()) {
					rs.next();
					flag = true;
					System.out.println("Second button clicked");
				}
			}catch(SQLException se) {
				se.printStackTrace();
			}
		}else if(ae.getSource()==bPrevious) {
			try {
				if(!rs.isFirst()) {
					rs.previous();
					flag = true;
					System.out.println("Previous Button Clicked");
				}
			}catch(SQLException se) {
				se.printStackTrace();
			}
		}else {
			try {
				rs.last();
				flag = true;
				System.out.println("Last button Clicked");
			}catch(SQLException se) {
				se.printStackTrace();
			}
		}
		
		if(flag == true) {
			try {
				tsno.setText(rs.getString(1));
				tsname.setText(rs.getString(2));
				taddrs.setText(rs.getString(3));
				tavg.setText(rs.getString(4));
			}catch(SQLException se) {
				se.printStackTrace();
			}
		}
	}

	@Override
	public void windowOpened(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowClosing(WindowEvent e) {
		// TODO Auto-generated method stub
		System.out.println("Window is closing");
		try {
			if(rs!=null)
				rs.close();
		}catch(SQLException se) {
			se.printStackTrace();
		}
		
		try {
			if(ps!=null)
				ps.close();
		}catch(SQLException se) {
			se.printStackTrace();
		}
		
		try {
			if(con!=null)
				con.close();
		}catch(SQLException se) {
			se.printStackTrace();
		}
	}

	@Override
	public void windowClosed(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowIconified(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowDeiconified(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowActivated(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowDeactivated(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}
    
}//class
