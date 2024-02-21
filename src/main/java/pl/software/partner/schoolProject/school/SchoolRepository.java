package pl.software.partner.schoolProject.school;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.software.partner.schoolProject.school.School;

@Repository
public interface SchoolRepository extends JpaRepository<School, Long> {

}
