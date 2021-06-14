package com.techtalentsouth.TechTalentBlog.controller;

import com.techtalentsouth.TechTalentBlog.model.BlogPost;
import com.techtalentsouth.TechTalentBlog.repository.BlogPostRepository;
import org.apache.coyote.Request;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Controller
public class BlogPostController {

    @Autowired
    private BlogPostRepository blogPostRepository;
    private static List<BlogPost> posts = new ArrayList<>();

//    @RequestMapping(value="/", method=RequestMethod.GET)
    @GetMapping(value="/")
    public String index(BlogPost blobPost, Model model) {
        posts.removeAll(posts);
        for(BlogPost post : blogPostRepository.findAll()) {
            posts.add(post);
        }
        model.addAttribute("posts", posts);
        return "blogpost/index";
    }

//    private BlogPost blogPost;
    @PostMapping(value="/blogposts")
    public String addNewBlogPost(BlogPost blogPost, Model model) {
//        blogPostRepository.save(new BlogPost(blogPost.getTitle(), blogPost.getAuthor(), blogPost.getBlogEntry()));
        System.out.println(blogPost);
        blogPostRepository.save(blogPost);
        model.addAttribute("title", blogPost.getTitle());
        model.addAttribute("author", blogPost.getAuthor());
        model.addAttribute("blogEntry", blogPost.getBlogEntry());
        return "blogpost/result";
    }

    @GetMapping(value = "/blogposts/new")
    public String newBlog (BlogPost blogPost) {
        return "blogpost/new";
    }

    @DeleteMapping(value = "/blogposts/{id}")
    public String deletePostWithId(@PathVariable(value = "id") Long postId){
//        BlogPost blogPost = blogPostRepository.findById(postId)
//                .orElseThrow(() -> new ResourceNotFoundException("Post not found for this id :: " + postId));
        blogPostRepository.deleteById(postId);
        return "blogpost/result";
    }

    @RequestMapping(value="/blogposts/{id}", method=RequestMethod.GET)
    public String editPostWithId(@PathVariable Long id, BlogPost blogPost, Model model) {
        Optional<BlogPost> post = blogPostRepository.findById(id);
        if(post.isPresent()){
            BlogPost actualPost = post.get();
            model.addAttribute("blogPost", actualPost);
        }
        return "blogpost/edit";
    }

    @RequestMapping(value="/blogposts/update/{id}")
    public String updateExistingPost(@PathVariable Long id, BlogPost blogPost, Model model) {
        Optional<BlogPost> post = blogPostRepository.findById(id);
        if(post.isPresent()) {
            BlogPost actualPost = post.get();
            actualPost.setTitle(blogPost.getTitle());
            actualPost.setAuthor(blogPost.getAuthor());
            actualPost.setBlogEntry(blogPost.getBlogEntry());
            blogPostRepository.save(actualPost);
            model.addAttribute("blogPost", actualPost);
        }
        return "blogpost/result";
    }
}
