package controllers;

import java.util.Map;

import com.fasterxml.jackson.databind.JsonNode;

import data.SignUpForm;
import models.User;
import play.Logger;
import play.core.routing.Route;
import play.data.Form;
import play.mvc.*;
import play.twirl.api.Html;
import utils.HashUtil;
import views.html.*;

public class Login extends Controller {
	
	public Result login(){
		String username = session("username");
		if(username != null){
			return redirect(controllers.routes.Application.index());
		}
		return loginPage("");
	}
		
	public Result loginPage(String msg){		
		Html content = sign_in_up.render(new Boolean(false),msg);
		return ok(main.render("Login!",content));
	}
	
	public Result loginAction(){
		Map<String, String[]> data = request().body().asFormUrlEncoded();
		String username = data.get("username")[0];
		String password = data.get("password")[0];
		String msg = "";
		
		if(!username.isEmpty()){
			if(password.isEmpty()){
				msg = "password can not empty!";
			}else{
				User user = User.find.where().eq("username", username)
						.eq("password", HashUtil.md5(password)).findUnique();
				if(user != null){
					//msg = username + " is taken!";
					session("username",username);
					return redirect(controllers.routes.Application.index());
				}else{
					msg = "Your username or password is not correct!";
				}
			}
				
		}else{
			msg = "username can not empty!";
		}
		return loginPage(msg);

	}
	
	public Result logout(){
		session().remove("username");
		return redirect(controllers.routes.Application.index());
	}
}
