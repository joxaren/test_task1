import javax.servlet.ServletException;
import javax.servlet.http.*;
import java.io.*;

import static java.time.LocalTime.now;

public class POServlet extends HttpServlet {

    private int SC_CUSTOM_TEST;

    public void doPost(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse)
            throws IOException {
        try {
            handleRequest(httpServletRequest, httpServletResponse);
        } catch (Exception ex) {
        }
    }

    public void doGet(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse)
            throws IOException {
        httpServletResponse.getWriter().print("teeeeeest");
    }

    private void handleRequest(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        try {
            Thread.sleep(100);
        } catch (InterruptedException ex) {
            ex.printStackTrace();
        }
        String IDNum = requestBodyToString(request);
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        if (IDNum != null && !IDNum.isEmpty()) {
            SC_CUSTOM_TEST = 0;
        } else {
            SC_CUSTOM_TEST = -1;
        }
        out.println("<!DOCTYPE html>");
        out.println("<body>");
        out.println("<p>IDNum: " + IDNum + "</p>");
        out.println("<p>Status: " + SC_CUSTOM_TEST + "</p>");
        out.println("</body>");
        out.println("</html>");
        out.close();
    }

    private static String requestBodyToString(HttpServletRequest request) {
        StringBuilder stringBuilder = new StringBuilder();
        BufferedReader bufferedReader = null;
        try {
            InputStream inputStream = request.getInputStream();
            if (inputStream != null) {
                bufferedReader = new BufferedReader(new InputStreamReader(
                        inputStream));
                char[] charBuffer = new char[256];
                int bytesRead = -1;
                while ((bytesRead = bufferedReader.read(charBuffer)) > 0) {
                    stringBuilder.append(charBuffer, 0, bytesRead);
                }
            } else {
                stringBuilder.append("");
            }
        } catch (IOException ex) {
            logMessage("requestBodyToString", "Got error:" + ex.toString());
        } finally {
            if (bufferedReader != null) {
                try {
                    bufferedReader.close();
                } catch (IOException ex) {
                    logMessage("requestBodyToString", "Got error:" + ex.toString());
                }
            }
        }
        String body = stringBuilder.toString();
        return body;
    }

    private static void logMessage(String module, String message) {
        System.out.println(now() + "\t " + module + ":" + message);
    }

    private static String makeSoapResponseSimple(String InnerXml) {
        return "<soap:Envelope xmlns:soap=\"http://schemas.xmlsoap.org/soap/envelope/\"><soap:Body>" + InnerXml + "</soap:Body></soap:Envelope>";
    }
}