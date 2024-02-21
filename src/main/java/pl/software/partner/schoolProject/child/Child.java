package pl.software.partner.schoolProject.child;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import pl.software.partner.schoolProject.attendance.Attendance;
import pl.software.partner.schoolProject.parent.Parent;
import pl.software.partner.schoolProject.school.School;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Entity
@Getter
@Setter
public class Child  {

    @Id
    @Column(nullable = false)
    private Long id;
    private String firstname;
    private String lastname;

    @Setter
    @ManyToOne
    @JoinColumn(name = "parent_id")
    private Parent parent;

    @Setter
    @ManyToOne
    @JoinColumn(name = "school_id")
    private School school;

    @OneToMany(mappedBy = "child")
    private List<Attendance> attendances;

}
