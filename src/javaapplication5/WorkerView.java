package javaapplication5;

import java.awt.Color;
import javax.swing.table.DefaultTableModel;
import javax.swing.Timer;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JTable;


public class WorkerView extends javax.swing.JFrame {

        private Database db = new Database();
        private String selectedData = "";
        private Date dzis = new Date();
        private DefaultTableModel dtm;
        private DefaultTableModel dtm2;
        private DefaultTableModel dtm3;
        private DefaultTableModel dtm4;
        private DefaultTableModel dtm5;
        private DefaultTableModel dtm6;
        private String idk;
        private String query="";
        private String imie;
        private String nazwisko;
        
        private boolean wszystko=true;
        private boolean ksiazki=false;
        private boolean czasopisma=false;
        private boolean filmy=false;
        private Connection conn = null;
        private Statement stmt;
        
        public WorkerView() {
        initComponents();  
    }
        
        
        
    public WorkerView(String imie, String nazwisko) throws ParseException, SQLException
    {
        initComponents();
        conn = DriverManager.getConnection("jdbc:mysql://localhost/biblioteka?user=root&password=");
        stmt = conn.createStatement();
        this.imie=imie;
        this.nazwisko=nazwisko;

        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
        this.setTitle("Biblioteka : pracownik");
        
        jLabel2.setText(imie+" "+nazwisko);
        idk = db.query("select idk from klient where imie='"+imie+"' and nazwisko='"+nazwisko+"'");
                
        jLabel6.setText("yyyy/MM/dd "+dateFormat.format(dzis));
        int delay = 1000; //milliseconds
        ActionListener taskPerformer = new ActionListener() 
        {
            public void actionPerformed(ActionEvent evt) 
            {
                String date = new java.text.SimpleDateFormat("HH:mm:ss").format(new java.util.Date(System.currentTimeMillis()));
                jLabel4.setText(date);
            }
        };
        Timer t = new Timer(delay, taskPerformer);
        t.start();
        
        
        dtm = new DefaultTableModel(0, 0) { @Override public boolean isCellEditable(int row, int column) { return false; } };      
        String[] cols = { "IDKS", "Tytuł", "Autor", "Gatunek", "Rok wyd", "Typ" };
        dtm.setColumnIdentifiers(cols);
        
        dtm2 = new DefaultTableModel(0, 0) { @Override public boolean isCellEditable(int row, int column) { return false; } };    
        String[] cols2 = { "IDWYP", "IDKS", "Data wyp", "Data zwr", "IDK" };
        dtm2.setColumnIdentifiers(cols2);
        
        dtm3 = new DefaultTableModel(0, 0) { @Override public boolean isCellEditable(int row, int column) { return false; } };    
        String[] cols3 = { "IDK", "Imie", "Nazwisko" };
        dtm3.setColumnIdentifiers(cols3);
        
        dtm4 = new DefaultTableModel(0, 0) { @Override public boolean isCellEditable(int row, int column) { return false; } };    
        String[] cols4 = { "IDA", "Imie", "Nazwisko" };
        dtm4.setColumnIdentifiers(cols4);
        
        dtm5 = new DefaultTableModel(0, 0) { @Override public boolean isCellEditable(int row, int column) { return false; } };    
        String[] cols5 = { "IDG", "Nazwa" };
        dtm5.setColumnIdentifiers(cols5);
        
        dtm6 = new DefaultTableModel(0, 0) { @Override public boolean isCellEditable(int row, int column) { return false; } };    
        String[] cols6 = { "IDWYP", "IDKS", "data_wyp", "data_zwr", "idp", "idk", "status" };
        dtm6.setColumnIdentifiers(cols6);
        
        
        


		// Create a new table instance
		jTable1.setModel(dtm);
                
                
                query="select * from zbior";
                ResultSet rs = stmt.executeQuery(query);
                while(rs.next())
                {
                    dtm.addRow( new Object[] {rs.getString(1), rs.getString(2), db.query("select nazwisko from autor where ida="+rs.getString(3))+" "+db.query("select imie from autor where ida="+rs.getString(3)), db.query("select nazwa from gatunek where idg="+rs.getString(4)),rs.getString(5), rs.getString(6) } );
                }
                
              
  
jTable1.getColumnModel().getColumn(0).setMinWidth(30);
jTable1.getColumnModel().getColumn(0).setMaxWidth(30);
jTable1.getColumnModel().getColumn(0).setPreferredWidth(30);
jTable1.getColumnModel().getColumn(1).setMinWidth(200);
jTable1.getColumnModel().getColumn(1).setMaxWidth(200);
jTable1.getColumnModel().getColumn(1).setPreferredWidth(200);
jTable1.getColumnModel().getColumn(2).setMinWidth(160);
jTable1.getColumnModel().getColumn(2).setMaxWidth(160);
jTable1.getColumnModel().getColumn(2).setPreferredWidth(160);
jTable1.getColumnModel().getColumn(3).setMinWidth(80);
jTable1.getColumnModel().getColumn(3).setMaxWidth(80);
jTable1.getColumnModel().getColumn(3).setPreferredWidth(80);
jTable1.getColumnModel().getColumn(4).setMinWidth(50);
jTable1.getColumnModel().getColumn(4).setMaxWidth(50);
jTable1.getColumnModel().getColumn(4).setPreferredWidth(50);
jTable1.getColumnModel().getColumn(5).setMinWidth(80);
jTable1.getColumnModel().getColumn(5).setMaxWidth(80);
jTable1.getColumnModel().getColumn(5).setPreferredWidth(80);
        
        
jTable1.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

		

// Create a new table instance
		jTable3.setModel(dtm2);
                
                
                query="select * from wypozyczenia where status='ocz'";
                ResultSet rs2 = stmt.executeQuery(query);
                while(rs2.next())
                {
                    dtm2.addRow( new Object[] {rs2.getString(1), rs2.getString(2), rs2.getString(3), rs2.getString(4), rs2.getString(6) } );
                }
                
              
  
jTable3.getColumnModel().getColumn(0).setMinWidth(50);
jTable3.getColumnModel().getColumn(0).setMaxWidth(50);
jTable3.getColumnModel().getColumn(0).setPreferredWidth(50);
jTable3.getColumnModel().getColumn(1).setMinWidth(50);
jTable3.getColumnModel().getColumn(1).setMaxWidth(50);
jTable3.getColumnModel().getColumn(1).setPreferredWidth(50);
jTable3.getColumnModel().getColumn(2).setMinWidth(70);
jTable3.getColumnModel().getColumn(2).setMaxWidth(70);
jTable3.getColumnModel().getColumn(2).setPreferredWidth(70);
jTable3.getColumnModel().getColumn(3).setMinWidth(70);
jTable3.getColumnModel().getColumn(3).setMaxWidth(70);
jTable3.getColumnModel().getColumn(3).setPreferredWidth(70);      
jTable3.getColumnModel().getColumn(4).setMinWidth(50);
jTable3.getColumnModel().getColumn(4).setMaxWidth(50);
jTable3.getColumnModel().getColumn(4).setPreferredWidth(50);    
jTable3.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

                //tabela klient
		jTable2.setModel(dtm3);
                query="select * from klient";
                ResultSet rs3 = stmt.executeQuery(query);
                while(rs3.next())
                {
                    dtm3.addRow( new Object[] {rs3.getString(1), rs3.getString(4), rs3.getString(5) } );
                }
jTable2.getColumnModel().getColumn(0).setMinWidth(30);
jTable2.getColumnModel().getColumn(0).setMaxWidth(30);
jTable2.getColumnModel().getColumn(0).setPreferredWidth(30);
jTable2.getColumnModel().getColumn(1).setMinWidth(90);
jTable2.getColumnModel().getColumn(1).setMaxWidth(90);
jTable2.getColumnModel().getColumn(1).setPreferredWidth(90);
jTable2.getColumnModel().getColumn(2).setMinWidth(90);
jTable2.getColumnModel().getColumn(2).setMaxWidth(90);
jTable2.getColumnModel().getColumn(2).setPreferredWidth(90);
jTable2.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

                //tabela autor
		jTable5.setModel(dtm4);
                query="select * from autor";
                ResultSet rs4 = stmt.executeQuery(query);
                while(rs4.next())
                {
                    dtm4.addRow( new Object[] {rs4.getString(1), rs4.getString(2), rs4.getString(3) } );
                }

jTable5.getColumnModel().getColumn(0).setMinWidth(30);
jTable5.getColumnModel().getColumn(0).setMaxWidth(30);
jTable5.getColumnModel().getColumn(0).setPreferredWidth(30);
jTable5.getColumnModel().getColumn(1).setMinWidth(90);
jTable5.getColumnModel().getColumn(1).setMaxWidth(90);
jTable5.getColumnModel().getColumn(1).setPreferredWidth(90);
jTable5.getColumnModel().getColumn(2).setMinWidth(90);
jTable5.getColumnModel().getColumn(2).setMaxWidth(90);
jTable5.getColumnModel().getColumn(2).setPreferredWidth(90);		
jTable5.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

                //tabela gatunek
                jTable4.setModel(dtm5);
                query="select * from gatunek";
                ResultSet rs5 = stmt.executeQuery(query);
                while(rs5.next())
                {
                    dtm5.addRow( new Object[] {rs5.getString(1), rs5.getString(2) } );
                }

jTable4.getColumnModel().getColumn(0).setMinWidth(30);
jTable4.getColumnModel().getColumn(0).setMaxWidth(30);
jTable4.getColumnModel().getColumn(0).setPreferredWidth(30);
jTable4.getColumnModel().getColumn(1).setMinWidth(100);
jTable4.getColumnModel().getColumn(1).setMaxWidth(100);
jTable4.getColumnModel().getColumn(1).setPreferredWidth(100);
jTable4.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);



