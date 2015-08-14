package controllers;

import play.mvc.Controller;
import play.mvc.Http.MultipartFormData;
import play.mvc.Http.MultipartFormData.FilePart;
import play.mvc.Result;
import play.twirl.api.Html;

import java.io.File;
import java.io.IOException;
import java.util.Date;

import com.google.common.io.Files;

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
	    FilePart picture = body.getFile("image");
	    if (picture != null) {
	        String fileName = picture.getFilename();
	        Logger.info(fileName);
	        String contentType = picture.getContentType();
	        Logger.info(contentType);
	        File file = picture.getFile();
	        Date now = new Date();
	        long timestamp = now.getTime();
	        File uploadFile = new File("public/upload/"+timestamp+ "_" + fileName);
	        try {
				Files.copy(file, uploadFile);
			} catch (IOException e) {				
				e.printStackTrace();
			}
	        return ok("upload/"+timestamp+ "_" + fileName);
	    } else {
	        flash("error", "Missing file");
	        return badRequest();
	    }
	    
	}
}
