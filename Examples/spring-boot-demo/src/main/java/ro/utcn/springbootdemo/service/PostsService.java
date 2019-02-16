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
package ro.utcn.springbootdemo.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ro.utcn.springbootdemo.entities.Post;
import ro.utcn.springbootdemo.entities.Tag;
import ro.utcn.springbootdemo.repository.PostRepository;
import ro.utcn.springbootdemo.repository.TagRepository;
import com.google.common.collect.Lists;

@Service
public class PostsService
{
    @Autowired
    private PostRepository repository;
    @Autowired
    private TagRepository tagRepository;

    public List<Post> getAllPosts()
    {
        return Lists.newArrayList(repository.findAll());
    }

    public Post getById(long id)
    {
        return repository.findOneWithTagsById(id).orElseThrow(() -> new IllegalArgumentException("Post not found. Id:" + id));
    }

    public void addOrCreateTag(Post post, String tagName)
    {
        Tag tag = tagRepository.findOneByName(tagName).orElseGet(() -> createTag(tagName));
        post.add(tag);
        repository.save(post);
    }

    private Tag createTag(String tagName)
    {
        Tag tag = new Tag();
        tag.setName(tagName);
        return tag;
    }
}
