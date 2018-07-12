/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sistemapedidos;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.scene.control.ComboBox;
import javax.swing.JComboBox;


public class DAL {
    private final String driver = "org.gjt.mm.mysql.Driver";
    private final String url = "jdbc:mysql://localhost/SistemaPedido";
    private final String usuario = "root";
    private final String senha = "";
    private Connection conexao;

    private static DAL dal=null;
    
    //implementação do singleton
    public static DAL getInstancia(){
        if(dal==null){
            dal = new DAL();
        }
        return dal;
    }
    
    private DAL() {
        try {
            Class.forName(driver);
            this.conexao = DriverManager.getConnection(url, usuario, senha);
            
            
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(DAL.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(DAL.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    //cria o banco de dados
    private void criaBanco() {
        String sql = "CREATE DATABASE IF NOT EXISTS poo2";
       //String cliente = "CREATE TABLE IF NOT EXISTS Cliente("
    }
    //insere os dados passados como parametro na tabela Cliente do banco de dados
    public void insereCliente(Cliente c) {    
        try {
            //Statement st = conexao.createStatement();
            String sql = "INSERT INTO Cliente (codcliente, Nome, endereco, telefone) values(?, ?, ?, ?)";
            PreparedStatement ps = conexao.prepareStatement(sql);
            //ps = conexao.prepareStatement(sql);
            ps.setInt(1, c.getCodCliente());
            ps.setString(2, c.getNome());
            ps.setString(3, c.getEndereco());
            ps.setString(4, c.getTelefone());
            ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(DAL.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    //insere os dados na tabela Vendedor do banco de dados
    public void insereVendedor(Vendedor v) {
        try {
            String sql = "INSERT INTO Vendedor(codvendedor, Nome) values(?,?) ";
            PreparedStatement ps = conexao.prepareStatement(sql);
            ps.setInt(1, v.getCodVendedor());
            ps.setString(2, v.getNome());
            ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(DAL.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
    }
    //insere os dados na tabela peoduto do banco de dados
    public void insereProduto(Produto p) {
        try {
            String sql = "INSERT INTO Produto(codproduto, descproduto, preco) values(?,?,?)";
            PreparedStatement ps = conexao.prepareStatement(sql);
            ps.setInt(1, p.getCodProduto());
            ps.setString(2, p.getDescProduto());
            ps.setDouble(3, p.getPrecoProduto());
            ps.executeUpdate();
            
        } catch (SQLException ex) {
            Logger.getLogger(DAL.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    //insere os dados na tabela Pedido
    public boolean inserePedido(Pedido p) {
        try {
            String sql = "INSERT INTO Pedido(cliente_codcliente, vendedor_codvendedor, dataemissao) values(?,?,?) ";
            PreparedStatement ps = conexao.prepareStatement(sql);
            //ps.setInt(1, p.getCodPedido());
            
            ps.setInt(1, p.getCodCliente());
            ps.setInt(2, p.getCodVendedor());
            ps.setDate(3, (java.sql.Date) p.getDataEmisao());
            ps.executeUpdate();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(DAL.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }
    
    //insere os dados na tabela itempedido
    public void insereItemPedido(ItemPedido i) {
        try {
            String sql = "INSERT INTO ItemPedido(pedido_numpedido, produto_codproduto, quantidade, produto_preco) VALUES (?,?,?,?)";
            PreparedStatement ps = conexao.prepareStatement(sql);
            ps.setInt(1, i.getNumPedido());
            ps.setInt(2, i.getCodProduto());
            ps.setDouble(3, i.getQuantidade());
            ps.setDouble(4, i.getValorUnitario());
            ps.executeUpdate();
            
        } catch (SQLException ex) {
            Logger.getLogger(DAL.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    //exclui o pedido do banco de dados Pedido
    public void excluirPedido(int numPedido) {
        try {
            String sql = "DELETE FROM Pedido where numPedido=" + numPedido;
            PreparedStatement ps = conexao.prepareStatement(sql);
            ps.executeUpdate();
            
        } catch (SQLException ex) {
            Logger.getLogger(DAL.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    //altera os campos na tabela Pedido onde numpepido for igual a numPedido passado como parametro
    public void alteraPedido(int numPedido, Pedido p) {
        try{
            String sql = "UPDATE Pedido SET (dataemissao = ?, Vendedor_codvendedor = ?, Cliente_codcliente = ?) WHERE numpedido="+numPedido;
            PreparedStatement ps = conexao.prepareStatement(sql);
            ps.setDate(1, (java.sql.Date) p.getDataEmisao());
            ps.setInt(2, p.getCodVendedor());
            ps.setInt(3, p.getCodCliente());
            ps.executeUpdate();
            
        
        } catch (SQLException ex) {
            Logger.getLogger(DAL.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    //faz a pesquisa no banco de dados e adiciona os resultados em um array. Apos retorna o array resultante
    public ArrayList<Cliente> pesquisarClientesCB(String nome) {
        //cria um novo array list cliete
        ArrayList<Cliente> resultado = new ArrayList<>();
        
        try {
            //faz a consulta no banco de dados
            String sql = "SELECT * from Cliente WHERE Nome LIKE '" + nome +"%'";
            //inicia o prepare statement
            PreparedStatement ps = conexao.prepareStatement(sql);
            //ps.setString(1, nome);
            //inicia o result set
            ResultSet rs = ps.executeQuery();
            //percorre o result set
            while (rs.next()) {
                //cria um novo objeto cliente e preenche os dados
                Cliente c = new Cliente();
                c.setCodCliente(rs.getInt(1));
                c.setNome(rs.getString(2));
                c.setEndereco(rs.getString(3));
                c.setTelefone(rs.getString(4));
                //adiciona no array list
                resultado.add(c);
                
               //System.out.println(rs.getString(1));
                
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(DAL.class.getName()).log(Level.SEVERE, null, ex);
        }
        //retorna o array list
        return resultado;
        
    }
    //metodo para pesquisa dos vendedores
    public ArrayList<Vendedor> pesquisarVendedorCB(String nome) {
        //cria o array list de vendedores
        ArrayList<Vendedor> resultado = new ArrayList<>();
        try {
            //faz a consulta no banco de dados
            String sql = "SELECT * from Vendedor WHERE Nome LIKE '" + nome +"%'";
            //inicia o prepared statement
            PreparedStatement ps = conexao.prepareStatement(sql);
            //ps.setString(1, nome);
            //inicia o result set
            ResultSet rs = ps.executeQuery();
            //percorre o result set rs
            while (rs.next()) {
                Vendedor v = new Vendedor(); //cria um novo objeto vendedor
                //preenche os dados do objeto e adiciona ao arraylist
                v.setCodVendedor(rs.getInt(1)); 
                v.setNome(rs.getString(2));
                resultado.add(v);
               //System.out.println(rs.getString(1));
                
            }
        
        }catch (SQLException ex) {
            Logger.getLogger(DAL.class.getName()).log(Level.SEVERE, null, ex);
        }
        //retorna o arrylist
        return resultado;
        
    }
    
    public ArrayList<Pedido> getTodosPedidos() {
        ArrayList<Pedido> listaPedidos = new ArrayList<>();
        try {
            String sql = "Select * from Pedido";
            PreparedStatement ps = conexao.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Pedido p = new Pedido();
                p.setCodPedido(rs.getInt(1));
                p.setDataEmisao(rs.getDate(2));
                p.setCodVendedor(rs.getInt(3));
                p.setCodCliente(rs.getInt(4));
                listaPedidos.add(p);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DAL.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return listaPedidos;
        
    }
    
    public ArrayList<ItemPedido> getTodosItens() {
        ArrayList<ItemPedido> listaItens = new ArrayList<>();
        try {
            String sql = "Select * from ItemPedido";
            PreparedStatement ps = conexao.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while(rs.next()) {
                ItemPedido ip = new ItemPedido();
                ip.setQuantidade(rs.getInt(1));
                ip.setNumPedido(rs.getInt(2));
                ip.setCodProduto(rs.getInt(3));
                ip.setValorUnitario(rs.getFloat(4));
                listaItens.add(ip);
                
            }
            
            
        } catch (SQLException ex) {
            Logger.getLogger(DAL.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return listaItens;
    }
    
    //retorna o ultimo numero do ultimo pedido
    public int getUltimoPedido() {
        int p = 0;
        
        try {
            //faz a consulta ao banco de dados
            String sql = "SELECT numpedido FROM Pedido ORDER BY numpedido DESC"; //DESC LIMIT 1";
            PreparedStatement ps = conexao.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            if(rs.next()) {
                p = rs.getInt(1);
            } 
            
        } catch (SQLException ex) {
            Logger.getLogger(DAL.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return p +1;
    }
    
    public Cliente pesquisarCliente(String nome) {
        try {
            String sql = "SELECT * from Cliente WHERE Nome LIKE '%" + nome +"%'";
            PreparedStatement  ps = conexao.prepareStatement(sql);
            //ps.setString(1, nome);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                Cliente c = new Cliente();
                c.setCodCliente(rs.getInt(1));
                c.setNome(rs.getString(2));
                c.setEndereco(rs.getString(3));
                c.setTelefone(rs.getString(4));
                
                return c;
            }
        } catch (SQLException ex) {
            Logger.getLogger(DAL.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return null;
    }
    
    
    public Vendedor pesquisarVendedor(String nome) {
        try {
            String sql = "SELECT * from Vendedor WHERE Nome LIKE '%" + nome +"%'";
            PreparedStatement  ps = conexao.prepareStatement(sql);
            //ps.setString(1, nome);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                Vendedor v = new Vendedor();
                v.setCodVendedor(rs.getInt(1));
                v.setNome(rs.getString(2));
                
                return v;
            }
        } catch (SQLException ex) {
            Logger.getLogger(DAL.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return null;
    }
    
    
    public ArrayList<Produto> pesquisarProdutoCB(String desc) {
        ArrayList<Produto> resultado = new ArrayList<>();
        try {
            String sql = "SELECT * FROM Produto WHERE descproduto LIKE '" +desc+"%'";
            PreparedStatement ps = conexao.prepareStatement(sql);
            //ps.setString(1, desc);
            ResultSet rs = ps.executeQuery();
            
            while (rs.next()) {
                Produto p = new Produto();
                p.setCodProduto(rs.getInt(1));
                p.setDescProduto(rs.getString(2));
                p.setPrecoProduto(rs.getDouble(3));
                resultado.add(p);
                
                
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(DAL.class.getName()).log(Level.SEVERE, null, ex);
        }
        return resultado;
    } 
    
    public Produto pesquisarProduto(String desc) {
         try {
            String sql = "SELECT * FROM Produto WHERE descproduto LIKE '%" + desc +"%'";
            PreparedStatement  ps = conexao.prepareStatement(sql);
            //ps.setString(1, desc);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                Produto p = new Produto();
                p.setCodProduto(rs.getInt(1));
                p.setDescProduto(rs.getString(2));
                p.setPrecoProduto(rs.getDouble(3));
                
                return p;
                
            }
        } catch (SQLException ex) {
            Logger.getLogger(DAL.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return null;
    }
}
