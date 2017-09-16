package cat.tecnocampus.domain;

import javax.persistence.*;

/**
 * Created by vil883 on 23/04/2017.
 */
@Entity
public class Resident {
    /* Indicamos a Spring-Data (JPA) que este campo es un campo ID en Base de datos */
    /* Indicamos a Spring-Data (JPA) que es autoincrement */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    /* Control de versionado, si a futuro cambiamos las entidades tenemos control de cuando se ha creado un registro, es decir en que
   versión de base de datos se ha añadido. */
    @Version
    private Integer version;

    private String nif;

    private String name;

    private String surname;

    private String secondSurname;

    private String floor;

    private String door;

    private String stairsRoute;

    private String phone;

    @Column(unique = true)
    private String email;

    @ManyToOne
    @JoinColumn(name="community_id", nullable=false)
    private Community community;

    private Boolean president;

    private String password;

    private Boolean active = true;

    public Resident() {}


    public Resident(String nif, String name, String surname, String secondSurname, String floor, String door, String stairsRoute, String phone, String email, Community community, Boolean president, String password) {
        this.nif = nif;
        this.name = name;
        this.surname = surname;
        this.secondSurname = secondSurname;
        this.floor = floor;
        this.door = door;
        this.stairsRoute = stairsRoute;
        this.phone = phone;
        this.email = email;
        this.community = community;
        this.president = president;
        this.password = password;

    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getVersion() {
        return version;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }

    public String getNif() {
        return nif;
    }

    public void setNif(String nif) {
        this.nif = nif;
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

    public String getSecondSurname() {
        return secondSurname;
    }

    public void setSecondSurname(String secondSurname) {
        this.secondSurname = secondSurname;
    }

    public String getFloor() {
        return floor;
    }

    public void setFloor(String floor) {
        this.floor = floor;
    }

    public String getDoor() {
        return door;
    }

    public void setDoor(String door) {
        this.door = door;
    }

    public String getStairsRoute() {
        return stairsRoute;
    }

    public void setStairsRoute(String stairsRoute) {
        this.stairsRoute = stairsRoute;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Community getCommunity() {
        return community;
    }

    public void setCommunity(Community community) {
        this.community = community;
    }

    public Boolean getPresident() {
        return president;
    }

    public void setPresident(Boolean president) {
        this.president = president;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

}

