document.addEventListener("DOMContentLoaded", function () {
    const taskForm = document.getElementById("task-form");
    const taskList = document.getElementById("task-list");
    const statusFilter = document.getElementById("status-filter");

    statusFilter.addEventListener("change", function () {
        const selectedStatus = statusFilter.value;
        filterTasks(selectedStatus);
    });

    let editingTaskIndex = -1; // Inicialmente, nenhum índice de tarefa está em edição.

    // Array para armazenar as tarefas
    let tasks = [];

    // Função para renderizar a lista de tarefas
    function renderTasks() {
        taskList.innerHTML = "";
        tasks.forEach(function (task, index) {
            const taskItem = document.createElement("div");
            taskItem.classList.add("task");
            taskItem.innerHTML = `
                <input type="checkbox" data-index="${index}" class="task-checkbox">
                <strong>${task.name}</strong>
                <p>${task.description}</p>
                <p>Data de Término: ${task.date}</p>
                <p>Prioridade: ${task.priority}</p>
                <p>Categoria: ${task.category}</p>
                <p>Status: ${task.status}</p>
            <button data-index="${index}" class="delete-button">Excluir</button>
            <button class="edit-button" data-index="${index}">Editar</button>
            <button data-index="${index}" class="move-button move-forward">Avançar</button>
            <button data-index="${index}" class="move-button move-backward">Retroceder</button>
            `;
            taskList.appendChild(taskItem);
        });
    }

    // Função para adicionar uma nova tarefa
    function addTask(name, description, date, priority, category, status) {
        tasks.push({
            name,
            description,
            date,
            priority,
            category,
            status
        });
        renderTasks();
    }

    // Função para excluir uma tarefa
    function deleteTask(index) {
        tasks.splice(index, 1);
        renderTasks();
    }

    // Evento para adicionar tarefa quando o formulário é enviado
    taskForm.addEventListener("submit", function (e) {
        e.preventDefault();
        const taskName = document.getElementById("task-name").value;
        const taskDescription = document.getElementById("task-description").value;
        const taskDate = document.getElementById("task-date").value;
        const taskPriority = document.getElementById("task-priority").value;
        const taskCategory = document.getElementById("task-category").value;
        const taskStatus = document.getElementById("task-status").value;

        if (taskName && taskDate && taskPriority && taskStatus) {
            if (editingTaskIndex !== -1) {
                // Atualize a tarefa em vez de adicionar uma nova
                tasks[editingTaskIndex] = {
                    name: taskName,
                    description: taskDescription,
                    date: taskDate,
                    priority: taskPriority,
                    category: taskCategory,
                    status: taskStatus
                };
                editingTaskIndex = -1; // Saia do modo de edição
            } else {
                // Adicione uma nova tarefa
                addTask(taskName, taskDescription, taskDate, taskPriority, taskCategory, taskStatus);
            }

            renderTasks();
            taskForm.reset();
        } else {
            alert("Preencha todos os campos obrigatórios.");
        }
    });

    // Evento para excluir tarefa quando o botão "Excluir" é clicado
    taskList.addEventListener("click", function (e) {
        if (e.target.classList.contains("edit-button")) {
            const index = e.target.getAttribute("data-index");
            if (index !== null) {
                // Preencha o formulário com os detalhes da tarefa para edição
                const task = tasks[index];
                document.getElementById("task-name").value = task.name;
                document.getElementById("task-description").value = task.description;
                document.getElementById("task-date").value = task.date;
                document.getElementById("task-priority").value = task.priority;
                document.getElementById("task-category").value = task.category;
                document.getElementById("task-status").value = task.status;

                // Defina a tarefa em edição
                editingTaskIndex = index;
            }
        }
    });

    taskList.addEventListener("click", function (e) {
        if (e.target.classList.contains("delete-button")) {
            const index = e.target.getAttribute("data-index");
            if (index !== null) {
                deleteTask(index);
            }
        }
    });

    function filterTasks(selectedStatus) {
        tasks.forEach(function (task, index) {
            const taskItem = taskList.childNodes[index];
            if (selectedStatus === "all" || task.status === selectedStatus) {
                taskItem.style.display = "block"; // Mostrar tarefa
            } else {
                taskItem.style.display = "none"; // Ocultar tarefa
            }
        });
    }
// Função para salvar tarefas no Local Storage
    function salvarTarefas(tarefas) {
        localStorage.setItem('tarefas', JSON.stringify(tarefas));
    }

// Função para carregar tarefas do Local Storage
    function carregarTarefas() {
        const tarefas = JSON.parse(localStorage.getItem('tarefas')) || [];
        return tarefas;
    }

// Função para adicionar uma nova tarefa
    function adicionarTarefa(tarefa) {
        const tarefas = carregarTarefas();
        tarefas.push(tarefa);
        salvarTarefas(tarefas);
    }

// Função para atualizar o status de uma tarefa por ID
    function atualizarStatusTarefa(id, novoStatus) {
        const tarefas = carregarTarefas();
        const tarefaIndex = tarefas.findIndex(tarefa => tarefa.id === id);

        if (tarefaIndex !== -1) {
            tarefas[tarefaIndex].status = novoStatus;
            salvarTarefas(tarefas);
        }
    }

    // Função para carregar e exibir as tarefas
    function carregarETornarListaDeTarefas() {
        const listaTarefas = document.getElementById('lista-tarefas');
        listaTarefas.innerHTML = ''; // Limpa a lista

        const tarefas = carregarTarefas();

        tarefas.forEach(tarefa => {
            const listItem = document.createElement('li');
            listItem.innerHTML = `
            <input type="checkbox" data-id="${tarefa.id}">
            ${tarefa.nome} - Status: ${tarefa.status}
        `;
            listaTarefas.appendChild(listItem);
        });
    }

    // Função para excluir uma tarefa
    function deleteTask(index) {
        tasks.splice(index, 1);
        renderTasks();
    }

    // Evento para avançar status
    taskList.addEventListener("click", function (e) {
        if (e.target.classList.contains("move-forward")) {
            const index = e.target.getAttribute("data-index");
            if (index !== null) {
                // Verifique se o status atual permite avançar
                const currentIndex = tasks[index].status;
                const nextStatus = getNextStatus(currentIndex);
                if (nextStatus) {
                    tasks[index].status = nextStatus;
                    renderTasks();
                }
            }
        }
    });

// Evento para retroceder status
    taskList.addEventListener("click", function (e) {
        if (e.target.classList.contains("move-backward")) {
            const index = e.target.getAttribute("data-index");
            if (index !== null) {
                // Verifique se o status atual permite retroceder
                const currentIndex = tasks[index].status;
                const previousStatus = getPreviousStatus(currentIndex);
                if (previousStatus) {
                    tasks[index].status = previousStatus;
                    renderTasks();
                }
            }
        }
    });

    // Evento para selecionar todas as tarefas
    document.getElementById("select-all").addEventListener("click", function () {
        const checkboxes = document.querySelectorAll(".task-checkbox");
        const selectAllButton = document.getElementById("select-all");

        // Verifique se pelo menos uma tarefa está selecionada
        let atLeastOneSelected = false;

        checkboxes.forEach(function (checkbox) {
            if (!checkbox.checked) {
                checkbox.checked = true;
                atLeastOneSelected = true;
            } else {
                checkbox.checked = false;
            }
        });

        // Alterne o texto do botão entre "Selecionar Todas" e "Desmarcar Todas"
        if (atLeastOneSelected) {
            selectAllButton.textContent = "Desmarcar Todas";
        } else {
            selectAllButton.textContent = "Selecionar Todas";
        }
    });

    // Função para avançar o status
    function getNextStatus(currentStatus) {
        switch (currentStatus) {
            case "TODO":
                return "DOING";
            case "DOING":
                return "DONE";
            // Adicione mais casos conforme necessário para seu fluxo de status.
            default:
                return currentStatus; // Caso não haja um próximo status definido, mantenha o status atual.
        }
    }

// Função para retroceder o status
    function getPreviousStatus(currentStatus) {
        switch (currentStatus) {
            case "DOING":
                return "TODO";
            case "DONE":
                return "DOING";
            // Adicione mais casos conforme necessário para seu fluxo de status.
            default:
                return currentStatus; // Caso não haja um status anterior definido, mantenha o status atual.
        }
    }

    // Evento para avançar todas as tarefas
    document.getElementById("move-all-forward").addEventListener("click", function () {
        tasks.forEach(function (task) {
            const nextStatus = getNextStatus(task.status);
            if (nextStatus) {
                task.status = nextStatus;
            }
        });
        renderTasks();
    });

// Evento para retroceder todas as tarefas
    document.getElementById("move-all-backward").addEventListener("click", function () {
        tasks.forEach(function (task) {
            const previousStatus = getPreviousStatus(task.status);
            if (previousStatus) {
                task.status = previousStatus;
            }
        });
        renderTasks();
    });
});