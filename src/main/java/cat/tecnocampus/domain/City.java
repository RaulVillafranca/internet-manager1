package cat.tecnocampus.domain;

import javax.persistence.*;

/**
 * Created by internet-manager on 11/04/17.
 */
@Entity
public class City {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Version
    private Integer version;

    private String name;

    private String postalcode;

    private String province;

    public City() {}

    public City(Integer id, Integer version, String name, String postalcode, String province) {
        this.id = id;
        this.version = version;
        this.name = name;
        this.postalcode = postalcode;
        this.province = province;
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPostalcode() {
        return postalcode;
    }

    public void setPostalcode(String postalcode) {
        this.postalcode = postalcode;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }
}