                //tabela gatunek
                jTable6.setModel(dtm6);
                query="select * from wypozyczenia";
                ResultSet rs6 = stmt.executeQuery(query);
                while(rs6.next())
                {
                    dtm6.addRow( new Object[] {rs6.getString(1), rs6.getString(2), rs6.getString(3), rs6.getString(4), rs6.getString(5), rs6.getString(6), rs6.getString(7) } );
                }

jTable6.getColumnModel().getColumn(0).setMinWidth(40);
jTable6.getColumnModel().getColumn(0).setMaxWidth(40);
jTable6.getColumnModel().getColumn(0).setPreferredWidth(40);
jTable6.getColumnModel().getColumn(1).setMinWidth(30);
jTable6.getColumnModel().getColumn(1).setMaxWidth(30);
jTable6.getColumnModel().getColumn(1).setPreferredWidth(30);
jTable6.getColumnModel().getColumn(2).setMinWidth(80);
jTable6.getColumnModel().getColumn(2).setMaxWidth(80);
jTable6.getColumnModel().getColumn(2).setPreferredWidth(80);
jTable6.getColumnModel().getColumn(3).setMinWidth(80);
jTable6.getColumnModel().getColumn(3).setMaxWidth(80);
jTable6.getColumnModel().getColumn(3).setPreferredWidth(80);
jTable6.getColumnModel().getColumn(4).setMinWidth(30);
jTable6.getColumnModel().getColumn(4).setMaxWidth(30);
jTable6.getColumnModel().getColumn(4).setPreferredWidth(30);
jTable6.getColumnModel().getColumn(5).setMinWidth(30);
jTable6.getColumnModel().getColumn(5).setMaxWidth(30);
jTable6.getColumnModel().getColumn(5).setPreferredWidth(30);
jTable6.getColumnModel().getColumn(6).setMinWidth(60);
jTable6.getColumnModel().getColumn(6).setMaxWidth(60);
jTable6.getColumnModel().getColumn(6).setPreferredWidth(60);
jTable6.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);


















