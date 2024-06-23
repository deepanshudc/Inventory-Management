package com.project.Inventory.Management.App.controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.project.Inventory.Management.App.entity.Authorities;
import com.project.Inventory.Management.App.entity.Item;
import com.project.Inventory.Management.App.entity.ItemCategory;
import com.project.Inventory.Management.App.entity.Users;
import com.project.Inventory.Management.App.model.ItemModel;
import com.project.Inventory.Management.App.model.UserModel;
import com.project.Inventory.Management.App.repository.ItemCategoryRepository;
import com.project.Inventory.Management.App.service.ItemService;
import com.project.Inventory.Management.App.service.UserService;
import com.project.Inventory.Management.App.util.DateUtil;

import jakarta.validation.Valid;



@Controller
@RequestMapping("/inventory")
public class MainController {
	
	@Autowired
	public ItemService service;
	
	@Autowired
	public ItemCategoryRepository categoryRepo;
	
	@Autowired
	public UserService userService;

	

	@RequestMapping("/")
	public String homepage(Model model) {
		String date = DateUtil.getFormattedDate();
	    model.addAttribute("date", date);
		return "index";
	}
	
	//add User
	
	@GetMapping("/addUser")
	public String addUser(Model model) {
		UserModel user= new UserModel();
		String date = DateUtil.getFormattedDate();
	    model.addAttribute("date", date);
		model.addAttribute("user",user);
		
		//list of users to show
		List<Users> usersList=userService.getAllUsers();
		List<Authorities> authorityList=userService.getAllAuthorities();
		List<UserModel> listUserModel= new ArrayList<UserModel>();
		for(int i=0;i<usersList.size();i++) {
			Users userToPut=usersList.get(i);
			Authorities authorityToPut=authorityList.get(i);
			UserModel newUser=new UserModel();
			newUser.setUserName(userToPut.getUsername());
			newUser.setAuthority(authorityToPut.getAuthority());
			
			listUserModel.add(newUser);			
		}
		model.addAttribute("listUserModel",listUserModel);
		return "add-user";
		
	}
	
	@PostMapping("/addUser")
	public String processAddUser(@Valid @ModelAttribute("user") UserModel user	,BindingResult theBindingResult, Model model) throws Exception {
		if(theBindingResult.hasErrors()) {
			return "add-user";
		}
		else {
		
			Users userFound=userService.findUser(user.getUserName());
			if(userFound!=null) {
	            model.addAttribute("userExists", "User already exists");
	    		String date = DateUtil.getFormattedDate();
	    	    model.addAttribute("date", date);
	    		model.addAttribute("user",user);
				return "add-user";
			}
			else {
			try {
				userService.addNewUser(user);
				System.out.println("User added");
			}catch (Exception e) {
				// TODO: handle exception
				throw new Exception("New user not able to add");
			}
				return "redirect:/inventory/";
		}
		}
	}
	
	@GetMapping("/user/delete/{userName}")
	public String deleteUser(@PathVariable("userName") String userName) {
		userService.deleteByID(userName);
		return "redirect:/inventory/addUser";
	}
	
	
	
	
	@GetMapping("/items")
	public String getItemList(Model model) throws Exception {
		
		String date = DateUtil.getFormattedDate();
	    model.addAttribute("date", date);

		List<Item> itemsList = service.getAllItemList();
		if(itemsList==null) {
			throw new Exception("List of Employee not found");
		}
		model.addAttribute("itemList",itemsList);
	
		return "itemList" ;
	}
	
	
	//make new item add
	@GetMapping("/items/addItems")
	public String showItemForm(Model theModel) {
		List<ItemCategory> categoryList=service.getItemCategoryList();
		String date = DateUtil.getFormattedDate();
		theModel.addAttribute("date", date);

		ItemModel item=new ItemModel();
		theModel.addAttribute("item",item);
		theModel.addAttribute("category",categoryList);

		return "show-form.html";
		
	}
	
