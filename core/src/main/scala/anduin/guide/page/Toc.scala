package anduin.guide.page

import japgolly.scalajs.react._
import japgolly.scalajs.react.vdom.html_<^._

import anduin.component.util.ComponentUtils
import anduin.guide.component.Heading
import anduin.style.Style

final case class Toc(
  content: List[(Int, String)]
) {
  def apply(): VdomElement = {
    Toc.component(this)
  }
}

object Toc {

  private final val ComponentName = ComponentUtils.name(this)

  private case class Backend(scope: BackendScope[Toc, _]) {
    def render(props: Toc): VdomElement = {
      <.div(
        Style.position.fixed.coordinate.top0,
        Style.height.pc100.overflow.auto,
        Style.fontSize.px12.backgroundColor.white,
        ^.width := "224px",
        ^.padding := "64px 0",
        ^.left := "1056px",
        props.content.toVdomArray { case (level, rawText) =>
          val text = rawText.replace("`", "")
          <.a(
            Style.color.inherit.display.block,
            ^.marginLeft := s"${level * 16}px",
            ^.href := s"#${Heading.getId(text)}",
            text
          )
        }
      )
    }
  }

  private val component = ScalaComponent
    .builder[Toc](ComponentName)
    .stateless
    .renderBackend[Backend]
    .build
}
