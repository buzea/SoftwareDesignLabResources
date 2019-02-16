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
package ro.utcn.springbootdemo.entities;

import java.util.HashSet;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.NaturalId;
import lombok.Getter;
import lombok.Setter;
import com.google.common.base.Objects;

@Entity
@Getter
@Setter
public class Tag
{
    @Id
    @GeneratedValue(
        strategy = GenerationType.AUTO,
        generator = "native"
    )
    @GenericGenerator(
        name = "native",
        strategy = "native"
    )
    private Long id;

    @Column(nullable = false, unique = true)
    @NaturalId
    private String name;

    @ManyToMany(mappedBy = "tags")
    private Set<Post> postList = new HashSet<>();

    @Override
    public boolean equals(Object o)
    {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        Tag tag = (Tag) o;
        return Objects.equal(id, tag.id) &&
            Objects.equal(name, tag.name);
    }

    @Override
    public int hashCode()
    {
        return Objects.hashCode(id, name);
    }
}
