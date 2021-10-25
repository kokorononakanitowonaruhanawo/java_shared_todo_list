package model;

import java.io.Serializable;

/**
 *	USERSモデル
 */
public class UsersModel implements Serializable {
	private int id;
	private String email;
	private String pass;
	private String name;
	private int is_deleted;
	
	public UsersModel() {
		//	処理なし
	}
	
	//	id
	public int getId() {return this.id;}
	public void setId(int id) {
		this.id = id;
	}
	
	//	email
	public String getEmail() {return this.email;}
	public void setEmail(String email) {
		this.email = email;
	}
	
	//	pass
	public String getPass() {return this.pass;}
	public void setPass(String pass) {
		this.pass = pass;
	}
	
	//	name
	public String getName() {return this.name;}
	public void setName(String name) {
		this.name = name;
	}
	
	//	is_deleted
	public int getIsDeleted() {return this.is_deleted;}
	public void setIsDeleted(int is_deleted) {
		this.is_deleted = is_deleted;
	}

}
