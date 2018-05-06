import java.io.*;
import jxl.*;
import jxl.format.CellFormat;
import jxl.read.biff.BiffException;
import jxl.write.*;
import jxl.write.biff.RowsExceededException;
import java.util.Vector;
class Book
{
	String BookName;
	String Writter;
	int number;
	int row;            //用于标记对象在.xls文件中的行号，方便修改文件中的图书信息
	int IsLended;   //值为1则可借，为0则不可借
	public Book(String b,String w,int n,int i,int r)
	{
		this.BookName=b;
		this.Writter=w;
		this.number=n;
		this.IsLended=i;
		this.row=r;
	}
}
public class BooksList 
{
	public File bookExcel=new File("图书信息.xls");
	public Vector<Book>Books=new Vector<Book>();
	public BooksList() throws BiffException, IOException 
	{
		Workbook excel = Workbook.getWorkbook(bookExcel);
		Sheet a=excel.getSheet(0);
		int k=a.getRows()-1;     //除去首行
		for(int i=1;i<=k;i++)
		{
				Cell number=a.getCell(0, i);
				Cell name=a.getCell(1, i) ;
				Cell writter=a.getCell(2, i) ;
				Cell islended=a.getCell(3, i) ;
				String b=name.getContents();
				String w=writter.getContents();
				int n=Integer.parseInt(number.getContents());
				int is=Integer.parseInt(islended.getContents());
				Book x=new Book(b,w,n,is,i);
				Books.add(x);
		}
	}
	public void update() throws BiffException, IOException           /* 更新BooksList类*/
	{
		Workbook excel = Workbook.getWorkbook(bookExcel);
		Sheet a=excel.getSheet(0);
		int k=a.getRows()-1;     //除去首行
		for(int i=1;i<=k;i++)
		{
				Cell number=a.getCell(0, i);
				Cell name=a.getCell(1, i) ;
				Cell writter=a.getCell(2, i) ;
				Cell islended=a.getCell(3, i) ;
				String b=name.getContents();
				String w=writter.getContents();
				int n=Integer.parseInt(number.getContents());
				int is=Integer.parseInt(islended.getContents());
				Book x=new Book(b,w,n,is,i);
				Books.add(x);
		}
	}
	public int excelRow() throws BiffException, IOException
	{	
		Workbook excel=Workbook.getWorkbook(bookExcel);
		Sheet s=excel.getSheet(0);
		return s.getRows();
	}
	public void Show()
	{
		Book vaule=null;
		for(int i=0;i<this.Books.size();i++)
		{
			vaule=this.Books.get(i);
			if(vaule.IsLended==1)
			{
			    System.out.println("图书编号为："+vaule.number+"  图书名字为："+vaule.BookName+"  图书作者为："+vaule.Writter+"  备注：该书可借");
			}
			else
			{
				System.out.println("图书编号为："+vaule.number+"  图书名字为："+vaule.BookName+"  图书作者为："+vaule.Writter+"  备注：该书不可借");
			}
		}
	}
	 //根据书名改变图书状态,用 k来选择还书或借书（1为还书，0为借书）
	public void bookChange(String x,int k) throws BiffException, IOException, RowsExceededException, WriteException
	{
		Book vaule=null;
		for(int i=0;i<this.Books.size();i++)
		{
			vaule=this.Books.get(i);
			if(vaule.BookName.equals(x))      /* 字符串的比较不能使用==（相当于比较他们的地址），应该用equals()方法*/
			{
				Workbook book = Workbook.getWorkbook(bookExcel);  
				WritableWorkbook wb = Workbook.createWorkbook(bookExcel, book);
				WritableSheet wSheet1=wb.getSheet("Sheet1");
				Cell y=wSheet1.getCell(3,vaule.row);
				CellFormat cf=y.getCellFormat();
				Label lbl;
				if(k==1)
				{
					lbl=new Label(3,vaule.row,"1",cf);
				}
				else
				{
					lbl=new Label(3,vaule.row,"0",cf);
				}
				wSheet1.addCell(lbl);
				wb.write();
				wb.close();
				this.update(); //修改完之后更新BooksList类
				return; 
			}
		}
		System.out.println("未找到该书！");
	}
	//根据编号改变图书状态
	public void bookChange(int x,int k) throws BiffException, IOException, RowsExceededException, WriteException
	{
		Book vaule=null;
		for(int i=0;i<this.Books.size();i++)
		{
			vaule=this.Books.get(i);
			if(vaule.number==x)   
			{
				Workbook book = Workbook.getWorkbook(bookExcel);  
				WritableWorkbook wb = Workbook.createWorkbook(bookExcel, book);
				WritableSheet wSheet1=wb.getSheet("Sheet1");
				Cell y=wSheet1.getCell(3,vaule.row);
				CellFormat cf=y.getCellFormat();
				Label lbl;
				if(k==1)
				{
					lbl=new Label(3,vaule.row,"1",cf);
				}
				else
				{
					lbl=new Label(3,vaule.row,"0",cf);
				}
				wSheet1.addCell(lbl);
				wb.write();
				wb.close();
				this.update(); 
				return; 
			}
		}
		System.out.println("未找到该书！");
	}
    public String bookFind(String x)
    {
    	Book vaule=null;
		for(int i=0;i<this.Books.size();i++)
		{
			vaule=this.Books.get(i);
			if(vaule.BookName.equals(x)) 
			{
				if(vaule.IsLended==1)
				{
					return "图书编号为："+vaule.number+"  图书名字为："+vaule.BookName+"  图书作者为："+vaule.Writter+"  备注：该书可借";
				}
				else
				{
					return"图书编号为："+vaule.number+"  图书名字为："+vaule.BookName+"  图书作者为："+vaule.Writter+"  备注：该书不可借";
				}
			}
		}
		return "Not Found!";
     }
    public int  bookFind1(String x)
    {
    	Book vaule=null;
		for(int i=0;i<this.Books.size();i++)
		{
			vaule=this.Books.get(i);
			if(vaule.BookName.equals(x))
			{
				return vaule.IsLended;
			}
		}
		return -1;
    }
   /* public String bookFind(int x)
    {
    	Book vaule=null;
		for(int i=0;i<this.Books.size();i++)
		{
			vaule=this.Books.get(i);
			if(vaule.number==x)
			{
				if(vaule.IsLended==1)
				{
					return "图书编号为："+vaule.number+"  图书名字为："+vaule.BookName+"  图书作者为："+vaule.Writter+"  备注：该书可借";
				}
				else
				{
					return"图书编号为："+vaule.number+"  图书名字为："+vaule.BookName+"  图书作者为："+vaule.Writter+"  备注：该书不可借";
				}
			}
		}
		return "Not Found!";
     }*/
}
	
