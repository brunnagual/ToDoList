package ToDoList;

import ToDoList.Utils.ArquivoUtil;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.awt.Toolkit;
import javax.swing.JOptionPane;

public class Aplicacao {
    private List<Tarefa> tarefas = new ArrayList<>();
    private Scanner scanner = new Scanner(System.in);

    public Aplicacao() {

    }

    public static void main(String[] args) {
        Aplicacao aplicacao = new Aplicacao();
        aplicacao.run();
    }

    public void run() {
        
        tarefas = ArquivoUtil.carregarTarefas();
        boolean continuar = true;
        
        System.out.println("Programa To do List");
        while (continuar) {
            System.out.println("\n ==== Menu ===== ");
            System.out.println("1. Adicionar Tarefa");
            System.out.println("2. Listar Tarefas por Categoria");
            System.out.println("3. Listar Tarefas por Prioridade");
            System.out.println("4. Listar Tarefas por Status");
            System.out.println("5. Deletar tarefa");
            System.out.println("6. Adicionar Alarme");
            System.out.println("0. Sair");
            System.out.println("\nEscolha uma opção: ");
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
                case 5:
                    deletarTarefa();
                    break;
                case 6:
                    definirAlarmeParaTarefa();
                    break;
                case 0:
                    continuar = false;
                    break;
                default:
                    System.out.println("Escolha inválida, tente novamente.");
            }
            verificarAlarmes(tarefas);
        }
    }

    private int lerEscolha() {
        int escolha = scanner.nextInt();
        scanner.nextLine();
        return escolha;
    }

public void adicionarTarefa() {
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
        System.out.println("Formato de data inválido: " + dataString);
        return;
    }

    System.out.println("Prioridade (1-5): ");
    int prioridade = scanner.nextInt();
    scanner.nextLine(); // Limpa o buffer

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

    Date dataAlarme = null;
    Tarefa tarefa = new Tarefa(nome, descricao, dataTermino, prioridade, categoria, status, dataAlarme);
    tarefas.add(tarefa);

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

    public void deletarTarefa() {
        System.out.println("Digite o nome da tarefa que deseja deletar: ");
        String nomeTarefa = scanner.nextLine();
        Tarefa tarefaParaDeletar = null;
        for (Tarefa tarefa : tarefas) {
            if (tarefa.getNome().equalsIgnoreCase(nomeTarefa)) {
                tarefaParaDeletar = tarefa;
                break;
            }
        }
        if (tarefaParaDeletar != null) {
            tarefas.remove(tarefaParaDeletar);
            System.out.println("Tarefa '" + nomeTarefa + "' deletada com sucesso.");
            ArquivoUtil.salvarTarefas(tarefas); // Salve as tarefas atualizadas no arquivo.
        } else {
            System.out.println("Tarefa '" + nomeTarefa + "' não encontrada.");
        }
    }

    private void definirAlarmeParaTarefa() {
        System.out.println("Digite o nome da tarefa para a qual deseja definir um alarme: ");
        String nomeTarefaAlarme = scanner.nextLine();

        Tarefa tarefaParaAlarme = null;
        for (Tarefa tarefa : tarefas) {
            if (tarefa.getNome().equalsIgnoreCase(nomeTarefaAlarme)) {
                tarefaParaAlarme = tarefa;
                break;
            }
        }

        if (tarefaParaAlarme != null) {
            System.out.println("Data de Alarme (dd/MM/yyyy): ");
            String dataAlarmeString = scanner.nextLine();

            SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
            Date dataAlarme = null;
            try {
                dataAlarme = dateFormat.parse(dataAlarmeString);
            } catch (ParseException e) {
                System.out.println("Formato de data de alarme inválido: " + dataAlarmeString);
                return;
            }

            tarefaParaAlarme.setDataAlarme(dataAlarme);
            System.out.println("Alarme definido com sucesso para a tarefa '" + tarefaParaAlarme.getNome() + "'.");
            ArquivoUtil.salvarTarefas(tarefas); // Salve as tarefas atualizadas no arquivo.
        } else {
            System.out.println("Tarefa '" + nomeTarefaAlarme + "' não encontrada.");
        }
    }

    public static void verificarAlarmes(List<Tarefa> tarefas) {
        Date currentDate = new Date();

        for (Tarefa task : tarefas) {
            Date alarmeDate = task.getDataAlarme();
            if (alarmeDate != null && alarmeDate.compareTo(currentDate) <= 0) {
                System.out.println("ALERTA: A tarefa '" + task.getNome() + "' está vencendo ou já venceu!");

                // Exibir uma mensagem pop-up
                JOptionPane.showMessageDialog(null, "ALERTA: A tarefa '" + task.getNome() + "' está vencendo ou já venceu!");

                // Emitir um alerta sonoro
                Toolkit.getDefaultToolkit().beep();
            }
        }
    }

    public void simulateUserInput(String nome, String descricao, String dataString, int prioridade, String categoria, String status, String observacoes) {
    }

    public List<Tarefa> getTarefas() {
        return tarefas;
    }


}

