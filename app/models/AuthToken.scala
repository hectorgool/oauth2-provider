package models


import java.util.UUID
import org.joda.time.DateTime


case class AccessToken(
	accessToken: String, 
	refreshToken: Option[String], 
	userGuid: UUID, 
	scope: Option[String], 
	expiresIn: Option[Long], 
	createdAt: DateTime, 
	clientId: Option[String]
)