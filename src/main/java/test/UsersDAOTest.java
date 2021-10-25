package test;

import dao.UsersDAO;
import model.UsersModel;

public class UsersDAOTest {

	public static void main(String[] args) {
		UsersDAO dao = new UsersDAO();
		/**
		 * insert
		 */
/**
		//user設定
		UsersModel user = new UsersModel();
		user.setEmail("aaa@aaa.com");
		user.setPass("aaa");
		user.setName("あああ");
		
		//登録
		
		dao.insert(user);
	
*/
		/**
		 * findall
		 */
/**		
		List<UsersModel> list = dao.findAll();
		for(UsersModel user : list) {
			System.out.println(user);
		}
*/
		
		/**
		 * update
		 */
		//user設定
		UsersModel user = new UsersModel();
		user.setId(2);
		user.setEmail("ccc@ccc.com");
		user.setPass("ccc");
		user.setName("あううう");
		user.setIsDeleted(1);
		dao.update(user);
	}
}
