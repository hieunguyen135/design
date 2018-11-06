package anduin.guide.page

import anduin.component.menu.MenuDivider
import anduin.guide.button.Button
import anduin.guide.Router
import anduin.guide.component.SimpleState
import anduin.mcro.Source
import anduin.style.Style
import japgolly.scalajs.react.vdom.html_<^._

object PageButtonTest {
  def render(ctl: Router.Ctl): VdomElement = {
    <.div(
      Toc(content = Source.toc())(),
      <.header(
        Style.margin.bottom32,
        Header(title = "Button", obj = Some(Button))()
      ),
      ExampleSimple()({
        SimpleState.Bool(
          initialValue = false,
          render = (isDark, setIsDark) => {
            SimpleState.Bool(
              initialValue = false,
              render = (isSelected, setIsSelected) => {
                SimpleState.Bool(
                  initialValue = false,
                  render = (isDisabled, setIsDisabled) => {
                    val margin = Style.margin.right12
                    <.div(
                      if (isDark) Style.backgroundColor.gray8 else Style.backgroundColor.white,
                      Style.padding.all20,
                      <.div(
                        Style.flexbox.flex,
                        <.div(margin, Button(onClick = setIsDark(!isDark))("Toggle Bg Color")),
                        <.div(margin, Button(onClick = setIsSelected(!isSelected))("Toggle isSelected")),
                        <.div(margin, Button(onClick = setIsDisabled(!isDisabled))("Toggle isDisabled"))
                      ),
                      <.div(Style.padding.ver8, MenuDivider()()),
                      <.div(
                        Style.flexbox.flex,
                        <.div( margin, Button( style = Button.Style.Full, color = Button.Color.White, isDisabled = isDisabled, isSelected = isSelected )("White") ),
                        <.div( margin, Button( style = Button.Style.Full, color = Button.Color.Black, isDisabled = isDisabled, isSelected = isSelected )("Black") ),
                        <.div( margin, Button( style = Button.Style.Full, color = Button.Color.Red, isDisabled = isDisabled, isSelected = isSelected )("Red") ),
                        <.div( margin, Button( style = Button.Style.Full, color = Button.Color.Orange, isDisabled = isDisabled, isSelected = isSelected )("Orange") ),
                        <.div( margin, Button( style = Button.Style.Full, color = Button.Color.Green, isDisabled = isDisabled, isSelected = isSelected )("Green") ),
                        <.div( margin, Button( style = Button.Style.Full, color = Button.Color.Blue, isDisabled = isDisabled, isSelected = isSelected )("Blue") ),
                      ),
                      <.div(
                        Style.flexbox.flex.margin.top16,
                        <.div( margin, Button( style = Button.Style.Ghost, color = Button.Color.White, isDisabled = isDisabled, isSelected = isSelected )("White") ),
                        <.div( margin, Button( style = Button.Style.Ghost, color = Button.Color.Black, isDisabled = isDisabled, isSelected = isSelected )("Black") ),
                        <.div( margin, Button( style = Button.Style.Ghost, color = Button.Color.Red, isDisabled = isDisabled, isSelected = isSelected )("Red") ),
                        <.div( margin, Button( style = Button.Style.Ghost, color = Button.Color.Orange, isDisabled = isDisabled, isSelected = isSelected )("Orange") ),
                        <.div( margin, Button( style = Button.Style.Ghost, color = Button.Color.Green, isDisabled = isDisabled, isSelected = isSelected )("Green") ),
                        <.div( margin, Button( style = Button.Style.Ghost, color = Button.Color.Blue, isDisabled = isDisabled, isSelected = isSelected )("Blue") ),
                      ),
                      <.div(
                        Style.flexbox.flex.margin.top16,
                        <.div( margin, Button( style = Button.Style.Minimal, color = Button.Color.White, isDisabled = isDisabled, isSelected = isSelected )("White") ),
                        <.div( margin, Button( style = Button.Style.Minimal, color = Button.Color.Black, isDisabled = isDisabled, isSelected = isSelected )("Black") ),
                        <.div( margin, Button( style = Button.Style.Minimal, color = Button.Color.Red, isDisabled = isDisabled, isSelected = isSelected )("Red") ),
                        <.div( margin, Button( style = Button.Style.Minimal, color = Button.Color.Orange, isDisabled = isDisabled, isSelected = isSelected )("Orange") ),
                        <.div( margin, Button( style = Button.Style.Minimal, color = Button.Color.Green, isDisabled = isDisabled, isSelected = isSelected )("Green") ),
                        <.div( margin, Button( style = Button.Style.Minimal, color = Button.Color.Blue, isDisabled = isDisabled, isSelected = isSelected )("Blue") ),
                      ),
                      <.div(
                        Style.flexbox.flex.margin.top16,
                        <.div( margin, Button( style = Button.Style.Link, color = Button.Color.White, isDisabled = isDisabled, isSelected = isSelected )("White") ),
                        <.div( margin, Button( style = Button.Style.Link, color = Button.Color.Black, isDisabled = isDisabled, isSelected = isSelected )("Black") ),
                        <.div( margin, Button( style = Button.Style.Link, color = Button.Color.Red, isDisabled = isDisabled, isSelected = isSelected )("Red") ),
                        <.div( margin, Button( style = Button.Style.Link, color = Button.Color.Orange, isDisabled = isDisabled, isSelected = isSelected )("Orange") ),
                        <.div( margin, Button( style = Button.Style.Link, color = Button.Color.Green, isDisabled = isDisabled, isSelected = isSelected )("Green") ),
                        <.div( margin, Button( style = Button.Style.Link, color = Button.Color.Blue, isDisabled = isDisabled, isSelected = isSelected )("Blue") ),
                      ),
                    )
                  }
                )()
              }
            )()
          }
        )()
        // xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx
      })
    )
  }
}
