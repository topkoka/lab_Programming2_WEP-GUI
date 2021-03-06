package app.user;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.model.beans.CustomerModel;
import org.model.dao.CustomerDAO;
import org.model.db.Database;

/**
 * Servlet implementation class EditUser
 */
@WebServlet("/EditUser.do")
public class EditUser extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void processRequest(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html;charset=UTF-8");
		HttpSession session = request.getSession();
		try (PrintWriter out = response.getWriter()) {

			if (session.getAttribute("username") != null) {
				Database db = new Database();
				CustomerDAO cusDAO = new CustomerDAO(db);
				int userId = Integer.parseInt(request.getParameter("customerId"));
				CustomerModel cusModel = cusDAO.FindByID(userId);
				request.setAttribute("cusModel", cusModel);
				db.close();

				RequestDispatcher rd = request.getRequestDispatcher("/app/user/edituser.jsp");
				rd.forward(request, response);
			} else {
				response.sendRedirect(request.getContextPath() + "/Login.do");
			}
		} catch (Exception e) {
		}

	}

	/**
	 * @see Servlet#getServletInfo()
	 */
	public String getServletInfo() {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		// response.getWriter().append("Served at:
		// ").append(request.getContextPath());
		processRequest(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		// doGet(request, response);
		processRequest(request, response);
	}

}
