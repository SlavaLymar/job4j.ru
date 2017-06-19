package ru.yalymar.mapping.model.dao.fileuploader;

import ru.yalymar.mapping.model.models.Image;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Set;

/**
 * @author slavalymar
 * @since 19.06.2017
 * @version 1
 */
public interface Upload {

    Set<Image> getFiles(HttpServletRequest req,
                        HttpServletResponse resp) throws IOException, ServletException;
}
