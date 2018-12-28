# Desafio - SkyHub

Pedimos que a entrega do desafio seja realizada em até 5 dias. Caso precise de mais tempo, nos avise que podemos negociar o prazo.


## A loja do "seu" Manuel
Seu Manuel deseja expandir seus negócios e vender online, para isso ele te contratou para tocar o desenvolvimento do sistema que vai dar suporte à operação da loja. São listados abaixo os serviços que devem ser disponibilizados pelo sistema.

### Produtos
Serviço de produtos para que seja possível cadastrar, recuperar, atualizar e deletar os produtos de sua loja.

Os produtos serão identificados por um **código** e terá as informações de **nome**, **descrição**, **estoque**, **preço** e mais alguns **atributos** que variam de produto para produto.

### Pedidos
Serviço de pedidos que permitirá registrar, recuperar e atualizar as vendas dos seus produtos. As informações dos pedido consistem em um **código** identificador, **data** da compra, nome do **comprador**, **estado** (ex: novo, aprovado, entregue e cancelado), valor do **frete** e a lista de **itens** que foram vendidos - cada item possui: **código** do produto; **quantidade**; e o **preço** de venda.

### Relatórios
Por fim, o sistema também deve fornecer a possibilidade de extrair um relatório que informa o **ticket médio** dado um intervalo de tempo (data inicial e final) - a definição de ticket médio é facilmente encontrada na web, mas fique à vontade para indicar a definição utilizada na solução.

## Considerações
Considere que o sistema deve:

- Recusar a criação de um pedido com item não cadastrado;
- Decrementar o estoque do(s) produto(s) sempre que um pedido é realizado;
- Evitar o cadastro de pedidos cujo item não tem estoque suficiente.

### Observações:
- Para o desafio, pedimos apenas que desenvolva as APIs do sistema (não é necessário desenvolver as telas - frontend);
- A API deve usar dados no formato JSON para realizar as operações;
- A estrutura do JSON de cada recurso deve ser definida por você (justifique as escolhas onde achar pertinente);
- Os dados devem ser armazenados em um banco também a seu critério;
- É necessário escrever testes automáticos para os serviços;
- A escolha das ferramentas para realizar o desafio são livres, mas esperamos que você nos diga o porque usou cada uma delas;
- Implementar um procedimento de geração de dados de exemplo seria um **ponto extra**!

## Critérios de avaliação
- Atendimento aos requisitos descritos;
- Legibilidade da solução;
- Cobertura dos testes;
- Eficiência - evite desperdício de recursos!

Crie um fork desse repositório e nos envie um **pull request**.

Não esqueça de ensinar como instalamos e rodamos seu projeto em nosso ambiente. :sunglasses:
