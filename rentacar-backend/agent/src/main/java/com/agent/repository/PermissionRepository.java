package com.agent.repository;

import com.agent.model.Permission;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PermissionRepository  extends JpaRepository<Permission,Long> {

}
