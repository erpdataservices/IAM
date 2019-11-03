package com.erpdata.iam.accessmanagement;

import org.springframework.data.jpa.repository.JpaRepository;

public interface PageAccessInfoRepository extends JpaRepository<PageAccessInfo, Long> {
	PageAccessInfo getByPageIdAndRoleId(Long roleId, Long pageId);
}
