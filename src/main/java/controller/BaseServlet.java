package controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public abstract class BaseServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            doProcess(request, response);
        } catch (Exception e) {
            handleException(response, e);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            doProcess(request, response);
        } catch (Exception e) {
            handleException(response, e);
        }
    }

    // Template method to process request
    protected abstract void doProcess(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, Exception;

    // Handle exceptions in a standard way across all servlets
    protected void handleException(HttpServletResponse response, Exception e) throws IOException {
        response.setContentType("text/html;charset=UTF-8");
        response.getWriter().write("<p>Error: " + e.getMessage() + "</p>");
    }
}
