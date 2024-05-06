package controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.FilmDao;
import models.Film;

/**
 * Servlet implementation for retrieving all films from the database.
 * Mapped to '/get-all-films' URL pattern.
 */
@WebServlet(name = "GetAllFilms", urlPatterns = {"/get-all-films"})
public class GetAllFilms extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private FilmDao filmDao;

    /**
     * Initializes the servlet and creates an instance of FilmDao.
     * This method is called once when the servlet is first loaded.
     */
    @Override
    public void init() {
        this.filmDao = FilmDao.getInstance(); // Initialize DAO to manage database operations for films.
    }

    /**
     * Handles GET requests to retrieve and display all films.
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            List<Film> films = filmDao.getAllFilms(); // Retrieve all films from the database.
            request.setAttribute("films", films); // Store the list of films in the request attribute.
            request.getRequestDispatcher("./views/films-list.jsp").forward(request, response); // Forward request and response to the JSP for display.
        } catch (Exception e) {
            // Handle exceptions that occur during the retrieval of films from the database.
            request.setAttribute("errorMessage", "Database error occurred while retrieving films: " + e.getMessage());
            request.getRequestDispatcher("./views/errors.jsp").forward(request, response); // Forward to the error page if an exception occurs.
        }
    }
}
