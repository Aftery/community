package top.aftery.community.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import top.aftery.community.dto.FileDTO;
import top.aftery.community.model.User;

import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Locale;
import java.util.Set;
import java.util.UUID;

@Slf4j
@Controller
public class FilesController {

    private static final Set<String> ALLOWED_EXT = new HashSet<>(Arrays.asList("jpg", "jpeg", "gif", "png", "webp"));
    private static final long MAX_SIZE = 2 * 1024 * 1024;

    @Value("${upload.dir}")
    private String uploadDir;

    @ResponseBody
    @PostMapping("/file/upload")
    public FileDTO upload(@RequestParam("editormd-image-file") MultipartFile file, HttpSession session) {
        User user = (User) session.getAttribute("user");
        if (user == null) {
            return fail("请先登录");
        }
        if (file == null || file.isEmpty()) {
            return fail("文件为空");
        }
        if (file.getSize() > MAX_SIZE) {
            return fail("图片不能超过 2MB");
        }
        String ext = StringUtils.getFilenameExtension(file.getOriginalFilename());
        ext = ext == null ? "" : ext.toLowerCase(Locale.ROOT);
        if (!ALLOWED_EXT.contains(ext)) {
            return fail("只允许上传 jpg/jpeg/gif/png/webp 图片");
        }
        // 文件名用 UUID 重新生成，杜绝原始文件名带来的路径穿越
        String filename = UUID.randomUUID().toString().replace("-", "") + "." + ext;
        try {
            Path dir = Paths.get(uploadDir).toAbsolutePath().normalize();
            Files.createDirectories(dir);
            file.transferTo(dir.resolve(filename).toFile());
        } catch (IOException e) {
            log.error("图片上传失败", e);
            return fail("上传失败，请稍后重试");
        }
        FileDTO dto = new FileDTO();
        dto.setSuccess(1);
        dto.setMessage("ok");
        dto.setUrl("/upload/" + filename);
        return dto;
    }

    private FileDTO fail(String message) {
        FileDTO dto = new FileDTO();
        dto.setSuccess(0);
        dto.setMessage(message);
        return dto;
    }
}
