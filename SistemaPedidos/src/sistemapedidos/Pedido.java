/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sistemapedidos;

import java.util.Date;

/**
 *
 * @author dioner
 */
public class Pedido {
    private int codPedido;
    private int codVendedor;
    private int codCliente;
    private Date dataEmisao;

    public int getCodPedido() {
        return codPedido;
    }

    public void setCodPedido(int codPedido) {
        this.codPedido = codPedido;
    }

    public int getCodVendedor() {
        return codVendedor;
    }

    public void setCodVendedor(int codVendedor) {
        this.codVendedor = codVendedor;
    }

    public int getCodCliente() {
        return codCliente;
    }

    public void setCodCliente(int codCliente) {
        this.codCliente = codCliente;
    }

    public Date getDataEmisao() {
        return dataEmisao;
    }

    public void setDataEmisao(Date dataEmisao) {
        this.dataEmisao = dataEmisao;
    }
    
    
    
}
