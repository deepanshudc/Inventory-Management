package com.project.Inventory.Management.App.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.project.Inventory.Management.App.entity.Authorities;
import com.project.Inventory.Management.App.entity.Users;
import com.project.Inventory.Management.App.model.UserModel;
import com.project.Inventory.Management.App.repository.AuthorityRepository;
import com.project.Inventory.Management.App.repository.UsersRepository;

import jakarta.transaction.Transactional;

@Service
public class UserService {
	
	@Autowired
	public UsersRepository userRepo;
	
	@Autowired
	public AuthorityRepository authorityRepo;
	
	@Autowired
	private BCryptPasswordEncoder passwordEncoder;
	
	@Transactional
	public void  addNewUser(UserModel userModel) {
		
		Users userEntity=new Users();
		Authorities authority= new Authorities();
		userEntity.setUsername(userModel.getUserName());
		String userPassword=userModel.getPassword();
		String encodedPassword=passwordEncoder.encode(userPassword);
		userEntity.setPassword(encodedPassword);
		authority.setUsername(userModel.getUserName());
		authority.setAuthority(userModel.getAuthority());
		
		userRepo.save(userEntity);
		authorityRepo.save(authority);
		
	}
	
	public Users findUser(String username) {
		
		Optional<Users> foundUser=userRepo.findById(username);
		if(foundUser.isPresent()) {
			return foundUser.get();
		}
		else {
			System.out.println("User Not found ");
			return null;
		}
		
	}

	public List<Users> getAllUsers() {
		// TODO Auto-generated method stub	
		return userRepo.findAll();
	}

	public List<Authorities> getAllAuthorities() {
		// TODO Auto-generated method stub
		return authorityRepo.findAll();
	}

	@Transactional
	public void deleteByID(String userName) {
		// TODO Auto-generated method stub
	 userRepo.deleteById(userName);
	 authorityRepo.deleteById(userName);
	 System.out.println("user deleted");
		
	}

}
