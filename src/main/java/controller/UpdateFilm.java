package controller;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.FilmDao;
import models.Film;

/**
 * Servlet implementation for updating an existing film in the database.
 * Mapped to '/update-film' URL pattern.
 */
@WebServlet(name = "UpdateFilm", urlPatterns = {"/update-film"})
public class UpdateFilm extends HttpServlet {
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
     * Handles GET requests to prepopulate the form for updating a film.
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String id = request.getParameter("id");
        if (id == null || id.trim().isEmpty()) {
            // Send an error if the film ID parameter is missing.
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Missing film ID.");
            return;
        }

        try {
            int filmId = Integer.parseInt(id); // Convert the string ID to integer.
            Film film = filmDao.getFilmById(filmId); // Retrieve the film by ID from the database.

            if (film == null) {
                // Send an error if no film is found with the provided ID.
                response.sendError(HttpServletResponse.SC_NOT_FOUND, "No film found with ID: " + filmId);
                return;
            }

            request.setAttribute("film", film); // Set the film object in the request scope to prepopulate the form.
            RequestDispatcher dispatcher = request.getRequestDispatcher("./views/edit.jsp");
            dispatcher.forward(request, response); // Forward to the edit page.
        } catch (NumberFormatException e) {
            // Handle invalid ID formats.
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid film ID format.");
        } catch (SQLException e) {
            // Handle SQL exceptions if retrieval fails.
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Database error when retrieving film.");
        }
    }

    /**
     * Handles POST requests to update a film's data in the database.
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            // Parse film details from the request parameters.
            int id = Integer.parseInt(request.getParameter("id"));
            String title = request.getParameter("title");
            int year = Integer.parseInt(request.getParameter("year"));
            String director = request.getParameter("director");
            String stars = request.getParameter("stars");
            String review = request.getParameter("review");

            Film film = new Film(id, title, year, director, stars, review); // Create a new Film object with the updated details.

            filmDao.updateFilm(film); // Call the DAO to update the film in the database.
            response.sendRedirect("get-all-films"); // Redirect to the film listing page if the update is successful.
        } catch (NumberFormatException | SQLException e) {
            // Handle parsing or database exceptions.
            request.setAttribute("errorMessage", "Error updating film: " + e.getMessage());
            request.getRequestDispatcher("./views/errors.jsp").forward(request, response); // Forward to the error page if an exception occurs.
        }
    }
}
