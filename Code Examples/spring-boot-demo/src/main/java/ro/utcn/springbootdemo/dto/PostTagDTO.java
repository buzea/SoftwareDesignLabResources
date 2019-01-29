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
package ro.utcn.springbootdemo.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import ro.utcn.springbootdemo.entities.Post;

/**
 * Data Transfer Object used to create a new Tag to Post mapping
 */
@Data
@AllArgsConstructor
public class PostTagDTO
{
    private Post post;
    private String tagName;
}
