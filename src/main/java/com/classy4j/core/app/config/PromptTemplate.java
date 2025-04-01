package com.classy4j.core.app.config;

import java.util.Map;

/**
 * 提示模板类，用于管理和处理提示模板
 */
public class PromptTemplate {
    private String template;
    private Map<String, Object> variables;

    public PromptTemplate(String template) {
        this.template = template;
    }

    public PromptTemplate(String template, Map<String, Object> variables) {
        this.template = template;
        this.variables = variables;
    }

    /**
     * 渲染模板
     *
     * @param variables 变量映射
     * @return 渲染后的文本
     */
    public String render(Map<String, Object> variables) {
        if (variables == null || variables.isEmpty()) {
            return template;
        }

        String result = template;
        for (Map.Entry<String, Object> entry : variables.entrySet()) {
            String key = entry.getKey();
            Object value = entry.getValue();
            result = result.replace(String.format("{%s}", key), value != null ? value.toString() : "");
        }
        return result;
    }

    /**
     * 渲染模板，使用构造时设置的变量
     *
     * @return 渲染后的文本
     */
    public String render() {
        return render(this.variables);
    }

    /**
     * 验证模板是否有效
     *
     * @return 是否有效
     */
    public boolean isValid() {
        return template != null && !template.isEmpty();
    }

    public String getTemplate() {
        return template;
    }

    public void setTemplate(String template) {
        this.template = template;
    }

    public Map<String, Object> getVariables() {
        return variables;
    }

    public void setVariables(Map<String, Object> variables) {
        this.variables = variables;
    }
}