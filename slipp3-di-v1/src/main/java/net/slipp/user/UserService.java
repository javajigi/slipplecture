package net.slipp.user;

import java.sql.SQLException;
import java.util.List;

public class UserService {
    private UserDao userDao;
    
    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }
    
	public void create(User user) throws SQLException, ExistedUserException {
		if (findUser(user.getUserId()) != null) {
			throw new ExistedUserException(user.getUserId() + "는 이미 존재하는 아이디입니다.");
		}

		userDao.create(user);
	}

	public void update(User user) throws SQLException {
		userDao.update(user);
	}

	public void remove(String userId) throws SQLException {
		userDao.remove(userId);
	}

	public User findUser(String userId) throws SQLException {
		return userDao.findUser(userId);
	}

	public List<User> findUserList() throws SQLException {
		return userDao.findUserList();
	}

	public boolean login(String userId, String password) throws SQLException, PasswordMismatchException {
		User user = findUser(userId);
		if (!user.isMatchPassword(password)) {
			throw new PasswordMismatchException("비밀번호가 일치하지 않습니다.");
		}
		return true;
	}
}
