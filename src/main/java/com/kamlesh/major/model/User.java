package com.kamlesh.major.model;

import lombok.Data;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.annotation.Role;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import java.util.List;

import static javax.persistence.CascadeType.MERGE;

@Entity
@Data
@Table(name="user")
public class User {
    private List<Role> roles;

    public User(User user){
        this.firstName=user.getFirstName();
        this.lastName=user.lastName;
        this.email=user.email;
        this.password=user.password;
        this.roles=user.getRoles();
    }
    public User(){

    }
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)


    private Integer id;

    @NotEmpty
    @Column(nullable=false)
    private String firstName;

    private String lastName;


    @Column(nullable = false,unique = true)
    @NotEmpty
    @Email(message = "{errors.invalid_email}")
    private  String email;

    @NotEmpty
    private  String password;

//    @ManyToMany(cascade = MERGE,fetch = FetchType.EAGER)
//    @JoinTable(name="user_role",joinColumns = {@JoinColumn(name="USER_ID"),referencedColumnName="ID"))},
//inverseJoinColumns={@JoinColumn(name = "ROLE_ID"),referencedColumnName="ID")}















}
