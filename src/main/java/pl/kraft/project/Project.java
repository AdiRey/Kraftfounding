package pl.kraft.project;

import pl.kraft.ability.Ability;
import pl.kraft.academicWorker.AcademicWorker;
import pl.kraft.file.FileEntity;
import pl.kraft.student.Student;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "project")
public class Project {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_project")
    private Long id;
    @Column(name = "title", nullable = false, unique = true, length = 100)
    private String title;
    @Column(name = "description", nullable = false, length = 250)
    private String description;
    @Column(name = "date", nullable = false)
    private LocalDateTime date;
    @Column(name = "limits",columnDefinition = "integer default 10")
    private int limit;
    @Column(name = "completed", columnDefinition = "boolean default false")
    private Boolean completed;
    @ManyToOne(fetch = FetchType.EAGER)
    private Student student;
    @JoinTable(name = "project_student",
            joinColumns = {@JoinColumn(name = "id_project_st", referencedColumnName = "id_project")},
            inverseJoinColumns = {@JoinColumn(name = "id_student_pr", referencedColumnName = "id_student")})
    @ManyToMany(fetch = FetchType.LAZY)
    private List<Student> studentList = new ArrayList<>();
    @ManyToMany(fetch = FetchType.LAZY)
    private List<AcademicWorker> workers = new ArrayList<>();
    @ManyToMany(fetch = FetchType.LAZY)
    private List<Ability> abilities = new ArrayList<>();
    @OneToMany(mappedBy = "project", fetch = FetchType.LAZY)
    private List<FileEntity> files = new ArrayList<>();

    public Project() {
    }

    public void addAbility(Ability ability) {
        this.abilities.add(ability);
    }

    public void clearAbility() {
        this.abilities.clear();
    }

    public void addFile(FileEntity fileEntity) {
        this.files.add(fileEntity);
    }

    public void addStudent(Student student) {
        this.studentList.add(student);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public Integer getLimit() {
        return limit;
    }

    public void setLimit(Integer limit) {
        this.limit = limit;
    }

    public Boolean getCompleted() {
        return completed;
    }

    public void setCompleted(Boolean completed) {
        this.completed = completed;
    }


    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }


    public List<Student> getStudentList() {
        return studentList;
    }

    public void setStudentList(List<Student> studentList) {
        this.studentList = studentList;
    }

    public List<Ability> getAbilities() {
        return abilities;
    }

    public void setAbilities(List<Ability> abilities) {
        this.abilities = abilities;
    }

    public void setLimit(int limit) {
        this.limit = limit;
    }

    public List<AcademicWorker> getWorkers() {
        return workers;
    }

    public void setWorkers(List<AcademicWorker> workers) {
        this.workers = workers;
    }

    public List<FileEntity> getFiles() {
        return files;
    }

    public void setFiles(List<FileEntity> files) {
        this.files = files;
    }
}
