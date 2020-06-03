package pl.kraft.security;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import pl.kraft.academicWorker.AcademicWorker;
import pl.kraft.academicWorker.AcademicWorkerRepository;
import pl.kraft.student.Student;
import pl.kraft.student.StudentRepository;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Component
public class CustomUserDetailsService implements UserDetailsService {

    private final StudentRepository studentRepository;
    private final AcademicWorkerRepository academicWorkerRepository;

    public CustomUserDetailsService(StudentRepository studentRepository, AcademicWorkerRepository academicWorkerRepository) {
        this.studentRepository = studentRepository;
        this.academicWorkerRepository = academicWorkerRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<Student> foundStudent;

        if (username.contains("@")) {
            foundStudent = studentRepository.findByEmail(username);
        } else {
            foundStudent = studentRepository.findByLogin(username);
        }

        if (foundStudent.isPresent()) {
            Student student = foundStudent.get();
            return new org.springframework.security.core.userdetails.User(
                    student.getEmail(),
                    student.getPassword(),
                    convertAuthorities(List.of("student", "prz", "kraft"))
            );
        }

        Optional<AcademicWorker> foundWorker;

        if (username.contains("@")) {
            foundWorker = academicWorkerRepository.findByEmail(username);
        } else {
            foundWorker = academicWorkerRepository.findByLogin(username);
        }

        if (foundWorker.isPresent()) {
            AcademicWorker academicWorker = foundWorker.get();
            return new org.springframework.security.core.userdetails.User(
                    academicWorker.getEmail(),
                    academicWorker.getPassword(),
                    convertAuthorities(List.of("worker", "prz", "knowledge"))
            );
        }

        throw new UsernameNotFoundException("Student not found");
    }

    private Set<GrantedAuthority> convertAuthorities(List<String> authorityList) {
        Set<GrantedAuthority> authorities = new HashSet<>();
        for (String auth : authorityList) {
            authorities.add(new SimpleGrantedAuthority(auth));
        }
        return authorities;
    }
}
