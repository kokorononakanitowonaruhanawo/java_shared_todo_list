package servlet;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import logic.ToDoItemsModelLogic;
import model.ToDoItemsModel;
import settings.MessageSettings;

/**
 * Servlet implementation class TodoRegisterServlet
 */
@WebServlet("/TodoRegisterServlet")
public class TodoRegisterServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public TodoRegisterServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// 登録画面（entry.jsp）に移動
		RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/jsp/todo/entry.jsp");
		dispatcher.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// エラーメッセージ
		String[] errMsg = new String[2];
		MessageSettings ms = new MessageSettings();
		RequestDispatcher dispatcher;
		
		// 入力データを取得
		ToDoItemsModel item = new ToDoItemsModel();
		// user_id
		item.setUserId(Integer.parseInt(request.getParameter("user_id")));
		// 項目
		String itemName = request.getParameter("item_name");
		if(itemName.isBlank()) {	// 注：isBlankは配列には使えない
			errMsg[0] = ms.MSG_REQUIRED;
		} else if(itemName.length() > 50) {
			errMsg[0] = ms.MSG_LENGTH_LONG;
		} else {
			item.setTodoItem(request.getParameter("item_name"));
		}
		// 期限日
		String ed = request.getParameter("expire_date");
		if(ed.isBlank()) {
			errMsg[1] = ms.MSG_REQUIRED;
		} else {
			item.setExpirationDate(java.sql.Date.valueOf(ed));
		}
		
		// 入力に不備がある場合
		if(errMsg[0] != null | errMsg[1] != null) {
			request.setAttribute("error", errMsg);
			// 登録（entry.jsp）にフォワード
			dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/todo/entry.jsp");
			dispatcher.forward(request, response);
			return;
		}
				
		//登録日（本日の日付）
		Date date = new Date();
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		String strDate = dateFormat.format(date);
		item.setRegistrationDate(java.sql.Date.valueOf(strDate));
		// 完了
		if(request.getParameter("finished") != null) {
			item.setFinishedDate(java.sql.Date.valueOf(strDate));
		}
		
		// 登録
		ToDoItemsModelLogic logic = new ToDoItemsModelLogic();
		int result = logic.register(item);
		
		if(result == ms.EXECUTION_FAILURE) {
			request.setAttribute("error", ms.MSG_ERROR_OCCURRED);
			// 登録（error.jsp）にフォワード
			dispatcher = request.getRequestDispatcher("WERB-INF/jsp/todo/error.jsp");
			dispatcher.forward(request, response);
			return;
		}
		
		// 一覧（main.jsp）にフォワード
		dispatcher = request.getRequestDispatcher("/MainServlet");
		dispatcher.forward(request, response);
	}

}
