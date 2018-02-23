package javaapplication5;

import javax.swing.table.DefaultTableModel;
import javax.swing.Timer;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.nio.charset.Charset;
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
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;


public class WidokKlient extends javax.swing.JFrame {

        Database db = new Database();
        String selectedData = "";
        String selectedData2 = "";
        Date dzis = new Date();
        DefaultTableModel dtm;
        DefaultTableModel dtm2;
        String idk;
        boolean ba=false;
        boolean c=false;
        String query="";
        Statement stmt;
        
        boolean wszystko=true;
        boolean ksiazki=false;
        boolean czasopisma=false;
        boolean filmy=false;
        Connection conn = null;
        
        public WidokKlient() {
        initComponents();  
    }
        
        
        
    public WidokKlient(String imie, String nazwisko) throws ParseException, SQLException
    {
        initComponents();
        conn = DriverManager.getConnection("jdbc:mysql://localhost/biblioteka?user=root&password=&characterEncoding=utf8");
        stmt = conn.createStatement();
        //Color myColor = Color.decode("#b5b5cc");
        //this.getContentPane().setBackground(myColor);
        //Date date = new Date();
        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
        this.setTitle("Biblioteka : klient");
        
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
        
        
        dtm = new DefaultTableModel(0, 0)
            {
                @Override
                public boolean isCellEditable(int row, int column) { return false; }    
            };
                
        String[] cols = { "IDKS", "Tytuł", "Autor", "Gatunek", "Rok wyd", "Typ" };
        dtm.setColumnIdentifiers(cols);
        
        dtm2 = new DefaultTableModel(0, 0)
            {
                @Override
                public boolean isCellEditable(int row, int column) { return false; }    
            };
                
        String[] cols2 = { "IDKS", "Tytuł", "Data wyp", "Data zwr", "IDP", "Status" };
        dtm2.setColumnIdentifiers(cols2);
        
       jButton2.setEnabled(false);
                    
                    
                    
       jButton3.addActionListener(new ActionListener() {
       public void actionPerformed(ActionEvent e) {
             // this makes sure the button you are pressing is the button variable
             if(e.getSource() == jButton3) {
                jButton2.setEnabled(true);
              }
       }
 });
        
        
        
        
        
            ListSelectionModel cellSelectionModel = jTable1.getSelectionModel();
    cellSelectionModel.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

    cellSelectionModel.addListSelectionListener(new ListSelectionListener() {
      @Override
      public void valueChanged(ListSelectionEvent e) {
        

        int selectedRow = jTable1.getSelectedRow();

            //selectedData = (String)jTable1.getValueAt(selectedRow, 0);

        
        System.out.println("Selected: " + selectedData);
      }

    });
    
                ListSelectionModel cellSelectionModel2 = jTable3.getSelectionModel();
    cellSelectionModel2.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

    cellSelectionModel2.addListSelectionListener(new ListSelectionListener() {
        @Override
        public void valueChanged(ListSelectionEvent e) {
        

        int selectedRow = jTable3.getSelectedRow();

            if(dtm2.getRowCount()!=0 && ba==false) { selectedData2 = (String)jTable3.getValueAt(selectedRow, 0); ba=true; }
            //jButton4.setEnabled(true);
        
        System.out.println("Selected: " + selectedData2);
      }

    });
        
        

        
        
        


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
                
                query="select * from wypozyczenia where idk="+idk;
                ResultSet rs2 = stmt.executeQuery(query);
                while(rs2.next())
                {
                    dtm2.addRow( new Object[] {rs2.getString(2), db.query("select tytul from zbior where idks="+rs2.getString(2)), rs2.getString(3), rs2.getString(4), rs2.getString(5),  rs2.getString(7) } );
                }
                
              
  
jTable3.getColumnModel().getColumn(0).setMinWidth(30);
jTable3.getColumnModel().getColumn(0).setMaxWidth(30);
jTable3.getColumnModel().getColumn(0).setPreferredWidth(30);
jTable3.getColumnModel().getColumn(1).setMinWidth(200);
jTable3.getColumnModel().getColumn(1).setMaxWidth(200);
jTable3.getColumnModel().getColumn(1).setPreferredWidth(200);
jTable3.getColumnModel().getColumn(2).setMinWidth(70);
jTable3.getColumnModel().getColumn(2).setMaxWidth(70);
jTable3.getColumnModel().getColumn(2).setPreferredWidth(70);
jTable3.getColumnModel().getColumn(3).setMinWidth(70);
jTable3.getColumnModel().getColumn(3).setMaxWidth(70);
jTable3.getColumnModel().getColumn(3).setPreferredWidth(70);      
jTable3.getColumnModel().getColumn(4).setMinWidth(30);
jTable3.getColumnModel().getColumn(4).setMaxWidth(30);
jTable3.getColumnModel().getColumn(4).setPreferredWidth(30);    
jTable3.getColumnModel().getColumn(4).setMinWidth(50);
jTable3.getColumnModel().getColumn(4).setMaxWidth(50);
jTable3.getColumnModel().getColumn(4).setPreferredWidth(50);   

jTable3.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);






jComboBox1.addActionListener(new ActionListener() {
 
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
                    dtm.addRow( new Object[] {rs.getString(1), rs.getString(2), db.query("select nazwisko from autor where ida="+rs.getString(3))+" "+db.query("select imie from autor where ida="+rs.getString(3)),  db.query("select nazwisko from autor where ida="+rs.getString(3)), db.query("select nazwa from gatunek where idg="+rs.getString(4)), rs.getString(5),  rs.getString(6) } );
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
            System.err.println("aa");
        if (selectedBook.equals("Autor")) 
        {
            //System.out.println(db.query("SELECT * FROM zbior z JOIN autor a ON z.ida = a.ida WHERE typ='film' ORDER BY a.nazwisko"));
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
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
        public boolean dostepnoscAll(String poczS, String konS, String idks) throws ParseException
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
    }
    
    



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
        jButton4 = new javax.swing.JButton();
        jScrollPane3 = new javax.swing.JScrollPane();
        jTextPane3 = new javax.swing.JTextPane();
        jLabel9 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jButton2 = new javax.swing.JButton();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jButton3 = new javax.swing.JButton();
        jScrollPane5 = new javax.swing.JScrollPane();
        jTextPane1 = new javax.swing.JTextPane();
        jScrollPane6 = new javax.swing.JScrollPane();
        jTextPane2 = new javax.swing.JTextPane();
        jLabel10 = new javax.swing.JLabel();
        jScrollPane4 = new javax.swing.JScrollPane();
        jTextPane4 = new javax.swing.JTextPane();
        jComboBox1 = new javax.swing.JComboBox<>();
        jLabel12 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jComboBox2 = new javax.swing.JComboBox<>();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jScrollPane7 = new javax.swing.JScrollPane();
        jTextPane5 = new javax.swing.JTextPane();
        jLabel15 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);

        jPanel1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jLabel5.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        jLabel5.setText("Panel klienta");

        jScrollPane2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jTable3.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        jScrollPane2.setViewportView(jTable3);

        jButton4.setText("Oddaj");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        jScrollPane3.setViewportView(jTextPane3);

        jLabel9.setText("IDKS:");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(60, 60, 60)
                        .addComponent(jLabel9)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton4))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(83, 83, 83)
                        .addComponent(jLabel5)))
                .addContainerGap(102, Short.MAX_VALUE))
            .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(6, 6, 6)
                .addComponent(jLabel5)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jButton4)
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel9, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        jPanel2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jTable1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jScrollPane1.setViewportView(jTable1);

        jButton2.setText("Zamów");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jLabel7.setText("od");

        jLabel8.setText("do");

        jButton3.setText("Wyszukaj");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jScrollPane5.setViewportView(jTextPane1);

        jScrollPane6.setViewportView(jTextPane2);

        jLabel10.setText("IDKS");

        jScrollPane4.setViewportView(jTextPane4);

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

        jLabel14.setText("Termin");

        jScrollPane7.setViewportView(jTextPane5);

        jLabel15.setText("Filtr");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jLabel10)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jButton2)
                        .addGap(225, 225, 225))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel7)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel12)
                            .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(6, 6, 6)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jComboBox2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel11))
                        .addGap(11, 11, 11))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(jLabel14)
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(jLabel8)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jScrollPane6, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel15)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jScrollPane7, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jButton3)))
                        .addContainerGap())))
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(221, 221, 221)
                .addComponent(jLabel13)
                .addGap(0, 0, Short.MAX_VALUE))
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 485, Short.MAX_VALUE)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(0, 50, Short.MAX_VALUE)
                        .addComponent(jLabel14)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jScrollPane6, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel13)
                        .addGap(20, 20, 20)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel11)
                            .addComponent(jLabel12))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jComboBox2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jButton3))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jLabel15, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jScrollPane7, javax.swing.GroupLayout.DEFAULT_SIZE, 23, Short.MAX_VALUE))))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 243, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButton2)
                    .addComponent(jLabel10)
                    .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
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

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButton1)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel2)))
                .addGap(89, 89, 89))
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel6)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel4)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
            .addGroup(layout.createSequentialGroup()
                .addGap(234, 234, 234)
                .addComponent(jLabel3)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
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
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(jLabel6))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(63, 63, 63))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        WidokLogowanie wl = new WidokLogowanie();
        wl.setVisible(true);
        
        this.dispose();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed

        //dtm2.removeRow(0);

        if (dtm2.getRowCount() > 0) 
        {
            for (int i = dtm2.getRowCount() - 1; i > -1; i--) 
            {
                dtm2.removeRow(i);
            }
        }
        if(!jTextPane3.getText().isEmpty() && !db.query("select count(*) from wypozyczenia where idks="+jTextPane3.getText()).equals("0")) db.usunDodaj("delete from wypozyczenia where idk="+idk+" and idks="+jTextPane3.getText());
        else JOptionPane.showMessageDialog(this, "Podaj odpowiedni IDKS zbioru.", "Blad oddawania", JOptionPane.ERROR_MESSAGE);
        
                for(int i=1; i<=Integer.parseInt(db.query("select count(*) from wypozyczenia where idk="+idk)); i++)
                {
                        dtm2.addRow( new Object[] {String.valueOf(i), db.query("select tytul from zbior where idks="+i+""), "aa", "a" } );

                }
    }//GEN-LAST:event_jButton4ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        //int licz=0;
        System.out.println(selectedData);
        System.out.println(jTextPane1.getText());
        System.out.println(jTextPane2.getText());
        if (dtm.getRowCount() > 0)
        {
            for (int i = dtm.getRowCount() - 1; i > -1; i--)
            {
                dtm.removeRow(i);
            }
        }

        try
        {
                query="select * from zbior where tytul like \"%"+jTextPane5.getText()+"%\" ";
                Charset.forName("UTF-8").encode(query);
                ResultSet rs = stmt.executeQuery(query);
                while(rs.next())
                {
                    dtm.addRow( new Object[] {rs.getString(1), rs.getString(2), db.query("select nazwisko from autor where ida="+rs.getString(3))+" "+db.query("select imie from autor where ida="+rs.getString(3)), db.query("select nazwa from gatunek where idg="+rs.getString(4)), rs.getString(5), rs.getString(6) } );
                }
        }
        catch (SQLException e) {}
        jButton2.setEnabled(true);
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        //JOptionPane.showMessageDialog(this, selectedData, "Zamowiles ksiazke", INFORMATION_MESSAGE);
        //System.out.println("cc"+db.query("select count(*) from zbior where idks=3"));
        if (!jTextPane4.getText().isEmpty() && !db.query("select count(*) from zbior where idks="+jTextPane4.getText()).equals("0") && db.query("select count(*) from wypozyczenia where idks="+jTextPane4.getText()+" and data_wyp= '"+jTextPane1.getText()+"' and data_zwr='"+jTextPane2.getText()+"'").equals("0"))
        {

            db.usunDodaj("insert into wypozyczenia (idks, data_wyp, data_zwr, idk, status) values ("+jTextPane4.getText()+", '"+jTextPane1.getText()+"', '"+jTextPane2.getText()+"', "+idk+", 'ocz')");
            if(dtm2.getRowCount() != Integer.valueOf(db.query("select count(*) from wypozyczenia where idk="+idk))) dtm2.addRow( new Object[] {jTextPane4.getText(), db.query("select tytul from zbior where idks="+jTextPane4.getText()+""), jTextPane1.getText(), jTextPane2.getText(), "", "rez" } );

        }
        else JOptionPane.showMessageDialog(this, "Zla wartosc IDKS.", "Blad oddawania", JOptionPane.ERROR_MESSAGE);
        jButton2.setEnabled(false);
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jComboBox1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jComboBox1ActionPerformed

    private void jComboBox2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jComboBox2ActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        
        


        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new WidokKlient().setVisible(true);
                
                
            }
        });
    }

    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JComboBox<String> jComboBox1;
    private javax.swing.JComboBox<String> jComboBox2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JScrollPane jScrollPane7;
    private javax.swing.JTable jTable1;
    private javax.swing.JTable jTable3;
    private javax.swing.JTextPane jTextPane1;
    private javax.swing.JTextPane jTextPane2;
    private javax.swing.JTextPane jTextPane3;
    private javax.swing.JTextPane jTextPane4;
    private javax.swing.JTextPane jTextPane5;
    // End of variables declaration//GEN-END:variables
}

