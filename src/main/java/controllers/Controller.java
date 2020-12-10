package controllers;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class Controller extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws javax.servlet.ServletException, IOException {
        String[] str = request.getRequestURI().split("/");
        switch (str[str.length - 1]){
            case "main.html":{
                request.getRequestDispatcher("/WEB-INF/jsp/main.jsp").forward(request, response);
                break;
            }
            default: {
                response.sendError(404);
                break;
            }
        }
    }
}

