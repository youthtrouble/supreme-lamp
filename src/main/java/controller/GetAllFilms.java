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
            // Retrieve pagination parameters from the request
            String offsetParam = request.getParameter("offset");
            String limitParam = request.getParameter("limit");

            // Default values for pagination
            int offset = 0;
            int limit = 10;

            // Parse the pagination parameters if they are provided
            if (offsetParam != null) {
                try {
                    offset = Integer.parseInt(offsetParam);
                } catch (NumberFormatException e) {
                    request.setAttribute("errorMessage", "Invalid offset format.");
                    request.getRequestDispatcher("./views/errors.jsp").forward(request, response);
                    return;
                }
            }

            if (limitParam != null) {
                try {
                    limit = Integer.parseInt(limitParam);
                } catch (NumberFormatException e) {
                    request.setAttribute("errorMessage", "Invalid limit format.");
                    request.getRequestDispatcher("./views/errors.jsp").forward(request, response);
                    return;
                }
            }

            // Retrieve films with pagination from the database
            List<Film> films = filmDao.getAllFilms(limit, offset);

            // Store the list of films in the request attribute
            request.setAttribute("films", films);

            // Forward request and response to the JSP for display
            request.getRequestDispatcher("./views/films-list.jsp").forward(request, response);
        } catch (Exception e) {
            // Handle exceptions that occur during the retrieval of films from the database
            request.setAttribute("errorMessage", "Database error occurred while retrieving films: " + e.getMessage());
            request.getRequestDispatcher("./views/errors.jsp").forward(request, response);
        }
    }

}
