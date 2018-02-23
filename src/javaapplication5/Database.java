package javaapplication5;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Database 
{
    private String polaczenieURL = "jdbc:mysql://localhost/biblioteka?user=root&password=";
    private String query;

    public String getQuery() { return query; }
    public void setQuery(String query) { this.query = query; }
    
    private String dane;
    private Connection conn = null;
    
    
    
    public Database()
    {

        try
        {
            conn = DriverManager.getConnection(polaczenieURL);
            Class.forName("com.mysql.jdbc.Driver");
        }
        catch (Exception ex){System.out.println(ex);}
    }
    
    
    

    
    public String query(String query)
    {
        try
        {
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            while(rs.next()) 
            {
                dane = rs.getString(1);
            }
        }
        catch (SQLException ex) {System.out.println(ex);}
        return dane;
    }
    
    public void usunDodaj(String query)
    {
        try
        {
            Statement stmt = conn.createStatement();
            stmt.execute(query);
        }
        catch (SQLException ex) {System.out.println(ex);}
    }
    
 }
