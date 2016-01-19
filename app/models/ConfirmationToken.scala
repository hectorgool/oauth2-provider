package models


import java.util.UUID
import org.joda.time.DateTime


case class ConfirmationToken(
	id: Option[Long] , 
	uuid: UUID, 
	email: String, 
	creationTime: DateTime, 
	expirationTime: DateTime, 
	isSignUp: Boolean
)
