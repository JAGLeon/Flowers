package Tony.Flowers.group.entity;

import Tony.Flowers.group.enums.Rol;
import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;


@Entity
public class Userr {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public Long id;
    public String email;
    public String password;
    public String name;
    
    @Enumerated(EnumType.STRING)
    public Rol rol;
    
    @Temporal(TemporalType.DATE)
    public Date upUser;

    @OneToOne
    public Imagee icon;
    
    public Userr() {
    }

    public Date getUpUser() {
        return upUser;
    }

    public void setUpUser(Date upUser) {
        this.upUser = upUser;
    }

    public Imagee getIcon() {
        return icon;
    }

    public void setIcon(Imagee icon) {
        this.icon = icon;
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

    public Rol getRol() {
        return rol;
    }

    public void setRol(Rol rol) {
        this.rol = rol;
    }

}
