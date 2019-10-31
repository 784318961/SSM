package com.service;

import java.util.List;


import com.pojo.Provider;

public interface ProviderService {

	//条件查询供应商列表，分页显示
	public List<Provider> getProviderList_service(String proName,String proCode,Integer currentPageNo,Integer pageSize);

	//条件查询供应商记录数
	public int getProviderCount_service(String proName,String proCode);

	//通过id查询指定供应商信息
	public Provider getThisProvider_service(String id);
	
	//增加供应商信息
	public int addProvider_service(Provider provider);
	
	//修改供应商信息
	public int updateProvider_service(Provider provider);
	
	//删除供应商信息
	public int deleteProvider_service(String id);
}
