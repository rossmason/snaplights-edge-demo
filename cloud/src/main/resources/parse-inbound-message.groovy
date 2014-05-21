import groovy.json.JsonSlurper
def slurper = new JsonSlurper()
result = slurper.parseText(message.getPayloadAsString())
if(result.type == 'command_sms') {
	color = result.message.substring(result.message.indexOf(' ') +1)
	if(color.startsWith(':') || color.startsWith(';')) {
	 switch ( color ) {
      case ":)": color = 'yellow'
      case ":(": color = 'blue'
      case ":*": color = 'pink'
      case ":-)": color = 'yellow'
      case ":'(": color = 'blue'
      case ":-*": color = 'pink'
      case ";-)": color = 'cyan'
      case ";)": color = 'cyan'
      default: throw new Exception("We think you sent a smiley but don't recognise it." + color)
     }
    }
	message.setInvocationProperty('color', color)
}
message.setInvocationProperty('sender', result.msisdn)