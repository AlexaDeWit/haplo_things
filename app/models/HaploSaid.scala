package models

import scala.slick.driver.PostgresDriver.simple._
import com.github.tototoshi.slick.PostgresJodaSupport._
import org.joda.time._

//A HaploSaid table storing shit haplo says.

case class HaploSaid( id: Option[Int], what_said: String, context_note: Option[String],
                      created_at: Option[DateTime] )

class HaploSaids(tag: Tag)
  extends Table[( Option[Int], String, Option[String], Option[DateTime] )]( tag, "haplosaid" ){

    def id          = column[Option[Int]]( "id", O.PrimaryKey, O.AutoInc )
    def what_said   = column[String]( "what_said" )
    def context_note= column[Option[String]]( "context_note" )
    def created_at  = column[Option[DateTime]]( "created_at" )
    def * = ( id, what_said, context_note, created_at )


}

object HaploSaids {
  val haploSaids = TableQuery[HaploSaids]

  def insert( haploSaid: HaploSaid )( implicit s: Session ){
    val tupled = HaploSaid.unapply( haploSaid ).get
    haploSaids.map( h => ( h.what_said, h.context_note ) ) += (tupled._2, tupled._3 )
  }

}


