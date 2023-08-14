package ToDoList;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

public class Aplicacao {
    private List<Tarefa> tarefas = new ArrayList<>();
    private Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        Aplicacao aplicacao = new Aplicacao();
        aplicacao.run();
    }

    public void run() {
        boolean continuar = true;
        System.out.println("To do List Programa");
        while (continuar) {
            System.out.println("\n ==== Menu ===== ");
            System.out.println("1. Adicionar Tarefa");
            System.out.println("2. Listar Tarefas por Categoria");
            System.out.println("3. Listar Tarefas por Prioridade");
            System.out.println("4. Listar Tarefas por Status");
            System.out.println("0. Sair");
            System.out.println("Escolha uma opção: ");
            int escolha = lerEscolha();
            switch (escolha) {
                case 1:
                    adicionarTarefa();
                    break;
                case 2:
                    listarTarefasPorCategoria();
                    break;
                case 3:
                    listarTarefasPorPrioridade();
                    break;
                case 4:
                    listarTarefasPorStatus();
                    break;
                case 0:
                    continuar = false;
                    break;
                default:
                    System.out.println("Escolha inválida, tente novamente.");
            }
        }
    }

    private int lerEscolha() {
        int escolha = scanner.nextInt();
        scanner.nextLine();
        return escolha;
    }

private void adicionarTarefa() {
    System.out.println("Nome da Tarefa: ");
    String nome = scanner.nextLine();

    System.out.println("Descrição: ");
    String descricao = scanner.nextLine();

    System.out.println("Data de Término (dd/mm/aaaa):");
    String dataString = scanner.nextLine();

    SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
    Date dataTermino = null;
    try {
        dataTermino = dateFormat.parse(dataString);
    } catch (ParseException e) {
        System.out.println("Formato de data inválido. Use dd/mm/aaaa");
        return;
    }

    System.out.println("Prioridade (1-5): ");
    int prioridade = scanner.nextInt();
    scanner.nextLine(); //Limpa o buffer

    if (prioridade < 1 || prioridade > 5) {
        System.out.println("Prioridade inválida, use um valor entre 1 e 5");
        return;
    }

    System.out.println("Categoria");
    String categoria = scanner.nextLine();

    System.out.println("Status(ToDo, Doing, Done): ");
    String status = scanner.nextLine();

    if (!status.equalsIgnoreCase("ToDo") && !status.equalsIgnoreCase("Doing") && !status.equalsIgnoreCase("Done")) {
        System.out.println("Status inválido, use ToDo, Doing ou Done");
        return;
    }

    System.out.println("Observações:");
    String observacoes = scanner.nextLine();

    Tarefa tarefa = new Tarefa(nome, descricao, dataTermino, prioridade, categoria, status);
    tarefas.add(tarefa);

    rebalancerPrioridade();
    ArquivoUtil.salvarTarefas(tarefas); // Salva as tarefas no arquivo
}
private void listarTarefasPorCategoria(){
    System.out.println("Digite a categoria: ");
    String categoria = scanner.nextLine();

    System.out.println("Tarefas na categoria '" + categoria +"':");
    for(Tarefa tarefa : tarefas){
        if (tarefa.getCategoria().equalsIgnoreCase(categoria)){
            System.out.println("Nome: " + tarefa.getNome() + ", Descrição: " + tarefa.getDescricao());
        }
    }
    }
private void listarTarefasPorPrioridade(){
    System.out.println("Digite a prioridade (1-5): ");
    int prioridade = scanner.nextInt();
    scanner.nextLine();

    System.out.println("Tarefas com prioridade" + prioridade + ":");
    for(Tarefa tarefa : tarefas){
        if(tarefa.getPrioridade() == prioridade){
            System.out.println("Nome: " + tarefa.getNome() + ", Descrição: " + tarefa.getDescricao());
        }
    }
}
private void listarTarefasPorStatus(){
    System.out.println("Digite o status (ToDo, Doing, Done): ");
    String status = scanner.nextLine();

    System.out.println("Tarefas com status '" + status + "':");
    for (Tarefa tarefa : tarefas) {
        if (tarefa.getStatus().equalsIgnoreCase(status)) {
            System.out.println("Nome: " + tarefa.getNome() + ", Descrição: " + tarefa.getDescricao());
        }
    }
}
private void rebalancerPrioridade(){
    tarefas.sort((t1, t2) -> t2.getPrioridade() - t1.getPrioridade());
    for (int i = 0; i < tarefas.size(); i++){
        tarefas.get(i).setPrioridade(i +1);
    }
  }
}

