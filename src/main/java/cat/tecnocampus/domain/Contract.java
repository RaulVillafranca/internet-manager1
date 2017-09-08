package cat.tecnocampus.domain;

import javax.persistence.*;
import java.sql.Date;

/**
 * Created by internet-manager on 10/07/2017.
 */
@Entity
public class Contract {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Version
    private Integer version;

    private String name;

    private Date dateStart;

    private Boolean active;

    private Double monthlyPrice;

    @ManyToOne
    @JoinColumn(name="community_id", nullable=false)
    private Community community;

    @ManyToOne
    @JoinColumn(name="provider_id", nullable=false)
    private Provider provider;

    public Contract() {}

    public Contract(String name, Date dateStart, Boolean active, Double monthlyPrice, Community community, Provider provider) {
        this.name = name;
        this.dateStart = dateStart;
        this.active = active;
        this.monthlyPrice = monthlyPrice;
        this.community = community;
        this.provider = provider;
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

    public Date getDateStart() {
        return dateStart;
    }

    public void setDateStart(Date dateStart) {
        this.dateStart = dateStart;
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    public Community getCommunity() {
        return community;
    }

    public void setCommunity(Community communityId) {
        this.community = communityId;
    }

    public Double getMonthlyPrice() {
        return monthlyPrice;
    }

    public void setMonthlyPrice(Double monthlyPrice) {
        this.monthlyPrice = monthlyPrice;
    }

    public Provider getProvider() {
        return provider;
    }

    public void setProvider(Provider provider) {
        this.provider = provider;
    }
}
