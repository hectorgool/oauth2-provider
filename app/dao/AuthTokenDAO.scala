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


class AccessTokenDAO @Inject()(protected val dbConfigProvider: DatabaseConfigProvider) extends HasDatabaseConfigProvider[JdbcProfile] {


	import driver.api._

	private val AccessTokens = TableQuery[AccessTokensTable]

	def all(): Future[Seq[AccessToken]] = db.run(AccessTokens.result)

	def insert(token: AccessToken): Future[Unit] = db.run(AccessTokens += token).map { _ => () }


	class AccessTokensTable(tag: Tag) extends Table[AccessToken](tag, "access_tokens") {

		def accessToken = column[String]("access_token", O.PrimaryKey)
		def refreshToken = column[Option[String]]("refresh_token")
		def userGuid = column[UUID]("user_guid")
		def scope = column[Option[String]]("scope")
		def expiresIn = column[Option[Long]]("expires_in")
		def createdAt = column[DateTime]("created_at")
		def clientId = column[Option[String]]("client_id")
		def * = (accessToken, refreshToken, userGuid, scope, expiresIn, createdAt, clientId) <> (AccessToken.tupled, AccessToken.unapply)

	}


}