package ru.itis.demo.services.interfaces;

import java.io.Writer;
import java.util.Map;

public interface TemplateResolver {
    void process(String name, Map<String, String> root, Writer writer);
    String process(String name, Map<String, String> root);
}