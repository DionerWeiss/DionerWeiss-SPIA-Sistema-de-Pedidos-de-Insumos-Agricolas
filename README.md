# SPIA
Sistema de Pedidos de Insumos Agrícolas.

<b>Desenvolvedor:</b> Dioner Weiss. </br>
<b>Tecnologias Utilizadas:</b> NetBeans, Java, MySQL. </br>
<b>Descrição: </b>Sistema de pedidos para uma loja de insumos agrícolas. O programa importa três arquivos '.csv' com as insformações dos vendedores, clientes e produtos da loja. Os vendedores devem preencher as informações requeridas, que serão inseridas em um banco de dados e após emitir os arquivos 'Pedido.csv' e 'ItemPedido.csv' para ser importado pelo sistema ERP da loja.

## Funcionalidades: </br>
1 - Interface: Ao executar estará bloqueada. Para desbloqueá-la é nescessário clicar em 'Emitir Pedido' no menu principal ou utilizar o atlaho CTRL+I.</br>

2 - Barra de Menu: </br>
  2.1 - Importar Dados: Importa para o banco de dados as informações contidas nos arquivos 'Cliente.csv', 'Vendedor.csv' e    'Produto.csv'. </br>
  2.2 - Emitir pedido: Desbloqueia a interface para uso. </br>
  2.3 - Exportar Dados: Exporta os dados das tabelas Pedido e ItemPedido para os arquivos 'Pedido.csv' e 'ItemPedido.csv' respectivamente. </br>
  2.4 - Ajuda: Botão de ajuda, com todas as informações referentes ao programa. </br>  
3 - Campos:
  3.1 - Nº Pedido: campo com o número do pedido. É sugerido automaticamente e não é editável. </br>
  3.2 - Data: campo para a data. É sugerida a data atual do computador, porém pode ser editada. </br>
  3.3 - Cliente: Barra de pesquisa dos clientes. Ao selecionar um resultado, será mostrado o contato e o endereço do cliente. </br>
  3.4 - Vendedor: Barra de pesquisa dos vendedores. </br>
  3.5 - Produto: Barra de pesquisa dos produtos. </br>
      3.5.1 - Cod. Produto: campo com o código do produto selecionado na pesquisa do item 3.5. Não editável.</br>
      3.5.2 - Descrição do Produto: mostra os resultados da pesquisa do item 3.5. Não editável. </br>
      3.5.3 - Quantidade: campo a ser preenchido com a quantidade do produto.</br>
      3.5.4 - Preço: campo com o preço do produto. Não editável. </br>
  3.6 - Adicionar Produto: insere as informações do item 3.5, devidamente preenchidas, na tabela de pedidos.
  3.7 - Excluir Produto: remove um item selecionado da tabela de pedidos.
  3.8 - Alterar Produto: modifica as informações de um item selecionado na tabela de pedidos. Ao clicar em algum item da tabela, as informações sobre aquele produto serão mostradas nos campos do item 3.5. Para alterar alguma informação, basta modificar a informação desejada em algum dos campos (apenas quantidade, já que os demais itens não são editáveis). Após concluir, basta clicar no botão Alterar Produto e as informações serão modificadas na tabela de pedidos.
  3.9 - Tabela de Pedidos: 
