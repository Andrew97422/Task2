package thisApplication.model.entity.door;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "doors")
@Builder
public class DoorEntity {
    @Id
    @Column(name = "id")
    private int id;

    @Column(name = "name")
    private String name;

    @Column(name = "room")
    private String room;

    @Column(name = "favorites")
    private boolean favorites;

    @Column(name = "snapshot")
    String snapshot;
}
