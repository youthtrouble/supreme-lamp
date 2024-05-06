package controller;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.FilmDao;
import models.Film;

/**
 * Servlet implementation for adding a new film to the database.
 * Mapped to '/add-film' URL pattern.
 */
@WebServlet(name = "AddFilm", urlPatterns = {"/add-film"})
public class AddFilm extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private FilmDao filmDAO;

    /**
     * Initializes the servlet and creates an instance of FilmDao.
     */
    @Override
    public void init() {
        filmDAO = FilmDao.getInstance(); // Initialize DAO to handle film database operations.
    }

    /**
     * Handles GET requests to display the film addition form.
     * Forwards the request to the 'add.jsp' page.
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Forward the request to the JSP page to display the add film form.
        request.getRequestDispatcher("./views/add.jsp").forward(request, response);
    }

    /**
     * Handles POST requests to submit new film data to the database.
     * Extracts form data, creates a Film object, and inserts it into the database.
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Extract film data from request parameters.
        String title = request.getParameter("title");
        int year;

        // Try to parse the year from the form input; handle number format exception.
        try {
            year = Integer.parseInt(request.getParameter("year"));
        } catch (NumberFormatException e) {
            // On parsing error, set error message and forward to the error display page.
            request.setAttribute("errorMessage", "Invalid year format: " + e.getMessage());
            request.getRequestDispatcher("./views/errors.jsp").forward(request, response);
            return;
        }

        // Extract remaining film data.
        String director = request.getParameter("director");
        String stars = request.getParameter("stars");
        String review = request.getParameter("review");

        // Create a new Film object.
        Film film = new Film(title, year, director, stars, review);

        // Insert the new film into the database.
        try {
            filmDAO.insertFilm(film);
            // Redirect to the film listing page upon successful insertion.
            response.sendRedirect("get-all-films");
        } catch (SQLException e) {
            // Handle SQL exception by setting an error message and forwarding to the error page.
            request.setAttribute("errorMessage", "Database error when adding film: " + e.getMessage());
            request.getRequestDispatcher("./views/errors.jsp").forward(request, response);
        } catch (Exception e) {
            // Handle any other exceptions.
            request.setAttribute("errorMessage", "Unexpected error: " + e.getMessage());
            request.getRequestDispatcher("./views/errors.jsp").forward(request, response);
        }
    }
}
