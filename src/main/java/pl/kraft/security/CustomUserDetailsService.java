package pl.kraft.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import pl.kraft.student.Student;
import pl.kraft.student.StudentRepository;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Component
public class CustomUserDetailsService implements UserDetailsService {

    private final StudentRepository studentRepository;

    @Autowired
    public  CustomUserDetailsService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<Student> foundOne;
        if (username.contains("@")) {
            foundOne = studentRepository.findByEmail(username);
        } else {
            foundOne = studentRepository.findByLogin(username);
        }

        if (foundOne.isEmpty())
            throw new UsernameNotFoundException("Student not found");

        Student student = foundOne.get();
        return new org.springframework.security.core.userdetails.User(
                        student.getEmail(),
                        student.getPassword(),
                        convertAuthorities(List.of("student", "prz", "kraft")) // TODO STUDENT 1/1, ACADEMIC WORKER 0/1
                );
    }

    private Set<GrantedAuthority> convertAuthorities(List<String> authorityList) {
        Set<GrantedAuthority> authorities = new HashSet<>();
        for (String auth : authorityList) {
            authorities.add(new SimpleGrantedAuthority(auth));
        }
        return authorities;
    }
}
