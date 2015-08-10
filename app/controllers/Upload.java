package controllers;

import play.mvc.Controller;
import play.mvc.Http.MultipartFormData;
import play.mvc.Http.MultipartFormData.FilePart;
import play.mvc.Result;
import play.twirl.api.Html;

import java.io.File;

import play.Logger;
import play.mvc.*;
import views.html.*;

public class Upload extends Controller {
	public Result index(){
		Html html = upload.render("");
		return ok(main.render("Upload",html));
	}
	public Result upload(){
		MultipartFormData body = request().body().asMultipartFormData();
	    FilePart picture = body.getFile("picture");
	    if (picture != null) {
	        String fileName = picture.getFilename();
	        Logger.info(fileName);
	        String contentType = picture.getContentType();
	        java.io.File file = picture.getFile();
	        file.renameTo(new File("public/upload/"+fileName));
	        return ok("File uploaded");
	    } else {
	        flash("error", "Missing file");
	        return badRequest();
	    }
	    
	}
}
