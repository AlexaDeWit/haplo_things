package haplothings.boundaries.requests

case class CreateHaploSaidRequest( what_said: String, context_note: Option[String],
                                   who_said: String )

