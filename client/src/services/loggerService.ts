enum Colors {
  Black = '\u001b[30m',
  Red = '\u001b[31m',
  Green = '\u001b[32m',
  Yellow = '\u001b[33m',
  Blue = '\u001b[34m',
  Magenta = '\u001b[35m',
  Cyan = '\u001b[36m',
  White = '\u001b[37m',

  BlackBright = '\u001b[30;1m',
  RedBright = '\u001b[31;1m',
  GreenBright = '\u001b[32;1m',
  YellowBright = '\u001b[33;1m',
  BlueBright = '\u001b[34;1m',
  MagentaBright = '\u001b[35;1m',
  CyanBright = '\u001b[36;1m',
  WhiteBright = '\u001b[37;1m',

  Reset = '\u001b[0m'
}

const StringColor = Colors.GreenBright,
  NumberColor = Colors.Blue,
  BooleanColor = Colors.Cyan,
  NullColor = Colors.RedBright,
  UndefinedColor = Colors.MagentaBright,
  KeyColor = Colors.YellowBright

function prettyAndColorfulJSON(value: object) {
  const json = JSON.stringify(value, undefined, '  ').replace(
    /("(\\u[a-zA-Z0-9]{4}|\\[^u]|[^\\"])*"(\s*:)?|\b(true|false|null)\b|-?\d+(?:\.\d*)?(?:[eE][+-]?\d+)?)/g,
    function(match) {
      let style = NumberColor
      if (/^"/.test(match)) {
        if (/:$/.test(match)) {
          style = KeyColor
        } else {
          style = StringColor
        }
      } else if (/true|false/.test(match)) {
        style = BooleanColor
      } else if (/null/.test(match)) {
        style = NullColor
      } else if (/undefined/.test(match)) {
        style = UndefinedColor
      }
      return style + match + '\u001b[0m'
    }
  )

  console.log(json)
}

export default {
  info: console.info,
  warn: console.warn,
  json: prettyAndColorfulJSON
}
