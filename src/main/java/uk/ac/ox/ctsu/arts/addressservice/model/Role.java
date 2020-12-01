package uk.ac.ox.ctsu.arts.addressservice.model;

import org.hibernate.envers.Audited;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;


import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;


@Entity
@Audited
public class Role {

    @Id
    @Column
    private String id; // maps to Azure MIP role

    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinTable(
            name = "roles_permissions",
            joinColumns = @JoinColumn(name = "role_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "permission_id", referencedColumnName = "id")
    )
    private Set<Permission> permissions = new HashSet<>();


    @Column
    @CreatedDate
    private LocalDateTime changedWhen;
    @Column
    @CreatedBy
    private String changedWho;

    public Role(String id) {
        this.id = id;
    }
    public Role() {
    }
    public void setId(String id) {
        this.id = id;
    }

    public void setPermissions(Set<Permission> permissions) {
        this.permissions = permissions;
    }

    public Set<Permission> getPermissions() {
        return permissions;
    }
}
