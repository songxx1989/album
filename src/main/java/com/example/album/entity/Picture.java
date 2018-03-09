package com.example.album.entity;

import javax.persistence.*;
import java.util.Date;

@Entity
public class Picture {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String label;
    private String originalFilename;
    private String filename;
    private String absolutePath;
    private String relativePath;
    private String contentType;
    @Column(length = 3000)
    private String description;
    private Date ctime;

    public Picture() {
    }

    public Picture(String label, String originalFilename, String filename, String absolutePath, String relativePath, String contentType, String description, Date ctime) {
        this.label = label;
        this.originalFilename = originalFilename;
        this.filename = filename;
        this.absolutePath = absolutePath;
        this.relativePath = relativePath;
        this.contentType = contentType;
        this.description = description;
        this.ctime = ctime;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getContentType() {
        return contentType;
    }

    public void setContentType(String contentType) {
        this.contentType = contentType;
    }

    public String getOriginalFilename() {
        return originalFilename;
    }

    public void setOriginalFilename(String originalFilename) {
        this.originalFilename = originalFilename;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }

    public String getAbsolutePath() {
        return absolutePath;
    }

    public void setAbsolutePath(String absolutePath) {
        this.absolutePath = absolutePath;
    }

    public String getRelativePath() {
        return relativePath;
    }

    public void setRelativePath(String relativePath) {
        this.relativePath = relativePath;
    }

    public Date getCtime() {
        return ctime;
    }

    public void setCtime(Date ctime) {
        this.ctime = ctime;
    }
}
