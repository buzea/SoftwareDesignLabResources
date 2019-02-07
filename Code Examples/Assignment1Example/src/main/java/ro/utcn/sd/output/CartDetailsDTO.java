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
package ro.utcn.sd.output;

import lombok.Data;

import java.util.HashSet;
import java.util.Set;

/**
 * DTO stands for Data Transfer Object.
 * DTOs are used to transfer data from Backend to Frontend.
 * Each DTO should contains the exact data needed to be displayed in the ui. NOTHING MORE, NOTHING LESS!
 * 1 DTO <=> 1 UI Element
 * <p>
 * DTOS can only contain other DTOs or raw data. NEVER put Entities in DTOs
 */
@Data
public class CartDetailsDTO {

    private final Set<ItemDTO> items = new HashSet<>();
    private final String       name;
    private       double       total;
}
