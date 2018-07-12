package sistemapedidos;

import java.util.*;
import javax.swing.JTextField;
import javax.swing.event.EventListenerList;

import javax.swing.table.AbstractTableModel;

public class ProdutoTabela extends AbstractTableModel {

    private ArrayList<Produto> dados = new ArrayList<>();
    //Nome das colunas da tabela.
    private String[] colunas = {"Código", "Descrição", "Qtde","Valor Unitário","Valor Total"};

    @Override
    //método que inseri o nome nas colunas da tabela.
    public String getColumnName(int coluna) {
        return colunas[coluna];
        
    }
    
    
    
    @Override
    //Quantidade de linhas
    public int getRowCount() {
        return dados.size();
    }

    @Override
    //Quantidade de colunas
    public int getColumnCount() {
        return colunas.length;
    }

    @Override
    //Pegar valor da tabela
    public Object getValueAt(int linha, int coluna) {
        
        switch(coluna){
            case 0:
                return dados.get(linha).getCodProduto();
            case 1:
                return dados.get(linha).getDescProduto();
            case 2:
                return dados.get(linha).getQuantProduto();
            case 3:
                return String.format("%.2f", dados.get(linha).getPrecoProduto());
            case 4:
                return String.format("%.2f", dados.get(linha).getQuantProduto() * dados.get(linha).getPrecoProduto());
        }
        return null;
    }
    
    
    public void adicionaLinha(Produto p){
        this.dados.add(p);
        this.fireTableDataChanged();
    }
    
    public void removeLinha(int linha){
        this.dados.remove(linha);
        this.fireTableRowsDeleted(linha, linha);
    }
    
    public void removeTabela() {
        for (int i=0; i<this.dados.size(); i++) {
            this.dados.remove(i);
            this.fireTableRowsDeleted(i, i);
            
        }
        //this.fireTableRowsDeleted(0, this.dados.size());
    }
    
   public void insereLinha(int linha, JTextField j) {
       ItemPedido ip = new  ItemPedido();
       ip.setCodProduto(this.getDados().get(linha).getCodProduto());
       ip.setQuantidade(this.getDados().get(linha).getQuantProduto());
       ip.setValorUnitario((float) this.getDados().get(linha).getPrecoProduto());
       ip.setNumPedido(Integer.parseInt(j.getText()));
       DAL.getInstancia().insereItemPedido(ip);
       //this.dados.remove(linha);
       //this.fireTableRowsDeleted(linha, linha);
   }
    
    //pega o valor total das vendas
    public float valorTotal() {
        float total = 0;
        for (int i=0; i<dados.size(); i++) {
            //System.out.println(i);
            total += dados.get(i).getQuantProduto() * dados.get(i).getPrecoProduto();
        }
        //System.out.println(total);
        return total;
    }
    
   
    @Override
    public void setValueAt(Object valor, int linha, int coluna) {
        switch(coluna){
            case 0:
                dados.get(linha).setCodProduto(Integer.parseInt((String) valor));
                break;
            case 1:
                dados.get(linha).setDescProduto((String) valor);
                break;
            case 2:
                dados.get(linha).setQuantProduto(Integer.parseInt((String) valor));
                break;
            case 3:
                dados.get(linha).setPrecoProduto(Float.parseFloat((String) valor));
                break;
        } 
        
        this.fireTableRowsUpdated(linha, linha);
    }

    public ArrayList<Produto> getDados() {
        return dados;
    }

    public void setDados(ArrayList<Produto> dados) {
        this.dados = dados;
    }

    public EventListenerList getListenerList() {
        return listenerList;
    }

    public void setListenerList(EventListenerList listenerList) {
        this.listenerList = listenerList;
    }

    

   

    public String[] getColunas() {
        return colunas;
    }

    public void setColunas(String[] colunas) {
        this.colunas = colunas;
    }
    
    
    
    
}

