package com.example.publicwifimap.servlet;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/history")
public class HistoryServlet  extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {


        String url = "WEB-INF/views/ing";
        RequestDispatcher requestDispatcher = request.getRequestDispatcher(url);

//        if(connection == null) {
//            request.setAttribute("content", "데이터 를 불러올 수 없습니다");
//        }

        requestDispatcher.forward(request, response);
    }
}
