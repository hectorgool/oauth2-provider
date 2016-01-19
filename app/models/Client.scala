package models


import java.util.UUID
import org.joda.time.DateTime


case class Client(
	id: String, 
	secret: Option[String], 
	redirectUri: Option[String], 
	scope: Option[String]
)
