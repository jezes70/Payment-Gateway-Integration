package com.cyngofokglobal.PaymentGatewayIntegration.repository;

import com.cyngofokglobal.PaymentGatewayIntegration.entity.Role;
import com.cyngofokglobal.PaymentGatewayIntegration.enums.RoleType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role, Long> {
    Optional<Role> findByName(RoleType name);
}
