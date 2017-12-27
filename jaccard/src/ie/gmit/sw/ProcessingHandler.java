package ie.gmit.sw;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

@MultipartConfig(
        fileSizeThreshold = 2097152,
        maxFileSize = 10485760L,
        maxRequestSize = 52428800L

)
@WebServlet(value = "/processing")
public class ProcessingHandler extends HttpServlet {
    private Logger logger = Logger.getLogger("ProcessingHandlerLogger");
    private int SHINGLE_SIZE;


    public void init() throws ServletException {
        ServletContext context = this.getServletContext();
        System.out.println("processing init()");
        System.out.println(context.getInitParameter("SHINGLE_SIZE"));
        //SHINGLE_SIZE = Integer.getInteger(context.getInitParameter("SHINGLE_SIZE"));
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // start comparison operation

        logger.log(Level.INFO, "Inside Processing POST");
        System.out.println(req.getParameter("txtTitle"));
        System.out.println(req.getParameter("txtDocument"));
        req.getRequestDispatcher("/processing.jsp").forward(req, resp);
        //doGet(req,resp);
    }
}
