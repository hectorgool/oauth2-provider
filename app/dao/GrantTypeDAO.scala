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


class GrantTypeDAO @Inject()(protected val dbConfigProvider: DatabaseConfigProvider) extends HasDatabaseConfigProvider[JdbcProfile] {


	import driver.api._

	private val GrantTypes = TableQuery[GrantTypesTable]

	def all(): Future[Seq[GrantType]] = db.run(GrantTypes.result)

	def insert(user: GrantType): Future[Unit] = db.run(GrantTypes += user).map { _ => () }


	private class GrantTypesTable(tag: Tag) extends Table[GrantType](tag, "grant_type") {

		def id = column[Int]("id", O.PrimaryKey)
		def grantType = column[String]("grant_type")
		def * = (id, grantType) <> (GrantType.tupled, GrantType.unapply)

	}


}
