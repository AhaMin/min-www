package base_www.file;

import base_core.constants.helper.FileUploadHelper;
import base_core.response.ResponseStatus;
import base_core.response.ResponseWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartRequest;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

/**
 * created by ewang on 2018/4/2.
 */
@Controller
@RequestMapping("/file")
public class FileUploadController {

    @Autowired
    private FileUploadHelper fileUploadHelper;

    @RequestMapping
    public ModelAndView welcome() {
        return new ModelAndView("/file");
    }

    @RequestMapping(value = "/upload", method = RequestMethod.POST)
    public ResponseWrapper upload(HttpServletRequest httpServletRequest) {
        MultipartFile file;
        long imageId = 0l;
        if (httpServletRequest instanceof MultipartRequest) {
            file = ((MultipartRequest) httpServletRequest).getFile("file");
            try {
                imageId = fileUploadHelper.upload(file);
            } catch (Exception e) {
                return new ResponseWrapper(ResponseStatus.UploadFail, "上传失败");
            }
        }

        return new ResponseWrapper().addObject("imageId", imageId);
    }
}