jComboBox1.addActionListener(new ActionListener()  { 
 
    @Override
    public void actionPerformed(ActionEvent event) {
        jComboBox1 = (JComboBox<String>) event.getSource();
        String selectedBook = (String) jComboBox1.getSelectedItem();
 
        
        if (selectedBook.equals("Wszystko")) 
        {
            wszystko=true;ksiazki=false;czasopisma=false;filmy=false;
            if (dtm.getRowCount() > 0) for (int i = dtm.getRowCount() - 1; i > -1; i--) dtm.removeRow(i);            
            try{
                query="select * from zbior";
                ResultSet rs2 = stmt.executeQuery(query);
                while(rs2.next())
                {
                    dtm.addRow( new Object[] {rs2.getString(1), rs2.getString(2), db.query("select nazwisko from autor where ida="+rs2.getString(3))+" "+db.query("select imie from autor where ida="+rs2.getString(3)), db.query("select nazwa from gatunek where idg="+rs2.getString(4)), rs2.getString(5), rs2.getString(6) } );
                }
            }
            catch (SQLException e) {}
        }     
        else if (selectedBook.equals("Książki")) 
        {
            wszystko=false;ksiazki=true;czasopisma=false;filmy=false;
            if (dtm.getRowCount() > 0) for (int i = dtm.getRowCount() - 1; i > -1; i--) dtm.removeRow(i);
            try{
                query="select * from zbior";
                ResultSet rs2 = stmt.executeQuery(query);
                while(rs2.next())
                {
                    if(!db.query("select count(*) from zbior where idks="+rs2.getString(1)+" and typ='ksiazka'").equals("0"))
                    dtm.addRow( new Object[] {rs2.getString(1), rs2.getString(2), db.query("select nazwisko from autor where ida="+rs2.getString(3))+" "+db.query("select imie from autor where ida="+rs2.getString(3)), db.query("select nazwa from gatunek where idg="+rs2.getString(4)), rs2.getString(5), rs2.getString(6) } );
                }
            }
            catch (SQLException e) {}
        }
        else if (selectedBook.equals("Czasopisma")) 
        {
            wszystko=false;ksiazki=false;czasopisma=true;filmy=false;
            if (dtm.getRowCount() > 0) for (int i = dtm.getRowCount() - 1; i > -1; i--) dtm.removeRow(i);
            try{
                query="select * from zbior";
                ResultSet rs2 = stmt.executeQuery(query);
                while(rs2.next())
                {
                    if(!db.query("select count(*) from zbior where idks="+rs2.getString(1)+" and typ='czasopismo'").equals("0"))
                    dtm.addRow( new Object[] {rs2.getString(1), rs2.getString(2), db.query("select nazwisko from autor where ida="+rs2.getString(3))+" "+db.query("select imie from autor where ida="+rs2.getString(3)), db.query("select nazwa from gatunek where idg="+rs2.getString(4)), rs2.getString(5), rs2.getString(6) } );
                }
            }
            catch (SQLException e) {}
        }
        else if (selectedBook.equals("Filmy")) 
        {
            wszystko=false;ksiazki=false;czasopisma=false;filmy=true;
            if (dtm.getRowCount() > 0) for (int i = dtm.getRowCount() - 1; i > -1; i--) dtm.removeRow(i);
            System.out.println(db.query("select count(*) tytul from zbior where typ='film'"));
            try{
                query="select * from zbior";
                ResultSet rs2 = stmt.executeQuery(query);
                while(rs2.next())
                {
                    if(!db.query("select count(*) from zbior where idks="+rs2.getString(1)+" and typ='film'").equals("0"))
                    dtm.addRow( new Object[] {rs2.getString(1), rs2.getString(2), db.query("select nazwisko from autor where ida="+rs2.getString(3))+" "+db.query("select imie from autor where ida="+rs2.getString(3)), db.query("select nazwa from gatunek where idg="+rs2.getString(4)), rs2.getString(5), rs2.getString(6) } );
                }
            }
            catch (SQLException e) {}
        }
    }
});






