/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package newtables;

import java.util.List;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author Jouni
 */
public interface CourseRepository extends JpaRepository<Course, Long> {
//    @EntityGraph(value = "Course.students")
//    List<Course> findByIdNotNull();
}
