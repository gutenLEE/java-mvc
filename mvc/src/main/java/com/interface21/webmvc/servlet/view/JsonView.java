package com.interface21.webmvc.servlet.view;

import java.util.Map;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import com.interface21.webmvc.servlet.View;

public class JsonView implements View {

    @Override
    public void render(
            final Map<String, ?> model, final HttpServletRequest request, HttpServletResponse response)
            throws Exception {}
}
