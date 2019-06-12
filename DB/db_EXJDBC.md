```java
package lab.jdbc.biz;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Properties;

import lab.jdbc.entity.Book;
import lab.jdbc.util.BookUtil;

public class BookBiz {
	private ArrayList<Book> books;

	public BookBiz() {
		super();
	}

	public Connection dbCon() {
		Connection con = null;
		try {
			Properties prop = new Properties();
			prop.load(new FileInputStream("c:/workspace/day13/src/dbinfo.properties"));

			Class.forName(prop.getProperty("driver"));
			// System.out.println("driver loading 성공");
			con = DriverManager.getConnection(prop.getProperty("url"), prop.getProperty("user"),
					prop.getProperty("pwd"));
			// System.out.println("db connect 성공");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return con;
	}

	public void dbClose(Connection con, Statement stat, ResultSet rs) {
		try {
			if (rs != null)
				stat.close();
			if (stat != null)
				stat.close();
			if (con != null)
				con.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void printAllBooks() {
		Connection con = null;
		Statement stat = null;
		String sql = "select * from book";
		ResultSet rs = null;
		try {
			con = dbCon();
			stat = con.createStatement();
			rs = stat.executeQuery(sql);
			BookUtil.printHeader();
			while (rs.next()) {
				Book book = new Book();
				book.setIsbn(rs.getString("isbn"));
				book.setTitle(rs.getString("title"));
				book.setAuthor(rs.getString("author"));
				book.setPrice(rs.getInt("price"));
				book.setCategory(rs.getString("category"));
				book.setDescript(rs.getString("descript"));
				System.out.println(book);
			}
			BookUtil.printTail();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			dbClose(con, stat, rs);
		}
	}

	public void printAllNovels() {
		Connection con = null;
		Statement stat = null;
		String sql = "select * from book where isbn like 'N%'";
		ResultSet rs = null;
		try {
			con = dbCon();
			stat = con.createStatement();
			rs = stat.executeQuery(sql);
			BookUtil.printHeader();
			while (rs.next()) {
				Book book = new Book();
				book.setIsbn(rs.getString("isbn"));
				book.setTitle(rs.getString("title"));
				book.setAuthor(rs.getString("author"));
				book.setPrice(rs.getInt("price"));
				System.out.println(book);
			}
			BookUtil.printTail();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			dbClose(con, stat, rs);
		}
	}

	public void printAllMagazines() {
		Connection con = null;
		Statement stat = null;
		String sql = "select * from book where isbn like 'M%'";
		ResultSet rs = null;
		try {
			con = dbCon();
			stat = con.createStatement();
			rs = stat.executeQuery(sql);
			BookUtil.printHeader();
			while (rs.next()) {
				Book book = new Book();
				book.setIsbn(rs.getString("isbn"));
				book.setTitle(rs.getString("title"));
				book.setPrice(rs.getInt("price"));
				book.setCategory(rs.getString("category"));
				book.setDescript(rs.getString("descript"));
				System.out.println(book);
			}
			BookUtil.printTail();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			dbClose(con, stat, rs);
		}
	}

	public void printMagazineOneYearSubscription() {
		Connection con = null;
		Statement stat = null;
		String sql = "select * from book where isbn like 'M%'";
		ResultSet rs = null;
		try {
			con = dbCon();
			stat = con.createStatement();
			rs = stat.executeQuery(sql);
			System.out.println("--------------------");
			int num = 1;
			while (rs.next()) {
				System.out.println(num++ + ". " + rs.getString("title") + " : " + rs.getInt("price") * 12 + "원");
			}
			System.out.println("--------------------");
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			dbClose(con, stat, rs);
		}
	}

	public ArrayList<Book> searchNovelByAuthor(String author) {
		ArrayList<Book> searchBooks = new ArrayList();
		Connection con = null;
		PreparedStatement stat = null;
		String sql = "select * from book where isbn like 'N%' and author like ?";
		ResultSet rs = null;
		try {
			con = dbCon();
			stat = con.prepareStatement(sql);
			stat.setString(1, "%" + author + "%");
			rs = stat.executeQuery();
			while (rs.next()) {
				Book book = new Book();
				book.setIsbn(rs.getString("isbn"));
				book.setTitle(rs.getString("title"));
				book.setAuthor(rs.getString("author"));
				book.setPrice(rs.getInt("price"));
				searchBooks.add(book);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			dbClose(con, stat, rs);
		}
		return searchBooks;
	}

	public ArrayList<Book> searchNovelByPrice(int minPrice, int maxPrice) {
		ArrayList<Book> searchBooks = new ArrayList();
		Connection con = null;
		PreparedStatement stat = null;
		String sql = "select * from book where isbn like 'N%' and price between ? and ? ";
		ResultSet rs = null;
		try {
			con = dbCon();
			stat = con.prepareStatement(sql);
			stat.setInt(1, minPrice);
			stat.setInt(2, maxPrice);
			rs = stat.executeQuery();
			while (rs.next()) {
				Book book = new Book();
				book.setIsbn(rs.getString("isbn"));
				book.setTitle(rs.getString("title"));
				book.setAuthor(rs.getString("author"));
				book.setPrice(rs.getInt("price"));
				searchBooks.add(book);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			dbClose(con, stat, rs);
		}
		return searchBooks;

	}

	public int insertBook(Book newBook) {
		int rows = 0;
		Connection con = null;
		PreparedStatement stat = null;
		String novel = "insert into book (isbn, title, author, price) values (?,?,?,?)";
		String magazine = "insert into book (isbn, title,  price, category, descript) values (?,?,?,?,?)";
		try {
			con = dbCon();
			if (newBook.getIsbn().startsWith("N")) {
				stat = con.prepareStatement(novel);
				stat.setString(1, newBook.getIsbn());
				stat.setString(2, newBook.getTitle());
				stat.setString(3, newBook.getAuthor());
				stat.setInt(4, newBook.getPrice());
			} else {
				stat = con.prepareStatement(magazine);
				stat.setString(1, newBook.getIsbn());
				stat.setString(2, newBook.getTitle());
				stat.setInt(3, newBook.getPrice());
				stat.setString(4, newBook.getCategory());
				stat.setString(5, newBook.getDescript());
			}
			rows = stat.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			dbClose(con, stat, null);
		}
		return rows;
	}

	public int updateBook(Book modifyBook) {
		int rows = 0;
		Connection con = null;
		PreparedStatement stat = null;
		String novel = "update book  set price =? where isbn = ?";
		String magazine = "update book  set price =? , descript = ? where isbn = ?";
		try {
			con = dbCon();
			if (modifyBook.getIsbn().startsWith("N")) {
				stat = con.prepareStatement(novel);
				stat.setString(2, modifyBook.getIsbn());
				stat.setInt(1, modifyBook.getPrice());
			} else {
				stat = con.prepareStatement(magazine);
				stat.setString(3, modifyBook.getIsbn());
				stat.setInt(1, modifyBook.getPrice());
				stat.setString(2, modifyBook.getDescript());
			}
			rows = stat.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			dbClose(con, stat, null);
		}
		return rows;
	}

	public int deleteBook(String isbn) {
		int rows = 0;
		Connection con = null;
		PreparedStatement stat = null;
		String sql = "delete from book where isbn = ?";
		try {
			con = dbCon();
			stat = con.prepareStatement(sql);
			stat.setString(1, isbn);
			rows = stat.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			dbClose(con, stat, null);
		}
		return rows;
	}

}



==================================BookTest.java=====================
package lab.jdbc.test;

import java.util.ArrayList;

import lab.jdbc.biz.BookBiz;
import lab.jdbc.entity.Book;
import lab.jdbc.util.BookUtil;

public class BookTest {

	public static void main(String[] args) {
		BookBiz biz = new BookBiz();
		ArrayList<Book> books = null;
		Book book = null;
		while (true) {
			printMenu();
			System.out.print("## 메뉴 입력:");
			String menu = BookUtil.getUerInput();
			if (menu.equals("0")) {
				System.out.println("------------------------");
				System.out.println("프로그램을 종료합니다. Bye~");
				System.out.println("------------------------");
				break;
			}
			switch (menu) {
			case "1":
				biz.printAllBooks();
				break;
			case "2":
				biz.printAllMagazines();
				break;
			case "3":
				biz.printAllNovels();
				break;
			case "4":
				biz.printMagazineOneYearSubscription();
				break;
			case "5":
				System.out.print("> 검색할 저장명을 입력하세요:");
				String author = BookUtil.getUerInput();
				books = biz.searchNovelByAuthor(author.trim());
				BookUtil.printHeader();
				for (Book b : books)
					System.out.println(b);
				BookUtil.printTail();
				break;
			case "6":
				System.out.print("> 검색할 소설 가격의  최소값을 입력하세요:");
				int minPrice = Integer.parseInt(BookUtil.getUerInput());
				System.out.print("> 검색할 소설 가격의 최대값을 입력하세요:");
				int maxPrice = Integer.parseInt(BookUtil.getUerInput());
				books = biz.searchNovelByPrice(minPrice, maxPrice);
				BookUtil.printHeader();
				for (Book b : books)
					System.out.println(b);
				BookUtil.printTail();
				break;
			case "7":
				book = new Book();
				System.out.print("> ISBN 입력하세요:");
				book.setIsbn(BookUtil.getUerInput());
				System.out.print("> 책 제목을 입력하세요:");
				book.setTitle(BookUtil.getUerInput());
				System.out.print("> 가격 입력하세요:");
				book.setPrice(Integer.parseInt(BookUtil.getUerInput()));
				if (book.getIsbn().startsWith("N")) {
					System.out.print("> 저자를 입력하세요:");
					book.setAuthor(BookUtil.getUerInput());
				} else {
					System.out.print("> 카테고리을 입력하세요:");
					book.setCategory(BookUtil.getUerInput());
					System.out.print("> 설명을 입력하세요:");
					book.setDescript(BookUtil.getUerInput());
				}
				if (biz.insertBook(book) > 0) {
					System.out.println("새 책 정보 추가 완료!!!");
				}
				break;
			case "8":
				book = new Book();
				System.out.print("> ISBN 입력하세요:");
				book.setIsbn(BookUtil.getUerInput());
				System.out.print("> 가격 입력하세요:");				
				book.setPrice(Integer.parseInt(BookUtil.getUerInput()));
				if(book.getIsbn().startsWith("M")) {
				System.out.print("> 설명을 입력하세요:");
				book.setDescript(BookUtil.getUerInput());
				}
				if (biz.updateBook(book) > 0) {
					System.out.println("책 정보 수정 완료!!!");
				}
				break;
			case "9":
				System.out.print("> ISBN 입력하세요:");
				String isbn = BookUtil.getUerInput();
				if (biz.deleteBook(isbn) > 0) {
					System.out.println("책 정보 삭제 완료!!!");
				}
				break;
			}// switch end
		} // while end

	}

	public static void printMenu() {
		System.out.println("===<< 도서 정보 프로그램 >>===");
		System.out.println("1. 전체 도서 정보 조회");
		System.out.println("2. 전체 잡지 조회");
		System.out.println("3. 전체 소설 조회");
		System.out.println("4. 잡지 연간 구독료 조회");
		System.out.println("5. 소설 저장명 검색");
		System.out.println("6. 소설 가격 검색(최소값 ~ 최대값)");
		System.out.println("7. 새 책정보 추가");
		System.out.println("8. 책정보 수정");
		System.out.println("9. 책정보 삭제");
		System.out.println("0. 시스템 종료");
		System.out.println("========================");

	}

}




============성적처리 DB 연동 =======================
[Student]
create table student (
studentNo   varchar2(5)  constraint student_pk primary key,
studentName varchar2(15)   not null,
c           number(3) default 0,
linux       number(3) default 0,
java        number(3) default 0,
careerYears number(2),
internYn     char(1) check (internYn in ('Y', 'N'))
);

alter table student add (average  number(5,2), 
                     pass char(1) check (pass in ('Y', 'N')));

alter table student modify (c           number(3) default 0,
linux       number(3) default 0,
java        number(3) default 0);

-전체 학생 목록 검색
-합격 학생 검색
-불합격 학생 검색
-학생정보 추가
-과목 점수 수정
-학생정보 삭제


GradeManager (클래스)
public Connection dbCon(){}
public void dbClose(Connection con, Statement stat, ResultSet rs){}
public ArrayList<Student> getAllStudent(){}
public ArrayList<Student> getPassStudent(){}
public ArrayList<Student> getFailStudent(){}
public int  insertStudent(Student s){}
public int  updateStudent(Student s){} //과목 점수 , 경력 , 인턴여부만 변경
public int  deleteStudent(String sno){} //학번으로 레코드 삭제
public Student  searchStudent(String sno){} //학번으로 수강생 검색



===============================CommonUtil.java======================================

package lab.jdbc.util;

import java.util.Scanner;

public class CommonUtil {
   public static String getUerInput() {
	   Scanner input = new Scanner(System.in);
	   return input.nextLine();
   }
   public static void printHead() {
	   System.out.println("=======================================================================================");
	   System.out.println("사번      이름   신입/경력  인턴여부  경력년수  C   Linux   Java   총점   평균   합격여부");;
	   System.out.println("=======================================================================================");
   }  
   public static void printTail() {
	   System.out.println("=======================================================================================");
   }
}


=======================GradeTest.java====================================
package lab.jdbc.test;


import java.util.ArrayList;

import lab.jdbc.biz.GradeManager;
import lab.jdbc.entity.Student;
import lab.jdbc.util.CommonUtil;

public class GradeTest {

	public static void main(String[] args) {
		GradeManager manager = new GradeManager();
		ArrayList<Student> students = null;
		Student std  = null;
		while (true) {
			printMenu();
			System.out.print("## 메뉴 입력:");
			String menu = CommonUtil.getUerInput();
			if (menu.equals("9")) {
				System.out.println("------------------------");
				System.out.println("프로그램을 종료합니다. Bye~");
				System.out.println("------------------------");
				break;
			}
			switch (menu) {
			case "1":
				students = manager.getAllStudent();
				CommonUtil.printHead();
				for(Student s : students) {
					System.out.println(s);
				}
				CommonUtil.printTail();
				break;
			case "2":
				students = manager.getPassStudent();
				CommonUtil.printHead();
				for(Student s : students) {
					System.out.println(s);
				}
				CommonUtil.printTail(); 
				break;
			case "3":
				students = manager.getFailStudent();
				CommonUtil.printHead();
				for(Student s : students) {
					System.out.println(s);
				}
				CommonUtil.printTail(); 
				break; 
				 
			case "4":	
				 std = new Student();
				System.out.print("> 사번 입력하세요:");
				  std.setStudentNo(CommonUtil.getUerInput());
				System.out.print("> 이름을 입력하세요:");
				std.setStudentName(CommonUtil.getUerInput());
				System.out.print("> C 점수를 입력하세요:");
				std.setC(Integer.parseInt(CommonUtil.getUerInput())); 
				System.out.print("> JAVA 점수를 입력하세요:");
				std.setJava(Integer.parseInt(CommonUtil.getUerInput()));
				System.out.print("> Linux 점수를 입력하세요:");
				std.setLinux(Integer.parseInt(CommonUtil.getUerInput()));
				if(std.getStudentNo().startsWith("1")) {
					System.out.print("> 인턴여부(Y/N)을 입력하세요:");
					std.setInternYn(CommonUtil.getUerInput());
				}else {
					System.out.print("> 경력년수을 입력하세요:");
					std.setCareerYears(Integer.parseInt(CommonUtil.getUerInput()));
				}
				if(manager.insertStudent(std)>0) {
					System.out.println("수강생 정보 추가하였습니다.");
				}
				break; 
				 
			case "5":
				std = new Student();
				System.out.print("> 변경할 사번 입력하세요:");
				  std.setStudentNo(CommonUtil.getUerInput());
				 
				  
				System.out.print("> C 점수를 입력하세요:");
				std.setC(Integer.parseInt(CommonUtil.getUerInput())); 
				System.out.print("> JAVA 점수를 입력하세요:");
				std.setJava(Integer.parseInt(CommonUtil.getUerInput()));
				System.out.print("> Linux 점수를 입력하세요:");
				std.setLinux(Integer.parseInt(CommonUtil.getUerInput()));
				if(std.getStudentNo().startsWith("1")) {
					System.out.print("> 인턴여부(Y/N)을 입력하세요:");
					std.setInternYn(CommonUtil.getUerInput());
				}else {
					System.out.print("> 경력년수을 입력하세요:");
					std.setCareerYears(Integer.parseInt(CommonUtil.getUerInput()));
				}
				if(manager.updateStudent(std)>0) {
					System.out.println("수강생 정보 수정하였습니다.");
				}
				break; 
			case "6":
				System.out.print("> 삭제할 사번 입력하세요:");
				String sno = CommonUtil.getUerInput();
				if(manager.deleteStudent(sno)>0) {
					System.out.println("수강생 정보 삭제하였습니다.");
				}  
				break;
			case "7":
				System.out.print("> 검색할 사번 입력하세요:");
				sno = CommonUtil.getUerInput();
				std = manager.searchStudent(sno);
				if(std!=null) {
					CommonUtil.printHead();					 
					System.out.println(std);				 
					CommonUtil.printTail();  
				}  
				break;			 
			 
			}// switch end
		} // while end

	}

	public static void printMenu() {
		System.out.println("===<< 성적관리 시스템 메뉴 >>===");
		System.out.println("1. 전체 수강생 조회");
		System.out.println("2. 합격 수강생 조회");
		System.out.println("3. 불합격 수강생 조회");
		System.out.println("4. 수강생 정보 추가");
		System.out.println("5. 수강생 정보 수정");
		System.out.println("6. 수강생 정보 삭제");
		System.out.println("7. 학번으로 수강생 정보 조회");		 
		System.out.println("9. 시스템 종료");
		System.out.println("===============================");

	}

}


==================================================================
public ArrayList<Student> getAllStudent(){
		ArrayList<Student> students = new ArrayList();
		Connection con = null;
		Statement stat = null;
		String sql = "select * from Student";
		ResultSet rs = null;
		try {
			con = dbCon();
			stat = con.createStatement();
			rs = stat.executeQuery(sql);		 
			while (rs.next()) {
				Student s1 = new Student();
				s1.setStudentNo(rs.getString("studentNo"));
				s1.setStudentName(rs.getString("studentName")); 
				s1.setC(rs.getInt("c"));
				s1.setJava(rs.getInt("java"));
				s1.setLinux(rs.getInt("linux"));
				s1.setCareerYears(rs.getInt("careerYears"));
				s1.setInternYn(rs.getString("internYn"));
				s1.setPass(rs.getString("pass"));
				s1.setAverage(rs.getDouble("average"));
				students.add(s1);
			}			 
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			dbClose(con, stat, rs);
		}
		return students;
	}





public int  insertStudent(Student s){
		int rows = 0;
		Connection con = null;
		PreparedStatement stat = null;
		String sql = "insert into student (studentno, studentname, c, java, linux, careeryears, internyn, average, pass) values (?,?,?,?,?,?,?, ?, ?)";
		try {
			con = dbCon();
			stat = con.prepareStatement(sql);
			stat.setString(1, s.getStudentNo());
			stat.setString(2, s.getStudentName());
			stat.setInt(3, s.getC());
			stat.setInt(4, s.getJava());
			stat.setInt(5, s.getLinux());			
			if(s.getStudentNo().startsWith("1")) {
				stat.setString(7, s.getInternYn());
				stat.setInt(6, 0);
			}else {
				stat.setInt(6, s.getCareerYears());
				stat.setString(7, "N");
			}
			double avg = (s.getC()+s.getJava()+s.getLinux())/3.0;			 
			stat.setDouble(8, Double.parseDouble( String.format("%.2f", avg)));
			if(s.getStudentNo().startsWith("1") && avg>=70) {
				stat.setString(9, "Y");
			}else if(s.getStudentNo().startsWith("2") && avg>=80) {
				stat.setString(9, "Y");
			}else {
				stat.setString(9, "N");
			}			
			rows = stat.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			dbClose(con, stat, null);
		}
		return rows;
	}


//과목 점수 , 경력 , 인턴여부만 변경
//과목 점수 , 경력 , 인턴여부만 변경
	public int  updateStudent(Student s){
		int rows = 0;
		Connection con = null;
		PreparedStatement stat = null;
		String sql = "update student set c=?, java=?, linux=?, careeryears=?, internyn=? , average=? , pass=? where studentno=?  ";
		try {
			con = dbCon();			
			stat = con.prepareStatement(sql);
			stat.setString(8, s.getStudentNo());			
			stat.setInt(1, s.getC());
			stat.setInt(2, s.getJava());
			stat.setInt(3, s.getLinux());
			if(s.getStudentNo().startsWith("1")) {
			stat.setInt(4, 0);
			stat.setString(5, s.getInternYn());
			}else {
				stat.setInt(4, s.getCareerYears());
				stat.setString(5, "N");
			}			
			double avg = (s.getC()+s.getJava()+s.getLinux())/3.0;			 
			stat.setDouble(6, Double.parseDouble( String.format("%.2f", avg)));		 
			if(s.getStudentNo().startsWith("1") && avg>=70.0) {
				stat.setString(7, "Y");
			}else if(s.getStudentNo().startsWith("2") && avg>=80.0) {
				stat.setString(7, "Y");
			}else {
				stat.setString(7, "N");
			}				
			rows = stat.executeUpdate();			
		} catch (Exception e) {			 
			e.printStackTrace();			
		} finally {
			dbClose(con, stat, null);
		}
		return rows;
	} 
	

=================Student.java===============================



package lab.jdbc.entity;

public class Student {
	private String studentNo ;
	private String studentName ;
	private int c  ;
	private int linux  ;
	private int java  ;
	private int careerYears ;
	private String internYn ;
	private double average;
	private String pass;
	
	public Student() {
		super();
	}
	public String getStudentNo() {
		return studentNo;
	}
	public void setStudentNo(String studentNo) {
		this.studentNo = studentNo;
	}
	public String getStudentName() {
		return studentName;
	}
	public void setStudentName(String studentName) {
		this.studentName = studentName;
	}
	public int getC() {
		return c;
	}
	public void setC(int c) {
		this.c = c;
	}
	public int getLinux() {
		return linux;
	}
	public void setLinux(int linux) {
		this.linux = linux;
	}
	public int getJava() {
		return java;
	}
	public void setJava(int java) {
		this.java = java;
	}
	public int getCareerYears() {
		return careerYears;
	}
	public void setCareerYears(int careerYears) {
		this.careerYears = careerYears;
	}
	public String getInternYn() {
		return internYn;
	}
	public void setInternYn(String internYn) {
		this.internYn = internYn;
	}
	public double getAverage() {
		return average;
	}
	public void setAverage(double average) {
		this.average = average;
	}
	public String getPass() {
		return pass;
	}
	public void setPass(String pass) {
		this.pass = pass;
	}
	@Override
	public String toString() {
		StringBuffer info = new StringBuffer();
		info.append(studentNo);
		info.append("  ");
		info.append(studentName);
		info.append("  ");
		if(this.studentNo.startsWith("1")) {
			info.append("신입");
		}else if(this.studentNo.startsWith("2")) {
			info.append("경력");
		}
		if(internYn!=null) {
		info.append(internYn);
		}else {
			info.append("\t\t");
		}
		if(careerYears>0) {
		info.append(careerYears);
		}else {
			info.append("\t\t");
		}
		info.append("  ");
		info.append(c);
		info.append("  ");
		info.append(linux);
		info.append("  ");
		info.append(java );
		info.append("  ");		
		info.append((c+java+linux));
		info.append("  ");	
		info.append(average);
		info.append("  ");	
		if(pass.equalsIgnoreCase("Y")) {
			info.append("합격");
		}else {
			info.append("불합격");
		}
		 
		 
		 return info.toString();
	}
	
	
	
	
}
```

