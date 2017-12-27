//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package ie.gmit.sw;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;



@MultipartConfig(
        fileSizeThreshold = 2097152,
        maxFileSize = 10485760L,
        maxRequestSize = 52428800L

)
public class ServiceHandler extends HttpServlet {

    private Logger logger = Logger.getLogger("ServiceHandlerLogger");
    private int SHINGLE_SIZE;
    private static long jobNumber = 0L;


    public ServiceHandler() {
    }

    public void init() throws ServletException {
        System.out.println("index init()");
    }

    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        logger.log(Level.INFO, "Inside GET");
    }

    public void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    }
}
