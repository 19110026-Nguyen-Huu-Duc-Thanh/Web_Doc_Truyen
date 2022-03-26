package web;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.List;
/*import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.io.PrintWriter;
import net.bytebuddy.utility.RandomString;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;*/
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import javax.servlet.http.HttpSession;
import dao.UserDAO;
import model.Content;
import model.User;

/**
 * Servlet implementation class UserServlet
 */
@WebServlet("/")
public class UserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private UserDAO userDAO;

	public void init() {
		userDAO = new UserDAO();
	}

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public UserServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	PrintWriter output;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String action = request.getServletPath();

		try {
			switch (action) {
			case "/insertUser":
				insertUser(request, response);
				break;
			case "/Home":
				loginUser(request, response);
				break;
			case "/logout":
				logoutUser(request, response);
				break;
			case "/AddContent":
				insertContent(request, response);
				break;
			case "/view-content":
				viewcontentForm(request, response);
				break;
			case "/editProfileForm":
				editProfileForm(request, response);
				break;
			case "/editProfile":
				editProfile(request, response);
				break;
			case "/updateContentForm":
				updateContentForm(request, response);
				break;
			case "/deleteContent":
				deleteContent(request, response);
				break;
			case "/updateContent":
				updateContent(request, response);
				break;
			case "/viewContentForm":
				viewContentForm(request, response);
				break;
			case "/viewContentPage":
				viewContentPage(request, response);
				break;
			}
		} catch (SQLException ex) {
			throw new ServletException(ex);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

	private void insertContent(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, IOException, ServletException {
		response.setContentType("text/html;charset=UTF-8");
		request.setCharacterEncoding("UTF-8");
		String tiles = request.getParameter("tiles");
		String brief = request.getParameter("brief");
		String content = request.getParameter("content");
		String checker = request.getParameter("name");
		int authorid = Integer.parseInt(request.getParameter("id"));
		Content a = new Content(tiles, brief, content, authorid);
		User checkname = userDAO.selectUserBYidname(authorid, checker);
		if (checkname == null) {
			request.getRequestDispatcher("logout").forward(request, response);
		} else {
			userDAO.insertContent(a);
			RequestDispatcher dispatcher = request.getRequestDispatcher(
					"home-page.tiles?id=" + authorid + "&name=" + checker + "&static=Add_successful");
			dispatcher.forward(request, response);
		}
	}

	private void insertUser(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, IOException, ServletException {
		response.setContentType("text/html;charset=UTF-8");
		String user = request.getParameter("username");
		String pass = request.getParameter("password");
		String email = request.getParameter("email");
		User checkname = userDAO.selectUserBYname(email);
		try {
			User newUser = new User(user, pass, email);
			if (checkname != null) {
				request.setAttribute("mess", "Email already exists");
				request.setAttribute("user", newUser);
				request.getRequestDispatcher("views/register.jsp").forward(request, response);

			} else {
				userDAO.insertUser(newUser);
				HttpSession session = request.getSession();
				session.setAttribute("user", newUser);
				RequestDispatcher dispatcher = request.getRequestDispatcher("views/registers_success.jsp");
				dispatcher.forward(request, response);
			}
		} catch (Exception e) {
			output = response.getWriter();
			output.println(e);
			// TODO: handle exception
		}
	}

	private void editProfileForm(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html;charset=UTF-8");
		request.setCharacterEncoding("UTF-8");
		String checker = request.getParameter("name");
		int authorid = Integer.parseInt(request.getParameter("id"));
		User user = userDAO.selectUserBYidname(authorid, checker);
		if (user == null) {
			RequestDispatcher dispatcher = request
					.getRequestDispatcher("home-page.tiles?id=" + authorid + "&name=" + checker);
			dispatcher.forward(request, response);
		} else {
			RequestDispatcher dispatcher = request.getRequestDispatcher("edit-profile.tiles?id=" + authorid + "&name="
					+ checker + "&lname=" + user.getLname() + "&fname=" + user.getFname() + "&phone=" + user.getPhone()
					+ "&email=" + user.getEmail() + "&des=" + user.getDescription());
			dispatcher.forward(request, response);
		}
	}

	private void editProfile(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, IOException, ServletException {
		response.setContentType("text/html;charset=UTF-8");
		request.setCharacterEncoding("UTF-8");
		String checker = request.getParameter("name");
		int authorid = Integer.parseInt(request.getParameter("id"));
		int phone = Integer.parseInt(request.getParameter("phone"));
		String firstname = request.getParameter("firstname");
		String lastname = request.getParameter("lastname");
		String email = request.getParameter("email");
		String des = request.getParameter("des");
		User newuser = new User(authorid, firstname, lastname, phone, email, des);
		if (userDAO.updateUser(newuser)) {
			RequestDispatcher dispatcher = request.getRequestDispatcher(
					"home-page.tiles?id=" + authorid + "&name=" + checker + "&static=update_profile_succssfull");
			dispatcher.forward(request, response);
		} else {
			String mess = "Error_Infomation_Can't_Update_Content";
			RequestDispatcher dispatcher = request
					.getRequestDispatcher("home-page.tiles?id=" + authorid + "&name=" + checker + "&static=" + mess);
			dispatcher.forward(request, response);
		}
	}

	private void viewContentForm(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		RequestDispatcher dispatcher;
		dispatcher = request.getRequestDispatcher("viewContentPage?Page=0");
		dispatcher.forward(request, response);
	}

	private void viewContentPage(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html;charset=UTF-8");
		request.setCharacterEncoding("UTF-8");
		String checker = request.getParameter("name");
		String search = request.getParameter("search");
		String authorid_check = request.getParameter("id");
		if (authorid_check == null) {
			authorid_check = "1";
		}
		int authorid = Integer.parseInt(authorid_check);
		UserDAO content = new UserDAO();
		int numPage = content.getNumPage(authorid, search);
		int pageReq = Integer.parseInt(request.getParameter("Page"));
		if (numPage < pageReq || 0 > pageReq) {
			response.sendRedirect(
					"view-content.tiles?Page=0&numId=0&id=" + authorid + "&name=" + checker + "&search=" + search);
		} else {
			String NumPageButton = "&id=" + authorid + "&name=" + checker + "&search=" + search;
			int currentBt = 1;
			int butP = 1;
			if (numPage != 0) {
				{
					while (currentBt <= 5) {
						if (pageReq - butP >= 0) {
							NumPageButton = NumPageButton + "&Bt" + Integer.toString(currentBt) + "="
									+ Integer.toString(pageReq - butP);
							currentBt++;
							butP++;
						} else
							break;
						if (pageReq - butP - 1 > 0 && currentBt == 5) {
							NumPageButton = NumPageButton + "&BtFirst=" + Integer.toString(0);
						}
					}
					currentBt = 6;
					butP = 1;
					while (currentBt <= 10) {
						if (pageReq + butP <= numPage) {
							NumPageButton = NumPageButton + "&Bt" + Integer.toString(currentBt) + "="
									+ Integer.toString(pageReq + butP);
							currentBt++;
							butP++;
						} else
							break;
						if (pageReq + butP - 1 < numPage && currentBt == 10) {
							NumPageButton = NumPageButton + "&BtLast=" + Integer.toString(numPage);
						}
					}
				}
			}
			int numId = content.getNumId(authorid, search);
			if (pageReq != 0) {
				numId = numId - numPage * 10;
				List<Content> listContent = content.selectAllContents(10, pageReq, authorid, search);
				request.setAttribute("listContent", listContent);
				RequestDispatcher dispatcher = request
						.getRequestDispatcher("view-content.tiles?Page=" + pageReq + "&numId=" + numId + NumPageButton);
				dispatcher.forward(request, response);
			} else {

				if (numPage >= 1) {
					List<Content> listContent = content.selectAllContents(10, pageReq, authorid, search);
					request.setAttribute("listContent", listContent);
					RequestDispatcher dispatcher = request
							.getRequestDispatcher("view-content.tiles?Page=0&numId=10" + NumPageButton);
					dispatcher.forward(request, response);
				} else {
					List<Content> listContent = content.selectAllContents(numId, pageReq, authorid, search);
					request.setAttribute("listContent", listContent);
					RequestDispatcher dispatcher = request
							.getRequestDispatcher("view-content.tiles?Page=0&numId=" + numId + NumPageButton);
					dispatcher.forward(request, response);
				}
			}
		}
	}

	private void deleteContent(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, IOException {
		response.setContentType("text/html;charset=UTF-8");
		request.setCharacterEncoding("UTF-8");
		String checker = request.getParameter("name");
		int authorid = Integer.parseInt(request.getParameter("id"));
		int id = Integer.parseInt(request.getParameter("idC"));
		if (id != 1) {
			if (userDAO.deleteContent(id, authorid))
				response.sendRedirect("home-page.tiles?static=done delete&id=" + authorid + "&name=" + checker);
			else
				response.sendRedirect("home-page.tiles?static=can't delete content&id=" + authorid + "&name=" + checker);
		} else
			response.sendRedirect("viewContentForm?id=" + authorid + "&name=" + checker);

	}

	private void updateContentForm(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, IOException, ServletException {
		response.setContentType("text/html;charset=UTF-8");
		request.setCharacterEncoding("UTF-8");
		String checker = request.getParameter("name");
		int authorid = Integer.parseInt(request.getParameter("id"));
		int id = Integer.parseInt(request.getParameter("idC"));
		Content newcontent = userDAO.selectContentBYid(id, authorid);
		if (newcontent == null) {
			RequestDispatcher dispatcher = request
					.getRequestDispatcher("home-page.tiles?id=" + authorid + "&name=" + checker + "&static=fail");
			dispatcher.forward(request, response);
		} else {
			RequestDispatcher dispatcher = request.getRequestDispatcher("add-content.tiles?id=" + authorid + "&name="
					+ checker + "&static=0&idC=" + newcontent.getId() + "&title=" + newcontent.getTitle() + "&brief="
					+ newcontent.getBrief() + "&contentn=" + newcontent.getContent() + "&sort=" + newcontent.getSort());
			dispatcher.forward(request, response);
		}
	}

	private void updateContent(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, IOException, ServletException {
		response.setContentType("text/html;charset=UTF-8");
		request.setCharacterEncoding("UTF-8");
		String checker = request.getParameter("name");
		int authorid = Integer.parseInt(request.getParameter("id"));
		int id = Integer.parseInt(request.getParameter("idC"));
		int sort = Integer.parseInt(request.getParameter("sort"));
		String tiles = request.getParameter("tiles");
		String brief = request.getParameter("brief");
		String content = request.getParameter("content2");
		Content newcontent = new Content(id, tiles, brief, content, sort, authorid);
		if (userDAO.updateContent(newcontent)) {
			RequestDispatcher dispatcher = request.getRequestDispatcher(
					"home-page.tiles?id=" + authorid + "&name=" + checker + "&static=Update_successfull");
			dispatcher.forward(request, response);
		} else {
			String mess = "Error Infomation - Can't Update Content";
			RequestDispatcher dispatcher = request.getRequestDispatcher(
					"home-page.tiles?id=" + authorid + "&name=" + checker + "&idC=" + id + "&static=" + mess);
			dispatcher.forward(request, response);
		}
	}

	private void viewcontentForm(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html;charset=UTF-8");
		request.setCharacterEncoding("UTF-8");
		String user = request.getParameter("");
		String authorid_check = request.getParameter("id");
		if (authorid_check == null) {
			authorid_check = "1";
		}
		int authorid = Integer.parseInt(authorid_check);
		response.sendRedirect("view-content.tiles?id=" + authorid + "&name=" + user);
	}

	private void loginUser(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, IOException, ServletException {
		response.setContentType("text/html;charset=UTF-8");
		request.setCharacterEncoding("UTF-8");
		String email = request.getParameter("email");
		String password = request.getParameter("password");
		try {
			User user = userDAO.login(email, password);
			if (user == null) {
				request.setAttribute("mess", "Wrong user or pass");
				request.getRequestDispatcher("index.jsp").forward(request, response);
			} else {
				RequestDispatcher dispatcher = request
						.getRequestDispatcher("editProfileForm?id=" + user.getId() + "&name=" + user.getUsername());
				HttpSession session = request.getSession();
				session.setAttribute("user", user);
				session.setMaxInactiveInterval(1000);
				dispatcher.forward(request, response);

			}
		} catch (Exception e) {
			output = response.getWriter();
			output.println(e);
			// TODO: handle exception
		}
	}

	private void logoutUser(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, IOException, ServletException {
		response.setContentType("text/html;charset=UTF-8");
		request.setCharacterEncoding("UTF-8");
		HttpSession session = request.getSession();
		session.removeAttribute("user");
		request.getSession().invalidate();
		response.sendRedirect("index.jsp");
	}

}
