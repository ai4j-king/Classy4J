package com.classy4j.core.model_manager.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 提示模板解析器
 * 
 * 规则：
 * 1. 模板变量必须用{{}}包裹
 * 2. 模板变量Key只能是：字母+数字+下划线，最大长度16个字符，只能以字母和下划线开头
 * 3. 模板变量Key不能包含换行或空格，必须符合规则2
 * 4. 除了上述之外，还接受3种特殊的模板变量Key：{{#histories#}} {{#query#}} {{#context#}}，不允许其他{{##}}模板变量
 */
public class PromptTemplateParser {
    private static final String REGEX = "\\{\\{([a-zA-Z_][a-zA-Z0-9_]{0,15}|#(?:histories|query|context)#)\\}\\}";
    private static final Pattern PATTERN = Pattern.compile(REGEX);

    private final String template;
    private final List<String> variableKeys;

    public PromptTemplateParser(String template) {
        this.template = template;
        this.variableKeys = extract();
    }

    /**
     * 提取模板变量
     */
    private List<String> extract() {
        List<String> keys = new ArrayList<>();
        Matcher matcher = PATTERN.matcher(template);
        while (matcher.find()) {
            keys.add(matcher.group(1));
        }
        return keys;
    }

    /**
     * 格式化模板
     *
     * @param inputs 输入变量映射
     * @param removeTemplateVariables 是否移除模板变量
     * @return 格式化后的字符串
     */
    public String format(Map<String, String> inputs, boolean removeTemplateVariables) {
        String result = template;
        for (String key : variableKeys) {
            String value = inputs.getOrDefault(key, "{{" + key + "}}");
            if (removeTemplateVariables) {
                value = removeTemplateVariables(value);
            }
            result = result.replace("{{" + key + "}}", value);
        }
        return result.replaceAll("<\\|.*?\\|>", "");
    }

    /**
     * 移除模板变量
     */
    public static String removeTemplateVariables(String text) {
        return text.replaceAll(REGEX, "{$1}");
    }

    /**
     * 获取变量键列表
     */
    public List<String> getVariableKeys() {
        return variableKeys;
    }
}