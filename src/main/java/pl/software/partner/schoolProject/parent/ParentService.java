package pl.software.partner.schoolProject.parent;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.software.partner.schoolProject.child.Child;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ParentService {

        private final ParentRepository parentRepository;


        public Parent getParentById(Long id) {
            return parentRepository.findById(id).orElse(null);
        }

        public List<Child> getChildrenByParentId(Long id) {
            return parentRepository.findById(id).orElse(null).getChildren();
        }
        public List<Parent> getParentsBySchool(Long schoolId){
            return parentRepository.findAll().stream()
                    .filter(parent -> parent.getChildren().stream()
                            .anyMatch(child -> child.getSchool().getId().equals(schoolId)))
                    .collect(Collectors.toList());
        }

}
