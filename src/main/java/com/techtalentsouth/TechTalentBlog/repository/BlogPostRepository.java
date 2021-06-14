package com.techtalentsouth.TechTalentBlog.repository;

import com.techtalentsouth.TechTalentBlog.model.BlogPost;
import org.springframework.data.repository.CrudRepository;

public interface BlogPostRepository extends CrudRepository<BlogPost, Long> {
}
