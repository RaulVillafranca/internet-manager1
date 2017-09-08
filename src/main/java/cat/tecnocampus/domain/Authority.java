package cat.tecnocampus.domain;

import javax.persistence.*;

/**
 * Created by internet-manager on 26/07/2017.
 */
@Entity(name = "authorities")
public class Authority {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Version
    private Integer version;

    @OneToOne
    @JoinColumn(name="username", nullable=false)
    private User user;

    private String authority;

    public Authority() {}

    public Authority(User user, String authority) {
        this.user = user;
        this.authority = authority;
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

    public User getUsername() {
        return user;
    }

    public void setUsername(User user) {
        this.user = user;
    }

    public String getAuthority() {
        return authority;
    }

    public void setAuthority(String authority) {
        this.authority = authority;
    }
}
