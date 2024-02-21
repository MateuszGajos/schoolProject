package pl.software.partner.schoolProject.attendance;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import pl.software.partner.schoolProject.child.Child;

import java.time.Duration;
import java.time.LocalDateTime;

@Entity
@Getter
@NoArgsConstructor
public class Attendance {
    @Id
    @Column(name = "id", nullable = false)
    private Long id;

    private LocalDateTime entryDate;
    private LocalDateTime exitDate;
    @Transient
    @Setter
    private Duration timeSpentInSchool;
    
    @ManyToOne
    @JoinColumn(name = "child_id")
    @JsonIgnore
    private Child child;

    public Duration getTimeSpentInSchool() {
        return Duration.between(entryDate, exitDate).abs();
    }

    public Attendance(Child child, LocalDateTime entryDate, LocalDateTime exitDate) {
        this.child = child;
        this.entryDate = entryDate;
        this.exitDate = exitDate;
    }

}
