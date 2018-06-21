package br.com.caixaCifra.Util;

import br.com.caixaCifra.Model.ClienteLogado;
import br.com.caixaCifra.Model.Conta;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Image;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
public class Relatorio {
    
    
    public boolean gerarRelatorio(String titulo, String conteudo,String nome) throws IOException {
        try {
            Document relatorioPDF = new Document();
            relatorioPDF.setPageSize(PageSize.A6);            
            PdfWriter.getInstance(relatorioPDF, new FileOutputStream("C:\\Users\\Casa\\Desktop\\Relatorio\\"+titulo+"\\"+titulo+"_"
                            +nome+"_"+pegarData()+"_"+pegarHora()+".pdf"));
            
            relatorioPDF.open();

            relatorioPDF.add(new Paragraph("--------------------------------------------------------"));
            Image figura = Image.getInstance("src/Resources/recibo25.png");
            relatorioPDF.add(figura);
            relatorioPDF.add((new Paragraph("Banco Cifra")));
            relatorioPDF.add(new Paragraph("--------------------------------------------------------"));
            relatorioPDF.add(new Paragraph("Operação de "+titulo+" efetuada."));
            relatorioPDF.add(new Paragraph("Data e hora da efetuação da operação:\n"+pegarDataeHora()));
            relatorioPDF.add(new Paragraph("--------------------------------------------------------"));
            relatorioPDF.add(new Paragraph(conteudo));
            relatorioPDF.add(new Paragraph("--------------------------------------------------------"));
             
            relatorioPDF.close();
            
            return true;
        }catch(DocumentException de){
            
             System.err.println(de.getMessage());
            return false;

        }catch (FileNotFoundException ex){
            
            System.err.println(ex.getMessage());
            return false;

        }
       
    }
    private String pegarDataeHora() { 
	DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss"); 
	Date date = new Date(); 
        
	return dateFormat.format(date); 
    }
    private String pegarData() { 
	DateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy"); 
	Date date = new Date(); 
        
	return dateFormat.format(date); 
    }
    private String pegarHora() { 
	DateFormat dateFormat = new SimpleDateFormat("HH.mm.ss"); 
	Date date = new Date(); 
        
	return dateFormat.format(date); 
    }

    public void gerarRelatorioSaque(String valor,Conta conta) throws IOException {
        gerarRelatorio("Saque", "Cliente: "+ClienteLogado.getCliente().getNomeCliente()
               +"\nSaque de: R$"+valor+"\nConta: "+conta.getNumConta()+" Agência: "+conta.getAgenciaConta()
               +"\nTipo conta: "+conta.getTipoConta()+" Banco: "+conta.getBanco(),
                (ClienteLogado.getCliente().getNomeCliente()).replace(" ", ""));
    }

    public void gerarRelatorioDeposito(String valor, Conta conta) throws IOException {
        gerarRelatorio("Deposito","Depósito de: R$"+valor+"\nConta: "+conta.getNumConta()+" Agência: "+conta.getAgenciaConta()
               +"\nTipo conta: "+conta.getTipoConta()+" Banco: "+conta.getBanco()
               +"\nCliente: "+conta.getCliente().getNomeCliente(),
                conta.getCliente().getNomeCliente().replace(" ", ""));    
    }
    
    public void gerarRelatorioSaldo(String valor, Conta conta) throws IOException {
        gerarRelatorio("Saldo","Saldo de: R$"+valor+"\nConta: "+conta.getNumConta()+" Agência: "+conta.getAgenciaConta()
               +"\nTipo conta: "+conta.getTipoConta()+" Banco: "+conta.getBanco()
               +"\nCliente: "+ClienteLogado.getCliente().getNomeCliente(),
                (ClienteLogado.getCliente().getNomeCliente()).replace(" ", ""));
    }
    
    public void gerarRelatorioTransferencia(String valor, Conta contaRemetente, Conta contaBonificada) throws IOException {
        gerarRelatorio("Transferencia","Transferência no valor: R$"+valor+"\nDe Conta: "+contaRemetente.getNumConta()
               +" Agência: "+contaRemetente.getAgenciaConta()
               +"\nTipo conta: "+contaRemetente.getTipoConta()+" Banco: "+contaRemetente.getBanco()
               +"\nCliente: "+ClienteLogado.getCliente().getNomeCliente()
               +"\n--------------------------------------------------------"
               +"\nPara Conta: "+contaBonificada.getNumConta()
               +" Agência: "+contaBonificada.getAgenciaConta()
               +"\nTipo conta: "+contaBonificada.getTipoConta()+" Banco: "+contaBonificada.getBanco()
               +"\nCliente: "+contaBonificada.getCliente().getNomeCliente(),
               (ClienteLogado.getCliente().getNomeCliente()).replace(" ", "")
               +"Para"+contaBonificada.getCliente().getNomeCliente().replace(" ", ""));
    }

    public void gerarRelatorioPagamento(String valor, String vencimento,String codBarra, Conta conta) throws IOException {
         gerarRelatorio("Pagamento","Valor do Boleto: R$"+valor+"\nData de vencimento: "+vencimento+"\nCódigo de Barra: "+
                 codBarra+"\nConta: "+conta.getNumConta()+" Agência: "+conta.getAgenciaConta()
               +"\nTipo conta: "+conta.getTipoConta()+" Banco: "+conta.getBanco()
               +"\nCliente: "+ClienteLogado.getCliente().getNomeCliente(),
                (ClienteLogado.getCliente().getNomeCliente()).replace(" ", ""));
    }


}
