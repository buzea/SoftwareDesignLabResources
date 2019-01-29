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
import ro.utcn.springbootdemo.entities.Tag;

public interface TagRepository extends CrudRepository<Tag, Long>
{
    Optional<Tag> findOneByName(String name);
}
