package com.ddmt.service.impl.district;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ddmt.service.district.DistrictServiceI;
import org.jeecgframework.core.common.service.impl.CommonServiceImpl;

@Service("districtService")
@Transactional
public class DistrictServiceImpl extends CommonServiceImpl implements DistrictServiceI {
	
}