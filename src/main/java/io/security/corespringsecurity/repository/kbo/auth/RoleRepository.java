package io.security.corespringsecurity.repository.kbo.auth;


import io.security.corespringsecurity.domain.entity.auth.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Long> {

    Role findByRoleName(String name);

    @Override
    void delete(Role role);

}
