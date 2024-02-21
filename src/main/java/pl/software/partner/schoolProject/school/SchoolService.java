package pl.software.partner.schoolProject.school;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.software.partner.schoolProject.child.Child;
import pl.software.partner.schoolProject.school.School;
import pl.software.partner.schoolProject.school.SchoolRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SchoolService {

    private final SchoolRepository schoolRepository;

    public List<School> getSchools() {
        return schoolRepository.findAll();
    }
    public School getSchoolById(Long id) {
        return schoolRepository.findById(id).orElse(null);
    }


    public List<Child> getChildrenBySchoolId(Long schoolId) {
        return schoolRepository.findById(schoolId).orElse(null).getChildren();
    }
}
