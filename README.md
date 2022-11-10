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
    private Desconto proximoDesconto;

    public Desconto(Desconto desconto){
        this.proximoDesconto = desconto;
    }

    public abstract BigDecimal calcular(Orcamento orcamento);
}
```
Observe que existe um método abstrato, isso significa que toda classe que herdar de Desconto precisará implementar o método conforme sua necessidade.




## Agradecimentos
Obrigado por ter acompanhado aos meus esforços em aplicar os conceitos do Design Patterns ao Projeto :octocat:

Como diria um velho mestre:
> *"Cedo ou tarde, você vai aprender, assim como eu aprendi, que existe uma diferença entre CONHECER o caminho e TRILHAR o caminho."*
>
> *Morpheus - The Matrix*