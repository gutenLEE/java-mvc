package com.interface21.webmvc.servlet.mvc.tobe;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.function.Consumer;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DispatcherServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;
    private static final String DEFAULT_BASE_PACKAGE = "camp.nextstep";
    private static final Logger log = LoggerFactory.getLogger(DispatcherServlet.class);

    private final List<HandlerMapping> handlers = new ArrayList<>();

    public DispatcherServlet() {}

    @Override
    public void init() {
        var annotationHandlerMapping = new AnnotationHandlerMapping(DEFAULT_BASE_PACKAGE);
        annotationHandlerMapping.initialize();
        this.handlers.add(annotationHandlerMapping);
    }

    @Override
    protected void service(final HttpServletRequest request, final HttpServletResponse response)
            throws ServletException {
        final String requestURI = request.getRequestURI();
        log.debug("Method : {}, Request URI : {}", request.getMethod(), requestURI);

        try {
            execute(request, response);
        } catch (Throwable e) {
            log.error("Exception : {}", e.getMessage(), e);
            throw new ServletException(e.getMessage());
        }
    }

    private void execute(HttpServletRequest request, HttpServletResponse response) {

        Optional<HandlerMapping> handler =
                handlers.stream().filter(it -> it.supports(request)).findFirst();

        handler.ifPresentOrElse(handleRequest(request, response), handleException(request));
    }

    private Consumer<HandlerMapping> handleRequest(
            HttpServletRequest request, HttpServletResponse response) {
        return handler -> {
            try {
                handler.adapt(handler.getHandler(request), request, response);
            } catch (Exception e) {
                log.error("Exception mapping [{}]: {}", request.getRequestURI(), e.getMessage(), e);
                throw new RuntimeException(e.getMessage());
            }
        };
    }

    private static Runnable handleException(HttpServletRequest request) {
        return () -> {
            try {
                throw new ServletException(
                        "Not found handler for request: [%s]".formatted(request.getRequestURI()));
            } catch (ServletException e) {
                throw new RuntimeException(e);
            }
        };
    }
}