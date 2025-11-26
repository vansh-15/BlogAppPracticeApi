package com.example.blogApp.service;

import com.example.blogApp.model.Blog;
import com.example.blogApp.repository.BlogRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BlogServic {

    @Autowired
    private BlogRepo repo;

    public List<Blog> getBlogs() {
        return repo.findAll();
    }

    public Blog getById(int id) {
        return repo.findById(id).orElse(null);
    }

    public Blog addBlog(Blog blog) {
        return repo.save(blog);
    }

    public Blog editBlog(Blog blog) {
        return repo.save(blog);
    }

    public void deleteBlog(int id) {
        repo.deleteById(id);
    }
}
