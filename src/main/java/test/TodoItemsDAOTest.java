package test;

import java.util.ArrayList;
import java.util.List;

import dao.TodoItemsDAO;
import model.ToDoItemsModel;

public class TodoItemsDAOTest {

	public static void main(String[] args) {
		// TODO 自動生成されたメソッド・スタブ
		TodoItemsDAO dao = new TodoItemsDAO();
		ToDoItemsModel todo = new ToDoItemsModel();
		/**
		 * Insert
		 */
/**
		todo.setUserId(3);
		java.sql.Date sqlDate= java.sql.Date.valueOf("2021-10-10");
		todo.setExpirationDate(sqlDate);
		sqlDate= java.sql.Date.valueOf("2021-10-01");
		todo.setRegistrationDate(sqlDate);
		todo.setTodoItem("かきくけこ");
		//実行
		dao.insert(todo);
*/
		
		/**
		 * update
		 */
		//id
/**		todo.setId(1);
		//期限日
		java.sql.Date sqlDate= java.sql.Date.valueOf("2021-10-10");
		todo.setExpirationDate(sqlDate);
		//完了日
		sqlDate= java.sql.Date.valueOf("2021-10-01");
		todo.setFinishedDate(sqlDate);
		//作業者
		todo.setUserId(2);
		//todo
		todo.setTodoItem("さしすせそ");
		
		//実行
		dao.update(todo);
*/
		
		/**
		 * findAll
		 */
		List<ToDoItemsModel> list = new ArrayList<>();
		list = dao.findItems("さ");
		System.out.println(list);
	}

}
