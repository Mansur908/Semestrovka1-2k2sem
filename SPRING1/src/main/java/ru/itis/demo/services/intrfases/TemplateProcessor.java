package ru.itis.demo.services.intrfases;

import java.util.Map;

public interface TemplateProcessor {
    String getProcessedTemplate(Map<String, String> params, String ftl);
}