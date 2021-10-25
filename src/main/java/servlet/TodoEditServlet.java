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

import logic.ToDoItemsModelLogic;
import model.ToDoItemsModel;
import settings.MessageSettings;

/**
 * Servlet implementation class TodoEditServlet
 */
@WebServlet("/TodoEditServlet")
public class TodoEditServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public TodoEditServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 編集内容を取得
		ToDoItemsModel item = new ToDoItemsModel();
		item.setId(Integer.parseInt(request.getParameter("id")));
		item.setUserId(Integer.parseInt(request.getParameter("user_id")));
		item.setTodoItem(request.getParameter("item_name"));
		item.setExpirationDate(java.sql.Date.valueOf(request.getParameter("expire_date")));
		if(request.getParameter("finished") != null) {
			//本日の日付を取得
			Date date = new Date();
			SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
			String strDate = dateFormat.format(date);
			item.setFinishedDate(java.sql.Date.valueOf(strDate));
		}
		
		// 取得内容を登録
		ToDoItemsModelLogic logic = new ToDoItemsModelLogic();
		MessageSettings ms = new MessageSettings();
		int result = logic.update(item);
		
		// 登録が失敗していたらメッセージを
		if(result == ms.EXECUTION_FAILURE) {
			HttpSession session = request.getSession();
			session.removeAttribute("error");
			session.setAttribute("error", ms.MSG_ERROR_OCCURRED);
		}
		
		// 一覧（Main.jsp）に移動
		RequestDispatcher dispatacher = request.getRequestDispatcher("MainServlet");
		dispatacher.forward(request, response);
	}


}
