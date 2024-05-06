package utils;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ViewUtil {

    /**
     * Forwards the request to the layout page with dynamic content.
     * @param request The HttpServletRequest object.
     * @param response The HttpServletResponse object.
     * @param pageTitle The title of the page to be displayed.
     * @param contentPage The relative path to the JSP file that contains the specific content.
     * @param errorMessage Optional error message for error handling.
     * @throws ServletException If a servlet-specific error occurs.
     * @throws IOException If an I/O error occurs.
     */
    public static void forwardToPage(HttpServletRequest request, HttpServletResponse response,
                                     String pageTitle, String contentPage, String errorMessage)
            throws ServletException, IOException {

        request.setAttribute("pageTitle", pageTitle);
        request.setAttribute("contentPage", contentPage);
        if (errorMessage != null && !errorMessage.isEmpty()) {
            request.setAttribute("error", errorMessage);
        }

        // Forward to the layout JSP
        request.getRequestDispatcher(contentPage).forward(request, response);
    }
}
