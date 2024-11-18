package com.diegosalazar.literAlura.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.util.List;
import java.util.Map;

@JsonIgnoreProperties(ignoreUnknown = true)
public class BookDto {
    private int id;
    private String title;
    private List<Map<String, String>> authors;
    private List<String> languages;
    private int download_count;

    // Getters y Setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }
    public List<Map<String, String>> getAuthors() { return authors; }
    public void setAuthors(List<Map<String, String>> authors) { this.authors = authors; }
    public List<String> getLanguages() { return languages; }
    public void setLanguages(List<String> languages) { this.languages = languages; }
    public int getDownload_count() { return download_count; }
    public void setDownload_count(int download_count) { this.download_count = download_count; }
}