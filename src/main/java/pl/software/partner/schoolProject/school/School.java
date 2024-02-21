package pl.software.partner.schoolProject.school;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import pl.software.partner.schoolProject.child.Child;

import java.util.List;

@Entity
@Getter
@Setter
@Table(name = "School")
@AllArgsConstructor
@NoArgsConstructor
public class School {

    @Id
    @Column(name = "id", nullable = false)
    private Long id;

    private String name;
    private float hourPrice;

    @OneToMany
    @JoinColumn(name = "school_id")
    private List<Child> children;

}
