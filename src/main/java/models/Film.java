package models;

import java.sql.Timestamp;

/**
 * Represents a film entity with properties to describe a film's details. This
 * class is annotated to be compatible with JAXB for XML serialization.
 */
public class Film {
	private int id;
	private String title;
	private int year;
	private String director;
	private String stars;
	private String review;
	private Timestamp added;
	private Timestamp lastModified;

	/**
	 * Default constructor for JAXB serialization and deserialization.
	 * It's intentionally empty to satisfy frameworks and libraries that require an empty constructor.
	 */
	public Film() {
		// No fields initialized here.
	}

	/**
	 * Constructs a Film with specified details, useful for creating a new Film
	 * record without an ID (typically before persistence).
	 */
	public Film(String title, int year, String director, String stars, String review) {
		this.title = title;
		this.year = year;
		this.director = director;
		this.stars = stars;
		this.review = review;
	}

	/**
	 * Constructs a Film with full details, including database-specific ID fields.
	 * This constructor is typically used when retrieving existing records from the database.
	 */
	public Film(int id, String title, int year, String director, String stars, String review) {
		this(title, year, director, stars, review); // Reuse the constructor that sets all fields except ID
		this.id = id;
	}

	// Getters and setters for each property to allow for encapsulation and controlled access/modification.

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		this.year = year;
	}

	public String getDirector() {
		return director;
	}

	public void setDirector(String director) {
		this.director = director;
	}

	public String getStars() {
		return stars;
	}

	public void setStars(String stars) {
		this.stars = stars;
	}

	public String getReview() {
		return review;
	}

	public void setReview(String review) {
		this.review = review;
	}

	public Timestamp getAdded() {
		return added;
	}

	public void setAdded(Timestamp added) {
		this.added = added;
	}

	public Timestamp getLastModified() {
		return lastModified;
	}

	public void setLastModified(Timestamp lastModified) {
		this.lastModified = lastModified;
	}

	/**
	 * Provides a string representation of a film, typically used for logging or debugging purposes.
	 * It includes the most relevant film details.
	 */
	@Override
	public String toString() {
		return "Film [id=" + id + ", title=" + title + ", year=" + year + ", director=" + director + ", stars=" + stars
				+ ", review=" + review + "]";
	}
}
