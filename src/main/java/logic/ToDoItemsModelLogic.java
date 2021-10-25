package logic;

import java.util.List;

import dao.TodoItemsDAO;
import model.ToDoItemsModel;
import settings.DatabaseSettings;
import settings.MessageSettings;

public class ToDoItemsModelLogic {
	DatabaseSettings dbs = new DatabaseSettings();
	MessageSettings ms = new MessageSettings();
	TodoItemsDAO dao = new TodoItemsDAO();
	
	/**
	 * ToDoItemsを全て取得
	 * @return List<ToDoItemsModel>
	 */
	public List<ToDoItemsModel> findAll() {
		List<ToDoItemsModel> list = dao.findAll();
		return list;
	}
	
	/**
	 * ToDoItemsModelを編集する
	 *  @param ToDoItemsModel
	 *  @return EXECUTION_SUCCESS か EXECUTION_FAILURE
	 */
	public int update(ToDoItemsModel item) {
		
		// update実行
//		TodoItemsDAO dao = new TodoItemsDAO();
		int result = dao.update(item);
		
		if(result == dbs.DB_EXECUTION_SUCCESS) {
			return ms.EXECUTION_SUCCESS;
		} else {
			return ms.EXECUTION_FAILURE;
		}
	}
	
	/**
	 * ToDoItemsModelを登録する
	 * @param ToDoItemsModel
	 * @return EXECUTION_SUCCESS か EXECUTION_FAILURE
	 */
	public int register(ToDoItemsModel item) {
		
		// register実行
		int result = dao.insert(item);
		
		if(result == dbs.DB_EXECUTION_SUCCESS) {
			return ms.EXECUTION_SUCCESS;
		} else {
			return ms.EXECUTION_FAILURE;
		}
	}
	
	
	/**
	 * ToDoItemsModelを検索する
	 * @param String (検索文字：項目名か担当者)
	 * @return List<ToDoItemsModel> 
	 */
	public List<ToDoItemsModel> search(String strSearch) {
		List<ToDoItemsModel> list = dao.findItems(strSearch);
		return list;
	}
}
