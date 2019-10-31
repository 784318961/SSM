package com.dao.provider;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.pojo.Provider;

public interface ProviderMapper {

	//条件查询供应商列表，分页显示
	public List<Provider> getProviderList_dao(@Param("proName") String proName,@Param("proCode") String proCode,@Param("currentPageNo") Integer currentPageNo,@Param("pageSize") Integer pageSize);

	//条件查询供应商记录数
	public int getProviderCount_dao(@Param("proName") String proName,@Param("proCode") String proCode);

	//通过id查询指定供应商信息
	public Provider getThisProvider_dao(@Param("id") String id);
	
	//增加供应商信息
	public int addProvider_dao(Provider provider);
	
	//修改供应商信息
	public int updateProvider_dao(Provider provider);
	
	//删除供应商信息
	public int deleteProvider_dao(@Param("id") String id);
}
