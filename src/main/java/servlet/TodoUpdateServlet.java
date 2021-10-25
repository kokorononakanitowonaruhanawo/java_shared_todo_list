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
import javax.servlet.http.HttpSession;

import dao.TodoItemsDAO;
import logic.ToDoItemsModelLogic;
import model.ToDoItemsModel;

/**
 * Servlet implementation class TodoUpdateServlet
 */
@WebServlet("/TodoUpdateServlet")
public class TodoUpdateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public TodoUpdateServlet() {
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
		// 編集・削除するtodo_itemを取得
		TodoItemsDAO dao = new TodoItemsDAO();
		ToDoItemsModel item = dao.findOneByID(request.getParameter("item_id"));

		RequestDispatcher dispatcher = null;
		String action = request.getParameter("action");
		if(action.equals("edit")) {
			// sessionにtodo_itemを保存
			HttpSession session = request.getSession();
			session.removeAttribute("item");
			session.setAttribute("item", item);
			// 編集（edit.jsp）にフォワード
			dispatcher = request.getRequestDispatcher("WEB-INF/jsp/todo/edit.jsp");
		} else if(action.equals("delete")) {
			//削除（is_deleted = 1）する
			item.setIsDeleted(1);
			//登録
			ToDoItemsModelLogic toDoItemsModelLogic = new ToDoItemsModelLogic();
			toDoItemsModelLogic.update(item);
			// 一覧へフォワード
			dispatcher = request.getRequestDispatcher("/MainServlet");
		} else if(action.equals("complete")) {
			//本日の日付を取得
			Date date = new Date();
			SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
			String strDate = dateFormat.format(date);
			item.setFinishedDate(java.sql.Date.valueOf(strDate));
			// 登録
			ToDoItemsModelLogic logic = new ToDoItemsModelLogic();
			logic.update(item);
			// 一覧へフォワード
			dispatcher = request.getRequestDispatcher("/MainServlet");
		} else {
			dispatcher = request.getRequestDispatcher("/LoginServlet");
		}
		dispatcher.forward(request, response);
	}

}
