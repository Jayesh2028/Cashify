package com.cashify.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.cashify.model.User;

public interface UserRepository extends JpaRepository<User, Long> {

	public User findByUserName(String userName);

	@Query(value = "from User u where u.userName=:userName and u.password=:password")
	public User getDynamicUserList(@Param("userName") String userName, @Param("password") String password);

	@Query(value = "select u from User u where u.userName=:userName")
	public List<User> findByUserNametoken(@Param("userName") String userName);
}
