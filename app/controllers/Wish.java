package controllers;

import java.util.Map;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ObjectNode;

import models.User;
import play.data.DynamicForm.Dynamic;
import play.data.Form;
import play.libs.Json;
import play.mvc.*;
import play.twirl.api.Html;
import utils.HashUtil;
import views.html.*;

/**
* @author kju
* @createdAt Aug 14, 2015
*/

public class Wish extends Controller{
	public Result create(){
		String username = session("username");
		User user = User.find.where().eq("username", username).findUnique();
		Form<Dynamic> form = Form.form().bindFromRequest();
		JsonNode node = Json.toJson(form.data());
		models.Wish wish = new models.Wish();
		wish.title =  node.get("w-title").asText();
		wish.content = node.get("w-content").asText();
		int rule = node.get("w-rule").asInt();
		switch(rule){
			case 1 : 
				wish.isPublic = true;
				break;
			case 2 : 
				wish.isPublic = false;
				break;
			case 3 :
				wish.hasPassword = true;
				wish.password = HashUtil.md5(node.get("w-password").asText());
		}
		//wish.content = new Html(node.get("w-content").asText());
		
		wish.creator = user;
		wish.imgLink = node.get("w-img").asText();
		wish.save();
		return ok(Json.toJson(wish));
	}
	
	public Result view(long id){
		models.Wish wish = models.Wish.find.byId(id);
		Html htm = new Html(wish.content);
		Html content = wish_view.render(wish, htm);		
		return ok(main.render("View",content));
	}
}
