package com.courses.api.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.courses.api.entity.StudentEntity;
import com.courses.api.entity.UserEntity;
import com.courses.api.repository.StudentRepository;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
	
	@Autowired
	StudentRepository studentRepository;
	
	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		StudentEntity student = studentRepository.findByEmail(email);
		
		if(student == null){
			throw new UsernameNotFoundException("Usuario não encontrado!");
		}
		return new UserEntity(student.getId(), student.getEmail(), student.getPass(), student.getRoles());
	}

}
