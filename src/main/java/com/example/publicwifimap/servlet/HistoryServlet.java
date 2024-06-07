package com.example.publicwifimap.servlet;

import com.example.publicwifimap.data.DB;
import com.example.publicwifimap.data.History;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.List;

@WebServlet("/history")
public class HistoryServlet  extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String lat = request.getParameter("lat");
        String lnt = request.getParameter("lnt");

        DB.insertLocation(lat, lnt);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String url = "WEB-INF/views/history.jsp";
        RequestDispatcher requestDispatcher = request.getRequestDispatcher(url);

        List<History> histories = DB.selectLocation();
        request.setAttribute("data", histories);

        requestDispatcher.forward(request, response);
    }

    @Override
    protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        StringBuilder sb = new StringBuilder();
        BufferedReader reader = request.getReader();
        String line;
        while((line = reader.readLine()) != null) {
            sb.append(line);
        }
        reader.close();
        JsonObject asJsonObject = JsonParser.parseString(sb.toString()).getAsJsonObject();
        int id = asJsonObject.get("id").getAsInt();

        DB.deleteHistory(id);
    }
}
