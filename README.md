[![Review Assignment Due Date](https://classroom.github.com/assets/deadline-readme-button-22041afd0340ce965d47ae6ef1cefeee28c7c493a6346c4f15d667ab976d596c.svg)](https://classroom.github.com/a/BXgjckY6)
[![Open in Visual Studio Code](https://classroom.github.com/assets/open-in-vscode-2e0aaae1b6195c2367325f4f02e2d04e9abb55f0b24a779b69b11b9e10269abc.svg)](https://classroom.github.com/online_ide?assignment_repo_id=19660088&assignment_repo_type=AssignmentRepo)
# Raposas e Coelhos v3

Este projeto é a versão final do simulador de Raposas e Coelhos, com as alterações da aula
teórica ([slides aqui](https://ufla-ipoo.github.io/ipoo-slides/cap10-1.html)).

O projeto faz parte do material do capítulo 12 do livro

```
   Objects First with Java - A Practical Introduction using BlueJ
   Sixth edition
   David J. Barnes and Michael Kölling
   Pearson Education, 2016
```

Os exercícios abaixo têm o objetivo de praticar os conceitos de **Classes Abstratas e Interfaces**.

## Orientações Gerais

- Você deve fazer um passo de cada vez, testá-lo, fazer o commit e enviar suas alterações.
Somente depois disso é que você deve passar para o próximo passo.

- **ATENÇÃO**: **desligue o GitHub Copilot para fazer o exercício!**
  - Se você utilizá-lo você não estará realmente exercitando os conceitos aprendidos e
    não terá o domínio adequado para desenvolver as habilidades necessárias para se tornar
	um bom programador/desenvolvedor.
  - Sem contar ainda a questão do plágio.
  - Lembre-se que você pode (e deve) consultar os materiais da disciplina para fazer o exercício.

- Esse arquivo README pode ser melhor visualizado no VS Code (com formatação adequada) 
  abrindo-o no modo de visualização. Para isso, basta apertar Ctrl+Sfhit+V com ele aberto.

## Passo 1 - Nova forma de visualização

Revise o código da classe `Simulador`. 
Encontre todas as ocorrências das classes e interfaces de visualização (aquelas cujos nomes começam com `Visao`) 
e rastreie todas as variáveis ​​declaradas usando qualquer um desses tipos.
A ideia é que você tente entender exatamente como as visualizações são usadas na
classe `Simulador`.

Em seguida, implemente uma nova classe `VisaoDeTexto` que implemente a interface
`VisaoSimulador`. 
- A classe criada deve fornecer uma visualização textual da simulação.
- A cada passo da simulação ela deve imprimir uma linha no terminal como o exemplo abaixo:
   
   ```Passo N - Raposas: 121 Coelhos: 266 ```

- Dica: você precisará usar um objeto da classe `EstatisticasCampo`.
  - Avalie quais métodos da classe são mais úteis para obter as informações necessárias.

Em seguida, altere a classe `Simulador` para que ela inclua a nova visualização.

Teste a sua implementação para garantir que a nova visualização está funcionando corretamente.

## Passo 2 - Aumentando o nível de abstração

Nossa simulação permite simular animais, mas uma simulação mais completa poderia incluir coisas
que não são animais, como plantas, caçadores ou informações sobre clima, por exemplo.

Para tratar isso, seria bom aumentar o nível de abstração do nosso modelo, criando uma classe
`Ator` que pudesse representar uma gama maior de entidades que podem interagir com a simulação.

Neste passo você deve criar uma interface `Ator` que tenha os métodos `agir` e `estaAtivo` e 
fazer as alterações necessárias na classe `Animal` para implementar essa interface.

- O método `agir` deve receber uma lista de atores por parâmetro e não deve retornar nada.
- Já o método `estaAtivo` deve retornar um booleano indicando se o ator está ativo ou não.
  - Obs.: estamos usando _ativo_ em vez de vivo porque poderia existir uma subclasse que não seja um ser vivo.

Neste passo, ainda não vamos usar a interface `Ator` na classe `Simulador` (e, portanto, ela ainda não vai compilar).
- Basta que a classe `Animal` esteja implementando a interface e ela e suas subclasses estejam compilando corretamente.

## Passo 3 - Usando atores na simulação

Neste passo você deve alterar a classe `Simulador` para que ela use a interface `Ator` em vez de usar diretamente a classe `Animal`.
- Lembre-se de adequar os nomes para que eles façam sentido com a nova interface.

Você deverá alterar também a classe `GeradorDePopulacoes`.

Após as alterações a simulação deve voltar funcionando normalmente.

## Passo 4 - Expandindo a simulação: caçadores

Aproveitando que a interface `Ator` permite expandir a forma de uso do simulador, vamos criar uma
nova classe `Cacador` para representar caçadores na simulação.

Essa nova classe não vai herdar de `Animal` porque ela tem características bem distintas dos animais.

- Os caçadores não têm idade máxima e não se alimentam nem se reproduzem. 
- A cada passo da simulação, um caçador se move para um local aleatório livre em qualquer lugar do campo 
  - e dispara um número fixo de tiros em alvos aleatórios ao redor do campo. 
  - Qualquer animal em um dos alvos é morto.

Antes de implementar, pense na modelagem da classe `Cacador`.

- Quais atributos (de instância e/ou estáticos) são necessários?
- Como deve ser a implementação do método `agir`?
- Como deve ser a implementação do método `estaAtivo`?
- É necessária a implementação de algum outro método?
- A nova classe geraria replicação de código com alguma outra classe já existente? 
  Em caso afirmativo, faça a refatoração necessária para evitar essa replicação.

Por fim, altere a classe `GeradorDePopulacoes` para que os caçadores sejam incluídos na simulação.

- Você deve colocar apenas um pequeno número de caçadores no campo no início da simulação.
- Lembre-se de definir uma cor para os caçadores no objeto que trata a visão de grade.
- Obs.: não é necessário tratar a visão de gráfico.

## Passo 5 - Melhorando a implementação dos caçadores

Faça experimentos com a simulação para encontrar bons valores
para os atributos dos caçadores.

As perguntas abaixo não precisam ser respondidas aqui, mas te ajudam a verificar se a implementação está adequada.

- Os caçadores permanecem na simulação durante todo o tempo ou desaparecem em algum momento?
- Se desaparecerem, por que isso ocorre e isso representa um
comportamento realista? Se não for, altere a simulação.
- Alguma outra classe precisará ser alterada como resultado da introdução dos caçadores?

## (Opcional) Passo 6 - Incrementando o uso do polimorfismo

Repare que a classe `Campo` utiliza a classe `Object` para guardar o que 
existe em cada localização do campo.

Mas agora que temos uma interface `Ator`, como ela representa qualquer coisa que pode estar no campo da simulação, poderíamos usá-la em vez de `Object`

- Isso tornaria o código mais claro, fácil de entender e ainda aumentaria as possibilidades de uso do polimorfismo.

Altere então a classe `Campo` para que ela utilize a interface `Ator` em vez de `Object`.

Teste a sua simulação antes de enviar suas alterações.

## (Opcional) Passo 7 - Diminuindo o acoplamento entre as classes

A classe `Raposa` possui acoplamento com a classe `Coelho` porque
ela precisa saber se um ator obtido do campo é um coelho para poder comê-lo.

Pensando que a simulação possa ter realmente apenas raposas e coelhos,
como esse acoplamento poderia ser removido?

- Dica: pense em como o polimorfismo e a inclusão de algum(ns) método(s) na interface `Ator` poderia ajudar a resolver essa questão.

Faça as alterações necessárias e teste sua implementação.

## (Opcional) Passo 8 - Flexibilizando a simulação

No passo anterior, provavelmente, você conseguiu uma solução que removeu
o acoplamento entre as classes `Raposa` e `Coelho`, mas que ainda não é flexível o suficiente.

- Imagine, por exemplo, se a simulação tivesse outros pares de predadores e presas, como
  leões e zebras, ou tubarões e peixes.

Uma maneira de tratar essa flexibilidade seria delegar a responsabilidade de
decidir se um ator é uma presa de um determinado predador (ou não) para uma outra classe.

Essa classe poderia ser responsável por registrar as relações entre predadores e presas.
- Isso deixaria o restante do sistema desacoplado dessas relações, permitindo que
  novos predadores e presas fossem adicionados sem a necessidade de alterar o código existente (exceto a própria classe que trata isso).

Altere então o seu projeto para usar a classe `RelacoesPredadorPresa` abaixo. 

```java
import java.util.HashMap;
import java.util.Map;

public class RelacoesPredadorPresa {
    // Guarda pares de classes no qual a chave é o predador e o valor é a presa
    private static final Map<Class<?>, Class<?>> predadorEPresa = new HashMap<>();

    // Registra relacoes predador-presa
    private static void registrarRelacoes() {
        predadorEPresa.put(Raposa.class, Coelho.class);
        predadorEPresa.put(Cacador.class, Coelho.class);
        predadorEPresa.put(Cacador.class, Raposa.class);
        // caso sejam criados outros predadores e presas, eles seriam criados aqui:
        // Exemplo: predadorEPresa.put(Leao.class, Zebra.class);
    }

    // Verifica se o predador consegue matar a presa
    public static boolean consegueMatar(Object predador, Object possivelPresa) {
        if (predador == null || possivelPresa == null) {
            return false; // Se algum dos objetos for nulo, não há relação
        }
        if (predadorEPresa.isEmpty()) {
            registrarRelacoes();
        }
        Class<?> presaEsperada = predadorEPresa.get(predador.getClass());
        return presaEsperada.isInstance(possivelPresa);
    }
}
```






