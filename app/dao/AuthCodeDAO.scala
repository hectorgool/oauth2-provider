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


class AuthCodeDAO @Inject()(protected val dbConfigProvider: DatabaseConfigProvider) extends HasDatabaseConfigProvider[JdbcProfile] {


	import driver.api._

	private val AuthCodes = TableQuery[AuthCodesTable]

	def all(): Future[Seq[AuthCode]] = db.run(AuthCodes.result)

	def insert(authCode: AuthCode): Future[Unit] = db.run(AuthCodes += authCode).map { _ => () }


	private class AuthCodesTable(tag: Tag) extends Table[AuthCode](tag, "auth_codes") {

		def authorizationCode = column[String]("authorization_code", O.PrimaryKey)
		def userGuid = column[UUID]("user_guid")
		def redirectUri = column[Option[String]]("redirect_uri")
		def createdAt = column[DateTime]("created_at")
		def scope = column[Option[String]]("scope")
		def clientId = column[Option[String]]("client_id")
		def expiresIn = column[Int]("expires_in")
		def * = (authorizationCode, userGuid, redirectUri, createdAt, scope, clientId, expiresIn) <> (AuthCode.tupled, AuthCode.unapply)

	}


}