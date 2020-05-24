package pl.kraft.ability;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import pl.kraft.project.Project;
import pl.kraft.student.Student;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "ability")
public class Ability {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_ability")
    private Long id;
    @Column(name = "ability", nullable = false, unique = true, length = 200)
    private String ability;
    @JoinTable(name = "student_ability",
            joinColumns = {@JoinColumn(name = "id_ability_st", referencedColumnName = "id_ability")},
            inverseJoinColumns = {@JoinColumn(name = "id_student_ab", referencedColumnName = "id_student")})
    @ManyToMany(fetch = FetchType.LAZY)
    @Fetch(FetchMode.SELECT)
    private List<Student> students = new ArrayList<>();
    @JoinTable(name = "project_student",
            joinColumns = {@JoinColumn(name = "id_ability_proj", referencedColumnName = "id_ability")},
            inverseJoinColumns = {@JoinColumn(name = "id_project_ab", referencedColumnName = "id_project")})
    @ManyToMany(fetch = FetchType.LAZY)
    @Fetch(FetchMode.SELECT)
    private List<Project> projects = new ArrayList<>();

    public Ability() {
    }

    public void addStudent(Student student) {
        this.students.add(student);
    }

    public void addProject(Project project) {
        this.projects.add(project);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAbility() {
        return ability;
    }

    public void setAbility(String ability) {
        this.ability = ability;
    }

    public List<Student> getStudents() {
        return students;
    }

    public void setStudents(List<Student> students) {
        this.students = students;
    }

    public List<Project> getProjects() {
        return projects;
    }

    public void setProjects(List<Project> projects) {
        this.projects = projects;
    }
}
