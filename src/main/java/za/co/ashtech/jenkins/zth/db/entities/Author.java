package za.co.ashtech.jenkins.zth.db.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table
public class Author {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Integer id;
	private String name;
    @ManyToOne
    @JoinColumn(name = "book_id")
	private Book book;
	
	
	public Author() {
		super();
	}
	
	public Author(String name) {
		super();
		this.name = name;
	}


	public Integer getId() {
		return id;
	}


	public void setId(Integer id) {
		this.id = id;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public Book getBook() {
		return book;
	}


	public void setBook(Book book) {
		this.book = book;
	}


	@Override
	public String toString() {
		return "Author [id=" + id + ", name=" + name + ", book=" + book + "]";
	}	

}
