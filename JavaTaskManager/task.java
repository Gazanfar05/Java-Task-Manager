public class Task {
    private String name;
    private boolean isDone;

    public Task(String name) {
        this.name = name;
        this.isDone = false;
    }

    public String getName() {
        return name;
    }

    public boolean isDone() {
        return isDone;
    }

    public void markDone() {
        isDone = true;
    }

    @Override
    public String toString() {
        return (isDone ? "[✓] " : "[ ] ") + name;
    }
}
//1. Run "javac Task.java TaskManager.java"
//2. Run "java TaskManager" in bash under your terminal