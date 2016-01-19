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


class ConfirmationTokenDAO @Inject()(protected val dbConfigProvider: DatabaseConfigProvider) extends HasDatabaseConfigProvider[JdbcProfile] {


	import driver.api._

	private val ConfirmationTokens = TableQuery[ConfirmationTokensTable]

	def all(): Future[Seq[ConfirmationToken]] = db.run(ConfirmationTokens.result)

	def insert(confirmationToken: ConfirmationToken): Future[Unit] = db.run(ConfirmationTokens += confirmationToken).map { _ => () }


	private class ConfirmationTokensTable(tag: Tag) extends Table[ConfirmationToken](tag, "confirmation_tokens") {

		def id = column[Long]("id", O.PrimaryKey, O.AutoInc)
		def uuid = column[UUID]("uuid")
		def email = column[String]("email")
		def creationTime = column[DateTime]("creation_time")
		def expirationTime = column[DateTime]("expiration_time")
		def isSignUp = column[Boolean]("is_sign_up")
		def * = (id.?, uuid, email, creationTime, expirationTime, isSignUp) <> (ConfirmationToken.tupled, ConfirmationToken.unapply)

	}


}