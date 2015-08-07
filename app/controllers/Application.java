package controllers;

import play.mvc.*;
import play.twirl.api.Html;
import views.html.*;

public class Application extends Controller {

    public Result index() {
        return ok(home.render("Your new application is ready."));
    }
    
    public Result test(){
    	Html content = sign_in_up.render(new Boolean(true),"");
    	return ok(main.render("home",content));
    }

}
