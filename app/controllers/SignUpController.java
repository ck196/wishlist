package controllers;

import com.fasterxml.jackson.databind.JsonNode;

import data.SignUpForm;
import play.Logger;
import play.data.Form;
import play.mvc.*;
import play.twirl.api.Html;
import views.html.*;

public class SignUpController extends Controller {
	
	public Result signUpPage(String msg){		
		Html content = sign_in_up.render(new Boolean(true),msg);
		return ok(main.render("Sign up",content));
	}
	
	public Result signUpAction(){
		JsonNode postData = request().body().asJson();
		Form<SignUpForm> form = Form.form(SignUpForm.class).bind(postData);
		SignUpForm data = form.get();
		Logger.info("Data:" + data.getUsername() );
		if(!data.getUsername().isEmpty()){
			if(!data.getPassword().equals(data.getConfirm())){
				String msg = "Your confirm doesn't match.";
				
				return signUpPage(msg);
			}
		}
		return signUpPage("");

	}
}
