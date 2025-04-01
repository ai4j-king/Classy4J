package com.classy4j.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.classy4j.model.EndUser;
import com.classy4j.repository.EndUserRepository;

/**
 * 终端用户服务
 */
@Service
public class EndUserService {
    
    private final EndUserRepository endUserRepository;
    
    public EndUserService(EndUserRepository endUserRepository) {
        this.endUserRepository = endUserRepository;
    }
    
    /**
     * 创建终端用户
     *
     * @param endUser 终端用户
     * @return 创建的终端用户
     */
    @Transactional
    public EndUser createEndUser(EndUser endUser) {
        return endUserRepository.save(endUser);
    }
    
    /**
     * 根据ID查询终端用户
     *
     * @param id 用户ID
     * @return 终端用户
     */
    public Optional<EndUser> getEndUserById(String id) {
        return endUserRepository.findById(id);
    }
    
    /**
     * 根据租户ID和应用ID查询用户列表
     *
     * @param tenantId 租户ID
     * @param appId 应用ID
     * @return 用户列表
     */
    public List<EndUser> getEndUsersByTenantIdAndAppId(String tenantId, String appId) {
        return endUserRepository.findByTenantIdAndAppId(tenantId, appId);
    }
    
    /**
     * 根据租户ID和会话ID查询用户
     *
     * @param tenantId 租户ID
     * @param sessionId 会话ID
     * @return 用户
     */
    public Optional<EndUser> getEndUserByTenantIdAndSessionId(String tenantId, String sessionId) {
        return endUserRepository.findByTenantIdAndSessionId(tenantId, sessionId);
    }
    
    /**
     * 根据租户ID和外部用户ID查询用户
     *
     * @param tenantId 租户ID
     * @param externalUserId 外部用户ID
     * @return 用户
     */
    public Optional<EndUser> getEndUserByTenantIdAndExternalUserId(String tenantId, String externalUserId) {
        return endUserRepository.findByTenantIdAndExternalUserId(tenantId, externalUserId);
    }
    
    /**
     * 更新终端用户
     *
     * @param endUser 终端用户
     * @return 更新后的终端用户
     */
    @Transactional
    public EndUser updateEndUser(EndUser endUser) {
        return endUserRepository.save(endUser);
    }
}