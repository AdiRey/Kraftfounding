package pl.kraft.file;

import pl.kraft.project.Project;
import pl.kraft.student.Student;

import javax.persistence.*;

@Entity
@Table(name = "file")
public class FileEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_file")
    private Long id;
    @Column(name = "name", nullable = false, unique = true)
    private String name;
    @ManyToOne(fetch = FetchType.EAGER)
    private Student student;
    @ManyToOne(fetch = FetchType.EAGER)
    private Project project;

    public FileEntity(){
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public Project getProject() {
        return project;
    }

    public void setProject(Project project) {
        this.project = project;
    }
}
