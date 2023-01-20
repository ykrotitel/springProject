package ru.rasskazov.testSpring.config;

import org.springframework.web.filter.HiddenHttpMethodFilter;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;

public class MySpringDispatcherServletInitializer extends AbstractAnnotationConfigDispatcherServletInitializer  {
    @Override
    protected Class<?>[] getRootConfigClasses() {
        return new Class[0];
    }

    @Override
    protected Class<?>[] getServletConfigClasses() {  // в случае с web.xml заменяет нам определение сервлета <servlet>
        return new Class[] {SpringConfig.class};
    }

    @Override
    protected String[] getServletMappings() { // в случае с web.xml заменяет блок определения <servlet-mapping>
        return new String[] {"/"};
    }

    @Override
    public void onStartup(ServletContext aServletContext) throws ServletException {
        super.onStartup(aServletContext);
        registerHiddenFieldFilter(aServletContext);
    }

    private void registerHiddenFieldFilter(ServletContext aContext) {
        aContext.addFilter("hiddenHttpMethodFilter",
                new HiddenHttpMethodFilter()).addMappingForUrlPatterns(null ,true, "/*");
    }
}
