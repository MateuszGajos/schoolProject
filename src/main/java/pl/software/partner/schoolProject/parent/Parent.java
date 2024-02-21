package pl.software.partner.schoolProject.parent;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import pl.software.partner.schoolProject.child.Child;

import java.util.List;

@Entity
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Parent {

    @Id
    @Column(name = "id", nullable = false)
    private Long id;
    private String firstname;
    private String lastname;

    @OneToMany
    @JoinColumn(name = "parent_id")
    private List<Child> children;

    public String getFullName() {
        return firstname + " " + lastname;
    }
}
