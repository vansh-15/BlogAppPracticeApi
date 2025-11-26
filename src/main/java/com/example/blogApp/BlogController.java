package com.example.blogApp;

import com.example.blogApp.model.Blog;
import com.example.blogApp.service.BlogServic;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class BlogController {

    @Autowired
    private BlogServic service;

    @GetMapping("/blogs")
    public ResponseEntity<List<Blog>> getAllBlogs(){
        return new ResponseEntity<>(service.getBlogs(),HttpStatus.OK);
    }

    @GetMapping("/blog/{id}")
    public ResponseEntity<Blog> getBlogById(@PathVariable int id){
        return new ResponseEntity<>(service.getById(id),HttpStatus.OK);
    }

    @PostMapping("/blog")
    public ResponseEntity<Blog> addBlog(@RequestBody Blog blog){
        return new ResponseEntity<>(service.addBlog(blog),HttpStatus.ACCEPTED);
    }

    @PutMapping("/blog/{id}")
    public ResponseEntity<Blog> editBlog(@PathVariable int id, @RequestBody Blog blog){
        Blog existing=service.getById(id);

        if(existing==null){
            Blog b=null;
            return new ResponseEntity<>(b,HttpStatus.NOT_FOUND);
        }
        existing.setTitle(blog.getTitle());
        existing.setContent(blog.getContent());
        existing.setCategory(blog.getCategory());
        existing.setTags(blog.getTags());

        return new ResponseEntity<>(service.editBlog(existing),HttpStatus.OK);
    }

    @DeleteMapping("/blog/{id}")
    public ResponseEntity<String> deleteBlog(@PathVariable int id){
        Blog blog=service.getById(id);
        if(blog!=null){
            service.deleteBlog(id);
            return new ResponseEntity<>("Deleted",HttpStatus.OK);
        }
        return new ResponseEntity<>("Not Found",HttpStatus.NOT_FOUND);
    }

}
