package dao


import com.github.tototoshi.slick.MySQLJodaSupport._
import java.util.UUID
import javax.inject.Inject
import models._
import org.joda.time.DateTime
import play.api.db.slick.DatabaseConfigProvider
import play.api.db.slick.HasDatabaseConfigProvider
import play.api.libs.concurrent.Execution.Implicits.defaultContext
import scala.concurrent.Future
import slick.driver.JdbcProfile


class UserDAO @Inject()(protected val dbConfigProvider: DatabaseConfigProvider) extends HasDatabaseConfigProvider[JdbcProfile] {


	import driver.api._

	private val Users = TableQuery[UsersTable]

	def all(): Future[Seq[User]] = db.run(Users.result)

	def insert(user: User): Future[Unit] = db.run(Users += user).map { _ => () }


	private class UsersTable(tag: Tag) extends Table[User](tag, "users") {

		def guid          = column[UUID]("guid", O.PrimaryKey)
		def firstName     = column[String]("first_name")
		def lastName      = column[String]("last_name")
		def email         = column[String]("email")
		def password      = column[String]("password")
		def salt          = column[Option[String]]("salt")
		def createdAt     = column[DateTime]("created_at")
		def createdByGuid = column[UUID]("created_by_guid")
		def updatedAt     = column[DateTime]("updated_at")
		def updatedByGuid = column[UUID]("updated_by_guid")
		def deletedAt     = column[Option[DateTime]]("deleted_at")
		def deletedByGuid = column[Option[UUID]]("deleted_by_guid")
		def *             = (guid, firstName, lastName, email, password, salt, createdAt, createdByGuid, updatedAt, updatedByGuid, deletedAt, deletedByGuid) <> (User.tupled, User.unapply)

	}


}