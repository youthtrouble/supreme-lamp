package controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.FilmDao;

/**
 * Servlet implementation for deleting a film from the database.
 * Mapped to '/delete-film' URL pattern.
 */
@WebServlet(name = "DeleteFilm", urlPatterns = {"/delete-film"})
public class DeleteFilm extends HttpServlet {
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
     * Handles POST requests to delete a specific film identified by its ID.
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int id; // Declare variable to hold film ID.

        // Attempt to parse the film ID from request parameters.
        try {
            id = Integer.parseInt(request.getParameter("id")); // Extract film ID from request.
        } catch (NumberFormatException e) {
            // Handle error if the film ID is not a valid integer.
            request.setAttribute("errorMessage", "Invalid film ID format.");
            request.getRequestDispatcher("./views/errors.jsp").forward(request, response);
            return; // Exit the method to prevent further execution.
        }

        // Try to delete the film using the extracted ID.
        try {
            filmDao.deleteFilm(id); // Call DAO method to delete the film.
            // If deletion is successful, redirect to the films listing page.
            response.sendRedirect("get-all-films");
        } catch (Exception e) {
            // Handle exceptions related to database operations.
            request.setAttribute("errorMessage", "Unable to delete film: " + e.getMessage());
            request.getRequestDispatcher("./views/errors.jsp").forward(request, response);
        }
    }
}
