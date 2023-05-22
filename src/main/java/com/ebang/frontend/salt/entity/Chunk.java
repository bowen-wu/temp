package com.ebang.frontend.salt.entity;

import java.io.Serializable;
import java.util.Date;

public class Chunk implements Serializable {
    private int id;
    private String name;
    private String creatorName;
    private String filePath;
    private int productId;
    private int isPublished;
    private int creatorId;
    private int enable;
    private int status;
    private Date createdAt;
    private Date updatedAt;

    public Chunk() {
    }

    public Chunk(int id, String name, String creatorName, String filePath, int productId, int isPublished, int creatorId, int enable, int status, Date createdAt, Date updatedAt) {
        this.id = id;
        this.name = name;
        this.creatorName = creatorName;
        this.filePath = filePath;
        this.productId = productId;
        this.isPublished = isPublished;
        this.creatorId = creatorId;
        this.enable = enable;
        this.status = status;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCreatorName() {
        return creatorName;
    }

    public void setCreatorName(String creatorName) {
        this.creatorName = creatorName;
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public int getIsPublished() {
        return isPublished;
    }

    public void setIsPublished(int isPublished) {
        this.isPublished = isPublished;
    }

    public int getCreatorId() {
        return creatorId;
    }

    public void setCreatorId(int creatorId) {
        this.creatorId = creatorId;
    }

    public int getEnable() {
        return enable;
    }

    public void setEnable(int enable) {
        this.enable = enable;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public Date getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
    }
}
