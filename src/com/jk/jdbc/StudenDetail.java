package com.jk.jdbc;
import java.sql.Connection;

import java.sql.ResultSet;
import java.sql.Statement;
import java.io.IOException;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Scanner;
/**
 * 
 * @author jitendra kumar
 *
 */

public class StudenDetail {

	public static void main(String[] args){
boolean  flag=false;
Scanner sc=null;
String desg1=null,desg2=null,desg3=null;
String query=null;
String cond=null;
Connection con=null;
ResultSet rs=null;
Statement st=null;
try {
	//Reading input
	sc=new Scanner(System.in);
	if(sc!=null) {
		System.out.print("Enter Designation 1: ");
		desg1=sc.next().toUpperCase();
	
		System.out.print("Enter Designation 2: ");
		desg2=sc.next().toUpperCase();
		System.out.print("Enter Designation 3: ");
		desg3=sc.next().toUpperCase();
	}
		//Convert input value as required for the sql query
		cond="('"+desg1+"','"+desg2+"','"+desg3+"')";
		System.out.println(cond);
        //Register JDBC Driver
		Class.forName("oracle.jdbc.driver.OracleDriver");
        //Establishing the Connection
		con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl","hr","jaygangwar");
        //Create Statement Object
		if(con!=null);
        st=con.createStatement();
        /*********prepare SQL Query *********
        Select empno,ename,job,sal from emp where job in('CLERK','MANAGER','SALESMAN');******/

       query="SELECT EMPNO,ENAME,JOB,SAL FROM EMP WHERE JOB in"+cond;
       System.out.println(query);
       //Send and Execute Query
       if(st!=null)
       rs=st.executeQuery(query);
        //Gather and Process Result
       if(rs!=null){
       while(rs.next()){
       flag=true;
       System.out.println(rs.getInt(1)+" "+rs.getString(2)+" "+rs.getString(3)+" "+rs.getInt(4));
   }// close of while

if(flag==false)
System.out.println("Record Not Found");
} // close of if
}// close of try

catch(SQLException se){
se.printStackTrace();
}

catch(ClassNotFoundException cnf){
cnf.printStackTrace();
}

catch(Exception e){
System.out.println(e.toString());
}

finally{
      //********close jdbc object*********
try{
   if(con!=null)
con.close();
}
catch(SQLException se){
se.printStackTrace();
}

try{
   if(rs!=null)
rs.close();
}
catch(SQLException se){
se.printStackTrace();
}

try{
   if(st!=null)
st.close();
}
catch(SQLException se){
se.printStackTrace();
}

try{
   if(sc!=null)
sc.close();
}
catch(Exception se){
se.printStackTrace();
}

}//close of finally
	}//close of main
}//close of class


/******** Compile & Run Program **********
javac -d . StudentDetail.java
java com.nt.jdbc.StudentDetail*****/

/**output
Enter Designation 1: MANAGER
Enter Designation 2: SALESMAN
Enter Designation 3: CLERK
('MANAGER','SALESMAN','CLERK')
SELECT EMPNO,ENAME,JOB,SAL FROM EMP WHERE JOB in('MANAGER','SALESMAN','CLERK')
101 Jeetu MANAGER 1000
102 Amit SALESMAN 20000
103 Ayush CLERK 10000
104 Shivam CLERK 19999
105 Ashish MANAGER 200001**/