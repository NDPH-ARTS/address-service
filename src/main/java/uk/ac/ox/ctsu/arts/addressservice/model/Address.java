package uk.ac.ox.ctsu.arts.addressservice.model;

import org.hibernate.envers.Audited;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Audited
public class Address {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "address_sequence")
    @SequenceGenerator(name = "address_sequence", sequenceName = "address_sequence", allocationSize = 1)
    @Column
    private Long id;

    @Column
    private String line1;
    @Column
    private String line2;
    @Column
    private String line3;
    @Column
    private String line4;
    @Column
    private String line5;
    @Column
    private String postalCode;
    @Column
    private String postalCountry;

    @Column
    @CreatedDate
    private LocalDateTime changedWhen;
    @Column
    @CreatedBy
    private String changedWho;

    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    public String getPostalCountry() {
        return postalCountry;
    }

    public void setPostalCountry(String postalCountry) {
        this.postalCountry = postalCountry;
    }

    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }

    public String getLine1() {
        return line1;
    }

    public void setLine1(String line1) {
        this.line1 = line1;
    }

    public String getLine2() {
        return line2;
    }

    public void setLine2(String line2) {
        this.line2 = line2;
    }

    public String getLine3() {
        return line3;
    }

    public void setLine3(String line3) {
        this.line3 = line3;
    }

    public String getLine4() {
        return line4;
    }

    public void setLine4(String line4) {
        this.line4 = line4;
    }

    public String getLine5() {
        return line5;
    }

    public void setLine5(String line5) {
        this.line5 = line5;
    }

    public LocalDateTime getChangedWhen() {
        return changedWhen;
    }

    public void setChangedWhen(LocalDateTime changedWhen) {
        this.changedWhen = changedWhen;
    }

    public String getChangedWho() {
        return changedWho;
    }

    public void setChangedWho(String changedWho) {
        this.changedWho = changedWho;
    }
}
