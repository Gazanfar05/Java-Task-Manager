const STORAGE_KEY = "task_orbit_tasks_v1";

const taskForm = document.getElementById("task-form");
const taskInput = document.getElementById("task-input");
const taskList = document.getElementById("task-list");
const stats = document.getElementById("stats");
const clearDoneBtn = document.getElementById("clear-done");
const emptyState = document.getElementById("empty-state");

let tasks = loadTasks();
render();

taskForm.addEventListener("submit", (event) => {
  event.preventDefault();
  const title = taskInput.value.trim();

  if (!title) {
    return;
  }

  tasks.unshift({
    id: crypto.randomUUID(),
    title,
    done: false
  });

  taskInput.value = "";
  saveTasks();
  render();
});

clearDoneBtn.addEventListener("click", () => {
  tasks = tasks.filter((task) => !task.done);
  saveTasks();
  render();
});

taskList.addEventListener("click", (event) => {
  const target = event.target;
  const item = target.closest(".task-item");
  if (!item) {
    return;
  }

  const id = item.dataset.id;

  if (target.classList.contains("delete-btn")) {
    tasks = tasks.filter((task) => task.id !== id);
    saveTasks();
    render();
    return;
  }

  if (target.classList.contains("task-toggle")) {
    tasks = tasks.map((task) => {
      if (task.id === id) {
        return { ...task, done: !task.done };
      }
      return task;
    });

    saveTasks();
    render();
  }
});

function render() {
  taskList.innerHTML = "";

  tasks.forEach((task) => {
    const li = document.createElement("li");
    li.className = `task-item${task.done ? " done" : ""}`;
    li.dataset.id = task.id;

    li.innerHTML = `
      <input class="task-toggle" type="checkbox" ${task.done ? "checked" : ""} aria-label="Mark task as done" />
      <p class="task-title">${escapeHtml(task.title)}</p>
      <button class="delete-btn" type="button" aria-label="Delete task">Delete</button>
    `;

    taskList.appendChild(li);
  });

  const doneCount = tasks.filter((task) => task.done).length;
  stats.textContent = `${tasks.length} tasks · ${doneCount} done`;
  emptyState.style.display = tasks.length === 0 ? "block" : "none";
  clearDoneBtn.disabled = doneCount === 0;
}

function loadTasks() {
  try {
    const raw = localStorage.getItem(STORAGE_KEY);
    if (!raw) {
      return [];
    }

    const parsed = JSON.parse(raw);
    if (!Array.isArray(parsed)) {
      return [];
    }

    return parsed.filter((item) => {
      return item && typeof item.id === "string" && typeof item.title === "string" && typeof item.done === "boolean";
    });
  } catch {
    return [];
  }
}

function saveTasks() {
  localStorage.setItem(STORAGE_KEY, JSON.stringify(tasks));
}

function escapeHtml(value) {
  return value
    .replaceAll("&", "&amp;")
    .replaceAll("<", "&lt;")
    .replaceAll(">", "&gt;")
    .replaceAll('"', "&quot;")
    .replaceAll("'", "&#39;");
}
