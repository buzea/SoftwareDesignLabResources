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
package ro.utcn.springbootdemo.repository;

import java.util.Optional;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import ro.utcn.springbootdemo.entities.Post;

public interface PostRepository extends CrudRepository<Post, Long>
{
    //    @Query("select post from Post post left join fetch post.tags where post.id =:id")
    Optional<Post> findOneWithTagsById(@Param("id") Long id);
}
