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
	int row;            //���ڱ�Ƕ�����.xls�ļ��е��кţ������޸��ļ��е�ͼ����Ϣ
	int IsLended;   //ֵΪ1��ɽ裬Ϊ0�򲻿ɽ�
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
	public File bookExcel=new File("ͼ����Ϣ.xls");
	public Vector<Book>Books=new Vector<Book>();
	public BooksList() throws BiffException, IOException 
	{
		Workbook excel = Workbook.getWorkbook(bookExcel);
		Sheet a=excel.getSheet(0);
		int k=a.getRows()-1;     //��ȥ����
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
	public void update() throws BiffException, IOException           /* ����BooksList��*/
	{
		Workbook excel = Workbook.getWorkbook(bookExcel);
		Sheet a=excel.getSheet(0);
		int k=a.getRows()-1;     //��ȥ����
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
			    System.out.println("ͼ����Ϊ��"+vaule.number+"  ͼ������Ϊ��"+vaule.BookName+"  ͼ������Ϊ��"+vaule.Writter+"  ��ע������ɽ�");
			}
			else
			{
				System.out.println("ͼ����Ϊ��"+vaule.number+"  ͼ������Ϊ��"+vaule.BookName+"  ͼ������Ϊ��"+vaule.Writter+"  ��ע�����鲻�ɽ�");
			}
		}
	}
	 //���������ı�ͼ��״̬,�� k��ѡ�������飨1Ϊ���飬0Ϊ���飩
	public void bookChange(String x,int k) throws BiffException, IOException, RowsExceededException, WriteException
	{
		Book vaule=null;
		for(int i=0;i<this.Books.size();i++)
		{
			vaule=this.Books.get(i);
			if(vaule.BookName.equals(x))      /* �ַ����ıȽϲ���ʹ��==���൱�ڱȽ����ǵĵ�ַ����Ӧ����equals()����*/
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
				this.update(); //�޸���֮�����BooksList��
				return; 
			}
		}
		System.out.println("δ�ҵ����飡");
	}
	//���ݱ�Ÿı�ͼ��״̬
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
		System.out.println("δ�ҵ����飡");
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
					return "ͼ����Ϊ��"+vaule.number+"  ͼ������Ϊ��"+vaule.BookName+"  ͼ������Ϊ��"+vaule.Writter+"  ��ע������ɽ�";
				}
				else
				{
					return"ͼ����Ϊ��"+vaule.number+"  ͼ������Ϊ��"+vaule.BookName+"  ͼ������Ϊ��"+vaule.Writter+"  ��ע�����鲻�ɽ�";
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
					return "ͼ����Ϊ��"+vaule.number+"  ͼ������Ϊ��"+vaule.BookName+"  ͼ������Ϊ��"+vaule.Writter+"  ��ע������ɽ�";
				}
				else
				{
					return"ͼ����Ϊ��"+vaule.number+"  ͼ������Ϊ��"+vaule.BookName+"  ͼ������Ϊ��"+vaule.Writter+"  ��ע�����鲻�ɽ�";
				}
			}
		}
		return "Not Found!";
     }*/
}
	
