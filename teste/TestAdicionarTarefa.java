package ToDoList.teste;

import ToDoList.Aplicacao;

import java.text.SimpleDateFormat;
import java.util.Date;

public class TestAdicionarTarefa {

    public static void main(String[] args) {
        // Given
        Aplicacao aplicacao = new Aplicacao();

        String nome = "Sample Task";
        String descricao = "This is a sample task.";
        String dataString = "28/08/2023";
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        Date dataTermino = null;
        try {
            dataTermino = dateFormat.parse(dataString);
        } catch (Exception e) {
            e.printStackTrace();
        }
        int prioridade = 3;
        String categoria = "Sample Category";
        String status = "ToDo";
        String observacoes = "Sample notes";

        // When
        aplicacao.simulateUserInput(nome, descricao, dataString, prioridade, categoria, status, observacoes);

        aplicacao.adicionarTarefa();

        // Then
        if (aplicacao.getTarefas().size() == 1) {
            System.out.println("Test Passed: Task adicionada com sucesso.");
        } else {
            System.out.println("Test Failed: Task não foi adicionada.");
        }
    }
}
