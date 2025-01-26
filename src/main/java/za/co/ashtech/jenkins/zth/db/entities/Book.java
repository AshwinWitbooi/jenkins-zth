package za.co.ashtech.jenkins.zth.db.entities;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;


@Entity
@Table
public class Book {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Integer id;
	private String title;
    @OneToMany(mappedBy = "book", cascade = CascadeType.ALL)
    private Set<Author> authors = new HashSet<Author>();
    
    
	public Book() {
		super();
	}

	public Book(String title, Set<Author> authors) {
		super();
		this.title = title;
		this.authors = authors;
	}

	public Integer getId() {
		return id;
	}


	public void setId(Integer id) {
		this.id = id;
	}


	public String getTitle() {
		return title;
	}


	public void setTitle(String title) {
		this.title = title;
	}
	

	public Set<Author> getAuthors() {
		return authors;
	}


	public void setAuthors(Set<Author> authors) {
		this.authors = authors;
	}


	@Override
	public String toString() {
		return "Book [id=" + id + ", title=" + title + ", authors=" + authors + "]";
	}

}
