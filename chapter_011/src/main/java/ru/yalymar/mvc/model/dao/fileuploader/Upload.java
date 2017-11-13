package ru.yalymar.mvc.model.dao.fileuploader;

import org.springframework.web.multipart.MultipartFile;
import ru.yalymar.mvc.model.models.Image;

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

    Set<Image> getFiles(HttpServletRequest req, MultipartFile[] files) throws IOException, ServletException;
}
