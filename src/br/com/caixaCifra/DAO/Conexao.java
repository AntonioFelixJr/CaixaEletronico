package br.com.caixaCifra.DAO;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexao {

    Connection con;
     
    public Connection abrirConexao(){
        System.out.println("Abrindo conexão...");
         try {
             Class.forName("com.mysql.jdbc.Driver");
         } catch (ClassNotFoundException ex) {
            System.out.println("Falha na busca do conector"+ex.toString());         
         
         }
         
        try {
            
            return con = DriverManager.getConnection("jdbc:mysql://localhost"
                    + "/dbBancoCifra?useTimezone=true&serverTimezone=UTC", "root","12345");

        } catch (SQLException ex){
            
            System.out.println("Falha na conexão do banco"+ex.toString());
            return null;
        }
    
        
        
    }
    public void fecharConexao() throws SQLException{
        System.out.println("Fechando conexão...");

        con.close();      
    }
        
            
       
    
     
    
    
}
