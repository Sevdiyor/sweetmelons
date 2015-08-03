package controllers
import models._
import play.api.Logger
import play.api.db.slick.Config.driver.simple._
import play.api.db.slick._
import play.api.mvc._
import scala.slick.lifted.TableQuery
class Questions extends Controller{
  val questionData = TableQuery[QuestionDataTable]

  def questionsList = DBAction { implicit rs =>
    Logger.info(s"SHOW_ALL = ${questionData.list}")
    Ok(views.html.questionList(questionData.list))
  }
  def questionsListAdmin = DBAction { implicit rs =>
    Logger.info(s"SHOW_ALL = ${questionData.list}")
    Ok(views.html.questionListAdmin(questionData.list))
  }

  def showQuestionForm = DBAction { implicit rs =>
    Ok(views.html.addQuestion())
  }

  def addQuestions = DBAction { implicit request =>
    val formParams = request.body.asFormUrlEncoded
    val question = formParams.get("question")(0)
    val aVariant = formParams.get("aVariant")(0)
    val bVariant = formParams.get("bVariant")(0)
    val cVariant = formParams.get("cVariant")(0)
    val dVariant = formParams.get("dVariant")(0)

    val questionId = (questionData returning questionData.map(_.id)) += QuestionData(None, question, aVariant, bVariant, cVariant, dVariant)
    Redirect(routes.Questions.questionsListAdmin())
  }


  def remove(id: Int) = DBAction { implicit request =>
    questionData.filter(_.id === id).delete
    Redirect(routes.Questions.questionsListAdmin())
  }
  def updateQuestions(id: Int) = DBAction { implicit request =>
    val formParams = request.body.asFormUrlEncoded
    val question = formParams.get("question")(0)
    val aVariant = formParams.get("aVariant")(0)
    val bVariant = formParams.get("bVariant")(0)
    val cVariant = formParams.get("cVariant")(0)
    val dVariant = formParams.get("dVariant")(0)

    val testQuestion = QuestionData(Some(id), question, aVariant, bVariant, cVariant, dVariant)
    questionData.filter(_.id === id).update(testQuestion)

    Redirect(routes.Questions.questionsListAdmin())
  }

  def editQuestionsForm(questionId: Int) = DBAction { implicit request =>
    val byId = questionData.findBy(_.id)
    val testQuestion = byId(questionId).list.head

    Ok(views.html.editQuestion(testQuestion))
  }

}
