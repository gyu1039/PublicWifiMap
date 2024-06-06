package com.example.publicwifimap.servlet;

import com.example.publicwifimap.data.DB;
import com.example.publicwifimap.data.Row;
import com.example.publicwifimap.data.WifiInfoWithDistance;
import lombok.extern.slf4j.Slf4j;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@WebServlet("/nearby") @Slf4j
public class NearByWifiServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String url = "/index.jsp";
        RequestDispatcher requestDispatcher = request.getRequestDispatcher(url);

        List<WifiInfoWithDistance> data = DB.getNearByWifi(
                Double.parseDouble(request.getParameter("lat")),
                Double.parseDouble(request.getParameter("lnt")));

        if(data.isEmpty()) {
            log.info("뭔가 이상");
        } else {
            data.stream().forEach(d -> log.info("d"));
        }

        request.setAttribute("data", data);

        requestDispatcher.forward(request, response);
    }
}
