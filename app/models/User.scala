package dao


import java.util.UUID
import org.joda.time.DateTime


case class User(
	guid: UUID,
	firstName: String,
	lastName: String,
	email: String,
	password: String,
	salt: Option[String],
	createdAt: DateTime,
	createdByGuid: UUID,
	updatedAt: DateTime,
	updatedByGuid: UUID,
	deletedAt: Option[DateTime],
	deletedByGuid: Option[UUID]
)
