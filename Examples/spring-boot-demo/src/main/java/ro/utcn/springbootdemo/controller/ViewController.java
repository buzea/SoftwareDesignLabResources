/*************************************************************************
 * ULLINK CONFIDENTIAL INFORMATION
 * _______________________________
 *
 * All Rights Reserved.
 *
 * NOTICE: This file and its content are the property of Ullink. The
 * information included has been classified as Confidential and may
 * not be copied, modified, distributed, or otherwise disseminated, in
 * whole or part, without the express written permission of Ullink.
 ************************************************************************/
package ro.utcn.springbootdemo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import ro.utcn.springbootdemo.dto.PostTagDTO;
import ro.utcn.springbootdemo.entities.Post;
import ro.utcn.springbootdemo.service.PostsService;

@Controller
public class ViewController {

    @Autowired
    private PostsService postsService;

    @RequestMapping("/login")
    public String login() {
        return "login";
    }

    @RequestMapping({"/index", "/"})
    public String index() {
        return "index";
    }

    @RequestMapping({"/home"})
    public String home(Model model) {
        List<Post> allPosts = postsService.getAllPosts();
        model.addAttribute("posts", allPosts);
        return "home";
    }

    @RequestMapping(path = "/details/{postId}", produces = "text/html")
    public String details(@PathVariable("postId") long postId, Model model) {
        Post post = postsService.getById(postId);
        model.addAttribute("post", post);
        model.addAttribute("tags", post.getTags());
        model.addAttribute("newMapping", new PostTagDTO(post, ""));
        return "post_details";
    }

    @RequestMapping(value = "/createTag", method = RequestMethod.POST)
    public String createTag(@ModelAttribute PostTagDTO newMapping) {
        postsService.addOrCreateTag(newMapping.getPost(), newMapping.getTagName());
        Long postId = newMapping.getPost().getId();
        return "redirect:/details/" + postId;
    }
}