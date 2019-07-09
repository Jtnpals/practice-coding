package practice.spring.example.project;

import lombok.extern.slf4j.Slf4j;

import javax.servlet.*;
import java.io.IOException;

@Slf4j
public class MainFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        log.info("****** Init filter ******");
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        log.info("****** Before filter ******");
        chain.doFilter(request,response);
        log.info("****** After filter ******");

    }

    @Override
    public void destroy() {
        log.info("****** Destroy filter ******");
    }
}
