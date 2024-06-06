package com.example.publicwifimap.servlet;


import com.example.publicwifimap.data.DB;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/openapi-list")
public class OpenAPIListServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String url = "WEB-INF/views/load-wifi.jsp";
        RequestDispatcher requestDispatcher = request.getRequestDispatcher(url);

        int num = DB.checkDataExists();
        request.setAttribute("data", num + "");

        requestDispatcher.forward(request, response);
    }
}
