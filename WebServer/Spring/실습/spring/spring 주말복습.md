```
drop table products purge;

create table products (
prodnum    number(8)  primary key ,   제품번호
pname      varchar2(30),   --상품 이름
category   varchar2(30), --상품 분류
description  varchar2(1000),--상품 특성, 설명
filename    varchar2(100),        ----이미지 파일 경로
manufacturer  varchar2(50), --제조사
unitPrice    number(7),     --개당 가격
unitsInStock   number(5)    --제고
);

 


--샘플 데이터 
insert into products ( prodnum ,pname, unitPrice, Description, Category, Manufacturer, UnitsInStock,  Condition,  Filename )
values ( 'P1234', 'iPhone 6s',800000, '4.7-inch, 1334X750 Renina HD display 8-megapixel iSight Camera,);
'Smart Phone','Apple', 1000, 'New', 'P1234.png');

insert into products ( prodnum ,pname, unitPrice, Description, Category, Manufacturer, UnitsInStock,  Condition,  Filename )
values ( 'P1235','LG PC 그램',
1500000,
'13.3-inch, IPS LED display, 5rd Generation Intel Core processors',
'Notebook',
'LG',
1000,
'Refurbished',
'P1235.png');

insert into products ( prodnum ,pname, unitPrice, Description, Category, Manufacturer, UnitsInStock,  Condition,  Filename )
values ( 'P1236',
'Galaxy Tab S',
900000,
'212.8*125.6*6.6mm,  Super AMOLED display, Octa-Core processor',
'Tablet',
'Samsung',
1000,
'Old',
'P1236.png');
commit;


select * from products;

select * from products where prodnum =? ;

select * from products where unitPrice between ? and ?;


update products set unitPrice = ?, UnitsInStock=?  where prodnum = ? ;

delete from products  where prodnum = ? ;
 
 
===========================================================
1. maven 프로젝트 생성 (pom.xml)
2. mapper 파일 설정  (ProductMapper.xml)
3. spring 설정 파일 설정  (application.xml)
4. 엔티티 클래스 생성
5. DAO 클래스 생성
6. 서비스 클래스 생성
7. test 클래스 생성 실행 결과 보기 
 
 public class SpringMybatisTest {

	public static void main(String[] args) {
		String[] configs = new String[]{"application.xml"};
		ApplicationContext context = 
				   new ClassPathXmlApplicationContext(configs);
		UserService service = 
				context.getBean("productService", ProductService.class);
		System.out.println("#######전체 상품 목록 ###########");
		List<Product> lists = service.findProductList();
		Iterator<Product> iter = lists.iterator();
		while (iter.hasNext()) {
			Product u = iter.next();
			System.out.println(u);
		}
		
		Product p = new Product();
        p.setProductId("B1000");
    	p.setPname("Spring과 MyBatis");
    	p.setCategory("Book");
    	p.setDescription("프로젝트로 배우는 프레임워크");
    	p.setFilename("spring.jpeg");
    	p.setManufacturer("멀티캠퍼스");
    	p.setUnitPrice(10000);
    	p.setUnitsInStock(300); 
		 
		System.out.println("insert rows = >" + service.addProduct(p));		
		System.out.println("#######s3 아이디 한행 검색 ###########"); 
		System.out.println(service.findProduct("s3"));		
		
		p.setUnitPrice(15000);
    	p.setUnitsInStock(200);  
    	p.setFilename('spring-mybatis.jpeg');
		System.out.println("update :s3 =>"+service.updateProduct(p));
		System.out.println(service.findProduct"s3"));
		System.out.println("delete :s3 =>"+service.removeProduct("s3"));
		System.out.println("#######전체 상품목록 ###########");
		 lists = service.findProductList();
		 iter = lists.iterator();
		while (iter.hasNext()) {
			Product u = iter.next();
			System.out.println(u);
		}
	}
 
 
 
 
```