package com.stephen.soloproject.models;

import java.util.Date;
import java.util.List;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;
import javax.validation.constraints.Future;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonIgnore;



@Entity
@Table(name="sets")
public class Set {
	
	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	private Long id;
	
	@NotBlank(message= "Title must not be blank!")
	@Size(min=3, message="Title should be more than 3 characters.")
	private String setTitle;
	
	@NotBlank(message= "Address must not be blank!")
	@Size(min=3, message="Address should be more than 3 characters.")
	private String address;
	
	@Future
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date setDate;
	
	@Column(updatable = false)
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date createdAt;
	
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date updatedAt;
	
	
	@PrePersist
	protected void onCreate() {
		this.createdAt = new Date();
	}
	
	@PreUpdate
	protected void onUpdate() {
		this.updatedAt = new Date();
	}
	
	@ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="user_id")
    private User user;
	
	@ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
        name = "likes", 
        joinColumns = @JoinColumn(name = "set_id"), 
        inverseJoinColumns = @JoinColumn(name = "user_id")
    )
    private List<User> likers;
	
	@ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "set_songs",
            joinColumns = @JoinColumn(name = "set_id"),
            inverseJoinColumns = @JoinColumn(name = "song_id")
    )
	private List<Song> setSongs;
	
	
	@OneToMany(mappedBy="set", fetch = FetchType.LAZY)
	@JsonIgnore
	private List<Song> songs;
	
	@OneToMany(mappedBy="set", fetch = FetchType.LAZY)
	private List<Musician> musicians;
	
	public Set() {}

	public Set(String setTitle, String address, Date setDate, User user,List<Song> songs, List<Musician> musicians, List<User> likers) {
		this.setTitle = setTitle;
		this.address = address;
		this.setDate = setDate;
		this.user = user;
		this.songs = songs;
		this.musicians = musicians;
		this.likers = likers;
		
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getSetTitle() {
		return setTitle;
	}

	public void setSetTitle(String setTitle) {
		this.setTitle = setTitle;
	}
	
	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public Date getSetDate() {
		return setDate;
	}

	public void setSetDate(Date setDate) {
		this.setDate = setDate;
	}

	public Date getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}

	public Date getUpdatedAt() {
		return updatedAt;
	}

	public void setUpdatedAt(Date updatedAt) {
		this.updatedAt = updatedAt;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public List<Song> getSongs() {
		return songs;
	}

	public void setSongs(List<Song> songs) {
		this.songs = songs;
	}

	public List<Musician> getMusicians() {
		return musicians;
	}

	public void setMusicians(List<Musician> musicians) {
		this.musicians = musicians;
	}

	public List<User> getLikers() {
		return likers;
	}

	public void setLikers(List<User> likers) {
		this.likers = likers;
	}

    public List<Song> getSetSongs() {
        return setSongs;
    }

    public void setSetSongs(List<Song> setSongs) {
        this.setSongs = setSongs;
    }

	
	
	
	
	
}
