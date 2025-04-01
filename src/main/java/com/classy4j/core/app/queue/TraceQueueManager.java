package com.classy4j.core.app.queue;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 追踪队列管理器，用于管理和追踪任务队列的状态
 */
public class TraceQueueManager {
    private static final Map<String, QueueItem> queueMap = new ConcurrentHashMap<>();

    /**
     * 队列项，用于存储任务信息
     */
    private static class QueueItem {
        private final String taskId;
        private final long createTime;
        private long updateTime;
        private String status;
        private Object result;

        public QueueItem(String taskId) {
            this.taskId = taskId;
            this.createTime = System.currentTimeMillis();
            this.updateTime = this.createTime;
            this.status = "pending";
        }

        public void updateStatus(String status) {
            this.status = status;
            this.updateTime = System.currentTimeMillis();
        }

        public void setResult(Object result) {
            this.result = result;
            this.updateTime = System.currentTimeMillis();
        }

        public String getTaskId() {
            return taskId;
        }

        public long getCreateTime() {
            return createTime;
        }

        public long getUpdateTime() {
            return updateTime;
        }

        public String getStatus() {
            return status;
        }

        public Object getResult() {
            return result;
        }
    }

    /**
     * 创建任务
     *
     * @param taskId 任务ID
     */
    public static void createTask(String taskId) {
        queueMap.put(taskId, new QueueItem(taskId));
    }

    /**
     * 更新任务状态
     *
     * @param taskId 任务ID
     * @param status 状态
     */
    public static void updateTaskStatus(String taskId, String status) {
        QueueItem item = queueMap.get(taskId);
        if (item != null) {
            item.updateStatus(status);
        }
    }

    /**
     * 设置任务结果
     *
     * @param taskId 任务ID
     * @param result 结果
     */
    public static void setTaskResult(String taskId, Object result) {
        QueueItem item = queueMap.get(taskId);
        if (item != null) {
            item.setResult(result);
        }
    }

    /**
     * 获取任务状态
     *
     * @param taskId 任务ID
     * @return 任务状态
     */
    public static String getTaskStatus(String taskId) {
        QueueItem item = queueMap.get(taskId);
        return item != null ? item.getStatus() : null;
    }

    /**
     * 获取任务结果
     *
     * @param taskId 任务ID
     * @return 任务结果
     */
    public static Object getTaskResult(String taskId) {
        QueueItem item = queueMap.get(taskId);
        return item != null ? item.getResult() : null;
    }

    /**
     * 删除任务
     *
     * @param taskId 任务ID
     */
    public static void removeTask(String taskId) {
        queueMap.remove(taskId);
    }

    /**
     * 清理过期任务
     *
     * @param maxAge 最大存活时间（毫秒）
     */
    public static void cleanExpiredTasks(long maxAge) {
        long now = System.currentTimeMillis();
        queueMap.entrySet().removeIf(entry ->
                now - entry.getValue().getUpdateTime() > maxAge);
    }
}