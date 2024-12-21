## LDTS_T01G06 - Alpha Squad

Neste projeto, propomos criar um jogo inspirado no [Armoured Commander](https://store.steampowered.com/app/1361580/Armoured_Commander/), com elementos adicionais retirados do sistema de combate do [XCOM Enemy Unknown](https://store.steampowered.com/app/200510/XCOM_Enemy_Unknown/). Por fim, também toma como inspiração [Dwarf Fortress](https://store.steampowered.com/app/975370/Dwarf_Fortress/).

O objetivo do jogo é proporcionar um confronto PvE entre dois esquadrões, em que o jogador poderá recolher *power-ups* para melhorar a sua performance e facilitar o combate. O jogo termina com a vitória do esquadrão ou com a morte do jogador, apresentando uma mensagem de *game over*.

O projeto foi desenvolvido por Casemiro Medeiros (up202301897@fe.up.pt), Guilherme Ferreira (up202207524@fe.up.pt) e Hugo Alves (up202305395@fe.up.pt) para LDTS 2024⁄25.

### *FEATURES* IMPLEMENTADAS

<!--- preciso pegar prints e gifs para add aqui --->

> This section should contain a list of implemented features and their descriptions. In the end of the section, include two or three screenshots that illustrate the most important features.

- **Menu inicial** - O jogo tem um menu inicial com opções de:
  - `Play` - Inicia o combate no nível 1
  - `Help` - Apresenta um ecrã que explica como o jogo funciona e algumas dicas
  - `Quit` - Fecha o jogo
- **Power-ups** - Implementamos alguns *power-ups* iniciais, sendo eles:
  - `Health (+)` - Aumenta o HP em 20 pontos
  - `Aim (o)` - Melhora a precisão em 10 pontos
  - `Damage (>)` - Aumenta o dano em 5 pontos
- **Blocos de obstáculos** - Implementamos alguns tipos de obstáculos, os quais são:
  - `Arbusto (B)` - Reduz a precisão do inimigo em 10 pontos
  - `Parede de Madeira Pequena (W)` - Reduz o dano recebido em 5 pontos e a precisão inimiga em 20 pontos
  - `Parede de Pedra Pequena (S)` - Reduz o dano recebido em 10 pontos e a precisão inimiga em 30 pontos
  
  > Você precisa estar adjacente a eles (8 blocos) e obstáculo precisa estar entre a origem e o alvo para surtir efeito. Flanquear é uma tática válida para negar os efeitos dos obstáculos.
- **Diferentes níveis** - Há mais inimigos em cada nível. Os aliados que se manterem vivos para o próximo nível, sofrem uma melhoria nos seus *stats*.
  - `Nvl 1` - 
  - `Nvl 2` - 
  - `Nvl 3` - 
- **Efeitos Sonoros** - Após o disparo, seja do aliado, seja do inimigo, sempre é seguido de um efeito sonoro de acerto ou de erro.



### *FEATURES* FUTURAS

Como em qualquer jogo, sempre existirão *features* possíveis para um *roadmap* que visa mantê-lo interessante. Entre elas, podemos citar:

- Modo PvP
  - Nesse modo, ao invés de ter um oponente controlado pela máquina, teríamos outro jogador localmente a traçar estratégias e a tomar decisões no confronto.

- Novos *power-ups*
  - Novos *power-ups* tornam o jogo mais dinâmico, uma vez que abre possibilidade para novas estratégias. Como exemplo destes novos *power-ups* temos:
    - Munição incendiária: adiciona o efeito *burn* em que o tanque atingido por essa munição sofre um dano extra por determinada quantidade de turnos.
    - Munição explosiva: adiciona a possibilidade de um ataque crítico que confere mais dano no tanque atingido.
    - Equipe de engenheiros: adiciona a capacidade de regenerar vida por determinada quantidade de turnos.

- Novas *features* no mapa
  - Atualizações no mapa dão mais variedades de estratégias e mais dinamicidade ao jogo. Como exemplo temos:
    - Blocos de água: o tanque não consegue atravessar estes blocos. (~~exceto se tenha um novo *power-up*, quem sabe...~~).
    - Blocos de lama: adicionam a possibilidade do movimento não ser bem-sucedido e o tanque nao se mover.
    - Minas terrestre: blocos que parecem normais, mas explodem ao serem atravessados por um tanque, causando dano significativo. Pode-se usar um novo *power-up* para contornar estes novos obstáculos.

### DESIGN

O design baseia-se em dois padrões. Primeiramente, utiliza a arquitetura MVC (Model - View - Controller), tornando o projeto mais modular. Contudo, com o avanço do desenvolvimento, foi percebida a necessidade de adotar outro padrão de desenvolvimento, o *State Pattern*.

> This section should be organized in different subsections, each describing a different design problem that you had to solve during the project. Each subsection should be organized in four different parts:

- **Problem in Context.** The description of the design context and the concrete problem that motivated the instantiation of the pattern. Someone else other than the original developer should be able to read and understand all the motivations for the decisions made. When refering to the implementation before the pattern was applied, don’t forget to [link to the relevant lines of code](https://help.github.com/en/articles/creating-a-permanent-link-to-a-code-snippet) in the appropriate version.
- **The Pattern.** Identify the design pattern to be applied, why it was selected and how it is a good fit considering the existing design context and the problem at hand.
- **Implementation.** Show how the pattern roles, operations and associations were mapped to the concrete design classes. Illustrate it with a UML class diagram, and refer to the corresponding source code with links to the relevant lines (these should be [relative links](https://help.github.com/en/articles/about-readmes#relative-links-and-image-paths-in-readme-files). When doing this, always point to the latest version of the code.
- **Consequences.** Benefits and liabilities of the design after the pattern instantiation, eventually comparing these consequences with those of alternative solutions.

**Example of one of such subsections**:

---

#### THE JUMP ACTION OF THE KANGAROOBOY SHOULD BEHAVE DIFFERENTLY DEPENDING ON ITS STATE

**Problema**

There was a lot of scattered conditional logic when deciding how the KangarooBoy should behave when jumping, as the jumps should be different depending on the items that came to his possession during the game (an helix will alow him to fly, driking a potion will allow him to jump double the height, etc.). This is a violation of the **Single Responsability Principle**. We could concentrate all the conditional logic in the same method to circumscribe the issue to that one method but the **Single Responsability Principle** would still be violated.

**Pattern escolhido**

Nós utilizamos o **State pattern**. Este padrão nos permite representar estados diversos com diferentes subclasses/funcionalidades. We can switch to a different state of the application by switching to another implementation (i.e., another subclass). This pattern allowed to address the identified problems because […].

**Implementação**

The following figure shows how the pattern’s roles were mapped to the application classes.

![img](https://www.fe.up.pt/~arestivo/page/img/examples/lpoo/state.svg)

These classes can be found in the following files:

- [Character](https://web.fe.up.pt/~arestivo/page/courses/2021/lpoo/template/src/main/java/Character.java)
- [JumpAbilityState](https://web.fe.up.pt/~arestivo/page/courses/2021/lpoo/template/src/main/java/JumpAbilityState.java)
- [DoubleJumpState](https://web.fe.up.pt/~arestivo/page/courses/2021/lpoo/template/src/main/java/DoubleJumpState.java)
- [HelicopterState](https://web.fe.up.pt/~arestivo/page/courses/2021/lpoo/template/src/main/java/HelicopterState.java)
- [IncreasedGravityState](https://web.fe.up.pt/~arestivo/page/courses/2021/lpoo/template/src/main/java/IncreasedGravityState.java)

**Consequences**

The use of the State Pattern in the current design allows the following benefits:

- The several states that represent the character’s hability to jump become explicit in the code, instead of relying on a series of flags.
- We don’t need to have a long set of conditional if or switch statements associated with the various states; instead, polimorphism is used to activate the right behavior.
- There are now more classes and instances to manage, but still in a reasonable number.

#### *CODE SMELLS* CONHECIDOS

##### *Buffer* de movimento do esquadrão aliado
Este pequeno *bug* acontece quando, antes da fase de movimento do esquadrão aliado, é pressionado alguma tecla de movimento. Após ser pressionada, ela é mantida em memória e, logo após entrar na fase de movimento, a personagem move-se nesta direção.

##### Code smell 2

##### Code smell 3

> This section should describe 3 to 5 different code smells that you have identified in your current implementation.

### TESTING (deixar por último)

- Screenshot of coverage report.
- Link to mutation testing report.

### AUTOAVALIAÇÃO

<!--- ter q mandar msg perguntando sobre isso aq --->

- Casemiro Medeiros:
- Guilherme Ferreira:
- Hugo Alves:
