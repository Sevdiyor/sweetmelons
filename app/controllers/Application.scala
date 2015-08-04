package controllers

import play.api._
import play.api.mvc._

object Application extends Controller {

  def index = Action {
    Ok(views.html.index("bbn"))
  }
  def verification = Action {
    Ok(views.html.verification())
  }
  def verificationAdmin = Action {
    Ok(views.html.verificationAdmin())
  }
}