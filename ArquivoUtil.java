package ToDoList;

import java.io.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ArquivoUtil {
    private static final String NOME_ARQUIVO = "tarefas.csv";

    public static void salvarTarefas(List<Tarefa> tarefas) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(NOME_ARQUIVO))) {
            for (Tarefa tarefa : tarefas) {
                writer.write(tarefa.getNome() + "," + tarefa.getDescricao() + ","
                        + tarefa.getDataTermino() + "," + tarefa.getPrioridade() + ","
                        + tarefa.getCategoria() + "," + tarefa.getStatus());
                writer.newLine();
            }
        } catch (IOException e) {
            System.out.println("Erro ao salvar tarefas: " + e.getMessage());
        }
    }

    public static List<Tarefa> carregarTarefas() {
        List<Tarefa> tarefas = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(NOME_ARQUIVO))) {
            String linha;
            while ((linha = reader.readLine()) != null) {
                String[] campos = linha.split(",");
                if (campos.length == 6) {
                    String nome = campos[0];
                    String descricao = campos[1];
                    Date dataTermino = new SimpleDateFormat("dd/MM/yyyy").parse(campos[2]);
                    int prioridade = Integer.parseInt(campos[3]);
                    String categoria = campos[4];
                    String status = campos[5];
                    Tarefa tarefa = new Tarefa(nome, descricao, dataTermino, prioridade, categoria, status);
                    tarefas.add(tarefa);
                } else {
                    System.out.println("Formato inválido da linha no arquivo: " + linha);
                }
            }
        } catch (IOException | ParseException e) {
            System.out.println("Erro ao carregar tarefas: " + e.getMessage());
        }
        return tarefas;
    }
}
