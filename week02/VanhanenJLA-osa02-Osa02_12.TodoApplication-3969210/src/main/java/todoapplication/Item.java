/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package todoapplication;

import javax.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.jpa.domain.AbstractPersistable;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author Jouni
 */

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Item extends AbstractPersistable<Long> {
 
    private String name;
 
}
