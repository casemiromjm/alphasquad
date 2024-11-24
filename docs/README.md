# LDTS 24/25 - Projeto

Neste projeto, propomos criar um jogo inspirado no [Armoured Commander](https://store.steampowered.com/app/1361580/Armoured_Commander/), com elementos adicionais retirados do sistema de combate do [XCOM Enemy Unknown](https://store.steampowered.com/app/200510/XCOM_Enemy_Unknown/).

O objetivo do jogo é proporcionar um confronto PvE entre dois esquadrões, onde o jogador poderá recolher *power-ups* para melhorar a sua performance e facilitar o combate. O jogo termina com a vitória do esquadrão ou com a morte do jogador, apresentando uma mensagem de *game over*.

---

## Autores do Projeto

O projeto foi desenvolvido pelos estudantes:
- Casemiro Medeiros (up202301897)
- Guilherme Ferreira (up202207524)
- Hugo Alves (up202305395)

---

## Lista de *Features* Planeadas

### Feature 1: Menu Inicial
Ao iniciar o jogo, o utilizador será apresentado a um menu inicial com três opções principais:
- **Play**: Dá início ao jogo.
- **Help**: Fornece informações úteis sobre as mecânicas, regras e objetivos do jogo.
- **Quit**: Permite sair do jogo.

---

### Feature 2: Estratégia e Movimento
Durante o jogo, o jogador poderá organizar o seu esquadrão, posicionando as unidades estrategicamente para:
- Atacar inimigos de forma eficaz.
- Utilizar objetos no cenário como cobertura, protegendo-se dos ataques adversários.

---

### Feature 3: Combate por Turnos
O jogo será jogado em turnos, com foco na tomada de decisões táticas sobre:
- A posição das unidades no campo de batalha.
- O momento e a estratégia de ataque.

---

### Feature 4: Sistema de *Power-ups*
No decorrer do jogo, o jogador poderá adquirir *power-ups*, que:
- Melhoram o nível do esquadrão.
- Aumentam as habilidades das unidades, tornando-as mais eficazes em combate.

---
### Feature 5: Progressão por Níveis

O jogo contará com um sistema de progressão através de três níveis distintos, aumentando gradualmente o desafio e a complexidade:

#### Nível 1
- Introduz os conceitos básicos de movimentação, ataque e uso de *power-ups*.
- O mapa é relativamente simples, com poucos obstáculos e inimigos menos agressivos.
- Serve como uma introdução para o jogador se familiarizar com as mecânicas.

#### Nível 2
- Aumenta a complexidade do mapa, introduzindo mais obstáculos e áreas estratégicas.
- O número e a força dos inimigos aumentam, exigindo maior planeamento e uso eficiente de coberturas.
- Novos tipos de inimigos podem ser introduzidos, forçando o jogador a adaptar a sua estratégia.

#### Nível 3
- O nível final apresenta o mapa mais desafiador, com um design mais intrincado e inimigos altamente agressivos.
- Os recursos (como *power-ups* ou itens de cura) tornam-se mais escassos, exigindo decisões precisas.
- Este nível culmina num confronto decisivo, testando ao máximo as habilidades do jogador e do seu esquadrão.


## Mockups

![mockup do projeto](src do png do mockup)

---

## Design

### Padrão MVC
O projeto implementa inicialmente a separação em **Model - View - Controller**.

---

### *Patterns* Utilizados
- MVC (Model-View-Controller)

---

### Problemas nos *Patterns* Utilizados
#### Problema 1 - (MVC)
Apesar de simplificar a organização das funcionalidades do projeto, também resulta, simultaneamente num elevado
número de classes e dependências o que, na criação de testes, dificulta o processo.

---

### Diagrama UML da Implementação Atual
![UML atual](uml.png)
- Algumas ligações são temporárias para facilitar a visualização do projeto.