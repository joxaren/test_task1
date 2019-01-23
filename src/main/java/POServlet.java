import javax.servlet.ServletException;
import javax.servlet.http.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Enumeration;

public class POServlet extends HttpServlet {

    int SC_CUSTOM_TEST = 1;

    public void doGet(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse)
            throws IOException {
        httpServletResponse.getWriter().print("test");
        try{
            handleRequest(httpServletRequest, httpServletResponse);
        } catch (Exception ex) {}
    }

    private void handleRequest(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        
    }



}