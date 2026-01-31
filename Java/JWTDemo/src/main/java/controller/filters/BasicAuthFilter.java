package controller.filters;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import utils.JWTManager;

import java.io.IOException;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.MalformedJwtException;

@WebFilter("/*")
public class BasicAuthFilter extends HttpFilter implements Filter {

	private static final long serialVersionUID = 1L;

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse res = (HttpServletResponse) response;

		res.setHeader("Cache-Control", "no-cache,no-store,must-revalidate");
		res.setHeader("Pragma", "no-cache");
		res.setDateHeader("Expires", 0);
		
		String path = req.getServletPath();
		if (path.endsWith("/index.html") || path.endsWith("/login") || path.endsWith("/signup") || path.endsWith(".css")
				|| path.endsWith("/logout")) {

			chain.doFilter(request, response);
			return;
		}
		String token = null;
		if (req.getCookies() != null) {
			for (Cookie c : req.getCookies()) {
				if ("auth_token".equals(c.getName()))
					token = c.getValue();
			}
		}
		if (token == null) {
			res.sendRedirect(req.getContextPath() + "/index.html");
		} else {
			try {
				String user = JWTManager.validate(token);
				System.out.println(token);
				req.setAttribute("user", user);
				System.out.println(req.getAttribute("user"));
				System.out.println("Setting user "+user);
				chain.doFilter(request, response);

			} catch (ExpiredJwtException e) {
				res.sendRedirect(req.getContextPath() + "/index.html");

			} catch (MalformedJwtException e) {
				// CASE: TAMPERED
				res.sendRedirect("https://nicetrymate.netlify.app");
			} catch (Exception e) {
				res.sendRedirect(req.getContextPath() + "/index.html");
			}
		}
	}


}
