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
import java.lang.reflect.Array;
import java.nio.CharBuffer;
import java.nio.file.Files;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.Callable;
import java.util.concurrent.TimeUnit;
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

        BufferedReader br = new BufferedReader(new InputStreamReader(is));

        Consumer<String> lineConsumer = line -> {

            if(line.length() > 15){
                // trim the last nth of extra characters and place into temp
                // 15 character string goes into shingle, remaining added to next
                // shingle.
            }else if(line.length() < 15){
                //
            }
            System.out.println("NEXT LINE");
        };


        Stream<String> stream = br.lines();

        System.out.println("Characters left in buffer: ");
        stream.forEach(lineConsumer);



        req.getRequestDispatcher("/processing.jsp").forward(req, resp);
        //doGet(req,resp);
    }

    private void testCallable(){
        Callable<Integer> task = () -> {
            try {
                TimeUnit.SECONDS.sleep(1);
                return 123;
            }
            catch (InterruptedException e) {
                throw new IllegalStateException("task interrupted", e);
            }
        };
    }
}
