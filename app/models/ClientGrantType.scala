package models


import java.util.UUID
import org.joda.time.DateTime


case class ClientGrantType(
	clientId: String, 
	grantTypeId: Int
)
