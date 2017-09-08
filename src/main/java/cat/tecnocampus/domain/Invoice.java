package cat.tecnocampus.domain;

import javax.persistence.*;
import java.sql.Date;

/**
 * Created by internet-manager on 19/07/2017.
 */
@Entity
public class Invoice {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Version
    private Integer version;

    private Date date;

    @ManyToOne
    @JoinColumn(name="contract_id", nullable=false)
    private Contract contract;

    @ManyToOne
    @JoinColumn(name="resident_id", nullable=false)
    private Resident resident;

    private Double subtotal;

    private Double vat;

    private Double total;

    private Boolean payed;

    public Invoice() {}

    public Invoice(Date date, Contract contract, Resident resident, Double subtotal, Double vat) {
        this.date = date;
        this.contract = contract;
        this.resident = resident;
        this.subtotal = subtotal * (1 - (vat/100));
        this.vat = vat;
        this.total = subtotal;
        this.payed = false;
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

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Contract getContract() {
        return contract;
    }

    public void setContract(Contract contract) {
        this.contract = contract;
    }

    public Resident getResident() {
        return resident;
    }

    public void setResident(Resident resident) {
        this.resident = resident;
    }

    public Double getSubtotal() {
        return round(subtotal, 2);
    }

    public void setSubtotal(Double subtotal) {
        this.subtotal = subtotal;
    }

    public Double getVat() {
        return vat;
    }

    public void setVat(Double vat) {
        this.vat = vat;
    }

    public Double getTotal() {
        return round(total, 2);
    }

    public void setTotal(Double total) {
        this.total = total;
    }

    public Boolean getPayed() {
        return payed;
    }

    public void setPayed(Boolean payed) {
        this.payed = payed;
    }

    private static double round(double value, int places) {
        if (places < 0) throw new IllegalArgumentException();

        long factor = (long) Math.pow(10, places);
        value = value * factor;
        long tmp = Math.round(value);
        return (double) tmp / factor;
    }
}
