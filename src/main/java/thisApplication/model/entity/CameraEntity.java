package thisApplication.model.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Builder
@Table(name = "cameras")
public class CameraEntity {
    @Id
    @Column(name = "id", nullable = false)
    private int id;

    @Column(name = "name")
    private String name;

    @Column(name = "snapshot")
    private String snapshot;

    @Column(name = "room")
    private String room;

    @Column(name = "favorites")
    private boolean favorites;

    @Column(name = "rec")
    private boolean rec;
}
