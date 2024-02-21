package pl.software.partner.schoolProject.child;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ChildService {

    private final ChildRepository childRepository;

    public Child getChildById(Long id) {
        return childRepository.findById(id).orElse(null);
    }

}
