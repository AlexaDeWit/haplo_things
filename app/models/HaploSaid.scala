package models

import scala.slick.driver.PostgresDriver.simple._
import com.github.tototoshi.slick.PostgresJodaSupport._
import org.joda.time._

//A HaploSaid table storing shit haplo says.

case class HaploSaid( id: Option[Int], what_said: String, context_note: Option[String],
                      created_at: Option[DateTime], who_said: String )
case class LiftedHaploSaid( id: Column[Option[Int]], what_said: Column[String],
                            context_note: Column[Option[String]], created_at: Column[Option[DateTime]],
                            who_said: Column[String] )

implicit object HaploSaidShape extends CaseClassShape( LiftedHaploSaid.tupled, HaploSaid.tupled )

class HaploSaids(tag: Tag)
  extends Table[HaploSaid]( tag, "shape_haploSaid" ){

    def id          = column[Option[Int]]( "id", O.PrimaryKey, O.AutoInc )
    def what_said   = column[String]( "what_said" )
    def context_note= column[Option[String]]( "context_note" )
    def created_at  = column[Option[DateTime]]( "created_at" )
    def who_said    = column[String]("who_said", O.Default( "Haplo" ) )
    def * = LiftedHaploSaid( id, what_said, context_note, created_at, who_said )

}

object HaploSaids {
  val haploSaids = TableQuery[HaploSaids]

  def insert( haploSaid: HaploSaid )( implicit s: Session ){
    val tupled = HaploSaid.unapply( haploSaid ).get
    haploSaids.map( h => ( h.what_said, h.context_note, h.who_said ) ) += (tupled._2, tupled._3, tupled._5 )
  }

}


