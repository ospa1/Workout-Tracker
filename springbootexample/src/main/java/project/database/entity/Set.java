package project.database.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import lombok.Data;

@Entity
@Table(name = "`set`")
@Data
public class Set {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Integer id;

	// can set nullable to true and let the db do it
	@Column(name = "date", nullable = false, updatable = false)
	@Temporal(TemporalType.TIMESTAMP)
	private Date date = new Date();

	@Column(name = "weight", nullable = false)
	private Integer weight;

	@Column(name = "reps", nullable = false)
	private Integer reps;

	@Column(name = "fk_user_id", nullable = false)
	private Integer userId;

	// @Column(name = "fk_exercise_id", nullable = false)
	// private Integer exerciseId;

	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name = "fk_exercise_id", nullable = false)
	private Exercise exercise;
}
