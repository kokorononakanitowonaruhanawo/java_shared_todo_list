package servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import logic.UsersModelLogic;
import model.UsersModel;
import settings.MessageSettings;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// ログイン画面にフォワード
		RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/jsp/login/index.jsp");
		dispatcher.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// ログインの入力データを取得
		UsersModel user = new UsersModel();
		user.setName(request.getParameter("user"));
		user.setPass(request.getParameter("password"));
		
		//ログインに成功したか失敗したか
		UsersModelLogic logic = new UsersModelLogic();
		UsersModel login_user = logic.login(user);
		//ログインに失敗
		if(login_user == null) {
			MessageSettings ms = new MessageSettings();
			request.setAttribute("error", ms.MSG_LOGIN_FAILURE);
			// TODO一覧表へフォワード
			RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/jsp/login/index.jsp");
			dispatcher.forward(request, response);
		} 
		//ログインに成功
		else {
			//sessionに保存
			HttpSession session = request.getSession();
			session.setAttribute("login_user", login_user);
			// TODO一覧表へフォワード
			RequestDispatcher dispatcher = request.getRequestDispatcher("/MainServlet");
			dispatcher.forward(request, response);
		}
	}

}