jComboBox2.addActionListener(new ActionListener() {
 
    @Override
    public void actionPerformed(ActionEvent event) {
        jComboBox2 = (JComboBox<String>) event.getSource();
        String selectedBook = (String) jComboBox2.getSelectedItem();
 
        try
        {
        if (selectedBook.equals("IDKS")) 
        {
            conn = DriverManager.getConnection("jdbc:mysql://localhost/biblioteka?user=root&password=");
            Statement stmt = conn.createStatement();
            
            if (dtm.getRowCount() > 0) for (int i = dtm.getRowCount() - 1; i > -1; i--) dtm.removeRow(i);
            if(wszystko==true) 
            {
                query="select * from zbior order by idks";
                ResultSet rs = stmt.executeQuery(query);
                while(rs.next())
                {
                    dtm.addRow( new Object[] {rs.getString(1), rs.getString(2), db.query("select nazwisko from autor where ida="+rs.getString(3))+" "+db.query("select imie from autor where ida="+rs.getString(3)), db.query("select nazwa from gatunek where idg="+rs.getString(4)), rs.getString(5),  rs.getString(6) } );
                }
            }
            if(ksiazki==true) 
            {
                query="select * from zbior where typ='ksiazka' order by idks";
                ResultSet rs = stmt.executeQuery(query);
                while(rs.next())
                {
                    dtm.addRow( new Object[] {rs.getString(1), rs.getString(2), db.query("select nazwisko from autor where ida="+rs.getString(3))+" "+db.query("select imie from autor where ida="+rs.getString(3)), db.query("select nazwa from gatunek where idg="+rs.getString(4)), rs.getString(5),  rs.getString(6) } );
                }
            }
            if(czasopisma==true) 
            {
                query="select * from zbior where typ='czasopismo' order by idks";
                ResultSet rs = stmt.executeQuery(query);
                while(rs.next())
                {
                    dtm.addRow( new Object[] {rs.getString(1), rs.getString(2), db.query("select nazwisko from autor where ida="+rs.getString(3))+" "+db.query("select imie from autor where ida="+rs.getString(3)), db.query("select nazwa from gatunek where idg="+rs.getString(4)), rs.getString(5),  rs.getString(6) } );
                }
            }
            if(filmy==true) 
            {
                query="select * from zbior where typ='film' order by idks";
                ResultSet rs = stmt.executeQuery(query);
                while(rs.next())
                {
                    dtm.addRow( new Object[] {rs.getString(1), rs.getString(2), db.query("select nazwisko from autor where ida="+rs.getString(3))+" "+db.query("select imie from autor where ida="+rs.getString(3)), db.query("select nazwa from gatunek where idg="+rs.getString(4)), rs.getString(5),  rs.getString(6) } );
                }
            }
        }
        }
        catch (SQLException e) {}
        
        
        
        
        
        
        
        try
        {
        if (selectedBook.equals("Tytul")) 
        {
            conn = DriverManager.getConnection("jdbc:mysql://localhost/biblioteka?user=root&password=");
            Statement stmt = conn.createStatement();
            
            if (dtm.getRowCount() > 0) for (int i = dtm.getRowCount() - 1; i > -1; i--) dtm.removeRow(i);
            if(wszystko==true) 
            {
                query="select * from zbior order by tytul";
                ResultSet rs = stmt.executeQuery(query);
                while(rs.next())
                {
                    dtm.addRow( new Object[] {rs.getString(1), rs.getString(2), db.query("select nazwisko from autor where ida="+rs.getString(3))+" "+db.query("select imie from autor where ida="+rs.getString(3)), db.query("select nazwa from gatunek where idg="+rs.getString(4)), rs.getString(5),  rs.getString(6) } );
                }
            }
            if(ksiazki==true) 
            {
                query="select * from zbior where typ='ksiazka' order by tytul";
                ResultSet rs = stmt.executeQuery(query);
                while(rs.next())
                {
                    dtm.addRow( new Object[] {rs.getString(1), rs.getString(2), db.query("select nazwisko from autor where ida="+rs.getString(3))+" "+db.query("select imie from autor where ida="+rs.getString(3)), db.query("select nazwa from gatunek where idg="+rs.getString(4)), rs.getString(5),  rs.getString(6) } );
                }
            }
            if(czasopisma==true) 
            {
                query="select * from zbior where typ='czasopismo' order by tytul";
                ResultSet rs = stmt.executeQuery(query);
                while(rs.next())
                {
                    dtm.addRow( new Object[] {rs.getString(1), rs.getString(2), db.query("select nazwisko from autor where ida="+rs.getString(3))+" "+db.query("select imie from autor where ida="+rs.getString(3)), db.query("select nazwa from gatunek where idg="+rs.getString(4)), rs.getString(5),  rs.getString(6) } );
                }
            }
            if(filmy==true) 
            {
                query="select * from zbior where typ='film' order by idks";
                ResultSet rs = stmt.executeQuery(query);
                while(rs.next())
                {
                    dtm.addRow( new Object[] {rs.getString(1), rs.getString(2), db.query("select nazwisko from autor where ida="+rs.getString(3))+" "+db.query("select imie from autor where ida="+rs.getString(3)), db.query("select nazwa from gatunek where idg="+rs.getString(4)), rs.getString(5),  rs.getString(6) } );
                }
            }
        }
        }
        catch (SQLException e) {}
        
        
        
        
        
        
        
        try
        {
        if (selectedBook.equals("Autor")) 
        {
            conn = DriverManager.getConnection("jdbc:mysql://localhost/biblioteka?user=root&password=");
            Statement stmt = conn.createStatement();
            
            if (dtm.getRowCount() > 0) for (int i = dtm.getRowCount() - 1; i > -1; i--) dtm.removeRow(i);
            if(wszystko==true) 
            {
                query="SELECT * FROM zbior z JOIN autor a ON z.ida = a.ida ORDER BY a.nazwisko";
                
                ResultSet rs = stmt.executeQuery(query);
                while(rs.next())
                {
                    dtm.addRow( new Object[] {rs.getString(1), rs.getString(2), db.query("select nazwisko from autor where ida="+rs.getString(3))+" "+db.query("select imie from autor where ida="+rs.getString(3)), db.query("select nazwa from gatunek where idg="+rs.getString(4)), rs.getString(5),  rs.getString(6) } );
                }
            }
            if(ksiazki==true) 
            {
                query="SELECT * FROM zbior z JOIN autor a ON z.ida = a.ida WHERE typ='ksiazka' ORDER BY a.nazwisko";
                ResultSet rs = stmt.executeQuery(query);
                while(rs.next())
                {
                    dtm.addRow( new Object[] {rs.getString(1), rs.getString(2), db.query("select nazwisko from autor where ida="+rs.getString(3))+" "+db.query("select imie from autor where ida="+rs.getString(3)), db.query("select nazwa from gatunek where idg="+rs.getString(4)), rs.getString(5),  rs.getString(6) } );
                }
            }
            if(czasopisma==true) 
            {
                query="SELECT * FROM zbior z JOIN autor a ON z.ida = a.ida WHERE typ='czasopismo' ORDER BY a.nazwisko";
                ResultSet rs = stmt.executeQuery(query);
                while(rs.next())
                {
                    dtm.addRow( new Object[] {rs.getString(1), rs.getString(2), db.query("select nazwisko from autor where ida="+rs.getString(3))+" "+db.query("select imie from autor where ida="+rs.getString(3)), db.query("select nazwa from gatunek where idg="+rs.getString(4)), rs.getString(5),  rs.getString(6) } );
                }
            }
            

            if(filmy==true) 
            {
                query="SELECT * FROM zbior z JOIN autor a ON z.ida = a.ida WHERE typ='film' ORDER BY a.nazwisko";
                ResultSet rs = stmt.executeQuery(query);
                while(rs.next())
                {
                    dtm.addRow( new Object[] {rs.getString(1), rs.getString(2), db.query("select nazwisko from autor where ida="+rs.getString(3))+" "+db.query("select imie from autor where ida="+rs.getString(3)), db.query("select nazwa from gatunek where idg="+rs.getString(4)), rs.getString(5),  rs.getString(6) } );
                }
            }
        }
        }
        catch (SQLException e) {}
        

        
        
        
        try
        {
        if (selectedBook.equals("Gatunek")) 
        {
            conn = DriverManager.getConnection("jdbc:mysql://localhost/biblioteka?user=root&password=");
            Statement stmt = conn.createStatement();
            
            if (dtm.getRowCount() > 0) for (int i = dtm.getRowCount() - 1; i > -1; i--) dtm.removeRow(i);
            if(wszystko==true) 
            {
                query="SELECT * FROM zbior z JOIN gatunek g ON z.idg = g.idg ORDER BY g.nazwa";
                ResultSet rs = stmt.executeQuery(query);
                while(rs.next())
                {
                    dtm.addRow( new Object[] {rs.getString(1), rs.getString(2), db.query("select nazwisko from autor where ida="+rs.getString(3))+" "+db.query("select imie from autor where ida="+rs.getString(3)), db.query("select nazwa from gatunek where idg="+rs.getString(4)), rs.getString(5),  rs.getString(6) } );
                }
            }
            if(ksiazki==true) 
            {
                query="SELECT * FROM zbior z JOIN gatunek g ON z.idg = g.idg WHERE typ='ksiazka' ORDER BY g.nazwa";
                ResultSet rs = stmt.executeQuery(query);
                while(rs.next())
                {
                    dtm.addRow( new Object[] {rs.getString(1), rs.getString(2), db.query("select nazwisko from autor where ida="+rs.getString(3))+" "+db.query("select imie from autor where ida="+rs.getString(3)), db.query("select nazwa from gatunek where idg="+rs.getString(4)), rs.getString(5),  rs.getString(6) } );
                }
            }
            if(czasopisma==true) 
            {
                query="SELECT * FROM zbior z JOIN gatunek g ON z.idg = g.idg WHERE typ='czasopismo' ORDER BY g.nazwa";
                ResultSet rs = stmt.executeQuery(query);
                while(rs.next())
                {
                    dtm.addRow( new Object[] {rs.getString(1), rs.getString(2), db.query("select nazwisko from autor where ida="+rs.getString(3))+" "+db.query("select imie from autor where ida="+rs.getString(3)), db.query("select nazwa from gatunek where idg="+rs.getString(4)), rs.getString(5),  rs.getString(6) } );
                }
            }
            if(filmy==true) 
            {
                query="SELECT * FROM zbior z JOIN gatunek g ON z.idg = g.idg WHERE typ='film' ORDER BY g.nazwa";
                ResultSet rs = stmt.executeQuery(query);
                while(rs.next())
                {
                    dtm.addRow( new Object[] {rs.getString(1), rs.getString(2), db.query("select nazwisko from autor where ida="+rs.getString(3))+" "+db.query("select imie from autor where ida="+rs.getString(3)), db.query("select nazwa from gatunek where idg="+rs.getString(4)), rs.getString(5),  rs.getString(6) } );
                }
            }
        }
        }
        catch (SQLException e) {}
        
        
        
        
        
        
        
        
        
        
        
        try
        {
        if (selectedBook.equals("Rok wyd")) 
        {
            conn = DriverManager.getConnection("jdbc:mysql://localhost/biblioteka?user=root&password=");
            Statement stmt = conn.createStatement();
            
            if (dtm.getRowCount() > 0) for (int i = dtm.getRowCount() - 1; i > -1; i--) dtm.removeRow(i);
            if(wszystko==true) 
            {
                query="select * from zbior order by rok_wyd";
                ResultSet rs = stmt.executeQuery(query);
                while(rs.next())
                {
                    dtm.addRow( new Object[] {rs.getString(1), rs.getString(2), db.query("select nazwisko from autor where ida="+rs.getString(3))+" "+db.query("select imie from autor where ida="+rs.getString(3)), db.query("select nazwa from gatunek where idg="+rs.getString(4)), rs.getString(5),  rs.getString(6) } );
                }
            }
            if(ksiazki==true) 
            {
                query="select * from zbior where typ='ksiazka' order by rok_wyd";
                ResultSet rs = stmt.executeQuery(query);
                while(rs.next())
                {
                    dtm.addRow( new Object[] {rs.getString(1), rs.getString(2), db.query("select nazwisko from autor where ida="+rs.getString(3))+" "+db.query("select imie from autor where ida="+rs.getString(3)), db.query("select nazwa from gatunek where idg="+rs.getString(4)), rs.getString(5),  rs.getString(6) } );
                }
            }
            if(czasopisma==true) 
            {
                query="select * from zbior where typ='czasopismo' order by rok wyd";
                ResultSet rs = stmt.executeQuery(query);
                while(rs.next())
                {
                    dtm.addRow( new Object[] {rs.getString(1), rs.getString(2), db.query("select nazwisko from autor where ida="+rs.getString(3))+" "+db.query("select imie from autor where ida="+rs.getString(3)), db.query("select nazwa from gatunek where idg="+rs.getString(4)), rs.getString(5),  rs.getString(6) } );
                }
            }
            if(filmy==true) 
            {
                query="select * from zbior where typ='film' order by rok_wyd";
                ResultSet rs = stmt.executeQuery(query);
                while(rs.next())
                {
                    dtm.addRow( new Object[] {rs.getString(1), rs.getString(2), db.query("select nazwisko from autor where ida="+rs.getString(3))+" "+db.query("select imie from autor where ida="+rs.getString(3)), db.query("select nazwa from gatunek where idg="+rs.getString(4)), rs.getString(5),  rs.getString(6) } );
                }
            }
        }
        }
        catch (SQLException e) {}
        
    }
});






    }
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
        /*public boolean dostepnoscAll(String poczS, String konS, String idks) throws ParseException
    {

        
        //jeśli data_wyp pomiedzy termin AND data_zwr pomiedzy termin
        if(!db.query("select count(*) from wypozyczenia where (idks="+idks+" and data_wyp between '"+poczS+"' and '"+konS+"') or (idks="+idks+" and data_zwr between '"+poczS+"' and '"+konS+"')").equals("0"))
            return false; 
             
        //jeśli data_wyp AND data_zwr wewnątrz terminu
        if(!db.query("select count(*) from wypozyczenia where idks="+idks+" and data_wyp > '"+poczS+"' and data_zwr < '"+konS+"'").equals("0"))
            return false;
        
        //jeśli data_wyp AND data_zwr zewnątrz terminu
        if(!db.query("select count(*) from wypozyczenia where idks="+idks+" and data_wyp < '"+poczS+"' and data_zwr > '"+konS+"'").equals("0"))
            return false;
        
        else
        return true;
    }*/
    
    



    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTable3 = new javax.swing.JTable();
        jLabel15 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jScrollPane8 = new javax.swing.JScrollPane();
        jTextPane5 = new javax.swing.JTextPane();
        jScrollPane9 = new javax.swing.JScrollPane();
        jTextPane6 = new javax.swing.JTextPane();
        jLabel19 = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        jScrollPane10 = new javax.swing.JScrollPane();
        jTextPane7 = new javax.swing.JTextPane();
        jLabel18 = new javax.swing.JLabel();
        jScrollPane11 = new javax.swing.JScrollPane();
        jTextPane8 = new javax.swing.JTextPane();
        jLabel21 = new javax.swing.JLabel();
        jLabel22 = new javax.swing.JLabel();
        jScrollPane12 = new javax.swing.JScrollPane();
        jTextPane9 = new javax.swing.JTextPane();
        jButton5 = new javax.swing.JButton();
        jLabel17 = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        jButton6 = new javax.swing.JButton();
        jLabel24 = new javax.swing.JLabel();
        jScrollPane14 = new javax.swing.JScrollPane();
        jTextPane11 = new javax.swing.JTextPane();
        jButton4 = new javax.swing.JButton();
        jScrollPane7 = new javax.swing.JScrollPane();
        jTextPane3 = new javax.swing.JTextPane();
        jLabel10 = new javax.swing.JLabel();
        jPanel5 = new javax.swing.JPanel();
        jLabel23 = new javax.swing.JLabel();
        jScrollPane13 = new javax.swing.JScrollPane();
        jTextPane10 = new javax.swing.JTextPane();
        jScrollPane17 = new javax.swing.JScrollPane();
        jTextPane12 = new javax.swing.JTextPane();
        jScrollPane18 = new javax.swing.JScrollPane();
        jTextPane13 = new javax.swing.JTextPane();
        jScrollPane19 = new javax.swing.JScrollPane();
        jTextPane14 = new javax.swing.JTextPane();
        jScrollPane20 = new javax.swing.JScrollPane();
        jTextPane15 = new javax.swing.JTextPane();
        jScrollPane21 = new javax.swing.JScrollPane();
        jTextPane16 = new javax.swing.JTextPane();
        jLabel28 = new javax.swing.JLabel();
        jLabel29 = new javax.swing.JLabel();
        jLabel30 = new javax.swing.JLabel();
        jLabel31 = new javax.swing.JLabel();
        jLabel32 = new javax.swing.JLabel();
        jButton7 = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jButton3 = new javax.swing.JButton();
        jComboBox1 = new javax.swing.JComboBox<>();
        jLabel12 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jComboBox2 = new javax.swing.JComboBox<>();
        jLabel13 = new javax.swing.JLabel();
        jScrollPane22 = new javax.swing.JScrollPane();
        jTextPane4 = new javax.swing.JTextPane();
        jLabel33 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        jTable2 = new javax.swing.JTable();
        jSeparator1 = new javax.swing.JSeparator();
        jLabel9 = new javax.swing.JLabel();
        jScrollPane4 = new javax.swing.JScrollPane();
        jTable4 = new javax.swing.JTable();
        jLabel25 = new javax.swing.JLabel();
        jScrollPane15 = new javax.swing.JScrollPane();
        jTable5 = new javax.swing.JTable();
        jLabel26 = new javax.swing.JLabel();
        jScrollPane16 = new javax.swing.JScrollPane();
        jTable6 = new javax.swing.JTable();
        jLabel27 = new javax.swing.JLabel();
        jButton2 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);

        jPanel1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jLabel5.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        jLabel5.setText("Panel pracownika");

        jScrollPane2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jTable3.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        jScrollPane2.setViewportView(jTable3);

        jLabel15.setText("Zatwierdź wypożyczenie");

        jLabel16.setText("Dodaj zbiór");

        jPanel3.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel3.setPreferredSize(new java.awt.Dimension(2, 75));

        jTextPane5.setBorder(null);
        jScrollPane8.setViewportView(jTextPane5);

        jTextPane6.setBorder(null);
        jScrollPane9.setViewportView(jTextPane6);

        jLabel19.setText("IDA");

        jLabel20.setText("IDG");

        jTextPane7.setBorder(null);
        jScrollPane10.setViewportView(jTextPane7);

        jLabel18.setText("Tytuł");

        jScrollPane11.setViewportView(jTextPane8);

        jLabel21.setText("Rok wyd");

        jLabel22.setText("Typ");

        jTextPane9.setBorder(null);
        jScrollPane12.setViewportView(jTextPane9);

        jButton5.setText("Dodaj");
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel18)
                    .addComponent(jLabel19, javax.swing.GroupLayout.Alignment.TRAILING))
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jScrollPane9, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(31, 31, 31)
                        .addComponent(jLabel20)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane10, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(26, 26, 26)
                        .addComponent(jLabel22)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jScrollPane12, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 42, Short.MAX_VALUE)
                        .addComponent(jButton5))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jScrollPane8, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel21)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane11, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jButton5)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jScrollPane8, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel18, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jScrollPane11, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel21, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(8, 8, 8)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel19, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel20, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jScrollPane10, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jLabel22, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jScrollPane12, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 23, Short.MAX_VALUE)))
                            .addComponent(jScrollPane9, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jLabel17.setText("Usuń zbiór");

        jPanel4.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jButton6.setText("Usuń");
        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton6ActionPerformed(evt);
            }
        });

        jLabel24.setText("IDKS");

        jScrollPane14.setViewportView(jTextPane11);

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                        .addComponent(jLabel24)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 10, Short.MAX_VALUE)
                        .addComponent(jScrollPane14, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jButton6)))
                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane14, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel24, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton6))
        );

        jButton4.setText("Zatwierdź");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        jScrollPane7.setViewportView(jTextPane3);

        jLabel10.setText("Edytuj zbiór");

        jPanel5.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jLabel23.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel23.setText("IDKS");

        jTextPane10.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jScrollPane13.setViewportView(jTextPane10);

        jTextPane12.setBorder(null);
        jScrollPane17.setViewportView(jTextPane12);

        jTextPane13.setBorder(null);
        jScrollPane18.setViewportView(jTextPane13);

        jTextPane14.setBorder(null);
        jScrollPane19.setViewportView(jTextPane14);

        jTextPane15.setBorder(null);
        jScrollPane20.setViewportView(jTextPane15);

        jTextPane16.setBorder(null);
        jScrollPane21.setViewportView(jTextPane16);

        jLabel28.setText("Tytuł");

        jLabel29.setText("IDA");

        jLabel30.setText("IDG");

        jLabel31.setText("Rok wyd");

        jLabel32.setText("Typ");

        jButton7.setText("Edytuj");
        jButton7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton7ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addComponent(jLabel28)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane17, javax.swing.GroupLayout.PREFERRED_SIZE, 184, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                .addComponent(jLabel23)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane13, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel29)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane18, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel30)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane20, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(20, 20, 20))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                .addComponent(jLabel31)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane19, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel32)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane21, javax.swing.GroupLayout.PREFERRED_SIZE, 68, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jButton7)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(jLabel23, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jScrollPane13, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 23, Short.MAX_VALUE)
                        .addComponent(jScrollPane18, javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(jLabel29, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(jLabel30, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jScrollPane20, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel28, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jScrollPane17))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 10, Short.MAX_VALUE)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane19, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel31, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jScrollPane21, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel32, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton7, javax.swing.GroupLayout.Alignment.TRAILING))
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(0, 15, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap())
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel15)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(112, 112, 112)
                                .addComponent(jLabel5))
                            .addComponent(jLabel16)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 290, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jButton4)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addGap(22, 22, 22)
                                        .addComponent(jScrollPane7, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel10)
                                .addGap(227, 227, 227)
                                .addComponent(jLabel17))
                            .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, 375, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(6, 6, 6))))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(6, 6, 6)
                .addComponent(jLabel5)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel15)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(31, 31, 31)
                        .addComponent(jScrollPane7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton4)))
                .addGap(6, 6, 6)
                .addComponent(jLabel16)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel17)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap())
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel10)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );

        jPanel2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jTable1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jScrollPane1.setViewportView(jTable1);

        jButton3.setText("Wyszukaj");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Wszystko", "Książki", "Czasopisma", "Filmy" }));
        jComboBox1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox1ActionPerformed(evt);
            }
        });

        jLabel12.setText("Wyświetl");

        jLabel11.setText("Sortuj");

        jComboBox2.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "IDKS", "Tytul", "Autor", "Gatunek", "Rok wyd" }));
        jComboBox2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox2ActionPerformed(evt);
            }
        });

        jLabel13.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        jLabel13.setText("Magazyn");

        jScrollPane22.setViewportView(jTextPane4);

        jLabel33.setText("Filtr");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                                .addComponent(jLabel13)
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel12)
                                    .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(6, 6, 6))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGap(165, 165, 165)
                                .addComponent(jLabel33)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jScrollPane22, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 34, Short.MAX_VALUE)))
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jComboBox2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel11))
                        .addGap(11, 11, 11))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jButton3)
                        .addGap(19, 19, 19))))
            .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel13)
                .addGap(20, 20, 20)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel11)
                    .addComponent(jLabel12))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jComboBox2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButton3)
                    .addComponent(jScrollPane22, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel33, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 14, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 272, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        jLabel1.setText("Zalogowano:");

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel2.setText("jLabel2");

        jButton1.setText("Wyloguj");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jLabel3.setFont(new java.awt.Font("Tahoma", 0, 36)); // NOI18N
        jLabel3.setText("Biblioteka");

        jLabel4.setText("godzina");

        jLabel6.setText("data");

        jTable2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jTable2.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        jScrollPane3.setViewportView(jTable2);

        jSeparator1.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N

        jLabel9.setText("Tabela: klient");

        jTable4.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jTable4.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        jScrollPane4.setViewportView(jTable4);

        jLabel25.setText("Tabela: autor");

        jTable5.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jTable5.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        jScrollPane15.setViewportView(jTable5);

        jLabel26.setText("Tabela: gatunek");

        jTable6.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jTable6.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        jScrollPane16.setViewportView(jTable6);

        jLabel27.setText("Tabela: wypozyczenia");

        jButton2.setText("Odśwież tabele");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jSeparator1)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jButton1)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel1)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel2)))
                        .addGap(93, 93, 93))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel9))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane15, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel25))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel26))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel27)
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addComponent(jScrollPane16, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel6)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel4))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(224, 224, 224)
                        .addComponent(jLabel3)))
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jButton2)
                .addGap(361, 361, 361))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addComponent(jLabel3)
                .addGap(19, 19, 19)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jLabel2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jButton1)
                .addGap(11, 11, 11)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(jLabel6))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel9)
                    .addComponent(jLabel25)
                    .addComponent(jLabel26)
                    .addComponent(jLabel27))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jScrollPane15, javax.swing.GroupLayout.DEFAULT_SIZE, 137, Short.MAX_VALUE)
                    .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addComponent(jScrollPane16, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton2)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        LoginView wl = new LoginView();
        wl.setVisible(true);
        
        this.dispose();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        //int licz=0;
        System.out.println(selectedData);
        if (dtm.getRowCount() > 0)
        {
            for (int i = dtm.getRowCount() - 1; i > -1; i--)
            {
                dtm.removeRow(i);
            }
        }
        
        
        try
        {
            System.out.println("\"%"+jTextPane4.getText()+"%\"");
                query="select * from zbior where tytul like \"%"+jTextPane4.getText()+"%\" ";
                ResultSet rs = stmt.executeQuery(query);
                while(rs.next())
                {
                    dtm.addRow( new Object[] {rs.getString(1), rs.getString(2), db.query("select nazwisko from autor where ida="+rs.getString(3))+" "+db.query("select imie from autor where ida="+rs.getString(3)), db.query("select nazwa from gatunek where idg="+rs.getString(4)), rs.getString(5), rs.getString(6) } );
                }
        }
        catch (SQLException e) {System.err.println(e);}
        
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jComboBox1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jComboBox1ActionPerformed

    private void jComboBox2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jComboBox2ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        db.usunDodaj("UPDATE wypozyczenia SET status='wyp', idp="+db.query("select idp from pracownik where imie='"+imie+"' and nazwisko='"+nazwisko+"'")+" WHERE idwyp="+jTextPane3.getText());
        
        if (dtm2.getRowCount() > 0)
        {
            for (int i = dtm2.getRowCount() - 1; i > -1; i--)
            {
                dtm2.removeRow(i);
            }
        }
        
        try
        {
                query="select * from wypozyczenia where status='ocz'";
                ResultSet rs = stmt.executeQuery(query);
                while(rs.next())
                {
                    dtm2.addRow( new Object[] {rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(6) } );
                }
        }
        catch (SQLException e) {}
    }//GEN-LAST:event_jButton4ActionPerformed

    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed
        if(!db.query("select count(*) from wypozyczenia where idks="+jTextPane11.getText()).equals("0"))
            db.usunDodaj("delete from wypozyczenia where idks="+jTextPane11.getText());
        db.usunDodaj("delete from zbior where idks="+jTextPane11.getText());
    }//GEN-LAST:event_jButton6ActionPerformed

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        db.usunDodaj("INSERT INTO zbior (tytul, ida, idg, rok_wyd, typ) VALUES ('"+jTextPane5.getText()+"', '"+jTextPane6.getText()+"', '"+jTextPane7.getText()+"', '"+jTextPane8.getText()+"', '"+jTextPane9.getText()+"')");
    }//GEN-LAST:event_jButton5ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        if (dtm3.getRowCount() > 0) for (int i = dtm3.getRowCount() - 1; i > -1; i--) dtm3.removeRow(i); 
        try {
                query= "select * from klient";
                ResultSet rs = stmt.executeQuery(query);
                while(rs.next()) dtm3.addRow( new Object[] {rs.getString(1), rs.getString(4), rs.getString(5) } );     
        } catch (SQLException e) {}
        
        if (dtm4.getRowCount() > 0) for (int i = dtm4.getRowCount() - 1; i > -1; i--) dtm4.removeRow(i); 
        try {
                query= "select * from autor";
                ResultSet rs = stmt.executeQuery(query);
                while(rs.next()) dtm4.addRow( new Object[] {rs.getString(1), rs.getString(2), rs.getString(3) } );     
        } catch (SQLException e) {}
        
        if (dtm5.getRowCount() > 0) for (int i = dtm5.getRowCount() - 1; i > -1; i--) dtm5.removeRow(i); 
        try {
                query= "select * from gatunek";
                ResultSet rs = stmt.executeQuery(query);
                while(rs.next()) dtm5.addRow( new Object[] {rs.getString(1), rs.getString(2) } );     
        } catch (SQLException e) {}
        
        if (dtm6.getRowCount() > 0) for (int i = dtm6.getRowCount() - 1; i > -1; i--) dtm6.removeRow(i); 
        try {
                query= "select * from wypozyczenia";
                ResultSet rs = stmt.executeQuery(query);
                while(rs.next()) dtm6.addRow( new Object[] {rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6), rs.getString(7) } );     
        } catch (SQLException e) {}
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton7ActionPerformed
        db.usunDodaj("UPDATE `zbior` SET `tytul`='"+jTextPane12.getText()+"',`ida`="+jTextPane13.getText()+",`idg`="+jTextPane15.getText()+",`rok_wyd`="+jTextPane14.getText()+",`typ`='"+jTextPane16.getText()+"' WHERE idks="+jTextPane10.getText());
    }//GEN-LAST:event_jButton7ActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        
        


        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new WorkerView().setVisible(true);
                
                
            }
        });
    }

    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
    private javax.swing.JButton jButton7;
    private javax.swing.JComboBox<String> jComboBox1;
    private javax.swing.JComboBox<String> jComboBox2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JLabel jLabel29;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel30;
    private javax.swing.JLabel jLabel31;
    private javax.swing.JLabel jLabel32;
    private javax.swing.JLabel jLabel33;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane10;
    private javax.swing.JScrollPane jScrollPane11;
    private javax.swing.JScrollPane jScrollPane12;
    private javax.swing.JScrollPane jScrollPane13;
    private javax.swing.JScrollPane jScrollPane14;
    private javax.swing.JScrollPane jScrollPane15;
    private javax.swing.JScrollPane jScrollPane16;
    private javax.swing.JScrollPane jScrollPane17;
    private javax.swing.JScrollPane jScrollPane18;
    private javax.swing.JScrollPane jScrollPane19;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane20;
    private javax.swing.JScrollPane jScrollPane21;
    private javax.swing.JScrollPane jScrollPane22;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane7;
    private javax.swing.JScrollPane jScrollPane8;
    private javax.swing.JScrollPane jScrollPane9;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JTable jTable1;
    private javax.swing.JTable jTable2;
    private javax.swing.JTable jTable3;
    private javax.swing.JTable jTable4;
    private javax.swing.JTable jTable5;
    private javax.swing.JTable jTable6;
    private javax.swing.JTextPane jTextPane10;
    private javax.swing.JTextPane jTextPane11;
    private javax.swing.JTextPane jTextPane12;
    private javax.swing.JTextPane jTextPane13;
    private javax.swing.JTextPane jTextPane14;
    private javax.swing.JTextPane jTextPane15;
    private javax.swing.JTextPane jTextPane16;
    private javax.swing.JTextPane jTextPane3;
    private javax.swing.JTextPane jTextPane4;
    private javax.swing.JTextPane jTextPane5;
    private javax.swing.JTextPane jTextPane6;
    private javax.swing.JTextPane jTextPane7;
    private javax.swing.JTextPane jTextPane8;
    private javax.swing.JTextPane jTextPane9;
    // End of variables declaration//GEN-END:variables
}

