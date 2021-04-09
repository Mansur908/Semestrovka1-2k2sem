package ru.itis.demo.services.impls;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;
import ru.itis.demo.services.intrfases.TemplateResolver;

import java.io.IOException;
import java.io.Writer;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class TemplateResolverImpl implements TemplateResolver {
    private final Configuration configuration;

    @Override
    public void process(String name, Map<String, String> root, Writer writer) {
        try {
            Template t = configuration.getTemplate(name);
            t.process(root, writer);
        } catch (IOException | TemplateException e) {
            throw new IllegalStateException(e);
        }
    }

    @Override
    public String process(String name, Map<String, String> root) {
        try {
            Template t = configuration.getTemplate(name);
            return FreeMarkerTemplateUtils.processTemplateIntoString(t, root);
        } catch (IOException | TemplateException e) {
            throw new IllegalStateException(e);
        }
    }
}
