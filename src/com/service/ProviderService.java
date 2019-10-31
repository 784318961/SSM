package com.service;

import java.util.List;


import com.pojo.Provider;

public interface ProviderService {

	//������ѯ��Ӧ���б���ҳ��ʾ
	public List<Provider> getProviderList_service(String proName,String proCode,Integer currentPageNo,Integer pageSize);

	//������ѯ��Ӧ�̼�¼��
	public int getProviderCount_service(String proName,String proCode);

	//ͨ��id��ѯָ����Ӧ����Ϣ
	public Provider getThisProvider_service(String id);
	
	//���ӹ�Ӧ����Ϣ
	public int addProvider_service(Provider provider);
	
	//�޸Ĺ�Ӧ����Ϣ
	public int updateProvider_service(Provider provider);
	
	//ɾ����Ӧ����Ϣ
	public int deleteProvider_service(String id);
}
