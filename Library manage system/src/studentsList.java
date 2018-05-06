import java.io.File;
import java.io.IOException;
import java.util.Vector;
import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;
import jxl.format.CellFormat;
import jxl.read.biff.BiffException;
import jxl.write.Label;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;
import jxl.write.biff.RowsExceededException;
class student
{
	String name;
	long  number;
	private long password;
	String  lendedBooks;
	String  overtimeBooks;
	int row;
	public student(String n,long num,String l,String o,int r,long ps)
	{
		this.name=n;
		this.number=num;
		this.lendedBooks=l;
		this.overtimeBooks=o;
		this.row=r;
		password =ps;
	}
	public long getPassword()
	{
		return this.password;
	}
}
public class studentsList 
{
	public File studentExcel=new File("学生信息.xls");
	Vector<student>students=new Vector();	
	public studentsList() throws BiffException, IOException
	{
		Workbook excel=Workbook.getWorkbook(studentExcel);
		Sheet b= excel.getSheet(0);
		int k=b.getRows()-1;
		for(int i=1;i<=k;i++)                    
		{
			Cell na=b.getCell(0,i);
			Cell num=b.getCell(1,i);
			Cell lb=b.getCell(2,i);
			Cell ob=b.getCell(3,i);
			Cell ps=b.getCell(4,i);
			String n=na.getContents();
			String l=lb.getContents();
			String o=ob.getContents();
			long nu=Long.parseLong(num.getContents());
			long p=Long.parseLong(ps.getContents());
			student y=new student(n,nu,l,o,i,p);
			students.add(y);
		}
	}
	public void addStudent(student x) throws BiffException, IOException, RowsExceededException, WriteException
	{
		Workbook student = Workbook.getWorkbook(studentExcel);  
		WritableWorkbook wb = Workbook.createWorkbook(studentExcel, student);
		WritableSheet wSheet1=wb.getSheet("Sheet1");
		Cell p=wSheet1.getCell(0,0);
		Cell q=wSheet1.getCell(1,0);
		Cell r=wSheet1.getCell(2,0);
		Cell s=wSheet1.getCell(3,0);
	    CellFormat cf1=p.getCellFormat();
	    CellFormat cf2=q.getCellFormat();
	    CellFormat cf3=r.getCellFormat();
	    CellFormat cf4=s.getCellFormat();
		Label l1=new Label(0,x.row,x.name,cf1);
		Label l2=new Label(1,x.row,String.valueOf(x.number),cf2); /*String s = String.valueOf( value);将任意数值类型转换为String型*/
		Label l3=new Label(2,x.row,x.lendedBooks,cf3);
		Label l4=new Label(3,x.row,x.overtimeBooks,cf4);
	    wSheet1.addCell(l1);
	    wSheet1.addCell(l2);
	    wSheet1.addCell(l3);
	    wSheet1.addCell(l4);
		wb.write();
		wb.close();
		this.studentUpdate();
	}
	public int excelRow() throws BiffException, IOException
	{	
		Workbook excel=Workbook.getWorkbook(studentExcel);
		Sheet s=excel.getSheet(0);
		return s.getRows();
	}	
	public void studentUpdate() throws BiffException, IOException
	{
		Workbook excel=Workbook.getWorkbook(studentExcel);
		Sheet b= excel.getSheet(0);
		int k=b.getRows()-1;
		for(int i=1;i<=k;i++)                    
		{
			Cell na=b.getCell(0,i);
			Cell num=b.getCell(1,i);
			Cell lb=b.getCell(2,i);
			Cell ob=b.getCell(3,i);
			Cell ps=b.getCell(4,i);
			String n=na.getContents();
			String l=lb.getContents();
			String o=ob.getContents();
			long nu=Long.parseLong(num.getContents());			
			long p=Long.parseLong(ps.getContents());
			student y=new student(n,nu,l,o,i,p);
			students.add(y);
		}
	}
   /*public void Display()
    {
    	student vaule=null;
    	for(int i=0;i<this.students.size();i++)
    	{
    		vaule=students.get(i);
    		System.out.println("学生姓名为："+vaule.name+"  学生学号为："+vaule.number+"  借阅书籍："+vaule.lendedBooks+"  超期书籍："+vaule.overtimeBooks);
    	}
    }*/
	public long password(long x)
	{
		student value=null;
		for(int i=0;i<this.students.size();i++)
		{
			value=students.get(i);
			if(value.number==x)
			{
				return value.getPassword();
			}
		}
			return 0000000000;
	}
    public String studentFind(long x)
    {
    	student vaule=null;
    	for(int i=0;i<this.students.size();i++)
    	{
    		vaule=students.get(i);
    		if(vaule.number==x)
    		{
    			return "学生姓名为："+vaule.name+"  学生学号为："+vaule.number+"  借阅书籍："+vaule.lendedBooks+"  超期书籍："+vaule.overtimeBooks;
    		}
    	}
    	return "Not Found!";
    }
    /*x,y,z分别为学生名字，书名，还书或借书（1为还，0为借）*/
    public void studentChange(String x,String y,int z) throws BiffException, IOException, RowsExceededException, WriteException
    {
    	student vaule=null;
    	for(int i=0;i<this.students.size();i++)
    	{
    		vaule=students.get(i);
    		if(vaule.name.equals(x))
    		{
    	    		Workbook student = Workbook.getWorkbook(studentExcel);  
    				WritableWorkbook wb = Workbook.createWorkbook(studentExcel, student);
    				WritableSheet wSheet1=wb.getSheet("Sheet1");
    				Cell q=wSheet1.getCell(2,vaule.row);
      				CellFormat cf2=q.getCellFormat();
    			    Cell p=wSheet1.getCell(3,vaule.row);
    				CellFormat cf3=p.getCellFormat();
    				Label lbl;
    				if(z==1)
    				{
    					if(vaule.overtimeBooks.contains(y))
    	    			{
    						lbl=new Label(3,vaule.row,vaule.overtimeBooks.replaceAll(y,""),cf3);
    	    			}
    					else
    					{
    						lbl=new Label(2,vaule.row,vaule.lendedBooks.replaceAll(y,""),cf2);
    					}
    					wSheet1.addCell(lbl);
    					wb.write();
    					wb.close();
    					this.studentUpdate(); //修改完之后更新studentsLink类
    					return; 
     				 }
    				else
    				{
    					lbl=new Label(2,vaule.row,vaule.lendedBooks+","+y,cf2);
    					wSheet1.addCell(lbl);
    					wb.write();
    					wb.close();
    					this.studentUpdate(); //修改完之后更新studentsLink类
    					return; 
    				}
    		}
    	}
    }
}
	