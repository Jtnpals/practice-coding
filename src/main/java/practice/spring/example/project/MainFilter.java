package practice.spring.example.project;

import lombok.extern.slf4j.Slf4j;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
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
        if(request instanceof HttpServletRequest){
            String url = ((HttpServletRequest) request).getRequestURI();
            String queryString  = ((HttpServletRequest) request).getQueryString();
            log.info("url:: " + url);
            log.info("query:: " + queryString);
        }
        chain.doFilter(request,response);
        log.info("****** After filter ******");

    }

    @Override
    public void destroy() {
        log.info("****** Destroy filter ******");
    }
}
