package model;

import java.io.Serializable;
import java.sql.Date;
import java.sql.Timestamp;
/**
 *	todo list モデル
 */
public class ToDoItemsModel implements Serializable {
	private int id;
	private int user_id;
	private Date registration_date;
	private Date expiration_date;
	private Date finished_date;
	private String todo_item;
	private int is_deleted;
	private Timestamp created_at;
	private Timestamp update_at;
	private UsersModel users;
	
	public ToDoItemsModel() {
		//	処理なし
	}
	
	//	id
	public int getId() {return this.id;}
	public void setId(int id) {
		this.id = id;
	}
	
	//	user_id
	public int getUserId() {return this.user_id;}
	public void setUserId(int user_id) {
		this.user_id = user_id;
	}
	
	//	registration_date
	public Date getRegistrationDate() {return this.registration_date;}
	public void setRegistrationDate(Date registration_date) {
		this.registration_date = registration_date;
	}
	
	//expiration_date
	public Date getExpirationDate() {return expiration_date;}
	public void setExpirationDate(Date expiration_date) {
		this.expiration_date = expiration_date;
	}
	
	//	finished_date
	public Date getFinishedDate() {return this.finished_date;}
	public void setFinishedDate(Date finished_date) {
		this.finished_date = finished_date;
	}
	
	//	todo_item
	public String getTodoItem() {return this.todo_item;}
	public void setTodoItem(String todo_item) {
		this.todo_item = todo_item;
	}
	
	//	is_deleted
	public int getIsDeleted() {return this.is_deleted;}
	public void setIsDeleted(int is_deleted) {
		this.is_deleted = is_deleted;
	}
	
	//	created_at
	public Timestamp getCreatedAt() {return this.created_at;}
	public void setCreatedAt(Timestamp created_at) {
		this.created_at = created_at;
	}
	
	//	update_at
	public Timestamp getUpdateAt() {return this.update_at;}
	public void setUpdateAt(Timestamp update_at) {
		this.update_at = update_at;
	}
	
	// UsersModel
	public UsersModel getUsersModel() {return this.users;}
	public void setUsersModel(UsersModel users) {
		this.users = users;
	}
}
