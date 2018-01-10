package ie.gmit.sw;

import ie.gmit.sw.common.File2ListFetcher;
import ie.gmit.sw.domain.Document;
import ie.gmit.sw.domain.Result;
import ie.gmit.sw.service.ServiceCommandsHandler;
import ie.gmit.sw.service.commands.DocumentEqualityCommandTypes;
import ie.gmit.sw.shingle.ShingleBuildersFactory;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;


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

        ShingleBuildersFactory.setShingleSize(SHINGLE_SIZE);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // start comparison operation
        logger.log(Level.INFO, "Inside Processing POST");

        final Collection<Part> files = req.getParts();

        Collection<Document> documents = processFileParts(files);
        Collection<Result> results;

        ServiceCommandsHandler service = new ServiceCommandsHandler(documents);

        results = service.executeCommand(DocumentEqualityCommandTypes.HASHCODE_K_GRAM_CMD);

        req.setAttribute("results", results);
        req.getRequestDispatcher("/results.jsp").forward(req, resp);
        //doGet(req,resp);
    }


    private Collection<Document> processFileParts(Collection<Part> files){
        Collection<Document> documents = new ArrayList<>();

        files.forEach(file ->{
            try(InputStream in = file.getInputStream()){
                List<String> fileWords = File2ListFetcher.fetchFile(in);
                Document document = new Document(file.getName(), fileWords);
                documents.add(document);
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

        return documents;
    }

}
