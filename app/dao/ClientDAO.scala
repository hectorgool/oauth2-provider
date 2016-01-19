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


class ClientDAO @Inject()(protected val dbConfigProvider: DatabaseConfigProvider) extends HasDatabaseConfigProvider[JdbcProfile] {


	import driver.api._

	private val Clients = TableQuery[ClientsTable]

	def all(): Future[Seq[Client]] = db.run(Clients.result)

	def insert(client: Client): Future[Unit] = db.run(Clients += client).map { _ => () }


	private class ClientsTable(tag: Tag) extends Table[Client](tag, "clients") {

		def id = column[String]("id", O.PrimaryKey)
		def secret = column[Option[String]]("secret")
		def redirectUri = column[Option[String]]("redirect_uri")
		def scope = column[Option[String]]("scope")
		def * = (id, secret, redirectUri, scope) <> (Client.tupled, Client.unapply)

	}


}