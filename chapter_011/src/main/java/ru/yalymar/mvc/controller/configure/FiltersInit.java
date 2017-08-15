package ru.yalymar.mvc.controller.configure;

import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;
import ru.yalymar.mvc.controller.filters.AuthFilter;
import ru.yalymar.mvc.controller.filters.UserFilter;
import javax.servlet.Filter;

public class FiltersInit extends AbstractAnnotationConfigDispatcherServletInitializer {

    @Override
    protected String[] getServletMappings() {
        return new String[0];
    }

    @Override
    protected Filter[] getServletFilters() {
        return new Filter[]{new AuthFilter(), new UserFilter()};
    }

    @Override
    protected Class<?>[] getRootConfigClasses() {
        return new Class[0];
    }

    @Override
    protected Class<?>[] getServletConfigClasses() {
        return new Class[0];
    }
}
