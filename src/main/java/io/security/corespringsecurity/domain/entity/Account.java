package io.security.corespringsecurity.domain.entity;

import io.security.corespringsecurity.domain.entity.auth.Role;
import io.security.corespringsecurity.domain.entity.board.Bookmark;
import io.security.corespringsecurity.domain.entity.board.Comment;
import io.security.corespringsecurity.domain.entity.profile.Notification;
import lombok.*;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.springframework.security.core.GrantedAuthority;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Data
@Table(name = "account")
@ToString(exclude = {"userRoles", "experience"})
@Builder
@EqualsAndHashCode(of = "id")
@AllArgsConstructor

public class Account implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name ="account_id")
    private Long id;

    @Column
    private String username;

    @Column
    private String email;

    @Column
    private int age;

    @Column
    private String password;

    private String filename;

    private String filepath;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "experience_id")
    private Experience experience;

    @ManyToMany(fetch = FetchType.LAZY, cascade={CascadeType.ALL})
    @JoinTable(name = "user_roles", joinColumns = { @JoinColumn(name = "user_id") }, inverseJoinColumns = {
            @JoinColumn(name = "role_id") })
    private Set<Role> userRoles = new HashSet<>();

    @OneToMany(mappedBy = "account", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Set<Notification> notifications = new HashSet<>();

    public Account() {
        // 기타 초기화 작업...

        // Notification 생성 및 연결
        Notification notification = new Notification("Welcome to our platform!");
        notification.setCheck(false);
        notification.setAccount(this);
        this.notifications.add(notification);

        Experience experience = new Experience();
        experience.setAccount(this);
    }



//    @OneToMany(mappedBy = "account", fetch = FetchType.EAGER, cascade = CascadeType.REMOVE)
//    private List<Comment> comments;

//    @OneToMany(mappedBy = "account", fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
//    private List<Bookmark> bookmarks;

}


