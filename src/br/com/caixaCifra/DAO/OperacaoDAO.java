package br.com.caixaCifra.DAO;

import br.com.caixaCifra.Model.Banco;
import br.com.caixaCifra.Model.Cliente;
import br.com.caixaCifra.Model.ClienteLogado;
import br.com.caixaCifra.Model.Conta;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class OperacaoDAO {
 
    
    ResultSet rs;
    int senha=0;
    Conexao con = new Conexao();
    
    public Conta validarLogin(Conta conta){
         Cliente cliente = new Cliente();
         Banco banco = new Banco(); 
  
     try {
  
            String query="SELECT tbCliente.codCliente,tbCliente.nomeCliente,tbConta.codConta FROM tbCliente" +
            " INNER JOIN tbConta ON tbCliente.codCliente = tbConta.codCliente WHERE tbConta.codBanco=? AND" +
            " tbConta.agenciaConta=? AND tbConta.numConta=? AND tbConta.tipoConta=?;";

            PreparedStatement stmt  = con.abrirConexao().prepareStatement(query);
            stmt.setInt(1,conta.getBanco().getCodBanco());
            stmt.setString(2,conta.getAgenciaConta());
            stmt.setString(3,conta.getNumConta());
            stmt.setString(4,conta.getTipoConta());
            rs = stmt.executeQuery();
            if (rs.next()) {
               
              cliente.setCodCliente(rs.getInt("codCliente"));
              cliente.setNomeCliente(rs.getString("nomeCliente"));
              conta.setCodConta(rs.getInt("codConta"));
            
                  
            } else {
                 System.out.println(stmt.toString());

            }
             
           
            con.fecharConexao();
            stmt.close();
            
        } catch (SQLException ex) {
            System.out.println("Falha no Login."+ex.toString());
            
            
        }
        conta.setCliente(cliente);
        return conta;
      }
     
     public List<Banco> construirCombo(List<Banco> bancos){
          
  
     try {
  
            String query="SELECT * FROM tbBanco;";

            PreparedStatement stmt  = con.abrirConexao().prepareStatement(query);
            
            rs = stmt.executeQuery();
            while (rs.next()) {
              Banco banco = new Banco();
              banco.setCodBanco(rs.getInt("codBanco"));
              banco.setNomeBanco(rs.getString("nomeBanco"));
              bancos.add(banco);
            } 
             
           
            con.fecharConexao();
            stmt.close();
            return bancos;
        } catch (SQLException ex) {
            
            System.out.println("Falha na montagem da combo."+ex.toString());
            return null;
            
        }
    }
	
    public double consultarSaldo(int conta){
        double saldo=-1;
     try{
  
            String query="SELECT saldoConta FROM tbConta WHERE codConta=?;";

            PreparedStatement stmt  = con.abrirConexao().prepareStatement(query);
            stmt.setInt(1,conta);
            
            rs = stmt.executeQuery();
           
            
            if (rs.next()) {
               
              saldo= rs.getDouble("saldoConta");
             
            } else {
                 System.out.println(stmt.toString());
                 

            }
            con.fecharConexao();
            stmt.close();
           
            
         
        } catch (SQLException ex) {
            System.out.println("Falha ao pegar o saldo."+ex.toString());
           
            
        }
        return saldo;
      }
    
    public Conta consultarConta(int codConta){
  
      Conta conta = new Conta();
      Banco banco = new Banco();
              
        try{
  
            String query="SELECT numConta,agenciaConta,tipoConta,codBanco FROM tbConta WHERE codConta=?;";

            PreparedStatement stmt  = con.abrirConexao().prepareStatement(query);
            stmt.setInt(1,codConta);
            
            rs = stmt.executeQuery();
           
            
            if (rs.next()) {
               
              conta.setNumConta(rs.getString("numConta"));
              conta.setAgenciaConta(rs.getString("agenciaConta"));
              conta.setTipoConta(rs.getString("tipoConta") );
              banco.setCodBanco(rs.getInt("codBanco"));

            } else {
                 System.out.println(stmt.toString());

            }
            con.fecharConexao();
            stmt.close();
           
            
         
        }catch(SQLException ex) {
            System.out.println("Falha ao pegar a conta."+ex.toString());
           
            
        }
        conta.setBanco(banco);
        return conta;
    }

    public void attSaldo(double saldo, int id) {
        
        try {

            String query="UPDATE tbConta  SET  saldoConta=? WHERE codConta=?;";

            PreparedStatement stmt  = con.abrirConexao().prepareStatement(query);
            stmt.setDouble(1,saldo);
            stmt.setInt(2,id);

            stmt.executeUpdate();


 
            con.fecharConexao();
            stmt.close();



        }catch (SQLException ex) {
               System.out.println("Falha ao  atualizar o saldo."+ex.toString());


        }
      
    }
    public int pegarSenha(int idConta) {
        
        try {

            String query="SELECT senhaConta FROM tbConta WHERE codConta=?;";

            PreparedStatement stmt  = con.abrirConexao().prepareStatement(query);
            stmt.setInt(1 ,idConta);

            rs=stmt.executeQuery();
        
            if(rs.next()){
                senha= rs.getInt("senhaConta");
            }              
    
            con.fecharConexao();
            stmt.close();

        }catch(SQLException ex){
            
               System.out.println("Falha ao pegar senha."+ex.toString());
               
        }
        return senha;
    }    
    
}
