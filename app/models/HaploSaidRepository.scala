package models

import scala.slick.driver.PostgresDriver.simple._
import com.github.tototoshi.slick.PostgresJodaSupport._
import org.joda.time._
import haplothings.boundaries.Repository
import haplothings.boundaries.datamodels.HaploSaid

//A HaploSaid table storing shit haplo says.

class HaploSaids(tag: Tag)
  extends Table[HaploSaid]( tag, "haplosaid" ){

    def id          = column[Int]( "id", O.PrimaryKey, O.AutoInc )
    def what_said   = column[String]( "what_said" )
    def context_note= column[Option[String]]( "context_note" )
    def created_at  = column[Option[DateTime]]( "created_at" )
    def who_said    = column[String]("who_said", O.Default( "Haplo" ) )
    def * =  ( id, what_said, context_note, created_at, who_said ) <> ( HaploSaid.tupled, HaploSaid.unapply )

}

class HaploSaidRepository( implicit s: Session ) extends Repository[HaploSaid] {

  val haploSaids = TableQuery[HaploSaids]

  def insert( haploSaid: HaploSaid ){
    haploSaids.map( h => ( h.what_said, h.context_note, h.who_said ) ) +=
      ( haploSaid.what_said, haploSaid.context_note, haploSaid.who_said )
  }

  def shitHaploSaid( limit: Int ): List[HaploSaid] = {
    page( limit, 1 )
  }

  def find( id: Int ): HaploSaid = {
    haploSaids.filter( _.id === id ).firstOption.get
  }

  def take( limit: Int, skip: Int = 0): List[HaploSaid] = {
    haploSaids.drop(skip).take(limit).list
  }

  def page( pageSize: Int, pageNum: Int ): List[HaploSaid] = {
    val offset = ( pageNum - 1 ) * pageSize
    haploSaids.sortBy( _.created_at.desc ).drop( offset ).take( pageSize ).list
  }

}


