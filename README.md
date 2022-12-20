## Conceito aplicado em projeto sobre Design Patterns
> Design Patterns são soluções genéricas para problemas recorrentes do desenvolvimento de software orientado a objetos.

[![Maven Badge](https://img.shields.io/badge/-Maven-000?style=flat-square&logo=Apache-Maven&logoColor=white&link=https://maven.apache.org/)](https://maven.apache.org/)
[![Design Badge](https://img.shields.io/badge/-GitHub-blue?style=flat-square&logo=GitHub&logoColor=white&link=https://refactoring.guru/design-patterns)](https://refactoring.guru/design-patterns)


<img align="right" width="300" height="450" src="https://github.com/willdkdevj/DESIGN_PATTERNS/blob/master/assets/designer.png">

## Descrição da Aplicação
A aplicação consiste em um simples projeto que implementa uma calculadora para fins tributários de uma empresa, na qual necessita administrar orçamentos cadastros e como ficariam os cálculos tributários a partir deles.

## Sobre o Design Patterns
**Design Patterns** em tradução literal serria *Padrões de Projeto*, mas no Brasil quando ouvimos falar de **padrão** entendemos que é algo regulamentado, que devemos seguir a risca. Mas não é bem isso que significa nos padrões americano.
Design Patterns são soluções singulares para problemas comuns no projeto de desenvolvimento de software. Eles são como modelos pré-fabricados que você pode personalizar para resolver um problema de design recorrente em seu código.
Design Patterns são um conjunto de ferramentas de soluções testadas e comprovadas para problemas comuns no projeto de software. Mesmo que você nunca encontre esses problemas, conhecer padrões ainda é útil porque ensina como resolver todos os tipos de problemas usando princípios de design orientado a objetos.
Os Design Patterns definem uma linguagem comum que você e seus colegas de equipe podem usar para se comunicar com mais eficiência.

### As Três Principais Categorias
Os padrões mais universais e de alto nível são os padrões de arquitetura . Os desenvolvedores podem implementar esses padrões em praticamente qualquer idioma. Ao contrário de outros padrões, eles podem ser usados ​​para projetar a arquitetura de um aplicativo inteiro. Além disso, todos os padrões podem ser categorizados por sua intenção ou propósito.
Existem três principais categorias de padrões de projeto:

* **Padrões de criação** que fornecem mecanismos de criação de objetos que aumentam a flexibilidade e a reutilização do código existente.

* **Padrões estruturais** que explicam como montar objetos e classes em estruturas maiores, mantendo essas estruturas flexíveis e eficientes.

* **Padrões Comportamentais** que cuidam da comunicação eficaz e da atribuição de responsabilidades entre os objetos.

## Aplicação do Design Patterns no Projeto
Cada uma destas categorias possuem seus próprios mecanismos, que são sub-categorias, que possuem funcionalidades distintas conforme a estrutura de código a ser implementada. 
Neste projeto, eu utilizei somente a **Categoria Comportamental** a fim de deixar o código mais coeso e simples, na qual o primeiro a ser utilizado foi a sub-categoria **Strategy**.
### State Pattern
No cenário em que ela foi aplicada foi no contexto de vários impostos, como ICMS, ISS, entre outros, onde para cada imposto a uma alíquota a ser calculada. Desta forma, poderiamos criar um método que recebesse o orçamento e calculasse aliquota através de um ENUM que representaria o imposto. Mas veja, este método poderia ficar gigante, pois a cada novo imposto seria necessário incluir mais uma cadeia no **IF** ou **SWITCH**.

Sempre que uma nova funcionalidade for implementada, o ideal é que possamos criar código novo e editar o mínimo possível de código já existente. Este é um dos principais pontos do princípio Aberto-Fechado *(Open-Closed Principle)* do **SOLID**. Ao editar código existente, podemos acabar quebrando funcionalidades já implementadas e funcionais.
Desta forma, seguindo este conceitos podemos colocar os cálculos (regras) encapsulados em métodos em classes distintas, uma para cada imposto.
```java
public class ICMS implements Imposto{
    @Override
	public BigDecimal calcular(Orcamento orcamento) {
		return orcamento.getValor().multiply(new BigDecimal("0.1"));
	}
}
```
> Classe ICMS - com o cálculo de 10% do valor do orçamento

```java
public class ISS implements Imposto{
	@Override
	public BigDecimal calcular(Orcamento orcamento) {
		return orcamento.getValor().multiply(new BigDecimal("0.06"));
	}
}
```
> Classe ISS - com o cálculo de 6% do valor do orçamento

Desta forma, quando precisar realizar o cálculo de determinado imposto a classe responsável por realizar o cálculo [CalculadoraDeImpostos] passa a interface como parâmetro, onde toda a classe que a implementar será aceita através do polimorfismo.
```java
public class CalculadoraDeImpostos {

	public BigDecimal calcular(Orcamento orcamento, Imposto imposto) {

		return imposto.calcular(orcamento);
	}

}
```
O Strategy é indicado quando temos um parâmetro e já sabemos que aquela regra vai ser aplicada, sendo o parâmetro determinante para a estratégia a ser utilizada.

### Chain of Responsability Pattern
Agora o negócio pode surgir com novas regras para tratar de descontos a serem aplicados nos orçamentos. Onde conforme a quantidade ou o valor a ser gasto seja aplicado um desconto ao orçamento. Poderiamos entrar novamente na questão de utilizar o **IF** para verificar a condicional da regra e realizar o cáculo, ficando algo como o código abaixo
```java
public class CalculadoraDeDescontos {
    
    public BigDecimal calcular(Orcamento orcamento){
        if(orcamento.getQtdItens() > 5){
            return orcamento.getValor().multiply(new BigDecimal("0.1"));
        }

        if(orcamento.getValor().compareTo(new BigDecimal("500")) > 0){
            return orcamento.getValor().multiply(new BigDecimal("0.1"));
        }
        
        return BigDecimal.ZERO;
    }
}
```
Funcionaria sem problemas, mas entrariamos naquele dilema: "E se entrar mais regras de negócios?". Ficaria um método imenso díficil de administrar. Desta forma, poderiamos pensar em utilizar o conceito da **Strategy**, mas aqui a definição não está clara se é para aplicar somente um desconto ou conforme o orçamento se enquadre a regra conforme ele vai as validando se é para aplicar o desconto. Então, seria uma validação em cascata a ser realizada, neste contexto entra a sub-categoria **Chain of Responsability**.

Foi utilizado o contexto similar ao *Strategy*, mas ao invés de aplicar somente a regra, foi inserido cada **if** em um método em classes isoladas, seguindo o Single Responsability [SOLID]. Desta forma, podemos instanciar a classe e invocar o método para obter o valor do desconto.
```java
    public BigDecimal calcular(Orcamento orcamento){
        BigDecimal desconto = new CalcularValorDeDescontoAcimaDeCinco(orcamento);
        desconto.add(new CalcularValorDeDescontoParaCompraAcimaQuinhentos(orcamento));

        return desconto;
    }
```
Notem que acabamos com aquela cadeia de IFs que poderia deixar o código díficil de entender, mas é possível melhorar. Como? Criando uma classe modelo a fim de todas as demais regras de descontos a sigam a fim de padronizar esta entrada através de **Classe Abstrata**.

Foi criada uma classe abstrata de Desconto onde ela tem o modelo que as demais classes precisam seguir. Segue abaixo:
```java

public abstract claass Desconto {
    public Desconto proximoDesconto;

    public Desconto(Desconto desconto){
        this.proximoDesconto = desconto;
    }

    public abstract BigDecimal calcular(Orcamento orcamento);
}
```
Observe que existe um método abstrato, isso significa que toda classe que herdar de Desconto precisará implementar o método conforme sua necessidade.
Desta forma, a classe para responsável por calcular os descontos ficará da seguinte maneira.

```java
public class CalculadoraDeDescontos {

    public BigDecimal calcular(Orcamento orcamento){
        Desconto desconto= new DescontoMaiorQueCincoItens(
                new DescontoMaiorQueQuinhentos(
                        new SemDesconto()));

        return desconto.calcular(orcamento);
    }
}
```
Desta forma, diferente do Strategy que foi utilizado a *CalculadoraDeImpostos*, não está explicita a regra a ser aplicada. Então foi necessário validar cada uma das regras a fim de aplica-la ou não. Essa que é a ideia do padrão **Chain Of Responsibility**.

Mas mesmo com esta melhoria ficou com um cara que ainda é possível melhorar este código! Mas como? Vocês notaram que as classes responsáveis por validaremm a regra de desconto seguem  o mesmo padrão de avaliação (com **IF**)? 

É possível passar desta maneira este processo de validação para a classe que serve de modelo para as regras de descontro, só será necessário modifica-la um pouco.
```java
public abstract class Desconto {
        public Desconto proximoDesconto;

    public Desconto(Desconto desconto){
        this.proximoDesconto = desconto;
    }
    public BigDecimal calcular(Orcamento orcamento){
        if(checarRegra(orcamento)){
            return validarCalcular(orcamento);
        }
        return proximoDesconto.calcular(orcamento);
    }

    protected abstract BigDecimal efetuarDesconto(Orcamento orcamento);
    public abstract boolean checarRegra(Orcamento orcamento);
}
```
### Template Method Pattern
Agora sim, a classe é um modelo para as demais que irão extende-la caso seja necessário a criação de mais regras para descontos, onde o método que responsável por validar a regra [checarRegra] e o método responsável por efetuar o cálculo de descontro [efetuarDesconto] estão disponíveis para que as classes filhas a implementem, onde o método calcular agora é um modelo de calculo que aplicará esta validação. Este é outro padrão conhecido como **Template Method**.

Desta forma, as classes filhas ficarão da seguinte forma.
```java
public class DescontoMaiorQueCincoItens extends Desconto {
    public DescontoMaiorQueCincoItens(Desconto proximoDesconto) {
        super(proximoDesconto);
    }

    @Override
    public BigDecimal validarCalcular(Orcamento orcamento) {
            return orcamento.getValor().multiply(new BigDecimal("0.06"));
    }

    @Override
    public boolean checarRegra(Orcamento orcamento) {
        return orcamento.getQtdItens() > 5;
    }
}
```
### State Pattern
Outro cenário pode surgir como regra de negócio, a possibilidade de fornecer mais um desconto caso o cliente insista em obte-lo. Mas para este desconto o orçamento do cliente deve estar em um estado que permita avaliar qual percentual aplicar.
O padrão que será aplicado neste contexto é o **State**, que também é muito similar ao *Strategy*, mas o que difere é que para ser aplicada determinada condição será avaliado o estado em que encontra um determminado processo. Vamos ao exemplo:
```java
public class ValidadoraDeDescontosExtras {

    public BigDecimal aplicarDescontoExtra(Orcamento orcamento, Situacao situacao){
        return situacao.calcularValorDescontoExtra(orcamento);
    }
}
``` 
 Esse padrão é utilizado em três tipos de situações, são elas:
 * Existe uma transição de estado para outro;
 * É necessário aplicar alguma regra;
 * Execução de algum algoritmo baseado em um estado de um objeto.

 Este padrão só pode ser aplicado quando existe um processo de transição de um estado para outro, que é o que ocorre nesta regra de negócio, onde a regra é a seguinte:
 * Caso o orçamente esteja na situação "Em Análise" receberá 5% de desconto para efetivá-lo;
 * Caso o orçamento esteja na situação "Aprovado" receberá 2% de descontro para efetivá-lo;
 * Caso o orçamento esteja nas situações "Reprovado" ou "Finalizado" não terá desconto;

 Desta forma, foi criada a classe Situação onde ela será abstrata, desta maneira, suas situações "filhas" terão suas caracteristicas, mas seus comportamento poderão ser diferente, entrando neste contexto o polimorfísmo e a sobreescrita de métodos que definirá o comportamento de cada estado.
 ```java
public abstract class Situacao {

    public BigDecimal calcularValorDescontoExtra(Orcamento orcamento){
        return BigDecimal.ZERO;
    }

    public void aprovar(Orcamento orcamento){
        throw new ErrorException("Orcamento nao pode ser aprovado!");
    }

    public void reprovar(Orcamento orcamento){
        throw new ErrorException("Orcamento nao pode ser reprovado!");
    }

    public void finalizar(Orcamento orcamento){
        throw new ErrorException("Orcamento nao pode ser finalizado!");
    }
}
 ```

As classes filhas herdaram as características da *classe Pai* (Situacao), mas poderão alterar suas características comportamentais conforme sua própria caracteristica interna para representar um determinado estado.
```java
public class EmAnalise extends Situacao {
    @Override
    public BigDecimal calcularValorDescontoExtra(Orcamento orcamento){
        return orcamento.getValor().multiply(new BigDecimal("0.05"));
    }
    @Override
    public void aprovar(Orcamento orcamento){
        orcamento.setSituacao(new Aprovado());
    }

    @Override
    public void reprovar(Orcamento orcamento) {
        orcamento.setSituacao(new Reprovado());
    }
}
```
>  Em Análise - Será aplicado o cálculo em cima do valor total do orçamento e ele pode ser remetido a aprovação;

```java
public class Aprovado extends Situacao {
    @Override
    public BigDecimal calcularValorDescontoExtra(Orcamento orcamento){
        return orcamento.getValor().multiply(new BigDecimal("0.02"));
    }

    @Override
    public void finalizar(Orcamento orcamento) {
        orcamento.setSituacao(new Finalizado());
    }
}
```
>  Aprovado - Será aplicado o cálculo em cima do valor total do orçamento e ele pode ser remetido a conclusão;

```java
public class Reprovado extends Situacao{

    @Override
    public void finalizar(Orcamento orcamento) {
        orcamento.setSituacao(new Finalizado());
    }
}
```
>  Em Análise - Será aplicado o cálculo em cima do valor total do orçamento e remetido a aprovação;

Deste modo, é possível que um objeto [Orcamento] se comporte de forma diferente dependendo seu estado [Situacao], onde a mudança de estado determinará a ação a ser tomada. Este é o conceito utilizado pelo *State*.

### Command Pattern
O conceito do **Command** se refere quando há um comando a ser executado pela estrutura de classes que será aplicado a outra aplicação, como salvar os dados no Banco de Dados, enviar um pedido por e-mail, encaminhar os dados para um webservice, entre outros. Desta forma, este padrão molda como deve ser a interface para a execução de comandos.
Seguindo o exemplo, utilizaremos a criação de um pedido para este orçamento, na qual ele deverá ser enviado por e-mail e ser salvo no Banco de Dados, desta maneira, utilizaremos o conceito para criar a estrutura que realize este comando.
```java
public class CriadorDePedido {

    /* Injeção de Dependência de serviço de Banco de Dados */
    /* Injeção de Dependência de serviço de Email */
    
    public static Pedido gerarPedido(Cliente cliente, Orcamento orcamento){
        Pedido pedido = new Pedido(cliente, LocalDateTime.now(), orcamento);
        executarEnvio(pedido);
        executarCriacaoNoBD(pedido);
        return pedido;
    }

    private static void executarEnvio(Pedido pedido){
        /* Invocar o objeto injetado para chamar a função responsável por enviar email */
        EnviarEmail enviarEmail = new EnviarEmail();
        enviarEmail.executar(pedido, CommandType.EMAIL);
    }
    private static void executarCriacaoNoBD(Pedido pedido){
        /* Invocar o objeto injetado para chamar a função responsável por salvar */
        CRUD crud = new CRUD();
        crud.executar(pedido, CommandType.CREATED);
    }
}
```

A ideia por tras deste conceito é desvincular os comandos que serão executados por outras dependências a lógica que trata os dados obtidos pela a aplicação, desta forma, é abstraído 
Um Command Handler tem como responsabilidade, normalmente, apenas orquestrar as tarefas a serem executadas, ou seja, chamar as classes necessárias que realizam as tarefas desejadas.

### Observer Pattern
Observe que a cada nova ação de comando que for necessário utilizarmos a classe terá que realizar esta injeção e também criar um novo método para executar esta ação. Isto forçaria a nossa classe crescer infinitamente.
O padrão **Observer** é comumente utilizado por diversas bibliotecas que trabalham com eventos. Muitas tecnologias em Java, como o Spring e o CDI, possuem componentes que nos auxiliam a trabalhar com eventos.
Os benefícios em separarmos cada ação em classes distintas para tratativa de cada evento são:
* Se em algum momento uma das tarefas parar de funcionar, nós sabemos que há uma classe específica para este propósito e podemos começar a depuração por ela;
* Imagine que a ferramenta utilizada para enviar e-mails mude depois de alguns anos. O nosso Command Handler não precisa saber deste detalhe específico, então é interessante que cada classe seja responsável apenas por uma pequena tarefa.
Então fazendo esta tratativa o código ficará da seguinte forma:
```java
public class CriadorDePedido {

    private List<CommandHandler> commandList;

    public CriadorDePedido(List<CommandHandler> list){
        this.commandList = list;
    }

    public void gerarPedido(Cliente cliente, Orcamento orcamento, CommandType type){
        Pedido pedido = new Pedido(cliente, LocalDateTime.now(), orcamento);
        commandList.forEach(command -> {
            command.executar(pedido, type);
        });

    }
}
```
Agora as dependencias são tratadas em classes que execução ações especificas com suas respectivas injeções.
```java
public class CRUD implements CommandHandler {

    /* Injeção de Dependência de serviço de Banco de Dados */

    public void executar(Pedido pedido, CommandType type){
        switch (type){
            case CREATED:
                executeSave(pedido);
                break;
            case READ:
                executeRead(pedido);
                break;
            case UPDATED:
                executeUpdate(pedido);
                break;
            case DELETE:
                executeDelete(pedido);
                break;
            default:
                throw new ErrorException("Não foi informado o comando correto!");
        }
    }

    private void executeSave(Pedido pedido){
        System.out.println("Salvar pedido");
    }

    private void executeRead(Pedido pedido){
        System.out.println("Consultar pedido");
    }

    private void executeUpdate(Pedido pedido){
        System.out.println("Atualizar pedido");
    }

    private void executeDelete(Pedido pedido){
        System.out.println("Deletar pedido");
    }
}
```
> Classe responsável por executar tratativas ao banco de dados.

```java
public class EnviarEmail implements CommandHandler{

    /* Injeção de Dependência de serviço de Email */

    @Override
    public void executar(Pedido pedido, CommandType type) {
        if(type.equals(CommandType.CREATED) || type.equals(CommandType.DELETE)) {
            executarEnvio(pedido);
        }
    }

    private void executarEnvio(Pedido pedido){
        System.out.println("Enviar email com os dados do pedido: " + pedido.toString());
    }
}
```
> Classe responsável por encaminhar e-mail sobre o processo realizado no banco de dados


Como vimos, classes podem possuir dependências para realizar suas tarefas e a alternativa foi utilizar de abstrações para separar as responsabilidades a serem executadas. Inclusive, esse é um dos princípio de SOLID (Dependency Inversion Principle, a letra D). Devemos sempre preferir depender de abstrações, ou seja, interfaces ou classes abstratas, sempre que possível, ao invés de implementações específicas.


### Adapter Pattern
Quando precisamos utilizar código legado ou código de componentes externos em nosso sistema, é muito comum não existir uma interface (métodos públicos) batendo com o que a gente precisa, então nesses casos nós criamos esta adaptação para não misturarmos com o que estamos desenvolvendo e também, caso haja a necessidade, realizar a substituições destes componentes sem o risco de afetar nossa aplicação. Desta forma, o padrão adapter nos permite estruturar classes que funcionaram como intermediadores entre nossa aplicação e componentes externos.

```java
public interface HttpAdapter {
    void post(String url, Map<String, Object> dados);
}
```
Seguindo o exemplo para adaptar um componente externo, eu implementei um componente que será resposável por tratar as requisições **HTTP** chamado *HttpAdapter*, onde caso seja necessário é só alterar a biblioteca utilizada para realizar a comunicação.

Já a implementação da classe responsável por realizar a conexão e tratar a chamada externa foi nomeada como *RegistroDeOrcamento*, na qual encaminhará os dados para o webservice parceiro.
```java
public class JavaHttpClient implements HttpAdapter {
    @Override
    public void post(String url, Map<String, Object> dados) {
        try {
            URL urlApi = new URL(url);
            URLConnection connection = urlApi.openConnection();
            connection.connect();;
        } catch (IOException e) {
            throw new RuntimeException("Erro ao enviar requisição ao webservice parceiro!");
        }
    }
}
```

Agora só é preciso passar o adaptador a classe que realiza a tratativa com o componente externo realizando as tratativas necessárias para o tipo de requisição que está sendo chamada.

```java
public class RegistroDeOrcamento {

    private HttpAdapter http;

    public RegistroDeOrcamento(HttpAdapter http) {
        this.http = http;
    }

    public void registrar(Orcamento orcamento){
        verificaStatus(orcamento);
        
        String url = "http://api.externa.orcamento/";
        Map<String, Object> dados = Map.of(
                "valor", orcamento.getValor(),
                "quantidadeItens", orcamento.getQtdItens()
        );

        http.post(url, dados);
    }
}
```

Desta maneira, podemos conectar qualquer tipo de componente sem necessariamente incorpora-lo ao código da aplicação, onde caso seja necessário é só substitui-lo ou modifica-lo sem o risco de quebrar a aplicação por haver qualquer tipo de dependência. Podendo estes componentes serem de Banco de Dados, bibliotecas REST, HTTP ou qualquer outro.

### Decoration Pattern
Quando precisamos informar mais de uma vez uma regra de negócio, onde precisamos realizar alguma operação com ambas ou mais regras do mesmo tipo podemos compor estas regras passando-as uma embutida na outra. Como se tivessemos a decorando com outra regra.
Desta forma, ao invés de termos uma interface que orienta os padrões que as regras devem seguir, implementamos uma classe abstrata na qual realizaremos algumas implementações para realizar a operação necessária com os impostos passados para cálculo. Então, vamos a implementação.
```java

```



## Agradecimentos
Obrigado por ter acompanhado aos meus esforços em aplicar os conceitos do Design Patterns ao Projeto :octocat:

Como diria um velho mestre:
> *"Cedo ou tarde, você vai aprender, assim como eu aprendi, que existe uma diferença entre CONHECER o caminho e TRILHAR o caminho."*
>
> *Morpheus - The Matrix*