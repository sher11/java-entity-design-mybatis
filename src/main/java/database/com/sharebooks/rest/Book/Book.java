package com.sharebooks.rest.Book;

import java.time.LocalDateTime;
import java.util.UUID;

public class Book implements Entity {
	private long id;
	private UUID uid;
	private String title;
	private String authorName;
	private String category;
	private String subcategory;
	private int pages;
	private long ownerId;
	private String imgSrc;
	private long createdBy;
	private LocalDateTime creationTime;
	private long lastModifiedBy;
	private LocalDateTime lastModificationTime;

	public Book(Builder b) {
		id = b.id;
		uid = b.uid;
		title = b.title;
		authorName = b.authorName;
		category = b.category;
		subcategory = b.subcategory;
		pages = b.pages;
		ownerId = b.ownerId;
		imgSrc = b.imgSrc;
		createdBy = b.createdBy;
		creationTime = b.creationTime;
		lastModifiedBy = b.lastModifiedBy;
		lastModificationTime = b.lastModificationTime;
	}

	public static class Builder {
		private long id;
		private UUID uid;
		private String title;
		private String authorName;
		private String category;
		private String subcategory;
		private int pages;
		private long ownerId;
		private String imgSrc;
		private long createdBy;
		private LocalDateTime creationTime;
		private long lastModifiedBy;
		private LocalDateTime lastModificationTime;

		public Builder id(Long id) {
			this.id = id;
			return this;
		}

		public Builder uid(UUID uid) {
			this.uid = uid;
			return this;
		}

		public Builder title(String title) {
			this.title = title;
			return this;
		}

		public Builder authorName(String title) {
			this.title = title;
			return this;
		}

		public Builder category(String category) {
			this.category = category;
			return this;
		}

		public Builder subcategory(String subcategory) {
			this.subcategory = subcategory;
			return this;
		}

		public Builder pages(int pages) {
			this.pages = pages;
			return this;
		}

		public Builder ownerId(long ownerId) {
			this.ownerId = ownerId;
			return this;
		}

		public Builder imgSrc(String imgSrc) {
			this.imgSrc = imgSrc;
			return this;
		}


		public Builder rentAmount(String title) {
			this.title = title;
			return this;
		}

		public Builder lastModificationTime(LocalDateTime lastModificationTime) {
			this.lastModificationTime = lastModificationTime;
			return this;
		}

		public Builder lastModifiedBy(Long lastModifiedBy) {
			this.lastModifiedBy = lastModifiedBy;
			return this;
		}

		public Builder creationTime(LocalDateTime creationTime) {
			this.creationTime = creationTime;
			return this;
		}

		public Builder createdBy(Long createdBy) {
			this.createdBy = createdBy;
			return this;
		}

		public Book build() {
			return new Book(this);
		}

	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public UUID getUid() {
		return uid;
	}

	public void setUid(UUID uid) {
		this.uid = uid;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getAuthorName() {
		return authorName;
	}

	public void setAuthorName(String authorName) {
		this.authorName = authorName;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getSubcategory() {
		return subcategory;
	}

	public void setSubcategory(String subcategory) {
		this.subcategory = subcategory;
	}

	public int getPages() {
		return pages;
	}

	public void setPages(int pages) {
		this.pages = pages;
	}

	public long getOwnerId() {
		return ownerId;
	}

	public void setOwnerId(long ownerId) {
		this.ownerId = ownerId;
	}

	public String getImgSrc() {
		return imgSrc;
	}

	public void setImgSrc(String imgSrc) {
		this.imgSrc = imgSrc;
	}

	public long getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(long createdBy) {
		this.createdBy = createdBy;
	}

	public LocalDateTime getCreationTime() {
		return creationTime;
	}

	public void setCreationTime(LocalDateTime creationTime) {
		this.creationTime = creationTime;
	}

	public long getLastModifiedBy() {
		return lastModifiedBy;
	}

	public void setLastModifiedBy(long lastModifiedBy) {
		this.lastModifiedBy = lastModifiedBy;
	}

	public LocalDateTime getLastModificationTime() {
		return lastModificationTime;
	}

	public void setLastModificationTime(LocalDateTime lastModificationTime) {
		this.lastModificationTime = lastModificationTime;
	}

}
