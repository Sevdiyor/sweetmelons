package controllers

import play.api._
import play.api.mvc._

object Application extends Controller {

  def index = Action {
    Ok(views.html.index("bbn"))
  }
//  def addQuestion = Action {
//    Ok(views.html.addQuestion())
//  }
//  def questionList = Action {
//    Ok(views.html.questionList())
//  }
//  def questionListAdmin = Action {
//    Ok(views.html.questionListAdmin())
//  }
  def verification = Action {
    Ok(views.html.verification())
  }
  def verificationAdmin = Action {
    Ok(views.html.verificationAdmin())
  }
  def passingTest = Action {
    Ok(views.html.passingTest())
  }
}