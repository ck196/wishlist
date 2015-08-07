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

public class SignUp extends Controller {
	
	public Result signUp(){		
		return signUpPage("");
	}
		
	public Result signUpPage(String msg){		
		Html content = sign_in_up.render(new Boolean(true),msg);
		return ok(main.render("Sign up",content));
	}
	
	public Result signUpAction(){
		Map<String, String[]> data = request().body().asFormUrlEncoded();
		String username = data.get("username")[0];
		String password = data.get("password")[0];
		String confirm = data.get("confirm")[0];
		Logger.info("Data: " + username + " - " + password + " - " + confirm );
		
		String msg = "";
		
		if(!username.isEmpty()){
			if(password.isEmpty() || confirm.isEmpty()){
				msg = "There is empty field";
			}
			else if(!password.equals(confirm)){
				msg = "Your confirm doesn't match.";
			}else{
				User user = User.find.where().eq("username", username).findUnique();
				if(user != null){
					msg = username + " is taken!";
				}else{
					user = new User();
					user.username = username;
					user.password = HashUtil.md5(password);
					user.save();
					session("username", username);
					return redirect(controllers.routes.Application.index());
				}
			}
				
		}else{
			msg = "username can not empty!";
		}
		return signUpPage(msg);

	}
}
