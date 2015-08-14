package models;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;

import com.avaje.ebean.Model;
import com.avaje.ebean.annotation.CreatedTimestamp;
import com.avaje.ebean.annotation.UpdatedTimestamp;
import com.fasterxml.jackson.annotation.JsonIgnore;


@Entity
public class User extends Model {
	
	@Id @GeneratedValue(strategy=GenerationType.IDENTITY)
	public long id;
	
	@NotNull
	@Column(unique = true)
	public String username;
	
	@NotNull @JsonIgnore
	public String password;
	
	@Column(name="created_at")
	@CreatedTimestamp
	public Date createdAt;
	
	@Column(name="updated_at")
	@UpdatedTimestamp
	public Date updatedAt;
	
	@OneToMany(mappedBy = "creator")
	List<Wish> wishes;
	
	public static Finder<Long, User> find = new Finder<>(User.class);
	
}
