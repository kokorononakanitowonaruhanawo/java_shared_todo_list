package logic;

import java.util.List;
import java.util.regex.Pattern;

import org.mindrot.jbcrypt.BCrypt;

import dao.UsersDAO;
import model.UsersModel;
import settings.MessageSettings;

public class UsersModelLogic {
	
	/**
	 * ログイン
	 * @param UsersModel
	 * @return UsersModel
	 */
	public UsersModel login(UsersModel user) {
		UsersDAO dao = new UsersDAO();
		String hashedPass = BCrypt.hashpw(user.getPass(), BCrypt.gensalt());
		return dao.search(user.getName(), user.getPass());
	}
	
	
	/**
	 * 全てのユーザーを取得
	 * @return List<UsersModel>
	 */
	public List<UsersModel> findAll() {
		UsersDAO dao = new UsersDAO();
		List<UsersModel> list = dao.findAll();
		return list;
	}
	
	
	
	/**
	 * ユーザー登録
	 * @param UsersModel
	 * @return DB_EXECUTION_SUCCESS または DB_EXECUTION_FAILURE
	 */
	public int register(UsersModel user) {
		UsersDAO dao = new UsersDAO();
		String hashedPass = BCrypt.hashpw(user.getPass(), BCrypt.gensalt());
		user.setPass(hashedPass);
		int result = dao.insert(user);
		return result;
	}
	
	
	/**
	 * ユーザー情報変更
	 * @param UsersModel
	 * @return DB_EXECUTION_SUCCESS または DB_EXECUTION_FAILURE
	 */
	public int update(UsersModel user) {
		UsersDAO dao = new UsersDAO();
		String hashedPass = BCrypt.hashpw(user.getPass(), BCrypt.gensalt());
		user.setPass(hashedPass);
		int result = dao.update(user);
		return result;
	}
	
	
	/**
	 * Emailのチェック
	 * @param String email
	 * @return EXECUTION_SUCCESS または EXECUTION_FAILURE
	 */
	public String checkEmail(String email) {
		String pattern = "^([a-zA-Z0-9])+([a-zA-Z0-9\\._-])*@([a-zA-Z0-9_-])+([a-zA-Z0-9\\._-]+)+$";
		MessageSettings ms = new MessageSettings();
		List<UsersModel> list = findAll();
		//255文字以上の場合
		
		// 空白の場合
		if(email.isBlank()) {
			return ms.MSG_REQUIRED;
		}
		// すでに使用されている場合
		for(UsersModel user : list) {
			if(user.getEmail().equals(email)) {
				return ms.MSG_ER_DUP_KEYNAME;
			}
		}
		// 正規表現に一致した
		Pattern p = Pattern.compile(pattern);
		if(p.matcher(email).find()) {
			return ms.MSG_VALID;
		}
		// その他
		return ms.MSG_INVALID_FORMAT;
	}
	
	
	
	/**
	 * パスワードのチェック
	 * @param String チェックするパスワード
	 * @return String
	 */
	public String checkPass(String pass) {
		String pattern = "^[A-Za-z0-9]+$";
		MessageSettings ms = new MessageSettings();
		// パスワードが8文字より短い、20文字より長い
		if(pass.length() < 8 | pass.length() > 20) {
			return ms.MSG_PASSWORD_FAILURE;
		}
		// 正規表現に一致した
		Pattern p = Pattern.compile(pattern);
		if(p.matcher(pass).find()) {
			return ms.MSG_VALID;
		}
		return ms.MSG_PASSWORD_FAILURE;
	}
	
	
	
	/**
	 * 氏名のチェック
	 * @param String チェックする氏名
	 * @return String
	 */
	public String checkName(String name) {
		MessageSettings ms = new MessageSettings();
		if(name.isBlank()) {
			return ms.MSG_REQUIRED;
		}
		if(name.length() > 50) {
			return ms.MSG_LENGTH_LONG;
		}
		// 既に使われているか
		List<UsersModel> list = findAll();
		for(UsersModel user : list) {
			if(user.getName().equals(name)) {
				return ms.MSG_ER_DUP_KEYNAME;
			}
		}
		return ms.MSG_VALID;
	}
}
