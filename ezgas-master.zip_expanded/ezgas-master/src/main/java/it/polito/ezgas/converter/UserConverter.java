package it.polito.ezgas.converter;

import it.polito.ezgas.dto.UserDto;
import it.polito.ezgas.entity.User;

public class UserConverter {
	public static UserDto toUserDto(User user) {
		
		UserDto userDto = new UserDto();
		userDto.setAdmin(user.getAdmin());
		userDto.setEmail(user.getEmail());
		userDto.setPassword(user.getPassword());
		userDto.setReputation(user.getReputation());
		userDto.setUserId(user.getUserId());
		userDto.setUserName(user.getUserName());
		
		return userDto;
	}
	
	public static User toUser(UserDto userDto) {
		
		User user = new User();
		user.setAdmin(userDto.getAdmin());
		user.setEmail(userDto.getEmail());
		user.setPassword(userDto.getPassword());
		user.setReputation(userDto.getReputation());
		user.setUserName(userDto.getUserName());
		user.setUserId(userDto.getUserId());
		
		return user;
		
	}
}
