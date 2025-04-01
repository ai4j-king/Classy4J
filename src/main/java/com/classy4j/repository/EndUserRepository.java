package com.classy4j.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.classy4j.model.EndUser;

/**
 * 终端用户数据访问层
 */
@Repository
public interface EndUserRepository extends JpaRepository<EndUser, String> {
    
    /**
     * 根据租户ID和应用ID查询用户列表
     *
     * @param tenantId 租户ID
     * @param appId 应用ID
     * @return 用户列表
     */
    List<EndUser> findByTenantIdAndAppId(String tenantId, String appId);
    
    /**
     * 根据租户ID和会话ID查询用户
     *
     * @param tenantId 租户ID
     * @param sessionId 会话ID
     * @return 用户
     */
    Optional<EndUser> findByTenantIdAndSessionId(String tenantId, String sessionId);
    
    /**
     * 根据租户ID和外部用户ID查询用户
     *
     * @param tenantId 租户ID
     * @param externalUserId 外部用户ID
     * @return 用户
     */
    Optional<EndUser> findByTenantIdAndExternalUserId(String tenantId, String externalUserId);
}