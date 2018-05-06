import javax.swing.*;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.event.*;
import java.io.*;
import jxl.Workbook;
import jxl.read.biff.BiffException;
import jxl.write.WriteException;
import jxl.write.biff.RowsExceededException;
class W
{	
	public static void main(String args[]) throws BiffException, IOException, RowsExceededException, WriteException 
	{
		/*new mainWindow();*/
		new passWindow();
	}
}
class passWindow extends JFrame
{
	public passWindow()
	{
		JLabel ID=new JLabel("用户名(学号)：");
		JTextField number=new JTextField(15);
		JLabel ps=new JLabel("密码：");
		JPasswordField Password = new JPasswordField(15);
		Password.setEchoChar('*');
		JFrame pass=new JFrame("登录");
		JButton yes=new JButton("确定");
		yes.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e)
			{
				try 
				{
					studentsList students=new studentsList();
					String word=new String(Password.getPassword());
					if(Long.parseLong(word)==students.password(Long.parseLong(number.getText())))
					{
						new mainWindow();
						pass.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
					}
				} catch (BiffException e1) {
					// TODO 自动生成的 catch 块
					e1.printStackTrace();
				} catch (IOException e1) {
					// TODO 自动生成的 catch 块
					e1.printStackTrace();
				}
				
			}
			
		});
		pass.setBounds(200, 400, 300, 250);
		pass.setBackground(Color.WHITE);
		pass.setLayout(new FlowLayout());
		pass.setVisible(true);
		pass.add(ID);
		pass.add(number);
		pass.add(ps);
		pass.add(Password);
		pass.add(yes);
	}	
}
class mainWindow extends JFrame
{
	/*query 只是一个页面跳转的JButton,其他的则需有具体的功能  */
	private JButton query=new JButton("查询信息");
	private JButton borrow=new JButton("借阅图书");
	private JButton back=new JButton("归还图书");
	private JButton findStudent=new JButton("查询学生借阅信息");
	private JButton findBook=new JButton("查询图书信息");
	JFrame mainWindow=new JFrame("图书信息管理系统");
	public mainWindow()
	{
		mainWindow.setBounds(100, 200, 550, 580);
		mainWindow.setBackground(Color.WHITE);
		mainWindow.setLayout(new FlowLayout());
		mainWindow.setVisible(true);
		mainWindow.add(query);
		mainWindow.add(borrow);
		mainWindow.add(back);
		mainWindow.addWindowListener(new WindowAdapter(){
		    public void windowClosing(WindowEvent e)
			{
				System.exit(0);
			}
		});
	    query.addActionListener(new ActionListener()
	      {
		    public void actionPerformed(ActionEvent e)
		    {
		    JDialog consult=new JDialog(mainWindow,"信息查询",false);
			consult.setBounds(150, 250, 480, 100);
			consult.setLayout(new FlowLayout());
			consult.add(findStudent);
			consult.add(findBook);
			consult.setVisible(true);
		    }
	   });
	    findStudent.addActionListener(new ActionListener()
	    		{
	    	public void actionPerformed(ActionEvent e)
	    	{
	    		JLabel studentNum=new JLabel("请输入你的学号:");
	    		studentNum.setBounds(100, 100, 50, 25);
	    		JTextArea Information=new  JTextArea (10,30);
	    		Information.setLineWrap(true);
	    		JTextField Number=new JTextField(15);
	    		JButton go=new JButton("确定");
	    		JFrame studentInformation=new JFrame("学生信息查询");
	    		go.addActionListener(new ActionListener()
	    				{							
							public void actionPerformed(ActionEvent e)
							{
								if(Number.getText().equals(""))
								{
									JOptionPane.showMessageDialog(studentInformation,"请输入你的名字!","",JOptionPane.WARNING_MESSAGE,null);
								    return;
								}
								else
								{	
									long n=Long.parseLong(Number.getText());
									studentsList students;
									try 
									{
										students = new studentsList();
										String in=students.studentFind(n);
										Information.setText(in);
									} 
									catch (BiffException e1) {e1.printStackTrace();}
									catch (IOException e1) {e1.printStackTrace();}																												
								}
							}
	    				});		    		
	    		studentInformation.setBounds(200, 300,350,350);
	    		studentInformation.setLayout(new FlowLayout());
	    		studentInformation.setVisible(true);
	    		studentInformation.add(studentNum);
	    		studentInformation.add(Number);
	    		studentInformation.add(Information);
	    		studentInformation.add(go);
	    	}	    	 
	    		});
	    findBook.addActionListener(new ActionListener()
			   {
				public void actionPerformed(ActionEvent e) 
				{
					JLabel book=new JLabel("请输入图书名称：");
					book.setBounds(100, 100, 50, 25);
					JTextField Name=new JTextField(10);
					JTextArea Information=new JTextArea(10,30);
					Information.setLineWrap(true);
					JButton yes=new JButton("确定");
					JFrame bookInformation=new JFrame("学生信息查询");
					yes.addActionListener(new ActionListener()
    				{
						public void actionPerformed(ActionEvent arg0)
						{
							if(Name.getText().equals(""))
							{
								JOptionPane.showMessageDialog(bookInformation,"请输入图书名称","",JOptionPane.WARNING_MESSAGE,null);
							}
							else
							{
								try
								{
									BooksList Books=new BooksList();
									Information.setText(Books.bookFind(Name.getText()));											
								} 
								catch (BiffException e) {e.printStackTrace();}
								catch (IOException e) {e.printStackTrace();}	
							}								
							
						}
    			
    				});					
		    		bookInformation.setBounds(200, 300,350,350);
		    		bookInformation.setLayout(new FlowLayout());
		    		bookInformation.setVisible(true);
		    		bookInformation.add(book);
		    		bookInformation.add(Name);
		    		bookInformation.add(Information);		
		    		bookInformation.add(yes);
				}
			   });
	    borrow.addActionListener(new ActionListener()
	    		{
					public void actionPerformed(ActionEvent e) 
					{
						JLabel sName=new JLabel("请输入你的名字：");
						sName.setBounds(100, 100, 50, 25);
						JTextField Name=new JTextField(15);
						JLabel sNumber=new JLabel("请输入你的学号：");
						sNumber.setBounds(100, 100, 50, 25);
						JTextField Number=new JTextField(15);
						JLabel book=new JLabel("请输入图书名称：");
						book.setBounds(100, 100, 50, 25);
						JTextField bName=new JTextField(15);
						JButton yes=new JButton("确定");
						JFrame borrowWindow=new JFrame("借阅图书");
						yes.addActionListener(new ActionListener()
								{
									public void actionPerformed(ActionEvent f) 
									{										
										try
										{
											BooksList books=new BooksList();
											if(books.bookFind1(bName.getText())==1)
											{
												books.bookChange(bName.getText(),0);
												studentsList students=new studentsList();
												if(students.studentFind(Long.parseLong(Number.getText())).equals("Not Found!"))
												{
													student s=new student(Name.getText(),Long.parseLong(Number.getText()),bName.getText(),"none",students.excelRow()+1,111111) ;
												    students.addStudent(s);
												    JOptionPane.showMessageDialog(borrowWindow,"借阅成功！","",JOptionPane.INFORMATION_MESSAGE,null);
												}
												else
												{
													students.studentChange(Name.getText(), bName.getText(), 0);
												}			
											}
											else
											{
												JOptionPane.showMessageDialog(borrowWindow,"该书已被借走！","",JOptionPane.ERROR_MESSAGE,null);
											}
										} 
										catch (BiffException e) {e.printStackTrace();}
										catch (IOException e) {e.printStackTrace();} 
										catch (RowsExceededException e) {e.printStackTrace();}																				
										catch (WriteException e) {e.printStackTrace();}																																											
									}						
								});						
						borrowWindow.setBounds(200, 300,350,350);
						borrowWindow.setLayout(new FlowLayout());
			    		borrowWindow.setVisible(true);		
			    		borrowWindow.add(sName);
			    		borrowWindow.add(Name);
			    		borrowWindow.add(sNumber);
			    		borrowWindow.add(Number);
			    		borrowWindow.add(book);
			    		borrowWindow.add(bName);
			    		borrowWindow.add(yes);
					}	    	
	    		});
	    back.addActionListener(new ActionListener()
	    		{
					public void actionPerformed(ActionEvent e)
					{
						JLabel sName=new JLabel("请输入你的名字：");
						sName.setBounds(100, 100, 50, 25);
						JTextField Name=new JTextField(15);				
						JLabel book=new JLabel("请输入图书名称：");
						book.setBounds(100, 100, 50, 25);
						JTextField bName=new JTextField(15);
						JButton yes=new JButton("确定");
						yes.addActionListener(new ActionListener()
						{
							public void actionPerformed(ActionEvent f)
							{
								BooksList books;
								try 
								{
									books = new BooksList();
									books.bookChange(bName.getText(),1);
									studentsList students=new studentsList();
									students.studentChange(Name.getText(),bName.getText(),1);
								} 
								catch (BiffException | IOException e) {e.printStackTrace();}
								catch (RowsExceededException e) {e.printStackTrace();}
								catch (WriteException e) {e.printStackTrace();}																																									
							}
						});
						JFrame backWindow=new JFrame("归还图书");
						backWindow.setBounds(200, 300,350,350);
						backWindow.setLayout(new FlowLayout());
						backWindow.setVisible(true);	
						backWindow.add(sName);
						backWindow.add(Name);
						backWindow.add(book);
						backWindow.add(bName);
						backWindow.add(yes);
					}	    	
	    		});	    	    				    	
     }
}



	

