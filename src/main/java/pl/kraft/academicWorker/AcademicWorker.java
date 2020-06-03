package pl.kraft.academicWorker;

import pl.kraft.ability.Ability;
import pl.kraft.project.Project;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "academicWorker")
public class AcademicWorker {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_worker")
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
    @Column(name = "interests", length = 250)
    private String interests;
    @Column(name = "image_url", columnDefinition = "varchar(250) default 'BASIC URL TO PHOTO'")
    private String imageUrl;
    @ManyToMany(mappedBy = "workers", fetch = FetchType.EAGER)
    private List<Ability> abilities = new ArrayList<>();
    @ManyToMany(mappedBy = "workers", fetch = FetchType.LAZY)
    private List<Project> projects = new ArrayList<>();

    public AcademicWorker() {
    }

    public void addAbility(Ability ability) {
        this.abilities.add(ability);
    }

    public void clearAbility() {
        this.abilities = new ArrayList<>();
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

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
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

    public List<Ability> getAbilities() {
        return abilities;
    }

    public void setAbilities(List<Ability> abilities) {
        this.abilities = abilities;
    }

    public List<Project> getProjects() {
        return projects;
    }

    public void setProjects(List<Project> projects) {
        this.projects = projects;
    }
}
