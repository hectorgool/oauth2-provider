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


class ClientGrantTypeDAO @Inject()(protected val dbConfigProvider: DatabaseConfigProvider) extends HasDatabaseConfigProvider[JdbcProfile] {


	import driver.api._

	private val ClientGrantTypes = TableQuery[ClientGrantTypesTable]

	def all(): Future[Seq[ClientGrantType]] = db.run(ClientGrantTypes.result)

	def insert(clientGrandType: ClientGrantType): Future[Unit] = db.run(ClientGrantTypes += clientGrandType).map { _ => () }


	private class ClientGrantTypesTable(tag: Tag) extends Table[ClientGrantType](tag, "client_grant_types") {

		def clientId = column[String]("client_id")
		def grantTypeId = column[Int]("grant_type_id")
		def * = (clientId, grantTypeId) <> (ClientGrantType.tupled, ClientGrantType.unapply)
		val pk = primaryKey("pk_client_grant_type", (clientId, grantTypeId))
  
	}


}