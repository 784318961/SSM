package com.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;

import com.dao.provider.ProviderMapper;
import com.pojo.Provider;
import com.service.ProviderService;

@Controller
@Service
public class ProviderServiceImpl implements ProviderService{

	@Autowired
	private ProviderMapper providerMapper;

	public ProviderMapper getProviderMapper() {
		return providerMapper;
	}

	public void setProviderMapper(ProviderMapper providerMapper) {
		this.providerMapper = providerMapper;
	}

	//条件查询供应商列表，分页显示
	@Override
	public List<Provider> getProviderList_service(String proName,
			String proCode, Integer currentPageNo, Integer pageSize) {
		Integer currentPageNo1=(currentPageNo-1)*pageSize;
		return providerMapper.getProviderList_dao(proName, proCode, currentPageNo1, pageSize);
	}

	//条件查询供应商记录数
	@Override
	public int getProviderCount_service(String proName, String proCode) {
		return providerMapper.getProviderCount_dao(proName, proCode);
	}

	//通过id查询指定供应商信息
	@Override
	public Provider getThisProvider_service(String id) {
		return providerMapper.getThisProvider_dao(id);
	}

	//增加供应商信息
	@Override
	public int addProvider_service(Provider provider) {
		return providerMapper.addProvider_dao(provider);
	}

	//修改供应商信息
	@Override
	public int updateProvider_service(Provider provider) {
		return providerMapper.updateProvider_dao(provider);
	}

	//删除供应商信息
	@Override
	public int deleteProvider_service(String id) {
		return providerMapper.deleteProvider_dao(id);
	}
	
	
}
