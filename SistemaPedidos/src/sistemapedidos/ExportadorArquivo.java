/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sistemapedidos;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author dioner
 */
public class ExportadorArquivo {
    
    public boolean ExportaPedido(String arq, ArrayList<Pedido> listaP) {
        try {
            
            FileWriter fw = new FileWriter(arq);
            for (int i=0; i<listaP.size(); i++) {
                fw.append(listaP.get(i).getCodPedido() + ";");
                fw.append(listaP.get(i).getDataEmisao() + ";");
                fw.append(listaP.get(i).getCodVendedor() + ";");
                fw.append(listaP.get(i).getCodCliente() + "\n");
            }
            fw.close();

            return true;
            
                   } catch (IOException ex) {
            Logger.getLogger(ExportadorArquivo.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
        
    }
    
    public boolean ExportaItemPedido(String arq, ArrayList<ItemPedido> listaI) {
        try {
            FileWriter fw = new FileWriter(arq);
            for (int i=0; i<listaI.size(); i++) {
                fw.append(listaI.get(i).getQuantidade() + ";");
                fw.append(listaI.get(i).getNumPedido() + ";");
                fw.append(listaI.get(i).getCodProduto() + ";");
                fw.append(listaI.get(i).getValorUnitario() + "\n");
                
            }
            fw.close();
            
            return true;
        } catch (IOException ex) {
            Logger.getLogger(ExportadorArquivo.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }
    
}
