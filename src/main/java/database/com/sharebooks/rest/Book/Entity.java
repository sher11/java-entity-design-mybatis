package com.sharebooks.rest.Book;

import java.time.LocalDateTime;
import java.util.UUID;

public interface Entity  {
	long getId();

	UUID getUid();
		
	long getCreatedBy();
	
	LocalDateTime getCreationTime();

	long getLastModifiedBy();

	LocalDateTime getLastModificationTime();

	void setLastModifiedBy(long userId);

	void setLastModificationTime(LocalDateTime time);
}