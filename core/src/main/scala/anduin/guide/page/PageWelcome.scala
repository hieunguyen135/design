package anduin.guide.page

import japgolly.scalajs.react.vdom.html_<^._

import anduin.component.button.Button
import anduin.guide.Main
import anduin.mcro.Source
import anduin.style.Style

object PageWelcome {
  def render(ctl: Main.Ctl): VdomElement = {
    <.div(
      Style.whiteSpace.pre,
      "Welcome",
      Markdown("""
        | # heading 1
        | ## heading 2a
        | ### heading 3
        | asdasd *a*
        |""".stripMargin)(),
      ExampleRich(
        Source.annotate(
          <.div(
            Style.flexbox.flex,
            <.div(
              Button()("Action")
            ),
            <.div(
              Style.margin.left12,
              Button(
                color = Button.ColorPrimary
              )("Submit")
            )
          )
        ))()
    )
  }
}