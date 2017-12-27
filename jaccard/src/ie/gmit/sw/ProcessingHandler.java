package ie.gmit.sw;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.CharBuffer;
import java.nio.file.Files;
import java.util.HashSet;
import java.util.Set;
import java.util.function.Consumer;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Stream;

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
        SHINGLE_SIZE = Integer.parseInt(context.getInitParameter("SHINGLE_SIZE"));
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // start comparison operation

        logger.log(Level.INFO, "Inside Processing POST");
        final Part filePart = req.getPart("txtDocument");

        final InputStream is = filePart.getInputStream();
        Set<String> shingles = new HashSet<>(64);
        char [] shingle = new char[SHINGLE_SIZE];

        BufferedReader br = new BufferedReader(new InputStreamReader(is));

        int chars = br.read(shingle, 0, SHINGLE_SIZE);

        System.out.println("Chars read from buffer: " + chars + " | "
                            + new String(shingle));

        Stream<String> stream = br.lines();

        System.out.println("Characters left in buffer: ");
        stream.forEach(System.out::println);



        req.getRequestDispatcher("/processing.jsp").forward(req, resp);
        //doGet(req,resp);
    }
}
