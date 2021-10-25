package servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import logic.ToDoItemsModelLogic;
import model.ToDoItemsModel;
import settings.MessageSettings;

/**
 * Servlet implementation class TodoSeachServlet
 */
@WebServlet("/TodoSearchServlet")
public class TodoSearchServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public TodoSearchServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		RequestDispatcher dispatcher = null;
		
		// 検索文字を取得
		String strSearch = request.getParameter("search");
		
		//todo_itemsを検索
		List<ToDoItemsModel> todoItems = new ArrayList<>();
		ToDoItemsModelLogic itemLogic = new ToDoItemsModelLogic();
		todoItems = itemLogic.search(strSearch);
		
		try {
			String str = todoItems.get(0).getTodoItem();
			
			//リクエストパラメーターにに保存
			request.setAttribute("search", todoItems);
			
			// 今日の日付を取得する（期限日が今日を過ぎているかどうかの判断に使う）。
			java.util.Date today = new java.util.Date();
			request.setAttribute("today", today);
			
			// 検索結果画面（main.jsp）へフォワード
			dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/todo/search.jsp");
			dispatcher.forward(request, response);
		} 
		//検索結果が見つからない場合
		catch (IndexOutOfBoundsException e) {
			// エラーメッセージ
			MessageSettings ms = new MessageSettings();
			session.removeAttribute("error");
			session.setAttribute("error", ms.MSG_NO_SEARCH);
			// エラー画面（error.jsp）にフォワード
			dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/todo/error.jsp");
			dispatcher.forward(request, response);
		}
	}

}
