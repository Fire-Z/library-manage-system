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
		JLabel ID=new JLabel("�û���(ѧ��)��");
		JTextField number=new JTextField(15);
		JLabel ps=new JLabel("���룺");
		JPasswordField Password = new JPasswordField(15);
		Password.setEchoChar('*');
		JFrame pass=new JFrame("��¼");
		JButton yes=new JButton("ȷ��");
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
					// TODO �Զ����ɵ� catch ��
					e1.printStackTrace();
				} catch (IOException e1) {
					// TODO �Զ����ɵ� catch ��
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
	/*query ֻ��һ��ҳ����ת��JButton,�����������о���Ĺ���  */
	private JButton query=new JButton("��ѯ��Ϣ");
	private JButton borrow=new JButton("����ͼ��");
	private JButton back=new JButton("�黹ͼ��");
	private JButton findStudent=new JButton("��ѯѧ��������Ϣ");
	private JButton findBook=new JButton("��ѯͼ����Ϣ");
	JFrame mainWindow=new JFrame("ͼ����Ϣ����ϵͳ");
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
		    JDialog consult=new JDialog(mainWindow,"��Ϣ��ѯ",false);
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
	    		JLabel studentNum=new JLabel("���������ѧ��:");
	    		studentNum.setBounds(100, 100, 50, 25);
	    		JTextArea Information=new  JTextArea (10,30);
	    		Information.setLineWrap(true);
	    		JTextField Number=new JTextField(15);
	    		JButton go=new JButton("ȷ��");
	    		JFrame studentInformation=new JFrame("ѧ����Ϣ��ѯ");
	    		go.addActionListener(new ActionListener()
	    				{							
							public void actionPerformed(ActionEvent e)
							{
								if(Number.getText().equals(""))
								{
									JOptionPane.showMessageDialog(studentInformation,"�������������!","",JOptionPane.WARNING_MESSAGE,null);
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
					JLabel book=new JLabel("������ͼ�����ƣ�");
					book.setBounds(100, 100, 50, 25);
					JTextField Name=new JTextField(10);
					JTextArea Information=new JTextArea(10,30);
					Information.setLineWrap(true);
					JButton yes=new JButton("ȷ��");
					JFrame bookInformation=new JFrame("ѧ����Ϣ��ѯ");
					yes.addActionListener(new ActionListener()
    				{
						public void actionPerformed(ActionEvent arg0)
						{
							if(Name.getText().equals(""))
							{
								JOptionPane.showMessageDialog(bookInformation,"������ͼ������","",JOptionPane.WARNING_MESSAGE,null);
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
						JLabel sName=new JLabel("������������֣�");
						sName.setBounds(100, 100, 50, 25);
						JTextField Name=new JTextField(15);
						JLabel sNumber=new JLabel("���������ѧ�ţ�");
						sNumber.setBounds(100, 100, 50, 25);
						JTextField Number=new JTextField(15);
						JLabel book=new JLabel("������ͼ�����ƣ�");
						book.setBounds(100, 100, 50, 25);
						JTextField bName=new JTextField(15);
						JButton yes=new JButton("ȷ��");
						JFrame borrowWindow=new JFrame("����ͼ��");
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
												    JOptionPane.showMessageDialog(borrowWindow,"���ĳɹ���","",JOptionPane.INFORMATION_MESSAGE,null);
												}
												else
												{
													students.studentChange(Name.getText(), bName.getText(), 0);
												}			
											}
											else
											{
												JOptionPane.showMessageDialog(borrowWindow,"�����ѱ����ߣ�","",JOptionPane.ERROR_MESSAGE,null);
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
						JLabel sName=new JLabel("������������֣�");
						sName.setBounds(100, 100, 50, 25);
						JTextField Name=new JTextField(15);				
						JLabel book=new JLabel("������ͼ�����ƣ�");
						book.setBounds(100, 100, 50, 25);
						JTextField bName=new JTextField(15);
						JButton yes=new JButton("ȷ��");
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
						JFrame backWindow=new JFrame("�黹ͼ��");
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



	

