# ToDoList

O Aplicativo Lista de Tarefas é um programa de linha de comando que permite aos usuários gerenciar tarefas. 
Os usuários podem adicionar tarefas com vários detalhes, como nome, descrição, data de vencimento, prioridade, categoria e status. 
O programa oferece opções para listar tarefas por categoria, prioridade ou status.

O aplicativo utiliza três classes principais: Aplicacao, Tarefa e ArquivoUtil. 

A classe Aplicacao contém a lógica principal do programa, incluindo adição de tarefas e exibição de listas de tarefas. 

A classe Tarefa define a estrutura de uma tarefa e suas propriedades. 

A classe ArquivoUtil lida com o armazenamento e recuperação dos dados das tarefas usando entrada e saída de arquivo.


Requisitos Obrigatórios:

A tarefa deve ter pelo menos os parâmetros: 

- Nome;
- Descrição;
- Data de término;
- Nível de prioridade de 1 a 5;
- Categoria;
- Status (todo, doing e done).
- CRD de Tarefas;
- Disponibilize essas ações em menu simples no terminal.
- Deve ser possível listar as atividades por:
- Categoria;
- Prioridade;
- Status.

Requisitos opcionais: 

- Ser possível consultar o número de atividades que estão concluídas (Done), estão para fazer (ToDo) e estão sendo feitas (Doing);
- Ser possível Atualizar tarefa;
- Ter uma opção de filtragem por data na listagem;
- Seria um diferencial para o seu programa ter uma persistência. Não é interessante perder todas as atividades sempre que o programa é fechado, então pense em salvar os dados em algum arquivo (.csv). 

Explicação do Teste Unitário: Adicionando uma Nova Tarefa

O teste unitário TestAdicionarTarefa foi criado para testar a funcionalidade "Adicionar Tarefa" do Aplicativo Lista de Tarefas. O teste segue o padrão Dado-Quando-Então, que consiste em três fases:

  Given: Nesta fase, uma instância da classe Aplicacao é criada, simulando o ambiente do programa. Os detalhes de uma tarefa de exemplo são preparados para o teste.

  When: O teste simula a entrada do usuário chamando simulateUserInput para fornecer os detalhes da tarefa de exemplo. O método adicionarTarefa é então chamado para adicionar a tarefa.

  Then: O teste verifica se as propriedades da tarefa adicionada correspondem aos valores esperados. Se a tarefa foi adicionada com sucesso e suas propriedades correspondem, o teste é considerado bem-sucedido. Caso contrário, o teste falha.

Este teste unitário oferece uma validação básica de que a funcionalidade "Adicionar Tarefa" está funcionando corretamente. Ele verifica se uma tarefa é adicionada corretamente à lista com os detalhes fornecidos.
