package it.polito.ezgas.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import exception.InvalidLoginDataException;
import exception.InvalidUserException;
import it.polito.ezgas.converter.UserConverter;
import it.polito.ezgas.dto.IdPw;
import it.polito.ezgas.dto.LoginDto;
import it.polito.ezgas.dto.UserDto;
import it.polito.ezgas.entity.User;
import it.polito.ezgas.repository.UserRepository;
import it.polito.ezgas.service.UserService;

/**
 * Created by softeng on 27/4/2020.
 */
@Service
public class UserServiceimpl implements UserService {

	private UserRepository userRepository;
	
	public UserServiceimpl(UserRepository userRepository) {
		this.userRepository = userRepository;
	}
	
	@Override
	public UserDto getUserById(Integer userId) throws InvalidUserException {
		
		if(userId == null)
			throw new InvalidUserException("Null userId, it must be positive");
		
		if(userId < 0)
			throw new InvalidUserException("Negative userId, it must be positive");
		
		User u = userRepository.findOne(userId);
		
		//check done to avoid null input parameter in UserConverter.toUserDto method
		if(u == null)
			return null;
		
		return UserConverter.toUserDto(u);
	}

	@Override
	public UserDto saveUser(UserDto userDto) {
		List<User> byEmail = userRepository.findByEmail(userDto.getEmail());
		
		// avoid inserting multiple users with same email
		// if the byEmail id is the same as userDto id, it means the user is being
		// updated and it is possible that his email is unchanged
		if(byEmail.size() > 0 && !byEmail.get(0).getUserId().equals(userDto.getUserId()))
			return UserConverter.toUserDto(byEmail.get(0));
						
		return UserConverter.toUserDto(userRepository.save(UserConverter.toUser(userDto)));
	}

	@Override
	public List<UserDto> getAllUsers() {
		return userRepository.findAll()
				.stream()
				.map(UserConverter::toUserDto)
				.collect(Collectors.toList());
	}

	@Override
	public Boolean deleteUser(Integer userId) throws InvalidUserException {
		
		if(userId == null)
			throw new InvalidUserException("Null userId, it must be positive");
		
		if(userId<0)
			throw new InvalidUserException("Negative userId, it must be positive");

		if (userRepository.findOne(userId) == null)
			return false;

		userRepository.delete(userId);
		return true;
	}

	@Override
	public LoginDto login(IdPw credentials) throws InvalidLoginDataException {

		LoginDto result = new LoginDto();
		
		if(credentials == null)
			throw new InvalidLoginDataException("Credentials cannot be null");
		
		List<User> users = userRepository.findByPasswordAndEmail(credentials.getPw(), credentials.getUser());
		
		if(users.size() == 0)
			throw new InvalidLoginDataException("Email or password not correct");
			
		result.setAdmin(users.get(0).getAdmin());
		result.setEmail(users.get(0).getEmail());
		result.setReputation(users.get(0).getReputation());
		result.setUserName(users.get(0).getUserName());
		result.setUserId(users.get(0).getUserId());
		// token must be updated
		
		return result;
	}

	@Override
	public Integer increaseUserReputation(Integer userId) throws InvalidUserException {
		
		if(userId == null)
			throw new InvalidUserException("Null userId, it must be positive");
		
		if(userId < 0)
			throw new InvalidUserException("Negative userId, it must be positive");
		
		User u = userRepository.findOne(userId);
		
		if (u == null)
			throw new InvalidUserException("User doesn't exist");
		
		if(u.getReputation() < 5)
			u.setReputation(u.getReputation()+1);
		
		userRepository.save(u);
		return u.getReputation();
	}

	@Override
	public Integer decreaseUserReputation(Integer userId) throws InvalidUserException {

		if(userId == null)
			throw new InvalidUserException("Null userId, it must be positive");
		
		if(userId < 0)
			throw new InvalidUserException("Negative userId, it must be positive");
		
		User u = userRepository.findOne(userId);
		
		if (u == null)
			throw new InvalidUserException("User doesn't exist");
		
		if(u.getReputation() > -5)
			u.setReputation(u.getReputation()-1);
		
		userRepository.save(u);
		return u.getReputation();
	}
	
}
