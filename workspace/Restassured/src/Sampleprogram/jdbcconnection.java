package Sampleprogram;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;


public class jdbcconnection
{
	Connection con = null;
	Statement stn = null;
	ResultSet resultset = null;
    @BeforeTest
	public void setconnection()throws Exception
	{
		Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");

		try {
			DriverManager.registerDriver(new com.microsoft.sqlserver.jdbc.SQLServerDriver());
			String Connectionurl= "jdbc:sqlserver://phnx-sha-east-us-sql.database.windows.net;databaseName=phnx_dit_db;user=vericheck;password=Iwa$!inAtlanta2017";
			con  = DriverManager.getConnection(Connectionurl);
			if (con != null) 
			{
				System.out.println("Connected");

			}
		}
		catch (SQLException ex) 
		{
			ex.printStackTrace();
		}

	}
	@Test
	public void dbquery() throws Exception 
	{	
		String sql ="Select * from [Company].[Company]";
		stn = con.createStatement();
		resultset = stn.executeQuery(sql);
		while(resultset.next()){
			System.out.println(resultset.getString("type")+" "+resultset.getString("Name")+" "+resultset.getString("doingbusinessas")+" "+resultset.getString("taxid"));
		}

		if(resultset !=null)
		{
			try 
			{
				resultset.close();
			}
			catch (Exception e)
			{

			}		
		}
	}
}






