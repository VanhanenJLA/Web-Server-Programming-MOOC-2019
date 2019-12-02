package reloadheroes;

import java.io.Serializable;
import javax.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.jpa.domain.AbstractPersistable;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
public class ReloadStatus extends AbstractPersistable<Long>
        implements Comparable<ReloadStatus>, Serializable {

    private String name;
    private Integer reloads;

    @Override
    public int compareTo(ReloadStatus other) {
        return other.getReloads() - reloads;
    }

}
