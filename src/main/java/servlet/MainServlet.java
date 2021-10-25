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
import logic.UsersModelLogic;
import model.ToDoItemsModel;
import model.UsersModel;
import settings.MessageSettings;

/**
 * Servlet implementation class MainServlet
 */
@WebServlet("/MainServlet")
public class MainServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MainServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		RequestDispatcher dispatcher;
		
		//todo_itemsを取得し、セッションに保存
		List<ToDoItemsModel> todoItems = new ArrayList<>();
		ToDoItemsModelLogic itemLogic = new ToDoItemsModelLogic();
		todoItems = itemLogic.findAll();
		
		try {
			String str = todoItems.get(0).getTodoItem();
			
			session.removeAttribute("todoItems");
			session.setAttribute("todoItems", todoItems);
			
			// userのリストを作成し、セッションに保存
			List<UsersModel> users = new ArrayList<>();
			UsersModelLogic userLogic = new UsersModelLogic();
			users = userLogic.findAll();
			session.removeAttribute("users");
			session.setAttribute("users", users);
					
			// 今日の日付を取得する（期限日が今日を過ぎているかどうかの判断に使う）。
			java.util.Date today = new java.util.Date();
			request.setAttribute("today", today);
			
			// main画面にフォワード
			dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/todo/main.jsp");
			dispatcher.forward(request, response);
		}
		// 作業が登録されていない場合は、エラー画面（error.jsp）に移動
		catch (IndexOutOfBoundsException e) {
			MessageSettings ms = new MessageSettings();
			session.setAttribute("error", ms.MSG_NO_TODOITEMS);
			dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/todo/error.jsp");
			dispatcher.forward(request, response);
		}
	}

}
