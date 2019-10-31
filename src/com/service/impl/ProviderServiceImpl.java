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

	//������ѯ��Ӧ���б���ҳ��ʾ
	@Override
	public List<Provider> getProviderList_service(String proName,
			String proCode, Integer currentPageNo, Integer pageSize) {
		Integer currentPageNo1=(currentPageNo-1)*pageSize;
		return providerMapper.getProviderList_dao(proName, proCode, currentPageNo1, pageSize);
	}

	//������ѯ��Ӧ�̼�¼��
	@Override
	public int getProviderCount_service(String proName, String proCode) {
		return providerMapper.getProviderCount_dao(proName, proCode);
	}

	//ͨ��id��ѯָ����Ӧ����Ϣ
	@Override
	public Provider getThisProvider_service(String id) {
		return providerMapper.getThisProvider_dao(id);
	}

	//���ӹ�Ӧ����Ϣ
	@Override
	public int addProvider_service(Provider provider) {
		return providerMapper.addProvider_dao(provider);
	}

	//�޸Ĺ�Ӧ����Ϣ
	@Override
	public int updateProvider_service(Provider provider) {
		return providerMapper.updateProvider_dao(provider);
	}

	//ɾ����Ӧ����Ϣ
	@Override
	public int deleteProvider_service(String id) {
		return providerMapper.deleteProvider_dao(id);
	}
	
	
}
