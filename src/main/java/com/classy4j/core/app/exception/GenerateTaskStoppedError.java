package com.classy4j.core.app.exception;

/**
 * 生成任务被停止的异常
 */
public class GenerateTaskStoppedError extends RuntimeException {
    private final String taskId;

    public GenerateTaskStoppedError(String taskId) {
        super("Generate task " + taskId + " has been stopped.");
        this.taskId = taskId;
    }

    public GenerateTaskStoppedError(String taskId, String message) {
        super(message);
        this.taskId = taskId;
    }

    public String getTaskId() {
        return taskId;
    }
}