package ie.gmit.sw;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.*;
import java.util.*;
import java.util.function.Consumer;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@MultipartConfig(
        fileSizeThreshold = 5097152,
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
        SHINGLE_SIZE = Integer.parseInt(context.getInitParameter("SHINGLE_SIZE"));
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // start comparison operation
        logger.log(Level.INFO, "Inside Processing POST");
        final Part filePart = req.getPart("txtDocument");

        try (InputStream is = filePart.getInputStream()) {

            // File part containing underlying ByteArrayInputStream
            if(is instanceof ByteArrayInputStream) System.out.println("IN is ByteArrayInputStream");

            // do some magic with input stream
        }


        req.getRequestDispatcher("/processing.jsp").forward(req, resp);
        //doGet(req,resp);
    }

}
