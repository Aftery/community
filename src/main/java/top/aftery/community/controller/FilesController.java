package top.aftery.community.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import top.aftery.community.dto.FileDTO;

@Controller
public class FilesController {

    @ResponseBody
    @RequestMapping("/file/upload")
    public FileDTO upload(){

        FileDTO fileDTO = new FileDTO();
        fileDTO.setSuccess(1);
        fileDTO.setMessage("ok");
        fileDTO.setUrl("/img/2323.png");

        return fileDTO;
    }
}
