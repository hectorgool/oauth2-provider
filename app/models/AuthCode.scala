package models


import java.util.UUID
import org.joda.time.DateTime


case class AuthCode(
	authorizationCode: String, 
	userGuid: UUID, 
	redirectUri: Option[String], 
	createdAt: DateTime, 
	scope: Option[String], 
	clientId: Option[String], 
	expiresIn: Int
)
