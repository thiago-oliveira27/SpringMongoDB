package com.spring.mongo.service;

import java.beans.PropertyDescriptor;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spring.mongo.domain.Post;
import com.spring.mongo.domain.User;
import com.spring.mongo.dtos.UserDTO;
import com.spring.mongo.repository.UserRepository;
import com.spring.mongo.service.exception.ObjectNotFoundException;

@Service
public class UserService {

	@Autowired
	public UserRepository userRepository;

	public User fromDTO(UserDTO userDTO) {
		return new User(userDTO.getId(), userDTO.getName(), userDTO.getEmail());
	}

	public List<User> findAll() {
		return userRepository.findAll();
	}

	public User findById(String id) {
		Optional<User> obj = userRepository.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado"));
	}

	public User insert(User user) {
		return userRepository.insert(user);
	}

	public void deleteUser(String id) {
		findById(id);
		userRepository.deleteById(id);
	}

	public User updateUser(User newUserData) {
		
        Optional<User> originalUser = userRepository.findById(newUserData.getId());

        if (originalUser.isPresent()) {
            User existingUser = originalUser.get();
            copyNonNullProperties(newUserData, existingUser);
            return userRepository.save(existingUser);
        }
        throw new ObjectNotFoundException("User not found");
    }

    private void copyNonNullProperties(Object src, Object target) {
        BeanUtils.copyProperties(src, target, getNullPropertyNames(src));
    }

    private String[] getNullPropertyNames(Object source) {
        final BeanWrapper src = new BeanWrapperImpl(source);
        PropertyDescriptor[] pds = src.getPropertyDescriptors();

        Set<String> emptyNames = new HashSet<>();
        for (PropertyDescriptor pd : pds) {
            Object srcValue = src.getPropertyValue(pd.getName());
            if (srcValue == null) emptyNames.add(pd.getName());
        }
        String[] result = new String[emptyNames.size()];
        return emptyNames.toArray(result);
    }
}
