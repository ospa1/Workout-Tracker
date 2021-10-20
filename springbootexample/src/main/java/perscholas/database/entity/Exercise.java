package perscholas.database.entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Table(name = "exercise")
@Data
public class Exercise {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "exercise_id")
	private Integer id;

	@Column(name = "name", nullable = false)
	private String name;

	// @Column(name = "fk_user_id", nullable = false)
	// private Integer userId;

	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name = "fk_user_id", nullable = false)
	private User user;

	@OneToMany(mappedBy = "exercise", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private List<Set> sets;
}
