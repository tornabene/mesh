package com.gentics.cailun.core.data.service;

import org.springframework.data.neo4j.conversion.Result;

import com.gentics.cailun.core.data.model.auth.Group;
import com.gentics.cailun.core.data.model.auth.User;
import com.gentics.cailun.core.data.service.generic.GenericNodeService;
import com.gentics.cailun.core.rest.user.response.UserResponse;
import com.gentics.cailun.path.PagingInfo;

public interface UserService extends GenericNodeService<User> {

	void setPassword(User user, String password);

	User findByUsername(String username);

	Result<User> findAllVisible(User user, PagingInfo pagingInfo);

	UserResponse transformToRest(User user);

	boolean removeUserFromGroup(User user, Group group);

}
