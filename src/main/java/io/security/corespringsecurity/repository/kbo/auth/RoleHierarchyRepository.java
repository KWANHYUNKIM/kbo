package io.security.corespringsecurity.repository.kbo.auth;


import io.security.corespringsecurity.domain.entity.auth.RoleHierarchy;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleHierarchyRepository extends JpaRepository<RoleHierarchy, Long> {

    RoleHierarchy findByChildName(String roleName);
}
