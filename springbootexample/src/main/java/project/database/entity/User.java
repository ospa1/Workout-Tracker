package project.database.entity;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import lombok.Data;

@Entity
@Table(name = "user")
@Data
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Integer id;

	@Column(name = "email", nullable = false)
	private String email;

	@Column(name = "password", nullable = false)
	private String password;

	// can set nullable to true and let the db do it
	@Column(name = "date_created", nullable = false, updatable = false)
	@Temporal(TemporalType.TIMESTAMP)
	private Date date_created = new Date();

	@OneToMany(mappedBy = "user", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private List<Exercise> exercises;

//	@Basic(fetch = FetchType.LAZY)
//	@OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
//	private List<UserRole> userRoles = new ArrayList<UserRole>();
}
