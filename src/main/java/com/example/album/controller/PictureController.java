package com.example.album.controller;

import com.example.album.entity.Comment;
import com.example.album.entity.Picture;
import com.example.album.repository.CommentRepository;
import com.example.album.repository.PictureRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Controller
@RequestMapping("/")
public class PictureController {

    @Autowired
    private PictureRepository pictureRepository;

    @Autowired
    private CommentRepository commentRepository;

    @GetMapping("/pictures")
    public String pictures(Model model) {
        List<Picture> pictureList = pictureRepository.findAll();
        model.addAttribute("pictures", pictureList);
        return "pictures";
    }

    @GetMapping("/pictures/add")
    public String picturesAddGet() {
        return "picturesAdd";
    }

    @PostMapping("/pictures/add")
    public String picturesAddPost(@RequestParam("picture") MultipartFile multipartFile, HttpServletRequest httpServletRequest) {
        String uploadFolder = "/upload/";

        String label = httpServletRequest.getParameter("label");
        String originalFilename = multipartFile.getOriginalFilename();
        String subffix = originalFilename.substring(originalFilename.lastIndexOf(".") + 1);
        String filename = UUID.randomUUID().toString() + '.' + subffix;
        String realPath = httpServletRequest.getServletContext().getRealPath(uploadFolder);
        String absolutePath = realPath + filename;
        String relativePath = uploadFolder + filename;
        String contentType = multipartFile.getContentType();
        String description = httpServletRequest.getParameter("description");

        File targetFile = new File(realPath);
        if (!targetFile.exists()) {
            targetFile.mkdirs();
        }
        try {
            FileOutputStream fileOutputStream = new FileOutputStream(realPath + filename);
            fileOutputStream.write(multipartFile.getBytes());
            fileOutputStream.flush();
            fileOutputStream.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        if (label == null || "".equals(label)) {
            label = originalFilename;
        }

        Picture picture = new Picture(label, originalFilename, filename, absolutePath,
                relativePath, contentType, description, new Date());
        pictureRepository.save(picture);

        return "redirect:/pictures";
    }

    @GetMapping("/pictures/{id}")
    public String pictures(@PathVariable("id") Long id, Model model) {
        Optional<Picture> pictureOptional = pictureRepository.findById(id);
        model.addAttribute("picture", pictureOptional.get());

        List<Comment> comments = commentRepository.findByPid(id);
        model.addAttribute("comments", comments);
        return "picture";
    }
}