	@PostMapping("/items/addItems")
	public String addItem(@Valid @ModelAttribute("item") ItemModel item	,BindingResult theBindingResult,Model model, Authentication authentication) {
			System.out.println(authentication);
		System.out.println(item.toString());
			if(theBindingResult.hasErrors()) {
				  List<ItemCategory> categoryList = service.getItemCategoryList();
			        model.addAttribute("category", categoryList);
				return "show-form";
			}
			else {
				 Item itemToAdd=new Item(item);
				 if(service.getItem(itemToAdd.getSKU())!=null) {
					 return "redirect:/inventory/items";
				 }
				 else {
					 service.addItem(itemToAdd);
						System.out.println("Item added");

						return "redirect:/inventory/items";
				 }
					
			}
	}
	
	//search item
	@GetMapping("/items/search")
	 public String searchItem(@RequestParam("itemSearch") String term, Model model) {
		List<Item> itemSearchList=service.searchedItemList(term);
		if(itemSearchList.isEmpty()) {
			List<Item> itemsList = service.getAllItemList();
			model.addAttribute("itemList",itemsList);
			model.addAttribute("searchMessage",new String("No Items found of this keyword "));
		}
		else {
			model.addAttribute("searchMessage",new String("Found list of items having keyword '"+term)+"' :");

			model.addAttribute("itemList",itemSearchList);	
		}
		return "itemList";
		
		
		
	}
	
	//update item
			
	@GetMapping("/items/edit/{itemSKU}")
	public String getItem(Model theModel, @PathVariable("itemSKU") String itemSKU) {
				Item itemFound = service.getItem(itemSKU);
				String date = DateUtil.getFormattedDate();
				theModel.addAttribute("date", date);

				List<ItemCategory> categoryList=service.getItemCategoryList();
				theModel.addAttribute("categoryList",categoryList);

				theModel.addAttribute("item",itemFound);
				return "edit-form";
				
	}
	
	@PutMapping("/items/edit/processUpdateItem")
	public String updateEmployee(@Valid @ModelAttribute("item") Item item) {
		service.updateItem(item);

	return "redirect:/inventory/items";
	}
	
	@GetMapping("/items/delete/{itemSKU}")
	public String deleteItem(@PathVariable("itemSKU") String SKU) {
		service.deleteBySKU(SKU);
		return "redirect:/inventory/items";
	}	
	
	
	// Category name APIs
	
	@GetMapping("/items/itemCategory")
	public String showItemCategory(Model theModel) {
		List<ItemCategory> categoryList=service.getItemCategoryList();
		String date = DateUtil.getFormattedDate();
		theModel.addAttribute("date", date);

		theModel.addAttribute("categoryList",categoryList);
		theModel.addAttribute("itemCategory", new ItemCategory());
		return "item-category";
	}
	
	
		
	
	@PostMapping("/items/itemCategory")
	public String addItemCategoryName(@Valid @ModelAttribute("itemCategory") ItemCategory itemCategory) {
		
		
		Optional<ItemCategory> checkCategoryName=categoryRepo.findById(itemCategory.getCategoryName());
		if(checkCategoryName.isPresent()) {
			System.out.println("Category already exist/added in database");
			return "redirect:/inventory/items/itemCategory";
		}
		else {
			try {
				categoryRepo.save(itemCategory);
			}
			catch (Exception e) {
				// TODO: handle exception
				System.out.println("Error:"+ e);
				return "redirect:/inventory/items/itemCategory";

			}
			System.out.println("category Added");
			return "redirect:/inventory/items/itemCategory";
			
		}
	
		
	}
	
	@GetMapping("/items/delete/itemCategory/{categoryName}")
	public String deleteCategoryName(@PathVariable("categoryName") String categoryName ) {
		try {
			categoryRepo.deleteById(categoryName);
			System.out.println("Deleted Category name : "+categoryName);
			return "redirect:/inventory/items/itemCategory";
		} catch (Exception e) {
			System.out.println(e);
			return "redirect:/inventory/items/itemCategory";

		}
	}



}
