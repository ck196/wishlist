package controllers;

import play.mvc.*;
import play.twirl.api.Html;
import views.html.*;

public class Application extends Controller {

    public Result index() {
        return ok(home.render("Your new application is ready."));
    }
    
    public Result test(){
    	Html content = create_wish.render("");
    	return ok(main.render("home",content));
    }

}
