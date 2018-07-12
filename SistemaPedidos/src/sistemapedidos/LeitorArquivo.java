/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sistemapedidos;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author dioner
 */
public class LeitorArquivo {
    //importa os dados da planilha Cliente para um ArrayList e insere no banco de dados usando 
    //o metodo insereCliente() da classe DAL
    public void ImportaArquivoCliente(String arq) {
        FileReader fr = null;
        try {
            fr = new FileReader(arq);
            FileInputStream fis = new FileInputStream(arq);
            BufferedReader br = new BufferedReader(new InputStreamReader(fis, "ISO-8859-1"));
            
            String linha = br.readLine();
            //ArrayList <Cliente> clientes = new ArrayList<>();
            while (linha != null) {
                String[] cells = linha.split(";");
                Cliente c = new Cliente();
                c.setCodCliente(Integer.parseInt(cells[0]));
                c.setNome(cells[1]);
                c.setEndereco(cells[2]);
                c.setTelefone(cells[3]);
                //clientes.add(c);
                DAL.getInstancia().insereCliente(c);
                linha = br.readLine();               
            }
            
            
            
        } catch (IOException ex) {
            Logger.getLogger(LeitorArquivo.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    //importa os dados da planilha Vendedor para um ArrayList e insere no banco de dados usando 
    //o metodo insereVenddor da classe DAL
    public void ImportaArquivoVendedores(String arq) {
        FileReader fr = null;
        try {
            fr = new FileReader(arq);
            FileInputStream fis = new FileInputStream(arq);
            BufferedReader br = new BufferedReader(new InputStreamReader(fis, "ISO-8859-1"));
//            br.readLine();
            String linha = br.readLine();
            //ArrayList<Vendedor> vendedores = new ArrayList<>();
            while(linha != null) {
                String[] cells = linha.split(";");
                Vendedor v = new Vendedor();
                v.setCodVendedor(Integer.parseInt(cells[0]));
                v.setNome(cells[1]);
                DAL.getInstancia().insereVendedor(v);
                linha = br.readLine();
            }
            
            
        } catch (FileNotFoundException ex) {
            Logger.getLogger(LeitorArquivo.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(LeitorArquivo.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void ImportaArquivoProduto(String arq) {
        FileReader fr = null;
        try {
            fr = new FileReader(arq);
            FileInputStream fis = new FileInputStream(arq);
            BufferedReader br = new BufferedReader(new InputStreamReader(fis, "ISO-8859-1"));
            String linha = br.readLine();
            while (linha != null) {
                String[] cells = linha.split(";");
                Produto p = new Produto();
                p.setCodProduto(Integer.parseInt(cells[0]));
                p.setDescProduto(cells[1]);
                p.setPrecoProduto(Double.parseDouble(cells[2].replace(",", ".")));//troca a ',' por '.'
                DAL.getInstancia().insereProduto(p);
                linha = br.readLine();
            }
            
        } catch (FileNotFoundException ex) {
            Logger.getLogger(LeitorArquivo.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(LeitorArquivo.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
    public boolean importaTudo(String cliente, String vendedor, String produto) {
        try {
            this.ImportaArquivoCliente(cliente);
            this.ImportaArquivoVendedores(vendedor);
            this.ImportaArquivoProduto(produto);
            return true;
        } catch (Exception e) {
            return false;
        }
        
        
    }
    
    
}
