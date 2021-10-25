package servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import logic.UsersModelLogic;
import model.UsersModel;
import settings.MessageSettings;

/**
 * Servlet implementation class UserRegisterServlet
 */
@WebServlet("/UserRegisterServlet")
public class UserRegisterServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UserRegisterServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 登録画面（user/register.jsp）にフォワード
		RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/jsp/user/register.jsp");
		dispatcher.forward(request, response);
		return;
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String[] errMsg = new String[4];
		MessageSettings ms = new MessageSettings();
		RequestDispatcher dispatcher;
		UsersModelLogic logic = new UsersModelLogic();
		String result = null;
		
		// 入力データを取得し、チェック
		// Email
		String email = request.getParameter("email");
		result = logic.checkEmail(email);
		if(!result.equals(ms.MSG_VALID)) {
			errMsg[0] = result;
		}
		result = null;
		// password
		String pass1 = request.getParameter("pass1");
		result = logic.checkPass(pass1);
		if(!result.equals(ms.MSG_VALID)) {
			errMsg[1] = result;
		}
		String pass2 = request.getParameter("pass2");
		if(!pass2.equals(pass1)) {
			errMsg[2] = ms.MSG_PASSWORD_NOT_CONSENSUS;
		}
		result = null;
		// 氏名
		String name = request.getParameter("name");
		result = logic.checkName(name);
		if(!result.equals(ms.MSG_VALID)) {
			errMsg[3] = result;
		}
		
		// 入力に不備がある場合は、やり直し
		for(String error : errMsg) {
			if(error != null) {
				request.setAttribute("error", errMsg);
				dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/user/register.jsp");
				dispatcher.forward(request, response);
				return;
			}
		}
		
		// ユーザー登録
		UsersModel user = new UsersModel();
		user.setEmail(email);
		user.setPass(pass1);
		user.setName(name);
		int iResult = logic.register(user);
		if(iResult == ms.EXECUTION_FAILURE) {
			dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/error/error.jsp");
			dispatcher.forward(request, response);
			return;
		}
		dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/user/registerDone.jsp");
		dispatcher.forward(request, response);
		return;
	}

}
