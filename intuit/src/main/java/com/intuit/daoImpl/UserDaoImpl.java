package com.intuit.daoImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import com.intuit.dao.UserDao;
import com.intuit.model.User;

@Repository
public class UserDaoImpl implements UserDao {

	@Autowired
	private MongoTemplate mongoTemplate;

	@Override
	public Boolean findByUsername(String userName) {
		Query query = new Query();
		query.addCriteria(Criteria.where("userName").is(userName));
		User response = mongoTemplate.findOne(query, User.class);
		if(response != null){
			return true;
		}
		return false;
	}

	@Override
	public User saveUser(User userEntity) {
		mongoTemplate.save(userEntity);
		return userEntity;
	}

	@Override
	public User loginUser(String userName, String password) {
		Query query = new Query();
		query.addCriteria(Criteria.where("userName").is(userName).and("password").is(password));
		User userResponse = mongoTemplate.findOne(query, User.class);
		return userResponse;
	}


}
