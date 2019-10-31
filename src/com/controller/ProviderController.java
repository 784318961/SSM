package com.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.pojo.Provider;
import com.service.ProviderService;

@Controller
@RequestMapping("/provider")
public class ProviderController {

	@Autowired
	private ProviderService providerService;

	public ProviderService getProviderService() {
		return providerService;
	}

	public void setProviderService(ProviderService providerService) {
		this.providerService = providerService;
	}

	// ҳ����ʾ�Ĵ�����
	@RequestMapping("/sys/providerList.html")
	public String getProviderList(
			@RequestParam(value = "proCode", required = false) String proCode,
			@RequestParam(value = "proName", required = false) String proName,
			@RequestParam(value = "currentPageNo", required = false) Integer currentPageNo,
			Model model) {
		// ����ҳ������
		int pageSize = 5;
		// ��ǰҳ��
		Integer currentPageNoThis = 1;

		if (proCode == null) {
			proCode = "";
		}
		if (proName == null) {
			proName = "";
		}
		if (currentPageNo != null) {
			currentPageNoThis = currentPageNo;
		}
		// �ܼ�¼��
		int totalCount = providerService.getProviderCount_service(proCode,
				proName);
		System.out.println("=====>"+totalCount);
		// ��ҳ��
		int totalPageCount = totalCount % pageSize == 0 ? totalCount / pageSize
				: totalCount / pageSize - 1;
		System.out.println("=====>"+totalPageCount);

		// ������ҳ��βҳ
		if (currentPageNoThis < 1) {
			currentPageNoThis = 1;
		} else if (currentPageNoThis > totalPageCount && totalPageCount!=0) {
			currentPageNoThis = totalPageCount;
		}
		System.out.println("=====>"+currentPageNoThis);

		List<Provider> providerList = providerService.getProviderList_service(
				proName, proCode, currentPageNoThis, pageSize);

		model.addAttribute("providerList", providerList);
		model.addAttribute("proCode", proCode);
		model.addAttribute("proName", proName);
		model.addAttribute("totalPageCount", totalPageCount);
		model.addAttribute("totalCount", totalCount);
		model.addAttribute("currentPageNo", currentPageNoThis);

		return "providerList";
	}
}
