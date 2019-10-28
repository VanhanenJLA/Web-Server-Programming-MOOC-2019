/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package newtables;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.NamedAttributeNode;
import javax.persistence.NamedEntityGraph;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.jpa.domain.AbstractPersistable;

//@NamedEntityGraph(name = "Course.students",
//        attributeNodes = {@NamedAttributeNode("pankki")})
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor

public class Course extends AbstractPersistable<Long> {
    
    private String name;
    
    @JoinTable(name = "Enrollment")
    @ManyToMany
    private List<Student> students = new ArrayList<>();
}
