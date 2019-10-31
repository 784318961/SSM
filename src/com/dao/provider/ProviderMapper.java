package com.dao.provider;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.pojo.Provider;

public interface ProviderMapper {

	//������ѯ��Ӧ���б���ҳ��ʾ
	public List<Provider> getProviderList_dao(@Param("proName") String proName,@Param("proCode") String proCode,@Param("currentPageNo") Integer currentPageNo,@Param("pageSize") Integer pageSize);

	//������ѯ��Ӧ�̼�¼��
	public int getProviderCount_dao(@Param("proName") String proName,@Param("proCode") String proCode);

	//ͨ��id��ѯָ����Ӧ����Ϣ
	public Provider getThisProvider_dao(@Param("id") String id);
	
	//���ӹ�Ӧ����Ϣ
	public int addProvider_dao(Provider provider);
	
	//�޸Ĺ�Ӧ����Ϣ
	public int updateProvider_dao(Provider provider);
	
	//ɾ����Ӧ����Ϣ
	public int deleteProvider_dao(@Param("id") String id);
}
