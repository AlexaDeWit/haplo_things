package haplothings.boundaries

trait RequestBoundary {

  def getResponse

  def validateRequest
}
