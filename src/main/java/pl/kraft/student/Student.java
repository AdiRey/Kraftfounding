package pl.kraft.student;

import pl.kraft.ability.Ability;
import pl.kraft.file.FileEntity;
import pl.kraft.project.Project;
import pl.kraft.subject.Subject;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "student")
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_student")
    private Long id;
    @Column(name = "email", nullable = false, unique = true, length = 100)
    private String email;
    @Column(name = "login", nullable = false, unique = true, length = 20)
    private String login;
    @Column(name = "password", nullable = false)
    private String password;
    @Column(name = "name", nullable = false, length = 40)
    private String name;
    @Column(name = "surname", nullable = false)
    private String surname;
    @Column(name = "availability", columnDefinition = "integer default 0")
    private Integer availability;
    @Column(name = "interests", length = 250)
    private String interests;
    @Column(name = "image_url", columnDefinition = "varchar(100) default 'icon.png'")
    private String imageUrl;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_subject", nullable = false)
    private Subject subject;
    @ManyToMany(mappedBy = "students", fetch = FetchType.EAGER)
    private List<Ability> abilities = new ArrayList<>();
    @OneToMany(mappedBy = "student", fetch = FetchType.LAZY)
    private List<Project> myProjects = new ArrayList<>();
    @ManyToMany(mappedBy = "studentList", fetch = FetchType.LAZY)
    private List<Project> projects = new ArrayList<>();
    //Lista plików w bazie danych należących do użytkownika
    @OneToMany(mappedBy = "student", fetch = FetchType.LAZY)
    private List<FileEntity> myFiles = new ArrayList<>();

    public Student() {
    }

    public void addAbility(Ability ability) {
        this.abilities.add(ability);
    }

    public void clearAbility() {
        this.abilities = new ArrayList<>();
    }

    public void addMyProjects(Project project) {
        this.myProjects.add(project);
    }

    public void addFile(FileEntity fileEntity) {
        this.myFiles.add(fileEntity);
    }

    public void addProjects(Project project) {
        this.projects.add(project);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public Integer getAvailability() {
        return availability;
    }

    public void setAvailability(Integer availability) {
        this.availability = availability;
    }

    public String getInterests() {
        return interests;
    }

    public void setInterests(String interests) {
        this.interests = interests;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public Subject getSubject() {
        return subject;
    }

    public void setSubject(Subject subject) {
        this.subject = subject;
    }

    public List<Ability> getAbilities() {
        return abilities;
    }

    public void setAbilities(List<Ability> abilities) {
        this.abilities = abilities;
    }

    public List<Project> getMyProjects() {
        return myProjects;
    }

    public void setMyProjects(List<Project> myProjects) {
        this.myProjects = myProjects;
    }

    public List<Project> getProjects() {
        return projects;
    }

    public void setProjects(List<Project> projects) {
        this.projects = projects;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public List<FileEntity> getMyFiles() {
        return myFiles;
    }

    public void setMyFiles(List<FileEntity> myFiles) {
        this.myFiles = myFiles;
    }
}
