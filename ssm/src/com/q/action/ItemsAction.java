package com.q.action;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.q.pojo.Items;
import com.q.pojo.QueryVo;
import com.q.service.ItemsService;

@Controller
@RequestMapping("/item")
public class ItemsAction {
	
	@Resource
	ItemsService itemsService;
	
	@RequestMapping("/list")
	public String list(Model model, Integer id) {
		List<Items> itemList = itemsService.findAll();
		model.addAttribute("itemList", itemList);
		System.out.println(1);
		return "itemList";
	}
	
	@RequestMapping("/itemEdit")
	public ModelAndView itemEdit(Integer id) {
		Items item = itemsService.findItemsByid(id);
		ModelAndView mav = new ModelAndView();
		mav.addObject("item", item);
		mav.setViewName("editItem");
		return mav;
	}
	
	@RequestMapping("/updateitem")
	public String updateitem(Items items) {
		System.out.println(items);
		itemsService.updateItem(items);
		return "redirect:/item/list.action";
	}
	
	@RequestMapping("/queryitem")
	public String queryitem(Model model, QueryVo vo) {
		List<Items> itemList = itemsService.findItemsByNameAndDetail(vo);
		model.addAttribute("itemList", itemList);
		return "itemList";
	}
}
