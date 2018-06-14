package br.com.artivinco.consultaboleto.security;

import static br.com.artivinco.consultaboleto.security.SecurityConstants.EXPIRATION_TIME;
import static br.com.artivinco.consultaboleto.security.SecurityConstants.HEADER_STRING;
import static br.com.artivinco.consultaboleto.security.SecurityConstants.SECRET;
import static br.com.artivinco.consultaboleto.security.SecurityConstants.TOKEN_PREFIX;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Date;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;

import br.com.artivinco.consultaboleto.json.LoginImport;
import br.com.artivinco.consultaboleto.json.LoginReturn;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

public class JWTAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

	private AuthenticationManager authenticationManager;
	
	public JWTAuthenticationFilter(AuthenticationManager authenticationManager) {
		this.authenticationManager = authenticationManager;
	}

	@Override
	public Authentication attemptAuthentication(HttpServletRequest req, HttpServletResponse res)
			throws AuthenticationException {

		try {
			LoginImport creds = new ObjectMapper().readValue(req.getInputStream(), LoginImport.class);
			return authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(creds.getUsuario(),
					creds.getSenhaUsuario(), new ArrayList<>()));
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}
	

	@Override
	protected void successfulAuthentication(HttpServletRequest req, HttpServletResponse res, FilterChain chain,
			Authentication auth) throws IOException, ServletException {
		String token = Jwts.builder().setSubject(((User) auth.getPrincipal()).getUsername())
				.setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
				.signWith(SignatureAlgorithm.HS512, SECRET).compact();
		
		res.setContentType("application/json");
		res.setCharacterEncoding("utf-8");
		res.addHeader(HEADER_STRING, TOKEN_PREFIX + token);
		PrintWriter writer = res.getWriter();
		LoginReturn loginReturn = new LoginReturn();

		loginReturn.setToken(TOKEN_PREFIX + token);
		loginReturn.setLogin(auth.getName());

		ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
		String json = ow.writeValueAsString(loginReturn);
		System.out.println(json);
		writer.print(json);

	}
}