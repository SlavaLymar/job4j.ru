package ru.yalymar.mapping.model.dao.fileuploader;

import ru.yalymar.mapping.model.Image;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Set;

public interface Upload {

    Set<Image> getFiles(HttpServletRequest req,
                        HttpServletResponse resp) throws IOException, ServletException;
}
